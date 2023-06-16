package com.example.demo.mapper;

import com.example.demo.DTO.CityDTO;
import com.example.demo.DTO.PetDayCareDTO;
import com.example.demo.entity.City;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class CityMapper {

    public CityMapper(){

    }

    public City mapToEntity(CityDTO dto){
        if(dto == null){
            return null;
        }
        City cityEntity = new City();
        cityEntity.setName(dto.getName());
        cityEntity.setId(dto.getId());
        return cityEntity;
    }

    public CityDTO mapToDto(City city){
        if(city == null){
            return null;
        }

        CityDTO cityDto = new CityDTO(city.getId(), city.getName());
        if(city.getPetDayCareSet() != null){
            cityDto.setPetDayCareDTOSet(city.getPetDayCareSet().stream().map(petDayCare -> {
                PetDayCareDTO pdcDto = new PetDayCareDTO(petDayCare.getName(),
                        petDayCare.getType(),
                        petDayCare.getCapacity(),
                        cityDto,
                        petDayCare.getAddress(),
                        petDayCare.getDetail(),
                        petDayCare.getImages(),
                        petDayCare.getCharacteristics(),
                        petDayCare.getBasicPrice(),
                        petDayCare.getHouseRules(),
                        petDayCare.getHealthAndSecurity(),
                        petDayCare.getCancellationPolicy());
                pdcDto.setId(petDayCare.getId());
                return pdcDto;
            }).collect(Collectors.toSet()));
        }
        return cityDto;
    }

}
