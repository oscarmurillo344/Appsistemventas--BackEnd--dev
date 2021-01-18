package com.tutorial.crud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class diaPollos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private int pollo;

    @NotNull
    private int presa;

    public diaPollos(){}

    public diaPollos(@NotNull int pollo, @NotNull int presa) {
        this.pollo = pollo;
        this.presa = presa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPollo() {
        return pollo;
    }

    public void setPollo(int pollo) {
        this.pollo = pollo;
    }

    public int getPresa() {
        return presa;
    }

    public void setPresa(int presa) {
        this.presa = presa;
    }
}
