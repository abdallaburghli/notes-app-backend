package com.example.notesApp.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Getter
@Setter
public class AuthResponse {

    private String accessToken;

    private String refreshToken;

    private Date accessTokenExpiry;

    private Date refreshTokenExpiry;

    private UserModel user;
}
