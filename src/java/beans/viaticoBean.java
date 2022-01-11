/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.OrdenDeCargaDao;
import dao.OrdenDeCargaDaoImpl;
import dao.TipoViaticoDao;
import dao.TipoViaticoDaoImpl;
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
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Camion;
import model.Chofer;
import model.OrdenDeCarga;
import model.TipoViatico;
import model.Usuario;
import model.Viatico;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.event.SelectEvent;
import java.util.Date;

/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class viaticoBean {
    private List<Viatico> viaticos;
    private List<Viatico> viabus;
    private List<Viatico> viabusfecha;
    private List<Viatico> aceptados;
    private List<Viatico> pendientes;
    private List<Viatico> anulados;
    private Viatico selectedViatico;
    private Viatico selectedViatico2;
    private OrdenDeCarga selectedOrden;
    private List<OrdenDeCarga> orden;
    private Camion selectedCamion;
    private Chofer selectedChofer;
    private Usuario usuario;
    private List<SelectItem> selectOneItemsTipoViatico;    
    private Integer ban=0;
    private List<OrdenDeCarga> ordenes;
    private Integer codorden;
    private Connection conexion;    
    private Date fechaini;
    private Date fechafin;
    private Date fechaini1;
    private Date fechafin1;
    private Integer nrovia;
    private Double montovia;
    public viaticoBean() {
        this.viaticos = new ArrayList<Viatico>();
        ViaticoDao viaticoDao = new ViaticoDaoImpl();
        this.viaticos = viaticoDao.findAll();
        this.viabus = new ArrayList<Viatico>();
        this.viabusfecha = new ArrayList<Viatico>();
        this.pendientes = new ArrayList<Viatico>();
        this.anulados = new ArrayList<Viatico>();
        this.aceptados = new ArrayList<Viatico>();
        this.selectedViatico = new Viatico();
        this.selectedViatico2 = new Viatico();
        this.selectedCamion = new Camion();
        this.selectedChofer = new Chofer();
        this.orden = new ArrayList<OrdenDeCarga>();   
        this.ordenes = new ArrayList<OrdenDeCarga>();
        this.selectedViatico.setFecha(new Date());
        OrdenDeCargaDao ordenDao = new OrdenDeCargaDaoImpl();
        this.ordenes = ordenDao.findOne();
    }
    public List<SelectItem> getSelectOneItemsTipoViatico() {
        this.selectOneItemsTipoViatico = new ArrayList<SelectItem>();
        TipoViaticoDao tipoviaticoDao = new TipoViaticoDaoImpl();
        List<TipoViatico> tipos = tipoviaticoDao.SelectItems();
        for (TipoViatico tipoviatico : tipos) {
            SelectItem selecItem = new SelectItem(tipoviatico.getIdTipo(), tipoviatico.getDescripcion());
            this.selectOneItemsTipoViatico.add(selecItem);
        }
        return selectOneItemsTipoViatico;
    }
    private boolean chkBoxCheckedo;

    public boolean isChkBoxCheckedo() {
        return chkBoxCheckedo;
    }

    public boolean isBtnDisabledo() {
        
        return !this.chkBoxCheckedo;
    }

    public Integer getNrovia() {
        return nrovia;
    }

    public Double getMontovia() {
        return montovia;
    }

    public void setNrovia(Integer nrovia) {
        this.nrovia = nrovia;
    }

    public void setMontovia(Double montovia) {
        this.montovia = montovia;
    }

    public List<Viatico> getViabusfecha() {
        return viabusfecha;
    }
    
    public void setChkBoxCheckedo(boolean chkBoxCheckedo) {
        this.chkBoxCheckedo = chkBoxCheckedo;
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

    public List<OrdenDeCarga> getOrdenes() {
        
        return ordenes;
    }

    public List<Viatico> getViabus() {
        return viabus;
    }

    
    public OrdenDeCarga getSelectedOrden() {
        return selectedOrden;
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
    
    public void setSelectedOrden(OrdenDeCarga selectedOrden) {
        this.selectedOrden = selectedOrden;
    }
    
    public List<Viatico> getViaticos() {
        
        return viaticos;
    }   
    public List<OrdenDeCarga> getOrden() {
        OrdenDeCargaDao ordenDao = new OrdenDeCargaDaoImpl();
        this.orden = ordenDao.findOne();
        return orden;
    }
    public List<Viatico> getPendientes() {
        ViaticoDao viaticoDao = new ViaticoDaoImpl();
        this.pendientes = viaticoDao.findPendiente();
        return pendientes;
    }
    public List<Viatico> getAnulados() {
        ViaticoDao viaticoDao = new ViaticoDaoImpl();
        this.anulados = viaticoDao.findAnulado();
        return anulados;
    }
    public List<Viatico> getAceptados() {
        ViaticoDao viaticoDao = new ViaticoDaoImpl();
        this.aceptados = viaticoDao.findAceptado();
        return aceptados;
    }

    public Viatico getSelectedViatico() {
        return selectedViatico;
    }

    public Camion getSelectedCamion() {
        return selectedCamion;
    }
    
    public void setSelectedViatico(Viatico selectedViatico) {
        this.selectedViatico = selectedViatico;
    }

    public void setSelectedCamion(Camion selectedCamion) {
        this.selectedCamion = selectedCamion;
    }

    public Chofer getSelectedChofer() {
        return selectedChofer;
    }

    public void setSelectedChofer(Chofer selectedChofer) {
        this.selectedChofer = selectedChofer;
    }
    
        public void onRowSelectCamion(SelectEvent event) {
        this.selectedViatico.setCamion(this.selectedCamion);
        this.selectedViatico.setChofer(this.selectedCamion.getChofer());
    }
    public void onRowSelectOrden(SelectEvent event) {
        this.selectedViatico.setOrdenDeCarga(this.selectedOrden);        
    }    
    public void btnCreateViatico(ActionEvent actionEvent) {
        String msg;
        if(this.selectedViatico.getMonto()>0){
            if(!this.selectedViatico.getDescripcion().equals("")){
                        ViaticoDao viaticoDao = new ViaticoDaoImpl();
                        
                        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
                        this.selectedViatico.setUsuario(usuario);
                        this.selectedViatico.setEstado("Pendiente");
                           OrdenDeCargaDao ordenDao = new OrdenDeCargaDaoImpl();
                            this.orden= ordenDao.findOne();
                            for(int i = 0; i < orden.size(); i++){                
                                this.selectedOrden=orden.get(i);                
                            }        
                            this.selectedViatico.setOrdenDeCarga(this.selectedOrden);
                            //this.selectedViatico.setViaje(this.selectedOrden);
                            System.out.println(ban);
                            System.out.print(this.selectedOrden.getIdOrdenDeCarga());
                        if (viaticoDao.create(this.selectedViatico)) {
                            msg = "Se creo correctamente el registro, dar click a PDF";
                            this.selectedViatico2 = this.selectedViatico;
                            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
                            FacesContext.getCurrentInstance().addMessage(null, message);
                            this.selectedViatico = new Viatico();
                            this.selectedCamion = new Camion();
                            this.ban = 0;
                            this.selectedOrden = new OrdenDeCarga();
                            this.selectedChofer = new Chofer();
                        } else {
                            msg = "Error al crear registro.";
                            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
                            FacesContext.getCurrentInstance().addMessage(null, message);
                        }
            }else{   
                msg = "Favor ingresar descripcion al viatico.";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }else{   
            msg = "Ingresar el Monto del Viatico.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }             

    }

    public void btnUpdateViatico(ActionEvent actionEvent) {
        ViaticoDao viaticoDao = new ViaticoDaoImpl();
        String msg;
        if (viaticoDao.update(this.selectedViatico)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteViatico(ActionEvent actionEvent) {
        ViaticoDao viaticoDao = new ViaticoDaoImpl();
        String msg;
        if (viaticoDao.delete(this.selectedViatico.getIdViatico())) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public void btnIniciar(ActionEvent actionEvent){
        this.viaticos = new ArrayList<Viatico>();
        this.pendientes = new ArrayList<Viatico>();
        this.anulados = new ArrayList<Viatico>();
        this.aceptados = new ArrayList<Viatico>();
        this.selectedViatico = new Viatico();
        this.selectedCamion = new Camion();
        this.selectedChofer = new Chofer();
        this.orden = new ArrayList<OrdenDeCarga>();   
        this.ordenes = new ArrayList<OrdenDeCarga>();     
    }
    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/viaticos.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getViaticos()));
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=reporte_viaticos.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
     public void onRowSelectViatico(SelectEvent event) {       
    }
      public void exportarPDF2(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/viatico.jrxml"));
      codorden= this.selectedViatico2.getIdViatico();
      Class.forName("org.postgresql.Driver");
      conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/newtranspbase", "postgres", "macorittogo");
      Map<String, Object> parametros = new HashMap<String, Object>();
      parametros.put("vCodviatico", codorden);
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=viatico.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
      public void exportarPDF3(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/viatico.jasper"));
      codorden= this.selectedViatico.getIdViatico();
      Class.forName("org.postgresql.Driver");
      conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/newtranspbase", "postgres", "macorittogo");
      Map<String, Object> parametros = new HashMap<String, Object>();
      parametros.put("vCodviatico", codorden);
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=viatico.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
    public void btncargar(ActionEvent actionEvent){
        this.selectedOrden = this.selectedViatico.getOrdenDeCarga();
        this.selectedCamion = this.selectedViatico.getCamion();
    }
    public void btnCargarVia(ActionEvent event) {
        ViaticoDao via = new ViaticoDaoImpl();
        this.viabus= via.findbus(this.selectedCamion, this.fechaini, this.fechafin);    
        this.nrovia = this.viabus.size();       
        this.montovia = this.sumarTotal(this.viabus);
    }
    public void btnCargarFecha(ActionEvent event) {
        ViaticoDao via = new ViaticoDaoImpl();
        this.viabusfecha= via.findfecha(this.fechaini1, this.fechafin1);    
        this.nrovia = this.viabusfecha.size();       
        this.montovia = this.sumarTotal(this.viabusfecha);
    }
    private Double sumarTotal(List<Viatico> viatico){
    Double suma =0.0;
       for(int i = 0; i < viatico.size(); i++){
           suma = suma + viatico.get(i).getMonto();
       }
        return suma;
    }
}
