package model;
// Generated 22/10/2021 08:36:07 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * PagoAdeDet generated by hbm2java
 */
@Entity
@Table(name="pago_ade_det"
    ,schema="public"
)
public class PagoAdeDet  implements java.io.Serializable {


     private int idPagoDet;
     private AdeSuministro adeSuministro;
     private PagoSuministro pagoSuministro;
     private Usuario usuario;
     private Double monto;
     private String descrip;

    public PagoAdeDet() {
    }

    public PagoAdeDet(AdeSuministro adeSuministro, PagoSuministro pagoSuministro, Usuario usuario, Double monto, String descrip) {
       this.adeSuministro = adeSuministro;
       this.pagoSuministro = pagoSuministro;
       this.usuario = usuario;
       this.monto = monto;
       this.descrip = descrip;
    }
   
     @SequenceGenerator(name="generator", sequenceName="pagoade_seq")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="id_pago_det", nullable=false)
    public int getIdPagoDet() {
        return this.idPagoDet;
    }
    
    public void setIdPagoDet(int idPagoDet) {
        this.idPagoDet = idPagoDet;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ade_id")
    public AdeSuministro getAdeSuministro() {
        return this.adeSuministro;
    }
    
    public void setAdeSuministro(AdeSuministro adeSuministro) {
        this.adeSuministro = adeSuministro;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pago_sumi_id")
    public PagoSuministro getPagoSuministro() {
        return this.pagoSuministro;
    }
    
    public void setPagoSuministro(PagoSuministro pagoSuministro) {
        this.pagoSuministro = pagoSuministro;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="usuario_id")
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    @Column(name="monto", precision=17, scale=17)
    public Double getMonto() {
        return this.monto;
    }
    
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    
    @Column(name="descrip", length=100)
    public String getDescrip() {
        return this.descrip;
    }
    
    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }




}


