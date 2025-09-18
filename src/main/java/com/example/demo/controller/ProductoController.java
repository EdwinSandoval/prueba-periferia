package com.example.demo.controller;

import com.example.demo.controller.dto.ProductoDto;
import com.example.demo.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping("/{productoId}")
    Mono<ProductoDto> getProducto(@PathVariable("productoId") Long productoId) {
        return productoService.getProducto(productoId);
    }

    @PostMapping
    Mono<ProductoDto> createProducto(@RequestBody ProductoDto productoDto) {
        return productoService.createProducto(productoDto);
    }

    @PutMapping("/{productoId}")
    Mono<ProductoDto> updateProducto(@PathVariable("productoId") Long productoId, @RequestBody ProductoDto productoDto) {
        return productoService.updateProducto(productoId, productoDto);
    }

    @DeleteMapping("/{productoId}")
    Mono<Void> deleteProducto(@PathVariable("productoId") Long productoId) {
        return productoService.deleteProducto(productoId);
    }

    @GetMapping("/all")
    Flux<ProductoDto> getAllProductos() {
        return productoService.getAllProductos();
    }
}
