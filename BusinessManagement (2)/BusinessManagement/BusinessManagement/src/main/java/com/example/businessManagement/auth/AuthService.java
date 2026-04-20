package com.example.businessManagement.auth;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtTokenProvider provider;

    public AuthService(JwtTokenProvider provider) {
        this.provider = provider;
    }
}
