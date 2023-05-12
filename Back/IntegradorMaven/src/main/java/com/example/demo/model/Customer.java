package com.example.demo.model;

import java.util.List;

public class Customer extends Person{

    private List<Pet> pets;

    public Customer(Integer id, String name, String lastName, String email, String passwork, String cellPhone, String address, String type, List<Pet> pets) {
        super(id, name, lastName, email, passwork, cellPhone, address, type);
        this.pets = pets;
    }
}
