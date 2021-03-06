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
 * AdelantoChofer generated by hbm2java
 */
@Entity
@Table(name="adelanto_chofer"
    ,schema="public"
)
public class AdelantoChofer  implements java.io.Serializable {


     private int idAdelantoChofer;
     private Camion camion;
     private Chofer chofer;
     private Usuario usuario;
     private Date fecha;
     private int monto;
     private String descripcion;
     private String estadoCobro;

    public AdelantoChofer() {
    }

	
    public AdelantoChofer(Camion camion, Chofer chofer, Usuario usuario, Date fecha, int monto, String estadoCobro) {
        this.camion = camion;
        this.chofer = chofer;
        this.usuario = usuario;
        this.fecha = fecha;
        this.monto = monto;
        this.estadoCobro = estadoCobro;
    }
    public AdelantoChofer(Camion camion, Chofer chofer, Usuario usuario, Date fecha, int monto, String descripcion, String estadoCobro) {
       this.camion = camion;
       this.chofer = chofer;
       this.usuario = usuario;
       this.fecha = fecha;
       this.monto = monto;
       this.descripcion = descripcion;
       this.estadoCobro = estadoCobro;
    }
   
     @SequenceGenerator(name="generator", sequenceName="adelanto_chofer_id_adelanto_chofer_seq")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")

    
    @Column(name="id_adelanto_chofer", nullable=false)
    public int getIdAdelantoChofer() {
        return this.idAdelantoChofer;
    }
    
    public void setIdAdelantoChofer(int idAdelantoChofer) {
        this.idAdelantoChofer = idAdelantoChofer;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="camion_chapa_camion", nullable=false)
    public Camion getCamion() {
        return this.camion;
    }
    
    public void setCamion(Camion camion) {
        this.camion = camion;
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
    @JoinColumn(name="usuario_id", nullable=false)
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha", nullable=false, length=13)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    @Column(name="monto", nullable=false)
    public int getMonto() {
        return this.monto;
    }
    
    public void setMonto(int monto) {
        this.monto = monto;
    }

    
    @Column(name="descripcion", length=50)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Column(name="estado_cobro", nullable=false, length=20)
    public String getEstadoCobro() {
        return this.estadoCobro;
    }
    
    public void setEstadoCobro(String estadoCobro) {
        this.estadoCobro = estadoCobro;
    }




}


