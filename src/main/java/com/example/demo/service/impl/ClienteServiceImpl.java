package com.example.demo.service.impl;

import com.example.demo.controller.dto.ClienteDto;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.entity.ClienteEntity;
import com.example.demo.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public Mono<ClienteDto> getCliente(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .map(clienteEntity -> new ClienteDto(clienteEntity.getId(), clienteEntity.getNombre(), clienteEntity.getEmail()));
    }

    @Override
    public Mono<ClienteDto> createCliente(ClienteDto clienteDto) {
        return clienteRepository.save(ClienteEntity.builder()
                .nombre(clienteDto.nombre())
                .email(clienteDto.email())
                .build())
                .map(clienteEntity -> new ClienteDto(clienteEntity.getId(), clienteEntity.getNombre(), clienteEntity.getEmail()));
    }

    @Override
    public Mono<ClienteDto> updateCliente(Long clienteId, ClienteDto clienteDto) {
        return clienteRepository.save(ClienteEntity.builder()
                .id(clienteId)
                        .nombre(clienteDto.nombre())
                        .email(clienteDto.email())
                .build())
                .map(clienteEntity -> new ClienteDto(clienteEntity.getId(), clienteEntity.getNombre(), clienteEntity.getEmail()));
    }

    @Override
    public Mono<Void> deleteCliente(Long clienteId) {
        return clienteRepository.deleteById(clienteId);
    }

    @Override
    public Flux<ClienteDto> getAllClientes() {
        return clienteRepository.findAll()
                .map(clienteEntity -> new ClienteDto(clienteEntity.getId(), clienteEntity.getNombre(), clienteEntity.getEmail()));
    }
}
