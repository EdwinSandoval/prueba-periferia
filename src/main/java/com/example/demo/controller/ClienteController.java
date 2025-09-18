package com.example.demo.controller;

import com.example.demo.controller.dto.ClienteDto;
import com.example.demo.service.ClienteService;
import com.example.demo.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping("/{clienteId}")
    Mono<ClienteDto> getCliente(@PathVariable("clienteId") Long clienteId) {
        return clienteService.getCliente(clienteId);
    }

    @PostMapping
    Mono<ClienteDto> createCliente(@RequestBody ClienteDto clienteDto) {
        return clienteService.createCliente(clienteDto);
    }

    @PutMapping("/{clienteId}")
    Mono<ClienteDto> updateCliente(@PathVariable("clienteId") Long clienteId, @RequestBody ClienteDto clienteDto) {
        return clienteService.updateCliente(clienteId, clienteDto);
    }

    @DeleteMapping("/{clienteId}")
    Mono<Void> deleteCliente(@PathVariable("clienteId") Long clienteId) {
        return clienteService.deleteCliente(clienteId);
    }

    @GetMapping("/all")
    Flux<ClienteDto> getAllClientes() {
        return clienteService.getAllClientes();
    }
}
