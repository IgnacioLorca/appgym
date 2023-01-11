package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@Entity
@EntityScan
@Table(name = "tblClasesReservas")
public class ClasesReservas {

    @Id
    @Column(name = "id_clases_res")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idClasRes;
    @Column(name = "fecha_update")
    private Date fechaUpdate;

    @ManyToOne
    @JoinColumn(name = "id_reservas")
    Reservas reservas;

    @ManyToOne
    @JoinColumn(name = "id_clases")
    ClasesPrecio clases;

    public Date getFechaUpdate() {
        return fechaUpdate;
    }
    public void setFechaUpdate(Date fechaUpdate) {
        this.fechaUpdate = fechaUpdate;
    }

    public Reservas getReservas() {
        return reservas;
    }
    public void setReservas(Reservas reservas) {
        this.reservas = reservas;
    }

    public ClasesPrecio getClases() {
        return clases;
    }
    public void setClases(ClasesPrecio clases) {
        this.clases = clases;
    }

    public ClasesReservas() {
    }

    public ClasesReservas(Date fechaUpdate, Reservas reservas, ClasesPrecio clases) {
        this.fechaUpdate = fechaUpdate;
        this.reservas = reservas;
        this.clases = clases;
    }
}
