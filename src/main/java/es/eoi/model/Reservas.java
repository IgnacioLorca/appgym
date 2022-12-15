package com.example.jpa_formacion.model;

import java.util.Date;


public class Reservas {
    // Atributos de la entidad "Reservas"
    private int idUsuario;
    private int idCatalogo;
    private int reservas;
    private Date fechaUpdate;
    private String historial;
    private String progresos;

    // Constructor de la clase "Reservas"
    public Reservas(int idUsuario, int idCatalogo, int reservas, Date fechaUpdate, String historial, String progresos) {
        this.idUsuario = idUsuario;
        this.idCatalogo = idCatalogo;
        this.reservas = reservas;
        this.fechaUpdate = fechaUpdate;
        this.historial = historial;
        this.progresos = progresos;
    }

    // Métodos de acceso para los atributos de la clase "Reservas"
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(int idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public int getReservas() {
        return reservas;
    }

    public void setReservas(int reservas) {
        this.reservas = reservas;
    }

    public Date getFechaUpdate() {
        return fechaUpdate;
    }

    public void setFechaUpdate(Date fechaUpdate) {
        this.fechaUpdate = fechaUpdate;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    public String getProgresos() {
        return progresos;
    }

    public void setProgresos(String progresos) {
        this.progresos = progresos;
    }
}
