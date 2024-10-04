package com.ejercicio_bci.login.exception;

import com.ejercicio_bci.login.dto.ErrorDTO;
import com.ejercicio_bci.login.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomErrorHandler {

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(InvalidPasswordException exception) {
        ErrorDTO error = ErrorDTO.builder().codigo(exception.getCode()).detail(exception.getMessage()).timestamp(LocalDateTime.now()).build();
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(error);
        return new ResponseEntity<>(ErrorResponseDTO.builder().error(errors).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(InvalidEmailException exception) {
        ErrorDTO error = ErrorDTO.builder().codigo(exception.getCode()).detail(exception.getMessage()).timestamp(LocalDateTime.now()).build();
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(error);
        return new ResponseEntity<>(ErrorResponseDTO.builder().error(errors).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception exception) {
        ErrorDTO error = ErrorDTO.builder().codigo(500).detail("Error Interno").timestamp(LocalDateTime.now()).build();
        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(error);
        return new ResponseEntity<>(ErrorResponseDTO.builder().error(errors).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
