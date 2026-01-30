//package com.Banco.CajerosService.Service;
//
//import com.Banco.CajerosService.JPA.CuentaBancariaJPA;
//import com.Banco.CajerosService.Repository.ICuentaBancariaRepository;
//import java.math.BigDecimal;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CajeroService {
//
//    private final ICuentaBancariaRepository cuentaRepo;
//
//    public CajeroService(ICuentaBancariaRepository cuentaRepo) {
//        this.cuentaRepo = cuentaRepo;
//    }
//
//    public BigDecimal obtenerSaldo(Long cuentaId) {
//        CuentaBancariaJPA cuenta = cuentaRepo.findById(cuentaId)
//                .orElseThrow(() -> new RuntimeException("Cuenta no existe"));
//
//        if (cuenta.getEstado() == null || cuenta.getEstado() != 1) {
//            throw new RuntimeException("Cuenta inactiva");
//        }
//
//        return cuenta.getSaldo();
//    }
//}
