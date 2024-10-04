package com.ejercicio_bci.login.util;

import com.ejercicio_bci.login.dto.SignUpRequest;
import com.ejercicio_bci.login.dto.SignUpResponse;

import java.time.LocalDateTime;

public class SignUpMock {

    public static SignUpResponse buildsignUpResponse() {
        return SignUpResponse.builder()
                .id(1L)
                .token("TOKEN")
                .isActive(true)
                .created(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .build();
    }

    public static SignUpRequest buildSignUpRequest() {
        return SignUpRequest.builder()
                .name("NAME")
                .email("EMAIL@EMAIL.COM")
                .password("PASSWORD")
                .phones(LoginMock.buildPhonesDTO())
                .build();
    }
}
