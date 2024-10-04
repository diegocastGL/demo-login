package com.ejercicio_bci.login.exception;

public class UserNotFoundException extends CustomException{

    public UserNotFoundException(int code, String msg) {
        super(code, msg);
    }
}
