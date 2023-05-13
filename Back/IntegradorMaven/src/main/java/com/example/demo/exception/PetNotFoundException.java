package com.example.demo.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class PetNotFoundException extends RuntimeException{

    public PetNotFoundException(int id){
        super("No existe una mascota con el id: "+id);
    }
}
