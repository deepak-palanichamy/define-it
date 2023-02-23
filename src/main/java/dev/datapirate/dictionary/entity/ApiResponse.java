package dev.datapirate.dictionary.entity;

import lombok.Data;

import java.util.List;

@Data
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private List<String> errors;
    private int errorCode;

    public ApiResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public ApiResponse(boolean success, List<String> errors, int errorCode) {
        this.success = success;
        this.errors = errors;
        this.errorCode = errorCode;
    }
}
