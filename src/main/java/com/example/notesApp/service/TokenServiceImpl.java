package com.example.notesApp.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.notesApp.pojo.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    private static final String PURPOSE = "purpose";
    private static final String ACCESS = "access";
    private static final String ROOT = "root";

    private final Algorithm authAlgorithm;

    @Value("${notesApp.access.token.age}")
    private int accessAge;

    @Value("${notesApp.admin.host}")
    private String adminHost;

    public TokenServiceImpl(@Value("${notesApp.jwt.auth.secret}") String authSecret) {
        this.authAlgorithm = Algorithm.HMAC256(authSecret);
    }

    @Override
    public Token generateAccessToken(UUID userId, UUID tokenId, boolean root) {
        Date expiry = new Date(System.currentTimeMillis() + accessAge * 1000L);
        String token = JWT.create()
                .withSubject(userId.toString())
                .withJWTId(tokenId.toString())
                .withIssuer(adminHost)
                .withAudience(adminHost)
                .withClaim(PURPOSE, ACCESS)
                .withClaim(ROOT, root)
                .withExpiresAt(expiry)
                .sign(authAlgorithm);
        return new Token(token, expiry);
    }

    @Override
    public Token generateRefreshToken(UUID userId, UUID tokenId) {
        return null;
    }
}
