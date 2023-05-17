package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")

    private String password;

    @Column(name = "cellPhone")
    private String cellPhone;

    @Column(name = "address")
    private String address;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "customer")
    List<Pet> pets;

    @OneToMany(mappedBy = "customer")
    List<Booking> bookings;

    public Customer(Integer id, String name,
                    String lastName, String email,
                    String password, String cellPhone,
                    String address, String type, List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.cellPhone = cellPhone;
        this.address = address;
        this.type = type;
       this.pets = new ArrayList<>();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
