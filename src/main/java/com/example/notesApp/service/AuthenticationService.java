package com.example.notesApp.service;

import com.example.notesApp.pojo.AuthResponse;
import com.example.notesApp.pojo.LoginRequest;
import com.example.notesApp.pojo.RegisterRequest;

public interface AuthenticationService {

    void register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
