package com.example.monnet.users_demo.api;

import com.example.monnet.users_demo.api.dto.LoginRequest;
import com.example.monnet.users_demo.api.dto.LoginResponse;
import com.example.monnet.users_demo.security.AppSecurityProperties;
import com.example.monnet.users_demo.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppSecurityProperties properties;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        AppSecurityProperties.User cfg = properties.user();
        if (!cfg.username().equals(request.username()) || !cfg.password().equals(request.password())) {
            return ResponseEntity.status(401).build();
        }
        String token = jwtService.generateToken(request.username());
        return ResponseEntity.ok(new LoginResponse(token, jwtService.getExpirationMinutes()));
    }
}
