package com.Banco.CajerosService.DTO;

public class ApiError {

    private Integer code;
    private String message;

    public ApiError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
