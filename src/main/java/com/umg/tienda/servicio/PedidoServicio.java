package com.umg.tienda.servicio;

import com.umg.tienda.modelo.DetallePedido;
import com.umg.tienda.modelo.Pedido;
import com.umg.tienda.repositorio.DetallePedidoRepositorio;
import com.umg.tienda.repositorio.PedidoRepositorio;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PedidoServicio {

    private final PedidoRepositorio pedidoRepositorio;
    private final DetallePedidoRepositorio detallePedidoRepositorio;

    public PedidoServicio(PedidoRepositorio pedidoRepositorio, DetallePedidoRepositorio detallePedidoRepositorio) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.detallePedidoRepositorio = detallePedidoRepositorio;
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepositorio.findAll();
    }

    public Pedido buscarPedido(Long id) {
        return pedidoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    public List<DetallePedido> listarDetalles(Pedido pedido) {
        return detallePedidoRepositorio.findByPedido(pedido);
    }
}
