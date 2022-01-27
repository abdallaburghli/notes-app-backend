package com.example.notesApp.config;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
public class CurrentPrincipal {

    private Type type;
    private UUID subjectId;
    private UUID tokenId;
    private boolean root;

    public enum Type {
        GUEST,
        USER
    }
}
