package com.belovstech.prjsalews.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="clients", schema = "clients")
@DynamicUpdate
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "birthday")
    private Date birthday;

    @CreatedDate
    @Column(name = "ins_date")
    private Date insDate;

    @Column(name = "ins_user")
    private UUID insUser;

    @LastModifiedDate
    @Column(name = "upd_date")
    private Date updDate;

    @Column(name = "upd_user")
    private UUID updUser;

    @Column(name = "owner")
    private UUID owner;

    @OneToMany
    @JoinColumn(name = "client_id")
    private Set<ClientToAddressEntity> addresses;

}
