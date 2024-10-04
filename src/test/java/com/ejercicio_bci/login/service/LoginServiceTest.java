package com.ejercicio_bci.login.service;

import com.ejercicio_bci.login.dto.LoginResponse;
import com.ejercicio_bci.login.exception.CustomException;
import com.ejercicio_bci.login.exception.UserNotFoundException;
import com.ejercicio_bci.login.mapper.PhoneMapper;
import com.ejercicio_bci.login.mapper.UserMapper;
import com.ejercicio_bci.login.repository.PhoneRepository;
import com.ejercicio_bci.login.repository.UserRepository;
import com.ejercicio_bci.login.util.PhoneMock;
import com.ejercicio_bci.login.util.UserMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PhoneRepository phoneRepository;

    @Spy
    private UserMapper userMapper;

    @Spy
    private PhoneMapper phoneMapper;

    @InjectMocks
    private LoginService service;

    @Test
    @DisplayName("Login - OK")
    public void loginTestOK() throws CustomException {
        when(jwtService.extractTokenFromAuthorization(anyString())).thenReturn("TOKEN");
        when(jwtService.extractEmail(anyString())).thenReturn("email@email.com");
        when(userRepository.findByEmail(anyString())).thenReturn(UserMock.buildUserEntity());
        when(phoneRepository.findAllByUserId(anyLong())).thenReturn(PhoneMock.buildPhonesEntities());
        when(jwtService.generateToken(anyString(), anyString())).thenReturn("NEW TOKEN");
        when(userRepository.save(any())).thenReturn(UserMock.buildUserEntity());

        LoginResponse response = service.login("Authorization");

        assertNotNull(response);
        assertEquals("NEW TOKEN", response.getToken());
    }

    @Test
    @DisplayName("Login - UserNotFoundException")
    public void loginTestUserNotFoundException() {
        when(jwtService.extractTokenFromAuthorization(anyString())).thenReturn("TOKEN");
        when(jwtService.extractEmail(anyString())).thenReturn("email@email.com");
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        Throwable exception = assertThrows(UserNotFoundException.class, () -> service.login("Authorization"));

        assertNotNull(exception);
        assertEquals("Usuario no encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Login - phones entities nulos")
    public void loginTestPhonesNull() throws CustomException {
        when(jwtService.extractTokenFromAuthorization(anyString())).thenReturn("TOKEN");
        when(jwtService.extractEmail(anyString())).thenReturn("email@email.com");
        when(userRepository.findByEmail(anyString())).thenReturn(UserMock.buildUserEntity());
        when(phoneRepository.findAllByUserId(anyLong())).thenReturn(null);
        when(jwtService.generateToken(anyString(), anyString())).thenReturn("NEW TOKEN");
        when(userRepository.save(any())).thenReturn(UserMock.buildUserEntity());

        LoginResponse response = service.login("Authorization");

        assertNotNull(response);
        assertEquals("NEW TOKEN", response.getToken());
    }

    @Test
    @DisplayName("Login - phones entities vacios")
    public void loginTestPhonesEmpty() throws CustomException {
        when(jwtService.extractTokenFromAuthorization(anyString())).thenReturn("TOKEN");
        when(jwtService.extractEmail(anyString())).thenReturn("email@email.com");
        when(userRepository.findByEmail(anyString())).thenReturn(UserMock.buildUserEntity());
        when(phoneRepository.findAllByUserId(anyLong())).thenReturn(new ArrayList<>());
        when(jwtService.generateToken(anyString(), anyString())).thenReturn("NEW TOKEN");
        when(userRepository.save(any())).thenReturn(UserMock.buildUserEntity());

        LoginResponse response = service.login("Authorization");

        assertNotNull(response);
        assertEquals("NEW TOKEN", response.getToken());
    }
}
