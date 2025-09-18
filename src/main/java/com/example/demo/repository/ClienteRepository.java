package com.example.demo.repository;

import com.example.demo.repository.entity.ClienteEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ClienteRepository extends R2dbcRepository<ClienteEntity, Long> {
}
