package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@Entity
@EntityScan
@Table(name="tblRecetas")
public class Recetas {
    @Id
    @Column(name="id_receta")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long idReceta;
    @Column(name="nombre_receta")
    public String nombreReceta;
    @Column(name="descripcion")
    public String descripcion;
    @Column(name="ingredientes")
    public String ingredientes;
    @Column(name="macro")
    public String macro;

    @ManyToOne
    @JoinColumn(name = "id_dietista")
    Dietistas dietistas;

    @OneToMany (mappedBy = "recetas")
    List<Usuario> listaUsuarios;

    public Recetas(){}

    public Recetas(long idReceta, String nombreReceta, String descripcion, String ingredientes, String macro) {
        this.idReceta = idReceta;
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
                ", nombreReceta='" + nombreReceta + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", ingredientes='" + ingredientes + '\'' +
                ", macro='" + macro + '\'' +
                '}';
    }
}

