package com.example.notesApp.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegisterRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String fullName;
}
