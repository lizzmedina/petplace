package com.example.demo.configuration.data.loader;

import com.example.demo.DTO.*;
import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import com.example.demo.utils.JsonHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class DataLoaderComponent {
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PetDayCareService petDayCareService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PetDayCareRepository petDayCareRepository;

    @Autowired
    private BookingScoreRepository bookingScoreRepository;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private FavoriteRepository favoriteRepository;

    public void loadInitialCities() {
        System.out.println("loading cities data...");
        List<PetDayCareDTO> petDayCareList = JsonHelper.readJsonFromFile("petdaycare_data.json", new TypeReference<>() {
        });
        petDayCareList.stream().map(PetDayCareDTO::getCity)
                .filter(Objects::nonNull)
                .map(CityDTO::getName)
                .distinct()
                .forEach(cityName -> {
                    Optional<City> cityOpt = cityRepository.findByName(cityName);
                    if (cityOpt.isEmpty()) {
                        cityRepository.save(new City(cityName));
                    } else {
                        System.out.println("City [" + cityName + "], already exists, skipping creation...");
                    }
                });
    }

    public void loadInitialPetDayCareData() {
        System.out.println("loading pet day care data...");
        List<PetDayCareDTO> petDayCareList = JsonHelper.readJsonFromFile("petdaycare_data.json", new TypeReference<>() {
        });
        petDayCareList.forEach(petDayCareDTO -> {
            try {
                // valida que ya no exista en la BD
                petDayCareService.findById(petDayCareDTO.getId());
                System.out.println("pet day care data with id " + petDayCareDTO.getId() + " already exists, skipping creation...");
            } catch (IllegalArgumentException e) {
                petDayCareService.saveBD(petDayCareDTO);
            }
        });
    }

    public void loadInitialCategoriesData() {
        System.out.println("loading categories data...");
        List<CategoryDTO> petDayCareList = JsonHelper.readJsonFromFile("categories_data.json", new TypeReference<>() {
        });
        petDayCareList.forEach(categoryDTO -> {
            try {
                categoryService.findById(categoryDTO.getId());
                System.out.println("category data with id " + categoryDTO.getId() + " already exists, skipping creation...");
            } catch (ResourceNotFoundException exception) {
                categoryService.save(categoryDTO);
                System.out.println("category " + categoryDTO.getTitle() + " saved successfully...");
            }
        });
    }


    public void loadInitialUserData() {
        System.out.println("loading user data...");
        List<UserDTO> userDTOList = JsonHelper.readJsonFromFile("user_data.json", new TypeReference<>() {
        });
        userDTOList.forEach(userDTO -> {
            try {
                userService.findById(userDTO.getId());
                System.out.println("user data with id " + userDTO.getId() + " already exists, skipping creation...");
            } catch (NoSuchElementException exception) {
                userService.save(userDTO);
                if (userDTO.isValidation()) {
                    userService.validation(userDTO.getEmail());
                }
            }
        });
    }


    public void loadInitialBookingData() {
        System.out.println("loading booking data...");
        List<BookingDTO> bookingList = JsonHelper.readJsonFromFile("booking_data.json", new TypeReference<>() {
        });


        LocalDate today = LocalDate.now();
        int addition = 1;
        for (int i = 0; i < bookingList.size(); i++, addition++) {
            BookingDTO booking = bookingList.get(i);
            Optional<Booking> entityOpt = bookingService.findById(booking.getIdBooking());

            if (entityOpt.isEmpty()) {
                LocalDate checkIn = today.plusDays(addition);
                LocalDate checkOut = today.plusDays(addition += 2);
                PetDayCare petDayCare = petDayCareService.findById(booking.getPetDayCareId());
                booking.setPetDayCare(new PetDayCareDTO(petDayCare));

                BookingCreationRequest creationRequest = new BookingCreationRequest();
                creationRequest.setUserId(booking.getUserId());
                creationRequest.setCheckInDate(checkIn);
                creationRequest.setCheckOutDate(checkOut);
                creationRequest.setPetDayCareId(petDayCare.getId());
                creationRequest.setDataPet(booking.getDataPet());
                bookingService.save(creationRequest);
            } else {
                System.out.println("booking data with id " + booking.getIdBooking() + " already exists, skipping creation...");
            }
        }
    }

    public void loadInitialPermissionData() {
        System.out.println("loading permission data...");
        List<Permission> permissionsList = JsonHelper.readJsonFromFile("permission_data.json", new TypeReference<>() {
        });
        permissionsList.forEach(permission -> {
            Optional<Permission> permissionOpt = permissionRepository.findById(permission.getId());
            if (permissionOpt.isEmpty()) {
                permissionRepository.save(permission);
            } else {
                System.out.println("permission data with id " + permission.getId() + " already exists, skipping creation...");
            }
        });
    }

    public void loadInitialRoleData() {
        System.out.println("loading role data...");
        List<Role> roleList = JsonHelper.readJsonFromFile("role_data.json", new TypeReference<>() {
        });
        roleList.forEach(role -> {
            Optional<Role> roleOpt = roleRepository.findById(role.getId());
            if (roleOpt.isEmpty()) {
                roleRepository.save(role);
            } else {
                System.out.println("role data with id " + role.getId() + " already exists, skipping creation...");
            }
        });
    }

    public void loadRolePermissionAssociations() {
        System.out.println("loading role permission association...");

        // admin roles
        Optional<Role> manager = roleRepository.findByName("Manager");
        permissionRepository.findAll().forEach(permission -> {
            Set<Permission> managerPermissions = manager.get().getPermissions();
            managerPermissions.add(permission);
        });
        roleRepository.save(manager.get());

        // customer roles
        List<HashMap<String, String>> associations = JsonHelper.readJsonFromFile("role_permission_data.json", new TypeReference<>() {
        });
        associations.forEach(association -> {
            Optional<Role> role = roleRepository.findByName(association.get("role"));
            Optional<Permission> permission = permissionRepository.findByName(association.get("permission"));

            role.get().getPermissions().add(permission.get());
            roleRepository.save(role.get());
        });
    }

    private void generateBookingScores() {
        var users = userService.getAllUsers();
        var bookings = bookingRepository.findAll();
        var rnd = new Random();

        int i = 0;
        while (i < bookings.size()){
            var booking = bookings.get(i);
            for (int j = 0; j < 50; j++) {
                var userId = users.get(rnd.nextInt(0, users.size())).getId();
                if(userId != booking.getUser().getId()){
                    BookingScore bookingScore = new BookingScore();
                    bookingScore.setBooking(booking);
                    bookingScore.setUserId(userId);
                    bookingScore.setScore(rnd.nextInt(1, 6));
                    bookingScoreRepository.save(bookingScore);
                }
            }
            i+= 2;
        }
    }

    public void loadInitialFavoriteData() {
        System.out.println("loading favorite data...");
        List<FavoriteDTO> favoriteDTOS = JsonHelper.readJsonFromFile("favorite_data.json", new TypeReference<>() {
        });
        favoriteDTOS.forEach(favoriteDTO -> {
            Optional<Favorite> booking1 = favoriteService.findByID(favoriteDTO.getIdFavorite());
            if (booking1.isEmpty()) {
                favoriteService.save(favoriteDTO);
            } else {
                System.out.println("favorite data with id " + favoriteDTO.getIdFavorite() + " already exists, skipping creation...");
            }
        });
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadInitialCities();
        loadInitialCategoriesData();
        loadInitialRoleData();
        loadInitialPermissionData();
        loadRolePermissionAssociations();
        loadInitialPetDayCareData();
        loadInitialUserData();
        loadInitialBookingData();
        loadInitialFavoriteData();

        generateBookingScores();
    }
}