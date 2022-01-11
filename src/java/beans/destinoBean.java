/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.DestinoDao;
import dao.DestinoDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.UnidadDestino;

/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class destinoBean {
    private List<UnidadDestino> destinos;
    private UnidadDestino selectedUnidadDestino;
    private Object destinoDao;
    
    public destinoBean() {
        this.destinos = new ArrayList<UnidadDestino>();
        this.selectedUnidadDestino = new UnidadDestino();
    }

    public List<UnidadDestino> getDestinos() {
        DestinoDao destinoDao = new DestinoDaoImpl();
        this.destinos = destinoDao.findAll();
        return destinos;
    }

    public UnidadDestino getSelectedUnidadDestino() {
        return selectedUnidadDestino;
    }

    public void setDestinos(List<UnidadDestino> destinos) {
        this.destinos = destinos;
    }

    public void setSelectedUnidadDestino(UnidadDestino selectedUnidadDestino) {
        this.selectedUnidadDestino = selectedUnidadDestino;
    }
    public void btnCreateUnidadDestino(ActionEvent actionEvent) {
        DestinoDao destinoDao = new DestinoDaoImpl();
        String msg;
        if (destinoDao.create(this.selectedUnidadDestino)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedUnidadDestino = new UnidadDestino();
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    public void btnUpdateUnidadDestino(ActionEvent actionEvent) {
        DestinoDao destinoDao = new DestinoDaoImpl();
        String msg;
        if (destinoDao.update(this.selectedUnidadDestino)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteUnidadDestino(ActionEvent actionEvent) {
        DestinoDao origenDao = new DestinoDaoImpl();
        String msg;
        if (origenDao.delete(this.selectedUnidadDestino.getIdUnidadDestino())) {
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
        this.destinos = new ArrayList<UnidadDestino>();
        this.selectedUnidadDestino = new UnidadDestino(); 
    }
}
