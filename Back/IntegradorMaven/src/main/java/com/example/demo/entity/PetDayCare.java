package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "petDayCare")
public class PetDayCare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToOne
//    @Column(name = "type")
    private Category type;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "detail")
    private String detail;

    @Column(name = "image")
    private List<String> images ;

    @Column(name = "characteristic")
    private List<String> characteristics;

//    @OneToOne
//    private Category category;

    @Column(name = "basicPrice")
    private double basicPrice;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "petDayCare")
    List<Booking> bookings;

//<<<<<<< HEAD
//      public PetDayCare(String name, Category type, Integer capacity, String city, String address, String detail, String image, double basicPrice) {
//=======
    public PetDayCare(String name, Category type, Integer capacity, String city, String address, String detail, List<String> images, List<String> characteristics, double basicPrice) {
//>>>>>>> b09c34b8f771be17bf00a49baac4e7a42de3c84d
       this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.city = city;
        this.address = address;
        this.detail = detail;
        this.images = new ArrayList<>();
        this.characteristics = new ArrayList<>();
        this.basicPrice = basicPrice;
    }

    public PetDayCare() {
    }

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

    public Category getType() {
        return type;
    }

    public void setType(Category type) {
        this.type = type;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<String> characteristics) {
        this.characteristics = characteristics;
    }

    public double getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(double basicPrice) {
        this.basicPrice = basicPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
