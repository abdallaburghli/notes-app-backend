package com.example.notesApp.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum Errors implements ErrorCode {
    INVALID_LOGIN("Invalid login", UNAUTHORIZED),
    INVALID_TOKEN("Invalid or expired token", UNAUTHORIZED),
    NOTE_DOES_NOT_EXIST("Note does not exist or does not belong to the user", NOT_FOUND),
    ILLEGAL_NOTE_REMOVAL("Illegal note removal");

    private final String message;
    private final HttpStatus httpStatus;

    Errors(String message) {
        this.message = message;
        this.httpStatus = UNPROCESSABLE_ENTITY;
    }

    Errors(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getErrorCode() {
        return name();
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getStatusCode() {
        return httpStatus.value();
    }
}
