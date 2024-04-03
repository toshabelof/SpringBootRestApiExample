package com.belovstech.prjsalews.config;

import com.belovstech.prjsalews.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private UserEntity user;

    public UUID getId(){
        return user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> simpleGrantedAuthority = new ArrayList<>();

        for (var role: user.getRoles()){
            simpleGrantedAuthority.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return simpleGrantedAuthority;
    }

    @Override
    public String getPassword() {
        return user.getAuth().getPassword();
//        return null;
    }

    @Override
    public String getUsername() {
        return user.getAuth().getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
