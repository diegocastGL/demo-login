package com.ejercicio_bci.login.service;

import com.ejercicio_bci.login.repository.UserRepository;
import com.ejercicio_bci.login.service.impl.MyUserDetailsServiceImpl;
import com.ejercicio_bci.login.util.UserMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MyUserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MyUserDetailsServiceImpl service;

    @Test
    @DisplayName("Cargar por nombre de usuario - OK")
    public void loadByUserNameTestOK() {
        when(userRepository.findByName(anyString())).thenReturn(UserMock.buildUserEntity());
        UserDetails userDetails = service.loadUserByUsername("username");

        assertNotNull(userDetails);
        assertEquals("username", userDetails.getUsername());
    }

    @Test
    @DisplayName("Cargar por nombre de usuario - UserNotFoundException")
    public void loadByUserNameTestException() {
        when(userRepository.findByName(anyString())).thenReturn(null);
        Throwable exception = assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("username"));

        assertNotNull(exception);
        assertEquals("No se encontro el usuario", exception.getMessage());
    }
}
