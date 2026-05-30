package com.umg.tienda.pago;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class PagoTarjeta implements EstrategiaPago {

    @Override
    public String procesarPago(BigDecimal monto) {
        return "Pago aprobado con tarjeta por Q " + monto;
    }

    @Override
    public String obtenerMetodo() {
        return "TARJETA";
    }
}
