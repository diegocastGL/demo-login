package com.ejercicio_bci.login.repository;

import com.ejercicio_bci.login.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {
    List<PhoneEntity> findAllByUserId(Long id);
}
