package com.Banco.CajerosService.DTO;

import java.util.List;
import java.util.Map;

/**
 * DTO para resultados de operaciones
 * Result DTO for Operations - Especialmente para retiros
 */
public class Result {

    private boolean success;
    private String message;
    private Object data;
    private List<Map<String, Object>> desglose;  // Para retiros (desglose de billetes)
    private Long saldoRestanteCentavos;           // Para retiros (saldo después)

    // Constructores
    public Result() {
    }

    public Result(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Result(Long saldoRestanteCentavos, List<Map<String, Object>> desglose) {
        this.saldoRestanteCentavos = saldoRestanteCentavos;
        this.desglose = desglose;
        this.success = true;
    }

    // Getters y Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<Map<String, Object>> getDesglose() {
        return desglose;
    }

    public void setDesglose(List<Map<String, Object>> desglose) {
        this.desglose = desglose;
    }

    public Long getSaldoRestanteCentavos() {
        return saldoRestanteCentavos;
    }

    public void setSaldoRestanteCentavos(Long saldoRestanteCentavos) {
        this.saldoRestanteCentavos = saldoRestanteCentavos;
    }
}
