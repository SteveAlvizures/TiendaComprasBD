package com.umg.tienda.repositorio;

import com.umg.tienda.modelo.ProductoCarrito;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoCarritoRepositorio extends JpaRepository<ProductoCarrito, Long> {

    Optional<ProductoCarrito> findByProductoId(Long productoId);
}
