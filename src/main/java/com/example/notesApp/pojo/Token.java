package com.example.notesApp.pojo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class Token {

    private final String token;

    private final Date expiryDate;
}
