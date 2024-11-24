package com.UserAuth.userAuth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.UserAuth.userAuth.dtos.SignUpDto;
import com.UserAuth.userAuth.exceptions.UserAlreadyExistsException;
import com.UserAuth.userAuth.models.UserEntity;
import com.UserAuth.userAuth.repositories.UserRepository;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        var user = userRepository.findByLogin(username);
        return user;
    }

    public UserDetails signUp(SignUpDto data) throws UserAlreadyExistsException{
        if (userRepository.findByLogin(data.login()) != null) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserEntity newUser = new UserEntity(data.login(), data.name(), data.usrname(), data.lastName(), data.phoneNumber(), encryptedPassword, data.role());
        return userRepository.save(newUser);
    }

}
