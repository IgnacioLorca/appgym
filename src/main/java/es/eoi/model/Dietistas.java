package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@Entity
@EntityScan
@Table(name="tblDietistas")
public class Dietistas {
    @Id
    @Column(name="id_dietista")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long idDietista;
    @Column(name="id_usuario")
    public long idUsuario;
    @Column(name="id_receta")
    public long idReceta;
    @Column(name="nombre_dietista")
    public String nombreDietista;
    @Column(name="fecha_insercion")
    public Date fechaInsercion;
    @Column(name="fecha_modificacion")
    public Date fechaModificacion;

    public Dietistas(){
    }

    public Dietistas(long idDietista, long idUsuario, long idReceta, String nombreDietista, Date fechaInsercion, Date fechaModificacion) {
        this.idDietista = idDietista;
        this.idUsuario = idUsuario;
        this.idReceta = idReceta;
        this.nombreDietista = nombreDietista;
        this.fechaInsercion = fechaInsercion;
        this.fechaModificacion = fechaModificacion;
    }

    public long getIdDietista() {
        return idDietista;
    }

    public void setIdDietista(long idDietista) {
        this.idDietista = idDietista;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(long idReceta) {
        this.idReceta = idReceta;
    }

    public String getNombreDietista() {
        return nombreDietista;
    }

    public void setNombreDietista(String nombreDietista) {
        this.nombreDietista = nombreDietista;
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
        return "Dietistas{" +
                "idDietista=" + idDietista +
                ", idUsuario=" + idUsuario +
                ", idReceta=" + idReceta +
                ", nombreDietista='" + nombreDietista + '\'' +
                ", fechaInsercion=" + fechaInsercion +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }
}
