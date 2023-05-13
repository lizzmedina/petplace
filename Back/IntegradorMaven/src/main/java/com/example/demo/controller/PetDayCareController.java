package com.example.demo.controller;

import com.example.demo.entity.PetDayCare;
import com.example.demo.service.PetDayCareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PetDayCareController {
     private PetDayCareService service; // me traigo las funcionalidades creadas en el servicio

    @Autowired // igual que en servicio me autoinstancia

    public PetDayCareController(PetDayCareService service) { // este es el constructor
        this.service = service;
    }

    @GetMapping("/prueba")
    public String prueba(){
        return " conectado";
    }

    @PostMapping("/petDayCare") // esta es la ruta, completa debe verse como localhost:8080/api/v1/petDayCare ,  importantisimo el metodo http va por post porque es para crear
    public PetDayCare save(@RequestBody PetDayCare petDayCare){ // importante la anotacion requestBody para que me reciba lo que le mando por json
        return service.save(petDayCare);
    }

    @GetMapping("/petDayCareList") // ruta para listar, localhost:8080/api/v1/petDayCareList
    public List<PetDayCare> petDayCareList(){
        return service.findAll();
    }

    @DeleteMapping("/petDayCare/{id}") //la ruta para borrar es igual que la de crear , lo cambia es el metodo http que usamos que ya no es post sino delete.. cuidado al hacer las pruebas con esto
    public String delete(@PathVariable("id") Integer id){ // importante la anotacion PathVariable que es la que me llega en la ruta para buscar por id y poder borrar
        return service.delete(id);
    }
}