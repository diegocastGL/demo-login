package com.ejercicio_bci.login.service;

import com.ejercicio_bci.login.util.JwtServiceMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class JwtServiceTest {

    @InjectMocks
    private JwtService service;

    @Test
    @DisplayName("Generate Token - OK")
    public void generarTokenTestOK() {
        String token = service.generateToken("username");

        assertNotNull(token);
    }

    @Test
    @DisplayName("Generate Token con email - OK")
    public void generarTokenConEmailTestOK() {
        String token = service.generateToken("username", "email@email.com");

        assertNotNull(token);
    }

    @Test
    @DisplayName("Extraer email de token - OK")
    public void extraerEmailDeTokenTestOK() {
        String token = service.generateToken("username", "email@email.com");

        String email = service.extractEmail(token);

        assertNotNull(email);
        assertEquals("email@email.com", email);
    }

    @Test
    @DisplayName("Extraer token de authorization con Bearer - OK")
    public void extraerTokenDeAuthorizationTestOK() {
        String tokenGenerado = service.generateToken("username", "email@email.com");
        String authorization = "Bearer " + tokenGenerado;
        assertTrue(authorization.startsWith("Bearer "));

        String token = service.extractTokenFromAuthorization(authorization);
        assertFalse(token.startsWith("Bearer "));
    }

    @Test
    @DisplayName("Extraer token de authorization si Bearer - OK")
    public void extraerTokenDeAuthorizationSinBearerTestOK() {
        String tokenGenerado = service.generateToken("username", "email@email.com");

        String token = service.extractTokenFromAuthorization(tokenGenerado);
        assertFalse(token.startsWith("Bearer "));
    }

    @Test
    @DisplayName("Extraer username de token - OK")
    public void extraerUsernameDeTokenTestOK() {
        String token = service.generateToken("username");
        String username = service.extractUserName(token);

        assertEquals("username", username);
    }

    @Test
    @DisplayName("validar token - OK")
    public void validarTokenTestOK() {
        String token = service.generateToken("username");
        assertTrue(service.validateToken(token, JwtServiceMock.buildUserDetails()));
    }

    @Test
    @DisplayName("validar token - INVALIDO")
    public void validarTokenInvalidoTest() {
        String token = service.generateToken("invalido");
        assertFalse(service.validateToken(token, JwtServiceMock.buildUserDetails()));
    }



}
