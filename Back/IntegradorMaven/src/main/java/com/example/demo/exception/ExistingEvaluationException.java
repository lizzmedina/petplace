package com.example.demo.exception;

import com.example.demo.DTO.CityDTO;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ExistingEvaluationException extends RuntimeException{

    public ExistingEvaluationException(Integer userId, String bookingName){
        super(String.format("El usuario (%s) ya evaluo su estadia en el hospedaje (%s)", userId, bookingName));
    }
}
