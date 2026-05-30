package com.umg.tienda.controlador;

import com.umg.tienda.servicio.ProductoServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductoControlador {

    private final ProductoServicio productoServicio;

    public ProductoControlador(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    @GetMapping({"/", "/productos"})
    public String mostrarProductos(Model model) {
        model.addAttribute("productos", productoServicio.listarProductos());
        return "productos";
    }
}
