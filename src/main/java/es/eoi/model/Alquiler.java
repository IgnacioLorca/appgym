package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

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

//    @OneToMany(mappedBy = "id_hist_reservas")
//    List<HistorialReservas> historialReservas;

    @ManyToOne
    @JoinColumn(name = "id_catalogo")
    CatalogoProductos catalogo;

    public long getIdAlquiler() {return idAlquiler;}
    public void setIdAlquiler(long idAlquiler) {this.idAlquiler = idAlquiler;}

    public int getInventario() {return inventario;}
    public void setInventario(int inventario) {this.inventario = inventario;}

    public Date getFechaUpdate() {return fechaUpdate;}
    public void setFechaUpdate(Date fechaUpdate) {this.fechaUpdate = fechaUpdate;}

    public Boolean getBl() {return bl;}
    public void setBl(Boolean bl) {this.bl = bl;}

//    public List<HistorialReservas> getHistorialReservas() {return historialReservas;}
//    public void setHistorialReservas(List<HistorialReservas> historialReservas) {this.historialReservas = historialReservas;}

    public CatalogoProductos getCatalogo() {return catalogo;}
    public void setCatalogo(CatalogoProductos catalogo) {this.catalogo = catalogo;}

    public Alquiler() {}

//    public Alquiler(long idAlquiler, int inventario, Date fechaUpdate, Boolean bl,
//                    List<HistorialReservas> historialReservas, CatalogoProductos catalogo) {
    public Alquiler(long idAlquiler, int inventario, Date fechaUpdate, Boolean bl, CatalogoProductos catalogo) {
        this.idAlquiler = idAlquiler;
        this.inventario = inventario;
        this.fechaUpdate = fechaUpdate;
        this.bl = bl;
        //this.historialReservas = historialReservas;
        this.catalogo = catalogo;
    }
}
