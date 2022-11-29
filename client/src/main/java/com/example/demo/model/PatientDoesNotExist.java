package com.example.demo.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PatientDoesNotExist extends RuntimeException {
    public PatientDoesNotExist(String message) {
        super(message);
    }

}
