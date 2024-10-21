package com.ejercicio_bci.login.exception

import com.ejercicio_bci.login.dto.ErrorResponseDTO
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class CustomErrorHandlerTestSpec extends Specification {

    CustomErrorHandler customErrorHandler

    def setup() {
        customErrorHandler = new CustomErrorHandler()
    }

    def "manejar excepcion InvalidPasswordException"() {
        given: "Una excepcion"
        InvalidPasswordException exception = new InvalidPasswordException(400, "Password nulo o vacio");

        when: "Se maneja la excepcion"
        ResponseEntity<ErrorResponseDTO> resultado = customErrorHandler.handleException(exception)

        then: "Se espera codigo y mensaje correspondiente"
        resultado.getBody().getError().get(0).getCodigo().equals(400)
        resultado.getBody().getError().get(0).getDetail().equals("Password nulo o vacio")
    }

    def "manejar excepcion InvalidEmailException"() {
        given: "Una excepcion"
        InvalidEmailException exception = new InvalidEmailException(400, "Email nulo o vacio");

        when: "Se maneja la excepcion"
        ResponseEntity<ErrorResponseDTO> resultado = customErrorHandler.handleException(exception)

        then: "Se espera codigo y mensaje correspondiente"
        resultado.getBody().getError().get(0).getCodigo().equals(400)
        resultado.getBody().getError().get(0).getDetail().equals("Email nulo o vacio")
    }


    def "manejar excepcion general"() {
        given: "Una excepcion"
        Exception exception = new Exception()

        when: "Se maneja la excepcion"
        ResponseEntity<ErrorResponseDTO> resultado = customErrorHandler.handleException(exception)

        then: "Se espera codigo y mensaje correspondiente"
        resultado.getBody().getError().get(0).getCodigo().equals(500)
        resultado.getBody().getError().get(0).getDetail().equals("Error Interno")
    }
}
