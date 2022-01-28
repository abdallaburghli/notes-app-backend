package com.example.notesApp.pojo;

import java.util.UUID;

public record AccessToken (
        UUID subject,
        UUID tokenId,
        boolean root) {
}
