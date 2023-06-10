package com.example.demo.service;

import com.example.demo.DTO.BookingDTO;
import com.example.demo.DTO.PetDayCareDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.example.demo.entity.Booking;
import com.example.demo.entity.PetDayCare;
import com.example.demo.repository.BookingRepository;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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

        Optional<User> user = this.userRepository.findById(bookingDTO.getUserId());
        Optional<PetDayCare> petDayCare = this.petDayCareRepository.findById(bookingDTO.getPetDayCareId());
        Optional<Booking> bookingPetDayCare = this.bookingRepository.findByPetDayCareId(bookingDTO.getPetDayCareId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");

        LocalDate checkIn = LocalDate.parse(bookingDTO.getCheckInCheckOut().get(0), formatter);
        LocalDate checkOut = LocalDate.parse(bookingDTO.getCheckInCheckOut().get(1), formatter);

        boolean resultado = available(checkIn.toString(), checkOut.toString());
        System.out.println("___________________________RESULTADO________________________");
        System.out.println(resultado);

        if(!user.isPresent() && !petDayCare.isPresent()){
            throw  new RuntimeException("El usuario o hotel no se encuentran registrados");
        }

        if(bookingPetDayCare.isPresent() && !available(checkIn.toString(), checkOut.toString())){
            throw  new RuntimeException("las fechas a reservar no estan disponibles en ese ajolamiento pues ya se encuentra reservado");
        }

        double totalpriceBooking = calculatePrice(checkIn, checkOut, petDayCare.get().getBasicPrice());

        Booking newBooking = new Booking(
                bookingDTO.getCheckInCheckOut(),
                checkIn,
                checkOut,
                bookingDTO.getPetName(),
                totalpriceBooking,
                user.get(),
                petDayCare.get());

        bookingRepository.save(newBooking);

        BookingDTO newBookingDTO = new BookingDTO(
                newBooking.getCheckInCheckOut(),
                totalpriceBooking,
                newBooking.getUser().getId(),
                newBooking.getPetDayCare().getId(),
                newBooking.getPetName());

        bookingDTO.setIdBooking(newBookingDTO.getIdBooking());

        return newBookingDTO;

    }


    public double calculatePrice(LocalDate checkIn, LocalDate checkOut, double basicPrice){

        long totalDays = ChronoUnit.DAYS.between(checkIn, checkOut);
             double total = (totalDays * basicPrice);
        return total;
    }


    public boolean available(String checkIn, String checkOut){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate startDay = LocalDate.parse(checkIn, formatter);
        LocalDate finalDay = LocalDate.parse(checkOut, formatter);

        LocalDate fechaActual = LocalDate.now();

        Integer resultado = bookingRepository.disponibilidadQuery(finalDay, startDay);

        System.out.println("___________________________________________________");
        System.out.println(resultado);

        if(startDay.compareTo(fechaActual) > 0 && resultado == 0) {
            return true;
        }

        return false;

    }

    public List<PetDayCare> search(String city, List<String> checkInCheckOut){
        Optional<City> cityId = cityRepository.findByName(city);

        if(!city.isEmpty() && checkInCheckOut == null){

             List<PetDayCare> petDayCareListByCity = petDayCareRepository.findAllByCityId(cityId.get().getId());

             return petDayCareListByCity;

        }



        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate checkIn = LocalDate.parse(checkInCheckOut.get(0), formatter);
        LocalDate checkOut = LocalDate.parse(checkInCheckOut.get(1), formatter);
        List<Integer> idList = bookingRepository.searchAvailablePetDayCares(cityId.get().getId(), checkIn, checkOut);


        List <PetDayCare> petDayCareListAvailable = petDayCareRepository.findAllById(idList);

        return petDayCareListAvailable;
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
                booking.get().getPetName(),
                booking.get().getTotalPrice(),
                booking.get().getUser(),
               booking.get().getPetDayCare()
        );

        return bookingDetail;
    }

    public List<Booking> findAll(){
        return bookingRepository.findAll();
    }


}
