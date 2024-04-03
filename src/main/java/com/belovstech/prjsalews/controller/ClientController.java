package com.belovstech.prjsalews.controller;

import com.belovstech.prjsalews.config.CustomUserDetails;
import com.belovstech.prjsalews.dto.*;
import com.belovstech.prjsalews.entity.ClientEntity;
import com.belovstech.prjsalews.service.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Tag(name = "ClientService", description = "Контроллер управления клиентами")
@RestController
@AllArgsConstructor
@RequestMapping("/Client")
public class ClientController {

    private ClientService clientService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ClientFullDto>> getClients(@AuthenticationPrincipal CustomUserDetails userDetails) {
        List<ClientEntity> clientData = clientService.getAllClients(userDetails);

        return ResponseEntity.ok(
                clientData.stream().map(this::convertToClientFullDto).collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientFullDto> getClientById(
            @PathVariable("id") UUID id,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        return ResponseEntity.ok(
                convertToClientFullDto(clientService.getClientById(id, userDetails))
        );
    }

    @PostMapping
    public ResponseEntity<ClientShortDto> createClient(
            @RequestBody ClientCreateDto client,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        return ResponseEntity.ok(
                new ClientShortDto(clientService.createClient(convertToEntity(client), userDetails))
        );
    }

    @PutMapping
    public ResponseEntity<ClientShortDto> updateClient(
            @RequestBody ClientUpdateDto client,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        return ResponseEntity.ok(
                new ClientShortDto(clientService.updateClient(convertToEntity(client), userDetails))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientShortDto> deleteClientById(
            @PathVariable("id") UUID id,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        return ResponseEntity.ok(new ClientShortDto(clientService.deleteClient(id, userDetails)));
    }



    private <T> ClientEntity convertToEntity(T clientDto){
        return modelMapper.map(clientDto, ClientEntity.class);
    }

    private ClientFullDto convertToClientFullDto(ClientEntity client){
        return modelMapper.map(client, ClientFullDto.class);
    }
}
