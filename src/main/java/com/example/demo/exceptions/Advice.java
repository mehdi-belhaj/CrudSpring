package com.example.demo.exceptions;

import com.example.demo.exceptions.httpResponse.BadRequest;
import com.example.demo.exceptions.httpResponse.Conflict;
import com.example.demo.exceptions.httpResponse.Forbidden;
import com.example.demo.exceptions.httpResponse.NotFound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class Advice extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ResponseEntity<Object> buildResponseEntity(Exception apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<Object> handleBadRequest(BadRequest ex) {
        Exception exception = new Exception(HttpStatus.BAD_REQUEST, 400, ex.getMessage());
        return buildResponseEntity(exception);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
        Exception exception = new Exception(HttpStatus.UNAUTHORIZED, 401, ex.getMessage());
        return buildResponseEntity(exception);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(Forbidden.class)
    public ResponseEntity<Object> handleForbidden(Forbidden ex) {
        Exception exception = new Exception(HttpStatus.FORBIDDEN, 403, ex.getMessage());
        return buildResponseEntity(exception);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        Exception exception = new Exception(HttpStatus.NOT_FOUND, 404, ex.getMessage());
        return buildResponseEntity(exception);
    }

    @ExceptionHandler(EntityNullPointerException.class)
    public ResponseEntity<Object> handleEntityNullPointerException(EntityNullPointerException ex) {
        Exception exception = new Exception(HttpStatus.NOT_FOUND, 404, ex.getMessage());
        return buildResponseEntity(exception);
    }

    @ExceptionHandler(EntityContentException.class)
    public ResponseEntity<Object> handleEntityContentException(EntityContentException ex) {
        Exception exception = new Exception(HttpStatus.NOT_FOUND, 404, ex.getMessage());
        return buildResponseEntity(exception);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFound.class)
    public Exception notFound02() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Conflict.class)
    public Exception conflict() {
        Exception exception = new Exception(HttpStatus.CONFLICT, 409, "Already exist email");
        return exception;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(java.lang.Exception.class)
    public Exception serverError() {
        Exception exception = new Exception();
        exception.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        exception.setMessage("Internal server error");
        return exception;
    }

    private Exception shareNotFound() {
        Exception exception = new Exception();
        exception.setStatus(HttpStatus.NOT_FOUND);
        exception.setMessage("Not found path");
        return exception;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingRequestCookieException.class)
    public Exception MissingRequestCookie(MissingRequestCookieException ex) {
        logger.error("Error due to: " + ex.getMessage());
        Exception exception = new Exception();
        exception.setStatus(HttpStatus.BAD_REQUEST);
        exception.setMessage("Session Expired");
        return exception;
    }
}
