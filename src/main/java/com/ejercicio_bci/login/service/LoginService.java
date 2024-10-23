package com.ejercicio_bci.login.service;

import com.ejercicio_bci.login.dto.LoginResponse;
import com.ejercicio_bci.login.exception.UserNotFoundException;

public interface LoginService {

    public LoginResponse login(String authorization) throws UserNotFoundException;
}
