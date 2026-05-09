package com.umg.tienda.repositorio;

import com.umg.tienda.modelo.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {
}
