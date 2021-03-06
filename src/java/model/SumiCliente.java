package model;
// Generated 22/10/2021 08:36:07 by Hibernate Tools 4.3.1


import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SumiCliente generated by hbm2java
 */
@Entity
@Table(name="sumi_cliente"
    ,schema="public"
)
public class SumiCliente  implements java.io.Serializable {


     private int idSumiCli;
     private Camion camion;
     private Chofer chofer;
     private Cliente cliente;
     private OrdenDeCarga ordenDeCarga;
     private Usuario usuario;
     private Double monto;
     private String descrip;
     private String estadoPago;
     private String estadoCobro;
     private Date fecha;

    public SumiCliente() {
    }

    public SumiCliente(Camion camion, Chofer chofer, Cliente cliente, OrdenDeCarga ordenDeCarga, Usuario usuario, Double monto, String descrip, String estadoPago, String estadoCobro, Date fecha) {
       this.camion = camion;
       this.chofer = chofer;
       this.cliente = cliente;
       this.ordenDeCarga = ordenDeCarga;
       this.usuario = usuario;
       this.monto = monto;
       this.descrip = descrip;
       this.estadoPago = estadoPago;
       this.estadoCobro = estadoCobro;
       this.fecha = fecha;
    }
   
     @SequenceGenerator(name="generator", sequenceName="sumi_cliente_seq")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="id_sumi_cli", nullable=false)
    public int getIdSumiCli() {
        return this.idSumiCli;
    }
    
    public void setIdSumiCli(int idSumiCli) {
        this.idSumiCli = idSumiCli;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="chapa_camion")
    public Camion getCamion() {
        return this.camion;
    }
    
    public void setCamion(Camion camion) {
        this.camion = camion;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="chofer_id")
    public Chofer getChofer() {
        return this.chofer;
    }
    
    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cliente_id")
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="orden_de_carga")
    public OrdenDeCarga getOrdenDeCarga() {
        return this.ordenDeCarga;
    }
    
    public void setOrdenDeCarga(OrdenDeCarga ordenDeCarga) {
        this.ordenDeCarga = ordenDeCarga;
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

    
    @Column(name="descrip", length=200)
    public String getDescrip() {
        return this.descrip;
    }
    
    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    
    @Column(name="estado_pago", length=20)
    public String getEstadoPago() {
        return this.estadoPago;
    }
    
    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    
    @Column(name="estado_cobro", length=20)
    public String getEstadoCobro() {
        return this.estadoCobro;
    }
    
    public void setEstadoCobro(String estadoCobro) {
        this.estadoCobro = estadoCobro;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha", length=13)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }




}


