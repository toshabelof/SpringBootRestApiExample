package com.belovstech.prjsalews.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ClientUpdateDto {

    @NonNull
    private UUID id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private Date birthday;

}
