package com.ejercicio_bci.login

import com.ejercicio_bci.login.controller.LoginController
import com.ejercicio_bci.login.controller.SignUpController
import com.ejercicio_bci.login.repository.PhoneRepository
import com.ejercicio_bci.login.repository.UserRepository
import com.ejercicio_bci.login.service.impl.JwtServiceimpl
import com.ejercicio_bci.login.service.impl.LoginServiceImpl
import com.ejercicio_bci.login.service.impl.MyUserDetailsServiceImpl
import com.ejercicio_bci.login.service.impl.SignUpServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification

@SpringBootTest
class LoadContextTestSpec extends Specification {

    @Autowired
    private ApplicationContext context;


    def "carga de contexto"() {
        when: "Se carga el contexto"
        context
        then: "beans tienen que ser creados"
        context.getBean(LoginController.class) != null
        context.getBean(SignUpController.class) != null
        context.getBean(SignUpController.class) != null
        context.getBean(JwtServiceimpl.class) != null
        context.getBean(LoginServiceImpl.class) != null
        context.getBean(MyUserDetailsServiceImpl.class) != null
        context.getBean(SignUpServiceImpl.class) != null
        context.getBean(PhoneRepository.class) != null
        context.getBean(UserRepository.class) != null
    }

}