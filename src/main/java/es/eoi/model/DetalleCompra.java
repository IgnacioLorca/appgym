package es.eoi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "DetalleCompra")
public class DetalleCompra {
    @Id
    @Column(name = "id_detalle")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDetalle;
    @Column(name = "cantidad", length = 60)
    private Long cantidad;
    @Column(name = "precio", length = 60)
    private Double precio;
    @Column(name = "total", length = 60)
    private Double total;

    @ManyToOne
    private Compra compra;

    @ManyToOne
    private Producto producto;
}
