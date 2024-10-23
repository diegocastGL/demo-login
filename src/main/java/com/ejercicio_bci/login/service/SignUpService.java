package com.ejercicio_bci.login.service;

import com.ejercicio_bci.login.dto.SignUpRequest;
import com.ejercicio_bci.login.dto.SignUpResponse;
import com.ejercicio_bci.login.exception.CustomException;

public interface SignUpService {

    public SignUpResponse signUp(SignUpRequest request) throws CustomException;
}
