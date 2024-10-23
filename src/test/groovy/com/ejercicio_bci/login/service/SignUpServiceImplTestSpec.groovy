package com.ejercicio_bci.login.service

import com.ejercicio_bci.login.dto.SignUpRequest
import com.ejercicio_bci.login.dto.SignUpResponse
import com.ejercicio_bci.login.entity.PhoneEntity
import com.ejercicio_bci.login.exception.InvalidEmailException
import com.ejercicio_bci.login.exception.InvalidPasswordException
import com.ejercicio_bci.login.mapper.PhoneMapper
import com.ejercicio_bci.login.mapper.UserMapper
import com.ejercicio_bci.login.repository.PhoneRepository
import com.ejercicio_bci.login.repository.UserRepository
import com.ejercicio_bci.login.service.impl.JwtServiceimpl
import com.ejercicio_bci.login.service.impl.SignUpServiceImpl
import com.ejercicio_bci.login.util.SignUpMock
import com.ejercicio_bci.login.util.UserMock
import com.ejercicio_bci.login.validator.SignUpValidator
import spock.lang.Specification

class SignUpServiceImplTestSpec extends Specification {

    UserRepository userRepository
    PhoneRepository phoneRepository
    SignUpValidator validator
    JwtServiceimpl jwtServiceimpl
    UserMapper userMapper
    PhoneMapper phoneMapper
    SignUpServiceImpl service

    def setup() {
        userRepository = Mock(UserRepository)
        phoneRepository = Mock(PhoneRepository)
        validator = Mock(SignUpValidator)
        jwtServiceimpl = Mock(JwtServiceimpl)
        userMapper = Spy(UserMapper)
        phoneMapper = Spy(PhoneMapper)
        service = new SignUpServiceImpl(userRepository,phoneRepository,validator,jwtServiceimpl,userMapper,phoneMapper)
    }

    def "SignUp Ok"() {
        given: "Un request de Sign Up"
        SignUpRequest request = SignUpMock.buildSignUpRequest()

        when: "Se hace el proceso de sign up"
        validator.isValidEmail(_) >> true
        validator.isValidPassword(_) >> true
        userRepository.save(_) >> UserMock.buildUserEntity()
        phoneRepository.saveAll(_) >> new ArrayList<PhoneEntity>()
        jwtServiceimpl.generateToken(_,_) >> "TOKEN"

        then: "Se hace el sign up"
        SignUpResponse response = service.signUp(request)
        response != null
        response.getToken() == "TOKEN"
    }

    def "SignUp - email invalido"() {
        given: "Un email invalido"
        SignUpRequest request = SignUpMock.buildSignUpRequest()
        request.setEmail("INVALIDO")

        when: "Se hace el proceso de sign up"
        validator.isValidEmail(_) >> false
        service.signUp(request)

        then: "Se devuelve exception"
        thrown(InvalidEmailException)
    }

    def "SignUp - password invalido"() {
        given: "Un email invalido"
        SignUpRequest request = SignUpMock.buildSignUpRequest()
        request.setEmail("INVALIDO")

        when: "Se hace el proceso de sign up"
        validator.isValidEmail(_) >> true
        validator.isValidPassword(_) >> false
        service.signUp(request)

        then: "Se devuelve exception"
        thrown(InvalidPasswordException)
    }

    def "SignUp - telefonos nulos"() {
        given: "Un request de Sign Up"
        SignUpRequest request = SignUpMock.buildSignUpRequest()
        request.setPhones(null)

        when: "Se hace el proceso de sign up"
        validator.isValidEmail(_) >> true
        validator.isValidPassword(_) >> true
        userRepository.save(_) >> UserMock.buildUserEntity()
        jwtServiceimpl.generateToken(_,_) >> "TOKEN"

        then: "Se hace el sign up"
        SignUpResponse response = service.signUp(request)
        response != null
        response.getToken() == "TOKEN"
    }
}
