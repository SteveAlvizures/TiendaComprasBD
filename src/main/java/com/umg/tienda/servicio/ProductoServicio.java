package com.umg.tienda.servicio;

import com.umg.tienda.modelo.Producto;
import com.umg.tienda.repositorio.ProductoRepositorio;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductoServicio {

    private final ProductoRepositorio productoRepositorio;

    public ProductoServicio(ProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    public List<Producto> listarProductos() {
        return productoRepositorio.findAll();
    }
}
