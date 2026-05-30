package com.umg.tienda.pago;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class PagoEfectivo implements EstrategiaPago {

    @Override
    public String procesarPago(BigDecimal monto) {
        return "Pago registrado en efectivo por Q " + monto;
    }

    @Override
    public String obtenerMetodo() {
        return "EFECTIVO";
    }
}
