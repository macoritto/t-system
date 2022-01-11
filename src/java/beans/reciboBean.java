/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ReciboDao;
import dao.ReciboDaoImpl;
import java.sql.Connection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.awt.event.ActionEvent;
import model.Recibo;
import java.util.List;
import model.Camion;
import model.Usuario;
import java.util.ArrayList;
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
import model.Credito;
import model.Usuario;
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
public class reciboBean {

    private Recibo selectedRecibo;
    private Recibo selectedRecibo2;
    private List<Recibo> recibos;    
    private Camion selectedCamion;
    private Usuario usuario;
    private Connection conexion;
    private Integer codrecibo;
    public reciboBean() {
        this.selectedRecibo = new Recibo();
        this.selectedRecibo2 = new Recibo();
        this.selectedCamion = new Camion();
        this.usuario = new Usuario();
        this.recibos = new ArrayList<Recibo>();
    }

    public Recibo getSelectedRecibo() {
        return selectedRecibo;
    }

    public List<Recibo> getRecibos() {
        ReciboDao recibo = new ReciboDaoImpl();
        this.recibos = recibo.findAll();
        return recibos;
    }

    public Camion getSelectedCamion() {
        return selectedCamion;
    }

    public void setSelectedRecibo(Recibo selectedRecibo) {
        this.selectedRecibo = selectedRecibo;
    }

    public void setSelectedCamion(Camion selectedCamion) {
        this.selectedCamion = selectedCamion;
    }
    public void btnCreateRecibo(ActionEvent actionEvent) {
        ReciboDao reciboDao = new ReciboDaoImpl();
        String msg;               
        this.selectedRecibo.setEstado("Pendiente");
        if (reciboDao.create(this.selectedRecibo)) {
            msg = "Se creo correctamente el registro, Para el reporte dar click en PDF";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedRecibo2 = this.selectedRecibo;
            this.selectedRecibo = new Recibo();
            this.selectedCamion = new Camion();
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    public void btnUpdateRecibo(ActionEvent actionEvent) {
        ReciboDao reciboDao = new ReciboDaoImpl();
        String msg;
        if (reciboDao.update(this.selectedRecibo)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteRecibo(ActionEvent actionEvent) {
        ReciboDao reciboDao = new ReciboDaoImpl();
        String msg;
        if (reciboDao.delete(this.selectedRecibo.getIdRecibo())) {
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
     this.recibos = new ArrayList<Recibo>();
        this.selectedCamion = new Camion();
        this.selectedRecibo = new Recibo();       
    }
    public void onRowSelectCamion(SelectEvent event) {
        this.selectedRecibo.setCamion(this.selectedCamion);        
    }  
    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/reporte1.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getRecibos()));
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=reporte_recibos.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
    public void exportarPDF2(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/recibo2.jasper"));
      codrecibo= this.selectedRecibo2.getIdRecibo();
      Class.forName("org.postgresql.Driver");
      conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/transpbase", "postgres", "macorittogo");
      Map<String, Object> parametros = new HashMap<String, Object>();
      parametros.put("vCodrecibo", codrecibo);
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=credito.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
    public void exportarPDF3(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/recibo2.jasper"));
      codrecibo= this.selectedRecibo.getIdRecibo();
      Class.forName("org.postgresql.Driver");
      conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/transpbase", "postgres", "macorittogo");
      Map<String, Object> parametros = new HashMap<String, Object>();
      parametros.put("vCodrecibo", codrecibo);
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=credito.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
    public void onRowSelectRecibo(SelectEvent event) {       
    }
}
