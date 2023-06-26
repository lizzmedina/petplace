package com.example.demo.DTO;

import com.example.demo.entity.Category;
import com.example.demo.entity.PetDayCare;

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

    private RatingDTO rating;
    private Boolean favorite;

    public PetDayCareDTO(PetDayCare petDayCare, CityDTO city, RatingDTO rating) {
        this.setId(petDayCare.getId());
        this.setName(petDayCare.getName());
        this.setType(petDayCare.getType());
        this.setCapacity(petDayCare.getCapacity());
        this.setCity(city);
        this.setAddress(petDayCare.getAddress());
        this.setDetail(petDayCare.getDetail());
        this.setImages(petDayCare.getImages());
        this.setCharacteristics(petDayCare.getCharacteristics());
        this.setBasicPrice(petDayCare.getBasicPrice());
        this.setHouseRules(petDayCare.getHouseRules());
        this.setHealthAndSecurity(petDayCare.getHealthAndSecurity());
        this.setCancellationPolicy(petDayCare.getCancellationPolicy());
        this.setId(id);
        this.setRating(rating);
        this.setFavorite(petDayCare.isFavorite());
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

    public RatingDTO getRating() {
        return rating;
    }

    public void setRating(RatingDTO rating) {
        this.rating = rating;
    }

    public Boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }
}
