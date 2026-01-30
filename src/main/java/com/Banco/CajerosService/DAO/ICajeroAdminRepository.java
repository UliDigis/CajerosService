package com.Banco.CajerosService.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.Banco.CajerosService.JPA.CajeroJPA;

public interface ICajeroAdminRepository extends JpaRepository<CajeroJPA, Long> {

    /**
     * Ejecuta SP_RECARGAR_CAJERO. Recarga un cajero específico usando su
     * CODIGO_CAJERO. El SP ajusta el efectivo del cajero a la plantilla
     * estándar.
     */
    @Procedure(procedureName = "SP_RECARGAR_CAJERO")
    void recargarCajero(@Param("p_codigo_cajero") String codigo);

    /**
     * Ejecuta SP_RECARGAR_TODOS_CAJEROS. Recarga todos los cajeros activos
     * ajustándolos a la plantilla estándar.
     */
    @Procedure(procedureName = "SP_RECARGAR_TODOS_CAJEROS")
    void recargarTodos();
}
