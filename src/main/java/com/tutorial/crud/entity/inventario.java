package com.tutorial.crud.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.List;

@Entity
public class inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @JsonFormat(timezone = "America/Guayaquil")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fecha;

    @JoinTable(
            name = "rel_Inv_product",
            joinColumns = @JoinColumn(name = "FK_Inventario", nullable = false),
            inverseJoinColumns = @JoinColumn(name="FK_Product", nullable = false)
    )
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Producto productoId;

    private String extras;

    private int cantidad;

    private int cantidadExist;

    public inventario(){}

    public inventario(@NotNull Calendar fecha, Producto productoId, String extras, int cantidad, int cantidadExist) {
        this.fecha = fecha;
        this.productoId = productoId;
        this.extras = extras;
        this.cantidad = cantidad;
        this.cantidadExist = cantidadExist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public Producto getProductoId() {
        return productoId;
    }

    public void setProductoId(Producto productoId) {
        this.productoId = productoId;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidadExist() {
        return cantidadExist;
    }

    public void setCantidadExist(int cantidadExist) {
        this.cantidadExist = cantidadExist;
    }
}
