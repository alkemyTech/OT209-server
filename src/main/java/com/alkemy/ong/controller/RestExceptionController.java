package com.alkemy.ong.controller;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.models.response.ApiErrorResponse;

@ControllerAdvice
public class RestExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ParamNotFound.class})
    protected ResponseEntity<Object> handleParamNotFound(RuntimeException ex, WebRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                Arrays.asList(ex.getMessage())
        );
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
