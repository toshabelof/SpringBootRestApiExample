package com.belovstech.prjsalews.controller;

import com.belovstech.prjsalews.dto.*;
import com.belovstech.prjsalews.entity.ClientToAddressEntity;
import com.belovstech.prjsalews.service.ClientToAddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Tag(name = "ClientToAddress", description = "Контроллер управления адресами клиентов")
@RestController
@AllArgsConstructor
@RequestMapping("/ClientToAddress")
public class ClientToAddressController {

    private ClientToAddressService addressService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ClientToAddressFullDto>> getAddresses(UUID clientId){
        List<ClientToAddressEntity> addressData = addressService.getAddressByClientId(clientId);

        return ResponseEntity.ok(
                addressData.stream().map(this::convertToAddressFullDto).collect(Collectors.toList())
        );
    }

    @PostMapping
    public ResponseEntity<ClientShortDto> createAddress(ClientToAddressCreateDto address){

        return ResponseEntity.ok(
            new ClientShortDto(addressService.saveAddress(convertToEntity(address)))
        );
    }

    @PutMapping
    public ResponseEntity<ClientShortDto> updateAddress(ClientToAddressUpdateDto address){

        return ResponseEntity.ok(
                new ClientShortDto(addressService.updateAddress(convertToEntity(address)))
        );
    }

    @DeleteMapping
    public ResponseEntity<ClientShortDto> deleteAddress(UUID addressId){

        return ResponseEntity.ok(
                new ClientShortDto(addressService.deleteAddress(addressId))
        );
    }

    private <T> ClientToAddressEntity convertToEntity(T addressDto){
        return modelMapper.map(addressDto, ClientToAddressEntity.class);
    }

    private <T> ClientToAddressFullDto convertToAddressFullDto(T addressEntity){
        return modelMapper.map(addressEntity, ClientToAddressFullDto.class);
    }

}
