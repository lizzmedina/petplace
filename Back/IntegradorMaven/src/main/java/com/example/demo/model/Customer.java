package com.example.demo.model;

import java.util.List;

public class Customer extends User {

    private List<Pet> pets;

    public Customer(Integer id, String name, String lastName, String email, String password, String cellPhone, String address, String type, List<Pet> pets) {
        super(id, name, lastName, email, password, cellPhone, address, type);
        this.pets = pets;
    }
}
