package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;
import java.util.List;

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
    @JoinColumn(name = "id_catalogo")
    CatalogoProductos catalogo;

    @OneToMany(mappedBy = "clases")
    List<ClasesReservas> clasesReservadas;

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

    public CatalogoProductos getCatalogo() {return catalogo;}
    public void setCatalogo(CatalogoProductos catalogo) {this.catalogo = catalogo;}

    public List<ClasesReservas> getClasesReservadas() {
        return clasesReservadas;
    }
    public void setClasesReservadas(List<ClasesReservas> clasesReservadas) {
        this.clasesReservadas = clasesReservadas;
    }

    public ClasesPrecio() {}

    public ClasesPrecio(long idClases, Float precioClases, Date fechaUpdate, String diaSemana, int cupoMaximo,
                        CatalogoProductos catalogo, List<ClasesReservas> clasesReservadas) {
        this.idClases = idClases;
        this.precioClases = precioClases;
        this.fechaUpdate = fechaUpdate;
        this.diaSemana = diaSemana;
        this.cupoMaximo = cupoMaximo;
        this.catalogo = catalogo;
        this.clasesReservadas = clasesReservadas;
    }
}
