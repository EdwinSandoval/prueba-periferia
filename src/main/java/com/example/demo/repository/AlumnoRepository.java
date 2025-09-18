package com.example.demo.repository;

import com.example.demo.model.AlumnoEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoRepository extends R2dbcRepository<AlumnoEntity, Long> {


    Mono<AlumnoEntity> findByAlumnoId(String id);
    Mono<Boolean> existsByAlumnoId(String alumnoId);
    Flux<AlumnoEntity> findByEstado(String estado);
}
