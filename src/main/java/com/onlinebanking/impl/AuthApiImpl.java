package com.onlinebanking.impl;

import com.onlinebanking.api.AuthApi;
import com.onlinebanking.model.LoginRequest;
import com.onlinebanking.model.LoginResponse;
import com.onlinebanking.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthApiImpl implements AuthApi {

    private static final Logger logger = LoggerFactory.getLogger(AuthApiImpl.class);
    private final JwtTokenProvider jwtTokenProvider;

    public AuthApiImpl(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public ResponseEntity<LoginResponse> authLoginPost(LoginRequest loginRequest) {
        try {
            if ("user".equals(loginRequest.getEmail()) && "password".equals(loginRequest.getPassword())) {
                String token = jwtTokenProvider.generateToken(loginRequest.getEmail());

                LoginResponse response = new LoginResponse();
                response.setToken(token);

                logger.info("User {} logged in successfully", loginRequest.getEmail());
                return ResponseEntity.ok(response);
            }

            logger.warn("Unauthorized login attempt for email: {}", loginRequest.getEmail());
            return ResponseEntity.status(401).body(new LoginResponse());
        } catch (Exception e) {
            logger.error("Error during login process", e);
            return ResponseEntity.status(500).body(new LoginResponse());
        }
    }

    @Override
    public ResponseEntity<Void> authLogoutPost() {
        logger.info("User logged out successfully");
        return ResponseEntity.ok().build();
    }
}
