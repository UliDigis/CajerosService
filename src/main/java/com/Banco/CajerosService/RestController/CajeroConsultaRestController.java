//package com.Banco.CajerosService.RestController;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.Banco.CajerosService.DTO.ApiResponse;
//import com.Banco.CajerosService.Service.CajeroConsultaService;
//
//@RestController
//@RequestMapping("/cajeros")
//public class CajeroConsultaRestController {
//
//    private final CajeroConsultaService service;
//
//    public CajeroConsultaRestController(CajeroConsultaService service) {
//        this.service = service;
//    }
//
//    /**
//     * Lista todos los cajeros con saldo disponible. El frontend decide si
//     */
//    @GetMapping
//    public ResponseEntity<ApiResponse> obtenerCajeros() {
//        return ResponseEntity.ok(service.obtenerCajeros());
//    }
//}
//
