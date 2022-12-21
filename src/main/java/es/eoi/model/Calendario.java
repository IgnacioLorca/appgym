package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@Entity
@EntityScan
@Table(name = "tblCalendario")
public class Calendario {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "idReservas")
    private long id_reservas;

    @Column(name = "fechaReserva")
    private Date fecha_reserva;

    @Column(name = "fechaUpdate")
    private Date fecha_update;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_reservas() {
        return id_reservas;
    }

    public void setId_reservas(long id_reservas) {
        this.id_reservas = id_reservas;
    }

    public Date getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(Date fechaReserva) {
        this.fecha_reserva = fechaReserva;
    }

    public Date getFecha_update() {
        return fecha_update;
    }

    public void setFecha_update(Date fechaUpdate) {
        this.fecha_update = fecha_update;
    }
}