package com.belovstech.prjsalews.controller;

import com.belovstech.prjsalews.dto.*;
import com.belovstech.prjsalews.entity.ClientEntity;
import com.belovstech.prjsalews.entity.UserEntity;
import com.belovstech.prjsalews.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Tag(name = "UserService", description = "Контроллер управления пользователями")
@RestController
@AllArgsConstructor
@RequestMapping("/Users")
public class UserController {

    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<UserFullDto>> getUsers() {
        List<UserEntity> userData = userService.getAllUsers();

        return ResponseEntity.ok(
                userData.stream().map(this::convertToUserFullDto).collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserFullDto> getUserById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(
                convertToUserFullDto(userService.getUserById(id))
        );
    }

    @PostMapping
    public ResponseEntity<UserShortDto> createUser(@RequestBody UserEntity user) {
        return ResponseEntity.ok(
                new UserShortDto(userService.createUser(user))
        );
    }

    @PutMapping
    public ResponseEntity<UserShortDto> updateUser(@RequestBody UserEntity user){
        return ResponseEntity.ok(
                new UserShortDto(userService.updateUser(user))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserShortDto> deleteUserById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(
                new UserShortDto(userService.deleteUser(id))
        );
    }

    private <T> UserEntity convertToEntity(T userDto){
        return modelMapper.map(userDto, UserEntity.class);
    }

    private UserFullDto convertToUserFullDto(UserEntity user){
        return modelMapper.map(user, UserFullDto.class);
    }
}
