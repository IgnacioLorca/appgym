package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@EntityScan
@Table(name = "tblDatosUsuario")
public class Usuario {

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idUsuario;
    @Column (name = "nombre", length = 50)
    private String nombre;
    @Column (name = "apellidos", length = 50)
    private String apellidos;
    @Column (name = "direccion", length = 50)
    private String direccion;
    @Column (name ="ciudad", length = 50)
    private String ciudad;
    @Column (name = "email", length = 50)
    private String email;
    @Column (name ="username",length = 10)
    private String username;
    @Column (name ="password",length = 20)
    private String password;
    @Column (name ="peso")
    private float peso;
    @Column (name ="altura")
    private float altura;
    @Column (name = "fecha_insert")
    private Date fechaInsert;
    @Column (name = "fecha_update")
    private Date fechaUpdate;
    @Column (name = "BL")
    private boolean BL;
    @Column (name = "tipo_usuario")
    private String tipoUsuario;

    @OneToOne
    @JoinColumn(name = "id_perfil")
    PerfilUsuario perfilUsuario;

    @ManyToMany
    @JoinTable(
            name = "tblListaContactos_tblDatosUsuario",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_contacto"))
    Set<ListaContactos> contactos;

    @ManyToOne
    @JoinColumn(name = "id_entrenador")
    Entrenadores entrenadores;

    @ManyToOne
    @JoinColumn(name = "id_dietista")
    Dietistas dietistas;

    @ManyToOne
    @JoinColumn(name = "id_receta")
    Recetas recetas;

    @OneToMany(mappedBy = "usuario")
    List<Reservas> reservas;

    public Usuario() {
    }

    public Usuario(long idUsuario, long idPerfil, String nombre, String apellidos, String direccion, String ciudad, String email, String username, String password, float peso, float altura, Date fechaInsert, Date fechaUpdate, boolean BL, String tipoUsuario, PerfilUsuario perfilUsuario, Set<ListaContactos> contactos) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.email = email;
        this.username = username;
        this.password = password;
        this.peso = peso;
        this.altura = altura;
        this.fechaInsert = fechaInsert;
        this.fechaUpdate = fechaUpdate;
        this.BL = BL;
        this.tipoUsuario = tipoUsuario;
        this.perfilUsuario = perfilUsuario;
        this.contactos = contactos;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
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

    public PerfilUsuario getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(PerfilUsuario perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

    public Set<ListaContactos> getContactos() {
        return contactos;
    }

    public void setContactos(Set<ListaContactos> contactos) {
        this.contactos = contactos;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", peso=" + peso +
                ", altura=" + altura +
                ", fechaInsert=" + fechaInsert +
                ", fechaUpdate=" + fechaUpdate +
                ", BL=" + BL +
                ", tipoUsuario='" + tipoUsuario + '\'' +
                ", perfilUsuario=" + perfilUsuario +
                ", contactos=" + contactos +
                '}';
    }
}
