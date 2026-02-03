package com.Banco.CajerosService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Banco.CajerosService.JPA.CuentaBancariaJPA;

public interface ICuentaBancariaRepository extends JpaRepository<CuentaBancariaJPA, Long> {
}
