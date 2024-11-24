package com.UserAuth.userAuth.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.UserAuth.userAuth.dtos.SignUpDto;
import com.UserAuth.userAuth.dtos.UpdateUserDto;
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
        UserEntity newUser = new UserEntity(data.login(), data.name(), data.usrname(), data.lastName(), data.phoneNumber(), encryptedPassword, data.role());
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

    public UserEntity updateUser(Long id, UpdateUserDto updateUser){
        
        UserEntity user = getUserById(id);
        String encryptedPassword = new BCryptPasswordEncoder().encode(updateUser.password());
        user.setPassword(encryptedPassword);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
        // Verifica si el rol del usuario autenticado es SUPERADMIN
        boolean isSuperAdmin = authentication.getAuthorities().stream()
            .anyMatch(authority -> authority.getAuthority().equals("ROLE_SUPERADMIN"));

        // Solo actualiza el rol si el usuario es SUPERADMIN
        if (isSuperAdmin) {
            user.setRole(updateUser.role());
        }

        return userRepository.save(user);
        
    }
}

