package com.example.demo.controller;

import com.example.demo.entity.Pet;
import com.example.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/pet", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PetController {
    private PetService service;

    @Autowired
    public PetController(PetService service) {
        this.service = service;
    }

    @PostMapping()
    public Pet save(@RequestBody Pet pet){
        return service.save(pet);
    }

    @GetMapping("/all")
    public List<Pet> petList(){
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
