package com.tutorial.crud.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String tipo;
    private float precio;
    private int presa;



    public Producto() {
    }

    public Producto(String nombre, String tipo,float precio, int presa) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.presa = presa;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getPresa() {
        return presa;
    }

    public void setPresa(int presa) {
        this.presa = presa;
    }
}
