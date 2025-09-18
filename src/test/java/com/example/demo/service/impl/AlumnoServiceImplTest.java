package com.example.demo.service.impl;
import com.example.demo.controller.dto.AlumnoDto;
import com.example.demo.model.AlumnoEntity;
import com.example.demo.model.EstadoAlumno;
import com.example.demo.repository.AlumnoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlumnoServiceImplTest {

    @Mock
    private AlumnoRepository alumnoRepository;

    @InjectMocks
    private AlumnoServiceImpl alumnoService;

    private AlumnoEntity alumnoEntity;
    private AlumnoDto alumnoDto;

    @BeforeEach
    void setUp() {
        alumnoEntity = AlumnoEntity.builder()
                .id(1L)
                .alumnoId("A001")
                .nombre("Juan")
                .apellido("Pérez")
                .estado(EstadoAlumno.ACTIVO)
                .edad(20)
                .build();

        alumnoDto = new AlumnoDto(1L, "A001", "Juan", "Pérez", EstadoAlumno.ACTIVO, 20);
    }

    @Test
    void getAlumno_shouldReturnAlumnoDto() {
        when(alumnoRepository.findByAlumnoId("A001")).thenReturn(Mono.just(alumnoEntity));

        StepVerifier.create(alumnoService.getAlumno("A001"))
                .expectNextMatches(dto -> dto.nombre().equals("Juan"))
                .verifyComplete();
    }

    @Test
    void createAlumno_shouldSaveNewAlumno() {
        when(alumnoRepository.existsByAlumnoId("A001")).thenReturn(Mono.just(false));
        when(alumnoRepository.save(any())).thenReturn(Mono.just(alumnoEntity));

        StepVerifier.create(alumnoService.createAlumno(alumnoDto))
                .verifyComplete();

        verify(alumnoRepository).save(any());
    }

    @Test
    void createAlumno_shouldFailIfExists() {
        when(alumnoRepository.existsByAlumnoId("A001")).thenReturn(Mono.just(true));

        StepVerifier.create(alumnoService.createAlumno(alumnoDto))
                .expectErrorMatches(e -> e instanceof IllegalArgumentException &&
                        e.getMessage().contains("ya está registrado"))
                .verify();
    }

    @Test
    void updateAlumno_shouldUpdateExistingAlumno() {
        when(alumnoRepository.findByAlumnoId("A001")).thenReturn(Mono.just(alumnoEntity));
        when(alumnoRepository.save(any())).thenReturn(Mono.just(alumnoEntity));

        StepVerifier.create(alumnoService.updateAlumno("A001", alumnoDto))
                .expectNextMatches(dto -> dto.nombre().equals("Juan"))
                .verifyComplete();
    }

    @Test
    void updateAlumno_shouldFailIfNotFound() {
        when(alumnoRepository.findByAlumnoId("A001")).thenReturn(Mono.empty());

        StepVerifier.create(alumnoService.updateAlumno("A001", alumnoDto))
                .expectErrorMatches(e -> e instanceof IllegalArgumentException &&
                        e.getMessage().contains("no existe"))
                .verify();
    }


    @Test
    void deleteAlumno_shouldDeleteIfExists() {
        when(alumnoRepository.findByAlumnoId("A001")).thenReturn(Mono.just(alumnoEntity));
        when(alumnoRepository.deleteById(1L)).thenReturn(Mono.empty());

        StepVerifier.create(alumnoService.deleteAlumno("A001"))
                .verifyComplete();

        verify(alumnoRepository).deleteById(1L);
    }

    @Test
    void deleteAlumno_shouldFailIfNotFound() {
        when(alumnoRepository.findByAlumnoId("A001")).thenReturn(Mono.empty());

        StepVerifier.create(alumnoService.deleteAlumno("A001"))
                .expectErrorMatches(e -> e instanceof IllegalArgumentException &&
                        e.getMessage().contains("No existe el alumno"))
                .verify();
    }

    @Test
    void getAllAlumnos_shouldReturnAll() {
        when(alumnoRepository.findAll()).thenReturn(Flux.just(alumnoEntity));

        StepVerifier.create(alumnoService.getAllAlumnos())
                .expectNextMatches(dto -> dto.alumnoId().equals("A001"))
                .verifyComplete();
    }

    @Test
    void getAlumnosActivos_shouldReturnOnlyActive() {
        // Simula que el repositorio devuelve alumnos con estado "ACTIVO"
        when(alumnoRepository.findByEstado(EstadoAlumno.ACTIVO.getValor()))
                .thenReturn(Flux.just(alumnoEntity));

        StepVerifier.create(alumnoService.getAlumnosActivos())
                .expectNextMatches(dto -> dto.estado() == EstadoAlumno.ACTIVO)
                .verifyComplete();
    }
}