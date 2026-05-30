package com.umg.tienda.controlador;

import com.umg.tienda.modelo.Orden;
import com.umg.tienda.modelo.Pago;
import com.umg.tienda.servicio.PagoServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PagoControlador {

    private final PagoServicio pagoServicio;

    public PagoControlador(PagoServicio pagoServicio) {
        this.pagoServicio = pagoServicio;
    }

    @GetMapping("/pago/{ordenId}")
    public String mostrarPago(@PathVariable Long ordenId, Model model) {
        Orden orden = pagoServicio.buscarOrden(ordenId);
        model.addAttribute("orden", orden);
        return "pago";
    }

    @PostMapping("/pago/realizar")
    public String realizarPago(@RequestParam Long ordenId,
                               @RequestParam String metodoPago,
                               Model model) {
        Pago pago = pagoServicio.realizarPago(ordenId, metodoPago);
        model.addAttribute("pago", pago);
        return "pago-confirmado";
    }
}
