package com.example.demo.service;

import com.example.demo.DTO.BookingDTO;
import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import com.example.demo.entity.Booking;
import com.example.demo.entity.PetDayCare;
import com.example.demo.repository.BookingRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;


import java.util.stream.Collectors;

@Service
public class BookingService {

    private List<Booking> bookingList;
    private BookingRepository bookingRepository;
    private  UserRepository userRepository;
    private PetDayCareRepository petDayCareRepository;
    private PetRepository petRepository;

    private CityRepository cityRepository;


    public BookingService(List<Booking> bookingList, BookingRepository bookingRepository, UserRepository userRepository, PetDayCareRepository petDayCareRepository, PetRepository petRepository, CityRepository cityRepository) {
        this.bookingList = bookingList;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.petDayCareRepository = petDayCareRepository;
        this.petRepository = petRepository;
        this.cityRepository = cityRepository;
    }

    public BookingDTO save(BookingDTO bookingDTO){

        if (bookingDTO == null) {
            throw new IllegalArgumentException("La reserva no puede ser nulo");
        }

        Optional<User> user = this.userRepository.findById(bookingDTO.getUserId());
        Optional<PetDayCare> petDayCare = this.petDayCareRepository.findById(bookingDTO.getPetDayCareId());

        LocalDate checkIn = getCheckInDate(bookingDTO);
        LocalDate checkOut = getCheckOutDate(bookingDTO);

        if(!user.isPresent() && !petDayCare.isPresent()){
            throw  new RuntimeException("El usuario o hotel no se encuentran registrados");
        }

        if(!available(bookingDTO.getPetDayCareId(), checkIn, checkOut)){
            throw  new RuntimeException("las fechas a reservar no estan disponibles en ese ajolamiento pues ya se encuentra reservado");
        }

        double totalpriceBooking = calculatePrice(checkIn, checkOut, petDayCare.get().getBasicPrice());

        Booking newBooking = new Booking(
                bookingDTO.getCheckInCheckOut(),
                checkIn,
                checkOut,
                bookingDTO.getDataPet(),
                totalpriceBooking,
                user.get(),
                petDayCare.get());

        bookingRepository.save(newBooking);

        BookingDTO newBookingDTO = new BookingDTO(
                newBooking.getCheckInCheckOut(),
                newBooking.getTotalPrice(),
                newBooking.getUser().getId(),
                newBooking.getPetDayCare().getId(),
                newBooking.getDataPet()
        );


        bookingDTO.setIdBooking(newBookingDTO.getIdBooking());

        return newBookingDTO;

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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
                .collect(Collectors.toList());

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

        return bookingsUser.stream().map(BookingDTO::new).toList();
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