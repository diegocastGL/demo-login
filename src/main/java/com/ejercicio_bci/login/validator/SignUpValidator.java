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
            log.error("Password vacio o nulo");
            throw new InvalidPasswordException(400, "Password vacio o nulo");
        }

        if (password.length() < 8) {
            log.error("Password menor a 8 caracteres");
            throw new InvalidPasswordException(400, "Password menor a 8 caracteres");
        }

        if (password.length() > 12) {
            log.error("Password mayor a 12 caracteres");
            throw new InvalidPasswordException(400, "Password mayor a 12 caracteres");
        }


        String regex = "^(?=.*[A-Z])(?=(?:[^0-9]*[0-9][^0-9]*){2})(?=.{8,12}$).*";
        return Pattern.compile(regex).matcher(password).matches();
    }
}
