package com.ejercicio_bci.login.exception;

public class InvalidEmailException extends CustomException{

    public InvalidEmailException(int code, String msg) {
        super(code, msg);
    }
}
