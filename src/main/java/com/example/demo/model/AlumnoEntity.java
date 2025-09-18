package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
