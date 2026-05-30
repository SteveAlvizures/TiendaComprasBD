package com.umg.tienda.repositorio;

import com.umg.tienda.modelo.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepositorio extends JpaRepository<Orden, Long> {
}
