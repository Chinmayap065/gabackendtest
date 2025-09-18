package com.mtd.GamingArcade.controller;

import com.mtd.GamingArcade.dto.AuthRequest;
import com.mtd.GamingArcade.dto.AuthResponse;
import com.mtd.GamingArcade.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        String token = authService.authenticateAdminUser(authRequest.username(), authRequest.password());
        return ResponseEntity.ok(new AuthResponse(token));
    }
    
}