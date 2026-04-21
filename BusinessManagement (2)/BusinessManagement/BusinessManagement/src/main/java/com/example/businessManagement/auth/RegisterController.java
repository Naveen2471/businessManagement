package com.example.businessManagement.auth;

import com.example.businessManagement.Common.ApiResponse;
import com.example.businessManagement.Model.User;
import com.example.businessManagement.user.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class RegisterController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ApiResponse<String> signup(@Valid @RequestBody SignupRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            return new ApiResponse<>(false, "Username already exists", null);
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole().name());


        userRepository.save(user);

        return new ApiResponse<>(true, "User registered successfully", "OK");
    }
}
