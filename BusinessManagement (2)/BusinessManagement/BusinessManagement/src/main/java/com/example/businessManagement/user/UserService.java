package com.example.businessManagement.user;

import com.example.businessManagement.Model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
    @RequiredArgsConstructor
    public class UserService {

        private final UserRepository repository;
        private final PasswordEncoder encoder;

        @Transactional
        public User create(User user) {
            user.setPassword(encoder.encode(user.getPassword()));
            return repository.save(user);
        }
    }


