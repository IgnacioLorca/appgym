package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@Entity
@EntityScan
@Table(name="EntrenamientosContratados")
public class Entrenamientos {
    @Id
    @Column(name="entrenamiento")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long id;
    @Column(name="nombre_entrenamiento")
    public String nombreEntrenamiento;
    @Column(name="fecha_insercion")
    public Date fechaInsercion;
    @Column(name="fecha_modificacion")
    public Date fechaModificacion;

    public Entrenamientos(){}

    public Entrenamientos(long id, String nombreEntrenamiento, Date fechaInsercion, Date fechaModificacion) {
        this.id = id;
        this.nombreEntrenamiento = nombreEntrenamiento;
        this.fechaInsercion = fechaInsercion;
        this.fechaModificacion = fechaModificacion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
                "id=" + id +
                ", nombreEntrenamiento='" + nombreEntrenamiento + '\'' +
                ", fechaInsercion=" + fechaInsercion +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }
}
