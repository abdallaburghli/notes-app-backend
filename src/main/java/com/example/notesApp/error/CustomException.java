package com.example.notesApp.error;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode error;
    private final String errorCode;
    private final String errorMessage;
    private int statusCode;

    public CustomException(ErrorCode error) {
        super(error.getMessage());
        this.error = error;
        this.errorCode = error.getErrorCode();
        this.errorMessage = error.getMessage();
        this.statusCode = error.getStatusCode();
    }

    public CustomException(ErrorCode error, String message) {
        super(error.getMessage());
        this.error = error;
        this.errorCode = error.getErrorCode();
        this.errorMessage = message;
        this.statusCode = error.getStatusCode();
    }

    public CustomException(ErrorCode error, Throwable cause) {
        super(error.getMessage(), cause);
        this.error = error;
        this.errorCode = error.getErrorCode();
        this.errorMessage = error.getMessage();
        this.statusCode = error.getStatusCode();
    }
}
