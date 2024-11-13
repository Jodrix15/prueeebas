package com.UserAuth.userAuth.dtos;

import com.UserAuth.userAuth.enums.UserRole;

public record SignUpDto(
    String login,
    String password,
    UserRole role){
}
