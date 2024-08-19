package com.samsung.jwtauthen.repositories.models;

import lombok.Data;

@Data
public class LoginRequest {
    public String username;
    public String password;
}
