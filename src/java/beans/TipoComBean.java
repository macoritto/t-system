/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.TipoCombDao;
import dao.TipoCombDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.TipoCombustible;

/**
 *
 * @author Usuario
 */
@ManagedBean
@RequestScoped
public class TipoComBean {
    private List<TipoCombustible> tipos;
    private TipoCombustible seletedTipoCombustible;        
    public TipoComBean() {
        this.seletedTipoCombustible= new TipoCombustible();
        this.tipos = new ArrayList<TipoCombustible>();
    }

    public List<TipoCombustible> getTipos() {
        TipoCombDao tipoCombDao = new TipoCombDaoImpl();
        this.tipos = tipoCombDao.findAll();
        return tipos;
    }

    public TipoCombustible getSeletedTipoCombustible() {
        return seletedTipoCombustible;
    }

    public void setSeletedTipoCombustible(TipoCombustible seletedTipoCombustible) {
        this.seletedTipoCombustible = seletedTipoCombustible;
    }
    public void btnCreateTipoComb(ActionEvent actionEvent) {
        TipoCombDao tipocombDao = new TipoCombDaoImpl();
        String msg;
        if (tipocombDao.create(this.seletedTipoCombustible)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnUpdateTipoComb(ActionEvent actionEvent) {
        TipoCombDao precioDao = new TipoCombDaoImpl();
        String msg;
        if (precioDao.update(this.seletedTipoCombustible)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteTipoComb(ActionEvent actionEvent) {
        TipoCombDao precioDao = new TipoCombDaoImpl();
        String msg;
        if (precioDao.delete(this.seletedTipoCombustible.getIdTipoCombustible())) {
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
