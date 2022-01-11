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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Chofer generated by hbm2java
 */
@Entity
@Table(name="chofer"
    ,schema="public"
)
public class Chofer  implements java.io.Serializable {


     private int idChofer;
     private int NCi;
     private String nombre;
     private String apellido;
     private Date fechaNacimiento;
     private String direccion;
     private String telefono;
     private Set adelantoChofers = new HashSet(0);
     private Set itemcs = new HashSet(0);
     private Set viajes = new HashSet(0);
     private Set extractocs = new HashSet(0);
     private Set viaticos = new HashSet(0);
     private Set camions = new HashSet(0);

    public Chofer() {
    }

	
    public Chofer(int NCi, String nombre, String apellido) {
        this.NCi = NCi;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    public Chofer(int NCi, String nombre, String apellido, Date fechaNacimiento, String direccion, String telefono, Set adelantoChofers, Set itemcs, Set viajes, Set extractocs, Set viaticos, Set camions) {
       this.NCi = NCi;
       this.nombre = nombre;
       this.apellido = apellido;
       this.fechaNacimiento = fechaNacimiento;
       this.direccion = direccion;
       this.telefono = telefono;
       this.adelantoChofers = adelantoChofers;
       this.itemcs = itemcs;
       this.viajes = viajes;
       this.extractocs = extractocs;
       this.viaticos = viaticos;
       this.camions = camions;
    }
   
     @SequenceGenerator(name="generator", sequenceName="chofer_id_chofer_seq")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="id_chofer", nullable=false)
    public int getIdChofer() {
        return this.idChofer;
    }
    
    public void setIdChofer(int idChofer) {
        this.idChofer = idChofer;
    }

    
    @Column(name="n_ci", nullable=false)
    public int getNCi() {
        return this.NCi;
    }
    
    public void setNCi(int NCi) {
        this.NCi = NCi;
    }

    
    @Column(name="nombre", nullable=false, length=50)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="apellido", nullable=false, length=50)
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha_nacimiento", length=13)
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    
    @Column(name="direccion", length=50)
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    @Column(name="telefono", length=30)
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="chofer")
    public Set getAdelantoChofers() {
        return this.adelantoChofers;
    }
    
    public void setAdelantoChofers(Set adelantoChofers) {
        this.adelantoChofers = adelantoChofers;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="chofer")
    public Set getItemcs() {
        return this.itemcs;
    }
    
    public void setItemcs(Set itemcs) {
        this.itemcs = itemcs;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="chofer")
    public Set getViajes() {
        return this.viajes;
    }
    
    public void setViajes(Set viajes) {
        this.viajes = viajes;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="chofer")
    public Set getExtractocs() {
        return this.extractocs;
    }
    
    public void setExtractocs(Set extractocs) {
        this.extractocs = extractocs;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="chofer")
    public Set getViaticos() {
        return this.viaticos;
    }
    
    public void setViaticos(Set viaticos) {
        this.viaticos = viaticos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="chofer")
    public Set getCamions() {
        return this.camions;
    }
    
    public void setCamions(Set camions) {
        this.camions = camions;
    }




}

