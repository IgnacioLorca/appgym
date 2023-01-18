package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;
import java.util.List;

@Entity
@EntityScan
@Table(name="tblDietistas")
public class Dietistas {
    @Id
    @Column(name="id_dietista")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long idDietista;
    @Column(name="nombre_dietista")
    public String nombreDietista;
    @Column(name="fecha_insercion")
    public Date fechaInsercion;
    @Column(name="fecha_modificacion")
    public Date fechaModificacion;

    @OneToMany (mappedBy = "dietistas")
    List<Usuario> listaUsuarios;

    @OneToMany (mappedBy = "dietistas")
    List<Recetas> listaRecetas;

    public Dietistas(){
    }

    public Dietistas(long idDietista, String nombreDietista, Date fechaInsercion, Date fechaModificacion) {
        this.idDietista = idDietista;
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
                ", nombreDietista='" + nombreDietista + '\'' +
                ", fechaInsercion=" + fechaInsercion +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }
}
