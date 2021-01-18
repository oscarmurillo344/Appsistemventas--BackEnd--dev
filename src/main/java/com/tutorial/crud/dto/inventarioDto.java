package com.tutorial.crud.dto;

import com.tutorial.crud.entity.Producto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Calendar;
import java.util.List;

public class inventarioDto {

    @NotBlank
    private Producto productoId;

    private String extras;

    @Min(0)
    private int cantidad;

    @Min(0)
    private int cantidadExist;

    public inventarioDto(){}

    public inventarioDto(@NotBlank Producto productoId, @NotBlank String extras, @Min(0) int cantidad, @Min(0) int cantidadExist) {
        this.productoId = productoId;
        this.extras = extras;
        this.cantidad = cantidad;
        this.cantidadExist = cantidadExist;
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
