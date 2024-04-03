package com.belovstech.prjsalews.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class UserCreateDto {

    @NonNull
    private String fullName;
    private Date birthday;
    @NonNull
    private String phoneNumber;
    private String email;
}
