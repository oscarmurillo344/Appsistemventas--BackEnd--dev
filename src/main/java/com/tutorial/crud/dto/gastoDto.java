package com.tutorial.crud.dto;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

public class gastoDto {

    @NotBlank
    private String tipo;

    @NotBlank
    private String usuario;

    @NotNull
    private float valor;
    @NotNull
    private String descripcion;

    public gastoDto(){}

    public gastoDto(@NotBlank String tipo, @NotBlank String usuario, @NotNull float valor, @NotNull String descripcion) {
        this.tipo = tipo;
        this.usuario = usuario;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
