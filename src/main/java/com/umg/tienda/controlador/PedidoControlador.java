package com.umg.tienda.controlador;

import com.umg.tienda.modelo.Pedido;
import com.umg.tienda.servicio.PedidoServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PedidoControlador {

    private final PedidoServicio pedidoServicio;

    public PedidoControlador(PedidoServicio pedidoServicio) {
        this.pedidoServicio = pedidoServicio;
    }

    @GetMapping("/pedidos")
    public String verPedidos(Model model) {
        model.addAttribute("pedidos", pedidoServicio.listarPedidos());
        return "pedidos";
    }

    @GetMapping("/pedidos/{id}")
    public String verDetallePedido(@PathVariable Long id, Model model) {
        Pedido pedido = pedidoServicio.buscarPedido(id);
        model.addAttribute("pedido", pedido);
        model.addAttribute("detalles", pedidoServicio.listarDetalles(pedido));
        return "detalle-pedido";
    }
}
