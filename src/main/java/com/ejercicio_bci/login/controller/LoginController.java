package com.ejercicio_bci.login.controller;

import com.ejercicio_bci.login.config.RolesConfig;
import com.ejercicio_bci.login.dto.LoginResponse;
import com.ejercicio_bci.login.exception.CustomException;
import com.ejercicio_bci.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    @PreAuthorize(RolesConfig.PRE_AUTH_USER)
    public LoginResponse login(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authorization) throws CustomException {
        return loginService.login(authorization);
    }
}
