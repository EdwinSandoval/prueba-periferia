package com.example.demo.controller;


import com.example.demo.controller.dto.AlumnoDto;
import com.example.demo.model.EstadoAlumno;
import com.example.demo.service.AlumnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = AlumnoController.class)
class AlumnoControllerTest {

    @MockitoBean
    private AlumnoService alumnoService;

    @Autowired
    private WebTestClient webTestClient;

    private AlumnoDto alumnoDto;

    @BeforeEach
    void setUp() {
        alumnoDto = new AlumnoDto(
                1L,
                "A001",
                "Juan",
                "Pérez",
                EstadoAlumno.ACTIVO,
                20
        );
    }

    @Test
    void getAlumno_shouldReturnAlumnoDto() {
        when(alumnoService.getAlumno("A001")).thenReturn(Mono.just(alumnoDto));

        webTestClient.get()
                .uri("/alumno/A001")
                .exchange()
                .expectStatus().isOk()
                .expectBody(AlumnoDto.class)
                .isEqualTo(alumnoDto);
    }

    @Test
    void createAlumno_shouldReturnCreatedStatus() {
        when(alumnoService.createAlumno(any())).thenReturn(Mono.empty());

        webTestClient.post()
                .uri("/alumno")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(alumnoDto)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    void updateAlumno_shouldReturnUpdatedAlumno() {
        when(alumnoService.updateAlumno(eq("A001"), any())).thenReturn(Mono.just(alumnoDto));

        webTestClient.put()
                .uri("/alumno/A001")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(alumnoDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AlumnoDto.class)
                .isEqualTo(alumnoDto);
    }

    @Test
    void deleteAlumno_shouldReturnNoContent() {
        when(alumnoService.deleteAlumno("A001")).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/alumno/A001")
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void getAllAlumnos_shouldReturnList() {
        when(alumnoService.getAllAlumnos()).thenReturn(Flux.just(alumnoDto));

        webTestClient.get()
                .uri("/alumno/all")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(AlumnoDto.class)
                .hasSize(1)
                .contains(alumnoDto);
    }

    @Test
    void getAllAlumnosEstadoActivo_shouldReturnOnlyActive() {
        when(alumnoService.getAlumnosActivos()).thenReturn(Flux.just(alumnoDto));

        webTestClient.get()
                .uri("/alumno/activos")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(AlumnoDto.class)
                .hasSize(1)
                .contains(alumnoDto);
    }
}