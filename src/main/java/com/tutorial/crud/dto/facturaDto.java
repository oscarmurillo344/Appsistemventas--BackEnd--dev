package com.tutorial.crud.dto;

import com.tutorial.crud.entity.Producto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class facturaDto {

    @NotBlank
    private String usuarioId;
    @Min(0)
    private int numeroFact;

    @NotBlank
    private Date datenow;

    @NotBlank
    private String dia;

    @NotBlank
    private Producto productoId;

    @Min(0)
    private int cantidad;

    private String extras;

     public facturaDto(){

     }

    public facturaDto(@NotBlank String usuarioId, @Min(0) int numeroFact, @NotBlank Date datenow, @NotBlank String dia, @NotBlank Producto productoId, @Min(0) int cantidad, String extras) {
        this.usuarioId = usuarioId;
        this.numeroFact = numeroFact;
        this.datenow = datenow;
        this.dia = dia;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.extras = extras;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getNumeroFact() {
        return numeroFact;
    }

    public void setNumeroFact(int numeroFact) {
        this.numeroFact = numeroFact;
    }

    public Date getDatenow() {
        return datenow;
    }

    public void setDatenow(Date datenow) {
        this.datenow = datenow;
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

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }
}
