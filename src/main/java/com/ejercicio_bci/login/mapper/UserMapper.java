package com.ejercicio_bci.login.mapper;

import com.ejercicio_bci.login.dto.SignUpRequest;
import com.ejercicio_bci.login.dto.SignUpResponse;
import com.ejercicio_bci.login.dto.LoginResponse;
import com.ejercicio_bci.login.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    public SignUpResponse entityToSignUpResponse(UserEntity entity) {
        SignUpResponse response = SignUpResponse.builder()
                .id(entity.getId())
                .created(entity.getCreated())
                .isActive(entity.getActive())
                .lastLogin(entity.getLastLogin())
                .build();

        return response;
    }

    public UserEntity signUpResponseToEntity(SignUpRequest signUp) {
        return UserEntity.builder()
                .active(true)
                .email(signUp.getEmail())
                .password(signUp.getPassword())
                .created(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .name(signUp.getName())
                .build();
    }

    public LoginResponse entityToLoginResponse(UserEntity entity) {
        return LoginResponse.builder()
                .id(entity.getId())
                .created(entity.getCreated())
                .lastLogin(entity.getLastLogin())
                .isActive(entity.getActive())
                .name(entity.getName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }
}
