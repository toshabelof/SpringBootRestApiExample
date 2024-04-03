package com.belovstech.prjsalews.dto;

import com.belovstech.prjsalews.entity.ClientToAddressEntity;
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
public class ClientFullDto {
    private UUID id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private Date birthday;
    private Date insDate;
    private UUID insUser;
    private Date updDate;
    private UUID updUser;
    private UUID owner;
    private Set<ClientToAddressEntity> addresses;
}
