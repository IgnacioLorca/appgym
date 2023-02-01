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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nombreTipo;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tipoCatalogo")
    private Set<Catalogo> catalogos;
}
