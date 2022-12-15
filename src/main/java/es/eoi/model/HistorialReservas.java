package es.eoi.model;

import java.util.Date;

public class HistorialReservas {
    private int idHistorialReservas;
    private int idReserva;
    private String accion;
    private Date fechaUpdate;

    public HistorialReservas() {}

    public int getIdHistorialReservas() {
        return idHistorialReservas;
    }

    public void setIdHistorialReservas(int idHistorialReservas) {
        this.idHistorialReservas = idHistorialReservas;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Date getFechaUpdate() {
        return fechaUpdate;
    }

    public void setFechaUpdate(Date fechaUpdate) {
        this.fechaUpdate = fechaUpdate;
    }
}


