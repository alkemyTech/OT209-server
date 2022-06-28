package com.alkemy.ong.models.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorResponse {

    private HttpStatus status;
    private String message;
    private List<String> errors;
}
