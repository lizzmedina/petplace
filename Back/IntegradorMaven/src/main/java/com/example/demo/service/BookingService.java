package com.example.demo.service;

import com.example.demo.DTO.BookingDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.example.demo.entity.Booking;
import com.example.demo.entity.PetDayCare;
import com.example.demo.repository.BookingRepository;
import org.springframework.stereotype.Service;


import java.util.stream.Collectors;

@Service
public class BookingService {

    private List<Booking> bookingList;
    private BookingRepository bookingRepository;
    private  UserRepository userRepository;
    private PetDayCareRepository petDayCareRepository;
    private PetRepository petRepository;


    public BookingService(List<Booking> bookingList, BookingRepository bookingRepository, UserRepository userRepository, PetDayCareRepository petDayCareRepository, PetRepository petRepository) {
        this.bookingList = bookingList;
        this.bookingRepository = bookingRepository;
        this.userRepository =  userRepository;
        this.petDayCareRepository = petDayCareRepository;
        this.petRepository = petRepository;
    }

    public Booking save(BookingDTO bookingDTO){

        Optional<User> user = this.userRepository.findById(bookingDTO.getUserId());
        Optional<PetDayCare> petDayCare = this.petDayCareRepository.findById(bookingDTO.getPetDayCareId());

        if(!user.isPresent() && !petDayCare.isPresent()){
            throw  new RuntimeException("Verifique que el cliente o la guarderia existan");
        }

        bookingDTO.getPets().forEach((pet) -> {Pet newPet = new Pet(
                pet.getId(),
                pet.getPetName(),
                pet.getPetType(),
                pet.getPetSize()
        );
            petRepository.save(newPet);
        });


        double totalpriceBooking = calculatePrice(bookingDTO.getCheckIn(), bookingDTO.getCheckOut(), petDayCare.get().getBasicPrice(), bookingDTO.getPets());

        Booking newBooking = new Booking(
                totalpriceBooking,
                bookingDTO.getCheckIn(),
                bookingDTO.getCheckOut(),
                bookingDTO.getPets(),
                user.get(),
                petDayCare.get()
        );

        return bookingRepository.save(newBooking);
    }


    public double calculatePrice(Date checkIn, Date checkOut, double basicPrice, List<Pet> petListBooking){

        double countPrice = 0.0;
        for (int i = 0; i < petListBooking.toArray().length; i++) {
            if (petListBooking.get(i).getPetSize() == "pequeño" && petListBooking.get(i).getPetType() == "perro" ) {
                basicPrice = basicPrice;
            }else if (petListBooking.get(i).getPetSize() == "mediano" && petListBooking.get(i).getPetType() == "perro") {
                basicPrice = basicPrice + (basicPrice*0.1);
            }else if (petListBooking.get(i).getPetSize() == "grande" && petListBooking.get(i).getPetType() == "perro") {
                basicPrice = basicPrice + (basicPrice * 0.2);
            }
            countPrice += basicPrice;
        }

        long startTime = checkIn.getTime() ;
        long endTime = checkOut.getTime();
        long daysSince = (long) Math.floor(startTime / (1000*60*60*24)); // convert to days, so that time changes do not affect
        long daysUntil = (long) Math.floor(endTime / (1000*60*60*24)); // convert to days, so that time changes do not affect
        long totalDays = daysUntil-daysSince;

                //double total = (totalDays * countPrice);
                double total = (totalDays * basicPrice);

        return total;
    }

    public Booking detail(Integer id){
        Optional<Booking> booking = bookingRepository.findById(id);

        if(!booking.isPresent()){
            throw new RuntimeException("la reserva no existe, verifique el numero de id");
        }

        Booking bookingDetail = new Booking(
                booking.get().getTotalPrice(),
                booking.get().getCheckIn(),
                booking.get().getCheckOut(),
                booking.get().getPets(),
                booking.get().getUser(),
                booking.get().getPetDayCare()
        );

        return bookingDetail;
    }

    public List<Booking> findAll(){
        return bookingRepository.findAll().stream().collect(Collectors.toList());
    }


}
