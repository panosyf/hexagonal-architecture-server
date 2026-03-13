package com.hexagonal.server.shared.kernel.common.api.exception.handler;

import com.hexagonal.server.shared.kernel.common.exception.ElementNotFoundException;
import com.hexagonal.server.shared.kernel.common.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ElementNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(final ElementNotFoundException exception) {
        ApiError apiError = new ApiError("Element not found", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(apiError);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handleBadRequestException(final IllegalArgumentException exception) {
        ApiError apiError = new ApiError("Illegal argument", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiError);
    }

}
