package com.example.notesApp.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {

    private String email;

    private String fullName;

    private Role role;

    public enum Role {
        USER,
        ROOT
    }
}
