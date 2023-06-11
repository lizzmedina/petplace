package com.example.demo.DTO;

import com.example.demo.entity.Pet;
import com.example.demo.model.Customer;
import com.example.demo.model.PetDayCare;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingDTO {

    private Integer idBooking;
    private List<String> checkInCheckOut;

    private double totalPrice;
    private Integer userId;
    private Integer petDayCareId;
//    List<Pet> pets;

    private String petName;


    public BookingDTO(List<String> checkInCheckOut, double totalPrice, Integer userId, Integer petDayCareId, String petName) {
        this.checkInCheckOut = checkInCheckOut;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.petDayCareId = petDayCareId;
        this.petName = petName;
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

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }
}
