package com.belovstech.prjsalews.repository;

import com.belovstech.prjsalews.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, UUID> {
}
