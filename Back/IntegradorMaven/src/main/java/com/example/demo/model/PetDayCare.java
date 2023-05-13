package com.example.demo.model;

import java.util.List;

public class PetDayCare {
    //completar clase: atributos, constructor y metodos
    private Integer id;
    private String name;
    private String type;
    private Integer capacity;
    private String city;
    private String address;
    private String detail;
    private String image;
    private double basicPrice;
    List<Pet> listPetsDay;
    PetDayCare petDayCare;

    public PetDayCare(Integer id, String name, String type, Integer capacity, String city, String address, String detail, String image, double basicPrice) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.city = city;
        this.address = address;
        this.detail = detail;
        this.image = image;
        this.basicPrice = basicPrice;
    }
    public List listPetsDayCare(){
        return listPetsDay;
    }

    public String deletePetsDayCare(Integer id){
        return "Mascota eliminada con exito";
    }

    public String addPetsDayCare(){
        return "Mascota agregada con exito";
    }

    public PetDayCare findPetdatCare(Integer id){
        return petDayCare;
    }
}
