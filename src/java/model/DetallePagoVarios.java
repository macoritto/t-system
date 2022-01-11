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
 * DetallePagoVarios generated by hbm2java
 */
@Entity
@Table(name="detalle_pago_varios"
    ,schema="public"
)
public class DetallePagoVarios  implements java.io.Serializable {


     private int idDetalle;
     private PagoVarios pagoVarios;
     private Varios varios;
     private double monto;
     private String descripcion;

    public DetallePagoVarios() {
    }

    public DetallePagoVarios(PagoVarios pagoVarios, Varios varios, double monto, String descripcion) {
       this.pagoVarios = pagoVarios;
       this.varios = varios;
       this.monto = monto;
       this.descripcion = descripcion;
    }
   
     @SequenceGenerator(name="generator", sequenceName="detalle_pago_varios_id_detalle_seq")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="id_detalle", nullable=false)
    public int getIdDetalle() {
        return this.idDetalle;
    }
    
    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pago_varios_id_pago_varios", nullable=false)
    public PagoVarios getPagoVarios() {
        return this.pagoVarios;
    }
    
    public void setPagoVarios(PagoVarios pagoVarios) {
        this.pagoVarios = pagoVarios;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="varios_id_varios", nullable=false)
    public Varios getVarios() {
        return this.varios;
    }
    
    public void setVarios(Varios varios) {
        this.varios = varios;
    }

    
    @Column(name="monto", nullable=false, precision=17, scale=17)
    public double getMonto() {
        return this.monto;
    }
    
    public void setMonto(double monto) {
        this.monto = monto;
    }

    
    @Column(name="descripcion", nullable=false, length=50)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }




}

