package com.Banco.CajerosService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Banco.CajerosService.JPA.CajeroJPA;

public interface ICajeroRepository extends JpaRepository<CajeroJPA, Integer> {
}
