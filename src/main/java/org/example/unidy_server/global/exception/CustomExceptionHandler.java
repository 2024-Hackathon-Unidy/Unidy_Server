package org.example.unidy_server.global.exception;

import org.example.unidy_server.global.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseResponse<?>> handleCustomException(CustomException exception) {
        final BaseResponse<?> response = BaseResponse.of(
                exception.getErrorCode().getStatus(),
                exception.getErrorCode().getMessage(),
                null
        );

        return ResponseEntity
                .status(exception.getErrorCode().getCode())
                .body(response);
    }

}