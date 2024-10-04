package com.ejercicio_bci.login.controller;

import com.ejercicio_bci.login.dto.SignUpRequest;
import com.ejercicio_bci.login.dto.SignUpResponse;
import com.ejercicio_bci.login.exception.CustomException;
import com.ejercicio_bci.login.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign-up")
public class SignUpController {

    @Autowired
    private SignUpService service;

    @PostMapping
    public SignUpResponse signUp(@RequestBody SignUpRequest request) throws CustomException {
        return service.signUp(request);
    }
}
