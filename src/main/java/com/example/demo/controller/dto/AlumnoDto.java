package com.example.demo.controller.dto;

import com.example.demo.model.EstadoAlumno;

public record AlumnoDto(Long id,String alumnoId, String nombre, String apellido, EstadoAlumno estado,Integer edad) {
}
