package com.brian.laudexback.exceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String error) {
        super(error);
    }
}
