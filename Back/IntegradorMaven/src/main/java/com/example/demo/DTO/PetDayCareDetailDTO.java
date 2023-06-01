package com.example.demo.DTO;

import com.example.demo.entity.Category;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;

import java.util.List;

public class PetDayCareDetailDTO {

    private String name;

    private Category type;

    private Integer capacity;

    private String city;

    private String address;

    private String detail;

    private List<String> images ;

    private List<String> characteristics;

    private double basicPrice;

    public PetDayCareDetailDTO(String name, Category type, Integer capacity, String city, String address, String detail, List<String> images, List<String> characteristics, double basicPrice) {
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
}
