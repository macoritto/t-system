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
 * Viaje generated by hbm2java
 */
@Entity
@Table(name="viaje"
    ,schema="public"
)
public class Viaje  implements java.io.Serializable {


     private int idViaje;
     private Chofer chofer;
     private OrdenDeCarga ordenDeCarga;
     private PrecioFlete precioFlete;
     private Seguro seguro;
     private Tolerancia tolerancia;
     private Usuario usuario;
     private String remisionOrigen;
     private String remisionDestino;
     private int pesoOrigen;
     private int pesoDestino;
     private Date fechaOrigen;
     private Date fechaDestino;
     private double precioPago;
     private double precioCobro;
     private String estadoPago;
     private String estadoCobro;
     private String descripcion;
     private double montoPagar;
     private double montoCobrar;
     private double montofaltante;
     private double montoBruto;
     private double dif;
     private double falCobrar;
     private double montoseguro;
     private double precioiva;
     private double montoconiva;
     private String estadocontra;
     private Set contrasenhas = new HashSet(0);
     private Set detPagoFletes = new HashSet(0);

    public Viaje() {
    }

	
    public Viaje(Chofer chofer, OrdenDeCarga ordenDeCarga, PrecioFlete precioFlete, Seguro seguro, Tolerancia tolerancia, Usuario usuario, String remisionOrigen, String remisionDestino, int pesoOrigen, int pesoDestino, Date fechaOrigen, Date fechaDestino, double precioPago, double precioCobro, String estadoPago, String estadoCobro, String descripcion, double montoPagar, double montoCobrar, double montofaltante, double montoBruto, double dif, double falCobrar, double montoseguro, double precioiva, double montoconiva, String estadocontra) {
        this.chofer = chofer;
        this.ordenDeCarga = ordenDeCarga;
        this.precioFlete = precioFlete;
        this.seguro = seguro;
        this.tolerancia = tolerancia;
        this.usuario = usuario;
        this.remisionOrigen = remisionOrigen;
        this.remisionDestino = remisionDestino;
        this.pesoOrigen = pesoOrigen;
        this.pesoDestino = pesoDestino;
        this.fechaOrigen = fechaOrigen;
        this.fechaDestino = fechaDestino;
        this.precioPago = precioPago;
        this.precioCobro = precioCobro;
        this.estadoPago = estadoPago;
        this.estadoCobro = estadoCobro;
        this.descripcion = descripcion;
        this.montoPagar = montoPagar;
        this.montoCobrar = montoCobrar;
        this.montofaltante = montofaltante;
        this.montoBruto = montoBruto;
        this.dif = dif;
        this.falCobrar = falCobrar;
        this.montoseguro = montoseguro;
        this.estadocontra = estadocontra;
        this.precioiva=precioiva;
        this.montoconiva=montoconiva;
    }
    public Viaje(Chofer chofer, OrdenDeCarga ordenDeCarga, PrecioFlete precioFlete, Seguro seguro, Tolerancia tolerancia, Usuario usuario, String remisionOrigen, String remisionDestino, int pesoOrigen, int pesoDestino, Date fechaOrigen, Date fechaDestino, double precioPago, double precioCobro, String estadoPago, String estadoCobro, String descripcion, double montoPagar, double montoCobrar, double montofaltante, double montoBruto, double dif, double falCobrar, double montoseguro,double precioiva,double montoconiva, String estadocontra, Set contrasenhas, Set detPagoFletes) {
       this.chofer = chofer;
       this.ordenDeCarga = ordenDeCarga;
       this.precioFlete = precioFlete;
       this.seguro = seguro;
       this.tolerancia = tolerancia;
       this.usuario = usuario;
       this.remisionOrigen = remisionOrigen;
       this.remisionDestino = remisionDestino;
       this.pesoOrigen = pesoOrigen;
       this.pesoDestino = pesoDestino;
       this.fechaOrigen = fechaOrigen;
       this.fechaDestino = fechaDestino;
       this.precioPago = precioPago;
       this.precioCobro = precioCobro;
       this.estadoPago = estadoPago;
       this.estadoCobro = estadoCobro;
       this.descripcion = descripcion;
       this.montoPagar = montoPagar;
       this.montoCobrar = montoCobrar;
       this.montofaltante = montofaltante;
       this.montoBruto = montoBruto;
       this.dif = dif;
       this.falCobrar = falCobrar;
       this.montoseguro = montoseguro;
       this.estadocontra = estadocontra;
       this.contrasenhas = contrasenhas;
       this.detPagoFletes = detPagoFletes;
       this.precioiva=precioiva;
       this.montoconiva=montoconiva;
    }
   
     @SequenceGenerator(name="generator", sequenceName="viaje_id_viaje_seq")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="id_viaje", nullable=false)
    public int getIdViaje() {
        return this.idViaje;
    }
    
    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="chofer_id_chofer", nullable=false)
    public Chofer getChofer() {
        return this.chofer;
    }
    
    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="orden_de_carga_id_orden_de_carga", nullable=false)
    public OrdenDeCarga getOrdenDeCarga() {
        return this.ordenDeCarga;
    }
    
    public void setOrdenDeCarga(OrdenDeCarga ordenDeCarga) {
        this.ordenDeCarga = ordenDeCarga;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="precio_flete_id_precio_flete", nullable=false)
    public PrecioFlete getPrecioFlete() {
        return this.precioFlete;
    }
    
    public void setPrecioFlete(PrecioFlete precioFlete) {
        this.precioFlete = precioFlete;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="seguro_id_seguro", nullable=false)
    public Seguro getSeguro() {
        return this.seguro;
    }
    
    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="tolerancia_id_tolerancia", nullable=false)
    public Tolerancia getTolerancia() {
        return this.tolerancia;
    }
    
    public void setTolerancia(Tolerancia tolerancia) {
        this.tolerancia = tolerancia;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="usuario_id", nullable=false)
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    @Column(name="remision_origen", nullable=false, length=20)
    public String getRemisionOrigen() {
        return this.remisionOrigen;
    }
    
    public void setRemisionOrigen(String remisionOrigen) {
        this.remisionOrigen = remisionOrigen;
    }

    
    @Column(name="remision_destino", nullable=false, length=20)
    public String getRemisionDestino() {
        return this.remisionDestino;
    }
    
    public void setRemisionDestino(String remisionDestino) {
        this.remisionDestino = remisionDestino;
    }

    
    @Column(name="peso_origen", nullable=false)
    public int getPesoOrigen() {
        return this.pesoOrigen;
    }
    
    public void setPesoOrigen(int pesoOrigen) {
        this.pesoOrigen = pesoOrigen;
    }

    
    @Column(name="peso_destino", nullable=false)
    public int getPesoDestino() {
        return this.pesoDestino;
    }
    
    public void setPesoDestino(int pesoDestino) {
        this.pesoDestino = pesoDestino;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha_origen", nullable=false, length=13)
    public Date getFechaOrigen() {
        return this.fechaOrigen;
    }
    
    public void setFechaOrigen(Date fechaOrigen) {
        this.fechaOrigen = fechaOrigen;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha_destino", nullable=false, length=13)
    public Date getFechaDestino() {
        return this.fechaDestino;
    }
    
    public void setFechaDestino(Date fechaDestino) {
        this.fechaDestino = fechaDestino;
    }

    
    @Column(name="precio_pago", nullable=false, precision=17, scale=17)
    public double getPrecioPago() {
        return this.precioPago;
    }
    
    public void setPrecioPago(double precioPago) {
        this.precioPago = precioPago;
    }

    
    @Column(name="precio_cobro", nullable=false, precision=17, scale=17)
    public double getPrecioCobro() {
        return this.precioCobro;
    }
    
    public void setPrecioCobro(double precioCobro) {
        this.precioCobro = precioCobro;
    }

    
    @Column(name="estado_pago", nullable=false, length=15)
    public String getEstadoPago() {
        return this.estadoPago;
    }
    
    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    
    @Column(name="estado_cobro", nullable=false, length=15)
    public String getEstadoCobro() {
        return this.estadoCobro;
    }
    
    public void setEstadoCobro(String estadoCobro) {
        this.estadoCobro = estadoCobro;
    }

    
    @Column(name="descripcion", nullable=false, length=50)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Column(name="monto_pagar", nullable=false, precision=17, scale=17)
    public double getMontoPagar() {
        return this.montoPagar;
    }
    
    public void setMontoPagar(double montoPagar) {
        this.montoPagar = montoPagar;
    }

    
    @Column(name="monto_cobrar", nullable=false, precision=17, scale=17)
    public double getMontoCobrar() {
        return this.montoCobrar;
    }
    
    public void setMontoCobrar(double montoCobrar) {
        this.montoCobrar = montoCobrar;
    }

    
    @Column(name="montofaltante", nullable=false, precision=17, scale=17)
    public double getMontofaltante() {
        return this.montofaltante;
    }
    
    public void setMontofaltante(double montofaltante) {
        this.montofaltante = montofaltante;
    }

    
    @Column(name="monto_bruto", nullable=false, precision=17, scale=17)
    public double getMontoBruto() {
        return this.montoBruto;
    }
    
    public void setMontoBruto(double montoBruto) {
        this.montoBruto = montoBruto;
    }

    
    @Column(name="dif", nullable=false, precision=17, scale=17)
    public double getDif() {
        return this.dif;
    }
    
    public void setDif(double dif) {
        this.dif = dif;
    }

    
    @Column(name="fal_cobrar", nullable=false, precision=17, scale=17)
    public double getFalCobrar() {
        return this.falCobrar;
    }
    
    public void setFalCobrar(double falCobrar) {
        this.falCobrar = falCobrar;
    }

    
    @Column(name="montoseguro", nullable=false, precision=17, scale=17)
    public double getMontoseguro() {
        return this.montoseguro;
    }
    
    public void setMontoseguro(double montoseguro) {
        this.montoseguro = montoseguro;
    }
    @Column(name="precioiva", nullable=false, precision=17, scale=17)
    public double getPrecioiva() {
        return precioiva;
    }

    public void setPrecioiva(double precioiva) {
        this.precioiva = precioiva;
    }
    @Column(name="montoconiva", nullable=false, precision=17, scale=17)
    public double getMontoconiva() {
        return montoconiva;
    }

    public void setMontoconiva(double montoconiva) {
        this.montoconiva = montoconiva;
    }

    
    @Column(name="estadocontra", nullable=false, length=50)
    public String getEstadocontra() {
        return this.estadocontra;
    }
    
    public void setEstadocontra(String estadocontra) {
        this.estadocontra = estadocontra;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="viaje")
    public Set getContrasenhas() {
        return this.contrasenhas;
    }
    
    public void setContrasenhas(Set contrasenhas) {
        this.contrasenhas = contrasenhas;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="viaje")
    public Set getDetPagoFletes() {
        return this.detPagoFletes;
    }
    
    public void setDetPagoFletes(Set detPagoFletes) {
        this.detPagoFletes = detPagoFletes;
    }




}

