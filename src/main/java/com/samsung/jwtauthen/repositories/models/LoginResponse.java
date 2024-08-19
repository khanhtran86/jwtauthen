package com.samsung.jwtauthen.repositories.models;

import lombok.Data;
import lombok.extern.java.Log;

@Data
public class LoginResponse {
    public String accessToken;
    private String tokenType = "Bearer";

    public LoginResponse(String accessToken)
    {
        this.accessToken = accessToken;
    }
}
