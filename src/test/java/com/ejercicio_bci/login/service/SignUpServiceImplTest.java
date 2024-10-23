package com.ejercicio_bci.login.service;

import com.ejercicio_bci.login.dto.SignUpRequest;
import com.ejercicio_bci.login.dto.SignUpResponse;
import com.ejercicio_bci.login.exception.CustomException;
import com.ejercicio_bci.login.exception.InvalidEmailException;
import com.ejercicio_bci.login.exception.InvalidPasswordException;
import com.ejercicio_bci.login.mapper.PhoneMapper;
import com.ejercicio_bci.login.mapper.UserMapper;
import com.ejercicio_bci.login.repository.PhoneRepository;
import com.ejercicio_bci.login.repository.UserRepository;
import com.ejercicio_bci.login.service.impl.JwtServiceimpl;
import com.ejercicio_bci.login.service.impl.SignUpServiceImpl;
import com.ejercicio_bci.login.util.SignUpMock;
import com.ejercicio_bci.login.util.UserMock;
import com.ejercicio_bci.login.validator.SignUpValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SignUpServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PhoneRepository phoneRepository;

    @Mock
    private SignUpValidator validator;

    @Mock
    private JwtServiceimpl jwtServiceimpl;

    @Spy
    private UserMapper userMapper;

    @Spy
    private PhoneMapper phoneMapper;

    @InjectMocks
    private SignUpServiceImpl service;

    @Test
    @DisplayName("Sign Up - OK")
    public void signUpTestOK() throws CustomException {
        when(validator.isValidEmail(anyString())).thenReturn(true);
        when(validator.isValidPassword(anyString())).thenReturn(true);
        when(userRepository.save(any())).thenReturn(UserMock.buildUserEntity());
        when(phoneRepository.saveAll(any())).thenReturn(new ArrayList<>());
        when(jwtServiceimpl.generateToken(anyString(),anyString())).thenReturn("TOKEN");

        SignUpRequest request = SignUpMock.buildSignUpRequest();
        SignUpResponse response = service.signUp(request);

        assertNotNull(response);
        assertEquals("TOKEN", response.getToken());
    }

    @Test
    @DisplayName("Sign Up - email invalido")
    public void signUpTestEmailInvalido() throws CustomException {
        when(validator.isValidEmail(anyString())).thenReturn(false);
        SignUpRequest request = SignUpMock.buildSignUpRequest();
        Throwable exception = assertThrows(InvalidEmailException.class, () -> service.signUp(request));

        assertNotNull(exception);
        assertEquals("El e-mail es inválido.", exception.getMessage());
    }

    @Test
    @DisplayName("Sign Up - password invalido")
    public void signUpTestPasswordInvalido() throws CustomException {
        when(validator.isValidEmail(anyString())).thenReturn(true);
        when(validator.isValidPassword(anyString())).thenReturn(false);
        SignUpRequest request = SignUpMock.buildSignUpRequest();
        Throwable exception = assertThrows(InvalidPasswordException.class, () -> service.signUp(request));

        assertNotNull(exception);
        assertEquals("La contraseña no cumple los requisitos.", exception.getMessage());
    }

    @Test
    @DisplayName("Sign Up - User repo exception")
    public void signUpTestPhoneNull() throws CustomException {
        when(validator.isValidEmail(anyString())).thenReturn(true);
        when(validator.isValidPassword(anyString())).thenReturn(true);
        when(userRepository.save(any())).thenReturn(UserMock.buildUserEntity());
        when(jwtServiceimpl.generateToken(anyString(),anyString())).thenReturn("TOKEN");

        SignUpRequest request = SignUpMock.buildSignUpRequest();
        request.setPhones(null);
        SignUpResponse response = service.signUp(request);

        assertNotNull(response);
        assertEquals("TOKEN", response.getToken());
    }
}
