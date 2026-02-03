package com.Banco.CajerosService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Banco.CajerosService.JPA.TarjetaJPA;

public interface ITarjetaRepository extends JpaRepository<TarjetaJPA, Integer> {
}
