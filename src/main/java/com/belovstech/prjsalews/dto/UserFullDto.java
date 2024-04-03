package com.belovstech.prjsalews.dto;

import com.belovstech.prjsalews.entity.AuthEntity;
import com.belovstech.prjsalews.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFullDto {

    private UUID id;
    private String fullName;
    private Date birthday;
    private String phoneNumber;
    private String email;
    private Date insDate;
    private Date updDate;
    private Date lastIn;
    private Boolean isAccountExpired;
    public Boolean isAccountLocked;
    public Boolean isCredentialsExpired;
    public Boolean isEnabled;
    private AuthEntity auth;
    private Set<RoleEntity> roles;

}
