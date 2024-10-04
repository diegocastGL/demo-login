package com.ejercicio_bci.login.controller;

import com.ejercicio_bci.login.dto.SignUpRequest;
import com.ejercicio_bci.login.dto.SignUpResponse;
import com.ejercicio_bci.login.exception.CustomException;
import com.ejercicio_bci.login.service.SignUpService;
import com.ejercicio_bci.login.util.SignUpMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SignUpControllerTest {

    @Mock
    private SignUpService service;

    @InjectMocks
    private SignUpController controller;

    @Test
    @DisplayName("Sign up - OK")
    public void signUpTestOK() throws CustomException {
        when(service.signUp(any())).thenReturn(SignUpMock.buildsignUpResponse());
        SignUpRequest request = SignUpMock.buildSignUpRequest();
        SignUpResponse response = controller.signUp(request);

        assertNotNull(response);
        assertEquals("TOKEN", response.getToken());
        assertTrue(response.getIsActive());

        System.out.println("TEST OK");
    }
}
