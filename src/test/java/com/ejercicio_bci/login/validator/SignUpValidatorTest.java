package com.ejercicio_bci.login.validator;

import com.ejercicio_bci.login.exception.InvalidEmailException;
import com.ejercicio_bci.login.exception.InvalidPasswordException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class SignUpValidatorTest {

    @InjectMocks
    private SignUpValidator validator;

    @Test
    @DisplayName("isValidEmail - OK")
    public void isValidEmailOK() throws InvalidEmailException {
        String email = "aaaaaaa@undominio.algo";
        boolean result = validator.isValidEmail(email);
        assertTrue(result);
    }

    @Test
    @DisplayName("isValidEmail - vacio")
    public void isValidEmailVacio() throws InvalidEmailException {
        Throwable exception = assertThrows(InvalidEmailException.class, () -> validator.isValidEmail(""));
        assertNotNull(exception);
        assertEquals("Email es vacio o nulo", exception.getMessage());
    }
    @Test
    @DisplayName("isValidEmail - nulo")
    public void isValidEmailNulo() throws InvalidEmailException {
        Throwable exception = assertThrows(InvalidEmailException.class, () -> validator.isValidEmail(null));
        assertNotNull(exception);
        assertEquals("Email es vacio o nulo", exception.getMessage());
    }


    @Test
    @DisplayName("isValidPassword - OK")
    public void isValidPasswordOK() throws InvalidPasswordException {
        String password = "a2asfGfdfdf4";
        boolean result = validator.isValidPassword(password);
        assertTrue(result);
    }

    @Test
    @DisplayName("isValidPassword - vacio")
    public void isValidPasswordVacio() {
        Throwable exception = assertThrows(InvalidPasswordException.class, () -> validator.isValidPassword(""));
        assertNotNull(exception);
        assertEquals("Password vacio o nulo", exception.getMessage());
    }

    @Test
    @DisplayName("isValidPassword - nulo")
    public void isValidPasswordNulo() {
        Throwable exception = assertThrows(InvalidPasswordException.class, () -> validator.isValidPassword(null));
        assertNotNull(exception);
        assertEquals("Password vacio o nulo", exception.getMessage());
    }
}