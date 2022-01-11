/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.AdelantoChoferDao;
import dao.AdelantoChoferDaoImpl;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.AdelantoChofer;
import model.Camion;
import model.Chofer;
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
public class adeChoBean {

    private List<AdelantoChofer> adelantos;
    private List<AdelantoChofer> adelantosbus;
    private List<AdelantoChofer> adelantosbusfecha;
    private Chofer selectedChofer;
    private Camion selectedCamion;
    private AdelantoChofer selectedAdelanto;
    private AdelantoChofer selectedAdelanto2;
    private Usuario usuario;
    private Integer codcredito;
    private Connection conexion;  
    private Date fechaini;
    private Date fechafin;
    private Date fechaini1;
    private Date fechafin1;
    private Integer nrocre;
    private Double montocre;
    public adeChoBean() {
        this.adelantos = new ArrayList<AdelantoChofer>();
        AdelantoChoferDao adelantoDao = new AdelantoChoferDaoImpl();
        this.adelantos = adelantoDao.findAll();
        this.adelantosbus = new ArrayList<AdelantoChofer>();
        this.adelantosbusfecha = new ArrayList<AdelantoChofer>();
        this.selectedAdelanto = new AdelantoChofer();
        this.selectedAdelanto2 = new AdelantoChofer();
        this.selectedCamion = new Camion();
        this.selectedChofer = new Chofer();
    }

    public List<AdelantoChofer> getAdelantos() {
        
        return adelantos;
    }

    public List<AdelantoChofer> getAdelantosbus() {
        return adelantosbus;
    }

    public List<AdelantoChofer> getAdelantosbusfecha() {
        return adelantosbusfecha;
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

    public Integer getNrocre() {
        return nrocre;
    }

    public Double getMontocre() {
        return montocre;
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

    public void setNrocre(Integer nrocre) {
        this.nrocre = nrocre;
    }

    public void setMontocre(Double montocre) {
        this.montocre = montocre;
    }

    public Chofer getSelectedChofer() {
        return selectedChofer;
    }

    public void setSelectedChofer(Chofer selectedChofer) {
        this.selectedChofer = selectedChofer;
    }
    
    public Camion getSelectedCamion() {
        return selectedCamion;
    }

    public AdelantoChofer getSelectedAdelanto() {
        return selectedAdelanto;
    }

    public void setSelectedCamion(Camion selectedCamion) {
        this.selectedCamion = selectedCamion;
    }

    public void setSelectedAdelanto(AdelantoChofer selectedAdelanto) {
        this.selectedAdelanto = selectedAdelanto;
    }
    public void onRowSelectCamion(SelectEvent event) {
        this.selectedAdelanto.setCamion(this.selectedCamion);
        this.selectedAdelanto.setChofer(this.selectedCamion.getChofer());
        this.selectedChofer = this.selectedAdelanto.getChofer();
    }   
   public void btnCreateAdelanto(ActionEvent actionEvent) {
        AdelantoChoferDao adelantoDao = new AdelantoChoferDaoImpl();
        String msg;
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
        this.selectedAdelanto.setUsuario(usuario);
        this.selectedAdelanto.setEstadoCobro("Pendiente");
        if (adelantoDao.create(this.selectedAdelanto)) {
            msg = "Se creo correctamente el registro, dar click en PDF";
            this.selectedAdelanto2 = this.selectedAdelanto;
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedAdelanto = new AdelantoChofer();
            this.selectedCamion = new Camion();
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    public void btnUpdateAdelanto(ActionEvent actionEvent) {
        AdelantoChoferDao adelantoDao = new AdelantoChoferDaoImpl();
        String msg;
        if (adelantoDao.update(this.selectedAdelanto)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteAdelanto(ActionEvent actionEvent) {
        AdelantoChoferDao adelantoDao = new AdelantoChoferDaoImpl();
        String msg;
        if (adelantoDao.delete(this.selectedAdelanto.getIdAdelantoChofer())) {
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
        this.adelantos = new ArrayList<AdelantoChofer>();
        this.selectedAdelanto = new AdelantoChofer();
        this.selectedCamion = new Camion();
    }
    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/adelantos.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getAdelantos()));
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=reporte_adelantos.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
    public void exportarPDF2(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/adelanto.jasper"));
      codcredito= this.selectedAdelanto2.getIdAdelantoChofer();
      Class.forName("org.postgresql.Driver");
      conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/transpbase", "postgres", "macorittogo");
      Map<String, Object> parametros = new HashMap<String, Object>();
      parametros.put("vCodcredito", codcredito);
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=adelanto.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
    public void exportarPDF3(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/adelanto.jasper"));
      codcredito= this.selectedAdelanto.getIdAdelantoChofer();
      Class.forName("org.postgresql.Driver");
      conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/transpbase", "postgres", "macorittogo");
      Map<String, Object> parametros = new HashMap<String, Object>();
      parametros.put("vCodcredito", codcredito);
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=adelanto.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
    public void onRowSelectAde(SelectEvent event) {       
    }
    public void btnCargarVia(ActionEvent event) {
        AdelantoChoferDao cre = new AdelantoChoferDaoImpl();
        this.adelantosbus= cre.findbus(this.selectedChofer, this.fechaini, this.fechafin);    
        this.nrocre = this.adelantosbus.size();       
        this.montocre = this.sumarTotal(this.adelantosbus);
    }
    public void btnCargarFecha(ActionEvent event) {
        AdelantoChoferDao cre = new AdelantoChoferDaoImpl();
        this.adelantosbusfecha = cre.findbusfecha(this.fechaini1, this.fechafin1);    
        this.nrocre = this.adelantosbusfecha.size();       
        this.montocre = this.sumarTotal(this.adelantosbusfecha);
    }
    private Double sumarTotal(List<AdelantoChofer> adelanto){
    Double suma =0.0;
       for(int i = 0; i < adelanto.size(); i++){
           suma = suma + adelanto.get(i).getMonto();
       }
        return suma;
    }
}
