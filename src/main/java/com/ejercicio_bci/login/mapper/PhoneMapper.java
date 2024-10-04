package com.ejercicio_bci.login.mapper;

import com.ejercicio_bci.login.dto.PhoneDTO;
import com.ejercicio_bci.login.entity.PhoneEntity;
import org.springframework.stereotype.Component;

@Component
public class PhoneMapper {

    public PhoneEntity dtoToEntity(PhoneDTO dto) {
        return PhoneEntity.builder()
                .number(dto.getNumber())
                .citycode(dto.getCitycode())
                .countryCode(dto.getCountrycode()).build();
    }

    public PhoneDTO entityToDTO(PhoneEntity phoneEntity) {
        return PhoneDTO.builder()
                .number(phoneEntity.getNumber())
                .countrycode(phoneEntity.getCountryCode())
                .citycode(phoneEntity.getCitycode())
                .build();
    }
}
