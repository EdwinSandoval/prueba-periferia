package com.example.demo.service;

import com.example.demo.controller.dto.AlumnoDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoService {

    Mono<AlumnoDto> getAlumno(String alumnoId);
    Mono<Void> createAlumno(AlumnoDto alumno);
    Mono<AlumnoDto> updateAlumno(String alumnoId, AlumnoDto alumno);
    Mono<Void> deleteAlumno(String alumnoId);
    Flux<AlumnoDto> getAllAlumnos();
    Flux<AlumnoDto> getAlumnosActivos();
}
