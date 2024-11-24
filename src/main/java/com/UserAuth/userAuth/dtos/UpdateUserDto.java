package com.UserAuth.userAuth.dtos;

import com.UserAuth.userAuth.enums.UserRole;

public record UpdateUserDto(
    String password,
    UserRole role){

}
