package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id")
    private Integer id;

    //@Column(name = "petName")
    private String petName;

    //@Column(name = "petType")
    private String petType;

    //@Column(name = "petSize")
    private String petSize;

    @ManyToOne
    private Customer customer;



    //building methods
    public Pet() {
    }

    public Pet(Integer id, String petName, String petType, String petSize ) {
        this.id = id;
        this.petName = petName;
        this.petType = petType;
        this.petSize = petSize;

    }

    //Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetSize() {
        return petSize;
    }

    public void setPetSize(String petSize) {
        this.petSize = petSize;
    }

}
