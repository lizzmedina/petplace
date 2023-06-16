package com.example.demo.controller;

import com.example.demo.DTO.BookingDTO;
import com.example.demo.entity.Booking;
import com.example.demo.entity.Pet;
import com.example.demo.entity.PetDayCare;
import com.example.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public BookingDTO save (@RequestBody BookingDTO bookingDTO){
        return service.save(bookingDTO);
    }

    @GetMapping("/search/{city}")
    public List<PetDayCare> search(@PathVariable("city") String city,  @RequestParam(value = "checkInCheckOut", required = false) List<String> checkInCheckOut){
        return service.search(city, checkInCheckOut);
    }
    @GetMapping("/{id}")
    public Booking detail (@PathVariable("id") Integer id){
        return service.detail(id);
    }

    @GetMapping("/all")
    public List<Booking> petList(){
        return service.findAll();
    }

    @GetMapping("/petDayCare/{id}")
    public List<BookingDTO> bookingsPetDayCare (@PathVariable("id") Integer id){
        return service.bookingsPetDayCare(id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Integer id) {
        return service.deleteById(id);
    }
}
