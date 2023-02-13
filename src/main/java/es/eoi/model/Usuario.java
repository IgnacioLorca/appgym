package es.eoi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "Usuario")
public class Usuario implements Serializable {
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
    @Column(name = "username", length = 60)
    private String username;
    @Column(name = "password", length = 60)
    private String password;
    @Column(name = "foto_perfil")
    private String fotoPerfil;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    @Column(name = "fecha_modif")
    private Date fechaModif;
    @Column(name = "BL")
    private boolean BL;
    @Column(name = "aprobado")
    private boolean aprobado;

    @OneToOne
    @JoinColumn(name = "id_datosbio")
    DatosBiometricos datosBiometricos;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Basic(optional = false)
    private boolean active;
}

