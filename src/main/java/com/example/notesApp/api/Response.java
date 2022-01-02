package com.example.notesApp.api;

import lombok.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Getter
@Setter
public class Response<T> {
    private int status;
    private String errorCode;
    private String message;
    private T data;
    private String requestId;

    public Response() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null)
            return;
    }

    public Response(T data) {
        this();
        this.status = 0;
        this.data = data;
    }
}
