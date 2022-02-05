package com.example.notesApp.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.notesApp.error.CustomException;
import com.example.notesApp.error.Errors;
import com.example.notesApp.pojo.AccessToken;
import com.example.notesApp.pojo.RefreshToken;
import com.example.notesApp.pojo.Token;
import com.example.notesApp.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    private static final String PURPOSE = "purpose";
    private static final String ACCESS = "access";
    private static final String ROOT = "root";
    private static final String REFRESH = "refresh";

    private final Algorithm authAlgorithm;

    @Value("${notesApp.access.token.age}")
    private int accessAge;
    @Value("${notesApp.refresh.token.age}")
    private int refreshAge;

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
        Date expiry = new Date(System.currentTimeMillis() + refreshAge * 1000L);
        String token = JWT.create()
                .withSubject(userId.toString())
                .withJWTId(tokenId.toString())
                .withIssuer(adminHost)
                .withAudience(adminHost)
                .withClaim(PURPOSE, REFRESH)
                .withExpiresAt(expiry)
                .sign(authAlgorithm);
        return new Token(token, expiry);
    }

    @Override
    public RefreshToken verifyRefreshToken(String token) {
        try {
            token = cleanToken(token);
            DecodedJWT jwt = JWT.require(authAlgorithm)
                    .withIssuer(adminHost)
                    .withAudience(adminHost)
                    .withClaim(PURPOSE, REFRESH)
                    .build()
                    .verify(token);
            UUID userId = UUID.fromString(jwt.getSubject());
            UUID tokenId = UUID.fromString(jwt.getId());
            return new RefreshToken(userId, tokenId);
        } catch (JWTVerificationException e) {
            throw new CustomException(Errors.INVALID_TOKEN);
        }
    }

    @Override
    public AccessToken verifyAccessToken(String token) {
        try {
            token = cleanToken(token);
            DecodedJWT jwt = JWT.require(authAlgorithm)
                    .withIssuer(adminHost)
                    .withAudience(adminHost)
                    .withClaim(PURPOSE, ACCESS)
                    .build()
                    .verify(token);
            UUID userId = UUID.fromString(jwt.getSubject());
            UUID tokenId = UUID.fromString(jwt.getId());
            Boolean root = jwt.getClaim(ROOT).asBoolean();
            return new AccessToken(userId, tokenId, root);
        } catch (JWTVerificationException e) {
            throw new CustomException(Errors.INVALID_TOKEN);
        }
    }

    private String cleanToken(String token) {
        return token.replaceAll("(?i)bearer ", "");
    }

}
