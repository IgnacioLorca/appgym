package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;
import java.util.List;

@Entity
@EntityScan
@Table(name = "tblAlquiler")
public class Alquiler {

    @Id
    @Column(name ="id_alquiler")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idAlquiler;
    @Column(name = "inventario")
    private int inventario;
    @Column(name = "fecha_update")
    private Date fechaUpdate;
    @Column(name = "bl")
    private Boolean bl;

    @ManyToOne
    @JoinColumn(name = "id_catalogo")
    CatalogoProductos catalogo;

    @OneToMany(mappedBy = "alquileres")
    List<MaterialReservas> materialesReservados;

    public long getIdAlquiler() {return idAlquiler;}
    public void setIdAlquiler(long idAlquiler) {this.idAlquiler = idAlquiler;}

    public int getInventario() {return inventario;}
    public void setInventario(int inventario) {this.inventario = inventario;}

    public Date getFechaUpdate() {return fechaUpdate;}
    public void setFechaUpdate(Date fechaUpdate) {this.fechaUpdate = fechaUpdate;}

    public Boolean getBl() {return bl;}
    public void setBl(Boolean bl) {this.bl = bl;}

    public CatalogoProductos getCatalogo() {return catalogo;}
    public void setCatalogo(CatalogoProductos catalogo) {this.catalogo = catalogo;}

    public List<MaterialReservas> getMaterialesReservados() {
        return materialesReservados;
    }
    public void setMaterialesReservados(List<MaterialReservas> materialesReservados) {
        this.materialesReservados = materialesReservados;
    }

    public Alquiler() {}

    public Alquiler(long idAlquiler, int inventario, Date fechaUpdate, Boolean bl, CatalogoProductos catalogo,
                    List<MaterialReservas> materialesReservados) {
        this.idAlquiler = idAlquiler;
        this.inventario = inventario;
        this.fechaUpdate = fechaUpdate;
        this.bl = bl;
        this.catalogo = catalogo;
        this.materialesReservados = materialesReservados;
    }
}
