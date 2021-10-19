package it.zysk.empik.recruitmenttask.rest;

import it.zysk.empik.recruitmenttask.rest.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException e) {
        ApiError apiError = ApiError.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();

        return ResponseEntity.badRequest().body(apiError);
    }
}
