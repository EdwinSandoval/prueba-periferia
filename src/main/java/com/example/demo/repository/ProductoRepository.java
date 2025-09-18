package com.example.demo.repository;

import com.example.demo.repository.entity.ClienteEntity;
import com.example.demo.repository.entity.ProductoEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ProductoRepository extends R2dbcRepository<ProductoEntity, Long> {
}
