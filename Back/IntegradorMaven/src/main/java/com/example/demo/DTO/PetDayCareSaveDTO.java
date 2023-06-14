package com.example.demo.DTO;
import com.example.demo.entity.Category;

import java.util.List;

public class PetDayCareSaveDTO {

    private Integer id;
    private String name;
    private String categoryName;
    private Integer capacity;
    private String cityName;
    private String address;
    private String detail;
    private List<String> images;
    private List<String> characteristics;
    private double basicPrice;
    private List<String> houseRules;
    private List<String> healthAndSecurity;
    private List<String> cancellationPolicy;


    public PetDayCareSaveDTO(String name, String categoryName, Integer capacity, String cityName, String address, String detail, List<String> images, List<String> characteristics, double basicPrice, List<String> houseRules, List<String> healthAndSecurity, List<String> cancellationPolicy) {
        this.name = name;
        this.categoryName = categoryName;
        this.capacity = capacity;
        this.cityName = cityName;
        this.address = address;
        this.detail = detail;
        this.images = images;
        this.characteristics = characteristics;
        this.basicPrice = basicPrice;
        this.houseRules = houseRules;
        this.healthAndSecurity = healthAndSecurity;
        this.cancellationPolicy = cancellationPolicy;
    }


    public PetDayCareSaveDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

