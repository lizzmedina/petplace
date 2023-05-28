package com.example.demo.model;

import java.util.List;

public class Pet {

    private String petName;
    private String petType;
    private String petSize;

    Pet pet;
    List<Pet> listPet;

    public Pet(String petName, String petType, String petSize) {
        this.petName = petName;
        this.petType = petType;
        this.petSize = petSize;
    }

    public Pet addPetList(Pet pet){
        return pet;
    }

    public List viewListPet(Pet pet){
        return listPet;
    }

}
