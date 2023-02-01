package es.eoi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tblListaContactos")
public class Contactos {

    @Id
    @Column(name ="id_contacto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRelacion;
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

}
