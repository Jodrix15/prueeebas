package com.UserAuth.userAuth.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UserAuth.userAuth.dtos.SignUpDto;
import com.UserAuth.userAuth.dtos.UpdateUserDto;
import com.UserAuth.userAuth.models.UserEntity;
import com.UserAuth.userAuth.repositories.UserRepository;
import com.UserAuth.userAuth.services.UserService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    
    @CrossOrigin
    @GetMapping("/all")
    public List<UserEntity> getAllUsers(){
        return userService.getAllUsers();
    }

    @CrossOrigin
    @PostMapping("signup-user")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDto data){
        userService.createUser(data);
        return ResponseEntity.status(HttpStatus.CREATED).build(); 
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        if(userService.deleteUserById(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
   
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUserDto updateUser){

        UserEntity savedUser = userService.updateUser(id, updateUser);
        return ResponseEntity.ok(savedUser); // 200 OK con el usuario actualizado 
    }

}
