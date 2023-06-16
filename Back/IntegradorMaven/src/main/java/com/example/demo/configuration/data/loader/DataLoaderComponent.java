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
            Optional<PetDayCare> petDayCareOpt = petDayCareService.findById(petDayCareDTO.getId());
            if (petDayCareOpt.isEmpty()) {
                petDayCareService.saveBD(petDayCareDTO);
            } else {
                System.out.println("pet day care data with id " + petDayCareDTO.getId() + " already exists, skipping creation...");
            }
        });
    }
    public void loadInitialCategoriesData() {
        System.out.println("loading categories data...");
        List<CategoryDTO> petDayCareList = JsonHelper.readJsonFromFile("categories_data.json", new TypeReference<>() {
        });
        petDayCareList.forEach(categoryDTO -> {
            try {
                categoryService.finById(categoryDTO.getId());
                System.out.println("category data with id " + categoryDTO.getId() + " already exists, skipping creation...");
            } catch (ResourceNotFoundException exception) {
                categoryService.save(categoryDTO);
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
                if(userDTO.isValidation()){
                    userService.validation(userDTO.getEmail());
                }
            }
        });
    }


    public void loadInitialBookingData() {
        System.out.println("loading booking data...");
        List<BookingDTO> bookingList = JsonHelper.readJsonFromFile("booking_data.json", new TypeReference<>() {
        });
        bookingList.forEach(booking -> {
            Optional<Booking> booking1 = bookingService.findById(booking.getIdBooking());
            if (booking1.isEmpty()) {
                bookingService.save(booking);
            } else {
                System.out.println("booking data with id " + booking.getIdBooking() + " already exists, skipping creation...");
            }
        });
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
    }
}