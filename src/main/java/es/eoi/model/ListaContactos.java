package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;
import java.util.Set;

@Entity
@EntityScan
@Table(name = "tblListaContactos")
public class ListaContactos {

    @Id
    @Column(name ="id_contacto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idContacto;
    @Column (name = "id_usuario")
    private long idUsuario;
    @Column (name = "id_usuario_contacto")
    private long idUsuarioContacto;
    @Column (name = "fecha_insert")
    private Date fechaInsert;
    @Column (name = "fecha_update")
    private Date fechaUpdate;
    @Column (name ="BL")
    private boolean BL;

    @ManyToMany(mappedBy = "contactos")
    Set<Usuario> usuarios;

    public ListaContactos() {
    }

    public ListaContactos(long idContacto, long idUsuario, long idUsuarioContacto, Date fechaInsert, Date fechaUpdate, boolean BL, Set<Usuario> usuarios) {
        this.idContacto = idContacto;
        this.idUsuario = idUsuario;
        this.idUsuarioContacto = idUsuarioContacto;
        this.fechaInsert = fechaInsert;
        this.fechaUpdate = fechaUpdate;
        this.BL = BL;
        this.usuarios = usuarios;
    }

    public long getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(long idContacto) {
        this.idContacto = idContacto;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdUsuarioContacto() {
        return idUsuarioContacto;
    }

    public void setIdUsuarioContacto(long idUsuarioContacto) {
        this.idUsuarioContacto = idUsuarioContacto;
    }

    public Date getFechaInsert() {
        return fechaInsert;
    }

    public void setFechaInsert(Date fechaInsert) {
        this.fechaInsert = fechaInsert;
    }

    public Date getFechaUpdate() {
        return fechaUpdate;
    }

    public void setFechaUpdate(Date fechaUpdate) {
        this.fechaUpdate = fechaUpdate;
    }

    public boolean isBL() {
        return BL;
    }

    public void setBL(boolean BL) {
        this.BL = BL;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return "Contactos{" +
                "idContacto=" + idContacto +
                ", idUsuario=" + idUsuario +
                ", idUsuarioContacto=" + idUsuarioContacto +
                ", fechaInsert=" + fechaInsert +
                ", fechaUpdate=" + fechaUpdate +
                ", BL=" + BL +
                ", usuarios=" + usuarios +
                '}';
    }
}
