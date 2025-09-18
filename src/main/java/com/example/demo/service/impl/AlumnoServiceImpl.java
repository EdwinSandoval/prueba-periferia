package com.example.demo.service.impl;

import com.example.demo.controller.dto.AlumnoDto;
import com.example.demo.model.AlumnoEntity;
import com.example.demo.model.EstadoAlumno;
import com.example.demo.repository.AlumnoRepository;
import com.example.demo.service.AlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;

    @Override
    public Mono<AlumnoDto> getAlumno(String alumnoId) {
        return alumnoRepository.findByAlumnoId(alumnoId)
                .map(alumnoEntity -> new AlumnoDto(
                        alumnoEntity.getId(),
                        alumnoEntity.getAlumnoId(),
                        alumnoEntity.getNombre(),
                        alumnoEntity.getApellido(),
                        alumnoEntity.getEstado(),
                        alumnoEntity.getEdad()));
    }


    @Override
    public Mono<Void> createAlumno(AlumnoDto dto) {
        return alumnoRepository.existsByAlumnoId(dto.alumnoId())
                .flatMap(existe -> {
                    if (existe) {
                        return Mono.error(new IllegalArgumentException("El alumno con el ID " + dto.id() + " ya está registrado"));
                    }

                    AlumnoEntity entity = buildAlumnoEntity(dto, EstadoAlumno.ACTIVO);
                    return alumnoRepository.save(entity).then();
                });
    }

    @Override
    public Mono<AlumnoDto> updateAlumno(String alumnoId, AlumnoDto dto) {
        return getAlumno(alumnoId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("El alumno con ID " + alumnoId + " no existe")))
                .flatMap(alumnoExistente -> {
                    // Aquí usamos el ID interno para evitar el error de clave duplicada
                    AlumnoEntity entity = buildAlumnoEntity(dto, dto.estado());
                    entity.setId(alumnoExistente.id()); // ¡Clave para que sea una actualización!
                    return alumnoRepository.save(entity).map(this::toDto);
                });
    }

    @Override
    public Mono<Void> deleteAlumno(String alumnoId) {
        return alumnoRepository.findByAlumnoId(alumnoId)
                .flatMap(alumno -> alumnoRepository.deleteById(alumno.getId()))
                .switchIfEmpty(Mono.error(new IllegalArgumentException("No existe el alumno con código: " + alumnoId)));
    }

    @Override
    public Flux<AlumnoDto> getAllAlumnos() {
        return alumnoRepository.findAll()
                .map(alumnoEntity -> new AlumnoDto(alumnoEntity.getId(),alumnoEntity.getAlumnoId(),
                        alumnoEntity.getNombre(),
                        alumnoEntity.getApellido(),
                        alumnoEntity.getEstado(),
                        alumnoEntity.getEdad()));
    }

    @Override
    public Flux<AlumnoDto> getAlumnosActivos() {
        return alumnoRepository.findByEstado(EstadoAlumno.ACTIVO.getValor())
                .map(alumnoEntity -> new AlumnoDto(
                        alumnoEntity.getId(),
                        alumnoEntity.getAlumnoId(),
                        alumnoEntity.getNombre(),
                        alumnoEntity.getApellido(),
                        alumnoEntity.getEstado(),
                        alumnoEntity.getEdad()
                ));
    }

    private AlumnoEntity buildAlumnoEntity(AlumnoDto dto,EstadoAlumno estado) {
        return AlumnoEntity.builder()
                .alumnoId(dto.alumnoId())
                .nombre(dto.nombre())
                .apellido(dto.apellido())
                .estado(estado)
                .edad(dto.edad())
                .build();
    }

    private AlumnoDto toDto(AlumnoEntity entity) {
        return new AlumnoDto(
                entity.getId(),
                entity.getAlumnoId(),
                entity.getNombre(),
                entity.getApellido(),
                entity.getEstado(),
                entity.getEdad()
        );
    }
}
