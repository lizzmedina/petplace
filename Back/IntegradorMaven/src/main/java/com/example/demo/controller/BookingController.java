package com.example.demo.controller;

// ANDREA VIVI

import com.example.demo.entity.Booking;
import com.example.demo.entity.Customer;
import com.example.demo.entity.PetDayCare;
import com.example.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    private BookingService service;

    @Autowired

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping("/pruebaBooking")//localhost:8080/api/booking/pruebaBooking
    public String pruebaBooking(){
        return " conectado Booking";
    }

    @PostMapping("/aBooking")//localhost:8080/api/booking/aBooking
    public Booking saveBooking(@RequestBody Booking booking){
        return service.saveBooking(booking);
    }

    @GetMapping("/price") //localhost:8080/api/booking/price
    public double calculatePrice(Date checkIn, Date checkOut, double totalPrice){
        return service.calculatePrice(checkIn,checkOut,totalPrice);
    }

    @GetMapping("/detail")//localhost:8080/api/booking/detail
    public String bookingDetail(String customer, String petDayCare, String addresPetDayCare, String cityPetDayCare, double totalPrice, Date checkIn, Date checkOut){
        return service.bookingDetail(customer, petDayCare, addresPetDayCare, cityPetDayCare, totalPrice, checkIn, checkOut);
    }

    /*public String bookingDetail(Customer customer, PetDayCare petDayCare, double totalPrice, Date checkIn, Date checkOut){
        return service.bookingDetail(customer,petDayCare,totalPrice, checkIn, checkOut);
    }*/



}
