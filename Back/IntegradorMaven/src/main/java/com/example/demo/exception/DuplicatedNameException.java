package com.example.demo.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class DuplicatedNameException extends RuntimeException{

    public DuplicatedNameException(String message){
        super(message);
    }
}
