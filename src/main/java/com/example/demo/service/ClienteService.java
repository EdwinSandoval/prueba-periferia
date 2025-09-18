package com.example.demo.service;

import com.example.demo.controller.dto.ClienteDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClienteService {

    Mono<ClienteDto> getCliente(Long clienteId);
    Mono<ClienteDto> createCliente(ClienteDto clienteDto);
    Mono<ClienteDto> updateCliente(Long clienteId, ClienteDto clienteDto);
    Mono<Void> deleteCliente(Long clienteId);
    Flux<ClienteDto> getAllClientes();

}
