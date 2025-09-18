package com.example.demo.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@Table("producto")
public class ProductoEntity {

    @Id
    Long id;

    String nombre;

    Double precio;

}
