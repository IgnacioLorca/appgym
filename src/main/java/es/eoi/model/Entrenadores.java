package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;


@Entity
@EntityScan
@Table(name="tblEntrenadoresContratados")
public class Entrenadores {
    @Id
    @Column(name="id_entrenador")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long idEntrenador;
    @Column(name="id_usuario")
    public long idUsuario;
    @Column(name="nombre_entrenador")
    public String nombreEntrenador;
    @Column(name="fecha_insercion")
    public Date fechaInsercion;
    @Column(name="fecha_modificacion")
    public Date fechaModificacion;


    public Entrenadores(){
    }

    public Entrenadores(long idEntrenador, long idUsuario, String nombreEntrenador, Date fechaInsercion, Date fechaModificacion) {
        this.idEntrenador = idEntrenador;
        this.idUsuario = idUsuario;
        this.nombreEntrenador = nombreEntrenador;
        this.fechaInsercion = fechaInsercion;
        this.fechaModificacion = fechaModificacion;
    }

    public long getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(long idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
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
                "idEntrenador=" + idEntrenador +
                ", idUsuario=" + idUsuario +
                ", nombreEntrenador='" + nombreEntrenador + '\'' +
                ", fechaInsercion=" + fechaInsercion +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }
}

