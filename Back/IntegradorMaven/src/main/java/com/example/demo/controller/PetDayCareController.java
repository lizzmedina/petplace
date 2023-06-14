package com.example.demo.controller;

import com.example.demo.DTO.PetDayCareDTO;
import com.example.demo.DTO.PetDayCareSaveDTO;
import com.example.demo.entity.PetDayCare;
import com.example.demo.service.PetDayCareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/petDayCare")
public class PetDayCareController {
     private PetDayCareService service;

    @Autowired

    public PetDayCareController(PetDayCareService service) { // este es el constructor
        this.service = service;
    }

    @PostMapping()
    public PetDayCareSaveDTO save(@RequestBody PetDayCareSaveDTO petDayCareSaveDTO){
        return service.save(petDayCareSaveDTO);
    }

    @GetMapping("/all")
    public List<PetDayCare> petDayCareList(){
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id){ // importante la anotacion PathVariable que es la que me llega en la ruta para buscar por id y poder borrar
        return service.delete(id);
    }

    @GetMapping("/category/{category}")
    public List<PetDayCare> findByCategory(@PathVariable("category") Integer type){
        return service.findByCategory(type);
    };

    @GetMapping("/detail/{id}")
    public PetDayCareDTO detail(@PathVariable("id") Integer id){
        return service.detail(id);
    }

    /*@PutMapping("/edit")
    public PetDayCareSaveDTO edit(@RequestBody PetDayCareSaveDTO petDayCareSaveDTO){
        return service.edit(petDayCareSaveDTO);
    }*/

    @PutMapping("/edit")
    public PetDayCareDTO edit(@RequestBody PetDayCareDTO petDayCareDTO){
        return service.edit(petDayCareDTO);
    }


}
