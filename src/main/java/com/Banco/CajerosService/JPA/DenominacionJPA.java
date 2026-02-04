package com.Banco.CajerosService.JPA;

import jakarta.persistence.*;

/**
 * Entidad de Denominación (Billetes y Monedas)
 * Denomination Entity (Bills and Coins)
 */
@Entity
@Table(name = "DENOMINACION")
public class DenominacionJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DENOMINACION", nullable = false)
    private Long idDenominacion;

    @Column(name = "VALOR_CENTAVOS", nullable = false, unique = true)
    private Long valorCentavos;  // Long, NO Integer (centavos)

    @Column(name = "TIPO", nullable = false, length = 10)
    private String tipo;  // BILLETE o MONEDA

    @Column(name = "ESTADO", nullable = false)
    private Integer estado;  // 0 = inactivo, 1 = activo

    // Constructores
    public DenominacionJPA() {
    }

    public DenominacionJPA(Long valorCentavos, String tipo, Integer estado) {
        this.valorCentavos = valorCentavos;
        this.tipo = tipo;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getIdDenominacion() {
        return idDenominacion;
    }

    public Long getValorCentavos() {
        return valorCentavos;
    }

    public void setValorCentavos(Long valorCentavos) {
        this.valorCentavos = valorCentavos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
