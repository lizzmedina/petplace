package com.example.demo.exception;

import com.example.demo.DTO.CityDTO;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ReferencedCityException extends RuntimeException{

    public ReferencedCityException(CityDTO city){
        super(String.format("La ciudad no se puede elimminar porque cuenta con alojamientos asociados.\n"+city.toString()));
    }
}
