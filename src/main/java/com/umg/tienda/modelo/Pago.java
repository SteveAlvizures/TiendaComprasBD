package com.umg.tienda.modelo;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;
    private BigDecimal monto;
    private String metodoPago;
    private String estado;
    private String referencia;

    @ManyToOne
    private Orden orden;

    public Pago() {
    }

    public Pago(LocalDateTime fecha, BigDecimal monto, String metodoPago, String estado, String referencia, Orden orden) {
        this.fecha = fecha;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.estado = estado;
        this.referencia = referencia;
        this.orden = orden;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public String getEstado() {
        return estado;
    }

    public String getReferencia() {
        return referencia;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }
}
