package com.example.demo.model;

import java.util.Date;

public class Booking {
    // completar clase: atributos( asegurarse que se importen las clases cuando se quieren colocar como atributos, constructor y metodos )
    private Integer idBooking;
    private PetDayCare petDayCare;
    private Date checkIn;
    private Date checkOut;
    private Customer customer;
    private double totalPrice;


    public Booking(Integer idBooking, PetDayCare petDayCare, Date checkIn, Date checkOut, Customer customer, double totalPrice) {
        this.idBooking = idBooking;
        this.petDayCare = petDayCare;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.customer = customer;
        this.totalPrice = totalPrice;
    }

    public double calculatePrice (double checkIn, double checkOut, Customer customer){
        return totalPrice;
    }


}
