package dev.naman.productservicettsevening.controllers;

import dev.naman.productservicettsevening.dtos.ErrorResponseDto;
import dev.naman.productservicettsevening.exception.CategoryNotFoundException;
import dev.naman.productservicettsevening.exception.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> idNotFoundException(Exception e){
        ErrorResponseDto error=new ErrorResponseDto();
        error.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> CategoryNotFoundException(Exception e){
        ErrorResponseDto error=new ErrorResponseDto();
        error.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
