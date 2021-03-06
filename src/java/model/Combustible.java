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
 * Combustible generated by hbm2java
 */
@Entity
@Table(name="combustible"
    ,schema="public"
)
public class Combustible  implements java.io.Serializable {


     private int idCombustible;
     private Usuario usuario;
     private ValeCombustible valeCombustible;
     private Viatico viatico;
     private String NBoleta;
     private Date fecha;
     private String descripcion;
     private double precio;
     private double litros;
     private double montoTotal;
     private double extras;
     private String estadoCobro;
     private String estadoPago;
     private double viatico_1;
     private double montoComb;
     private Set detallePagoCombs = new HashSet(0);

    public Combustible() {
    }

	
    public Combustible(Usuario usuario, ValeCombustible valeCombustible, Viatico viatico, String NBoleta, Date fecha, double precio, double litros, double montoTotal, double extras, String estadoCobro, String estadoPago, double viatico_1, double montoComb) {
        this.usuario = usuario;
        this.valeCombustible = valeCombustible;
        this.viatico = viatico;
        this.NBoleta = NBoleta;
        this.fecha = fecha;
        this.precio = precio;
        this.litros = litros;
        this.montoTotal = montoTotal;
        this.extras = extras;
        this.estadoCobro = estadoCobro;
        this.estadoPago = estadoPago;
        this.viatico_1 = viatico_1;
        this.montoComb = montoComb;
    }
    public Combustible(Usuario usuario, ValeCombustible valeCombustible, Viatico viatico, String NBoleta, Date fecha, String descripcion, double precio, double litros, double montoTotal, double extras, String estadoCobro, String estadoPago, double viatico_1, double montoComb, Set detallePagoCombs) {
       this.usuario = usuario;
       this.valeCombustible = valeCombustible;
       this.viatico = viatico;
       this.NBoleta = NBoleta;
       this.fecha = fecha;
       this.descripcion = descripcion;
       this.precio = precio;
       this.litros = litros;
       this.montoTotal = montoTotal;
       this.extras = extras;
       this.estadoCobro = estadoCobro;
       this.estadoPago = estadoPago;
       this.viatico_1 = viatico_1;
       this.montoComb = montoComb;
       this.detallePagoCombs = detallePagoCombs;
    }
   
     @SequenceGenerator(name="generator", sequenceName="combustible_id_combustible_seq")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="id_combustible", nullable=false)
    public int getIdCombustible() {
        return this.idCombustible;
    }
    
    public void setIdCombustible(int idCombustible) {
        this.idCombustible = idCombustible;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="usuario_id", nullable=false)
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="vale_combustible_id_vale_combustible", nullable=false)
    public ValeCombustible getValeCombustible() {
        return this.valeCombustible;
    }
    
    public void setValeCombustible(ValeCombustible valeCombustible) {
        this.valeCombustible = valeCombustible;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="viatico_id_viatico", nullable=false)
    public Viatico getViatico() {
        return this.viatico;
    }
    
    public void setViatico(Viatico viatico) {
        this.viatico = viatico;
    }

    
    @Column(name="n_boleta", nullable=false, length=20)
    public String getNBoleta() {
        return this.NBoleta;
    }
    
    public void setNBoleta(String NBoleta) {
        this.NBoleta = NBoleta;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha", nullable=false, length=13)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    @Column(name="descripcion", length=50)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Column(name="precio", nullable=false, precision=17, scale=17)
    public double getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
    @Column(name="litros", nullable=false, precision=17, scale=17)
    public double getLitros() {
        return this.litros;
    }
    
    public void setLitros(double litros) {
        this.litros = litros;
    }

    
    @Column(name="monto_total", nullable=false, precision=17, scale=17)
    public double getMontoTotal() {
        return this.montoTotal;
    }
    
    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    
    @Column(name="extras", nullable=false, precision=17, scale=17)
    public double getExtras() {
        return this.extras;
    }
    
    public void setExtras(double extras) {
        this.extras = extras;
    }

    
    @Column(name="estado_cobro", nullable=false, length=15)
    public String getEstadoCobro() {
        return this.estadoCobro;
    }
    
    public void setEstadoCobro(String estadoCobro) {
        this.estadoCobro = estadoCobro;
    }

    
    @Column(name="estado_pago", nullable=false, length=20)
    public String getEstadoPago() {
        return this.estadoPago;
    }
    
    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    
    @Column(name="viatico", nullable=false, precision=17, scale=17)
    public double getViatico_1() {
        return this.viatico_1;
    }
    
    public void setViatico_1(double viatico_1) {
        this.viatico_1 = viatico_1;
    }

    
    @Column(name="monto_comb", nullable=false, precision=17, scale=17)
    public double getMontoComb() {
        return this.montoComb;
    }
    
    public void setMontoComb(double montoComb) {
        this.montoComb = montoComb;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="combustible")
    public Set getDetallePagoCombs() {
        return this.detallePagoCombs;
    }
    
    public void setDetallePagoCombs(Set detallePagoCombs) {
        this.detallePagoCombs = detallePagoCombs;
    }




}


