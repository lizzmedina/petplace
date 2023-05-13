package com.example.demo.service;


import com.example.demo.entity.PetDayCare;
import com.example.demo.repository.PetDayCareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetDayCareService {

    private PetDayCareRepository repository; // me trajo las funcionalidades de JPA

    private List<PetDayCare> petDayCareList; // ver que sea la entidad la que se esta importando

    @Autowired //es una anotacion que me autoinstancia los atributos que tengo arriba

    public PetDayCareService(PetDayCareRepository repository, List<PetDayCare> petDayCareList) { //este es un constructor
        this.repository = repository;
        this.petDayCareList = petDayCareList;
    }

    //ACA VAN LOS METODOS:

    public PetDayCare save(PetDayCare petDayCare){ // metodo para guardar
        return repository.save(petDayCare); // utiliza del jpa la funcion save,  y garda lo que le llega en el body (json), y devuelve la entidad
    }

    public List<PetDayCare> findAll(){ // metodo para listar todos los productos
        return repository.findAll().stream().collect(Collectors.toList());
    }

    public String delete(Integer id){ // metodo para eliminar un producto
        Optional<PetDayCare> petDayCare = this.repository.findById(id); // simulamos que existe una guarderia creada y la busca por id

        if(!petDayCare.isPresent()){ //si no esta presente, manda una excepcion
            throw new RuntimeException("La guarderia no fue encontrada");
        }
        repository.delete(petDayCare.get()); // si si esta presente ese id eliminalo del repo y devuelve un string con un mensaje
        return "El producto fue eliminado ";

    }
}