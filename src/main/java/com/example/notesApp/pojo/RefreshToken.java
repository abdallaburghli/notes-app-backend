package com.example.notesApp.pojo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class RefreshToken {
    private final UUID userId;
    private final UUID tokenId;
}
