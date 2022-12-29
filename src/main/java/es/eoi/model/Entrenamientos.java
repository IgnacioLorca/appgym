package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;
import java.util.List;


@Entity
@EntityScan
@Table(name="tblEntrenamientosContratados")
public class Entrenamientos {
    @Id
    @Column(name="id_entrenamiento")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long idEntrenamiento;
    @Column(name="nombre_entrenamiento")
    public String nombreEntrenamiento;
    @Column(name="fecha_insercion")
    public Date fechaInsercion;
    @Column(name="fecha_modificacion")
    public Date fechaModificacion;

    @OneToMany (mappedBy = "entrenamientos")
    List<Entrenadores> listaEntrenadores;


    public Entrenamientos(){}

    public Entrenamientos(long idEntrenamiento, String nombreEntrenamiento, Date fechaInsercion, Date fechaModificacion) {
        this.idEntrenamiento = idEntrenamiento;
        this.nombreEntrenamiento = nombreEntrenamiento;
        this.fechaInsercion = fechaInsercion;
        this.fechaModificacion = fechaModificacion;
    }

    public long getIdEntrenamiento() {
        return idEntrenamiento;
    }

    public void setIdEntrenamiento(long idEntrenamiento) {
        this.idEntrenamiento = idEntrenamiento;
    }

    public String getNombreEntrenamiento() {
        return nombreEntrenamiento;
    }

    public void setNombreEntrenamiento(String nombreEntrenamiento) {
        this.nombreEntrenamiento = nombreEntrenamiento;
    }

    public Date getFechaInsercion() {
        return fechaInsercion;
    }

    public void setFechaInsercion(Date fechaInsercion) {
        this.fechaInsercion = fechaInsercion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Override
    public String toString() {
        return "Entrenamientos{" +
                "idEntrenamiento=" + idEntrenamiento +
                ", nombreEntrenamiento='" + nombreEntrenamiento + '\'' +
                ", fechaInsercion=" + fechaInsercion +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }
}
