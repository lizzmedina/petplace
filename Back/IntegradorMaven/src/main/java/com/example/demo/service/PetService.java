package com.example.demo.service;

import com.example.demo.entity.Pet;
import com.example.demo.entity.PetDayCare;
import com.example.demo.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@Service
public class PetService {

    private PetRepository petRepository;

    @Autowired

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet save(Pet pet) {
        if (pet != null) {
            return petRepository.save(pet);
        } else {
            return null;
        }
    }

    public List<Pet> findAll(){
        return petRepository.findAll();

    }

    public boolean delete(Integer id){
        Optional<Pet> petOpt = petRepository.findById(id);
        if(petOpt.isPresent()){
            petRepository.delete(petOpt.get());
            return true;
        }else{
            return false;
        }

    }

}