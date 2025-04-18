package com.scss.jobcoordi.chat.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> MethodArgumentNotValidExceptionException(MethodArgumentNotValidException ex){
        String errorMessage = ex.getBindingResult()
                .getAllErrors()
                .getFirst()
                .getDefaultMessage();

        return ResponseEntity.status(400).body(new ErrorResponse(errorMessage));
    }

    @ExceptionHandler(UuidNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUuidNotFoundException(UuidNotFoundException ex){
        return ResponseEntity.status(404).body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(AiServiceException.class)
    public ResponseEntity<ErrorResponse> AiServiceExceptionException(AiServiceException ex){
        return ResponseEntity.status(500).body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> ExceptionException(Exception ex){
        return ResponseEntity.status(500).body(new ErrorResponse("생각지도 못한 오류 발생..! 발견 시 알려주기 바람"));
    }




}
