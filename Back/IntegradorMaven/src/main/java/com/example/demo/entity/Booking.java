package com.example.demo.entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBooking;

    @Column(name = "bookingDaysList")
    List<String> checkInCheckOut;

    @Column(name = "checkIn")
    private LocalDate checkIn;

    @Column(name = "checkOut")
    private LocalDate checkOut;

    @Column(name = "dataPet")
    private List<String> dataPet;

    @Column(name = "totalPrice")
    private double totalPrice;
    @ManyToOne
    private User user;
    @ManyToOne
    private PetDayCare petDayCare;

    @OneToMany(mappedBy = "bookingScoreId.booking")
    private List<BookingScore> bookingScore;


    public Booking(List<String> checkInCheckOut, LocalDate checkIn, LocalDate checkOut, List<String> dataPet, double totalPrice, User user, PetDayCare petDayCare) {
        this.checkInCheckOut = checkInCheckOut;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.dataPet = dataPet;
        this.totalPrice = totalPrice;
        this.user = user;
        this.petDayCare = petDayCare;
    }

    public Booking(String checkIn, String checkOut, LocalDate in, LocalDate out, List<String> dataPet, double totalpriceBooking, User user, PetDayCare petDayCare) {
    }



    public Booking() {
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public Integer getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(Integer idBooking) {
        this.idBooking = idBooking;
    }

    public List<String> getCheckInCheckOut() {
        return checkInCheckOut;
    }

    public void setCheckInCheckOut(List<String> checkInCheckOut) {
        this.checkInCheckOut = checkInCheckOut;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PetDayCare getPetDayCare() {
        return petDayCare;
    }

    public void setPetDayCare(PetDayCare petDayCare) {
        this.petDayCare = petDayCare;
    }

    public List<String> getDataPet() {
        return dataPet;
    }

    public void setDataPet(List<String> dataPet) {
        this.dataPet = dataPet;
    }

    public List<BookingScore> getBookingScore() {
        return bookingScore;
    }

    public void setBookingScore(List<BookingScore> bookingScore) {
        this.bookingScore = bookingScore;
    }
}
