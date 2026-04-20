package com.example.businessManagement.user;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
    public class UserResponse {
        private Long id;
        private String username;
        private String role;
    }


