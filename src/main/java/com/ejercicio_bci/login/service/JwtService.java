package com.ejercicio_bci.login.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    public String generateToken(String username);
    public String generateToken(String username, String email);
    public String extractEmail(String token);
    public String extractTokenFromAuthorization(String authorization);
    public String extractUserName(String token);
    public boolean validateToken(String token, UserDetails userDetails);
}
