package com.ejercicio_bci.login.service;

import com.ejercicio_bci.login.dto.MyUserDetails;
import com.ejercicio_bci.login.entity.UserEntity;
import com.ejercicio_bci.login.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByName(username);

        if (user == null) {
            log.error("No se encontro el usuario");
            throw new UsernameNotFoundException("No se encontro el usuario");
        }

        MyUserDetails userDetail = new MyUserDetails(user);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        userDetail.setAuthorities(authorities);

        return userDetail;
    }
}
