package com.example.demo.DTO;

import com.example.demo.entity.Pet;
import com.example.demo.model.Customer;
import com.example.demo.model.PetDayCare;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingDTO {

    private Integer petDayCareId;
    private Date checkIn;
    private Date checkOut;
    private Integer userId;

    List<Pet> pets;


    public BookingDTO(Integer petDayCareId, Date checkIn, Date checkOut, Integer userId, List<Pet> pets) {

        this.petDayCareId = petDayCareId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.userId = userId;
        this.pets = new ArrayList<>();

    }

    public BookingDTO() {
    }

     public Integer getPetDayCareId() {
        return petDayCareId;
    }

    public void setPetDayCareId(Integer petDayCareId) {
        this.petDayCareId = petDayCareId;
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


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
