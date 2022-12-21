package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;


@Entity
@EntityScan
@Table(name="EntrenadoresContratados")
public class Entrenadores {
    @Id
    @Column(name="entrenador")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long id;
    @Column(name="nombre_entrenador")
    public String nombreEntrenador;
    @Column(name="fecha_insercion")
    public Date fechaInsercion;
    @Column(name="fecha_modificacion")
    public Date fechaModificacion;

    public Entrenadores(){}

    public Entrenadores(long id, String nombreEntrenador, Date fechaInsercion, Date fechaModificacion) {
        this.id = id;
        this.nombreEntrenador = nombreEntrenador;
        this.fechaInsercion = fechaInsercion;
        this.fechaModificacion = fechaModificacion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreEntrenador() {
        return nombreEntrenador;
    }

    public void setNombreEntrenador(String nombreEntrenador) {
        this.nombreEntrenador = nombreEntrenador;
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
        return "Entrenadores{" +
                "id=" + id +
                ", nombreEntrenador='" + nombreEntrenador + '\'' +
                ", fechaInsercion=" + fechaInsercion +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }
}
