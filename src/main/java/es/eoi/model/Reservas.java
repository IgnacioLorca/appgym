package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@Entity
@EntityScan
@Table(name = "tblReservas")
public class Reservas {
    @Id
    @GeneratedValue
    @Column(name = "id_usuario")
    private long id_usuario;

    @Column(name = "id_catalogo")
    private long id_catalogo;

    private int reservas;
    private Date fecha_update;
    private String historial;
    private String progresos;

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public long getId_catalogo() {
        return id_catalogo;
    }

    public void setId_catalogo(long id_catalogo) {
        this.id_catalogo = id_catalogo;
    }

    public int getReservas() {
        return reservas;
    }

    public void setReservas(int reservas) {
        this.reservas = reservas;
    }

    public Date getFecha_update() {
        return fecha_update;
    }

    public void setFecha_update(Date fecha_update) {
        this.fecha_update = fecha_update;
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