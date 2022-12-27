package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;
import java.util.List;

@Entity
@EntityScan
@Table(name = "tblHistorialReservas")
public class HistorialReservas {

    @Id
    @Column(name = "id_historial")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idHistorial;
    @Column(name = "accion")
    private String accion;
    @Column(name = "fecha_update")
    private Date fechaUpdate;

    @OneToMany(mappedBy = "historialReservas")
    List<Alquiler> alquiler;
    @OneToMany(mappedBy = "historialReservas")
    List<ClasesPrecio> clasesPrecios;

    // ¿Relación entre Reservas e HdR? Se puede obviar esta tabla?


    public Long getIdHistorial() {return idHistorial;}
    public void setIdHistorial(Long idHistorial) {this.idHistorial = idHistorial;}

    public String getAccion() {return accion;}
    public void setAccion(String accion) {this.accion = accion;}

    public Date getFechaUpdate() {return fechaUpdate;}
    public void setFechaUpdate(Date fechaUpdate) {this.fechaUpdate = fechaUpdate;}

    public List<Alquiler> getAlquiler() {return alquiler;}
    public void setAlquiler(List<Alquiler> alquiler) {this.alquiler = alquiler;}

    public List<ClasesPrecio> getClasesPrecios() {return clasesPrecios;}
    public void setClasesPrecios(List<ClasesPrecio> clasesPrecios) {this.clasesPrecios = clasesPrecios;}

    public HistorialReservas() {
    }

    public HistorialReservas(Long idHistorial, String accion, Date fechaUpdate,
                             List<Alquiler> alquiler, List<ClasesPrecio> clasesPrecios) {
        this.idHistorial = idHistorial;
        this.accion = accion;
        this.fechaUpdate = fechaUpdate;
        this.alquiler = alquiler;
        this.clasesPrecios = clasesPrecios;
    }
}
