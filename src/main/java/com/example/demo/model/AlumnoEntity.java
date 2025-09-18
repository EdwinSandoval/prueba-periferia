package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Table("alumno")
public class AlumnoEntity {

    @Id
    private Long id;
    @Column("alumno_id")
    private String alumnoId;

    private String nombre;

    private String apellido;

    private EstadoAlumno estado;

    private Integer edad;


}
