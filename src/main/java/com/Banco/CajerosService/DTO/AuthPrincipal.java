package com.Banco.CajerosService.DTO;

public class AuthPrincipal {

    private Long usuarioId;
    private Long cuentaId;

    public AuthPrincipal(Long usuarioId, Long cuentaId) {
        this.usuarioId = usuarioId;
        this.cuentaId = cuentaId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Long getCuentaId() {
        return cuentaId;
    }
}
