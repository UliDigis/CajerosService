package com.Banco.CajerosService.DTO;

/**
 * Constantes de códigos de error para la API
 * Error Code Constants
 */
public class ErrorCode {

    // Errores de autenticación
    public static final String INVALID_CREDENTIALS = "AUTH_001";
    public static final String INVALID_CARD = "AUTH_002";
    public static final String INVALID_PIN = "AUTH_003";
    public static final String CARD_INACTIVE = "AUTH_004";
    public static final String ACCOUNT_INACTIVE = "AUTH_005";
    public static final String EXPIRED_TOKEN = "AUTH_006";

    // Errores de saldo
    public static final String INSUFFICIENT_BALANCE = "BALANCE_001";
    public static final String INVALID_AMOUNT = "BALANCE_002";
    public static final String ACCOUNT_NOT_FOUND = "BALANCE_003";

    // Errores de ATM
    public static final String ATM_INACTIVE = "ATM_001";
    public static final String ATM_NOT_FOUND = "ATM_002";
    public static final String INSUFFICIENT_CASH = "ATM_003";
    public static final String CANNOT_DISPENSE_AMOUNT = "ATM_004";

    // Errores de validación
    public static final String INVALID_REQUEST = "VALIDATION_001";
    public static final String MISSING_PARAMETERS = "VALIDATION_002";
    public static final String INVALID_EMAIL = "VALIDATION_003";
    public static final String INVALID_CARD_NUMBER = "VALIDATION_004";

    // Errores de transacción
    public static final String TRANSACTION_FAILED = "TX_001";
    public static final String TRANSACTION_ROLLED_BACK = "TX_002";

    // Errores generales
    public static final String INTERNAL_ERROR = "ERR_001";
    public static final String DATABASE_ERROR = "ERR_002";
    public static final String UNKNOWN_ERROR = "ERR_003";
}
