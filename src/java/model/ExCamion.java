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
 * ExCamion generated by hbm2java
 */
@Entity
@Table(name="ex_camion"
    ,schema="public"
)
public class ExCamion  implements java.io.Serializable {


     private int id;
     private Camion camion;
     private Extracto extracto;

    public ExCamion() {
    }

    public ExCamion(Camion camion, Extracto extracto) {
       this.camion = camion;
       this.extracto = extracto;
    }
   
     @SequenceGenerator(name="generator", sequenceName="ex_camion_id_seq")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="id", nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="camion_chapa_camion", nullable=false)
    public Camion getCamion() {
        return this.camion;
    }
    
    public void setCamion(Camion camion) {
        this.camion = camion;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="extracto_id_liquidacion", nullable=false)
    public Extracto getExtracto() {
        return this.extracto;
    }
    
    public void setExtracto(Extracto extracto) {
        this.extracto = extracto;
    }




}

