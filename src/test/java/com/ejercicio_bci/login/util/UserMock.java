package com.ejercicio_bci.login.util;

import com.ejercicio_bci.login.entity.UserEntity;

import java.time.LocalDateTime;

public class UserMock {

    public static UserEntity buildUserEntity() {
        return UserEntity.builder()
                .name("username")
                .password("password")
                .email("email@email.com")
                .id(1L)
                .created(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .active(true)
                .build();
    }
}
