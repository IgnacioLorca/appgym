package es.eoi.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tblHistorialReservas")
public class HistorialReservas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idReserva")
    private Long idReserva;

    @Column(name = "accion")
    private String accion;

    @Column(name = "fechaUpdate")
    private Date fechaUpdate;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
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
