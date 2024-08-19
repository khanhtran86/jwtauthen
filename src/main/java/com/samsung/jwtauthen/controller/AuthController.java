package com.samsung.jwtauthen.controller;

import com.samsung.jwtauthen.configuration.CustomUserDetails;
import com.samsung.jwtauthen.configuration.jwt.JwtTokenProvider;
import com.samsung.jwtauthen.repositories.models.LoginRequest;
import com.samsung.jwtauthen.repositories.models.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    HttpSecurity http;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/api/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest)
    {
        //Xac thuc user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }
}
