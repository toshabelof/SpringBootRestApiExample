package com.belovstech.prjsalews.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientToAddressFullDto {

    private UUID id;
    private UUID clientId;
    private String address;
    private Date insDate;
    private UUID insUser;
    private Date updDate;
    private UUID updUser;

}
