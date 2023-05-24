package com.example.demo.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBooking;

    @Column(name = "checkIn")
    private Date checkIn;

    @Column(name = "checkOut")
    private Date checkOut;

    @Column(name = "totalPrice")
    private double totalPrice;
    @ManyToOne
    private User user;
    @ManyToOne
    private PetDayCare petDayCare;
    @OneToMany(mappedBy = "booking")
    List<Pet> pets;

    public Booking(double totalPrice, Date checkIn, Date checkOut, List<Pet> pets
                   ,User user, PetDayCare petDayCare
    ) {
        this.petDayCare = petDayCare;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.user = user;
        this.totalPrice = totalPrice;
        this.pets = new ArrayList<>();
    }

    public Booking() {
    }

    public Integer getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(Integer idBooking) {
        this.idBooking = idBooking;
    }

    public PetDayCare getPetDayCare() {
        return petDayCare;
    }

    public void setPetDayCare(PetDayCare petDayCare) {
        this.petDayCare = petDayCare;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
