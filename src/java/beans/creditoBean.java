/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CreditoDao;
import dao.CreditoDaoImpl;
import java.awt.event.ActionEvent;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import java.io.FileInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Camion;
import model.Credito;
import model.Propietario;
import model.Usuario;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import org.primefaces.event.SelectEvent;
import net.sf.jasperreports.engine.design.JasperDesign;
import javax.ws.rs.core.Response;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer; 
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class creditoBean {

    private List<Credito> creditos;
    private List<Credito> creditobus;
    private List<Credito> creditobusfecha;
    private Credito selectedCredito;
    private Propietario selectedPropietario;
    private Credito selectedCredito2;
    private Camion selectedCamion;
    private Usuario usuario;
    private Integer codcredito;
    private Connection conexion;  
    private boolean chkDescrip;
    private Date fechaini;
    private Date fechafin;
    private Date fechaini1;
    private Date fechafin1;
    private Integer nrocre;
    private Double montocre;
    private byte[] bytes = null;
    public creditoBean() {
        this.creditos = new ArrayList<Credito>();
        CreditoDao creditoDao = new CreditoDaoImpl();
        this.creditos = creditoDao.findAll();
        this.creditobus = new ArrayList<Credito>();
        this.creditobusfecha = new ArrayList<Credito>();
        this.selectedCamion = new Camion();
        this.selectedCredito = new Credito();
        this.selectedCredito2 = new Credito();
        this.selectedCredito.setFecha(new Date());
        this.selectedCredito.setFechaVencimiento(new Date());
    }

    public List<Credito> getCreditos() {
        
        return creditos;
    }
    public Propietario getSelectedPropietario() {
            return selectedPropietario;
    }

    public List<Credito> getCreditobusfecha() {
        return creditobusfecha;
    }

    public void setSelectedPropietario(Propietario selectedPropietario) {
        this.selectedPropietario = selectedPropietario;
    }   
    public Credito getSelectedCredito() {          
        return selectedCredito;
    }
    public boolean isBtnDisabled() {        
        return this.chkDescrip;
    }
    public Camion getSelectedCamion() {
        return selectedCamion;
    }

    public List<Credito> getCreditobus() {
        return creditobus;
    }
    
    public void setSelectedCredito(Credito selectedCredito) {
        this.selectedCredito = selectedCredito;
    }

    public void setSelectedCamion(Camion selectedCamion) {
        this.selectedCamion = selectedCamion;
    }
    
    public void onRowSelectCamion(SelectEvent event) {
        this.selectedCredito.setCamion(this.selectedCamion);        
    }   
    public void onRowSelectPropietario(SelectEvent event) {        
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
    
   public void btnCreateCredito(ActionEvent actionEvent) {
       String msg;
       if(this.selectedCredito.getMonto()>0){
            if(!this.selectedCredito.getDescripcion().equals("")){
                        CreditoDao creditoDao = new CreditoDaoImpl();
                        
                        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
                        this.selectedCredito.setUsuario(usuario);
                        this.selectedCredito.setEstadoCobro("Pendiente");
                        if (creditoDao.create(this.selectedCredito)) {
                            msg = "Se creo correctamente el registro, dar click en PDF";
                            this.selectedCredito2 = this.selectedCredito;
                            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
                            FacesContext.getCurrentInstance().addMessage(null, message);
                            this.selectedCredito = new Credito();
                            this.selectedCamion = new Camion();
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
    public void btnUpdateCredito(ActionEvent actionEvent) {
        CreditoDao creditoDao = new CreditoDaoImpl();
        String msg;
        if (creditoDao.update(this.selectedCredito)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteCredito(ActionEvent actionEvent) {
        CreditoDao creditoDao = new CreditoDaoImpl();
        String msg;
        if (creditoDao.delete(this.selectedCredito.getIdCredito())) {
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
     this.creditos = new ArrayList<Credito>();
        this.selectedCamion = new Camion();
        this.selectedCredito = new Credito();       
    }
    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/creditos.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getCreditos()));
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=reporte_creditos.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
    public void exportarPDF2(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/credito.jasper"));
      codcredito= this.selectedCredito2.getIdCredito();
      Class.forName("org.postgresql.Driver");
      conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/newtranspbase", "postgres", "macorittogo");
      Map<String, Object> parametros = new HashMap<String, Object>();
      parametros.put("vCodcredito", codcredito);
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
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/credito.jasper"));
      codcredito= this.selectedCredito.getIdCredito();
      Class.forName("org.postgresql.Driver");
      conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/newtranspbase", "postgres", "macorittogo");
      Map<String, Object> parametros = new HashMap<String, Object>();
      parametros.put("vCodcredito", codcredito);
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=credito.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
    JasperPrint jasperPrint;
    public static JasperDesign jasperDesign;
    public static JasperReport jasperReport;
    public void exportarxls(ActionEvent actionEvent)throws JRException, IOException, ClassNotFoundException, SQLException {
//            JasperPrint jasperPrint;
//            Class.forName("org.postgresql.Driver");
//            conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/transpbase", "postgres", "macorittogo");
//            Map<String, Object> parametros = new HashMap<String, Object>();
//            parametros.put("vCodcredito", codcredito);    
//            JasperReport reporte = (JasperReport) JRLoader.loadObject("C:/Users/Usuario/Documents/transporsystem/t-system/web/reportes/credito.jasper");            
//            jasperPrint=JasperFillManager.fillReport(reporte, parametros,conexion);
//            HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();            
//            httpServletResponse.setHeader("Content-disposition", "attachment; filename=report.xls");
//            httpServletResponse.setContentType("application/vnd.ms-excel");
//            ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
//            JRXlsxExporter docxExporter=new JRXlsxExporter();
//            docxExporter.setParameter(JRExporterParameter.INPUT_URL, "/reportes/credito.xls");
//            docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//            docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
//            docxExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
//            docxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
//            docxExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
//            docxExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);        
//            docxExporter.setParameter(JRXlsExporterParameter.CHARACTER_ENCODING, "UTF-8");
//            docxExporter.exportReport();                                    
//            servletOutputStream.flush();
//            servletOutputStream.close();
//            FacesContext.getCurrentInstance().responseComplete();
              String reportPath;
              //reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/credito.jasper");
              InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("C:/Users/Usuario/Documents/transporsystem/t-system/web/reportes/credito.jrxml");
              try {
                    Class.forName("org.postgresql.Driver");
                    conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/transpbase", "postgres", "macorittogo");
                    
                    //Crear el mapa de parametros
                   Map<String, Object> parameters = new HashMap<String, Object>();
                   parameters.put("vCodcredito", codcredito);
                 // System.out.println("Codped: "+codped );
                    jasperDesign = JRXmlLoader.load(resourceAsStream);
                    jasperReport = JasperCompileManager.compileReport(jasperDesign);
                    OutputStream outputfile = new FileOutputStream(new File("C:/Users/Usuario/Documents/transporsystem/t-system/web/reportes/credito.xlsx"));
                    jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conexion);
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    bytes = JasperRunManager.runReportToPdf(jasperReport,new HashMap(),conexion);
                    JRXlsxExporter docxExporter = new JRXlsxExporter();                
                    docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
                    docxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
                    docxExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                    docxExporter.exportReport();
                    FacesContext.getCurrentInstance().responseComplete();
                    outputfile.write(output.toByteArray());
                } catch (SQLException ex) {                    
                } catch (ClassNotFoundException ex) {                    
                }                
                //HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                //httpServletResponse.setHeader("Content-disposition", "attachment; filename=credito.xlsx");
                //httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                //ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                

    }                    
    public void onRowSelectCredito(SelectEvent event) {       
    }
    public String btnDescrip(){     
        String a;
        if(this.selectedCredito.getDescripcion()=="Cheque wyryry"){
            a="true";
        }else{
            a="false";
        }               
        return a;
    }
    public void btnCargarVia(ActionEvent event) {
        CreditoDao cre = new CreditoDaoImpl();
        this.creditobus= cre.findbus(this.selectedPropietario, this.fechaini, this.fechafin);    
        this.nrocre = this.creditobus.size();       
        this.montocre = this.sumarTotal(this.creditobus);
    }
    public void btnCargarFecha(ActionEvent event) {
        CreditoDao cre = new CreditoDaoImpl();
        this.creditobusfecha = cre.findfecha(this.fechaini1, this.fechafin1);    
        this.nrocre = this.creditobusfecha.size();       
        this.montocre = this.sumarTotal(this.creditobusfecha);
    }
    private Double sumarTotal(List<Credito> credito){
    Double suma =0.0;
       for(int i = 0; i < credito.size(); i++){
           suma = suma + credito.get(i).getMonto();
       }
        return suma;
    }
}
