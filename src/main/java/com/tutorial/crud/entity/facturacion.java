package com.tutorial.crud.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tutorial.crud.security.entity.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class facturacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numeroFact;

    @NotNull
    private String usuario;

    @NotNull
    @JsonFormat(timezone = "America/Guayaquil",locale = "es_EC")
    @Temporal(TemporalType.DATE)
    private Date datenow;

    @NotNull
    @JsonFormat(timezone = "America/Guayaquil",locale = "es_EC")
    @Temporal(TemporalType.TIME)
    private Date tiempoactual;

    @NotNull
    private String dia;

    @NotNull
    @JoinTable(
            name = "rel_fact_product",
            joinColumns = @JoinColumn(name = "FK_Fact", nullable = false),
            inverseJoinColumns = @JoinColumn(name="FK_Product", nullable = false)
    )
    @ManyToOne(cascade = {CascadeType.MERGE},
            fetch = FetchType.EAGER)
    private Producto productoId;

    private int cantidad;

    public facturacion(){
    }

    public facturacion(int numeroFact, @NotNull String usuario, @NotNull Date datenow, @NotNull Date tiempoactual, @NotNull String dia, @NotNull Producto productoId, int cantidad) {
        this.numeroFact = numeroFact;
        this.usuario = usuario;
        this.datenow = datenow;
        this.tiempoactual = tiempoactual;
        this.dia = dia;
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroFact() {
        return numeroFact;
    }

    public void setNumeroFact(int numeroFact) {
        this.numeroFact = numeroFact;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getDatenow() {
        return datenow;
    }

    public void setDatenow(Date datenow) {
        this.datenow = datenow;
    }

    public Date getTiempoactual() {
        return tiempoactual;
    }

    public void setTiempoactual(Date tiempoactual) {
        this.tiempoactual = tiempoactual;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Producto getProductoId() {
        return productoId;
    }

    public void setProductoId(Producto productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}