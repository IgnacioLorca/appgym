package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@EntityScan
@Table(name="Recetas")
public class Recetas {
    @Id
    @Column(name="receta")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long idReceta;
    @Column(name="usuario")
    public long idUsuario;
    @Column(name="dietista")
    public long idDietista;
    @Column(name="nombre_receta")
    public String nombreReceta;
    @Column(name="descripcion")
    public String descripcion;
    @Column(name="ingredientes")
    public String ingredientes;
    @Column(name="macro")
    public String macro;

    public Recetas(){}

    public Recetas(long idReceta, long idUsuario, long idDietista, String nombreReceta, String descripcion, String ingredientes, String macro) {
        this.idReceta = idReceta;
        this.idUsuario = idUsuario;
        this.idDietista = idDietista;
        this.nombreReceta = nombreReceta;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
        this.macro = macro;
    }

    public long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(long idReceta) {
        this.idReceta = idReceta;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdDietista() {
        return idDietista;
    }

    public void setIdDietista(long idDietista) {
        this.idDietista = idDietista;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getMacro() {
        return macro;
    }

    public void setMacro(String macro) {
        this.macro = macro;
    }

    @Override
    public String toString() {
        return "Recetas{" +
                "idReceta=" + idReceta +
                ", idUsuario=" + idUsuario +
                ", idDietista=" + idDietista +
                ", nombreReceta='" + nombreReceta + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", ingredientes='" + ingredientes + '\'' +
                ", macro='" + macro + '\'' +
                '}';
    }
}

