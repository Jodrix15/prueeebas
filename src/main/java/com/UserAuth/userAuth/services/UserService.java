package com.UserAuth.userAuth.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.UserAuth.userAuth.dtos.SignUpDto;
import com.UserAuth.userAuth.exceptions.UserAlreadyExistsException;
import com.UserAuth.userAuth.models.UserEntity;
import com.UserAuth.userAuth.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
   
    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public UserDetails createUser(SignUpDto data){
        if (userRepository.findByLogin(data.login()) != null) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserEntity newUser = new UserEntity(data.login(), encryptedPassword, data.role());
        return userRepository.save(newUser);
    }

    public boolean deleteUserById(Long id) {
    
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id); 
            return true; 
        } else {
            return false; 
        }
    } 

    private UserEntity getUserById(Long id){

        return userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));
    }

    public UserEntity updateUser(Long id, UserEntity updateUser){
        
        getUserById(id);
        userRepository.save(updateUser);
        
        return updateUser;
        
      
               
    }
}

