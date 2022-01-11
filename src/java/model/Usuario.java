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
 * Usuario generated by hbm2java
 */
@Entity
@Table(name="usuario"
    ,schema="public"
)
public class Usuario  implements java.io.Serializable {


     private int id;
     private Rol rol;
     private String usuario;
     private String clave;
     private String email;
     private int estado;
     private String usuariocreacion;
     private Date fechacreacion;
     private String usuariomodificacion;
     private Date fechamodificacion;
     private Set valeVarioses = new HashSet(0);
     private Set varioses = new HashSet(0);
     private Set viajes = new HashSet(0);
     private Set pagoFletes = new HashSet(0);
     private Set precioFletes = new HashSet(0);
     private Set adelantoChofers = new HashSet(0);
     private Set combustibles = new HashSet(0);
     private Set valeCombustibles = new HashSet(0);
     private Set pagoCombustibles = new HashSet(0);
     private Set viaticos = new HashSet(0);
     private Set creditos = new HashSet(0);
     private Set extractocs = new HashSet(0);
     private Set ordenDeCargas = new HashSet(0);
     private Set pagoVarioses = new HashSet(0);
     private Set contrasenhas = new HashSet(0);
     private Set extractos = new HashSet(0);

    public Usuario() {
    }

	
    public Usuario(Rol rol, String usuario, String clave, String email, int estado, String usuariocreacion, Date fechacreacion, String usuariomodificacion, Date fechamodificacion) {
        this.rol = rol;
        this.usuario = usuario;
        this.clave = clave;
        this.email = email;
        this.estado = estado;
        this.usuariocreacion = usuariocreacion;
        this.fechacreacion = fechacreacion;
        this.usuariomodificacion = usuariomodificacion;
        this.fechamodificacion = fechamodificacion;
    }
    public Usuario(Rol rol, String usuario, String clave, String email, int estado, String usuariocreacion, Date fechacreacion, String usuariomodificacion, Date fechamodificacion, Set valeVarioses, Set varioses, Set viajes, Set pagoFletes, Set precioFletes, Set adelantoChofers, Set combustibles, Set valeCombustibles, Set pagoCombustibles, Set viaticos, Set creditos, Set extractocs, Set ordenDeCargas, Set pagoVarioses, Set contrasenhas, Set extractos) {
       this.rol = rol;
       this.usuario = usuario;
       this.clave = clave;
       this.email = email;
       this.estado = estado;
       this.usuariocreacion = usuariocreacion;
       this.fechacreacion = fechacreacion;
       this.usuariomodificacion = usuariomodificacion;
       this.fechamodificacion = fechamodificacion;
       this.valeVarioses = valeVarioses;
       this.varioses = varioses;
       this.viajes = viajes;
       this.pagoFletes = pagoFletes;
       this.precioFletes = precioFletes;
       this.adelantoChofers = adelantoChofers;
       this.combustibles = combustibles;
       this.valeCombustibles = valeCombustibles;
       this.pagoCombustibles = pagoCombustibles;
       this.viaticos = viaticos;
       this.creditos = creditos;
       this.extractocs = extractocs;
       this.ordenDeCargas = ordenDeCargas;
       this.pagoVarioses = pagoVarioses;
       this.contrasenhas = contrasenhas;
       this.extractos = extractos;
    }
   
     @SequenceGenerator(name="generator", sequenceName="usuario_id_seq")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="id", nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rol_id", nullable=false)
    public Rol getRol() {
        return this.rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    
    @Column(name="usuario", nullable=false, length=20)
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
    @Column(name="clave", nullable=false, length=32)
    public String getClave() {
        return this.clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }

    
    @Column(name="email", nullable=false, length=60)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="estado", nullable=false)
    public int getEstado() {
        return this.estado;
    }
    
    public void setEstado(int estado) {
        this.estado = estado;
    }

    
    @Column(name="usuariocreacion", nullable=false, length=25)
    public String getUsuariocreacion() {
        return this.usuariocreacion;
    }
    
    public void setUsuariocreacion(String usuariocreacion) {
        this.usuariocreacion = usuariocreacion;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fechacreacion", nullable=false, length=13)
    public Date getFechacreacion() {
        return this.fechacreacion;
    }
    
    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    
    @Column(name="usuariomodificacion", nullable=false, length=25)
    public String getUsuariomodificacion() {
        return this.usuariomodificacion;
    }
    
    public void setUsuariomodificacion(String usuariomodificacion) {
        this.usuariomodificacion = usuariomodificacion;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fechamodificacion", nullable=false, length=13)
    public Date getFechamodificacion() {
        return this.fechamodificacion;
    }
    
    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getValeVarioses() {
        return this.valeVarioses;
    }
    
    public void setValeVarioses(Set valeVarioses) {
        this.valeVarioses = valeVarioses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getVarioses() {
        return this.varioses;
    }
    
    public void setVarioses(Set varioses) {
        this.varioses = varioses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getViajes() {
        return this.viajes;
    }
    
    public void setViajes(Set viajes) {
        this.viajes = viajes;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getPagoFletes() {
        return this.pagoFletes;
    }
    
    public void setPagoFletes(Set pagoFletes) {
        this.pagoFletes = pagoFletes;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getPrecioFletes() {
        return this.precioFletes;
    }
    
    public void setPrecioFletes(Set precioFletes) {
        this.precioFletes = precioFletes;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getAdelantoChofers() {
        return this.adelantoChofers;
    }
    
    public void setAdelantoChofers(Set adelantoChofers) {
        this.adelantoChofers = adelantoChofers;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getCombustibles() {
        return this.combustibles;
    }
    
    public void setCombustibles(Set combustibles) {
        this.combustibles = combustibles;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getValeCombustibles() {
        return this.valeCombustibles;
    }
    
    public void setValeCombustibles(Set valeCombustibles) {
        this.valeCombustibles = valeCombustibles;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getPagoCombustibles() {
        return this.pagoCombustibles;
    }
    
    public void setPagoCombustibles(Set pagoCombustibles) {
        this.pagoCombustibles = pagoCombustibles;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getViaticos() {
        return this.viaticos;
    }
    
    public void setViaticos(Set viaticos) {
        this.viaticos = viaticos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getCreditos() {
        return this.creditos;
    }
    
    public void setCreditos(Set creditos) {
        this.creditos = creditos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getExtractocs() {
        return this.extractocs;
    }
    
    public void setExtractocs(Set extractocs) {
        this.extractocs = extractocs;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getOrdenDeCargas() {
        return this.ordenDeCargas;
    }
    
    public void setOrdenDeCargas(Set ordenDeCargas) {
        this.ordenDeCargas = ordenDeCargas;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getPagoVarioses() {
        return this.pagoVarioses;
    }
    
    public void setPagoVarioses(Set pagoVarioses) {
        this.pagoVarioses = pagoVarioses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getContrasenhas() {
        return this.contrasenhas;
    }
    
    public void setContrasenhas(Set contrasenhas) {
        this.contrasenhas = contrasenhas;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getExtractos() {
        return this.extractos;
    }
    
    public void setExtractos(Set extractos) {
        this.extractos = extractos;
    }




}

