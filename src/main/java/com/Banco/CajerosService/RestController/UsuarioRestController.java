package com.Banco.CajerosService.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Banco.CajerosService.DTO.ApiResponse;
import com.Banco.CajerosService.Service.UsuarioConsultaService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {

    private final UsuarioConsultaService usuarioService;

    public UsuarioRestController(UsuarioConsultaService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Retorna el listado de usuarios vía SP_OBTENER_USUARIOS.
     */
    @GetMapping
    public ApiResponse obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }
}

