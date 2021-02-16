package com.tutorial.crud.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Entity
public class gastos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String tipo;

    @NotNull
    private String usuario;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "America/Guayaquil",locale = "es_EC")
    private Calendar fecha;
    @NotNull
    private float valor;
    @NotNull
    private String descripcion;

    public gastos(){}

    public gastos(@NotNull String tipo, @NotNull String usuario, @NotNull Calendar fecha, @NotNull float valor, @NotNull String descripcion) {
        this.id = id;
        this.tipo = tipo;
        this.usuario = usuario;
        this.fecha = fecha;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
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
