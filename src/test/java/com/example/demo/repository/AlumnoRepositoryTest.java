package com.example.demo.repository;


import com.example.demo.model.AlumnoEntity;
import com.example.demo.model.EstadoAlumno;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.dao.DataAccessException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataR2dbcTest
class AlumnoRepositoryTest {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Test
    void findByAlumnoId_shouldReturnAlumno() {
        AlumnoEntity alumno = AlumnoEntity.builder()
                .alumnoId("A001")
                .nombre("Juan")
                .apellido("Pérez")
                .estado(EstadoAlumno.ACTIVO)
                .edad(20)
                .build();

        Mono<AlumnoEntity> saveAndFind = alumnoRepository.save(alumno)
                .then(alumnoRepository.findByAlumnoId("A001"));

        StepVerifier.create(saveAndFind)
                .expectNextMatches(a -> a.getNombre().equals("Juan"))
                .verifyComplete();
    }

    @Test
    void existsByAlumnoId_shouldReturnTrueIfExists() {
        AlumnoEntity alumno = AlumnoEntity.builder()
                .alumnoId("A002")
                .nombre("Ana")
                .apellido("Gómez")
                .estado(EstadoAlumno.ACTIVO)
                .edad(22)
                .build();

        Mono<Boolean> result = alumnoRepository.save(alumno)
                .then(alumnoRepository.existsByAlumnoId("A002"));

        StepVerifier.create(result)
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void findByEstado_shouldReturnOnlyActiveAlumnos() {
        AlumnoEntity activo = AlumnoEntity.builder()
                .alumnoId("A003")
                .nombre("Luis")
                .apellido("Torres")
                .estado(EstadoAlumno.ACTIVO)
                .edad(25)
                .build();

        AlumnoEntity inactivo = AlumnoEntity.builder()
                .alumnoId("A004")
                .nombre("Carlos")
                .apellido("Ramírez")
                .estado(EstadoAlumno.INACTIVO)
                .edad(30)
                .build();

        Flux<AlumnoEntity> result = alumnoRepository.saveAll(Flux.just(activo, inactivo))
                .thenMany(alumnoRepository.findByEstado(EstadoAlumno.ACTIVO.getValor()));

        StepVerifier.create(result)
                .expectNextMatches(a -> a.getAlumnoId().equals("A003"))
                .verifyComplete();
    }
}