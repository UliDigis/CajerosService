package com.Banco.CajerosService.Repository;

import com.Banco.CajerosService.JPA.CuentaBancariaJPA;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICuentaBancariaRepository extends JpaRepository<CuentaBancariaJPA, Long> {

    Optional<CuentaBancariaJPA> findByNumeroCuenta(String numeroCuenta);
}
