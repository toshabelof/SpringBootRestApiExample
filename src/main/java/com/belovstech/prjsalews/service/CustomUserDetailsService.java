package com.belovstech.prjsalews.service;

import com.belovstech.prjsalews.config.CustomUserDetails;
import com.belovstech.prjsalews.entity.UserEntity;
import com.belovstech.prjsalews.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByAuthLogin(username);
        return user.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
