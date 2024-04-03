package com.belovstech.prjsalews.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.*;

@Entity
@Table(name = "users", schema = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NonNull
    @Column(name = "full_name")
    private String fullName;

    @NonNull
    private Date birthday;

    @NonNull
    @Column(name = "phone_number")
    private String phoneNumber;

    @NonNull
    private String email;

    @NonNull
    @CreatedDate
    @Column(name = "ins_date")
    private Date insDate;

    @NonNull
    @LastModifiedDate
    @Column(name = "upd_date")
    private Date updDate;

    @NonNull
    @Column(name = "last_in")
    private Date lastIn = Calendar.getInstance().getTime();

    @Column(name = "is_account_expired")
    private Boolean isAccountExpired = false;

    @Column(name = "is_account_locked")
    public Boolean isAccountLocked = false;

    @Column(name = "is_credentials_expired")
    public Boolean isCredentialsExpired = false;

    @Column(name = "is_enabled")
    public Boolean isEnabled = true;

    @OneToOne
    @JoinColumn(name = "auth_id")
    private AuthEntity auth;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "users", name = "user2roles",
            joinColumns =
                    {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<RoleEntity> roles;
}
