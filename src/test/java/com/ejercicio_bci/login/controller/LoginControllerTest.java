package com.ejercicio_bci.login.controller;

import com.ejercicio_bci.login.dto.LoginResponse;
import com.ejercicio_bci.login.exception.CustomException;
import com.ejercicio_bci.login.service.impl.LoginServiceImpl;
import com.ejercicio_bci.login.util.LoginMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @Mock
    private LoginServiceImpl service;

    @InjectMocks
    private LoginController controller;

    @Test
    @DisplayName("Login - OK")
    public void loginTestOK() throws CustomException {
        when(service.login(anyString())).thenReturn(LoginMock.buildLoginResponse());
        LoginResponse response = controller.login("token");
        assertNotNull(response);
        assertEquals(1, response.getId());
        assertEquals("EMAIL@EMAIL.COM", response.getEmail());
    }
}
