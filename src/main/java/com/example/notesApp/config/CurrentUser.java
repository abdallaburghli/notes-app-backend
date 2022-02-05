package com.example.notesApp.config;

import com.example.notesApp.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentUser {
    private User user;

    public CurrentUser() {
    }

    public CurrentUser(User user) {
        this.user = user;
    }
}
