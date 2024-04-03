package com.belovstech.prjsalews.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class UserUpdateDto {

    private String fullName;
    private Date birthday;
    private String phoneNumber;
    private String email;
    private Date updDate = Calendar.getInstance().getTime();
    private Date lastIn = Calendar.getInstance().getTime();
    private Boolean isAccountExpired;
    public Boolean isAccountLocked;
    public Boolean isCredentialsExpired;
    public Boolean isEnabled;

}
