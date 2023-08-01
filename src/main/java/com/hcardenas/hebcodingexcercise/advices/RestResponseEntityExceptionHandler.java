package com.hcardenas.hebcodingexcercise.advices;

import com.hcardenas.hebcodingexcercise.exceptions.ImaggaClientErrorException;
import com.hcardenas.hebcodingexcercise.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ImaggaClientErrorException.class)
    public final ResponseEntity<ErrorResponse> handleClientError(ImaggaClientErrorException exception) {

        ErrorResponse response = ErrorResponse.builder()
                .status(exception.getStatus()).message(exception.getMessage()).build();

        return new ResponseEntity<>(response, exception.getStatus());
    }




}
