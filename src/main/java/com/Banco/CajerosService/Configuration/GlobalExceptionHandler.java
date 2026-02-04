package com.Banco.CajerosService.Configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.Banco.CajerosService.DTO.ApiResponse;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Gestor global de excepciones
 * Global Exception Handler for all application exceptions
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones genéricas de RuntimeException
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {
        
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", ex.getMessage());
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("path", request.getDescription(false).replace("uri=", ""));

        // Si el mensaje contiene palabras clave, mapear a HTTP status específico
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String errorCode = "ERR_001";

        if (ex.getMessage() != null) {
            String message = ex.getMessage().toLowerCase();
            
            if (message.contains("credencial") || message.contains("inválido")) {
                status = HttpStatus.UNAUTHORIZED;
                errorCode = "AUTH_001";
            } else if (message.contains("saldo") || message.contains("insufficient")) {
                status = HttpStatus.BAD_REQUEST;
                errorCode = "BALANCE_001";
            } else if (message.contains("tarjeta") || message.contains("card")) {
                status = HttpStatus.BAD_REQUEST;
                errorCode = "AUTH_002";
            } else if (message.contains("cajero") || message.contains("atm")) {
                status = HttpStatus.BAD_REQUEST;
                errorCode = "ATM_001";
            } else if (message.contains("no existe") || message.contains("not found")) {
                status = HttpStatus.NOT_FOUND;
                errorCode = "NOT_FOUND";
            }
        }

        errorDetails.put("errorCode", errorCode);
        return ResponseEntity.status(status).body(ApiResponse.error(errorDetails));
    }

    /**
     * Maneja IllegalArgumentException
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", ex.getMessage());
        errorDetails.put("errorCode", "VALIDATION_001");
        errorDetails.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(errorDetails));
    }

    /**
     * Maneja IllegalStateException
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse> handleIllegalStateException(
            IllegalStateException ex, WebRequest request) {

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", ex.getMessage());
        errorDetails.put("errorCode", "STATE_ERROR");
        errorDetails.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(errorDetails));
    }

    /**
     * Maneja NullPointerException
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse> handleNullPointerException(
            NullPointerException ex, WebRequest request) {

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", "Un valor requerido es nulo");
        errorDetails.put("errorCode", "VALIDATION_002");
        errorDetails.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(errorDetails));
    }

    /**
     * Maneja todas las excepciones no capturadas
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGlobalException(Exception ex, WebRequest request) {

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", "Error interno del servidor");
        errorDetails.put("errorCode", "ERR_003");
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("exception", ex.getClass().getSimpleName());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(errorDetails));
    }
}
