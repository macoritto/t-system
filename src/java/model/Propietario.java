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
 * Propietario generated by hbm2java
 */
@Entity
@Table(name="propietario"
    ,schema="public"
)
public class Propietario  implements java.io.Serializable {


     private int idPropietario;
     private String nombre;
     private String apellido;
     private Date fechaCreacion;
     private String direccion;
     private String telefono;
     private int ciPropietario;
     private Set extractos = new HashSet(0);
     private Set camions = new HashSet(0);

    public Propietario() {
    }

	
    public Propietario(String nombre, String apellido, Date fechaCreacion, String direccion, String telefono, int ciPropietario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaCreacion = fechaCreacion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciPropietario = ciPropietario;
    }
    public Propietario(String nombre, String apellido, Date fechaCreacion, String direccion, String telefono, int ciPropietario, Set extractos, Set camions) {
       this.nombre = nombre;
       this.apellido = apellido;
       this.fechaCreacion = fechaCreacion;
       this.direccion = direccion;
       this.telefono = telefono;
       this.ciPropietario = ciPropietario;
       this.extractos = extractos;
       this.camions = camions;
    }
   
     @SequenceGenerator(name="generator", sequenceName="propietario_id_propietario_seq")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="id_propietario", nullable=false)
    public int getIdPropietario() {
        return this.idPropietario;
    }
    
    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
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
    @Column(name="fecha_creacion", nullable=false, length=13)
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    
    @Column(name="direccion", nullable=false, length=50)
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    @Column(name="telefono", nullable=false, length=30)
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    @Column(name="ci_propietario", nullable=false)
    public int getCiPropietario() {
        return this.ciPropietario;
    }
    
    public void setCiPropietario(int ciPropietario) {
        this.ciPropietario = ciPropietario;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="propietario")
    public Set getExtractos() {
        return this.extractos;
    }
    
    public void setExtractos(Set extractos) {
        this.extractos = extractos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="propietario")
    public Set getCamions() {
        return this.camions;
    }
    
    public void setCamions(Set camions) {
        this.camions = camions;
    }




}


