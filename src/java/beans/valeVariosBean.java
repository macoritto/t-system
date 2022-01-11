/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ProVariosDao;
import dao.ProVariosDaoImpl;
import dao.ValeVariosDao;
import dao.ValeVariosDaoImpl;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Camion;
import model.ProveedorVarios;
import model.Usuario;
import model.ValeVarios;
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
public class valeVariosBean {
    private List<ValeVarios> valevarios;
    private List<ValeVarios> pendientes;
    private List<ValeVarios> anulados;
    private List<ValeVarios> aceptados;
    private List<ValeVarios> valelist;
    private List<ProveedorVarios> proveedores;
    private ValeVarios selectedValeVarios;
    private ValeVarios selectedValeVarios2;
    private Camion selectedCamion;
    private Usuario usuario;
    private ProveedorVarios SelectedProveedorVarios;
    private Integer codvalevarios;
    private Connection conexion;
    private String nomusu;
    public valeVariosBean() {
        this.selectedCamion = new Camion();
        this.valevarios = new ArrayList<ValeVarios>();
        ValeVariosDao valeDao = new ValeVariosDaoImpl();
        this.valevarios = valeDao.findAll();
        this.proveedores = new ArrayList<ProveedorVarios>();
        this.pendientes = new ArrayList<ValeVarios>();
        this.pendientes = valeDao.findPendiente();
        this.anulados = new ArrayList<ValeVarios>();
        this.anulados = valeDao.findAnulado();
        this.aceptados = new ArrayList<ValeVarios>();
        this.aceptados = valeDao.findAceptado();
        this.selectedValeVarios = new ValeVarios();
        this.selectedValeVarios2 = new ValeVarios();
        this.SelectedProveedorVarios = new ProveedorVarios();
        this.valelist = new ArrayList<ValeVarios>();
    }
  public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/valevarioslist.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getValelist()));
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=reporte_vales_varios.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
   public void exportarPDF2(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/valevarios.jasper"));
      codvalevarios= this.selectedValeVarios2.getIdValeVarios();
      usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
      nomusu = usuario.getUsuario();
      Class.forName("org.postgresql.Driver");
      conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/transpbase", "postgres", "macorittogo");
      Map<String, Object> parametros = new HashMap<String, Object>();
      Map<String, Object> usu = new HashMap<String, Object>();
      parametros.put("vCodvalevarios", codvalevarios);
      usu.put("vUsu", nomusu);
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=vale_varios.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
   public void exportarPDF3(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/valevarios.jasper"));
      codvalevarios= this.selectedValeVarios.getIdValeVarios();
      usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
      nomusu = usuario.getUsuario();
      Class.forName("org.postgresql.Driver");
      conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/transpbase", "postgres", "macorittogo");
      Map<String, Object> parametros = new HashMap<String, Object>();
      Map<String, Object> usu = new HashMap<String, Object>();
      parametros.put("vCodvalevarios", codvalevarios);
      usu.put("vUsu", nomusu);
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=vale_varios.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }

    public List<ValeVarios> getValelist() {
        return valelist;
    }

    public void setValelist(List<ValeVarios> valelist) {
        this.valelist = valelist;
    }
   
    public List<ValeVarios> getValevarios() {
        
        return valevarios;
    }   
    public List<ValeVarios> getPendientes() {
        ValeVariosDao valeDao = new ValeVariosDaoImpl();
        
        return pendientes;
    } 
    public List<ValeVarios> getAnulados() {
        ValeVariosDao valeDao = new ValeVariosDaoImpl();
        
        return anulados;
    }  
    public List<ValeVarios> getAceptados() {
        ValeVariosDao valeDao = new ValeVariosDaoImpl();
        
        return aceptados;
    }  
     public List<ProveedorVarios> getProveedores() {
        ProVariosDao proDao = new ProVariosDaoImpl();
        this.proveedores = proDao.findAll();
        return proveedores;
    }
    public ValeVarios getSelectedValeVarios() {
        return selectedValeVarios;
    }

    public Camion getSelectedCamion() {
        return selectedCamion;
    }

    public ProveedorVarios getSelectedProveedorVarios() {
        return SelectedProveedorVarios;
    }

    public void setSelectedValeVarios(ValeVarios selectedValeVarios) {
        this.selectedValeVarios = selectedValeVarios;
    }

    public void setSelectedCamion(Camion selectedCamion) {
        this.selectedCamion = selectedCamion;
    }

    public void setSelectedProveedorVarios(ProveedorVarios SelectedProveedorVarios) {
        this.SelectedProveedorVarios = SelectedProveedorVarios;
    }

    public void onRowSelectCamion(SelectEvent event) {
        this.selectedValeVarios.setCamion(this.selectedCamion);
    }
    public void onRowSelectVa(SelectEvent event) {    
        for(int i = 0; i<this.valelist.size(); i++){
            this.selectedValeVarios=this.valelist.get(i);            
        }
    }
    public void onRowSelectPro(SelectEvent event) {
        this.selectedValeVarios.setProveedorVarios(this.SelectedProveedorVarios);
    }
      public void btnCreateValeVarios(ActionEvent actionEvent) {
        ValeVariosDao valeDao = new ValeVariosDaoImpl();
        String msg;
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
        this.selectedValeVarios.setUsuario(usuario);
        this.selectedValeVarios.setEstado("Pendiente");
        if (valeDao.create(this.selectedValeVarios)) {
            msg = "Se creo correctamente el registro, dar Click a PDF";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedValeVarios2 = this.selectedValeVarios;
            this.selectedValeVarios = new ValeVarios();
            this.selectedCamion = new Camion();
            this.SelectedProveedorVarios = new ProveedorVarios();
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    public void btnUpdateValeVarios(ActionEvent actionEvent) {
        ValeVariosDao valeDao = new ValeVariosDaoImpl();
        String msg;
        if (valeDao.update(this.selectedValeVarios)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteValeVarios(ActionEvent actionEvent) {
        ValeVariosDao valeDao = new ValeVariosDaoImpl();
        String msg;
        if (valeDao.delete(this.selectedValeVarios.getIdValeVarios())) {
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
        this.selectedCamion = new Camion();
        this.valevarios = new ArrayList<ValeVarios>();
        this.proveedores = new ArrayList<ProveedorVarios>();
        this.pendientes = new ArrayList<ValeVarios>();
        this.anulados = new ArrayList<ValeVarios>();
        this.aceptados = new ArrayList<ValeVarios>();
        this.selectedValeVarios = new ValeVarios();
        this.SelectedProveedorVarios = new ProveedorVarios();
    }
}
