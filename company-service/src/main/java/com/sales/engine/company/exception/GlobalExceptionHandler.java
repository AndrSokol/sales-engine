package com.sales.engine.company.exception;

import com.sales.engine.company.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CompanyAlreadyExistsException.class)
    public ResponseEntity<ExceptionDto> handleCustomException(CompanyAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDto(ex.getMessage()));
    }
}
