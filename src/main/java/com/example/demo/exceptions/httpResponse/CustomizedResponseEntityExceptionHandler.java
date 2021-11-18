package com.example.demo.exceptions.httpResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequest.class)
    public final ResponseEntity<Object> handleBadRequestException(BadRequest ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Conflict.class)
    public final ResponseEntity<Object> handleConflictException(Conflict ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Forbidden.class)
    public final ResponseEntity<Object> handleForbiddenException(Forbidden ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InternalServerError.class)
    public final ResponseEntity<Object> handleInternalServerErrorException(InternalServerError ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFound.class)
    public final ResponseEntity<Object> handleNotFoundException(NotFound ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RequestTimeout.class)
    public final ResponseEntity<Object> handleRequestTimeoutException(RequestTimeout ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler(ServiceUnavailable.class)
    public final ResponseEntity<Object> handleServiceUnavailableException(ServiceUnavailable ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Unauthorized.class)
    public final ResponseEntity<Object> handleUnauthorizedException(Unauthorized ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }
}
