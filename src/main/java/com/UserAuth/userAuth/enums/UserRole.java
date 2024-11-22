package com.UserAuth.userAuth.enums;

public enum UserRole {
    SUPERADMIN("superadmin"),
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getValue(){
        return role;
    }

}
