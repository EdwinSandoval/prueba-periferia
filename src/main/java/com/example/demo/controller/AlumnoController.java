package com.example.demo.controller;

import com.example.demo.controller.dto.AlumnoDto;
import com.example.demo.service.AlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/alumno")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @GetMapping("/{alumnoId}")
    Mono<AlumnoDto> getAlumno(@PathVariable("alumnoId") String alumnoId) {
        return alumnoService.getAlumno(alumnoId);
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> createAlumno(@RequestBody AlumnoDto alumnoDto) {
        return alumnoService.createAlumno(alumnoDto)
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
    }

    @PutMapping("/{alumnoId}")
    Mono<AlumnoDto> updateAlumno(@PathVariable("alumnoId") String alumnoId, @RequestBody AlumnoDto alumnoDto) {
        return alumnoService.updateAlumno(alumnoId, alumnoDto);
    }

    @DeleteMapping("/{alumnoId}")
    Mono<Void> deleteAlumno(@PathVariable("alumnoId") String alumnoId) {
        return alumnoService.deleteAlumno(alumnoId);
    }

    @GetMapping("/all")
    Flux<AlumnoDto> getAllAlumnos() {
        return alumnoService.getAllAlumnos();
    }

    @GetMapping("/activos")
    Flux<AlumnoDto> getAllAlumnosEstadoActivo() {
        return alumnoService.getAlumnosActivos();
    }
}
