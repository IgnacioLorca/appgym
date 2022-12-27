package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@Entity
@EntityScan
@Table(name = "tblClasesPrecio")
public class ClasesPrecio {

    @Id
    @Column(name = "id_clases")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idClases;
    @Column(name = "precio_clases")
    private Float precioClases;
    @Column(name = "fecha_update")
    private Date fechaUpdate;
    @Column(name = "dia_semana", length = 10)
    private String diaSemana;
    @Column(name = "cupo_maximo")
    private int cupoMaximo;

    @ManyToOne
    @JoinColumn(name = "id_historial")
    HistorialReservas historialReservas;

    @ManyToOne
    @JoinColumn(name = "id_catalogo")
    CatalogoProductos catalogo;

    public long getIdClases() {return idClases;}
    public void setIdClases(long idClases) {this.idClases = idClases;}

    public Float getPrecioClases() {return precioClases;}
    public void setPrecioClases(Float precioClases) {this.precioClases = precioClases;}

    public Date getFechaUpdate() {return fechaUpdate;}
    public void setFechaUpdate(Date fechaUpdate) {this.fechaUpdate = fechaUpdate;}

    public String getDiaSemana() {return diaSemana;}
    public void setDiaSemana(String diaSemana) {this.diaSemana = diaSemana;}

    public int getCupoMaximo() {return cupoMaximo;}
    public void setCupoMaximo(int cupoMaximo) {this.cupoMaximo = cupoMaximo;}

    public HistorialReservas getHistorialReservas() {return historialReservas;}
    public void setHistorialReservas(HistorialReservas historialReservas) {
        this.historialReservas = historialReservas;
    }

    public CatalogoProductos getCatalogo() {return catalogo;}
    public void setCatalogo(CatalogoProductos catalogo) {this.catalogo = catalogo;}

    public ClasesPrecio() {}

    public ClasesPrecio(long idClases, Float precioClases, Date fechaUpdate, String diaSemana,
                        int cupoMaximo, HistorialReservas historialReservas, CatalogoProductos catalogo) {
        this.idClases = idClases;
        this.precioClases = precioClases;
        this.fechaUpdate = fechaUpdate;
        this.diaSemana = diaSemana;
        this.cupoMaximo = cupoMaximo;
        this.historialReservas = historialReservas;
        this.catalogo = catalogo;
    }
}
