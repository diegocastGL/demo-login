package com.ejercicio_bci.login.validator

import com.ejercicio_bci.login.exception.InvalidEmailException
import com.ejercicio_bci.login.exception.InvalidPasswordException
import spock.lang.Specification

class SignUpValidatorTestSpec extends Specification {

    SignUpValidator validator

    def setup() {
        validator = Spy(SignUpValidator)
    }

    def "correo valido"() {
        given: "un correo"
        String correo = "aaaaaaa@undominio.algo"

        expect: "Que sea valido"
        validator.isValidEmail(correo)
    }

    def "correo vacio"() {
        given: "un correo vacio"
        String correo = ""

        when: "validacion de correo"
        validator.isValidEmail(correo)

        then: "Se arroja excepcion"
        thrown(InvalidEmailException)
    }

    def "correo nulo"() {
        given: "un correo nulo"
        String correo = null

        when: "validacion de correo"
        validator.isValidEmail(correo)

        then: "Se arroja excepcion"
        thrown(InvalidEmailException)
    }

    def "password valido"() {
        given: "un password valido"
        String password = "a2asfGfdfdf4"

        expect: "Que sea valido"
        validator.isValidPassword(password)
    }

    def "password vacio"() {
        given: "un password vacio"
        String password = ""

        when: "validacion de correo"
        validator.isValidPassword(password)

        then: "Se arroja excepcion"
        thrown(InvalidPasswordException)
    }

    def "password nulo"() {
        given: "un password nulo"
        String password = null

        when: "validacion de correo"
        validator.isValidPassword(password)

        then: "Se arroja excepcion"
        thrown(InvalidPasswordException)
    }
}
