package com.ejercicio_bci.login.exception;

public class InvalidPasswordException extends CustomException{

    public InvalidPasswordException(int code, String msg) {
        super(code, msg);
    }
}
