package com.ejercicio_bci.login.service;

import com.ejercicio_bci.login.dto.PhoneDTO;
import com.ejercicio_bci.login.dto.LoginResponse;
import com.ejercicio_bci.login.entity.PhoneEntity;
import com.ejercicio_bci.login.entity.UserEntity;
import com.ejercicio_bci.login.exception.UserNotFoundException;
import com.ejercicio_bci.login.mapper.PhoneMapper;
import com.ejercicio_bci.login.mapper.UserMapper;
import com.ejercicio_bci.login.repository.PhoneRepository;
import com.ejercicio_bci.login.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LoginService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private PhoneMapper phoneMapper;

    @Autowired
    private UserMapper userMapper;

    public LoginResponse login(String authorization) throws UserNotFoundException {
        String token = jwtService.extractTokenFromAuthorization(authorization);
        String email = jwtService.extractEmail(token);

        UserEntity entity = userRepository.findByEmail(email);

        if (entity == null) {
            log.error("Usuario no encontrado");
            throw new UserNotFoundException(404,"Usuario no encontrado");
        }

        List<PhoneEntity> phonesEntities = phoneRepository.findAllByUserId(entity.getId());
        List<PhoneDTO> phones = new ArrayList<>();
        if (phonesEntities != null && !phonesEntities.isEmpty()) {
           phones = phonesEntities.stream().map(phoneMapper::entityToDTO).collect(Collectors.toList());
        }

        String newToken = jwtService.generateToken(entity.getName(),entity.getEmail());
        entity.setLastLogin(LocalDateTime.now());
        UserEntity entityUpdated = userRepository.save(entity);
        LoginResponse response = userMapper.entityToLoginResponse(entityUpdated);
        response.setToken(newToken);
        response.setPhones(phones);

        return response;
    }
}
