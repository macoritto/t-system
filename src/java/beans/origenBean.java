/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.OrigenDao;
import dao.OrigenDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.UnidadOrigen;

/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class origenBean {
    private List<UnidadOrigen> origenes;
    private UnidadOrigen selectedUnidadOrigen;
    private Object origenDao;
    
    public origenBean() {
        this.origenes = new ArrayList<UnidadOrigen>();
        this.selectedUnidadOrigen = new UnidadOrigen();
    }

    public List<UnidadOrigen> getOrigenes() {
        OrigenDao origenDao = new OrigenDaoImpl();
        this.origenes = origenDao.findAll();
        return origenes;
    }

    public UnidadOrigen getSelectedUnidadOrigen() {
        return selectedUnidadOrigen;
    }

    public void setOrigenes(List<UnidadOrigen> origenes) {
        this.origenes = origenes;
    }

    public void setSelectedUnidadOrigen(UnidadOrigen selectedUnidadOrigen) {
        this.selectedUnidadOrigen = selectedUnidadOrigen;
    }
    public void btnCreateUnidadOrigen(ActionEvent actionEvent) {
        OrigenDao origenDao = new OrigenDaoImpl();
        String msg;
        if (origenDao.create(this.selectedUnidadOrigen)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedUnidadOrigen= new UnidadOrigen();
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    public void btnIniciar(ActionEvent actionEvent){
        this.origenes = new ArrayList<UnidadOrigen>();
        this.selectedUnidadOrigen = new UnidadOrigen();  
    }
    public void btnUpdateUnidadOrigen(ActionEvent actionEvent) {
        OrigenDao origenDao = new OrigenDaoImpl();
        String msg;
        if (origenDao.update(this.selectedUnidadOrigen)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteUnidadOrigen(ActionEvent actionEvent) {
        OrigenDao origenDao = new OrigenDaoImpl();
        String msg;
        if (origenDao.delete(this.selectedUnidadOrigen.getIdUnidadOrigen())) {
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
