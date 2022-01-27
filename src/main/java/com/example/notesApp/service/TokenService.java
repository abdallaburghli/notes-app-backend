package com.example.notesApp.service;

import com.example.notesApp.pojo.Token;

import java.util.UUID;

public interface TokenService {

    Token generateAccessToken(UUID userId, UUID tokenId, boolean root);

    Token generateRefreshToken(UUID userId, UUID tokenId);
}
