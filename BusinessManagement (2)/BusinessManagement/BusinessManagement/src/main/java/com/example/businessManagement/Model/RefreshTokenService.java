package com.example.businessManagement.Model;

import com.example.businessManagement.Model.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh-token-expiration:604800000}") // 7 days default
    private Long refreshTokenDurationMs;

    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUsername(username);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setCreatedAt(Instant.now());
        refreshToken.setRevoked(false);

        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token expired. Please login again.");
        }
        return token;
    }

    @Transactional
    public void deleteByUsername(String username) {
        refreshTokenRepository.deleteByUsername(username);
    }

    @Transactional
    public void revokeToken(String token) {
        refreshTokenRepository.findByToken(token).ifPresent(refreshToken -> {
            refreshToken.setRevoked(true);
            refreshTokenRepository.save(refreshToken);
        });
    }

    public RefreshToken create(User user) {
        return createRefreshToken(user.getUsername());
    }
}