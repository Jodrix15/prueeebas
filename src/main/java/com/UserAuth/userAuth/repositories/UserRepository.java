package com.UserAuth.userAuth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.UserAuth.userAuth.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserDetails findByLogin(String login);

}
