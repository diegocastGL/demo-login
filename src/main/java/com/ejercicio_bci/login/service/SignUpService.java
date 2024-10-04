package com.ejercicio_bci.login.service;

import com.ejercicio_bci.login.dto.PhoneDTO;
import com.ejercicio_bci.login.dto.SignUpRequest;
import com.ejercicio_bci.login.dto.SignUpResponse;
import com.ejercicio_bci.login.entity.PhoneEntity;
import com.ejercicio_bci.login.entity.UserEntity;
import com.ejercicio_bci.login.exception.CustomException;
import com.ejercicio_bci.login.exception.InvalidEmailException;
import com.ejercicio_bci.login.exception.InvalidPasswordException;
import com.ejercicio_bci.login.exception.UserNotFoundException;
import com.ejercicio_bci.login.mapper.PhoneMapper;
import com.ejercicio_bci.login.mapper.UserMapper;
import com.ejercicio_bci.login.repository.PhoneRepository;
import com.ejercicio_bci.login.repository.UserRepository;
import com.ejercicio_bci.login.validator.SignUpValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private SignUpValidator validator;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private PhoneMapper phoneMapper;

    private static final String EX_MSG_UNIQUE_CONSTRAINT = "testdb.CONSTRAINT_INDEX_4 ON testdb.USERS(EMAIL)";

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public SignUpResponse signUp(SignUpRequest request) throws CustomException {

        //Validaciones de email y password
        if (!validator.isValidEmail(request.getEmail())) {
            log.error("El e-mail es inválido!");
            throw new InvalidEmailException(400,"El e-mail es inválido.");
        }

        if (!validator.isValidPassword(request.getPassword())) {
            log.error("La contraseña no cumple los requisitos");
            throw new InvalidPasswordException(400,"La contraseña no cumple los requisitos.");
        }

        //encoder de password
        request.setPassword(encoder.encode(request.getPassword()));

        UserEntity userEntity;

        //persistencia de usuario y obtención de user id
        try {
            userEntity = userRepository.save(mapper.signUpResponseToEntity(request));
        } catch (Exception e) {
            if (e.getMessage().contains(EX_MSG_UNIQUE_CONSTRAINT)) {
                log.error("El e-mail ya esta registrado. Intente otro");
                throw new InvalidEmailException(400,"El e-mail ya está registrado. Intente otro");
            }
            throw e;
        }

        List<PhoneEntity> phones = new ArrayList<>();
        if (request.getPhones() != null) {
            phones = request.getPhones().stream().map(phoneMapper::dtoToEntity).collect(Collectors.toList());
            phones.forEach(phone -> phone.setUserId(userEntity.getId()));
        }

        if (!phones.isEmpty()) {
            try {
                phoneRepository.saveAll(phones);
            } catch (Exception e) {
                log.error("Error en la persistencia de los teléfonos del usuario con id: {}", userEntity.getId());
            }
        }

        String token = jwtService.generateToken(userEntity.getName(),userEntity.getEmail());
        SignUpResponse response = mapper.entityToSignUpResponse(userEntity);
        response.setToken(token);

        return response;
    }
}
