package com.belovstech.prjsalews.repository;

import com.belovstech.prjsalews.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByPhoneNumber(String phoneNumber);

    Optional<UserEntity> findByAuthLogin(String login);

}
