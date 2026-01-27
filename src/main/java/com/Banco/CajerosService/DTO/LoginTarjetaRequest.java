package com.Banco.CajerosService.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class LoginTarjetaRequest {

    @NotBlank
    @Pattern(regexp = "\\d{8}", message = "numeroTarjeta debe ser de 8 dígitos")
    private String numeroTarjeta;

    @NotBlank
    @Pattern(regexp = "\\d{4}", message = "nip debe ser de 4 dígitos")
    private String nip;

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
}
