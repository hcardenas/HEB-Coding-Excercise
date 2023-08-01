package com.hcardenas.hebcodingexcercise.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ImaggaClientErrorException extends RuntimeException{

    private HttpStatus status;
    private String message;

}
