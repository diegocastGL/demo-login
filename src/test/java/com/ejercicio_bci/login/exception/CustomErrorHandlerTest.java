package com.ejercicio_bci.login.exception;

import com.ejercicio_bci.login.dto.ErrorResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CustomErrorHandlerTest {

    @InjectMocks
    private CustomErrorHandler errorHandler;

    @Test
    @DisplayName("Handle InvalidPasswordException")
    public void handleInvalidPasswordException() {
       InvalidPasswordException exception = new InvalidPasswordException(400, "Password nulo o vacio");
       ResponseEntity<ErrorResponseDTO> result = errorHandler.handleException(exception);

       assertNotNull(result);
       assertEquals("Password nulo o vacio", result.getBody().getError().get(0).getDetail());
       assertEquals(400, result.getBody().getError().get(0).getCodigo());
    }

    @Test
    @DisplayName("Handle InvalidEmailException")
    public void handleInvalidEmailException() {
        InvalidEmailException exception = new InvalidEmailException(400, "Email nulo o vacio");
        ResponseEntity<ErrorResponseDTO> result = errorHandler.handleException(exception);

        assertNotNull(result);
        assertEquals("Email nulo o vacio", result.getBody().getError().get(0).getDetail());
        assertEquals(400, result.getBody().getError().get(0).getCodigo());
    }

    @Test
    @DisplayName("Handle Exception")
    public void handleException() {
        Exception exception = new Exception();
        ResponseEntity<ErrorResponseDTO> result = errorHandler.handleException(exception);

        assertNotNull(result);
        assertEquals("Error Interno", result.getBody().getError().get(0).getDetail());
        assertEquals(500, result.getBody().getError().get(0).getCodigo());
    }
}
