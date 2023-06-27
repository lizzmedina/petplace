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
    private UserDTO user;
    private PetDayCareDTO petDayCare;
    private List<String> dataPet;
    private LocalDate checkIn;
    private LocalDate checkOut;

    private LocalDate creationDate;
    private Integer score;

    public BookingDTO(Booking booking){
        this.setIdBooking(booking.getIdBooking());
        this.setCheckInCheckOut(booking.getCheckInCheckOut());
        this.setTotalPrice(booking.getTotalPrice());
        this.setUserId(booking.getUser().getId());
        this.setPetDayCareId(booking.getPetDayCare().getId());
        this.setDataPet(booking.getDataPet());
        this.setCheckIn(booking.getCheckIn());
        this.setCheckOut(booking.getCheckOut());
        this.setPetDayCare(new PetDayCareDTO(booking.getPetDayCare()));
        this.setUser(new UserDTO(booking.getUser()));
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public PetDayCareDTO getPetDayCare() {
        return petDayCare;
    }

    public void setPetDayCare(PetDayCareDTO petDayCare) {
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
