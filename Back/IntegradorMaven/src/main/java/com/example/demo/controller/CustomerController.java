package com.example.demo.controller;


import com.example.demo.entity.Customer;
import com.example.demo.entity.Pet;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/customer", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerController {
    private CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping()
    public Customer save(@RequestBody Customer customer,@RequestBody List<Pet> petList) {
        return service.save(customer, petList);
    }

    @GetMapping("/all")
    public List<Customer> customerList() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}