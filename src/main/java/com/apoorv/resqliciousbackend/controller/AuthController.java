package com.apoorv.resqliciousbackend.controller;


import com.apoorv.resqliciousbackend.dto.AuthRequest;
import com.apoorv.resqliciousbackend.dto.AuthResponse;
import com.apoorv.resqliciousbackend.dto.RegisterRequest;
import com.apoorv.resqliciousbackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return new ResponseEntity<>(authService.register(request), HttpStatus.OK);
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthResponse> register(
            @RequestBody AuthRequest request
    ) {
        return new ResponseEntity<>(authService.authenticate(request), HttpStatus.OK);
    }
}