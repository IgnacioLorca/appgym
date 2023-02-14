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
@Table(name = "Producto")
public class Producto {
    @Id
    @Column(name = "id_producto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProducto;
    @Column(name = "nombre", length = 50)
    private String nombre;
    @Column(name = "descripcion", length = 50)
    private String descripcion;
    @Column(name = "cantidad")
    private Long cantidad;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "rutaImagen", length = 50)
    private String rutaImagen;
}
