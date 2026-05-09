package com.umg.tienda.repositorio;

import com.umg.tienda.modelo.DetallePedido;
import com.umg.tienda.modelo.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePedidoRepositorio extends JpaRepository<DetallePedido, Long> {

    List<DetallePedido> findByPedido(Pedido pedido);
}
