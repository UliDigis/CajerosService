package com.Banco.CajerosService.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class LoginCuentaRequest {

    @NotBlank
    @Pattern(regexp = "\\d{8}", message = "numeroCuenta debe ser de 10 dígitos")
    private String numeroCuenta;

    @NotBlank
    @Pattern(regexp = "\\d{4}", message = "nip debe ser de 4 dígitos")
    private String nip;

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
}
