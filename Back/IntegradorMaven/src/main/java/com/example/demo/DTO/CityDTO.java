package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;
import java.util.stream.Collectors;

public class CityDTO {
    private Integer id;
    private String name;
    @JsonIgnore
    private Set<PetDayCareDTO> petDayCareDTOSet;

    public CityDTO(Integer id, String name) {
        this.name = name;
        this.id = id;
    }

    public CityDTO(String name) {
        this.name = name;
    }

    public CityDTO(){

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

    public Set<PetDayCareDTO> getPetDayCareDTOSet() {
        return petDayCareDTOSet;
    }

    public void setPetDayCareDTOSet(Set<PetDayCareDTO> petDayCareDTOSet) {
        this.petDayCareDTOSet = petDayCareDTOSet;
    }

    @Override
    public String toString() {
        String petDayCaresSummary = "";

        if (petDayCareDTOSet != null) {
            petDayCaresSummary = petDayCareDTOSet.stream()
                    .map(pdc -> String.format("{ id: %s, nombre: %s }", pdc.getId(), pdc.getName()))
                    .collect(Collectors.joining(", "));
        }

        return String.format("Ciudad [ id: %s, nombre: %s, alojamientos: [%s]]", id, name, petDayCaresSummary);
    }
}
