package com.example.demo.exceptions.httpResponse;

public class BadRequest extends RuntimeException {
    public BadRequest(String msg) {
        super(msg);
    }
}
