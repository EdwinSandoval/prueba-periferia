package com.example.demo.exceptions.custom;

public class ExisteAlumno extends RuntimeException{
    public ExisteAlumno(String message) {
        super(message);
    }

}
