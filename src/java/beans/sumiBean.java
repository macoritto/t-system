/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.AdeSumiDao;
import dao.AdeSumiDaoImpl;
import dao.CamionDao;
import dao.CamionDaoImpl;
import dao.CombDao;
import dao.CombDaoImpl;
import dao.DetAde;
import dao.OrdenDeCargaDao;
import dao.OrdenDeCargaDaoImpl;
import dao.PagoAdeImpl;
import dao.PagoSumiDaoImpl;
import dao.PagoSumiDetDao;
import dao.PagoSuministroDao;
import dao.PagoSuministroDaoImpl;
import dao.PreSumiDao;
import dao.PreSumiDaoImpl;
import dao.PrecioCombDao;
import dao.PrecioCombDaoImpl;
import dao.ProCombDao;
import dao.ProCombDaoImpl;
import dao.SumiDao;
import dao.SumiDaoImpl;
import dao.TipoViaticoDao;
import dao.TipoViaticoDaoImpl;
import dao.ValeCombDao;
import dao.ValeCombDaoImpl;
import dao.ViaticoDao;
import dao.ViaticoDaoImpl;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Camion;
import model.Combustible;
import model.TipoViatico;
import model.Usuario;
import model.ValeCombustible;
import model.Viatico;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.event.SelectEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import model.AdeSuministro;
import model.OrdenDeCarga;
import model.PagoAdeDet;
import model.PagoSumiDet;
import model.PagoSuministro;
import model.PrecioComb;
import model.PrecioSuministro;
import model.ProveeCliente;
import model.Proveedor;
import model.Suministro;
import model.TipoSuministro;
import model.UnidadProvee;
import model.sumidet;

/**
 *
 * @author Usuario
 */
@ManagedBean
@SessionScoped
public class sumiBean implements Serializable {

    /**
     * Creates a new instance of comBean
     */
    private Combustible selectedCombustible;
    private Suministro selectedSuministro;
    private Suministro selectedSuministro2;
    private PagoSuministro selectedPago;
    private UnidadProvee selectedUnidad;
    private List<PrecioSuministro> prepro;
    private List<Suministro> selectedSuministros;
    private List<Proveedor> proCombustibles;
    private PagoSumiDet selectedPagosumi;
    private List<PagoSumiDet> pagoSumiDet;
    private List<PagoSumiDet> detpagovista;
    private List<PagoAdeDet> detadevista;
    private List<sumidet> sumidetaux;
    private List<sumidet> sumidetaux1;
    private List<sumidet> sumidetaux2;
    private String descrip1;
    private String descrip;
    private String estadoaux;
    private sumidet selectedSumidet;
    private List<PagoSumiDet> pagoSumiDetAux;
    private ProveeCliente selectedProvee;
    private List<TipoSuministro> tipos;
    private List<Combustible> combustibles;
    private OrdenDeCarga selectedOrdenDeCarga;
    private List<OrdenDeCarga> orden;
    private PrecioComb selectedPrecio;
    private ValeCombustible selectedValeComb;
    private TipoSuministro selectedTipo;
    private ValeCombustible selectedValeComb2;
    private List<Combustible> buscomb;
    private List<Combustible> comblist;
    private List<ValeCombustible> valespen;
    private List<Camion> camiones;
    private ValeCombustible selectedValeCombustible;
    private Camion selectedCamion;
    private Viatico selectedViatico;
    private Usuario usuario;
    private List<TipoViatico> tipo;
    private List<Viatico> viaticos;
    private List<Suministro> suministros;
    private List<Suministro> suministropen;
    private List<PagoSuministro> pagosumi;
    private Double totalcomb;
    private Double totalsumi;
    private Double totalade;
    private Double totalvia;
    private Double totalex;
    private Double total;
    private Double bustotalcomb;
    private Double bustotalvia;
    private Double bustotalex;
    private Double bustotal;
    private List<PrecioComb> precios;
    private Integer nrocomb;
    private Integer busnrocomb;
    private Date fechaini;
    private Date fechafin;
    private Date fechaini1;
    private Date fechafin1;
    private Proveedor selectedProveedor;
    private Double litros;
    private Boolean validar;
    private Boolean preboo;
    private Boolean bootipo;
    private Boolean readonly;
    private Boolean boocrear;
    private Integer codvale;
    private Connection conexion;
    private List<SelectItem> selectOneItemsTipo;
    private List<PrecioSuministro> selectedPrecioSumi;
    private PrecioSuministro selectedprecios;
    private Date dia = new Date();

    public sumiBean() {
        this.selectedCamion = new Camion();
        this.selectedSuministro = new Suministro();
        this.selectedSuministro2 = new Suministro();
        this.selectedUnidad = new UnidadProvee();
        this.selectedCombustible = new Combustible();
        this.selectedPago = new PagoSuministro();
        this.camiones = new ArrayList<Camion>();
        this.pagosumi = new ArrayList<PagoSuministro>();
        this.precios = new ArrayList<PrecioComb>();
        this.tipo = new ArrayList<TipoViatico>();
        this.tipos = new ArrayList<TipoSuministro>();
        this.suministros = new ArrayList<Suministro>();
        this.selectedTipo = new TipoSuministro();
        this.selectedViatico = new Viatico();
        this.buscomb = new ArrayList<Combustible>();
        this.selectedProveedor = new Proveedor();
        this.selectedCombustible.setFecha(new Date());
        this.selectedOrdenDeCarga = new OrdenDeCarga();
        this.selectedProvee = new ProveeCliente();
        this.orden = new ArrayList<OrdenDeCarga>();
        this.prepro = new ArrayList<PrecioSuministro>();
        this.validar = false;
        this.preboo = true;
        this.bootipo = true;
        this.boocrear = true;
        this.readonly = true;
        this.selectedprecios = new PrecioSuministro();
        this.selectedSuministro.setFecha(dia);
        this.suministropen = new ArrayList<Suministro>();
        this.selectedSuministros = new ArrayList<Suministro>();
        this.selectedPagosumi = new PagoSumiDet();
        this.pagoSumiDet = new ArrayList<PagoSumiDet>();
        this.pagoSumiDetAux = new ArrayList<PagoSumiDet>();
        this.detpagovista = new ArrayList<PagoSumiDet>();
        this.detadevista = new ArrayList<PagoAdeDet>();
        this.sumidetaux = new ArrayList<sumidet>();
        this.sumidetaux1 = new ArrayList<sumidet>();
        this.sumidetaux2 = new ArrayList<sumidet>();
        this.totalsumi = 0.0;
        this.totalade = 0.0;
        this.selectedSumidet = new sumidet();
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getDescrip1() {
        this.descrip1 = this.selectedPago.getDescrip();
        return descrip1;
    }

    public void setDescrip1(String descrip1) {
        this.descrip1 = descrip1;
    }

    public PagoSuministro getSelectedPago() {
        return selectedPago;
    }

    public void setSelectedPago(PagoSuministro selectedPago) {
        this.selectedPago = selectedPago;
    }

    public Combustible getSelectedCombustible() {
        return selectedCombustible;
    }

    public List<PrecioSuministro> getPrepro() {
        PreSumiDao sumiDao = new PreSumiDaoImpl();
        this.prepro = sumiDao.findPro(this.selectedUnidad);
        return prepro;
    }

    public Double getTotalsumi() {
        return totalsumi;
    }

    public void setTotalsumi(Double totalsumi) {
        this.totalsumi = totalsumi;
    }

    public List<PagoSumiDet> getPagoSumiDetAux() {
        return pagoSumiDetAux;
    }

    public void setPagoSumiDetAux(List<PagoSumiDet> pagoSumiDetAux) {
        this.pagoSumiDetAux = pagoSumiDetAux;
    }

    public List<sumidet> getSumidetaux1() {
        return sumidetaux1;
    }

    public void setSumidetaux1(List<sumidet> sumidetaux1) {
        this.sumidetaux1 = sumidetaux1;
    }

    public List<sumidet> getSumidetaux() {
        String msg;
        FacesMessage message = null;
        PagoSumiDetDao sumi = new PagoSumiDaoImpl();
        sumidet sumidett = new sumidet();
        this.sumidetaux = new ArrayList<sumidet>();
        this.sumidetaux2 = new ArrayList<sumidet>();
        this.detpagovista = sumi.findByPagoc(this.selectedPago);
        for (int i = 0; i < this.detpagovista.size(); i++) {
            sumidett.setChapa(this.detpagovista.get(i).getSuministro().getCamion().getChapaCamion());
            sumidett.setId(this.detpagovista.get(i).getSuministro().getIdSuministro());
            sumidett.setFecha(this.detpagovista.get(i).getSuministro().getFecha());
            sumidett.setChofer(this.detpagovista.get(i).getSuministro().getChofer().getNombre() + " " + this.detpagovista.get(i).getSuministro().getChofer().getApellido());
            sumidett.setDescrip(this.detpagovista.get(i).getSuministro().getDescrip());
            sumidett.setLitros(this.detpagovista.get(i).getSuministro().getLitros());
            sumidett.setMonto(this.detpagovista.get(i).getSuministro().getMonto());
            sumidett.setPrecio(this.detpagovista.get(i).getSuministro().getPrecioSumi());
            sumidett.setTipo(this.detpagovista.get(i).getSuministro().getTipoSuministro().getNombre());
            sumidett.setTotal(this.detpagovista.get(i).getSuministro().getMonto());
            this.sumidetaux.add(sumidett);
            sumidett = new sumidet();
        }
        System.out.print("INTENTA CARGAR");
        System.out.print(this.selectedPago.getIdPagosumi());
        System.out.print(this.selectedPago.getDescrip());
        List<AdeSuministro> adeaux;
        adeaux = new ArrayList<AdeSuministro>();
        DetAde sumiaux = new PagoAdeImpl();
        this.detadevista = sumiaux.findByPagoc(this.selectedPago);
        for (int i = 0; i < this.detadevista.size(); i++) {
            sumidett.setChapa("N/S");
            sumidett.setId(this.detadevista.get(i).getAdeSuministro().getIdAde());
            sumidett.setFecha(this.detadevista.get(i).getAdeSuministro().getFecha());
            sumidett.setChofer("N/S");
            sumidett.setDescrip(this.detadevista.get(i).getAdeSuministro().getDescrip());
            sumidett.setLitros(1.0);
            sumidett.setMonto(this.detadevista.get(i).getAdeSuministro().getMonto());
            sumidett.setPrecio(1.0);
            sumidett.setTipo("Adelanto a Proveedor");
            sumidett.setTotal(this.detadevista.get(i).getAdeSuministro().getMonto() * -1);
            this.sumidetaux.add(sumidett);
            sumidett = new sumidet();
        }
        return sumidetaux;
    }

    public void setSumidetaux(List<sumidet> sumidetaux) {
        this.sumidetaux = sumidetaux;
    }

    public List<sumidet> getSumidetaux2() {
        return sumidetaux2;
    }

    public Double getTotalade() {
        return totalade;
    }

    public void setSumidetaux2(List<sumidet> sumidetaux2) {
        this.sumidetaux2 = sumidetaux2;
    }

    public sumidet getSelectedSumidet() {
        return selectedSumidet;
    }

    public void setSelectedSumidet(sumidet selectedSumidet) {
        this.selectedSumidet = selectedSumidet;
    }

    public List<PagoSumiDet> getDetpagovista() {
        return detpagovista;
    }

    public void setDetpagovista(List<PagoSumiDet> detpagovista) {
        this.detpagovista = detpagovista;
    }

    public List<PagoSuministro> getPagosumi() {
        PagoSuministroDao pagos = new PagoSuministroDaoImpl();
        this.pagosumi = pagos.findAll();
        return pagosumi;
    }

    public PagoSumiDet getSelectedPagosumi() {
        return selectedPagosumi;
    }

    public void setPagosumi(List<PagoSuministro> pagosumi) {
        this.pagosumi = pagosumi;
    }

    public void setSelectedPagosumi(PagoSumiDet selectedPagosumi) {
        this.selectedPagosumi = selectedPagosumi;
    }

    public List<PagoSumiDet> getPagoSumiDet() {
        return pagoSumiDet;
    }

    public List<PagoAdeDet> getDetadevista() {
        return detadevista;
    }

    public void setDetadevista(List<PagoAdeDet> detadevista) {
        this.detadevista = detadevista;
    }

    public void setPagoSumiDet(List<PagoSumiDet> pagoSumiDet) {
        this.pagoSumiDet = pagoSumiDet;
    }

    public ProveeCliente getSelectedProvee() {
        return selectedProvee;
    }

    public List<Suministro> getSelectedSuministros() {
        return selectedSuministros;
    }

    public void setSelectedSuministros(List<Suministro> selectedSuministros) {
        this.selectedSuministros = selectedSuministros;
    }

    public void setSelectedProvee(ProveeCliente selectedProvee) {
        this.selectedProvee = selectedProvee;
    }

    public List<Suministro> getSuministropen() {
        return suministropen;
    }

    public void setSuministropen(List<Suministro> suministropen) {
        this.suministropen = suministropen;
    }

    public Suministro getSelectedSuministro2() {
        return selectedSuministro2;
    }

    public void setSelectedSuministro2(Suministro selectedSuministro2) {
        this.selectedSuministro2 = selectedSuministro2;
    }

    public List<SelectItem> getSelectOneItemsTipo() {
        this.selectOneItemsTipo = new ArrayList<SelectItem>();
        SumiDao tipoDao = new SumiDaoImpl();
        List<TipoSuministro> tipos = tipoDao.SelectItems();
        for (TipoSuministro tipo : tipos) {
            SelectItem selecItem = new SelectItem(tipo.getIdTipo(), tipo.getNombre());
            this.selectOneItemsTipo.add(selecItem);
        }
        return selectOneItemsTipo;
    }

    public List<Suministro> getSuministros() {
        this.suministros = new ArrayList<Suministro>();
        SumiDao sumiDao = new SumiDaoImpl();
        this.suministros = sumiDao.findAll();
        return suministros;
    }

    public void setSuministros(List<Suministro> suministros) {
        this.suministros = suministros;
    }

    public Boolean getBoocrear() {
        return boocrear;
    }

    public Boolean getBootipo() {
        return bootipo;
    }

    public void setBootipo(Boolean bootipo) {
        this.bootipo = bootipo;
    }

    public TipoSuministro getSelectedTipo() {
        return selectedTipo;
    }

    public void setSelectedTipo(TipoSuministro selectedTipo) {
        this.selectedTipo = selectedTipo;
    }

    public List<TipoSuministro> getTipos() {
        SumiDao preFleteDao = new SumiDaoImpl();
        this.tipos = preFleteDao.tipos();
        return tipos;
    }

    public PrecioComb getSelectedPrecio() {
        return selectedPrecio;
    }

    public Boolean getValidar() {
        return validar;
    }

    public Boolean getPreboo() {
        return preboo;
    }

    public void setPreboo(Boolean preboo) {
        this.preboo = preboo;
    }

    public void setSelectedPrecio(PrecioComb selectedPrecio) {
        this.selectedPrecio = selectedPrecio;
    }

    public void setSelectedValeComb(ValeCombustible selectedValeComb) {
        this.selectedValeComb = selectedValeComb;
    }

    public Double getBustotalcomb() {
        return bustotalcomb;
    }

    public Double getBustotalvia() {
        return bustotalvia;
    }

    public Double getBustotalex() {
        return bustotalex;
    }

    public Double getBustotal() {
        return bustotal;
    }

    public Double getLitros() {
        return litros;
    }

    public Suministro getSelectedSuministro() {
        return selectedSuministro;
    }

    public void setSelectedSuministro(Suministro selectedSuministro) {
        this.selectedSuministro = selectedSuministro;
    }

    public UnidadProvee getSelectedUnidad() {
        return selectedUnidad;
    }

    public void setSelectedUnidad(UnidadProvee selectedUnidad) {
        this.selectedUnidad = selectedUnidad;
    }

    public void setLitros(Double litros) {
        this.litros = litros;
    }

    public void setBustotalcomb(Double bustotalcomb) {
        this.bustotalcomb = bustotalcomb;
    }

    public void setBustotalvia(Double bustotalvia) {
        this.bustotalvia = bustotalvia;
    }

    public void setBustotalex(Double bustotalex) {
        this.bustotalex = bustotalex;
    }

    public void setBustotal(Double bustotal) {
        this.bustotal = bustotal;
    }

    public Integer getBusnrocomb() {
        return busnrocomb;
    }

    public void setBusnrocomb(Integer busnrocomb) {
        this.busnrocomb = busnrocomb;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public PrecioSuministro getSelectedprecios() {
        return selectedprecios;
    }

    public void setSelectedprecios(PrecioSuministro selectedprecios) {
        this.selectedprecios = selectedprecios;
    }

    public Date getFechaini() {
        return fechaini;
    }

    public void setFechaini(Date fechaini) {
        this.fechaini = fechaini;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Proveedor getSelectedProveedor() {
        return selectedProveedor;
    }

    public void setSelectedProveedor(Proveedor selectedProveedor) {
        this.selectedProveedor = selectedProveedor;
    }

    public Date getFechafin1() {
        return fechafin1;
    }

    public Date getFechaini1() {
        return fechaini1;
    }

    public void setFechaini1(Date fechaini1) {
        this.fechaini1 = fechaini1;
    }

    public void setFechafin1(Date fechafin1) {
        this.fechafin1 = fechafin1;
    }

    public OrdenDeCarga getSelectedOrdenDeCarga() {
        return selectedOrdenDeCarga;
    }

    public void setSelectedOrdenDeCarga(OrdenDeCarga selectedOrdenDeCarga) {
        this.selectedOrdenDeCarga = selectedOrdenDeCarga;
    }

    public Boolean getReadonly() {
        return readonly;
    }

    public void setReadonly(Boolean readonly) {
        this.readonly = readonly;
    }

    public void onRowSelectPro(SelectEvent event) {
        this.selectedSuministro.setUnidadProvee(this.selectedUnidad);
        this.selectedPrecio = new PrecioComb();
    }

    public void onRowSelectTipo(SelectEvent event) {
        this.selectedSuministro.setTipoSuministro(this.selectedTipo);
        String msg;
        if (selectedSuministro.getTipoSuministro().getIdTipo() != 1) {
            SumiDao sumi = new SumiDaoImpl();
            this.selectedPrecioSumi = sumi.precio();
            if (this.selectedPrecioSumi.size() > 0) {
                for (int i = 0; i < selectedPrecioSumi.size(); i++) {
                    this.selectedprecios = selectedPrecioSumi.get(i);
                }
            } else {
                this.selectedprecios = new PrecioSuministro();
            }
            Double aux = 1.0;
            msg = "Ingrese el Monto Total.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedSuministro.setLitros(aux);
            System.out.print("SE SELECCIONO OTRO QUE NO ES COMBUSTIBLE");
            this.selectedSuministro.setPrecioSuministro(this.selectedprecios);
            preboo = true;
        } else {
            msg = "Seleccione el Precio del Combustible y los Litros.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            Double aux = 0.0;
            this.selectedSuministro.setLitros(aux);
            preboo = false;
        }
    }

    public void onRowSelectCamion(AjaxBehaviorEvent event) {
        String msg;
        CamionDao caDao = new CamionDaoImpl();
        this.camiones = caDao.findCamion(this.selectedCamion.getChapaCamion());
        if (this.camiones.size() > 0) {
            for (int i = 0; i < camiones.size(); i++) {
                this.selectedCamion = camiones.get(i);
            }
            this.selectedSuministro.setCamion(this.selectedCamion);
            this.selectedSuministro.setChofer(this.selectedOrdenDeCarga.getCamion().getChofer());
            msg = "Se selecciono la Orden de Carga y el Camion, cargue los datos.";
            this.bootipo = false;
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "No existe el Camion ingresado.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.bootipo = true;
        }
    }

    public void onRowSelectOrden(AjaxBehaviorEvent event) {
        String msg;
        OrdenDeCargaDao ordenDao = new OrdenDeCargaDaoImpl();
        this.orden = ordenDao.findUno(this.selectedOrdenDeCarga.getIdOrdenDeCarga());
        if (this.orden.size() > 0) {
            for (int i = 0; i < orden.size(); i++) {
                this.selectedOrdenDeCarga = orden.get(i);
            }

        } else {
            this.selectedOrdenDeCarga = new OrdenDeCarga();
        }

        if (this.selectedOrdenDeCarga.getCamion() == null) {
            msg = "No Existe la Orden De Carga Especificada.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedSuministro = new Suministro();
            this.selectedOrdenDeCarga = new OrdenDeCarga();
            this.selectedprecios = new PrecioSuministro();
            this.selectedUnidad = new UnidadProvee();
            this.orden = new ArrayList<OrdenDeCarga>();
            this.selectedCamion = new Camion();
            this.selectedTipo = new TipoSuministro();
            this.selectedSuministro.setFecha(new Date());
            this.bootipo = true;
        } else {
            if (this.selectedOrdenDeCarga.getEstadoliq().equals("Pagado")) {
                msg = "El numero de Orden Especificado ya fue liquidado.";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                if (this.selectedOrdenDeCarga.getIdOrdenDeCarga() == 1) {
                    this.bootipo = true;
                    this.readonly = false;
                    this.selectedSuministro.setOrdenDeCarga(this.selectedOrdenDeCarga);
                    msg = "Se selecciono la orden para suministros, ingrese la chapa del Camion.";
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                } else {
                    this.selectedCamion = this.selectedOrdenDeCarga.getCamion();
                    this.bootipo = false;
                    this.selectedSuministro.setCamion(this.selectedCamion);
                    this.selectedSuministro.setChofer(this.selectedOrdenDeCarga.getCamion().getChofer());
                    this.selectedSuministro.setOrdenDeCarga(this.selectedOrdenDeCarga);
                    msg = "Se selecciono la Orden de Carga.";
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }

            }
        }
    }

    public void onRowSelectMonto(AjaxBehaviorEvent event) {
        Double litros = 0.0, precio = 0.0, total = 0.0;
        litros = this.selectedSuministro.getLitros();
        precio = this.selectedprecios.getPrecio();
        total = litros * precio;
        this.selectedSuministro.setMontoTotal(total);
        this.selectedSuministro.setMonto(total);
        if (total > 0) {
            this.boocrear = false;
        }
    }

    public void onRowSelectLitros(AjaxBehaviorEvent event) {
        Double litros = 0.0, precio = 0.0, total = 0.0;
        //litros=this.selectedSuministro.getLitros();
        if (!this.selectedSuministro.getTipoSuministro().getNombre().equals("Combustible")) {
            precio = this.selectedprecios.getPrecio();
            total = this.selectedSuministro.getMontoTotal();
            litros = 1.0;
            this.selectedSuministro.setLitros(litros);
            this.selectedSuministro.setMonto(total);
            if (litros > 0) {
                this.boocrear = false;
            }
        } else {
            precio = this.selectedprecios.getPrecio();
            total = this.selectedSuministro.getMontoTotal();
            litros = total / precio;
            this.selectedSuministro.setLitros(litros);
            this.selectedSuministro.setMonto(total);
            if (litros > 0) {
                this.boocrear = false;
            }
        }

    }

    public List<PrecioComb> getPrecios() {
        PrecioCombDao precioDao = new PrecioCombDaoImpl();
        this.precios = precioDao.findPrecios(this.selectedProveedor);
        return precios;
    }
    private boolean chkBoxChecked;

    public boolean isChkBoxChecked() {
        return chkBoxChecked;
    }

    public boolean isBtnDisabled() {

        return !this.chkBoxChecked;
    }

    private boolean chkBoxCheckedo;

    public boolean isChkBoxCheckedo() {
        return chkBoxChecked;
    }

    public boolean isBtnDisabledo() {

        return !this.chkBoxCheckedo;
    }

    public void setChkBoxCheckedo(boolean chkBoxCheckedo) {
        this.chkBoxCheckedo = chkBoxCheckedo;
    }

    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/comblist.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getComblist()));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=comb.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public List<Combustible> getCombustibles() {

        return combustibles;
    }

    public List<Camion> getCamiones() {
        return camiones;
    }

    public ValeCombustible getSelectedValeCombustible() {
        return selectedValeCombustible;
    }

    public Camion getSelectedCamion() {
        return selectedCamion;
    }

    public Viatico getSelectedViatico() {
        return selectedViatico;
    }

    public List<Combustible> getComblist() {
        this.totalvia = this.sumarViatico(this.comblist);
        this.nrocomb = this.comblist.size();
        return comblist;
    }

    public Double getTotalvia() {
        return totalvia;
    }

    public void setTotalvia(Double totalvia) {
        this.totalvia = totalvia;
    }

    public Integer getNrocomb() {
        return nrocomb;
    }

    public void setNrocomb(Integer nrocomb) {
        this.nrocomb = nrocomb;
    }

    public void setComblist(List<Combustible> comblist) {
        this.comblist = comblist;
    }

    public void setSelectedViatico(Viatico selectedViatico) {
        this.selectedViatico = selectedViatico;
    }

    public void setSelectedCombustible(Combustible selectedCombustible) {
        this.selectedCombustible = selectedCombustible;
    }

    public void setSelectedValeCombustible(ValeCombustible selectedValeCombustible) {
        this.selectedValeCombustible = selectedValeCombustible;
    }

    public void setSelectedCamion(Camion selectedCamion) {
        this.selectedCamion = selectedCamion;
    }

    public Double getTotalcomb() {
        return totalcomb;
    }

    public Double getTotalex() {
        return totalex;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotalcomb(Double totalcomb) {
        this.totalcomb = totalcomb;
    }

    public void setTotalex(Double totalex) {
        this.totalex = totalex;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void onRowSelectVale(SelectEvent event) {
        this.selectedCombustible.setValeCombustible(this.selectedValeCombustible);
        this.selectedCombustible.setPrecio(this.selectedValeCombustible.getPrecioComb());
        this.selectedCombustible.setExtras(this.selectedValeCombustible.getExtras());
        this.selectedCombustible.setLitros(this.selectedValeCombustible.getLitros());
        this.selectedCombustible.setMontoTotal(this.selectedValeCombustible.getMontoTotal());
        this.selectedCombustible.setMontoComb(this.selectedValeCombustible.getMontoComb());
        this.selectedCombustible.setViatico_1(this.selectedValeCombustible.getViatico());
    }

    public void onRowSelectCamion(SelectEvent event) {
        for (int i = 0; i < this.comblist.size(); i++) {
            this.selectedCombustible = this.comblist.get(i);
        }
    }

    public void onRowSelectComb(SelectEvent event) {
        this.totalvia = this.sumarViatico(this.comblist);
        this.nrocomb = this.comblist.size();
        this.totalcomb = this.sumarComb(this.comblist);
        this.totalex = this.sumarExtras(this.comblist);
    }

    public List<ValeCombustible> getValespen() {
        ValeCombDao valeCombDao = new ValeCombDaoImpl();
        this.valespen = valeCombDao.findVales(this.selectedCamion);
        return valespen;
    }

    public String cargarArray(ActionEvent event) {
        System.out.print("AAAAAAA");
        String msg, hola = "es el problema";
        FacesMessage message = null;
        SumiDao sumi = new SumiDaoImpl();
        sumidet sumidett = new sumidet();
        this.sumidetaux = new ArrayList<sumidet>();
        this.sumidetaux1 = new ArrayList<sumidet>();
        this.sumidetaux2 = new ArrayList<sumidet>();
        this.suministropen = sumi.findprovee(this.selectedProvee.getIdProveecli());
        System.out.print(this.suministropen.size());
        for (int i = 0; i < this.suministropen.size(); i++) {
            sumidett.setChapa(this.suministropen.get(i).getCamion().getChapaCamion());
            sumidett.setId(this.suministropen.get(i).getIdSuministro());
            sumidett.setFecha(this.suministropen.get(i).getFecha());
            sumidett.setChofer(this.suministropen.get(i).getChofer().getNombre() + " " + this.suministropen.get(i).getChofer().getApellido());
            sumidett.setDescrip(this.suministropen.get(i).getDescrip());
            sumidett.setLitros(this.suministropen.get(i).getLitros());
            sumidett.setMonto(this.suministropen.get(i).getMonto());
            sumidett.setPrecio(this.suministropen.get(i).getPrecioSumi());
            sumidett.setTipo(this.suministropen.get(i).getTipoSuministro().getNombre());
            sumidett.setTotal(this.suministropen.get(i).getMonto());
            this.sumidetaux1.add(sumidett);
            sumidett = new sumidet();
        }
        List<AdeSuministro> adeaux;
        adeaux = new ArrayList<AdeSuministro>();
        AdeSumiDao sumiaux = new AdeSumiDaoImpl();
        adeaux = sumiaux.proveedor(this.selectedProvee);
        for (int i = 0; i < adeaux.size(); i++) {
            sumidett.setChapa("N/S");
            sumidett.setId(adeaux.get(i).getIdAde());
            sumidett.setFecha(adeaux.get(i).getFecha());
            sumidett.setChofer("N/S");
            sumidett.setDescrip(adeaux.get(i).getDescrip());
            sumidett.setLitros(1.0);
            sumidett.setMonto(adeaux.get(i).getMonto());
            sumidett.setPrecio(1.0);
            sumidett.setTipo("Adelanto a Proveedor");
            sumidett.setTotal(adeaux.get(i).getMonto() * -1);
            this.sumidetaux1.add(sumidett);
            sumidett = new sumidet();
        }
        System.out.print("ACA ESTAN LOS CARGADOS");
        System.out.print(this.sumidetaux.size());
        if (this.sumidetaux1.size() == 0) {
            msg = "El Proveedor no posee pendientes.";
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
        } else {
            msg = "Los pendientes del Proveedor.";
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        }
        return msg;
    }

    public void cargarDescrip(ActionEvent event) {
        this.descrip1 = this.selectedPago.getDescrip();
    }

    public void btnCreateComb(ActionEvent actionEvent) {
        SumiDao sumiDao = new SumiDaoImpl();
        String msg;
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
        this.selectedSuministro.setPrecioSumi(this.selectedprecios.getPrecio());
        this.selectedSuministro.setEstadoCobro("Pendiente");
        this.selectedSuministro.setEstadoPago("Pendiente");
        this.selectedSuministro.setUsuario(usuario);
        System.out.print(this.selectedOrdenDeCarga.getIdOrdenDeCarga());
        if (sumiDao.create(this.selectedSuministro)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedSuministro = new Suministro();
            this.selectedOrdenDeCarga = new OrdenDeCarga();
            this.selectedprecios = new PrecioSuministro();
            this.selectedUnidad = new UnidadProvee();
            this.orden = new ArrayList<OrdenDeCarga>();
            this.selectedCamion = new Camion();
            this.selectedTipo = new TipoSuministro();
            this.selectedSuministro.setFecha(new Date());
            this.readonly = true;
            this.bootipo = true;
            this.boocrear = true;
        } else {
            msg = "Error al crear registro, asegurate de cargar todos los datos.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnUpdateComb(ActionEvent actionEvent) {
        SumiDao sumiDao = new SumiDaoImpl();
        String msg;
        if (sumiDao.update(this.selectedSuministro)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteComb(ActionEvent actionEvent) {
        SumiDao sumiDao = new SumiDaoImpl();
        this.selectedValeCombustible = this.selectedCombustible.getValeCombustible();
        String msg;
        if (sumiDao.delete(this.selectedSuministro.getIdSuministro())) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnIniciar(ActionEvent actionEvent) {
        this.selectedCamion = new Camion();
        this.selectedSuministro = new Suministro();
        this.selectedSuministro2 = new Suministro();
        this.selectedUnidad = new UnidadProvee();
        this.selectedCombustible = new Combustible();
        this.selectedPago = new PagoSuministro();
        this.camiones = new ArrayList<Camion>();
        this.pagosumi = new ArrayList<PagoSuministro>();
        this.precios = new ArrayList<PrecioComb>();
        this.tipo = new ArrayList<TipoViatico>();
        this.tipos = new ArrayList<TipoSuministro>();
        this.suministros = new ArrayList<Suministro>();
        this.selectedTipo = new TipoSuministro();
        this.selectedViatico = new Viatico();
        this.buscomb = new ArrayList<Combustible>();
        this.selectedProveedor = new Proveedor();
        this.selectedCombustible.setFecha(new Date());
        this.selectedOrdenDeCarga = new OrdenDeCarga();
        this.selectedProvee = new ProveeCliente();
        this.orden = new ArrayList<OrdenDeCarga>();
        this.prepro = new ArrayList<PrecioSuministro>();
        this.validar = false;
        this.preboo = true;
        this.bootipo = true;
        this.boocrear = true;
        this.selectedprecios = new PrecioSuministro();
        this.selectedSuministro.setFecha(dia);
        this.suministropen = new ArrayList<Suministro>();
        this.selectedSuministros = new ArrayList<Suministro>();
        this.selectedPagosumi = new PagoSumiDet();
        this.pagoSumiDet = new ArrayList<PagoSumiDet>();
        this.pagoSumiDetAux = new ArrayList<PagoSumiDet>();
        this.detpagovista = new ArrayList<PagoSumiDet>();
        this.detadevista = new ArrayList<PagoAdeDet>();
        this.sumidetaux = new ArrayList<sumidet>();
        this.sumidetaux1 = new ArrayList<sumidet>();
        this.sumidetaux2 = new ArrayList<sumidet>();
        this.totalsumi = 0.0;
        this.totalade = 0.0;
        this.selectedSumidet = new sumidet();
    }

    private Double sumarViatico(List<Combustible> comblist) {
        Double suma = 0.0;
        for (int i = 0; i < comblist.size(); i++) {
            suma = suma + comblist.get(i).getViatico_1();
        }
        return suma;
    }

    private Double sumarComb(List<Combustible> comblist) {
        Double suma = 0.0;
        for (int i = 0; i < comblist.size(); i++) {
            suma = suma + comblist.get(i).getMontoComb();
        }
        return suma;
    }

    private Double sumarExtras(List<Combustible> comblist) {
        Double suma = 0.0;
        for (int i = 0; i < comblist.size(); i++) {
            suma = suma + comblist.get(i).getExtras();
        }
        return suma;
    }

    private Double sumarLitros(List<Combustible> comblist) {
        Double suma = 0.0;
        for (int i = 0; i < comblist.size(); i++) {
            suma = suma + comblist.get(i).getLitros();
        }
        return suma;
    }

    private Double sumarTotal(List<Combustible> comblist) {
        Double suma = 0.0;
        for (int i = 0; i < comblist.size(); i++) {
            suma = suma + comblist.get(i).getMontoTotal();
        }
        return suma;
    }

    public void btnCargar(ActionEvent event) {
        this.descrip = this.selectedPago.getDescrip();
        this.descrip1 = this.selectedPago.getEstado();
        System.out.print("Este es el descrip");
        System.out.print(this.descrip);
        System.out.print("Este es el estado");
        System.out.print(this.descrip1);
    }

    public void btnCargarDescrip(ActionEvent event) {

    }

    public void btnCargarPro(ActionEvent event) {
        CombDao comb = new CombDaoImpl();
        this.buscomb = comb.findCamionP(this.selectedProveedor, this.fechaini1, this.fechafin1);
        this.busnrocomb = this.buscomb.size();
        this.bustotalcomb = this.sumarComb(this.buscomb);
        this.bustotalvia = this.sumarViatico(this.buscomb);
        this.bustotalex = this.sumarExtras(this.buscomb);
        this.bustotal = this.sumarTotal(this.buscomb);
        this.litros = this.sumarLitros(this.buscomb);

    }

    public void onRowSelectPrecio(SelectEvent event) {
        this.selectedSuministro.setPrecioSuministro(this.selectedprecios);
        //banpro = 1;
        //this.selectedValeComb.setMontoTotal(this.selectedValeComb.getLitros()*this.selectedValeComb.getPrecioComb());
        //this.selectedValeComb.setTipoCombustible(this.selectedPrecio.getTipoCombustible());
    }

    public void btnProcesar(ActionEvent event) {
        String msg;
        FacesMessage message;
        Double li = this.selectedSuministro.getLitros();
        PrecioSuministro precio;
        Double total;
        precio = this.selectedprecios;
        if (precio == null) {
            precio = new PrecioSuministro();
        }
        System.out.println("El precio seleccionado");
        System.out.println(precio.getPrecio());
        System.out.println("VIATICO");
        System.out.println(this.selectedCombustible.getViatico_1());
        System.out.println("EXTRAS");
        System.out.println(this.selectedCombustible.getExtras());
        System.out.println("LITROS");
        System.out.println(this.selectedCombustible.getLitros());
        if (precio.getIdPrecioSumi() != 3 && precio.getPrecio() > 0.0) {
            this.selectedValeComb.setPrecioComb(this.selectedPrecio.getMonto());
            this.selectedValeComb.setMontoComb(this.selectedCombustible.getLitros() * this.selectedValeComb.getPrecioComb());
            System.out.println(this.selectedValeComb.getMontoComb());
            this.selectedValeComb.setTipoCombustible(this.selectedPrecio.getTipoCombustible());
            total = this.selectedValeComb.getViatico() + this.selectedValeComb.getExtras() + this.selectedValeComb.getMontoComb();
            this.selectedValeComb.setMontoTotal(total);
            this.selectedCombustible.setExtras(this.selectedValeComb.getExtras());
            this.selectedCombustible.setViatico_1(this.selectedValeComb.getViatico());
            if (this.selectedValeComb.getMontoComb() > 0 || this.selectedValeComb.getExtras() > 0) {
                this.chkBoxChecked = true;
                msg = "Se proceso con existo";
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
            } else {
                msg = "Ingresar el litraje o los extras.";
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            }

            //this.chkBoxChecked = true;                
        } else {
            msg = "Seleccionar Precio";
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");

        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        //return msg;
    }

    public void exportarPDF2(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException {        
        codvale = this.selectedSuministro.getIdSuministro();
        if (this.selectedSuministro.getTipoSuministro().getIdTipo() == 0 || this.selectedSuministro.getTipoSuministro().getIdTipo() == 3) {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/viatico.jasper"));
            String letras = "";
            letras = Numero_a_Letra.cantidadConLetra(this.selectedSuministro.getMonto().toString());
            System.out.print("Este es el codigo del suministro");
            System.out.print(codvale);
            usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
            String nomusu = this.usuario.getUsuario();
            Class.forName("org.postgresql.Driver");
            conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5433/newtranspbase", "postgres", "macorittogo");
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("vCodviatico", codvale);
            parametros.put("vUsu", nomusu);
            parametros.put("letras", letras);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=suministro N° " + this.selectedSuministro.getIdSuministro() + ".pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } else {
            if (this.selectedSuministro.getTipoSuministro().getIdTipo() == 1) {
                File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/sumicomb.jasper"));
                String letras = "";
                letras = Numero_a_Letra.cantidadConLetra(this.selectedSuministro.getMonto().toString());
                System.out.print("Este es el codigo del suministro");
                System.out.print(codvale);
                usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
                String nomusu = this.usuario.getUsuario();
                Class.forName("org.postgresql.Driver");
                conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5433/newtranspbase", "postgres", "macorittogo");
                Map<String, Object> parametros = new HashMap<String, Object>();
                parametros.put("vCodviatico", codvale);
                parametros.put("vUsu", nomusu);
                parametros.put("letras", letras);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.addHeader("Content-disposition", "attachment; filename=suministro N° " + this.selectedSuministro.getIdSuministro() + ".pdf");
                ServletOutputStream stream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
                stream.close();
                FacesContext.getCurrentInstance().responseComplete();
            }else{
                File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/sumicre.jasper"));
                String letras = "";
                letras = Numero_a_Letra.cantidadConLetra(this.selectedSuministro.getMonto().toString());
                System.out.print("Este es el codigo del suministro");
                System.out.print(codvale);
                usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
                String nomusu = this.usuario.getUsuario();
                Class.forName("org.postgresql.Driver");
                conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5433/newtranspbase", "postgres", "macorittogo");
                Map<String, Object> parametros = new HashMap<String, Object>();
                parametros.put("vCodviatico", codvale);
                parametros.put("vUsu", nomusu);
                parametros.put("letras", letras);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.addHeader("Content-disposition", "attachment; filename=suministro N° " + this.selectedSuministro.getIdSuministro() + ".pdf");
                ServletOutputStream stream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
                stream.close();
                FacesContext.getCurrentInstance().responseComplete();
            }
        }

    }

    public void onRowSelectProvee(SelectEvent event) {
        this.selectedPago.setProveeCliente(this.selectedProvee);
    }

    public void bntAdd(sumidet sumidet) {
        SumiDao sumi = new SumiDaoImpl();
//        this.selectedSuministros= sumi.findOne(sumidet.getId());
//        for(int i=0; i<this.selectedSuministros.size(); i++){
//            this.selectedSuministro=this.selectedSuministros.get(i);
//        }
//        this.selectedPagosumi.setIdDetalle(sumidet.getId());
//        this.selectedPagosumi.setCant(sumidet.getLitros());
//        this.selectedPagosumi.setMonto(sumidet.getMonto());
//        this.selectedPagosumi.setPrecioComb(sumidet.getPrecio());
//        this.selectedPagosumi.setSuministro(this.selectedSuministro);
        if (sumidet.getTipo().equals("Adelanto a Proveedor") && sumidet.getMonto() > 0) {
            sumidet.setMonto(sumidet.getMonto() * -1);
        }
        this.sumidetaux2.add(sumidet);
        this.pagoSumiDet.add(this.selectedPagosumi);
        this.sumidetaux1.remove(sumidet);
        sumarTotalComb(this.sumidetaux2);
//        this.totalActivo = this.sumarActivo(detextracto);
//        this.totalPasivo = this.sumarPasivo(detextracto);
//        this.acufle = this.sumarFlete(detextracto);
//        this.acucomb = this.sumarComb(detextracto);
//        this.acuade = this.sumarAdelanto(detextracto);
//        this.acucre = this.sumarCredito(detextracto);
//        this.acufal = this.sumarFaltante(detextracto);
//        this.acureci = this.sumarRecibo(detextracto);
//        this.acuse = this.sumarSeguro(detextracto);
//        this.acuvarios = this.sumarVarios(detextracto);
//        this.acuvia = this.sumarVia(detextracto);
//        totalcobrar = (this.totalActivo-this.totalPasivo);      
        this.selectedPagosumi = new PagoSumiDet();
        this.selectedSuministro2 = new Suministro();
    }

    public void bntDescargar(sumidet sumidet) {
        this.sumidetaux1.add(sumidet);
        this.sumidetaux2.remove(sumidet);
        sumarTotalComb(this.sumidetaux2);
        //for(int i=0; i <this.detpagov.size(); i++){
        //if(this.detpagov.get(i)==detallePagoVarios){
        //selectedAux.add(detallePagoVarios.getVarios());
        //}
        //}
//        this.totalviatico = this.sumarTotalViatico(detpagoc);
//        this.totalcomb = this.sumarTotalComb(detpagoc);
//        this.totalex = this.sumarTotalExtras(detpagoc);
//        this.selectedPago.setTotal(this.totalviatico+this.totalcomb+this.totalex);
    }

    private Double sumarTotalComb(List<sumidet> detsumi) {
        Double sumas = 0.0, sumaa = 0.0;
        for (int i = 0; i < detsumi.size(); i++) {
            if (detsumi.get(i).getTipo().equals("Adelanto a Proveedor")) {
                sumaa = sumaa + detsumi.get(i).getMonto();
            } else {
                sumas = sumas + detsumi.get(i).getMonto();
            }
        }
        this.totalsumi = sumas;
        this.totalade = sumaa;
        sumaa = sumaa * -1;
        this.total = sumas - (sumaa);
        return sumas;
    }

    public void btnCreatePago() {
        PagoSuministroDao pagoDao = new PagoSuministroDaoImpl();
        DetAde adeDet = new PagoAdeImpl();
        String msg;
        FacesMessage message = null;
        this.selectedPago.setEstado("Pagado");
        this.selectedPago.setDescrip("");
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
        this.selectedPago.setFactura("");
        this.selectedPago.setMontoSumi(this.totalsumi);
        this.selectedPago.setMontoAdelanto(this.totalade);
        this.selectedPago.setMontoPagar(this.total);
        this.selectedPago.setSaldo(this.total);
        this.selectedPago.setUsuario(usuario);
        SumiDao sumi = new SumiDaoImpl();
        List<PagoSumiDet> detpagoaux = new ArrayList<PagoSumiDet>();
        PagoSumiDet selectedDet = new PagoSumiDet();
        AdeSumiDao adesumiaux = new AdeSumiDaoImpl();
        List<AdeSuministro> adeaux;
        AdeSumiDao sumiaux = new AdeSumiDaoImpl();
        AdeSuministro selectedAde = new AdeSuministro();
        adeaux = new ArrayList<AdeSuministro>();
        PagoAdeDet selectedAdeDet = new PagoAdeDet();
        List<PagoAdeDet> detAde = new ArrayList<PagoAdeDet>();
        for (int i = 0; i < this.sumidetaux2.size(); i++) {
            if (this.sumidetaux2.get(i).getTipo().equals("Adelanto a Proveedor")) {
                adeaux = adesumiaux.findOne(this.sumidetaux2.get(i).getId());
                for (int e = 0; e < adeaux.size(); e++) {
                    selectedAde = adeaux.get(e);
                }
                selectedAdeDet.setMonto(selectedAde.getMonto());
                selectedAdeDet.setDescrip(selectedAde.getDescrip());
                selectedAdeDet.setAdeSuministro(selectedAde);
                selectedAdeDet.setUsuario(usuario);
                detAde.add(selectedAdeDet);
                selectedAde = new AdeSuministro();
                selectedAdeDet = new PagoAdeDet();
            } else {
                this.selectedSuministros = sumi.findOne(this.sumidetaux2.get(i).getId());
                for (int e = 0; e < this.selectedSuministros.size(); e++) {
                    this.selectedSuministro = this.selectedSuministros.get(e);
                }
                selectedDet.setCant(this.selectedSuministro.getLitros());
                selectedDet.setMonto(this.selectedSuministro.getMontoTotal());
                selectedDet.setPrecioComb(this.selectedSuministro.getPrecioSumi());
                selectedDet.setSuministro(this.selectedSuministro);
                detpagoaux.add(selectedDet);
                selectedDet = new PagoSumiDet();
                this.selectedSuministro = new Suministro();
            }

        }
        if (pagoDao.create(this.selectedPago, detpagoaux)) {
            for (int i = 0; i < detpagoaux.size(); i++) {
                this.selectedSuministro = detpagoaux.get(i).getSuministro();
                this.selectedSuministro.setEstadoPago("Pagado");
                SumiDao combDao = new SumiDaoImpl();
                if (combDao.update(this.selectedSuministro)) {
                }
            }
            for (int a = 0; a < detAde.size(); a++) {
                System.out.print("Este es la cantidad del detalle");
                System.out.print(detAde.size());
                detAde.get(a).setPagoSuministro(this.selectedPago);
                if (adeDet.create(detAde.get(a))) {
                    selectedAde = detAde.get(a).getAdeSuministro();
                    selectedAde.setEstadoPago("Pagado");
                    AdeSumiDao combDao = new AdeSumiDaoImpl();
                    if (combDao.update(selectedAde)) {
                    }
                } else {
                    msg = "No guarda el det";
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
                }
            }

            msg = "Se Creo correctamente el registro";
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, " ");
        } else {
            msg = "Error al Crear el registro";
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void btnDeletePago(ActionEvent actionEvent) {
        PagoSuministroDao pagoDao = new PagoSuministroDaoImpl();
        String msg;
        FacesMessage message;
        PagoSumiDetDao detpagoDao = new PagoSumiDaoImpl();
        DetAde pagoade = new PagoAdeImpl();
        this.detpagovista = detpagoDao.findByPagoc(this.selectedPago);
        this.detadevista = pagoade.findByPagoc(this.selectedPago);
        AdeSuministro selectedAde = new AdeSuministro();
        if (pagoDao.delete(this.selectedPago)) {
            msg = "Se Elimino correctamente el registro";
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, " ");
            for (int i = 0; i < detpagovista.size(); i++) {
                this.selectedSuministro = detpagovista.get(i).getSuministro();
                this.selectedSuministro.setEstadoPago("Pendiente");
                SumiDao sumiDao = new SumiDaoImpl();
                if (sumiDao.update(this.selectedSuministro)) {
                }
            }
            for (int i = 0; i < detadevista.size(); i++) {
                selectedAde = detadevista.get(i).getAdeSuministro();
                selectedAde.setEstadoPago("Pendiente");
                AdeSumiDao sumiDao = new AdeSumiDaoImpl();
                if (sumiDao.update(selectedAde)) {
                }
            }
        } else {
            msg = "Error al Eliminar el registro";
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, " ");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void cargarDescrip1(ActionEvent event) {
        System.out.print("INTENTA CARGAR DESCRIP");
        this.descrip1 = this.selectedPago.getDescrip();
        this.estadoaux = this.selectedPago.getEstado();
    }

    public void cargarEstado(ActionEvent event) {
        System.out.print("INTENTA CARGAR EL ESTADO");
        this.estadoaux = this.selectedPago.getEstado();
    }

    public void btnUpdatePago() {
        System.out.print("Vemos si carga o no el descrip");
        System.out.print(descrip1);
        System.out.print("El pago seleccionado es");
        System.out.print(this.selectedPago.getIdPagosumi());
        PagoSuministroDao pagoDao = new PagoSuministroDaoImpl();
        Integer bande;
        String msg;
        String est;
        String a;
        a = "Anulado";
        this.selectedPago.setDescrip(this.descrip1);
        System.out.print("Este es el estado");
        System.out.print(this.selectedPago.getEstado());
        //this.selectedPago.setEstado(this.estadoaux);
        est = this.selectedPago.getEstado();
        PagoSumiDetDao detpagoDao = new PagoSumiDaoImpl();
        this.detpagovista = detpagoDao.findByPagoc(this.selectedPago);
        if (a.equals(this.selectedPago.getEstado())) {
            bande = 1;
            this.selectedPago.setEstado("Anulado");
        } else {
            this.selectedPago.setEstado("Pagado");
            bande = 0;
        }
        System.out.println(bande);
        System.out.println(this.selectedPago.getDescrip());
        System.out.println(this.selectedPago.getEstado());
        AdeSuministro selectedAde = new AdeSuministro();
        if (pagoDao.update(this.selectedPago)) {
            System.out.print("Entra aca");
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, " ");
            FacesContext.getCurrentInstance().addMessage(null, message);
            if (bande == 1) {
                for (int i = 0; i < detpagovista.size(); i++) {
                    this.selectedSuministro = detpagovista.get(i).getSuministro();
                    this.selectedSuministro.setEstadoPago("Pendiente");
                    SumiDao combDao = new SumiDaoImpl();
                    if (combDao.update(this.selectedSuministro)) {
                    }
                }
            } else {
                for (int i = 0; i < detpagovista.size(); i++) {
                    this.selectedSuministro = detpagovista.get(i).getSuministro();
                    this.selectedSuministro.setEstadoPago("Pendiente");
                    SumiDao combDao = new SumiDaoImpl();
                    if (combDao.update(this.selectedSuministro)) {
                    }
                }
            }
            if (bande == 1) {
                for (int i = 0; i < detadevista.size(); i++) {
                    selectedAde = detadevista.get(i).getAdeSuministro();
                    selectedAde.setEstadoPago("Pendiente");
                    AdeSumiDao sumiDao = new AdeSumiDaoImpl();
                    if (sumiDao.update(selectedAde)) {
                    }
                }
            } else {
                for (int i = 0; i < detadevista.size(); i++) {
                    selectedAde = detadevista.get(i).getAdeSuministro();
                    selectedAde.setEstadoPago("Pendiente");
                    AdeSumiDao sumiDao = new AdeSumiDaoImpl();
                    if (sumiDao.update(selectedAde)) {
                    }
                }
            }

        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, " ");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

}
