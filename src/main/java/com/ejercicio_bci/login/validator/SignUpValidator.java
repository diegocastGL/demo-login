package com.ejercicio_bci.login.validator;

import com.ejercicio_bci.login.exception.InvalidEmailException;
import com.ejercicio_bci.login.exception.InvalidPasswordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Pattern;

@Component
@Slf4j
public class SignUpValidator {

    public boolean isValidEmail(String email) throws InvalidEmailException {
        String emailDeEjemplo = "aaaaaaa@undominio.algo";

        if (Objects.isNull(email) || email.isBlank()) {
            log.info("Email es vacio o nulo");
            throw new InvalidEmailException(400,"Email es vacio o nulo");
        }

        String regex = "^(.+)@(\\S+)$";
        return Pattern.compile(regex).matcher(email).matches();
    }

    public boolean isValidPassword(String password) throws InvalidPasswordException {
        String passwordDeEjemplo = "a2asfGfdfdf4";

        if (Objects.isNull(password) || password.isBlank()) {
            log.info("Password vacio o nulo");
            throw new InvalidPasswordException(400, "Password vacio o nulo");
        }

        String regex = "([\\d{2}a-zA-Z{1}]){8,12}";
        return Pattern.compile(regex).matcher(password).matches();
    }
}
