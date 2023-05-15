package com.example.demo.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @Column(name = "idBooking")
    private Integer idBooking;

//    @Column(name ="petDayCare")
//    private PetDayCare petDayCare;

    @Column(name = "checkIn")
    private Date checkIn;

    @Column(name = "checkOut")
    private Date checkOut;

//    @Column(name = "customer")
//    private Customer customer;

    @Column(name = "totalPrice")
    private double totalPrice;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private PetDayCare petDayCare;

    //building methods
    public Booking(double totalPrice, Date checkIn, Date checkOut
//                   ,Customer customer, PetDayCare petDayCare
    ) {
//        this.petDayCare = petDayCare;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
//        this.customer = customer;
        this.totalPrice = totalPrice;
    }

    public Booking() {
    }



    //Getters and Setters
    public Integer getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(Integer idBooking) {
        this.idBooking = idBooking;
    }

//    public PetDayCare getPetDayCare() {
//        return petDayCare;
//    }
//
//    public void setPetDayCare(PetDayCare petDayCare) {
//        this.petDayCare = petDayCare;
//    }

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

//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
