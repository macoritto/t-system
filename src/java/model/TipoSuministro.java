package model;
// Generated 22/10/2021 08:36:07 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TipoSuministro generated by hbm2java
 */
@Entity
@Table(name="tipo_suministro"
    ,schema="public"
)
public class TipoSuministro  implements java.io.Serializable {


     private int idTipo;
     private String nombre;
     private String aux;
     private Set suministros = new HashSet(0);

    public TipoSuministro() {
    }

	
    public TipoSuministro(int idTipo) {
        this.idTipo = idTipo;
    }
    public TipoSuministro(int idTipo, String nombre, String aux, Set suministros) {
       this.idTipo = idTipo;
       this.nombre = nombre;
       this.aux = aux;
       this.suministros = suministros;
    }
   
     @Id 

    
    @Column(name="id_tipo", nullable=false)
    public int getIdTipo() {
        return this.idTipo;
    }
    
    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    
    @Column(name="nombre", length=70)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="aux")
    public String getAux() {
        return this.aux;
    }
    
    public void setAux(String aux) {
        this.aux = aux;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="tipoSuministro")
    public Set getSuministros() {
        return this.suministros;
    }
    
    public void setSuministros(Set suministros) {
        this.suministros = suministros;
    }




}


