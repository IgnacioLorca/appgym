package es.eoi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "Contactos")
public class Contactos {

    @Id
    @Column(name="id_relacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRelacion;
    @Column (name = "fecha_creacion")
    private Date fechaCreacion;
    @Column (name = "fecha_modif")
    private Date fechaModif;
    @Column (name = "BL")
    private boolean BL;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "contactos")
    private Set<Usuario> usuarios;

}
