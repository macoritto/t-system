/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.OrdenDeCargaDao;
import dao.OrdenDeCargaDaoImpl;
import dao.PrecioCombDao;
import dao.PrecioCombDaoImpl;
import dao.ValeCombDao;
import dao.ValeCombDaoImpl;
import dao.ViaticoDao;
import dao.ViaticoDaoImpl;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Camion;
import model.OrdenDeCarga;
import model.PrecioComb;
import model.Proveedor;
import model.Usuario;
import model.ValeCombustible;
import model.Viatico;
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
public class valecomBean {
    private List<ValeCombustible> vales;
    private List<ValeCombustible> valelist;
    private List<Viatico> viatico;
    private List<OrdenDeCarga> orden;
    private List<ValeCombustible> reportes;
    private List<Viatico> viapen;
    private List<ValeCombustible> pendientes;
    private List<ValeCombustible> anulados;
    private List<ValeCombustible> aceptados;
    private List<PrecioComb> precios;
    private List<OrdenDeCarga> ordenes;
    private ValeCombustible selectedValeComb;
    private ValeCombustible selectedValeComb2;
    private Camion selectedCamion;
    private OrdenDeCarga selectedOrdenDeCarga;
    private Proveedor selectedProveedor;
    private Viatico selectedViatico;
    private PrecioComb selectedPrecio;
    private Usuario usuario;
    private Integer band;
    private Integer ban;
    private Integer idv;
    private Integer codvale;
    private Connection conexion;
    private Integer banpro =0;
    public valecomBean() {
        this.vales = new ArrayList<ValeCombustible>();
        ValeCombDao valeDao = new ValeCombDaoImpl();
        this.vales = valeDao.findAll();
        this.valelist = new ArrayList<ValeCombustible>();
        this.ordenes =new ArrayList<OrdenDeCarga>();
        this.viatico = new ArrayList<Viatico>();
        this.viapen =new ArrayList<Viatico>();
        this.aceptados =new ArrayList<ValeCombustible>();
        this.aceptados = valeDao.findAceptado();
        this.pendientes =new ArrayList<ValeCombustible>();
        this.pendientes = valeDao.findPendiente();
        this.anulados =new ArrayList<ValeCombustible>();
        this.anulados = valeDao.findAnulado();
        this.precios =new ArrayList<PrecioComb>();
        this.reportes =new ArrayList<ValeCombustible>();
        this.selectedCamion = new Camion();
        this.selectedValeComb = new ValeCombustible();
        this.selectedValeComb2 = new ValeCombustible();
        this.selectedOrdenDeCarga = new OrdenDeCarga();
        this.selectedProveedor = new Proveedor();
        this.selectedViatico = new Viatico();
        this.orden = new ArrayList<OrdenDeCarga>();
        this.band = 0;
        this.ban = 0;
    }
    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/valelist.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getValelist()));
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=reporte_vales_comb.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
      public void exportarPDF2(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/vale.jasper"));
      codvale= this.selectedValeComb2.getIdValeCombustible();
      Class.forName("org.postgresql.Driver");
      conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/transpbase", "postgres", "macorittogo");
      Map<String, Object> parametros = new HashMap<String, Object>();
      parametros.put("vCodvale", codvale);
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=vale_comb.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
      public void exportarPDF3(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/vale.jasper"));
      codvale= this.selectedValeComb.getIdValeCombustible();
      Class.forName("org.postgresql.Driver");
      conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/transpbase", "postgres", "macorittogo");
      Map<String, Object> parametros = new HashMap<String, Object>();
      parametros.put("vCodvale", codvale);
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=vale_comb.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
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
    public List<ValeCombustible> getVales() {
        
        return vales;
    }

    public List<Viatico> getViatico() {
        ViaticoDao viaticoDao = new ViaticoDaoImpl();
        this.viatico = viaticoDao.findOne();
        return viatico;
    }
    
    public List<ValeCombustible> getReportes() {
        ValeCombDao valeDao = new ValeCombDaoImpl();
        this.reportes = valeDao.findReporte(this.selectedValeComb);
        return reportes;
    }

    public List<ValeCombustible> getPendientes() {
        
        return pendientes;
    }    
    

        public List<ValeCombustible> getAnulados() {
        
        return anulados;
    }    

    public List<ValeCombustible> getAceptados() {
        ValeCombDao valeDao = new ValeCombDaoImpl();
        
        return aceptados;
    }    


    public List<Viatico> getViapen() {
        ViaticoDao viaticoDao = new ViaticoDaoImpl();
        this.viapen = viaticoDao.findViaticos(this.selectedCamion);
        return viapen;
    }

    public List<PrecioComb> getPrecios() {
        PrecioCombDao precioDao = new PrecioCombDaoImpl();
        this.precios = precioDao.findPrecios(this.selectedProveedor);
        return precios;
    }    
    public ValeCombustible getSelectedValeComb() {
        return selectedValeComb;
    }

    
    public Camion getSelectedCamion() {
        return selectedCamion;
    }

    public OrdenDeCarga getSelectedOrdenDeCarga() {
        return selectedOrdenDeCarga;
    }

    public Proveedor getSelectedProveedor() {
        return selectedProveedor;
    }

    public Viatico getSelectedViatico() {
        return selectedViatico;
    }

    public PrecioComb getSelectedPrecio() {
        return selectedPrecio;
    }

    
    public void setSelectedProveedor(Proveedor selectedProveedor) {
        this.selectedProveedor = selectedProveedor;
    }

    public void setSelectedViatico(Viatico selectedViatico) {
        this.selectedViatico = selectedViatico;
    }

    public void setSelectedPrecio(PrecioComb selectedPrecio) {
        this.selectedPrecio = selectedPrecio;
    }  

    public void setSelectedValeComb(ValeCombustible selectedValeComb) {
        this.selectedValeComb = selectedValeComb;
    }

    public void setSelectedCamion(Camion seletedCamion) {
        this.selectedCamion = seletedCamion;
    }

    public void setSelectedOrdenDeCarga(OrdenDeCarga selectedOrdenDeCarga) {
        this.selectedOrdenDeCarga = selectedOrdenDeCarga;
    }
    public void onRowSelectCamion(SelectEvent event) {
        this.selectedValeComb.setCamion(this.selectedCamion);
    }
    public void onRowSelectPro(SelectEvent event) {
        this.selectedValeComb.setProveedor(this.selectedProveedor);
        this.selectedPrecio = new PrecioComb();
    }
    public void onRowSelectOrden(SelectEvent event) {
        this.selectedValeComb.setOrdenDeCarga(this.selectedOrdenDeCarga);
    }
    public void onRowSelectVale(SelectEvent event) {
        for(int i = 0; i<this.valelist.size(); i++){
            this.selectedValeComb= this.valelist.get(i);
        }        
    }
    public void onRowSelectV(SelectEvent event) {
        if(this.chkBoxChecked==false){
          band=0;  
          System.out.println(band);
        }else{
            band=1;
            System.out.println(band);
        }        
    }  

    public List<ValeCombustible> getValelist() {
        return valelist;
    }

    public void setValelist(List<ValeCombustible> valelist) {
        this.valelist = valelist;
    }
    
    public void onRowSelectO(SelectEvent event) {
        if(this.chkBoxCheckedo==false){
          ban=0;  
          System.out.println(ban);
        }else{
            ban=1;
            System.out.println(ban);
        }        
    }
    public void btnIniciar(ActionEvent actionEvent){
        this.vales = new ArrayList<ValeCombustible>();
        this.ordenes =new ArrayList<OrdenDeCarga>();
        this.viatico = new ArrayList<Viatico>();
        this.viapen =new ArrayList<Viatico>();
        this.aceptados =new ArrayList<ValeCombustible>();
        this.pendientes =new ArrayList<ValeCombustible>();
        this.anulados =new ArrayList<ValeCombustible>();
        this.precios =new ArrayList<PrecioComb>();
        this.reportes =new ArrayList<ValeCombustible>();
        this.selectedCamion = new Camion();
        this.selectedValeComb = new ValeCombustible();
        this.selectedOrdenDeCarga = new OrdenDeCarga();
        this.selectedProveedor = new Proveedor();
        this.selectedViatico = new Viatico();
        this.orden = new ArrayList<OrdenDeCarga>();
        this.band = 0;
        this.ban = 0;   
    }
    public void onRowSelectPrecio(SelectEvent event) {
        this.selectedValeComb.setPrecioComb(this.selectedPrecio.getMonto());
        banpro = 1;
        //this.selectedValeComb.setMontoTotal(this.selectedValeComb.getLitros()*this.selectedValeComb.getPrecioComb());
        //this.selectedValeComb.setTipoCombustible(this.selectedPrecio.getTipoCombustible());
    }
    public List<OrdenDeCarga> getOrdenes() {
        OrdenDeCargaDao ordenDao = new OrdenDeCargaDaoImpl();
        this.ordenes = ordenDao.findOrden(this.selectedCamion);
        return ordenes;
    }
    
    
    public void btnCreateValeComb(ActionEvent actionEvent) {
        ValeCombDao valeDao = new ValeCombDaoImpl();
        String msg;        
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
        this.selectedValeComb.setUsuario(usuario);
        this.selectedValeComb.setEstado("Pendiente");        
        System.out.println(band);
        if(ban==0){
           OrdenDeCargaDao ordenDao = new OrdenDeCargaDaoImpl();
            this.orden= ordenDao.findOne();
            for(int i = 0; i < orden.size(); i++){                
                this.selectedOrdenDeCarga=orden.get(i);                
            }            
            this.selectedValeComb.setOrdenDeCarga(this.selectedOrdenDeCarga);
            System.out.println(ban);
            System.out.print(this.selectedOrdenDeCarga.getIdOrdenDeCarga());
        }
        if (valeDao.create(this.selectedValeComb)) {
            msg = "Se creo correctamente el registro, dar click a PDF";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedViatico.setEstado("Aceptado");
            this.selectedValeComb2 = this.selectedValeComb;
            ViaticoDao viaticoDao = new ViaticoDaoImpl();
            if (viaticoDao.update(this.selectedViatico)) {            
            }
            this.vales = new ArrayList<ValeCombustible>();
            this.ordenes =new ArrayList<OrdenDeCarga>();
            this.viatico = new ArrayList<Viatico>();
            this.viapen =new ArrayList<Viatico>();
            this.aceptados =new ArrayList<ValeCombustible>();
            this.pendientes =new ArrayList<ValeCombustible>();
            this.anulados =new ArrayList<ValeCombustible>();
            this.precios =new ArrayList<PrecioComb>();
            this.reportes =new ArrayList<ValeCombustible>();
            this.selectedCamion = new Camion();
            this.selectedValeComb = new ValeCombustible();
            this.selectedOrdenDeCarga = new OrdenDeCarga();
            this.selectedProveedor = new Proveedor();
            this.selectedViatico = new Viatico();
            this.orden = new ArrayList<OrdenDeCarga>();
            this.band = 0;
            this.ban = 0; 
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnUpdateValeComb(ActionEvent actionEvent) {
        ValeCombDao valeDao = new ValeCombDaoImpl();
        String msg;
        if (valeDao.update(this.selectedValeComb)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteValeComb(ActionEvent actionEvent) {
        ValeCombDao valeDao = new ValeCombDaoImpl();
        String msg;
        if (valeDao.delete(this.selectedValeComb.getIdValeCombustible())) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public void btnProcesar(ActionEvent event) {
        String msg;
        FacesMessage message;        
        Double li = this.selectedValeComb.getLitros();
        PrecioComb precio;
        Double total;
        precio = this.selectedPrecio;
        if(precio==null){
            precio = new PrecioComb();
        }        
            if(precio.getMonto()>0.0){
                this.selectedValeComb.setPrecioComb(this.selectedPrecio.getMonto());
                this.selectedValeComb.setMontoComb(this.selectedValeComb.getLitros()*this.selectedValeComb.getPrecioComb());
                System.out.println(this.selectedValeComb.getMontoComb());
                this.selectedValeComb.setTipoCombustible(this.selectedPrecio.getTipoCombustible()); 
                total = this.selectedValeComb.getViatico()+this.selectedValeComb.getExtras()+this.selectedValeComb.getMontoComb();
                this.selectedValeComb.setMontoTotal(total);
                msg ="Se proceso con existo";
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
                this.chkBoxChecked = true;                
            }else{
               msg ="Seleccionar Precio";
               message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg,"");
               
            }                                                   
        FacesContext.getCurrentInstance().addMessage(null, message);        
        //return msg;
    }
    public void btnCargar(ActionEvent event) {
        this.selectedOrdenDeCarga = this.selectedValeComb.getOrdenDeCarga();
        this.selectedCamion = this.selectedValeComb.getCamion();
    }
}
