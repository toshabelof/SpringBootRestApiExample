package com.belovstech.prjsalews.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "client2address", schema = "clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ClientToAddressEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @NonNull
    @Column(name = "client_id")
    private UUID clientId;

    @NonNull
    private String address;

    @NonNull
    @CreatedDate
    @Column(name = "ins_date")
    private Date insDate;

    @NonNull
    @Column(name = "ins_user")
    private UUID insUser;

    @NonNull
    @LastModifiedDate
    @Column(name = "upd_date")
    private Date updDate;

    @NonNull
    @Column(name = "upd_user")
    private UUID updUser;

}
