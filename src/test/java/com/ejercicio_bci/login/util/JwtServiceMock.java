package com.ejercicio_bci.login.util;

import com.ejercicio_bci.login.dto.MyUserDetails;
import com.ejercicio_bci.login.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;


public class JwtServiceMock {

    public static UserDetails buildUserDetails() {
        UserEntity user = UserMock.buildUserEntity();
        UserDetails userDetails = new MyUserDetails(user);
        return userDetails;
    }
}
