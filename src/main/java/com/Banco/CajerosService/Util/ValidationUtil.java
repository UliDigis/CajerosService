package com.Banco.CajerosService.Util;

import java.util.regex.Pattern;

/**
 * Utility para validaciones de datos
 * Validation Utility
 */
public class ValidationUtil {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@(.+)$"
    );

    private static final Pattern CARD_NUMBER_PATTERN = Pattern.compile("^\\d{8}$");

    private static final Pattern PIN_PATTERN = Pattern.compile("^\\d{4,6}$");

    private static final Pattern ATM_CODE_PATTERN = Pattern.compile("^[A-Z0-9-]{3,30}$");

    /**
     * Valida formato de email
     * 
     * @param email Email a validar
     * @return true si es válido
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Valida número de tarjeta (8 dígitos)
     * 
     * @param cardNumber Número de tarjeta
     * @return true si es válido
     */
    public static boolean isValidCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.isBlank()) {
            return false;
        }
        return CARD_NUMBER_PATTERN.matcher(cardNumber).matches();
    }

    /**
     * Valida PIN (4-6 dígitos)
     * 
     * @param pin NIP a validar
     * @return true si es válido
     */
    public static boolean isValidPin(String pin) {
        if (pin == null || pin.isBlank()) {
            return false;
        }
        return PIN_PATTERN.matcher(pin).matches();
    }

    /**
     * Valida código de ATM (ATM-001, ATM-002, etc)
     * 
     * @param atmCode Código del ATM
     * @return true si es válido
     */
    public static boolean isValidAtmCode(String atmCode) {
        if (atmCode == null || atmCode.isBlank()) {
            return false;
        }
        return ATM_CODE_PATTERN.matcher(atmCode).matches();
    }

    /**
     * Valida que una contraseña sea segura
     * Mínimo 8 caracteres, con mayúsculas, minúsculas, números
     * 
     * @param password Contraseña a validar
     * @return true si es válida
     */
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasUpperCase = password.matches(".*[A-Z].*");
        boolean hasLowerCase = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*\\d.*");

        return hasUpperCase && hasLowerCase && hasDigit;
    }

    /**
     * Valida número de cuenta bancaria
     * 
     * @param accountNumber Número de cuenta
     * @return true si es válido
     */
    public static boolean isValidAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.isBlank()) {
            return false;
        }
        // Número de cuenta entre 10-30 caracteres alfanuméricos
        return accountNumber.matches("^[A-Z0-9]{10,30}$");
    }

    /**
     * Limpia y normaliza un string de entrada
     * Elimina espacios y caracteres peligrosos
     * 
     * @param input String de entrada
     * @return String normalizado
     */
    public static String sanitize(String input) {
        if (input == null) {
            return null;
        }
        return input.trim().replaceAll("[^a-zA-Z0-9@._-]", "");
    }

    /**
     * Valida que un ID sea válido (número positivo)
     * 
     * @param id ID a validar
     * @return true si es válido
     */
    public static boolean isValidId(Long id) {
        return id != null && id > 0;
    }
}
