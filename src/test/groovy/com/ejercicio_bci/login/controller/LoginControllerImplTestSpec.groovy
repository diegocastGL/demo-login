package com.ejercicio_bci.login.controller

import com.ejercicio_bci.login.dto.LoginResponse
import com.ejercicio_bci.login.service.impl.LoginServiceImpl
import com.ejercicio_bci.login.util.LoginMock
import spock.lang.Specification

class LoginControllerImplTestSpec extends Specification {

    LoginServiceImpl loginService
    LoginController loginController

    def setup() {
        loginService = Mock(LoginServiceImpl)
        loginController = new LoginController(loginService)
    }

    def "login test ok"() {
        given: "Un token de inicio de sesión"
        String token = "TOKEN"
        LoginResponse expectedResponse = LoginMock.buildLoginResponse()

        when: "Se llama al service"
        loginService.login(token) >> expectedResponse

        then: "La respuesta es válida"
        LoginResponse response = loginController.login(token)
        response == expectedResponse
    }
}
