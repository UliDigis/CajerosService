package com.Banco.CajerosService.DTO;

public class TarjetaAuthResult {

    private Long usuarioId;
    private Long cuentaId;
    private Long rolId;
    private String rolNombre;

    public TarjetaAuthResult(Long usuarioId, Long cuentaId, Long rolId, String rolNombre) {
        this.usuarioId = usuarioId;
        this.cuentaId = cuentaId;
        this.rolId = rolId;
        this.rolNombre = rolNombre;
    }

    public Long getUsurioId() {
        return usuarioId;
    }

    public Long getCuentaId() {
        return cuentaId;
    }

    public Long getRolId() {
        return rolId;
    }

    public String getRolNombre() {
        return rolNombre;
    }
}
