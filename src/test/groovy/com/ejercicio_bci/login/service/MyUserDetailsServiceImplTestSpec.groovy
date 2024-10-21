package com.ejercicio_bci.login.service

import com.ejercicio_bci.login.exception.UserNotFoundException
import com.ejercicio_bci.login.repository.UserRepository
import com.ejercicio_bci.login.service.impl.MyUserDetailsServiceImpl
import com.ejercicio_bci.login.util.UserMock
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import spock.lang.Specification

class MyUserDetailsServiceImplTestSpec extends Specification {

    UserRepository userRepository
    MyUserDetailsServiceImpl service

    def setup() {
        userRepository = Mock(UserRepository)
        service = new MyUserDetailsServiceImpl(userRepository)
    }

    def "Cargar por nombre de usuario"() {
        given: "Un usuario"
        String usuario = "username"

        when: "Se carga el usuario"
        userRepository.findByName(_) >> UserMock.buildUserEntity()

        then: "Se carga el usuario correctamente"
        UserDetails userDetails = service.loadUserByUsername(usuario)
        userDetails != null
        userDetails.getUsername().equalsIgnoreCase("username")
    }

    def "Cargar por nombre de usuario - UserNotFoundException"() {
        given: "Un usuario"
        String usuario = "username"

        when: "No se carga el usuario"
        userRepository.findByName(_) >> null
        service.loadUserByUsername(usuario)

        then: "Se arrroja una exception"
        thrown(UsernameNotFoundException)
    }
}
