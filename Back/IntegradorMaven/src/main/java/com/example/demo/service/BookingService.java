package com.example.demo.service;

import com.example.demo.DTO.BookingDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.PetDayCareRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

//andrea vivi


import com.example.demo.entity.Booking;
import com.example.demo.entity.Customer;
import com.example.demo.entity.PetDayCare;
import com.example.demo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private List<Booking> bookingList;
    private BookingRepository bookingRepository;
    private CustomerRepository customerRepository;
    private PetDayCareRepository petDayCareRepository;


    public BookingService(List<Booking> bookingList, BookingRepository bookingRepository, CustomerRepository customerRepository, PetDayCareRepository petDayCareRepository) {
        this.bookingList = bookingList;
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.petDayCareRepository = petDayCareRepository;
    }

    public Booking save(BookingDTO bookingDTO){

        Optional<Customer> customer = this.customerRepository.findById(bookingDTO.getCustomerId());
        Optional<PetDayCare> petDayCare = this.petDayCareRepository.findById(bookingDTO.getPetDayCareId());

        if(!customer.isPresent() && !petDayCare.isPresent()){
            throw  new RuntimeException("Verifique que el cliente o la guarderia existan");
        }

        double totalpriceBooking = calculatePrice(bookingDTO.getCheckIn(), bookingDTO.getCheckOut(), petDayCare.get().getBasicPrice());

        Booking newBooking = new Booking(
                totalpriceBooking,
                bookingDTO.getCheckIn(),
                bookingDTO.getCheckOut()
             //   customer,
             //   petDayCare
        );

        return bookingRepository.save(newBooking);
    }


    public double calculatePrice(Date checkIn, Date checkOut, double basicPrice/*, List<Pet> petListCustomer*/){

        //El precio base = si es gato, si es perro pequeño.
        //Si es perro mediano o grande, al precio base se le suma un valor 10% al mediano y 20% al grande.

        /*double countPrice = 0.0;
        for (int i = 0; i < petListCustomer.toArray().length; i++) {
            if (petListCustomer.get(i).getPetSize() == "pequeño" && petListCustomer.get(i).getPetType() == "perro" ) {
                basicPrice = basicPrice;
            }else if (petListCustomer.get(i).getPetSize() == "mediano" && petListCustomer.get(i).getPetType() == "perro") {
                basicPrice = basicPrice + (basicPrice*0.1);
            }else if (petListCustomer.get(i).getPetSize() == "grande" && petListCustomer.get(i).getPetType() == "perro") {
                basicPrice = basicPrice + (basicPrice * 0.2);
            }
            countPrice += basicPrice;
        }*/

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
                booking.get().getCheckOut()
        );

        return bookingDetail;
    }

    public List<Booking> findAll(){
        return bookingRepository.findAll().stream().collect(Collectors.toList());
    }


}
