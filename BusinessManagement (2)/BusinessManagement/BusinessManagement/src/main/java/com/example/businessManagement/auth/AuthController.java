package com.example.businessManagement.auth;

import com.example.businessManagement.Common.ApiResponse;
import com.example.businessManagement.Model.RefreshToken;
import com.example.businessManagement.Model.RefreshTokenService;
import com.example.businessManagement.Model.User;
import com.example.businessManagement.user.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenProvider jwtProvider;
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest req) {

        // 1️⃣ Fetch user from DB
        User user = userRepo.findByUsername(req.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        // 2️⃣ Match BCrypt password
        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        // 3️⃣ Generate JWT token
        String accessToken = jwtProvider.generateToken(
                user.getUsername(),
                String.valueOf(user.getRole())
        );

        // 4️⃣ Create refresh token (optional)
        RefreshToken refreshToken = refreshTokenService.create(user);

        // 5️⃣ Send response
        LoginResponse response = new LoginResponse(accessToken);

        return new ApiResponse<>(
                true,
                "Login successful",
                response
        );
    }
}
