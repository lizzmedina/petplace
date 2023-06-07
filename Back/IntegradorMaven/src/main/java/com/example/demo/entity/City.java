package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Set;


@Entity
@Table(name = "city")
public class City {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PetDayCare> petDayCareSet;

    public City(String name) {
        this.name = name;
    }

    public City() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<PetDayCare> getPetDayCareSet() {
        return petDayCareSet;
    }

    public void setPetDayCareSet(Set<PetDayCare> petDayCareSet) {
        this.petDayCareSet = petDayCareSet;
    }
}


