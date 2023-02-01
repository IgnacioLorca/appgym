package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;
import java.util.List;

@Entity
@EntityScan
@Table(name = "tblCalendario")
public class Calendario {

    @Id
    @Column(name ="id_calendario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCalendario;
    @Column(name = "fecha_reserva")
    private Date fechaReserva;
    @Column(name = "fecha_update")
    private Date fechaUpdate;

    @OneToMany(mappedBy = "calendario")
    List<Reservas> reservas;

    public long getIdCalendario() {return idCalendario;}
    public void setIdCalendario(long idCalendario) {this.idCalendario = idCalendario;}

    public Date getFechaReserva() {return fechaReserva;}
    public void setFechaReserva(Date fechaReserva) {this.fechaReserva = fechaReserva;}

    public Date getFechaUpdate() {return fechaUpdate;}
    public void setFechaUpdate(Date fechaUpdate) {this.fechaUpdate = fechaUpdate;}

    public List<Reservas> getReservas() {return reservas;}
    public void setReservas(List<Reservas> reservas) {this.reservas = reservas;}

    public Calendario() {
    }

    public Calendario(long idCalendario, Date fechaReserva, Date fechaUpdate, List<Reservas> reservas) {
        this.idCalendario = idCalendario;
        this.fechaReserva = fechaReserva;
        this.fechaUpdate = fechaUpdate;
        this.reservas = reservas;
    }
}