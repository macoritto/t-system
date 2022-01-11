/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CamionDao;
import dao.CamionDaoImpl;
import dao.ClienteDao;
import dao.ClienteDaoImpl;
import dao.ExSumiDao;
import dao.ExSumiDaoImpl;
import dao.OrdenDeCargaDao;
import dao.OrdenDeCargaDaoImpl;
import dao.PreFleteDao;
import dao.PreFleteDaoImpl;
import dao.SeguroDao;
import dao.SeguroDaoImpl;
import dao.SumiDao;
import dao.SumiDaoImpl;
import dao.ToleranciaDao;
import dao.ToleranciaDaoImpl;
import dao.ViajeDao;
import dao.ViajeDaoImpl;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Camion;
import model.Cliente;
import model.DetExtracto;
import model.Extracto;
import model.ExtractoSumi;
import model.OrdenDeCarga;
import model.PrecioFlete;
import model.PrecioSuministro;
import model.Propietario;
import model.Seguro;
import model.Suministro;
import model.TipoSuministro;
import model.Tolerancia;
import model.UnidadProvee;
import model.Usuario;
import model.Viaje;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class viajeBean {

    private List<Viaje> viajes;
    private List<Camion> camiones;
    private List<Viaje> viajesaux;
    private List<Cliente> clientes;
    private List<Cliente> selectedClientes;
    private List<Propietario> selectedPropietarios;
    private List<Viaje> viajebus;
    private List<Viaje> viajebus1;
    private List<Viaje> viajebus2;
    private List<Viaje> fleteview;
    private List<Viaje> selectedViajes;
    private Cliente selectedCliente;
    private List<Seguro> seguros;
    private List<Tolerancia> tolerancias;
    private List<Viaje> pendientes;
    private Viaje selectedViaje;
    private List<OrdenDeCarga> ordenes;
    private Camion selectedCamion;
    private OrdenDeCarga selectedOrdenDeCarga;
    private Tolerancia selectedTolerancia;
    private Propietario selectedPropietario;
    private PrecioFlete selectedPrecio;
    private List<PrecioFlete> precios;
    private Usuario usuario;
    private Seguro selectedSeguro;
    private Double tolerancia;
    private Integer faltante;
    private Double cantidadf;
    private Double preciof;
    private Date fechaini;
    private Date fechafin;
    private Date fechaini1;
    private Date fechafin1;
    private Date fechaini2;
    private Date fechafin2;
    private Integer nrofletes;
    private Integer busnrofletes;
    private Double totalbruto;
    private Integer pesoorigen;
    private Integer pesodestino;
    private Integer dif;
    private Double ganancia;
    private Double porcentaje;
    private String ventana;

    public viajeBean() {
        this.viajes = new ArrayList<Viaje>();
        ViajeDao viajeDao = new ViajeDaoImpl();
        this.viajes = viajeDao.findAll();
        this.viajesaux = new ArrayList<Viaje>();
        this.fleteview = new ArrayList<Viaje>();
        this.pendientes = new ArrayList<Viaje>();
        this.selectedClientes = new ArrayList<Cliente>();
        this.selectedPropietarios = new ArrayList<Propietario>();
        this.selectedViaje = new Viaje();
        this.selectedOrdenDeCarga = new OrdenDeCarga();
        this.tolerancias = new ArrayList<Tolerancia>();
        this.selectedTolerancia = new Tolerancia();
        this.selectedPrecio = new PrecioFlete();
        this.precios = new ArrayList<PrecioFlete>();
        this.seguros = new ArrayList<Seguro>();
        this.selectedSeguro = new Seguro();
        this.ordenes = new ArrayList<OrdenDeCarga>();
        this.selectedViajes = new ArrayList<Viaje>();
        this.viajebus = new ArrayList<Viaje>();
        this.viajebus1 = new ArrayList<Viaje>();
        this.viajebus2 = new ArrayList<Viaje>();
        this.clientes = new ArrayList<Cliente>();
        this.camiones = new ArrayList<Camion>();
        CamionDao camionDao = new CamionDaoImpl();
        this.selectedCamion = new Camion();
        this.camiones = camionDao.findAll();
        this.ventana = "";
        this.selectedPropietario = new Propietario();
    }

    public List<Camion> getCamiones() {
        return camiones;
    }

    public List<Propietario> getSelectedPropietarios() {
        return selectedPropietarios;
    }

    public Propietario getSelectedPropietario() {
        return selectedPropietario;
    }

    public void setSelectedPropietarios(List<Propietario> selectedPropietarios) {
        this.selectedPropietarios = selectedPropietarios;
    }

    public void setSelectedPropietario(Propietario selectedPropietario) {
        this.selectedPropietario = selectedPropietario;
    }

    public String getVentana() {
        if (this.selectedViaje.getPesoDestino() > 50000) {
            this.ventana = "PF('verificar').show()";
        } else {
            this.ventana = "";
        }
        return ventana;
    }

    public void setVentana(String ventana) {
        this.ventana = ventana;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public List<Tolerancia> getTolerancias() {
        ToleranciaDao toleranciaDao = new ToleranciaDaoImpl();
        this.tolerancias = toleranciaDao.findTole(this.selectedOrdenDeCarga);
        return tolerancias;
    }
    private boolean chkBoxChecked;

    public boolean isChkBoxChecked() {
        return chkBoxChecked;
    }

    public Integer getDif() {
        return dif;
    }

    public void setDif(Integer dif) {
        this.dif = dif;
    }

    public boolean isBtnDisabled() {

        return !this.chkBoxChecked;
    }

    public List<PrecioFlete> getPrecios() {
        PreFleteDao precioDao = new PreFleteDaoImpl();
        this.precios = precioDao.findPre(this.selectedOrdenDeCarga);
        return precios;
    }

    public List<Seguro> getSeguros() {
//        SeguroDao seguroDao = new SeguroDaoImpl();
//        System.out.print("Este es el id ES ");
//        if(this.selectedOrdenDeCarga==null){
//            this.seguros = seguroDao.findSeguro(3);
//        }else{
//            this.seguros = seguroDao.findSeguro(this.selectedOrdenDeCarga.getCliente().getIdCliente());
//        }        
//        for(int a=0; a<this.seguros.size(); a++){    
//            System.out.print("EL SEGURO ES ");
//            System.out.print(this.seguros.get(a).getMonto());
//        }  
        return seguros;
    }

    public void setSeguros(List<Seguro> seguros) {
        this.seguros = seguros;
    }

    public List<Viaje> getViajes() {

        return viajes;
    }

    public List<Cliente> getSelectedClientes() {
        return selectedClientes;
    }

    public Date getFechaini() {
        return fechaini;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public Date getFechaini1() {
        return fechaini1;
    }

    public Date getFechafin1() {
        return fechafin1;
    }

    public Date getFechaini2() {
        return fechaini2;
    }

    public Date getFechafin2() {
        return fechafin2;
    }

    public void setFechaini2(Date fechaini2) {
        this.fechaini2 = fechaini2;
    }

    public void setFechafin2(Date fechafin2) {
        this.fechafin2 = fechafin2;
    }

    public void setFechaini(Date fechaini) {
        this.fechaini = fechaini;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public void setFechaini1(Date fechaini1) {
        this.fechaini1 = fechaini1;
    }

    public void setFechafin1(Date fechafin1) {
        this.fechafin1 = fechafin1;
    }

    public List<Cliente> getClientes() {
        ClienteDao cliente = new ClienteDaoImpl();
        this.clientes = cliente.findAll();
        return clientes;
    }

    public List<Viaje> getViajebus1() {
        return viajebus1;
    }

    public void setViajebus1(List<Viaje> viajebus1) {
        this.viajebus1 = viajebus1;
    }

    public List<Viaje> getViajebus2() {
        return viajebus2;
    }

    public void setViajebus2(List<Viaje> viajebus2) {
        this.viajebus2 = viajebus2;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Integer getNrofletes() {
        return nrofletes;
    }

    public Integer getBusnrofletes() {
        return busnrofletes;
    }

    public Double getTotalbruto() {
        return totalbruto;
    }

    public Integer getPesoorigen() {
        return pesoorigen;
    }

    public Integer getPesodestino() {
        return pesodestino;
    }

    public Double getGanancia() {
        return ganancia;
    }

    public Cliente getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    public List<Viaje> getFleteview() {
        ViajeDao viajeDao = new ViajeDaoImpl();
        this.fleteview = viajeDao.findCamion(this.selectedCamion);
        return fleteview;
    }

    public List<OrdenDeCarga> getOrdenes() {
        OrdenDeCargaDao ordenDao = new OrdenDeCargaDaoImpl();
        this.ordenes = ordenDao.findOrden(this.selectedCamion);
        return ordenes;
    }

    public List<Viaje> getPendientes() {
        ViajeDao viajeDao = new ViajeDaoImpl();
        this.pendientes = viajeDao.findPen(this.selectedCamion);
        return pendientes;
    }

    public Viaje getSelectedViaje() {
        return selectedViaje;
    }

    public void setSelectedViaje(Viaje selectedViaje) {
        this.selectedViaje = selectedViaje;
    }

    public Camion getSelectedCamion() {
        return selectedCamion;
    }

    public OrdenDeCarga getSelectedOrdenDeCarga() {
        return selectedOrdenDeCarga;
    }

    public void setSelectedCamion(Camion selectedCamion) {
        this.selectedCamion = selectedCamion;
    }

    public void setSelectedOrdenDeCarga(OrdenDeCarga selectedOrdenDeCarga) {
        this.selectedOrdenDeCarga = selectedOrdenDeCarga;
    }

    public Tolerancia getSelectedTolerancia() {
        return selectedTolerancia;
    }

    public PrecioFlete getSelectedPrecio() {
        return selectedPrecio;
    }

    public void setSelectedPrecio(PrecioFlete selectedPrecio) {
        this.selectedPrecio = selectedPrecio;
    }

    public void setSelectedTolerancia(Tolerancia selectedTolerancia) {
        this.selectedTolerancia = selectedTolerancia;
    }

    public Seguro getSelectedSeguro() {
        return selectedSeguro;
    }

    public void setSelectedSeguro(Seguro selectedSeguro) {
        this.selectedSeguro = selectedSeguro;
    }

    public List<Viaje> getSelectedViajes() {
        return selectedViajes;
    }

    public void setSelectedViajes(List<Viaje> selectedViajes) {
        this.selectedViajes = selectedViajes;
    }

    public void setViajes(List<Viaje> viajes) {
        this.viajes = viajes;
    }

    public void setSelectedClientes(List<Cliente> selectedClientes) {
        this.selectedClientes = selectedClientes;
    }

    public void setOrdenes(List<OrdenDeCarga> ordenes) {
        this.ordenes = ordenes;
    }

    public void onRowSelectOrden(SelectEvent event) {
        this.selectedViaje.setOrdenDeCarga(this.selectedOrdenDeCarga);
        this.selectedViaje.setChofer(this.selectedOrdenDeCarga.getCamion().getChofer());
        this.chkBoxChecked = false;

    }

    public void onRowSelectViaje(SelectEvent event) {
        SeguroDao seguro = new SeguroDaoImpl();
        this.seguros = seguro.findSeguro(this.selectedOrdenDeCarga.getCliente().getIdCliente());
    }

    public void onRowSelectCliente(SelectEvent event) {
    }

    public void onRowSelectF(SelectEvent event) {
        for (int i = 0; i < this.selectedViajes.size(); i++) {
            this.selectedViaje = this.selectedViajes.get(i);
        }
    }

    public List<Viaje> getViajebus() {
        return viajebus;
    }

    public void setViajebus(List<Viaje> viajebus) {
        this.viajebus = viajebus;
    }

    public void onRowSelectTolerancia(SelectEvent event) {
        this.selectedViaje.setTolerancia(this.selectedTolerancia);
        //faltante=this.selectedViaje.getPesoOrigen()-this.selectedViaje.getPesoDestino();
        //tolerancia=this.selectedViaje.getPesoDestino()*this.selectedTolerancia.getTolerancia();
        //cantidadf= (faltante-tolerancia);
        //if(cantidadf>0){            
        //preciof= this.selectedOrdenDeCarga.getProducto().getPrecio();
        //this.selectedViaje.setMontofaltante(cantidadf*preciof);
        //}else{
        //this.selectedViaje.setMontofaltante(0);
        //}

        //System.out.println(this.selectedViaje.getMontofaltante());
    }

    public void onRowSelectPrecio(SelectEvent event) {
        this.selectedViaje.setPrecioFlete(this.selectedPrecio);
        //this.selectedViaje.setPrecioPago(this.selectedPrecio.getPrecioPago());
        //this.selectedViaje.setMontoCobrar(this.selectedPrecio.getPrecioCobro());
        //this.selectedViaje.setMontoPagar(this.selectedViaje.getPesoDestino()*this.selectedPrecio.getPrecioPago());
        //this.selectedViaje.setMontoBruto(this.selectedViaje.getPesoDestino()*this.selectedPrecio.getPrecioPago());

    }

    public void onRowSelectSeguro(SelectEvent event) {
        this.selectedViaje.setSeguro(this.selectedSeguro);
        //this.selectedViaje.setMontoPagar(this.selectedViaje.getMontoPagar()-(this.selectedSeguro.getMonto()+this.selectedViaje.getMontofaltante()));

    }

    public void onRowSelectCamion(SelectEvent event) {
        this.chkBoxChecked = false;
    }

    public void onRowSelectPropietario(SelectEvent event) {

    }

    public void btnCreateViaje(ActionEvent actionEvent) {
        ViajeDao viajeDao = new ViajeDaoImpl();
        String msg;
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
        this.selectedViaje.setUsuario(usuario);
        this.selectedViaje.setEstadoCobro("Pendiente");
        this.selectedViaje.setEstadoPago("Pendiente");
        this.selectedOrdenDeCarga.setEstadoOrden("Aceptado");
        this.selectedOrdenDeCarga.setEstadoliq("Pendiente");
        this.selectedViaje.setEstadocontra("Pendiente");
        if (viajeDao.create(this.selectedViaje)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedOrdenDeCarga.setEstadoOrden("Aceptado");
            OrdenDeCargaDao ordenDao = new OrdenDeCargaDaoImpl();
            if (ordenDao.update(this.selectedOrdenDeCarga)) {
            }
            SumiDao sumi = new SumiDaoImpl();
            Suministro selectedsuministro = new Suministro();
            selectedsuministro.setCamion(this.selectedCamion);
            selectedsuministro.setChofer(this.selectedCamion.getChofer());
            selectedsuministro.setDescrip("Seguro del Viaje Nro.:" + " " + this.selectedViaje.getRemisionDestino());
            selectedsuministro.setEstadoCobro("Pendiente");
            selectedsuministro.setEstadoPago("Pendiente");
            selectedsuministro.setFecha(this.selectedViaje.getFechaDestino());
            selectedsuministro.setLitros(1.0);
            selectedsuministro.setMonto(this.selectedViaje.getMontoseguro());
            selectedsuministro.setMontoTotal(this.selectedViaje.getMontoseguro());
            selectedsuministro.setOrdenDeCarga(this.selectedViaje.getOrdenDeCarga());
            selectedsuministro.setPrecioSumi(1.0);
            selectedsuministro.setUsuario(usuario);
            TipoSuministro tipo = new TipoSuministro();
            tipo.setIdTipo(5);
            selectedsuministro.setTipoSuministro(tipo);
            PrecioSuministro preciosumi = new PrecioSuministro();
            preciosumi.setIdPrecioSumi(3);
            selectedsuministro.setPrecioSuministro(preciosumi);
            UnidadProvee unidad = new UnidadProvee();
            unidad.setIdUnidad(4);
            selectedsuministro.setUnidadProvee(unidad);
            if (sumi.create(selectedsuministro)) {
                System.out.print("ENTRO EN CREAR");
                selectedsuministro = new Suministro();
            }
            if (this.selectedViaje.getMontofaltante() > 0.0) {
                selectedsuministro = new Suministro();
                selectedsuministro.setCamion(this.selectedCamion);
                selectedsuministro.setChofer(this.selectedCamion.getChofer());
                selectedsuministro.setDescrip("Faltante del Flete Nro.:" + " " + this.selectedViaje.getRemisionDestino());
                selectedsuministro.setEstadoCobro("Pendiente");
                selectedsuministro.setEstadoPago("Pendiente");
                selectedsuministro.setFecha(this.selectedViaje.getFechaDestino());
                selectedsuministro.setLitros(1.0);
                selectedsuministro.setMonto(this.selectedViaje.getMontofaltante());
                selectedsuministro.setMontoTotal(this.selectedViaje.getMontofaltante());
                selectedsuministro.setOrdenDeCarga(this.selectedViaje.getOrdenDeCarga());
                selectedsuministro.setPrecioSumi(1.0);
                selectedsuministro.setUsuario(usuario);
                tipo.setIdTipo(4);
                selectedsuministro.setTipoSuministro(tipo);
                preciosumi.setIdPrecioSumi(3);
                selectedsuministro.setPrecioSuministro(preciosumi);
                unidad.setIdUnidad(4);
                selectedsuministro.setUnidadProvee(unidad);
                if (sumi.create(selectedsuministro)) {
                    System.out.print("ENTRO EN CREAR");
                    selectedsuministro = new Suministro();
                }
            }

            this.viajes = new ArrayList<Viaje>();
            this.pendientes = new ArrayList<Viaje>();
            this.selectedViaje = new Viaje();
            this.selectedOrdenDeCarga = new OrdenDeCarga();
            this.tolerancias = new ArrayList<Tolerancia>();
            this.selectedTolerancia = new Tolerancia();
            this.selectedPrecio = new PrecioFlete();
            this.precios = new ArrayList<PrecioFlete>();
            this.seguros = new ArrayList<Seguro>();
            this.selectedSeguro = new Seguro();
            this.ordenes = new ArrayList<OrdenDeCarga>();
            this.selectedCamion = new Camion();
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnUpdateViaje(ActionEvent actionEvent) {
        ViajeDao viajeDao = new ViajeDaoImpl();
        String msg;
        if (viajeDao.update(this.selectedViaje)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteViaje(ActionEvent actionEvent) {
        ViajeDao viajeDao = new ViajeDaoImpl();
        this.selectedOrdenDeCarga = this.selectedViaje.getOrdenDeCarga();
        String msg;
        SumiDao sumiaux = new SumiDaoImpl();
        Suministro selectsumi = new Suministro();
        List<Suministro> sumielimi = new ArrayList<Suministro>();
        TipoSuministro tipo = new TipoSuministro();
        Extracto detex= new Extracto();
        ExSumiDao exsumiDao= new ExSumiDaoImpl();
        List<ExtractoSumi> exaux = new ArrayList<ExtractoSumi>();
        Integer bandera = 0;
        tipo.setIdTipo(4);
        sumielimi = sumiaux.pendseguro(this.selectedOrdenDeCarga.getIdOrdenDeCarga(), tipo);
        for (int i = 0; i < sumielimi.size(); i++) {
            selectsumi = sumielimi.get(i);
        }
        exaux=exsumiDao.findByExtracto(selectsumi.getIdSuministro());
        for (int i = 0; i < exaux.size(); i++) {
           detex = exaux.get(i).getDetExtracto().getExtracto();
        }
        if (sumiaux.delete(selectsumi.getIdSuministro())) {
        } else {
            bandera = 1;
        }
        selectsumi = new Suministro();
        sumielimi = new ArrayList<Suministro>();
        tipo.setIdTipo(5);
        sumielimi = sumiaux.pendfaltante(this.selectedOrdenDeCarga.getIdOrdenDeCarga(), tipo);
        for (int i = 0; i < sumielimi.size(); i++) {
            selectsumi = sumielimi.get(i);
        }
        if (sumiaux.delete(selectsumi.getIdSuministro())) {
        } else {
            bandera = 1;
        }
        if (bandera == 0) {
            if (viajeDao.delete(this.selectedViaje.getIdViaje())) {
                msg = "Se actualizo correctamente el registro";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                this.selectedOrdenDeCarga.setEstadoOrden("Pendiente");
                OrdenDeCargaDao ordenDao = new OrdenDeCargaDaoImpl();
                if (ordenDao.update(this.selectedOrdenDeCarga)) {
                }
            } else {
                msg = "Error al actualizar registro.";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }else{
            msg = "No se puede eliminar flete ya que se encuentra incluido en una Liquidacion N°. '"+detex.getIdLiquidacion()+"'";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnIniciar(ActionEvent actionEvent) {
        this.viajes = new ArrayList<Viaje>();
        this.pendientes = new ArrayList<Viaje>();
        this.selectedViaje = new Viaje();
        this.selectedOrdenDeCarga = new OrdenDeCarga();
        this.tolerancias = new ArrayList<Tolerancia>();
        this.selectedTolerancia = new Tolerancia();
        this.selectedPrecio = new PrecioFlete();
        this.precios = new ArrayList<PrecioFlete>();
        this.seguros = new ArrayList<Seguro>();
        this.selectedSeguro = new Seguro();
    }

    public void btnCargar(ActionEvent actionEvent) {
        this.selectedCamion = this.selectedViaje.getOrdenDeCarga().getCamion();
        this.selectedOrdenDeCarga = this.selectedViaje.getOrdenDeCarga();
        this.selectedPrecio = this.selectedViaje.getPrecioFlete();
        this.selectedSeguro = this.selectedViaje.getSeguro();
        this.selectedTolerancia = this.selectedViaje.getTolerancia();
    }

    public void btnProcesar(ActionEvent event) {
        String msg;
        FacesMessage message;
        Double tot = 0.0;
        Double tot1 = 0.0;
        Double totc = 0.0;
        Double faltante1 = 0.0;
        Double seguro = 0.0;
        Double monto = 0.0;
        Tolerancia tolerancia2;
        tolerancia2 = this.selectedTolerancia;
        PrecioFlete precio2;
        precio2 = this.selectedPrecio;
        Seguro seguro2;
        seguro2 = this.selectedSeguro;
        Integer bandp = 0;
        Integer bands = 0;
        Integer bandt = 0;
        Integer bandremision = 0;
        ViajeDao viajeDao = new ViajeDaoImpl();
        this.viajes = viajeDao.findAll();
        for (int a = 0; a < this.viajes.size(); a++) {
            if (this.selectedViaje.getRemisionDestino().equals(this.viajes.get(a).getRemisionDestino())) {
                System.out.print("hola");
                bandremision = 1;
            }
        }
        if (this.selectedPrecio == null) {
            bandp = 1;
            System.out.println(bandp);
        }
        if (this.selectedTolerancia == null) {
            bands = 1;
        }
        if (this.selectedSeguro == null) {
            bandt = 1;
        }
        if (bandremision.equals(0)) {
            if (this.selectedViaje.getPesoDestino() < 50000 && this.selectedViaje.getPesoDestino() - this.selectedViaje.getPesoOrigen() > -3000) {
                if (this.selectedViaje.getPesoOrigen() > 0 && this.selectedViaje.getPesoDestino() > 0) {
                    System.out.println(this.selectedSeguro.getDescripcion());
                    System.out.println(this.selectedPrecio.getDescripcion());
                    System.out.println(this.selectedTolerancia.getIdTolerancia());
                    if (this.selectedSeguro.getDescripcion() != null && this.selectedPrecio.getDescripcion() != null && this.selectedTolerancia.getTipo() != null) {
                        faltante = this.selectedViaje.getPesoOrigen() - this.selectedViaje.getPesoDestino();
                        dif = faltante;
                        String tipo1;
                        String tipo2;
                        tipo1 = "Porciento";
                        tipo2 = "Kilogramos";
                        System.out.println(this.selectedTolerancia.getIdTolerancia());
                        if (this.selectedTolerancia.getTipo().equals(tipo1)) {
                            tolerancia = this.selectedViaje.getPesoDestino() * this.selectedTolerancia.getTolerancia();
                            System.out.println("Tolerancia en kilos");
                            System.out.print(tolerancia);
                        } else {
                            if (this.selectedTolerancia.getTipo().equals(tipo2)) {
                                tolerancia = this.selectedTolerancia.getTolerancia();
                            }
                        }
                        cantidadf = (faltante - tolerancia);
                        if (cantidadf > 0) {
                            preciof = this.selectedOrdenDeCarga.getProducto().getPrecio();
                            System.out.println(tipo2);
                            this.selectedViaje.setMontofaltante(cantidadf * preciof);
                        } else {
                            this.selectedViaje.setMontofaltante(0);
                        }
                        this.selectedViaje.setPrecioPago(this.selectedPrecio.getPrecioPago());
                        this.selectedViaje.setPrecioCobro(this.selectedPrecio.getPrecioCobro());
                        this.selectedViaje.setMontoCobrar(this.selectedPrecio.getPrecioCobro() * this.selectedViaje.getPesoDestino());
                        this.selectedViaje.setMontoPagar(this.selectedViaje.getPesoDestino() * this.selectedPrecio.getPrecioPago());
                        this.selectedViaje.setMontoBruto(this.selectedViaje.getPesoDestino() * this.selectedPrecio.getPrecioCobro());
                        this.selectedViaje.setMontoconiva(this.selectedViaje.getPesoDestino() * this.selectedPrecio.getPrecioiva());
                        this.selectedViaje.setPrecioiva(this.selectedPrecio.getPrecioiva());
                        this.selectedViaje.setMontoseguro(this.selectedSeguro.getMonto());
                        this.selectedViaje.setFalCobrar(cantidadf);
                        this.selectedViaje.setDif(faltante);
                        faltante1 = this.selectedViaje.getMontofaltante();
                        seguro = this.selectedViaje.getSeguro().getMonto();
                        monto = this.selectedViaje.getMontoPagar();
                        tot1 = faltante1 + seguro;
                        tot = monto - tot1;
                        totc = this.selectedViaje.getMontoCobrar() - faltante1;
                        this.porcentaje = totc - tot;
                        this.selectedViaje.setMontoPagar(tot);
                        this.selectedViaje.setMontoCobrar(totc);
                        //this.selectedViaje.setMontoPagar(this.selectedViaje.getMontoPagar()-(this.selectedSeguro.getMonto()+this.selectedViaje.getMontofaltante()));
                        msg = "Se proceso con existo";
                        message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
                        this.chkBoxChecked = true;
                    } else {
                        msg = "Completar todos los datos solicitados. 1";
                        message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
                    }
                } else {
                    msg = "Completar todos los datos solicitados. 2";
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
                }
            } else {
                msg = "Verificar los datos del peso destino.";
                this.ventana = "PF('ordenDialogAgregar').show()";
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            }
        } else {
            msg = "Verificar Remision, ya existe un flete con el mismo numero de Remision.";
            this.ventana = "PF('ordenDialogAgregar').show()";
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void exportarPDFflete(ActionEvent actionEvent) throws JRException, IOException {
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/fletes.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getSelectedViajes()));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=fletes_pendientes.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void verificar1(ActionEvent event) {
        this.ventana = "";
    }

    public void onRowSelectCamion1(AjaxBehaviorEvent event) {
        System.out.print("ESTE ES LA CHAPA QUE INGRESARON");
        String msg;
        ViajeDao flete = new ViajeDaoImpl();
        CamionDao camion = new CamionDaoImpl();
        System.out.print("ESTE ES LA CHAPA QUE INGRESARON");
        String chapa = this.selectedCamion.getChapaCamion();
        System.out.print(chapa);
        Date fecha1 = this.getFechafin();
        Date fecha2 = this.getFechaini();
        this.camiones = camion.findCamion(chapa);
        if (this.camiones.size() > 0) {
            for (int i = 0; i < camiones.size(); i++) {
                this.selectedCamion = camiones.get(i);
            }
            this.viajebus1 = flete.FleteBus(this.selectedCamion, fecha2, fecha1);
            this.busnrofletes = this.viajebus1.size();
            this.totalbruto = this.sumarBruto(this.viajebus1);
            this.pesoorigen = this.sumarPesoO(this.viajebus1);
            this.pesodestino = this.sumarPesoD(this.viajebus1);
            this.ganancia = this.sumarGanancia(this.viajebus1);
            Double gananciaaux;
            Double montoaux;
            for (int i = 0; i < this.viajebus1.size(); i++) {
                montoaux = this.viajebus1.get(i).getMontoPagar() + this.viajebus1.get(i).getMontoseguro();
                gananciaaux = this.viajebus1.get(i).getMontoCobrar() - montoaux;
                this.viajebus1.get(i).setFalCobrar(gananciaaux);
            }
            if (this.busnrofletes == 0) {
                msg = "La chapa especificada no tiene viajes.";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                msg = "Se selecciono los viajes de la chapa en las fechas especificadas.";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            this.selectedCamion = new Camion();
            msg = "No existe la chapa especificada.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnCargarBus(ActionEvent event) {
//        ViajeDao flete = new ViajeDaoImpl();
//        CamionDao camion = new CamionDaoImpl();
//        this.camiones = camion.findCamion(this.selectedCamion.getChapaCamion());
//        for (int i = 0; i < this.camiones.size(); i++) {
//            this.selectedCamion = camiones.get(i);
//        }
//        this.viajebus1 = flete.FleteBus(this.selectedCamion, this.fechaini, this.fechafin);
//        this.busnrofletes = this.viajebus1.size();
//        this.totalbruto = this.sumarBruto(this.viajebus1);
//        this.pesoorigen = this.sumarPesoO(this.viajebus1);
//        this.pesodestino = this.sumarPesoD(this.viajebus1);
//        this.ganancia = this.sumarGanancia(this.viajebus1);
//        Double gananciaaux;
//        Double montoaux;
//        for (int i = 0; i < this.viajebus1.size(); i++) {
//            montoaux = this.viajebus1.get(i).getMontoPagar() + this.viajebus1.get(i).getMontoseguro();
//            gananciaaux = this.viajebus1.get(i).getMontoCobrar() - montoaux;
//            this.viajebus1.get(i).setFalCobrar(gananciaaux);
//        }
        System.out.print("ESTE ES LA CHAPA QUE INGRESARON");
        String msg;
        ViajeDao flete = new ViajeDaoImpl();
        CamionDao camion = new CamionDaoImpl();
        System.out.print("ESTE ES LA CHAPA QUE INGRESARON");
        String chapa = this.selectedCamion.getChapaCamion();
        System.out.print(chapa);
        Date fecha1 = this.getFechafin();
        Date fecha2 = this.getFechaini();
        this.camiones = camion.findCamion(chapa);
        if (this.camiones.size() > 0) {
            for (int i = 0; i < camiones.size(); i++) {
                this.selectedCamion = camiones.get(i);
            }
            this.viajebus1 = flete.FleteBus(this.selectedCamion, fecha2, fecha1);
            this.busnrofletes = this.viajebus1.size();
            this.totalbruto = this.sumarBruto(this.viajebus1);
            this.pesoorigen = this.sumarPesoO(this.viajebus1);
            this.pesodestino = this.sumarPesoD(this.viajebus1);
            this.ganancia = this.sumarGanancia(this.viajebus1);
            Double gananciaaux;
            Double montoaux;
            for (int i = 0; i < this.viajebus1.size(); i++) {
                montoaux = this.viajebus1.get(i).getMontoPagar() + this.viajebus1.get(i).getMontoseguro();
                gananciaaux = this.viajebus1.get(i).getMontoCobrar() - montoaux;
                this.viajebus1.get(i).setFalCobrar(gananciaaux);
            }
            if (this.busnrofletes == 0) {
                msg = "La chapa especificada no tiene viajes.";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
                FacesContext.getCurrentInstance().addMessage(null, message);
                this.viajebus1 = new ArrayList<Viaje>();
            } else {
                msg = "Se selecciono los viajes de la chapa en las fechas especificadas.";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            this.selectedCamion = new Camion();
            msg = "No existe la chapa especificada.";
            this.viajebus1 = new ArrayList<Viaje>();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnCargarBusCli(ActionEvent event) {
        ViajeDao flete = new ViajeDaoImpl();
        this.viajebus = new ArrayList<Viaje>();
        for (int i = 0; i < this.selectedClientes.size(); i++) {
            this.selectedCliente = this.selectedClientes.get(i);
            this.viajesaux = flete.FleteBusCli(this.selectedCliente, this.fechaini1, this.fechafin1);
            for (int e = 0; e < this.viajesaux.size(); e++) {
                this.viajebus.add(this.viajesaux.get(e));
            }
            this.selectedCliente = new Cliente();
        }
        this.busnrofletes = this.viajebus.size();
        this.totalbruto = this.sumarBruto(this.viajebus);
        this.pesoorigen = this.sumarPesoO(this.viajebus);
        this.pesodestino = this.sumarPesoD(this.viajebus);
        this.ganancia = this.sumarGanancia(this.viajebus);
        Double gananciaaux;
        Double montoaux;
        for (int i = 0; i < this.viajebus.size(); i++) {
            montoaux = this.viajebus.get(i).getMontoPagar() + this.viajebus.get(i).getMontoseguro();
            gananciaaux = this.viajebus.get(i).getMontoCobrar() - montoaux;
            this.viajebus.get(i).setFalCobrar(gananciaaux);
        }
    }

    public void btnCargarBusPro(ActionEvent event) {
        ViajeDao flete = new ViajeDaoImpl();
        this.viajebus2 = new ArrayList<Viaje>();

        this.viajesaux = flete.FleteBusPro(this.selectedPropietario, this.fechaini2, this.fechafin2);
        for (int e = 0; e < this.viajesaux.size(); e++) {
            this.viajebus2.add(this.viajesaux.get(e));
        }
        this.selectedPropietario = new Propietario();

        this.busnrofletes = this.viajebus2.size();
        this.totalbruto = this.sumarBruto(this.viajebus2);
        this.pesoorigen = this.sumarPesoO(this.viajebus2);
        this.pesodestino = this.sumarPesoD(this.viajebus2);
        this.ganancia = this.sumarGanancia(this.viajebus2);
        Double gananciaaux;
        Double montoaux;
        for (int i = 0; i < this.viajebus2.size(); i++) {
            montoaux = this.viajebus2.get(i).getMontoPagar() + this.viajebus2.get(i).getMontoseguro();
            gananciaaux = this.viajebus2.get(i).getMontoCobrar() - montoaux;
            this.viajebus2.get(i).setFalCobrar(gananciaaux);
        }
    }

    private Double sumarBruto(List<Viaje> viajelist) {
        Double suma = 0.0;
        for (int i = 0; i < viajelist.size(); i++) {
            suma = suma + viajelist.get(i).getMontoBruto();
        }
        return suma;
    }

    private Integer sumarPesoO(List<Viaje> viajelist) {
        Integer suma = 0;
        for (int i = 0; i < viajelist.size(); i++) {
            suma = suma + viajelist.get(i).getPesoOrigen();
        }
        return suma;
    }

    private Integer sumarPesoD(List<Viaje> viajelist) {
        Integer suma = 0;
        for (int i = 0; i < viajelist.size(); i++) {
            suma = suma + viajelist.get(i).getPesoDestino();
        }
        return suma;
    }

    private Double sumarGanancia(List<Viaje> viajelist) {
        Double suma = 0.0;
        Double diferencia = 0.0;
        Double aux = 0.0;
        for (int i = 0; i < viajelist.size(); i++) {
            aux = viajelist.get(i).getMontoPagar() + viajelist.get(i).getMontoseguro();
            diferencia = viajelist.get(i).getMontoCobrar() - aux;
            suma = suma + diferencia;
        }
        return suma;
    }
}
