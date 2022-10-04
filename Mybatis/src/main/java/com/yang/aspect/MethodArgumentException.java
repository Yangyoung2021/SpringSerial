package com.yang.aspect;


import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class MethodArgumentException extends Exception {

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public MethodArgumentException(@Nullable String message) {
        super(message);
    }
}
