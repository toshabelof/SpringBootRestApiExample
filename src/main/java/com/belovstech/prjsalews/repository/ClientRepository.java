package com.belovstech.prjsalews.repository;

import com.belovstech.prjsalews.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, UUID> {

    List<ClientEntity> findByOwner(UUID owner);
    ClientEntity findByOwnerAndPhoneNumber(UUID owner, String phoneNumber);

    ClientEntity findByOwnerAndId(UUID owner,UUID uuid);
}
