package com.example.demo.exceptions.httpResponse;

public class Forbidden extends RuntimeException {
    public Forbidden(String msg) {
        super(msg);
    }
}
