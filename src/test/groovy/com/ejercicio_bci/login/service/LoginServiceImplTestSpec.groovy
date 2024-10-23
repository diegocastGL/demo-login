package com.ejercicio_bci.login.service

import com.ejercicio_bci.login.dto.LoginResponse
import com.ejercicio_bci.login.entity.PhoneEntity
import com.ejercicio_bci.login.exception.UserNotFoundException
import com.ejercicio_bci.login.mapper.PhoneMapper
import com.ejercicio_bci.login.mapper.UserMapper
import com.ejercicio_bci.login.repository.PhoneRepository
import com.ejercicio_bci.login.repository.UserRepository
import com.ejercicio_bci.login.service.impl.JwtServiceimpl
import com.ejercicio_bci.login.service.impl.LoginServiceImpl
import com.ejercicio_bci.login.util.PhoneMock
import com.ejercicio_bci.login.util.UserMock
import spock.lang.Specification

class LoginServiceImplTestSpec extends Specification {

    JwtServiceimpl jwtServiceimpl
    UserRepository userRepository
    PhoneRepository phoneRepository
    PhoneMapper phoneMapper
    UserMapper userMapper
    LoginServiceImpl service

    def setup() {
        jwtServiceimpl = Mock(JwtServiceimpl)
        userRepository = Mock(UserRepository)
        phoneRepository = Mock(PhoneRepository)
        phoneMapper = Spy(PhoneMapper)
        userMapper = Spy(UserMapper)
        service = new LoginServiceImpl(jwtServiceimpl,userRepository,phoneRepository,phoneMapper,userMapper)
    }

    def "login ok"() {
        given: "Un header de authorization"
        String authorization = "Authorization"

        when: "Se hace el proceso de login"
        jwtServiceimpl.extractTokenFromAuthorization(authorization) >> "TOKEN"
        jwtServiceimpl.extractEmail(_) >> "email@email.com"
        userRepository.findByEmail(_) >> UserMock.buildUserEntity()
        phoneRepository.findAllByUserId(_) >> PhoneMock.buildPhonesEntities()
        jwtServiceimpl.generateToken(_, _) >> "NEW TOKEN"
        userRepository.save(_) >> UserMock.buildUserEntity()

        then: "Se devuelve los datos del usuario y se genera nuevo token"
        LoginResponse response = service.login(authorization)
        response != null
        response.getToken().equalsIgnoreCase("NEW TOKEN")
        response.getName() != null
        !response.getPhones().isEmpty()
    }

    def "login - UserNotFoundException"() {
        given: "Un header de authorization"
        String authorization = "Authorization"

        when: "Se hace el proceso de login"
        jwtServiceimpl.extractTokenFromAuthorization(authorization) >> "TOKEN"
        jwtServiceimpl.extractEmail(_) >> "email@email.com"
        userRepository.findByEmail(_) >> null
        service.login(authorization)

        then: "Se devuelve una excepcion de usuario no encontrado"
        thrown(UserNotFoundException)
    }

    def "login teléfonos null"() {
        given: "Un header de authorization"
        String authorization = "Authorization"

        when: "Se hace el proceso de login"
        jwtServiceimpl.extractTokenFromAuthorization(authorization) >> "TOKEN"
        jwtServiceimpl.extractEmail(_) >> "email@email.com"
        userRepository.findByEmail(_) >> UserMock.buildUserEntity()
        phoneRepository.findAllByUserId(_) >> null
        jwtServiceimpl.generateToken(_, _) >> "NEW TOKEN"
        userRepository.save(_) >> UserMock.buildUserEntity()

        then: "Se devuelve los datos del usuario y se genera nuevo token"
        LoginResponse response = service.login(authorization)
        response != null
        response.getToken().equalsIgnoreCase("NEW TOKEN")
        response.getPhones().isEmpty()
    }

    def "login teléfonos vacios"() {
        given: "Un header de authorization"
        String authorization = "Authorization"

        when: "Se hace el proceso de login"
        jwtServiceimpl.extractTokenFromAuthorization(authorization) >> "TOKEN"
        jwtServiceimpl.extractEmail(_) >> "email@email.com"
        userRepository.findByEmail(_) >> UserMock.buildUserEntity()
        phoneRepository.findAllByUserId(_) >> new ArrayList<PhoneEntity>()
        jwtServiceimpl.generateToken(_, _) >> "NEW TOKEN"
        userRepository.save(_) >> UserMock.buildUserEntity()

        then: "Se devuelve los datos del usuario y se genera nuevo token"
        LoginResponse response = service.login(authorization)
        response != null
        response.getToken().equalsIgnoreCase("NEW TOKEN")
        response.getPhones().isEmpty()
    }


}
