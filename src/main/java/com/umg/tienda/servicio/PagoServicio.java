package com.umg.tienda.servicio;

import com.umg.tienda.modelo.Orden;
import com.umg.tienda.modelo.Pago;
import com.umg.tienda.pago.EstrategiaPago;
import com.umg.tienda.repositorio.OrdenRepositorio;
import com.umg.tienda.repositorio.PagoRepositorio;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PagoServicio {

    private final OrdenRepositorio ordenRepositorio;
    private final PagoRepositorio pagoRepositorio;
    private final List<EstrategiaPago> estrategiasPago;

    public PagoServicio(OrdenRepositorio ordenRepositorio,
                        PagoRepositorio pagoRepositorio,
                        List<EstrategiaPago> estrategiasPago) {
        this.ordenRepositorio = ordenRepositorio;
        this.pagoRepositorio = pagoRepositorio;
        this.estrategiasPago = estrategiasPago;
    }

    public Orden buscarOrden(Long ordenId) {
        return ordenRepositorio.findById(ordenId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
    }

    public Pago realizarPago(Long ordenId, String metodoPago) {
        Orden orden = buscarOrden(ordenId);

        EstrategiaPago estrategiaSeleccionada = estrategiasPago.stream()
                .filter(estrategia -> estrategia.obtenerMetodo().equalsIgnoreCase(metodoPago))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Metodo de pago no valido"));

        String referencia = estrategiaSeleccionada.procesarPago(orden.getTotal());

        orden.setEstado("PAGADA");
        ordenRepositorio.save(orden);

        Pago pago = new Pago(
                LocalDateTime.now(),
                orden.getTotal(),
                estrategiaSeleccionada.obtenerMetodo(),
                "APROBADO",
                referencia,
                orden
        );

        return pagoRepositorio.save(pago);
    }

    public List<Pago> listarPagos() {
        return pagoRepositorio.findAll();
    }
}
