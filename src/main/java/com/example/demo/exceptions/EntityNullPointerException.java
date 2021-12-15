package com.example.demo.exceptions;

public class EntityNullPointerException extends RuntimeException {

    public EntityNullPointerException(String msg) {
        super(msg);
    }

    public EntityNullPointerException() {
        super();
    }
}
