package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;
import java.util.List;

@Entity
@EntityScan
@Table(name = "tblReservas")
public class Reservas {

    @Id
    @Column(name = "id_reservas")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idReservas;
    @Column(name = "progresos")
    private Float progresos;
    @Column(name = "fecha_update")
    private Date fechaUpdate;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_calendario")
    Calendario calendario;

    @JoinTable(
            name = "reserva_catalogo",
            joinColumns = @JoinColumn(name = "id_reservas"),
            inverseJoinColumns = @JoinColumn(name = "id_catalogo"))
    @ManyToMany(cascade = CascadeType.ALL)
    List<CatalogoProductos> catalogo;

    // ¿Relación entre Reservas e HdR? Se puede obviar esta tabla?

    public long getIdReservas() {return idReservas;}
    public void setIdReservas(long idReservas) {this.idReservas = idReservas;}

    public Float getProgresos() {return progresos;}
    public void setProgresos(Float progresos) {this.progresos = progresos;}

    public Date getFechaUpdate() {return fechaUpdate;}
    public void setFechaUpdate(Date fechaUpdate) {this.fechaUpdate = fechaUpdate;}

    public Usuario getUsuario() {return usuario;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}

    public Calendario getCalendario() {return calendario;}
    public void setCalendario(Calendario calendario) {this.calendario = calendario;}

    public List<CatalogoProductos> getCatalogo() {return catalogo;}
    public void setCatalogo(List<CatalogoProductos> catalogo) {this.catalogo = catalogo;}

    public Reservas() {
    }

    public Reservas(long idReservas, Float progresos, Date fechaUpdate, Usuario usuario,
                    Calendario calendario, List<CatalogoProductos> catalogo) {
        this.idReservas = idReservas;
        this.progresos = progresos;
        this.fechaUpdate = fechaUpdate;
        this.usuario = usuario;
        this.calendario = calendario;
        this.catalogo = catalogo;
    }
}