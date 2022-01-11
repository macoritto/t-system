package model;
// Generated 19/11/2021 11:27:52 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * DetOrden generated by hbm2java
 */
@Entity
@Table(name="det_orden"
    ,schema="public"
)
public class DetOrden  implements java.io.Serializable {


     private int idDetOrden;
     private DetExtracto detExtracto;
     private OrdenDeCarga ordenDeCarga;
     private String descrip;
     private Double monto;

    public DetOrden() {
    }

	
    public DetOrden(int idDetOrden) {
        this.idDetOrden = idDetOrden;
    }
    public DetOrden(int idDetOrden, DetExtracto detExtracto, OrdenDeCarga ordenDeCarga, String descrip, Double monto) {
       this.idDetOrden = idDetOrden;
       this.detExtracto = detExtracto;
       this.ordenDeCarga = ordenDeCarga;
       this.descrip = descrip;
       this.monto = monto;
    }
   
     @Id 

    
    @Column(name="id_det_orden", unique=true, nullable=false)
    public int getIdDetOrden() {
        return this.idDetOrden;
    }
    
    public void setIdDetOrden(int idDetOrden) {
        this.idDetOrden = idDetOrden;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_det_extracto")
    public DetExtracto getDetExtracto() {
        return this.detExtracto;
    }
    
    public void setDetExtracto(DetExtracto detExtracto) {
        this.detExtracto = detExtracto;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_orden_de")
    public OrdenDeCarga getOrdenDeCarga() {
        return this.ordenDeCarga;
    }
    
    public void setOrdenDeCarga(OrdenDeCarga ordenDeCarga) {
        this.ordenDeCarga = ordenDeCarga;
    }

    
    @Column(name="descrip", length=300)
    public String getDescrip() {
        return this.descrip;
    }
    
    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    
    @Column(name="monto", precision=17, scale=17)
    public Double getMonto() {
        return this.monto;
    }
    
    public void setMonto(Double monto) {
        this.monto = monto;
    }




}

