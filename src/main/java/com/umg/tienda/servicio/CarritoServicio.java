package com.umg.tienda.servicio;

import com.umg.tienda.modelo.DetallePedido;
import com.umg.tienda.modelo.Pedido;
import com.umg.tienda.modelo.Producto;
import com.umg.tienda.modelo.ProductoCarrito;
import com.umg.tienda.repositorio.DetallePedidoRepositorio;
import com.umg.tienda.repositorio.PedidoRepositorio;
import com.umg.tienda.repositorio.ProductoCarritoRepositorio;
import com.umg.tienda.repositorio.ProductoRepositorio;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CarritoServicio {

    private final ProductoRepositorio productoRepositorio;
    private final ProductoCarritoRepositorio productoCarritoRepositorio;
    private final PedidoRepositorio pedidoRepositorio;
    private final DetallePedidoRepositorio detallePedidoRepositorio;

    public CarritoServicio(ProductoRepositorio productoRepositorio,
                           ProductoCarritoRepositorio productoCarritoRepositorio,
                           PedidoRepositorio pedidoRepositorio,
                           DetallePedidoRepositorio detallePedidoRepositorio) {
        this.productoRepositorio = productoRepositorio;
        this.productoCarritoRepositorio = productoCarritoRepositorio;
        this.pedidoRepositorio = pedidoRepositorio;
        this.detallePedidoRepositorio = detallePedidoRepositorio;
    }

    public List<ProductoCarrito> listarProductosCarrito() {
        return productoCarritoRepositorio.findAll();
    }

    public void agregarProducto(Long productoId) {
        Producto producto = productoRepositorio.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (producto.getExistencias() <= 0) {
            throw new RuntimeException("El producto no tiene existencias disponibles");
        }

        ProductoCarrito productoCarrito = productoCarritoRepositorio.findByProductoId(productoId)
                .orElse(null);

        if (productoCarrito == null) {
            productoCarrito = new ProductoCarrito(producto, 1, producto.getPrecio());
        } else {
            if (productoCarrito.getCantidad() >= producto.getExistencias()) {
                throw new RuntimeException("No hay mas existencias disponibles de este producto");
            }
            productoCarrito.setCantidad(productoCarrito.getCantidad() + 1);
            productoCarrito.setSubtotal(producto.getPrecio().multiply(BigDecimal.valueOf(productoCarrito.getCantidad())));
        }

        productoCarritoRepositorio.save(productoCarrito);
    }

    public void quitarUnidad(Long productoCarritoId) {
        ProductoCarrito productoCarrito = productoCarritoRepositorio.findById(productoCarritoId)
                .orElseThrow(() -> new RuntimeException("Producto del carrito no encontrado"));

        if (productoCarrito.getCantidad() <= 1) {
            productoCarritoRepositorio.delete(productoCarrito);
        } else {
            productoCarrito.setCantidad(productoCarrito.getCantidad() - 1);
            productoCarrito.setSubtotal(productoCarrito.getProducto().getPrecio().multiply(BigDecimal.valueOf(productoCarrito.getCantidad())));
            productoCarritoRepositorio.save(productoCarrito);
        }
    }

    public void eliminarProducto(Long productoCarritoId) {
        productoCarritoRepositorio.deleteById(productoCarritoId);
    }

    public BigDecimal calcularTotal() {
        return productoCarritoRepositorio.findAll()
                .stream()
                .map(ProductoCarrito::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Pedido confirmarCompra() {
        List<ProductoCarrito> productosCarrito = productoCarritoRepositorio.findAll();

        if (productosCarrito.isEmpty()) {
            throw new RuntimeException("El carrito esta vacio");
        }

        BigDecimal total = calcularTotal();
        Pedido pedido = new Pedido(LocalDateTime.now(), total, "CONFIRMADO");
        pedido = pedidoRepositorio.save(pedido);

        for (ProductoCarrito productoCarrito : productosCarrito) {
            Producto producto = productoCarrito.getProducto();

            if (producto.getExistencias() < productoCarrito.getCantidad()) {
                throw new RuntimeException("No hay suficientes existencias de " + producto.getNombre());
            }

            DetallePedido detallePedido = new DetallePedido(
                    pedido,
                    producto,
                    productoCarrito.getCantidad(),
                    productoCarrito.getSubtotal()
            );
            detallePedidoRepositorio.save(detallePedido);

            producto.setExistencias(producto.getExistencias() - productoCarrito.getCantidad());
            productoRepositorio.save(producto);
        }

        productoCarritoRepositorio.deleteAll();
        return pedido;
    }
}
