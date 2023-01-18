package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@Entity
@EntityScan
@Table(name = "tblCatalogo")
public class CatalogoProductos {

    @Id
    @Column(name = "id_catalogo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCatalogo;
    @Column (name ="nombre",length = 50)
    private String nombre;
    @Column (name ="descripcion",length = 100)
    private String descripcion;

    @OneToMany(mappedBy = "catalogo")
//    @JoinColumn(name = "id_tipo_producto")
    List<TipoProducto> tipoProductos;

    @OneToMany(mappedBy = "catalogo")
//    @JoinColumn(name = "id_clases")
    List<ClasesPrecio> clasesPrecio;

    @OneToMany(mappedBy = "catalogo")
//    @JoinColumn(name = "id_alquiler")
    List<Alquiler> alquiler;

    @ManyToMany(mappedBy = "catalogo")
    List<Reservas> reservas;

    public long getIdCatalogo() {return idCatalogo;}
    public void setIdCatalogo(long idCatalogo) {this.idCatalogo = idCatalogo;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public List<TipoProducto> getTipoProductos() {return tipoProductos;}
    public void setTipoProductos(List<TipoProducto> tipoProductos) {this.tipoProductos = tipoProductos;}

    public List<ClasesPrecio> getClasesPrecio() {return clasesPrecio;}
    public void setClasesPrecio(List<ClasesPrecio> clasesPrecio) {this.clasesPrecio = clasesPrecio;}

    public List<Alquiler> getAlquiler() {return alquiler;}
    public void setAlquiler(List<Alquiler> alquiler) {this.alquiler = alquiler;}

    public List<Reservas> getReservas() {
        return reservas;
    }
    public void setReservas(List<Reservas> reservas) {
        this.reservas = reservas;
    }

    public CatalogoProductos() {
    }

    public CatalogoProductos(long idCatalogo, String nombre, String descripcion, List<TipoProducto> tipoProductos,
                             List<ClasesPrecio> clasesPrecio, List<Alquiler> alquiler, List<Reservas> reservas) {
        this.idCatalogo = idCatalogo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoProductos = tipoProductos;
        this.clasesPrecio = clasesPrecio;
        this.alquiler = alquiler;
        this.reservas = reservas;
    }
}
