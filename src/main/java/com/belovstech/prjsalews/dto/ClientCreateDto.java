package com.belovstech.prjsalews.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ClientCreateDto {

    @NonNull
    private String fullName;
    @NonNull
    private String phoneNumber;
    private String email;
    private Date birthday;

}
