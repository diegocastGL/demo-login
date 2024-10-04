package com.ejercicio_bci.login.util;

import com.ejercicio_bci.login.dto.PhoneDTO;
import com.ejercicio_bci.login.entity.PhoneEntity;

import java.util.ArrayList;
import java.util.List;

public class PhoneMock {

    public static List<PhoneDTO> buildPhonesDTO() {
        List<PhoneDTO> phones = new ArrayList<>();
        phones.add(buildPhone(1L));
        phones.add(buildPhone(2L));
        return phones;
    }

    public static PhoneDTO buildPhone(Long id) {
        return PhoneDTO.builder()
                .citycode(id)
                .number(1112222L)
                .countrycode("countryCode")
                .build();
    }

    public static List<PhoneEntity> buildPhonesEntities() {
        List<PhoneEntity> phonesEntities = new ArrayList<>();
        phonesEntities.add(buildPhoneEntity(1L));
        phonesEntities.add(buildPhoneEntity(2L));
        return phonesEntities;
    }

    private static PhoneEntity buildPhoneEntity(long id) {
        return PhoneEntity.builder()
                .id(id)
                .number(1112233L)
                .countryCode("countryCode")
                .citycode(id)
                .userId(id)
                .build();
    }
}
