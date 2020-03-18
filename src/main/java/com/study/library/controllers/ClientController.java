package com.study.library.controllers;

import com.study.library.converters.Converter;
import com.study.library.dto.ClientDto;
import com.study.library.entities.ClientEntity;
import com.study.library.exceptions.NotFoundException;
import com.study.library.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientRepository clientRepository;
    private final Converter converter;

    @Autowired
    public ClientController(ClientRepository clientRepository, Converter converter) {
        this.clientRepository = clientRepository;
        this.converter = converter;
    }

    @GetMapping("/{passport}")
    public ResponseEntity search(@PathVariable Integer passport) {
        Optional<ClientEntity> clientByPassportOptional = clientRepository.findByPassport(passport);
        ClientEntity clientEntity = clientByPassportOptional.orElseThrow(() -> {
            throw new NotFoundException("This client can not find");
        });
        return ResponseEntity.ok(converter.convertClientEntityToClientDto(clientEntity));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ClientDto client) {
        Optional<ClientEntity> clientByPassportOptional = clientRepository.findByPassport(client.getPassport());
        if (clientByPassportOptional.isPresent()) {
            throw new IllegalArgumentException("This client already exists");
        }
        ClientEntity save = clientRepository.save(converter.convertClientDtoToClientEntity(client));
        return ResponseEntity.ok(save);
    }

}
