/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ChoferDao;
import dao.ChoferDaoImpl;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Chofer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Usuario
 */
@ManagedBean (name="choferBean")
@RequestScoped
public class choferBean {
    private List<Chofer> choferes;
    private Chofer selectedChofer;
    private Object choferDao;

   private List<SelectItem> selectOneItemsChofer;
    public choferBean() {
        this.choferes = new ArrayList<Chofer>();
        this.selectedChofer = new Chofer();
    }

    public List<SelectItem> getSelectOneItemsChofer() {
        this.selectOneItemsChofer = new ArrayList<SelectItem>();
        ChoferDao choferDao = new ChoferDaoImpl();
        List <Chofer> choferes = choferDao.SelectItems();
        for (Chofer chofer : choferes){
            SelectItem selecItem = new SelectItem(chofer.getIdChofer(),chofer.getNombre()+" "+chofer.getApellido());
            this.selectOneItemsChofer.add(selecItem);
        }
        return selectOneItemsChofer;
    }
    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/cholist.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getChoferes()));
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=reporte_choferes.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
    public List<Chofer> getChoferes() {
        ChoferDao choferDao = new ChoferDaoImpl();
        this.choferes = choferDao.findAll();
        return choferes;
    }

    public Chofer getSelectedChofer() {
        return selectedChofer;
    }

    public void setSelectedChofer(Chofer selectedChofer) {
        this.selectedChofer = selectedChofer;
    }
    public void btnIniciar(ActionEvent actionEvent){
        this.choferes = new ArrayList<Chofer>();
        this.selectedChofer = new Chofer();    
    }
    public void btnCreateChofer(ActionEvent actionEvent) {
        ChoferDao choferDao = new ChoferDaoImpl();
        String msg;
        Date hoy = new Date();
        String fecha = new SimpleDateFormat("YYYY-MM-dd").format(hoy);
        this.selectedChofer.setFechaNacimiento(java.sql.Date.valueOf(fecha));
        if (choferDao.create(this.selectedChofer)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    public void btnUpdateChofer(ActionEvent actionEvent) {
        ChoferDao choferDao = new ChoferDaoImpl();
        String msg;
        Date hoy = new Date();
        String fecha = new SimpleDateFormat("YYYY-MM-dd").format(hoy);
        this.selectedChofer.setFechaNacimiento(java.sql.Date.valueOf(fecha));
        if (choferDao.update(this.selectedChofer)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteChofer(ActionEvent actionEvent) {
        ChoferDao choferDao = new ChoferDaoImpl();
        String msg;
        if (choferDao.delete(this.selectedChofer.getIdChofer())) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
}
        public void selectChofer() {
        RequestContext.getCurrentInstance().openDialog("formAgregarChofer");
    }
     
    public void elegirChofer(SelectEvent event) {
        Chofer chofer = (Chofer) event.getObject();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Chofer Seleccionado", "Chapa:" + chofer.getIdChofer());
         
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void seleccionarChoferFromDialog(Chofer chofer) {
        RequestContext.getCurrentInstance().closeDialog(chofer);
    }
}
