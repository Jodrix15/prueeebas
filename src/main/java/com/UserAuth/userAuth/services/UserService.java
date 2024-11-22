package com.UserAuth.userAuth.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UserAuth.userAuth.models.UserEntity;
import com.UserAuth.userAuth.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
   
    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

  
    /*public ResponseEntity<UserEntity> getUserById(@PathVariable Long id){
        Optional<UserEntity> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user){
        UserEntity saveUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
        
    }

    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity updateUser){
        if(!userRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        UserEntity saveUser = userRepository.save(updateUser);
        return ResponseEntity.ok(saveUser);*/
    }

