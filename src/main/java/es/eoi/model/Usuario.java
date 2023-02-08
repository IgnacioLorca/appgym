package es.eoi.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "Usuario")
public class Usuario {
    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;
    @Column(name = "nombre", length = 50)
    private String nombre;
    @Column(name = "apellidos", length = 50)
    private String apellidos;
    @Column(name = "direccion", length = 50)
    private String direccion;
    @Column(name = "ciudad", length = 50)
    private String ciudad;
    @Column(name = "provincia", length = 50)
    private String provincia;
    @Column(name = "email", length = 50)
    private String email;
    @Column(name = "username", length = 10)
    private String username;
    @Column(name = "password", length = 20)
    private String password;
    @Column(name = "fotoPerfil")
    private String fotoPerfil;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    @Column(name = "fecha_modif")
    private Date fechaModif;
    @Column(name = "BL")
    private boolean BL;

    @OneToOne
    @JoinColumn(name = "id_datosbio")
    DatosBiometricos datosBiometricos;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}
