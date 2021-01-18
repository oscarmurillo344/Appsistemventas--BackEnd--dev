package com.tutorial.crud.dto;

public class actualizarPollo {

    int pollo;
    int presa;

    public  actualizarPollo(){}
    public actualizarPollo(int pollo, int presa) {
        this.pollo = pollo;
        this.presa = presa;
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
