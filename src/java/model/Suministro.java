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
 * Suministro generated by hbm2java
 */
@Entity
@Table(name="suministro"
    ,schema="public"
)
public class Suministro  implements java.io.Serializable {


     private int idSuministro;
     private Camion camion;
     private Chofer chofer;
     private OrdenDeCarga ordenDeCarga;
     private PrecioSuministro precioSuministro;
     private TipoSuministro tipoSuministro;
     private UnidadProvee unidadProvee;
     private Usuario usuario;
     private Double monto;
     private Double montoTotal;
     private Double litros;
     private Double precioSumi;
     private String estadoPago;
     private String estadoCobro;
     private String descrip;
     private Date fecha;
     private Set pagoSumiDets = new HashSet(0);
     private Set detSumis = new HashSet(0);

    public Suministro() {
    }

    public Suministro(Camion camion, Chofer chofer, OrdenDeCarga ordenDeCarga, PrecioSuministro precioSuministro, TipoSuministro tipoSuministro, UnidadProvee unidadProvee, Usuario usuario, Double monto, Double montoTotal, Double litros, Double precioSumi, String estadoPago, String estadoCobro, String descrip, Date fecha, Set pagoSumiDets, Set detSumis) {
       this.camion = camion;
       this.chofer = chofer;
       this.ordenDeCarga = ordenDeCarga;
       this.precioSuministro = precioSuministro;
       this.tipoSuministro = tipoSuministro;
       this.unidadProvee = unidadProvee;
       this.usuario = usuario;
       this.monto = monto;
       this.montoTotal = montoTotal;
       this.litros = litros;
       this.precioSumi = precioSumi;
       this.estadoPago = estadoPago;
       this.estadoCobro = estadoCobro;
       this.descrip = descrip;
       this.fecha = fecha;
       this.pagoSumiDets = pagoSumiDets;
       this.detSumis = detSumis;
    }
   
     @SequenceGenerator(name="generator", sequenceName="suministro_seq")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="id_suministro", nullable=false)
    public int getIdSuministro() {
        return this.idSuministro;
    }
    
    public void setIdSuministro(int idSuministro) {
        this.idSuministro = idSuministro;
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
    @JoinColumn(name="orden_de_carga")
    public OrdenDeCarga getOrdenDeCarga() {
        return this.ordenDeCarga;
    }
    
    public void setOrdenDeCarga(OrdenDeCarga ordenDeCarga) {
        this.ordenDeCarga = ordenDeCarga;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="precio_id")
    public PrecioSuministro getPrecioSuministro() {
        return this.precioSuministro;
    }
    
    public void setPrecioSuministro(PrecioSuministro precioSuministro) {
        this.precioSuministro = precioSuministro;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="tipo_suministro")
    public TipoSuministro getTipoSuministro() {
        return this.tipoSuministro;
    }
    
    public void setTipoSuministro(TipoSuministro tipoSuministro) {
        this.tipoSuministro = tipoSuministro;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="unidad_id")
    public UnidadProvee getUnidadProvee() {
        return this.unidadProvee;
    }
    
    public void setUnidadProvee(UnidadProvee unidadProvee) {
        this.unidadProvee = unidadProvee;
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

    
    @Column(name="monto_total", precision=17, scale=17)
    public Double getMontoTotal() {
        return this.montoTotal;
    }
    
    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    
    @Column(name="litros", precision=17, scale=17)
    public Double getLitros() {
        return this.litros;
    }
    
    public void setLitros(Double litros) {
        this.litros = litros;
    }

    
    @Column(name="precio_sumi", precision=17, scale=17)
    public Double getPrecioSumi() {
        return this.precioSumi;
    }
    
    public void setPrecioSumi(Double precioSumi) {
        this.precioSumi = precioSumi;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="suministro")
    public Set getPagoSumiDets() {
        return this.pagoSumiDets;
    }
    
    public void setPagoSumiDets(Set pagoSumiDets) {
        this.pagoSumiDets = pagoSumiDets;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="suministro")
    public Set getDetSumis() {
        return this.detSumis;
    }
    
    public void setDetSumis(Set detSumis) {
        this.detSumis = detSumis;
    }




}

