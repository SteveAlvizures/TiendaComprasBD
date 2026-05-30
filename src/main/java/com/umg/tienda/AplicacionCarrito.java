package com.umg.tienda;

import com.umg.tienda.modelo.Producto;
import com.umg.tienda.repositorio.ProductoRepositorio;
import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AplicacionCarrito {

    public static void main(String[] args) {
        SpringApplication.run(AplicacionCarrito.class, args);
    }

    @Bean
    public CommandLineRunner cargarProductos(ProductoRepositorio productoRepositorio) {
        return args -> {
            if (productoRepositorio.count() == 0) {
                productoRepositorio.save(new Producto("Laptop Lenovo", "Equipo practico para estudio y trabajo.", new BigDecimal("4850.00"), 8));
                productoRepositorio.save(new Producto("Mouse inalambrico", "Mouse comodo para uso diario.", new BigDecimal("95.00"), 20));
                productoRepositorio.save(new Producto("Teclado mecanico", "Teclado resistente para escribir con comodidad.", new BigDecimal("320.00"), 12));
                productoRepositorio.save(new Producto("Monitor 24 pulgadas", "Monitor Full HD para oficina o estudio.", new BigDecimal("1150.00"), 6));
                productoRepositorio.save(new Producto("Audifonos Bluetooth", "Audifonos con buen sonido y bateria durable.", new BigDecimal("275.00"), 15));
                productoRepositorio.save(new Producto("Memoria USB 64GB", "Unidad para guardar documentos y archivos.", new BigDecimal("75.00"), 30));
            }
        };
    }
}
