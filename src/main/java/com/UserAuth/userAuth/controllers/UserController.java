package com.UserAuth.userAuth.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UserAuth.userAuth.models.UserEntity;
import com.UserAuth.userAuth.repositories.UserRepository;
import com.UserAuth.userAuth.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @CrossOrigin
    @GetMapping
    public List<UserEntity> getAllUsers(){
        return userService.getAllUsers();
    }

    /*@CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id){
        Optional<UserEntity> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user){
        UserEntity saveUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
        
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity updateUser){
        if(!userRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        UserEntity saveUser = userRepository.save(updateUser);
        return ResponseEntity.ok(saveUser);
    }*/

    //mostrarDatos{username}

    //usuario solo modificar su cuenta, y eliminarla (ya puede crearseala)

    //superadmin todo. La lista de usuarios debe mostrar los datos de los mismos
    //admin todo excepto dar de alta  a usuarios

}
