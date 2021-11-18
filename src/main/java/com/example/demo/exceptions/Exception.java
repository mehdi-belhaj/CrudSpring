package com.example.demo.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Exception {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;
    private int code;
    private HttpStatus status;
    private String message;

    public Exception(HttpStatus status, int code, String message) {
        this.code = code;
        this.status = status;
        this.timestamp = new Date();
        this.message = message;
    }
}
