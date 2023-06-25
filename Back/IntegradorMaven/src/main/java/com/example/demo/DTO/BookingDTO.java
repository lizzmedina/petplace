package com.example.demo.DTO;

import com.example.demo.entity.Booking;
import com.example.demo.entity.PetDayCare;
import com.example.demo.entity.User;

import java.time.LocalDate;
import java.util.List;

public class BookingDTO {

    private Integer idBooking;
    private List<String> checkInCheckOut;
    private double totalPrice;
    private Integer userId;
    private Integer petDayCareId;
    private User user;
    private PetDayCare petDayCare;
    private List<String> dataPet;
    private LocalDate checkIn;
    private LocalDate checkOut;

    private boolean evaluated;
    private LocalDate creationDate;


    public BookingDTO(List<String> checkInCheckOut, double totalPrice, Integer userId, Integer petDayCareId, List<String> dataPet) {
        this.checkInCheckOut = checkInCheckOut;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.petDayCareId = petDayCareId;
        this.dataPet = dataPet;
        this.evaluated = false;
    }

    public BookingDTO(Booking booking){
        this.setIdBooking(booking.getIdBooking());
        this.setCheckInCheckOut(booking.getCheckInCheckOut());
        this.setTotalPrice(booking.getTotalPrice());
        this.setUserId(booking.getUser().getId());
        this.setPetDayCareId(booking.getPetDayCare().getId());
        this.setDataPet(booking.getDataPet());
        this.setCheckIn(booking.getCheckIn());
        this.setCheckOut(booking.getCheckOut());
        this.setPetDayCare(booking.getPetDayCare());
        this.setUser(booking.getUser());
        this.setCreationDate(booking.getCreationDate());
    }

    public BookingDTO() {
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPetDayCareId() {
        return petDayCareId;
    }

    public void setPetDayCareId(Integer petDayCareId) {
        this.petDayCareId = petDayCareId;
    }

    public List<String> getDataPet() {
        return dataPet;
    }

    public void setDataPet(List<String> dataPet) {
        this.dataPet = dataPet;
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

    public boolean isEvaluated() {
        return evaluated;
    }

    public BookingDTO setEvaluated(boolean evaluated) {
        this.evaluated = evaluated;
        return this;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
