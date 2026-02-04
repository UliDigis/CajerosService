package com.Banco.CajerosService.Util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Utility para conversión de dinero entre centavos y decimales
 * Money Converter Utility - Centavos ↔ Decimal
 */
public class MoneyConverter {

    private static final BigDecimal CENT_DIVISOR = BigDecimal.valueOf(100);
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("$0.00");

    /**
     * Convierte centavos a BigDecimal (dinero decimal)
     * 
     * @param centavos Cantidad en centavos (ej: 5050)
     * @return BigDecimal con dinero decimal (ej: 50.50)
     */
    public static BigDecimal centavosToBigDecimal(Long centavos) {
        if (centavos == null || centavos == 0) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(centavos).divide(CENT_DIVISOR);
    }

    /**
     * Convierte centavos a double (dinero decimal)
     * 
     * @param centavos Cantidad en centavos
     * @return double con dinero decimal
     */
    public static double centavosToDouble(Long centavos) {
        if (centavos == null || centavos == 0) {
            return 0.0;
        }
        return centavos / 100.0;
    }

    /**
     * Convierte BigDecimal (dinero decimal) a centavos
     * 
     * @param dinero BigDecimal con dinero decimal (ej: 50.50)
     * @return Long con cantidad en centavos (ej: 5050)
     */
    public static Long bigDecimalToCentavos(BigDecimal dinero) {
        if (dinero == null || dinero.compareTo(BigDecimal.ZERO) == 0) {
            return 0L;
        }
        return dinero.multiply(CENT_DIVISOR).longValue();
    }

    /**
     * Convierte double (dinero decimal) a centavos
     * 
     * @param dinero double con dinero decimal
     * @return Long con cantidad en centavos
     */
    public static Long doubleToCentavos(double dinero) {
        if (dinero == 0.0) {
            return 0L;
        }
        return BigDecimal.valueOf(dinero).multiply(CENT_DIVISOR).longValue();
    }

    /**
     * Formatea centavos como string de dinero formateado
     * 
     * @param centavos Cantidad en centavos
     * @return String formateado (ej: "$50.50")
     */
    public static String formatCentavos(Long centavos) {
        if (centavos == null) {
            return DECIMAL_FORMAT.format(0);
        }
        BigDecimal dinero = centavosToBigDecimal(centavos);
        return DECIMAL_FORMAT.format(dinero);
    }

    /**
     * Valida si un monto en centavos es válido (positivo y múltiplo de 50)
     * Mínimo: 50 centavos ($0.50)
     * 
     * @param centavos Cantidad en centavos
     * @return true si es válido
     */
    public static boolean isValidAmount(Long centavos) {
        if (centavos == null || centavos < 50) {
            return false;
        }
        // Debe ser múltiplo de 50 (puede dispensarse)
        return centavos % 50 == 0;
    }

    /**
     * Obtiene el mínimo retirable (50 centavos = $0.50)
     * 
     * @return Long con mínimo en centavos
     */
    public static Long getMinimumAmount() {
        return 50L;
    }
}
