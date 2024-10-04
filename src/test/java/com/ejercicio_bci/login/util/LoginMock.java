package com.ejercicio_bci.login.util;

import com.ejercicio_bci.login.dto.LoginResponse;
import com.ejercicio_bci.login.dto.PhoneDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LoginMock {

    public static LoginResponse buildLoginResponse() {
        return LoginResponse.builder()
                .name("NAME")
                .password("PASSWORD")
                .email("EMAIL@EMAIL.COM")
                .isActive(true)
                .created(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .id(1L)
                .token("TOKEN")
                .phones(buildPhonesDTO())
                .build();
    }

    public static List<PhoneDTO> buildPhonesDTO() {
        List<PhoneDTO> phones = new ArrayList<>();
        phones.add(PhoneDTO.builder().citycode(1L).countrycode("countrycode").number(1113344L).build());
        return phones;
    }
}
