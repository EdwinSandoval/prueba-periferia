package com.example.demo.exceptions.custom;

public class NoExisteAlumno extends RuntimeException{
    public NoExisteAlumno(String message) {
        super(message);
    }

}
