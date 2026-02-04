package com.Banco.CajerosService.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Servicio de encriptación con Bcrypt para NIP y contraseñas
 * Encryption Service for PIN and passwords using Bcrypt
 */
@Service
public class EncryptionService {

    private final BCryptPasswordEncoder passwordEncoder;

    public EncryptionService() {
        this.passwordEncoder = new BCryptPasswordEncoder(10);
    }

    /**
     * Encripta un NIP
     * 
     * @param nipPlano NIP sin encriptar (ej: "1234")
     * @return NIP encriptado para guardar en BD
     */
    public String encryptPin(String nipPlano) {
        if (nipPlano == null || nipPlano.isEmpty()) {
            throw new IllegalArgumentException("NIP no puede estar vacío");
        }
        return passwordEncoder.encode(nipPlano);
    }

    /**
     * Valida que un NIP plano coincida con el hash guardado en BD
     * 
     * @param nipPlano          NIP sin encriptar (ej: "1234")
     * @param nipHashAlmacenado Hash del NIP en BD
     * @return true si coinciden
     */
    public boolean validatePin(String nipPlano, String nipHashAlmacenado) {
        if (nipPlano == null || nipHashAlmacenado == null) {
            return false;
        }
        return passwordEncoder.matches(nipPlano, nipHashAlmacenado);
    }
}