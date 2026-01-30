package com.Banco.CajerosService.DTO;

public class ApiResponse {

    private boolean success;
    private Object data;
    private Object error;

    public ApiResponse(boolean success, Object data, Object error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public static ApiResponse ok(Object data) {
        return new ApiResponse(true, data, null);
    }

    public static ApiResponse error(Object error) {
        return new ApiResponse(false, null, error);
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getData() {
        return data;
    }

    public Object getError() {
        return error;
    }
}
