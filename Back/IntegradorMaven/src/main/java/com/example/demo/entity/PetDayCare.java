package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Entity
@Table(name = "pet_day_care")
public class PetDayCare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToOne
    private Category type;

    @Column(name = "capacity")
    private Integer capacity;

    @ManyToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    private City city;

    @Column(name = "address")
    private String address;

    @Column(name = "detail")
    private String detail;

    @Column(name = "images", length = 2000)
    private List<String> images ;

    @Column(name = "characteristic")
    private List<String> characteristics;

    @Column(name = "basicPrice")
    private double basicPrice;


    public PetDayCare(String name, Category type, Integer capacity, City city, String address, String detail,
                      List<String> images, List<String> characteristics, double basicPrice) {

       this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.city = city;
        this.address = address;
        this.detail = detail;
        this.images = images;
        this.characteristics = characteristics;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
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


}

