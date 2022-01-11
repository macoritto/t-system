/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.PropietarioDao;
import dao.PropietarioDaoImpl;
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
import model.Propietario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class propietarioBean {
    private List<Propietario> propietarios;
    private Propietario selectedPropietario;
    private Object propietarioDao;
    private List<SelectItem> selectOneItemsPropietario;
    public propietarioBean() {
        this.propietarios = new ArrayList<Propietario>();
        this.selectedPropietario = new Propietario();
    }
    public List<SelectItem> getSelectOneItemsPropietario() {
        this.selectOneItemsPropietario = new ArrayList<SelectItem>();
        PropietarioDao propietarioDao = new PropietarioDaoImpl();
        List <Propietario> propietarios = propietarioDao.SelectItems();
        for (Propietario propietario : propietarios){
            SelectItem selecItem = new SelectItem(propietario.getIdPropietario(),propietario.getNombre()+" "+propietario.getApellido());
            this.selectOneItemsPropietario.add(selecItem);
        }
        return selectOneItemsPropietario;
    }
    
    public List<Propietario> getPropietarios() {
        PropietarioDao propietarioDao = new PropietarioDaoImpl();
        this.propietarios = propietarioDao.findAll();
        return propietarios;
    }
    
    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/prolist.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getPropietarios()));
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=propietarios.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
    public Propietario getSelectedPropietario() {
        return selectedPropietario;
    }

    public void setSelectedPropietario(Propietario selectedPropietario) {
        this.selectedPropietario = selectedPropietario;
    }
    public void btnCreatePropietario(ActionEvent actionEvent) {
        PropietarioDao propietarioDao = new PropietarioDaoImpl();
        String msg;
        Date hoy = new Date();
        String fecha = new SimpleDateFormat("YYYY-MM-dd").format(hoy);
        this.selectedPropietario.setFechaCreacion(java.sql.Date.valueOf(fecha));
        if (propietarioDao.create(this.selectedPropietario)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    public void btnUpdatePropietario(ActionEvent actionEvent) {
        PropietarioDao propietarioDao = new PropietarioDaoImpl();
        String msg;
        Date hoy = new Date();
        String fecha = new SimpleDateFormat("YYYY-MM-dd").format(hoy);
        this.selectedPropietario.setFechaCreacion(java.sql.Date.valueOf(fecha));
        if (propietarioDao.update(this.selectedPropietario)) {
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
        this.propietarios = new ArrayList<Propietario>();
        this.selectedPropietario = new Propietario();   
    }
    public void btnDeletePropietario(ActionEvent actionEvent) {
        PropietarioDao propietarioDao = new PropietarioDaoImpl();
        String msg;
        if (propietarioDao.delete(this.selectedPropietario.getIdPropietario())) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
}
}
