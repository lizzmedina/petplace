package com.example.demo.service;

import com.example.demo.entity.Pet;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PetService {

    private PetRepository petRepository;

    @Autowired

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }


    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public void delete(Integer id) {
        Optional<Pet> petOpt = petRepository.findById(id);
        if (petOpt.isPresent()) {
            petRepository.delete(petOpt.get());
        }
        throw new ResourceNotFoundException("No existe una mascota con el id: " + id);
    }
}