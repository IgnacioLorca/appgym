package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@Entity
@EntityScan
@Table(name = "tblMaterialReservas")
public class MaterialReservas {

    @Id
    @Column(name = "id_materiales_res")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idMatRes;
    @Column(name = "fecha_update")
    private Date fechaUpdate;

    @ManyToOne
    @JoinColumn(name = "id_reservas")
    Reservas reservas;

    @ManyToOne
    @JoinColumn(name = "id_alquiler")
    Alquiler alquiler;

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

    public Alquiler getAlquiler() {
        return alquiler;
    }
    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }

    public MaterialReservas() {
    }

    public MaterialReservas(Date fechaUpdate, Reservas reservas, Alquiler alquiler) {
        this.fechaUpdate = fechaUpdate;
        this.reservas = reservas;
        this.alquiler = alquiler;
    }
}
