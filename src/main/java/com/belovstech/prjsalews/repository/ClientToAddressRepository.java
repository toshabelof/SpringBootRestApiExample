package com.belovstech.prjsalews.repository;

import com.belovstech.prjsalews.entity.ClientToAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientToAddressRepository extends JpaRepository<ClientToAddressEntity, UUID> {

    List<ClientToAddressEntity> findByClientId(UUID clientId);

    ClientToAddressEntity findByAddress(String address);
}
