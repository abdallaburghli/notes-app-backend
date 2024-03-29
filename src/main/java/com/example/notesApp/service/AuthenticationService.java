package com.example.notesApp.service;

import com.example.notesApp.config.CurrentPrincipal;
import com.example.notesApp.pojo.AuthResponse;
import com.example.notesApp.pojo.LoginRequest;
import com.example.notesApp.pojo.RegisterRequest;

public interface AuthenticationService {

    void register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    CurrentPrincipal authenticate(String token);

    AuthResponse refresh(String refreshToken);
}
