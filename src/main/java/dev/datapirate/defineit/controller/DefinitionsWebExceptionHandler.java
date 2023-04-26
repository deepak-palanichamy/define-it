package dev.datapirate.defineit.controller;

import dev.datapirate.defineit.exception.DefinitionNotFoundException;
import dev.datapirate.defineit.model.ApiResponse;
import dev.datapirate.defineit.model.v2.ConsolidatedDefinitionV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class DefinitionsWebExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DefinitionNotFoundException.class)
    public ResponseEntity<ApiResponse<ConsolidatedDefinitionV2>> handleDefinitionNotFoundException(DefinitionNotFoundException ex) {
        log.info("Exiting handleDefinitionNotFoundException(), ex: {}", ex.getMessage());
        ApiResponse<ConsolidatedDefinitionV2> apiResponse = new ApiResponse<>();
//        apiResponse.setErrorCode(ex.getStatus().value());
        apiResponse.setErrors(List.of(ex.getMessage()));
        log.info("Leaving handleDefinitionNotFoundException(), apiResponse: {}", apiResponse);
        return new ResponseEntity<>(apiResponse, ex.getStatus());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<ConsolidatedDefinitionV2>> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.info("Exiting handleIllegalArgumentException(), ex: {}", ex.getMessage());
        ApiResponse<ConsolidatedDefinitionV2> apiResponse = new ApiResponse<>();
//        apiResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setErrors(List.of("Bad Request", "Invalid input for request", ex.getMessage()));
        log.info("Leaving handleIllegalArgumentException(), apiResponse: {}", apiResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

}
