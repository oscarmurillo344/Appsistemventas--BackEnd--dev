package com.tutorial.crud.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProductoDto {

    @NotBlank
    private String nombre;
    @NotBlank
    private String tipo;
    @Min(0)
    private Float precio;
    @Min(0)
    private int presa;

    public ProductoDto() {
    }

    public ProductoDto(@NotBlank String nombre, String tipo, @Min(0) Float precio, int presa) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.presa = presa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public int getPresa() {
        return presa;
    }

    public void setPresa(int presa) {
        this.presa = presa;
    }
}
