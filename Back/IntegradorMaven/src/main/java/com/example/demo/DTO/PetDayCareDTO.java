package com.example.demo.DTO;

import com.example.demo.entity.Category;

import java.util.List;

public class PetDayCareDTO {

    private Integer id;
    private String name;
    private Category type;
    private Integer capacity;
    private CityDTO city;
    private String address;
    private String detail;
    private List<String> images;
    private List<String> characteristics;
    private double basicPrice;
    private List<String> houseRules;
    private List<String> healthAndSecurity;
    private List<String> cancellationPolicy;


    public PetDayCareDTO(String name, Category type, Integer capacity, CityDTO city, String address, String detail, List<String> images, List<String> characteristics, double basicPrice, List<String> houseRules, List<String> healthAndSecurity, List<String> cancellationPolicy) {
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.city = city;
        this.address = address;
        this.detail = detail;
        this.images = images;
        this.characteristics = characteristics;
        this.basicPrice = basicPrice;
        this.houseRules = houseRules;
        this.healthAndSecurity = healthAndSecurity;
        this.cancellationPolicy = cancellationPolicy;
    }

    public PetDayCareDTO() {
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

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
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

    public List<String> getHouseRules() {
        return houseRules;
    }

    public void setHouseRules(List<String> houseRules) {
        this.houseRules = houseRules;
    }

    public List<String> getHealthAndSecurity() {
        return healthAndSecurity;
    }

    public void setHealthAndSecurity(List<String> healthAndSecurity) {
        this.healthAndSecurity = healthAndSecurity;
    }

    public List<String> getCancellationPolicy() {
        return cancellationPolicy;
    }

    public void setCancellationPolicy(List<String> cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
    }
}
