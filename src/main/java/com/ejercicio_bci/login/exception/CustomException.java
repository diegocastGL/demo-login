package com.ejercicio_bci.login.exception;

import lombok.Getter;

@Getter
public class CustomException extends Exception {

    private Integer code;

    public CustomException(int code, String msg) {
        super(msg);
        this.code = code;
    }
}
