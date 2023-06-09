package com.project.blockchainapi.exception;

import com.project.blockchainapi.response.MessageResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.stream.Collectors;

import static com.project.blockchainapi.constant.Constant.SERVER_ERROR;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @Order(Ordered.HIGHEST_PRECEDENCE)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> error.getDefaultMessage()
                ));


        MessageResponse response = MessageResponse.builder()
                .internalStatus("BAD_REQUEST")
                .internalMessage("Invalid form field")
                .data(errors)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {

        Map<String, String> errors = ex.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(
                        error -> error.getPropertyPath().toString(),
                        error -> error.getMessage()
                ));

        MessageResponse response = MessageResponse.builder()
                .internalStatus("BAD_REQUEST")
                .internalMessage("Invalid form field")
                .data(errors)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerException.class)
    protected ResponseEntity<Object> handleConstraintViolation(InternalServerException ex) {

        MessageResponse response = MessageResponse.builder()
                .internalStatus(SERVER_ERROR)
                .internalMessage(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ExpiredJwtException ex) {

        MessageResponse response = MessageResponse.builder()
                .internalStatus("JWT_EXPIRED")
                .internalMessage(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ClientRequestException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ClientRequestException ex) {
        MessageResponse response = MessageResponse.builder()
                .internalStatus("BAD_REQUEST")
                .internalMessage(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
