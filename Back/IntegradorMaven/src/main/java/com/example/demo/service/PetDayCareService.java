package com.example.demo.service;


import com.example.demo.DTO.PetDayCareDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.PetDayCare;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
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

    private CategoryService categoryService;

    @Autowired

    public PetDayCareService(PetDayCareRepository repository, List<PetDayCare> petDayCareList, CategoryService categoryService) {
        this.repository = repository;
        this.petDayCareList = petDayCareList;
        this.categoryService = categoryService;
    }

    public PetDayCareDTO save(PetDayCareDTO petDayCare){

        Category category = this.categoryService.findByName(petDayCare.getCategoryName());

        PetDayCare newPetDayCare = new PetDayCare(
                petDayCare.getName(),
                category,
                petDayCare.getCapacity(),
                petDayCare.getCity(),
                petDayCare.getAddress(),
                petDayCare.getDetail(),
                petDayCare.getImages(),
                petDayCare.getCharacteristics(),
                petDayCare.getBasicPrice()
        );

        repository.save(newPetDayCare);



        PetDayCareDTO petDayCareDTO = new PetDayCareDTO(
                newPetDayCare.getName(),
                newPetDayCare.getType().getTitle(),
                newPetDayCare.getCapacity(),
                newPetDayCare.getCity(),
                newPetDayCare.getAddress(),
                newPetDayCare.getDetail(),
                newPetDayCare.getImages(),
                newPetDayCare.getCharacteristics(),
                newPetDayCare.getBasicPrice());

        return petDayCareDTO;
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

    public List<PetDayCare> findByCategory(Integer type){

        return repository.findByCategory(type).stream().collect(Collectors.toList());

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
