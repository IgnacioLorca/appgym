package es.eoi.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import jakarta.persistence.*;
import java.util.List;

@Entity
@EntityScan
@Table(name = "empleado")
public class Empleado {
  @Id
  @Column (name ="codigo_empleado")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @Column (name ="nombre",length = 50)
  private String nombre;
  @Column (name ="apellido1",length = 50 )
  private String apellido1;
  @Column (name ="apellido2",length = 50)
  private String apellido2;
  @Column (name ="extension",length = 10  )
  private String extension;
  @Column (name ="email",length = 100  )
  private String email;
  @Column (name ="codigo_jefe")
  private int codigoJefe;
  @Column (name ="puesto",length =  50)
  private String puesto;


  @OneToMany(mappedBy = "empleado")
  List<Cliente> cliente;

  @ManyToOne
  @JoinColumn(name = "codigo_oficina")
  Oficina oficina;

  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido1() {
    return apellido1;
  }
  public void setApellido1(String apellido1) {
    this.apellido1 = apellido1;
  }

  public String getApellido2() {
    return apellido2;
  }
  public void setApellido2(String apellido2) {
    this.apellido2 = apellido2;
  }

  public String getExtension() {
    return extension;
  }
  public void setExtension(String extension) {
    this.extension = extension;
  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public int getCodigoJefe() {
    return codigoJefe;
  }
  public void setCodigoJefe(int codigoJefe) {
    this.codigoJefe = codigoJefe;
  }

  public String getPuesto() {
    return puesto;
  }
  public void setPuesto(String puesto) {
    this.puesto = puesto;
  }

  public List<Cliente> getCliente() {
    return cliente;
  }
  public void setCliente(List<Cliente> cliente) {
    this.cliente = cliente;
  }

  public Oficina getOficina() {
    return oficina;
  }
  public void setOficina(Oficina oficina) {
    this.oficina = oficina;
  }

  public Empleado() {}

  public Empleado(long id, String nombre, String apellido1, String apellido2, String extension,
                  String email, String codigoOficina, int codigoJefe, String puesto,
                  List<Cliente> cliente, Oficina oficina) {
    this.id = id;
    this.nombre = nombre;
    this.apellido1 = apellido1;
    this.apellido2 = apellido2;
    this.extension = extension;
    this.email = email;
    this.codigoJefe = codigoJefe;
    this.puesto = puesto;
    this.cliente = cliente;
    this.oficina = oficina;
  }
}
