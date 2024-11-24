package com.UserAuth.userAuth.dtos;

import com.UserAuth.userAuth.enums.UserRole;

public record SignUpDto(
    String login,
    String name,  
    String usrname, 
    String lastName, 
    String phoneNumber,
    String password,
    UserRole role){
}
