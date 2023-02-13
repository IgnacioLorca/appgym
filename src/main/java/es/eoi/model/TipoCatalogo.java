package es.eoi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "TipoCatalogo")
public class TipoCatalogo {
    @Id
    @Column(name="id_tipoCatalogo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoCatalogo;
    @Column(nullable = false)
    private String nombreTipo;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tipoCatalogo")
    private Set<Catalogo> catalogos;
}
