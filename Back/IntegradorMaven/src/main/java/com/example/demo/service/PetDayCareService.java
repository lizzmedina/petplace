package com.example.demo.service;


import com.example.demo.entity.PetDayCare;
import com.example.demo.repository.PetDayCareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetDayCareService {

    private PetDayCareRepository repository;

    private List<PetDayCare> petDayCareList;

    @Autowired

    public PetDayCareService(PetDayCareRepository repository, List<PetDayCare> petDayCareList) {
        this.repository = repository;
        this.petDayCareList = petDayCareList;
    }


    public PetDayCare save(PetDayCare petDayCare){
        return repository.save(petDayCare);
    }

    public List<PetDayCare> findAll(){ // metodo para listar todos los productos
        return repository.findAll().stream().collect(Collectors.toList());
    }

    public String delete(Integer id){
        Optional<PetDayCare> petDayCare = this.repository.findById(id);

        if(!petDayCare.isPresent()){
            throw new RuntimeException("La guarderia no fue encontrada");
        }
        repository.delete(petDayCare.get());
        return "El producto fue eliminado ";

    }

    public List<PetDayCare> findByCategory(String type){

        String normalizacion = type.toLowerCase();

        if (!normalizacion.equals("perros") && !normalizacion.equals("gatos") && !normalizacion.equals("canarios") && !normalizacion.equals("conejos")) {
            throw new RuntimeException("La categoria no es válida, cerciorarse de que sea perros, gatos, canarios o conejos");
        }

        return repository.findByCategory(normalizacion).stream().collect(Collectors.toList());

    }

    public PetDayCare detail(Integer id){
        Optional<PetDayCare> petDayCare = this.repository.findById(id);

        if(!petDayCare.isPresent()){ //si no esta presente, manda una excepcion
            throw new RuntimeException("La guarderia no fue encontrada");
        };

        PetDayCare detailPetDatCare = new PetDayCare(
                petDayCare.get().getName(),
                petDayCare.get().getType(),
                petDayCare.get().getCapacity(),
                petDayCare.get().getCity(),
                petDayCare.get().getAddress(),
                petDayCare.get().getDetail(),
                petDayCare.get().getImages(),
                petDayCare.get().getCharacteristics(),
                petDayCare.get().getBasicPrice()
        );


         return detailPetDatCare;

    }

}
