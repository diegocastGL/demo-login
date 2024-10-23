package com.ejercicio_bci.login.service

import com.ejercicio_bci.login.service.impl.JwtServiceimpl
import com.ejercicio_bci.login.util.JwtServiceMock
import org.springframework.security.core.userdetails.UserDetails
import spock.lang.Specification

class JwtServiceImplTestSpec extends Specification {

    JwtServiceimpl service

    def setup() {
        service = new JwtServiceimpl()
    }

    def "Generar token"() {
        given: "Un usuario"
        String usuario = "usuario"

        expect: "se genera token"
        String token = service.generateToken(usuario)
        token != null
    }

    def "Generar token con user y email"() {
        given: "Un usuario y un email"
        String usuario = "usuario"
        String email = "email@email.com"

        expect: "Se genera token"
        String token = service.generateToken(usuario, email)
        token != null
    }

    def "Extraer email de token"() {
        given: "Un usuario y un email"
        String usuario = "usuario"
        String email = "email@email.com"

        when: "Se genera token"
        String token = service.generateToken(usuario, email)
        token != null

        then: "Se extrae el email del token"
        String tokenEmail = service.extractEmail(token)
        tokenEmail != null
        tokenEmail == email
    }

    def "Extraer user de token"() {
        given: "Un usuario "
        String usuario = "usuario"

        when: "Se genera token"
        String token = service.generateToken(usuario)
        token != null

        then: "Se extrae el user del token"
        String nombreUsuario = service.extractUserName(token)
        nombreUsuario != null
        nombreUsuario == usuario
    }

    def "Extraer token de authorization"() {
        given: "Un usuario"
        String usuario = "usuario"

        when: "Se genera authorization"
        String token = service.generateToken(usuario)
        token != null
        String auth = "Bearer " + token
        auth != null

        then: "Se extrae token de authorization"
            String resultado = service.extractTokenFromAuthorization(auth)
            resultado != null
            resultado == token
    }

    def "Extraer token de authorization sin bearer"() {
        given: "Un usuario"
        String usuario = "usuario"

        when: "Se genera authorization"
        String token = service.generateToken(usuario)
        token != null

        then: "Se extrae token de authorization"
        String resultado = service.extractTokenFromAuthorization(token)
        resultado != null
        resultado == token
    }

    def "Validar token"() {
        given: "Un usuario"
        String nombre = "username"

        when: "Se genera token"
        String token = service.generateToken(nombre)
        token != null
        UserDetails usuario = JwtServiceMock.buildUserDetails()

        then: "Se valida token"
        service.validateToken(token, usuario)
    }

    def "Validar token invalido"() {
        given: "Un usuario"
        String nombre = "invalido"

        when: "Se genera token"
        String token = service.generateToken(nombre)
        token != null
        UserDetails usuario = JwtServiceMock.buildUserDetails()

        then: "Se valida token"
        !service.validateToken(token, usuario)
    }

}
