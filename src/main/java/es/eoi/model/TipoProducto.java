package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@EntityScan
@Table(name = "tblTipoProducto")
public class TipoProducto {

    @Id
    @Column(name = "id_tipo_producto")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idTipoProducto;
    @Column (name ="nombre",length = 50)
    private String nombre;
    @Column (name ="descripcion",length = 100)
    private String descripcion;

    @ManyToOne
    CatalogoProductos catalogo;

    public long getIdTipoProducto() {
        return idTipoProducto;
    }
    public void setIdTipoProducto(long idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public CatalogoProductos getCatalogo() {return catalogo;}
    public void setCatalogo(CatalogoProductos catalogo) {this.catalogo = catalogo;}

    public TipoProducto() {}

    public TipoProducto(long idTipoProducto, String nombre, String descripcion, CatalogoProductos catalogo) {
        this.idTipoProducto = idTipoProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.catalogo = catalogo;
    }
}
