package com.example.demo.DTO;

import com.example.demo.model.Customer;
import com.example.demo.model.PetDayCare;

import java.util.Date;

public class BookingDTO {

    private Integer petDayCareId;
    private Date checkIn;
    private Date checkOut;
    private Integer customerId;


    public BookingDTO(Integer petDayCareId, Date checkIn, Date checkOut, Integer customerId) {

        this.petDayCareId = petDayCareId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.customerId = customerId;

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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

}
