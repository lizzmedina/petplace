package com.example.demo.controller;

import com.example.demo.entity.Pet;
import com.example.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/pet", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PetController {
    private PetService service;

    @Autowired
    public PetController(PetService service) { // este es el constructor
        this.service = service;
    }

    @PostMapping()
    public Pet save(@RequestBody Pet pet){
        return service.save(pet);
    }

    @GetMapping("/test")
    public String ejemplo(){
        return "service.findAll();";
    }

    @GetMapping("/all")
    public List<Pet> petList(){
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean deleted = service.delete(id);
        if(deleted){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.internalServerError().body("No existe una mascota con id: " + id);
        }
    }
}
