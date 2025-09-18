package com.example.demo.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@Table("cliente")
public class ClienteEntity {

    @Id
    Long id;

    String nombre;

    String email;

}
