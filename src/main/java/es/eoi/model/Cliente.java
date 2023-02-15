package es.eoi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Cliente {
    @Id
    @Column(name ="id_cliente")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idCliente;
    @Column (name ="nombre",length = 50)
    private String nombre;
    @Column (name ="apellidos",length = 30)
    private String apellido;
    @Column (name ="username",length = 15)
    private String username;
    @Column (name ="direccion",length = 50)
    private String direccion;
    @Column (name ="ciudad",length = 50)
    private String ciudad;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    @Column(name = "fecha_modif")
    private Date fechaModif;
    @Column(name = "BL")
    private boolean BL;


    @OneToOne
    @JoinColumn(name = "id_usuario")
    Usuario usuario;
}
