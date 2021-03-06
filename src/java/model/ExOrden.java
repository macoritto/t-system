package model;
// Generated 23/11/2021 15:18:07 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ExOrden generated by hbm2java
 */
@Entity
@Table(name="ex_orden"
    ,schema="public"
)
public class ExOrden  implements java.io.Serializable {


     private int idExOrden;
     private Extracto extracto;
     private OrdenDeCarga ordenDeCarga;
     private String descrip;

    public ExOrden() {
    }

	
    public ExOrden(int idExOrden) {
        this.idExOrden = idExOrden;
    }
    public ExOrden(int idExOrden, Extracto extracto, OrdenDeCarga ordenDeCarga, String descrip) {
       this.idExOrden = idExOrden;
       this.extracto = extracto;
       this.ordenDeCarga = ordenDeCarga;
       this.descrip = descrip;
    }
   
     @Id 

    
    @Column(name="id_ex_orden", unique=true, nullable=false)
    public int getIdExOrden() {
        return this.idExOrden;
    }
    
    public void setIdExOrden(int idExOrden) {
        this.idExOrden = idExOrden;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="extracto_id")
    public Extracto getExtracto() {
        return this.extracto;
    }
    
    public void setExtracto(Extracto extracto) {
        this.extracto = extracto;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="orden_id")
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




}


