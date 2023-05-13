package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id")
    private Integer id;

    //@Column(name = "name")

    private String name;

    //@Column(name = "lastName")
    private String lastName;

    //@Column(name = "email")
    private String email;

    //@Column(name="passwork")
    private String passwork;

    //@Column(name= "cellPhone")
    private String cellPhone;

    //@Column(name=" address")
    private String address;

    //@Column(name="type")
    private String type;

    @OneToMany(mappedBy = "customer")
    List<Pet> pets;

    @OneToMany(mappedBy = "customer")
    List<Booking> bookings;

    //CORREGIR RELACION
//    @OneToMany
//    @JoinColumn(name = "id")
//    private Pet pet;



    //building methods
    public Customer(Integer id, String name, String lastName, String email, String passwork, String cellPhone, String address, String type//, Pet pet
        ) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.passwork = passwork;
        this.cellPhone = cellPhone;
        this.address = address;
        this.type = type;
        //this.pet = pet;
    }

    public Customer() {
    }

    //Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswork() {
        return passwork;
    }

    public void setPasswork(String passwork) {
        this.passwork = passwork;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public Pet getPet() {
//        return pet;
//    }
//
//    public void setPet(Pet pet) {
//        this.pet = pet;
//    }
}
