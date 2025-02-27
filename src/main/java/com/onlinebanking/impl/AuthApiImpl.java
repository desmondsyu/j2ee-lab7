package com.onlinebanking.impl;
import com.onlinebanking.api.AuthApi;
import com.onlinebanking.model.LoginRequest;
import com.onlinebanking.model.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthApiImpl implements AuthApi {

    static {

    }

    @Override
    public ResponseEntity<LoginResponse> authLoginPost(LoginRequest loginRequest) {
        if ("user".equals(loginRequest.getEmail()) && "password".equals(loginRequest.getPassword())) {
            LoginResponse response = new LoginResponse();
            response.setToken("mock-jwt-token");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).build();
    }

    @Override
    public ResponseEntity<Void> authLogoutPost() {
        return ResponseEntity.ok().build();
    }
}
