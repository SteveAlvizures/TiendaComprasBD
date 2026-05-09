package com.umg.tienda.controlador;

import com.umg.tienda.modelo.Pedido;
import com.umg.tienda.servicio.CarritoServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CarritoControlador {

    private final CarritoServicio carritoServicio;

    public CarritoControlador(CarritoServicio carritoServicio) {
        this.carritoServicio = carritoServicio;
    }

    @PostMapping("/carrito/agregar/{id}")
    public String agregarProducto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            carritoServicio.agregarProducto(id);
            redirectAttributes.addFlashAttribute("mensaje", "Producto agregado al carrito correctamente.");
        } catch (RuntimeException error) {
            redirectAttributes.addFlashAttribute("error", error.getMessage());
        }
        return "redirect:/productos";
    }

    @GetMapping("/carrito")
    public String verCarrito(Model model) {
        model.addAttribute("productosCarrito", carritoServicio.listarProductosCarrito());
        model.addAttribute("total", carritoServicio.calcularTotal());
        return "carrito";
    }

    @PostMapping("/carrito/quitar/{id}")
    public String quitarUnidad(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            carritoServicio.quitarUnidad(id);
            redirectAttributes.addFlashAttribute("mensaje", "Se quito una unidad del producto.");
        } catch (RuntimeException error) {
            redirectAttributes.addFlashAttribute("error", error.getMessage());
        }
        return "redirect:/carrito";
    }

    @PostMapping("/carrito/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        carritoServicio.eliminarProducto(id);
        redirectAttributes.addFlashAttribute("mensaje", "Producto eliminado del carrito.");
        return "redirect:/carrito";
    }

    @PostMapping("/carrito/confirmar")
    public String confirmarCompra(RedirectAttributes redirectAttributes) {
        try {
            Pedido pedido = carritoServicio.confirmarCompra();
            redirectAttributes.addFlashAttribute("pedidoId", pedido.getId());
            redirectAttributes.addFlashAttribute("total", pedido.getTotal());
            return "redirect:/confirmacion";
        } catch (RuntimeException error) {
            redirectAttributes.addFlashAttribute("error", error.getMessage());
            return "redirect:/carrito";
        }
    }

    @GetMapping("/confirmacion")
    public String mostrarConfirmacion() {
        return "confirmacion";
    }
}
