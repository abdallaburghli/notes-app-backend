package com.example.notesApp.controller;

import com.example.notesApp.api.Response;
import com.example.notesApp.pojo.AuthResponse;
import com.example.notesApp.pojo.LoginRequest;
import com.example.notesApp.pojo.RegisterRequest;
import com.example.notesApp.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;

    @PostMapping("/api/register")
    public Response<?> register(@Valid @RequestBody RegisterRequest request) {
        authenticationService.register(request);
        return new Response<>(new HashMap<>());
    }

    @PostMapping("/api/login")
    public Response<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse res = authenticationService.login(request);
        return new Response<>(res);
    }

    @PostMapping("/api/refresh")
    public Response<AuthResponse> refresh(@RequestHeader(HttpHeaders.AUTHORIZATION) String refreshToken) {
        AuthResponse res = authenticationService.refresh(refreshToken);
        return new Response<>(res);
    }
}
