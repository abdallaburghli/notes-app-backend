package com.example.notesApp.service.impl;

import com.example.notesApp.config.CurrentPrincipal;
import com.example.notesApp.mapper.UserMapper;
import com.example.notesApp.model.User;
import com.example.notesApp.model.repository.UserRepo;
import com.example.notesApp.pojo.*;
import com.example.notesApp.security.PasswordPolicy;
import com.example.notesApp.service.AuthenticationService;
import com.example.notesApp.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepo userRepo;
    private final PasswordPolicy passwordPolicy;
    private final TokenService tokenService;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Override
    @Transactional
    public void register(RegisterRequest request) {
        String email = request.getEmail().toLowerCase();

        User user = new User();
        user.setEmail(email);
        user.setFullName(request.getFullName());
        user.setActive(true);
        user.setPassword(passwordPolicy.encode(request.getPassword()));

        userRepo.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepo.findByEmailAndActiveIsTrue(request.getEmail().toLowerCase())
                .orElseThrow(() -> new RuntimeException("cant find this user"));

        if (!passwordPolicy.matches(request.getPassword(), user.getPassword()))
            throw new RuntimeException("Login Error");

        user.setLastLogin(ZonedDateTime.now());
        userRepo.save(user);

        UUID tokenId = UUID.randomUUID();

        return generateAuthResponse(user, tokenId);
    }

    private AuthResponse generateAuthResponse(User user, UUID tokenId) {

        Token accessToken = tokenService.generateAccessToken(user.getId(), tokenId, user.isRoot());

        UserModel userModel = userMapper.convert(user);

        userModel.setRole(user.isRoot() ? UserModel.Role.ROOT : UserModel.Role.USER);

        return new AuthResponse()
                .setAccessToken(accessToken.getToken())
                .setAccessTokenExpiry(accessToken.getExpiryDate())
                .setUser(userModel);
    }

    @Override
    public CurrentPrincipal authenticate(String token) {
        if (!StringUtils.hasText(token))
            return new CurrentPrincipal()
                    .setType(CurrentPrincipal.Type.GUEST);

        AccessToken accessToken = tokenService.verifyAccessToken(token);

        return new CurrentPrincipal()
                .setType(CurrentPrincipal.Type.USER)
                .setSubjectId(accessToken.subject())
                .setTokenId(accessToken.tokenId())
                .setRoot(accessToken.root());
    }
}