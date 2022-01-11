package model;
// Generated 22/10/2021 08:36:07 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AdeSuministro generated by hbm2java
 */
@Entity
@Table(name="ade_suministro"
    ,schema="public"
)
public class AdeSuministro  implements java.io.Serializable {


     private int idAde;
     private ProveeCliente proveeCliente;
     private Usuario usuario;
     private String descrip;
     private Date fecha;
     private String estadoPago;
     private String estadoCobro;
     private Double monto;
     private Set pagoAdeDets = new HashSet(0);

    public AdeSuministro() {
    }

    public AdeSuministro(ProveeCliente proveeCliente, Usuario usuario, String descrip, Date fecha, String estadoPago, String estadoCobro, Double monto, Set pagoAdeDets) {
       this.proveeCliente = proveeCliente;
       this.usuario = usuario;
       this.descrip = descrip;
       this.fecha = fecha;
       this.estadoPago = estadoPago;
       this.estadoCobro = estadoCobro;
       this.monto = monto;
       this.pagoAdeDets = pagoAdeDets;
    }
   
     @SequenceGenerator(name="generator", sequenceName="ade_sumi_seq")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="id_ade", nullable=false)
    public int getIdAde() {
        return this.idAde;
    }
    
    public void setIdAde(int idAde) {
        this.idAde = idAde;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="proveedor_id")
    public ProveeCliente getProveeCliente() {
        return this.proveeCliente;
    }
    
    public void setProveeCliente(ProveeCliente proveeCliente) {
        this.proveeCliente = proveeCliente;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="usuario_id")
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    @Column(name="descrip", length=100)
    public String getDescrip() {
        return this.descrip;
    }
    
    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha", length=13)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    @Column(name="estado_pago", length=30)
    public String getEstadoPago() {
        return this.estadoPago;
    }
    
    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    
    @Column(name="estado_cobro", length=30)
    public String getEstadoCobro() {
        return this.estadoCobro;
    }
    
    public void setEstadoCobro(String estadoCobro) {
        this.estadoCobro = estadoCobro;
    }

    
    @Column(name="monto", precision=17, scale=17)
    public Double getMonto() {
        return this.monto;
    }
    
    public void setMonto(Double monto) {
        this.monto = monto;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="adeSuministro")
    public Set getPagoAdeDets() {
        return this.pagoAdeDets;
    }
    
    public void setPagoAdeDets(Set pagoAdeDets) {
        this.pagoAdeDets = pagoAdeDets;
    }




}


