package model;
// Generated 22/10/2021 08:36:07 by Hibernate Tools 4.3.1


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

/**
 * PrecioSuministro generated by hbm2java
 */
@Entity
@Table(name="precio_suministro"
    ,schema="public"
)
public class PrecioSuministro  implements java.io.Serializable {


     private int idPrecioSumi;
     private UnidadProvee unidadProvee;
     private String nombre;
     private Double precio;
     private String estado;
     private Set suministros = new HashSet(0);

    public PrecioSuministro() {
    }

    public PrecioSuministro(UnidadProvee unidadProvee, String nombre, Double precio, String estado, Set suministros) {
       this.unidadProvee = unidadProvee;
       this.nombre = nombre;
       this.precio = precio;
       this.estado = estado;
       this.suministros = suministros;
    }
   
     @SequenceGenerator(name="generator", sequenceName="precio_suministro_id_seq")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="id_precio_sumi", nullable=false)
    public int getIdPrecioSumi() {
        return this.idPrecioSumi;
    }
    
    public void setIdPrecioSumi(int idPrecioSumi) {
        this.idPrecioSumi = idPrecioSumi;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_unidad_pro")
    public UnidadProvee getUnidadProvee() {
        return this.unidadProvee;
    }
    
    public void setUnidadProvee(UnidadProvee unidadProvee) {
        this.unidadProvee = unidadProvee;
    }

    
    @Column(name="nombre", length=60)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="precio", precision=17, scale=17)
    public Double getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    
    @Column(name="estado", length=20)
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="precioSuministro")
    public Set getSuministros() {
        return this.suministros;
    }
    
    public void setSuministros(Set suministros) {
        this.suministros = suministros;
    }




}


