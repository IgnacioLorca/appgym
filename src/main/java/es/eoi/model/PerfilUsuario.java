package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@EntityScan
@Table(name = "tblPerfilUsuario")
public class PerfilUsuario {

    @Id
    @Column(name = "id_perfil")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idPerfil;
    @Column (name = "nombre_perfil", length = 50)
    private String nombrePerfil;
    @Column (name = "descripcion", length = 50)
    private String descripcion;
    @Column (name ="BL")
    private boolean BL;

    @OneToOne(mappedBy = "perfilUsuario")
    private Usuario usuario;

    public PerfilUsuario() {
    }

    public PerfilUsuario(long idPerfil, String nombrePerfil, String descripcion, boolean BL, Usuario usuario) {
        this.idPerfil = idPerfil;
        this.nombrePerfil = nombrePerfil;
        this.descripcion = descripcion;
        this.BL = BL;
        this.usuario = usuario;
    }

    public long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isBL() {
        return BL;
    }

    public void setBL(boolean BL) {
        this.BL = BL;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "PerfilUsuario{" +
                "idPerfil=" + idPerfil +
                ", nombrePerfil='" + nombrePerfil + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", BL=" + BL +
                ", usuario=" + usuario +
                '}';
    }
}
