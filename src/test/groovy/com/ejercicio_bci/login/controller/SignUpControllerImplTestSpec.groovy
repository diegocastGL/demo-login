package com.ejercicio_bci.login.controller

import com.ejercicio_bci.login.dto.SignUpRequest
import com.ejercicio_bci.login.dto.SignUpResponse
import com.ejercicio_bci.login.service.impl.SignUpServiceImpl
import com.ejercicio_bci.login.util.SignUpMock
import spock.lang.Specification

class SignUpControllerImplTestSpec extends Specification {

    SignUpServiceImpl service
    SignUpController controller

    def setup() {
        service = Mock(SignUpServiceImpl)
        controller = new SignUpController(service)
    }

    def "SignUp test"() {
        given: "Un request de Sign Up"
        SignUpRequest request = SignUpMock.buildSignUpRequest()
        SignUpResponse expected = SignUpMock.buildsignUpResponse()

        when: "se llama al service"
        service.signUp(request) >> expected

        then: "La respuesta es válida"
        SignUpResponse response = controller.signUp(request)
        response == expected
    }

}
