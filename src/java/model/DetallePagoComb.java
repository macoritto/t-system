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
 * DetallePagoComb generated by hbm2java
 */
@Entity
@Table(name="detalle_pago_comb"
    ,schema="public"
)
public class DetallePagoComb  implements java.io.Serializable {


     private int idDetalle;
     private Combustible combustible;
     private PagoCombustible pagoCombustible;
     private double monto;
     private double litros;
     private double precioComb;

    public DetallePagoComb() {
    }

    public DetallePagoComb(Combustible combustible, PagoCombustible pagoCombustible, double monto, double litros, double precioComb) {
       this.combustible = combustible;
       this.pagoCombustible = pagoCombustible;
       this.monto = monto;
       this.litros = litros;
       this.precioComb = precioComb;
    }
   
     @SequenceGenerator(name="generator", sequenceName="detalle_pago_comb_id_detalle_seq")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="id_detalle", nullable=false)
    public int getIdDetalle() {
        return this.idDetalle;
    }
    
    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="combustible_id_combustible", nullable=false)
    public Combustible getCombustible() {
        return this.combustible;
    }
    
    public void setCombustible(Combustible combustible) {
        this.combustible = combustible;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pago_combustible_id_pago_combustible", nullable=false)
    public PagoCombustible getPagoCombustible() {
        return this.pagoCombustible;
    }
    
    public void setPagoCombustible(PagoCombustible pagoCombustible) {
        this.pagoCombustible = pagoCombustible;
    }

    
    @Column(name="monto", nullable=false, precision=17, scale=17)
    public double getMonto() {
        return this.monto;
    }
    
    public void setMonto(double monto) {
        this.monto = monto;
    }

    
    @Column(name="litros", nullable=false, precision=17, scale=17)
    public double getLitros() {
        return this.litros;
    }
    
    public void setLitros(double litros) {
        this.litros = litros;
    }

    
    @Column(name="precio_comb", nullable=false, precision=17, scale=17)
    public double getPrecioComb() {
        return this.precioComb;
    }
    
    public void setPrecioComb(double precioComb) {
        this.precioComb = precioComb;
    }




}


