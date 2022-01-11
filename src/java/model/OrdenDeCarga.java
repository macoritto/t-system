package model;
// Generated 23/08/2021 09:14:32 AM by Hibernate Tools 4.3.1

import dao.SumiCliDao;
import dao.SumiCliDaoImpl;
import dao.SumiDao;
import dao.SumiDaoImpl;
import dao.ViajeDao;
import dao.ViajeDaoImpl;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import org.primefaces.event.ReorderEvent;

/**
 * OrdenDeCarga generated by hbm2java
 */
public class OrdenDeCarga implements java.io.Serializable {

    private int idOrdenDeCarga;
    private Camion camion;
    private Cliente cliente;
    private Producto producto;
    private UnidadDestino unidadDestino;
    private UnidadOrigen unidadOrigen;
    private Usuario usuario;
    private Date fechaEmision;
    private String estadoOrden;
    private String estadoliq;
    private List<Suministro> suministros = new ArrayList<Suministro>();
    private List<SumiCliente> sumiclis = new ArrayList<SumiCliente>();
    private List<sumitem> sumitems = new ArrayList<sumitem>();
    private List<sumitem> sumitems2 = new ArrayList<sumitem>();
    private List<Viaje> fletes = new ArrayList<Viaje>();
    private String asumi;
    private String anega;
    private String saldo;
    private Double totalactivo=0.0;
    private Double totalpasivo=0.0;
    private Double saldoextra=0.0;
    private Double totalactivo1=0.0;
    private Double totalpasivo1=0.0;
    private Double saldoextra1=0.0;
    private String chapacamion="";
    private Set viajes = new HashSet(0);
    private Set valeCombustibles = new HashSet(0);
    private Set viaticos = new HashSet(0);

    public OrdenDeCarga() {
    }

    public OrdenDeCarga(Camion camion, Cliente cliente, Producto producto, UnidadDestino unidadDestino, UnidadOrigen unidadOrigen, Usuario usuario, Date fechaEmision, String estadoOrden, String estadoliq) {
        this.camion = camion;
        this.cliente = cliente;
        this.producto = producto;
        this.unidadDestino = unidadDestino;
        this.unidadOrigen = unidadOrigen;
        this.usuario = usuario;
        this.fechaEmision = fechaEmision;
        this.estadoOrden = estadoOrden;
        this.estadoliq=estadoliq;
    }

    public OrdenDeCarga(Camion camion, Cliente cliente, Producto producto, UnidadDestino unidadDestino, UnidadOrigen unidadOrigen, Usuario usuario, Date fechaEmision, String estadoOrden, String estadoliq, Set viajes, Set valeCombustibles, Set viaticos) {
        this.camion = camion;
        this.cliente = cliente;
        this.producto = producto;
        this.unidadDestino = unidadDestino;
        this.unidadOrigen = unidadOrigen;
        this.usuario = usuario;
        this.fechaEmision = fechaEmision;
        this.estadoOrden = estadoOrden;
        this.estadoliq = estadoliq;
        this.viajes = viajes;
        this.valeCombustibles = valeCombustibles;
        this.viaticos = viaticos;
    }

    public int getIdOrdenDeCarga() {
        return this.idOrdenDeCarga;
    }

    public void setIdOrdenDeCarga(int idOrdenDeCarga) {
        this.idOrdenDeCarga = idOrdenDeCarga;
    }

    public Camion getCamion() {
        return this.camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public UnidadDestino getUnidadDestino() {
        return this.unidadDestino;
    }

    public void setUnidadDestino(UnidadDestino unidadDestino) {
        this.unidadDestino = unidadDestino;
    }

    public UnidadOrigen getUnidadOrigen() {
        return this.unidadOrigen;
    }

    public void setUnidadOrigen(UnidadOrigen unidadOrigen) {
        this.unidadOrigen = unidadOrigen;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaEmision() {
        return this.fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getEstadoOrden() {
        return this.estadoOrden;
    }

    public void setEstadoOrden(String estadoOrden) {
        this.estadoOrden = estadoOrden;
    }
    @Column(name="estadoliq", length=15)
    public String getEstadoliq() {
        return this.estadoliq;
    }
    
    public void setEstadoliq(String estadoliq) {
        this.estadoliq = estadoliq;
    }

    public String getChapacamion() {
        return chapacamion;
    }

    public void setChapacamion(String chapacamion) {
        this.chapacamion = chapacamion;
    }

    public Set getViajes() {
        return this.viajes;
    }

    public void setViajes(Set viajes) {
        this.viajes = viajes;
    }

    public Set getValeCombustibles() {
        return this.valeCombustibles;
    }

    public void setValeCombustibles(Set valeCombustibles) {
        this.valeCombustibles = valeCombustibles;
    }

    public Set getViaticos() {
        return this.viaticos;
    }

    public void setViaticos(Set viaticos) {
        this.viaticos = viaticos;
    }

    public List<sumitem> getSumitems() {
        if (this.sumitems.size() > 0) {
            System.out.print("DICE QUE YA ESTA CARGADO");
        } else {
            SumiDao sumi = new SumiDaoImpl();
            sumitem ejemplo = new sumitem();
            SumiCliDao sumiDao =new SumiCliDaoImpl();
            this.suministros = sumi.pendienteschapa(idOrdenDeCarga, chapacamion);
            ViajeDao viajedao = new ViajeDaoImpl();
            //this.asumi=this.sumarSuministro(suministros);
            this.fletes = viajedao.fletesumi(idOrdenDeCarga);
            this.sumiclis= sumiDao.findOneChapa(idOrdenDeCarga, chapacamion);
            String bandecolor = "";
            for (int i = 0; i < this.fletes.size(); i++) {
                ejemplo.setFecha(this.fletes.get(i).getFechaDestino());
                ejemplo.setMonto(this.fletes.get(i).getMontoBruto());
                ejemplo.setDescrip("Flete de " + this.fletes.get(i).getOrdenDeCarga().getUnidadOrigen().getNombre() + " a " + this.fletes.get(i).getOrdenDeCarga().getUnidadDestino().getNombre() + " con Remision Nro. " + this.fletes.get(i).getRemisionDestino() + " con P. Origen: " + this.fletes.get(i).getPesoOrigen() + " y P. Destino " + this.fletes.get(i).getPesoDestino());
                ejemplo.setTipo("Flete");
                ejemplo.setChapa(this.fletes.get(i).getOrdenDeCarga().getCamion().getChapaCamion());
                ejemplo.setOrden(idOrdenDeCarga);
                ejemplo.setPrecio(this.fletes.get(i).getPrecioPago());
                this.totalactivo=this.totalactivo+this.fletes.get(i).getMontoBruto();
                ejemplo.setId(this.fletes.get(i).getIdViaje());
                if (this.fletes.get(i).getEstadoCobro().equals("Pendiente")) {
                    ejemplo.setColor("red");
                    bandecolor = "red";
                } else {
                    ejemplo.setColor("green");
                    bandecolor = "green";
                }
                this.sumitems.add(ejemplo);
                ejemplo = new sumitem();
            }
            for (int i = 0; i < this.suministros.size(); i++) {
                if (this.suministros.get(i).getTipoSuministro().getNombre().equals("Seguro") || (this.suministros.get(i).getTipoSuministro().getNombre().equals("Faltante"))) {
                    ejemplo.setId(this.suministros.get(i).getIdSuministro());
                    ejemplo.setFecha(this.suministros.get(i).getFecha());
                    ejemplo.setMonto(this.suministros.get(i).getMonto() * -1);
                    ejemplo.setDescrip(this.suministros.get(i).getDescrip());
                    ejemplo.setTipo(this.suministros.get(i).getTipoSuministro().getNombre());
                    ejemplo.setChapa(this.suministros.get(i).getCamion().getChapaCamion());
                    ejemplo.setOrden(idOrdenDeCarga);
                    ejemplo.setPrecio(this.suministros.get(i).getPrecioSumi());
                    this.totalpasivo=this.totalpasivo+this.suministros.get(i).getMonto();
                    if (this.suministros.get(i).getEstadoPago().equals("Pendiente")) {
                        ejemplo.setColor("red");
                    } else {
                        ejemplo.setColor("green");
                    }
                    if (this.suministros.get(i).getTipoSuministro().getNombre().equals("Seguro") || (this.suministros.get(i).getTipoSuministro().getNombre().equals("Faltante"))) {
                        ejemplo.setColor(bandecolor);
                    }
                    this.sumitems.add(ejemplo);
                    ejemplo = new sumitem();
                }    
            }
            for (int i = 0; i < this.suministros.size(); i++) {
                if (!this.suministros.get(i).getTipoSuministro().getNombre().equals("Seguro") && !this.suministros.get(i).getTipoSuministro().getNombre().equals("Faltante")) {    
                    ejemplo.setId(this.suministros.get(i).getIdSuministro());
                    ejemplo.setFecha(this.suministros.get(i).getFecha());
                    ejemplo.setMonto(this.suministros.get(i).getMonto() * -1);
                    ejemplo.setDescrip(this.suministros.get(i).getDescrip());
                    ejemplo.setTipo(this.suministros.get(i).getTipoSuministro().getNombre());
                    ejemplo.setChapa(this.suministros.get(i).getCamion().getChapaCamion());
                    ejemplo.setOrden(idOrdenDeCarga);
                    ejemplo.setPrecio(this.suministros.get(i).getPrecioSumi());
                    this.totalpasivo=this.totalpasivo+this.suministros.get(i).getMonto();
                    if (this.suministros.get(i).getEstadoPago().equals("Pendiente")) {
                        ejemplo.setColor("red");
                    } else {
                        ejemplo.setColor("green");
                    }
                    if (this.suministros.get(i).getTipoSuministro().getNombre().equals("Seguro") || (this.suministros.get(i).getTipoSuministro().getNombre().equals("Faltante"))) {
                        ejemplo.setColor(bandecolor);
                    }
                    this.sumitems.add(ejemplo);
                    ejemplo = new sumitem();
                }    
            }
            for (int i = 0; i < this.sumiclis.size(); i++) {
                ejemplo.setId(this.sumiclis.get(i).getIdSumiCli());
                ejemplo.setFecha(this.sumiclis.get(i).getFecha());
                ejemplo.setMonto(this.sumiclis.get(i).getMonto() * -1);
                ejemplo.setDescrip(this.sumiclis.get(i).getDescrip());
                ejemplo.setTipo("SumiCli");
                ejemplo.setChapa(this.sumiclis.get(i).getCamion().getChapaCamion());
                ejemplo.setOrden(idOrdenDeCarga);
                ejemplo.setPrecio(1.0);
                this.totalpasivo=this.totalpasivo+this.sumiclis.get(i).getMonto();
                if (this.sumiclis.get(i).getEstadoCobro().equals("Pendiente")) {
                    ejemplo.setColor("red");
                } else {
                    ejemplo.setColor("green");
                }
                this.sumitems.add(ejemplo);
                ejemplo = new sumitem();
            }
            sumarSumitems(sumitems);
        }
        this.saldoextra=this.totalactivo-this.totalpasivo;
        return this.sumitems;
    }

    public List<sumitem> getSumitems2() {
        if (this.sumitems2.size() > 0) {
            System.out.print("DICE QUE YA ESTA CARGADO");
        } else {
            SumiDao sumi = new SumiDaoImpl();
            SumiCliDao sumiDao =new SumiCliDaoImpl();
            sumitem ejemplo = new sumitem();
            this.suministros = sumi.pendienteschapa(idOrdenDeCarga, chapacamion);
            ViajeDao viajedao = new ViajeDaoImpl();
            this.sumiclis= sumiDao.findOneChapa(idOrdenDeCarga, chapacamion);
            //this.asumi=this.sumarSuministro(suministros);
            this.fletes = viajedao.fletesumi(idOrdenDeCarga);
            String bandecolor = "";
            for (int i = 0; i < this.fletes.size(); i++) {
                ejemplo.setFecha(this.fletes.get(i).getFechaDestino());
                ejemplo.setMonto(this.fletes.get(i).getMontoBruto());
                ejemplo.setDescrip("Flete de " + this.fletes.get(i).getOrdenDeCarga().getUnidadOrigen().getNombre() + " a " + this.fletes.get(i).getOrdenDeCarga().getUnidadDestino().getNombre() + " con Remision Nro. " + this.fletes.get(i).getRemisionDestino() + " con P. Origen: " + this.fletes.get(i).getPesoOrigen() + " y P. Destino " + this.fletes.get(i).getPesoDestino());
                ejemplo.setTipo("Flete");
                ejemplo.setChapa(this.fletes.get(i).getOrdenDeCarga().getCamion().getChapaCamion());
                ejemplo.setOrden(idOrdenDeCarga);
                ejemplo.setPrecio(this.fletes.get(i).getPrecioPago());
                this.totalactivo1=this.totalactivo1+this.fletes.get(i).getMontoBruto();
                if (this.fletes.get(i).getEstadoCobro().equals("Pendiente")) {
                    ejemplo.setColor("red");
                    bandecolor = "red";
                } else {
                    ejemplo.setColor("green");
                    bandecolor = "green";
                }
                this.sumitems2.add(ejemplo);
                ejemplo = new sumitem();
            }
            for (int i = 0; i < this.suministros.size(); i++) {
                if (this.suministros.get(i).getTipoSuministro().getNombre().equals("Seguro") || this.suministros.get(i).getTipoSuministro().getNombre().equals("Faltante")) {
                    ejemplo.setFecha(this.suministros.get(i).getFecha());
                    ejemplo.setMonto(this.suministros.get(i).getMonto() * -1);
                    ejemplo.setDescrip(this.suministros.get(i).getDescrip());
                    ejemplo.setTipo(this.suministros.get(i).getTipoSuministro().getNombre());
                    ejemplo.setChapa(this.suministros.get(i).getCamion().getChapaCamion());
                    ejemplo.setOrden(idOrdenDeCarga);
                    ejemplo.setPrecio(this.suministros.get(i).getPrecioSumi());
                    this.totalpasivo1=this.totalpasivo1+this.suministros.get(i).getMonto();
                    if (this.suministros.get(i).getEstadoPago().equals("Pendiente")) {
                        ejemplo.setColor("red");
                    } else {
                        ejemplo.setColor("green");
                    }
                    if (this.suministros.get(i).getTipoSuministro().getNombre().equals("Seguro") || (this.suministros.get(i).getTipoSuministro().getNombre().equals("Faltante"))) {
                        ejemplo.setColor(bandecolor);
                    }
                    this.sumitems2.add(ejemplo);
                    ejemplo = new sumitem();
                }
            }
            for (int i = 0; i < this.suministros.size(); i++) {
                if (!this.suministros.get(i).getTipoSuministro().getNombre().equals("Seguro") && !this.suministros.get(i).getTipoSuministro().getNombre().equals("Faltante")) {
                    ejemplo.setFecha(this.suministros.get(i).getFecha());
                    ejemplo.setMonto(this.suministros.get(i).getMonto() * -1);
                    ejemplo.setDescrip(this.suministros.get(i).getDescrip());
                    ejemplo.setTipo(this.suministros.get(i).getTipoSuministro().getNombre());
                    ejemplo.setChapa(this.suministros.get(i).getCamion().getChapaCamion());
                    ejemplo.setOrden(idOrdenDeCarga);
                    ejemplo.setPrecio(this.suministros.get(i).getPrecioSumi());
                    this.totalpasivo1=this.totalpasivo1+this.suministros.get(i).getMonto();
                    if (this.suministros.get(i).getEstadoPago().equals("Pendiente")) {
                        ejemplo.setColor("red");
                    } else {
                        ejemplo.setColor("green");
                    }
                    if (this.suministros.get(i).getTipoSuministro().getNombre().equals("Seguro") || (this.suministros.get(i).getTipoSuministro().getNombre().equals("Faltante"))) {
                        ejemplo.setColor(bandecolor);
                    }
                    this.sumitems2.add(ejemplo);
                    ejemplo = new sumitem();
                }
            }
             for (int i = 0; i < this.sumiclis.size(); i++) {
                ejemplo.setId(this.sumiclis.get(i).getIdSumiCli());
                ejemplo.setFecha(this.sumiclis.get(i).getFecha());
                ejemplo.setMonto(this.sumiclis.get(i).getMonto() * -1);
                ejemplo.setDescrip(this.sumiclis.get(i).getDescrip());
                ejemplo.setTipo("SumiCli");
                ejemplo.setChapa(this.sumiclis.get(i).getCamion().getChapaCamion());
                ejemplo.setOrden(idOrdenDeCarga);
                ejemplo.setPrecio(1.0);
                this.totalpasivo1=this.totalpasivo1+this.sumiclis.get(i).getMonto();
                if (this.sumiclis.get(i).getEstadoPago().equals("Pendiente")) {
                    ejemplo.setColor("red");
                } else {
                    ejemplo.setColor("green");
                }
                this.sumitems2.add(ejemplo);
                ejemplo = new sumitem();
            }
            sumarSumitems(sumitems2);
        }
        this.saldoextra1=this.totalactivo1-this.totalpasivo1;
        return this.sumitems2;
    }

    private Double sumarSumitems(List<sumitem> sumiitems) {
        Double suma = 0.0;
        Double suma2 = 0.0;
        Double saldo = 0.0;
        Double aux = 0.0;
        for (int i = 0; i < sumiitems.size(); i++) {
            if (sumiitems.get(i).getMonto() > 0) {
                suma = suma + sumiitems.get(i).getMonto();
            } else {
                suma2 = suma2 - sumiitems.get(i).getMonto();
            }
        }
        DecimalFormat formateador = new DecimalFormat("###,###");
        this.asumi = formateador.format(suma);
        this.anega = formateador.format(suma2);
        aux = suma - suma2;
        this.saldo = formateador.format(aux);
        return suma;
    }
    public void onRowReorder(ReorderEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se Reorde", " de: " + event.getFromIndex() + ", To:" + event.getToIndex());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String getAsumi() {
        return asumi;
    }

    public String getAnega() {
        return anega;
    }

    public void setAnega(String anega) {
        this.anega = anega;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public Double getTotalactivo() {
        return totalactivo;
    }

    public void setTotalactivo(Double totalactivo) {
        this.totalactivo = totalactivo;
    }

    public Double getTotalpasivo() {
        return totalpasivo;
    }

    public void setTotalpasivo(Double totalpasivo) {
        this.totalpasivo = totalpasivo;
    }

    public Double getSaldoextra() {
        return saldoextra;
    }

    public void setSaldoextra(Double saldoextra) {
        this.saldoextra = saldoextra;
    }

    public Double getTotalactivo1() {
        return totalactivo1;
    }

    public void setTotalactivo1(Double totalactivo1) {
        this.totalactivo1 = totalactivo1;
    }

    public Double getTotalpasivo1() {
        return totalpasivo1;
    }

    public void setTotalpasivo1(Double totalpasivo1) {
        this.totalpasivo1 = totalpasivo1;
    }

    public Double getSaldoextra1() {
        return saldoextra1;
    }

    public void setSaldoextra1(Double saldoextra1) {
        this.saldoextra1 = saldoextra1;
    }

}
