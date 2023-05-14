package com.example.demo.controller;

import com.example.demo.DTO.BookingDTO;
import com.example.demo.entity.Booking;
import com.example.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// ANDREA VIVI
@RestController
@RequestMapping(value = "/api/v1/booking")
public class BookingController {

    private BookingService service;

    @Autowired
    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping()
    public Booking save (@RequestBody BookingDTO bookingDTO){
        return service.save(bookingDTO);
    }

    @GetMapping("/{id}")
    public Booking detail (@PathVariable("id") Integer id){
        return service.detail(id);
    }
}
