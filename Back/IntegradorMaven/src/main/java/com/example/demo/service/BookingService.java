package com.example.demo.service;

import com.example.demo.DTO.BookingCreationRequest;
import com.example.demo.DTO.BookingDTO;
import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    private List<Booking> bookingList;
    private BookingRepository bookingRepository;
    private UserRepository userRepository;
    private PetDayCareRepository petDayCareRepository;
    private PetRepository petRepository;

    private CityRepository cityRepository;
    private BookingScoreRepository bookingScoreRepository;


    public BookingService(List<Booking> bookingList,
                          BookingRepository bookingRepository,
                          UserRepository userRepository,
                          PetDayCareRepository petDayCareRepository,
                          PetRepository petRepository,
                          CityRepository cityRepository,
                          BookingScoreRepository bookingScoreRepository) {
        this.bookingList = bookingList;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.petDayCareRepository = petDayCareRepository;
        this.petRepository = petRepository;
        this.cityRepository = cityRepository;
        this.bookingScoreRepository = bookingScoreRepository;
    }

    public BookingDTO save(BookingCreationRequest bookingCreationRequest){

        if(bookingCreationRequest == null){
            throw new IllegalArgumentException("La reserva no puede ser nula");
        }

        User user = userRepository.findById(bookingCreationRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("No se encuentra registrado el usuario con id (%s)", bookingCreationRequest.getUserId())));

        PetDayCare petDayCare = this.petDayCareRepository.findById(bookingCreationRequest.getPetDayCareId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("No se encuentra un alojamiento con id (%s)", bookingCreationRequest.getPetDayCareId())));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setPetDayCare(petDayCare);
        booking.setCheckIn(bookingCreationRequest.getCheckInDate());
        booking.setCheckOut(bookingCreationRequest.getCheckOutDate());
        booking.setCheckInCheckOut(
                List.of(formatter.format(bookingCreationRequest.getCheckInDate()),
                        formatter.format(bookingCreationRequest.getCheckOutDate())));
        booking.setDataPet(bookingCreationRequest.getDataPet());

        if(!available(booking.getPetDayCare().getId(), booking.getCheckIn(), booking.getCheckOut())){
            System.out.println(String.format("pdc: %s, checkIn: %s, checkOut: %s, user: %s"
            ,petDayCare.getId(), bookingCreationRequest.getCheckInDate(), bookingCreationRequest.getCheckOutDate(), bookingCreationRequest.getUserId()));
            throw new RuntimeException("las fechas a reservar no estan disponibles en ese ajolamiento pues ya se encuentra reservado");
        }

        double totalPrice = calculatePrice(booking.getCheckIn(), booking.getCheckOut(), petDayCare.getBasicPrice());
        booking.setTotalPrice(totalPrice);

        var savedBooking = bookingRepository.save(booking);

        booking.setIdBooking(savedBooking.getIdBooking());

        return new BookingDTO(booking);
    }


    public double calculatePrice(LocalDate checkIn, LocalDate checkOut, double basicPrice){

        long totalDays = ChronoUnit.DAYS.between(checkIn, checkOut);
        double total = (totalDays * basicPrice);
        return total;
    }


    public boolean available(Integer petDayCareId, LocalDate checkIn, LocalDate checkOut){
        Integer cantReservas = bookingRepository.disponibilidadQuery(petDayCareId, checkOut, checkIn);
        return checkIn.compareTo(LocalDate.now()) > 0 && cantReservas == 0;
    }

    public List<PetDayCare> search(String city, List<String> checkInCheckOut){
        Optional<City> cityId = cityRepository.findByName(city);

        if(!city.isEmpty() && checkInCheckOut == null){

            List<PetDayCare> petDayCareListByCity = petDayCareRepository.findAllByCityId(cityId.get().getId());

            return petDayCareListByCity;

        }

        LocalDate checkIn = LocalDate.parse(checkInCheckOut.get(0), formatter);
        LocalDate checkOut = LocalDate.parse(checkInCheckOut.get(1), formatter);
        List<Integer> idList = bookingRepository.searchAvailablePetDayCares(cityId.get().getId(), checkIn, checkOut);


        List <PetDayCare> petDayCareListAvailable = petDayCareRepository.findAllById(idList);

        return petDayCareListAvailable;
    }


    public List<BookingDTO> bookingsPetDayCare(Integer idPetDayCare){
        List<Booking> bookingsPetDayCare = bookingRepository.findByPetDayCareId(idPetDayCare);

        List<BookingDTO> bookingDTOList = bookingsPetDayCare.stream()
                .map(BookingDTO::new)
                .toList();

        return bookingDTOList;
    }

    public Booking detail(Integer id){
        Optional<Booking> booking = bookingRepository.findById(id);


        if(!booking.isPresent()){
            throw new RuntimeException("la reserva no existe, verifique el numero de id");
        }

        Booking bookingDetail = new Booking(
                booking.get().getCheckInCheckOut(),
                booking.get().getCheckIn(),
                booking.get().getCheckOut(),
                booking.get().getDataPet(),
                booking.get().getTotalPrice(),
                booking.get().getUser(),
                booking.get().getPetDayCare()
        );

        return bookingDetail;
    }

    public List<Booking> findAll(){
        return bookingRepository.findAll();
    }

    public Optional<Booking> findById(Integer id) {

        return bookingRepository.findById(id);

    }

    public String deleteById(Integer id) {
        Optional<Booking> bookingopt = this.bookingRepository.findById(id);

        if (!bookingopt.isPresent()) {
            throw new ResourceNotFoundException("No existe una categoria registrado con el id: " + id);
        }
        bookingRepository.delete(bookingopt.get());
        return "Se elimino exitosamente la reserva de id: " + id;
    }

    public List<BookingDTO> bookingsUserId(Integer idUser){
        List<Booking> bookingsUser = bookingRepository.findByUserId(idUser);

        if(idUser == null){
            throw new IllegalArgumentException("El id no puede ser nulo");
        }

        if(bookingsUser.isEmpty()){
            throw new IllegalArgumentException("Las reservas no fueron encontradas");
        }

        var bookingScores =
                bookingScoreRepository.findByBookingScoreIdUserIdAndBookingScoreIdBookingIn(idUser, bookingsUser);


        return bookingsUser.stream().map(BookingDTO::new)
                .map(bookingDTO -> this.setBookingScore(bookingDTO, bookingScores))
                .toList();
    }

    private BookingDTO setBookingScore(BookingDTO bookingDTO, List<BookingScore> bookingScores){
        var score = bookingScores.stream()
                .filter(bs -> bookingDTO.getIdBooking() == bs.getBookingId())
                .map(BookingScore::getScore).findFirst().orElse(null);
        bookingDTO.setScore(score);
        return bookingDTO;
    }

    private LocalDate parseStringToDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

    public LocalDate getCheckInDate(BookingDTO bookingDTO){
        return parseStringToDate(bookingDTO.getCheckInCheckOut().get(0));
    }

    public LocalDate getCheckOutDate(BookingDTO bookingDTO){
        return parseStringToDate(bookingDTO.getCheckInCheckOut().get(1));
    }
}