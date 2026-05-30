package com.umg.tienda.pago;

import java.math.BigDecimal;

public interface EstrategiaPago {

    String procesarPago(BigDecimal monto);

    String obtenerMetodo();
}
