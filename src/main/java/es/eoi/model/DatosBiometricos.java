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
@Table(name = "DatosBiometricos")
public class DatosBiometricos {
    @Id
    @Column(name = "id_datosbio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDatosBio;
    @Column (name ="peso")
    private float peso;
    @Column (name ="altura")
    private float altura;
    @Column (name ="IMC")
    private float IMC;
    @Column (name = "fecha_creacion")
    private Date fechaCreacion;
    @Column (name = "fecha_modif")
    private Date fechaModif;
    @Column (name = "BL")
    private boolean BL;

    @OneToOne(mappedBy = "datosBiometricos")
    private Usuario usuario;
}
