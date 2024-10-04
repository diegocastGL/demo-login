package com.ejercicio_bci.login.repository;

import com.ejercicio_bci.login.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByName(String username);

    UserEntity findByEmail(String email);
}
