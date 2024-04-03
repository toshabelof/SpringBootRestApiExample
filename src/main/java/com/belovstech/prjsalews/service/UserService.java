package com.belovstech.prjsalews.service;

import com.belovstech.prjsalews.controller.DefaultExceptionHandler;
import com.belovstech.prjsalews.entity.AuthEntity;
import com.belovstech.prjsalews.entity.RoleEntity;
import com.belovstech.prjsalews.entity.UserEntity;
import com.belovstech.prjsalews.exception.AlreadyExistException;
import com.belovstech.prjsalews.exception.DataBaseException;
import com.belovstech.prjsalews.exception.NotFoundException;
import com.belovstech.prjsalews.repository.AuthRepository;
import com.belovstech.prjsalews.repository.RoleRepository;
import com.belovstech.prjsalews.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userService")
public class UserService {

    private static final Logger logger = LogManager.getLogger(DefaultExceptionHandler.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(UUID id) {
        return userRepository
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public UUID createUser(UserEntity user) throws AlreadyExistException {
        try {
            Optional<UserEntity> userData = userRepository.findByPhoneNumber(user.getPhoneNumber());
            if (userData.isPresent()) {
                throw new AlreadyExistException("Пользователь с таким номером телефоном уже существует");
            }

            AuthEntity auth = user.getAuth();
            auth.setPassword(passwordEncoder.encode(auth.getPassword()));
            user.setAuth(auth);
            authRepository.save(auth);

            RoleEntity role = roleRepository.findByRoleName(RoleEntity.SUPER_ADMIN);
            Set<RoleEntity> roles = new HashSet<>();
            roles.add(role);

            user.setRoles(roles);

            return userRepository.save(user).getId();

        } catch (RuntimeException ex) {
            throw new DataBaseException(ex.getMessage());
        }
    }

    public UUID updateUser(UserEntity user) throws NotFoundException{
        UserEntity userData = userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        try{
            AuthEntity auth = user.getAuth();
            auth.setPassword(passwordEncoder.encode(auth.getPassword()));
            user.setAuth(auth);

            userRepository.save(user);
            return user.getId();
        }
        catch (RuntimeException ex){
            throw new DataBaseException(ex.getMessage());
        }
    }

    public UUID deleteUser(UUID userId) throws NotFoundException {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        try {
            userRepository.deleteById(user.getId());
            return user.getId();
        } catch (RuntimeException ex) {
            throw new DataBaseException(ex.getMessage());
        }
    }
}
