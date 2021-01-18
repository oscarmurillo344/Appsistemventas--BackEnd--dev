package com.tutorial.crud.dto;

import java.util.Calendar;
import java.sql.Date;

public class BetweenFechas {

    String usuario;
    String tiempoF;
    String tiempoS;
    Date fechaFirst;
    Date fechaSecond;

    public BetweenFechas(){}

    public BetweenFechas(String usuario, String tiempoF, String tiempoS, Date fechaFirst, Date fechaSecond) {
        this.usuario = usuario;
        this.tiempoF = tiempoF;
        this.tiempoS = tiempoS;
        this.fechaFirst = fechaFirst;
        this.fechaSecond = fechaSecond;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTiempoF() {
        return tiempoF;
    }

    public void setTiempoF(String tiempoF) {
        this.tiempoF = tiempoF;
    }

    public String getTiempoS() {
        return tiempoS;
    }

    public void setTiempoS(String tiempoS) {
        this.tiempoS = tiempoS;
    }

    public Date getFechaFirst() {
        return fechaFirst;
    }

    public void setFechaFirst(Date fechaFirst) {
        this.fechaFirst = fechaFirst;
    }

    public Date getFechaSecond() {
        return fechaSecond;
    }

    public void setFechaSecond(Date fechaSecond) {
        this.fechaSecond = fechaSecond;
    }
}
