package es.eoi.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@Entity
@EntityScan
@Table(name = "tblDatosBio")
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
    @Column (name = "fecha_insert")
    private Date fechaInsert;
    @Column (name = "fecha_update")
    private Date fechaUpdate;
    @Column (name = "BL")
    private boolean BL;

    @OneToOne(mappedBy = "datosBiometricos")
    private Usuario usuario;

    public DatosBiometricos(){}

    public DatosBiometricos(long idDatosBio, float peso, float altura, float IMC, Date fechaInsert, Date fechaUpdate, boolean BL) {
        this.idDatosBio = idDatosBio;
        this.peso = peso;
        this.altura = altura;
        this.IMC = IMC;
        this.fechaInsert = fechaInsert;
        this.fechaUpdate = fechaUpdate;
        this.BL = BL;
    }

    public long getIdDatosBio() {
        return idDatosBio;
    }

    public void setIdDatosBio(long idDatosBio) {
        this.idDatosBio = idDatosBio;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getIMC() {
        return IMC;
    }

    public void setIMC(float IMC) {
        this.IMC = IMC;
    }

    public Date getFechaInsert() {
        return fechaInsert;
    }

    public void setFechaInsert(Date fechaInsert) {
        this.fechaInsert = fechaInsert;
    }

    public Date getFechaUpdate() {
        return fechaUpdate;
    }

    public void setFechaUpdate(Date fechaUpdate) {
        this.fechaUpdate = fechaUpdate;
    }

    public boolean isBL() {
        return BL;
    }

    public void setBL(boolean BL) {
        this.BL = BL;
    }

    @Override
    public String toString() {
        return "DatosBiometricos{" +
                "idDatosBio=" + idDatosBio +
                ", peso=" + peso +
                ", altura=" + altura +
                ", IMC=" + IMC +
                ", fechaInsert=" + fechaInsert +
                ", fechaUpdate=" + fechaUpdate +
                ", BL=" + BL +
                '}';
    }
}
