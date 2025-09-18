package com.example.demo.service;

import com.example.demo.controller.dto.ProductoDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoService {

    Mono<ProductoDto> getProducto(Long productoId);
    Mono<ProductoDto> createProducto(ProductoDto productoDto);
    Mono<ProductoDto> updateProducto(Long productoId, ProductoDto productoDto);
    Mono<Void> deleteProducto(Long productoId);
    Flux<ProductoDto> getAllProductos();

}
