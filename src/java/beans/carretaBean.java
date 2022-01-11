/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CarretaDao;
import dao.CarretaDaoImpl;
import dao.TipoCarretaDao;
import dao.TipoCarretaDaoImpl;
import java.awt.event.ActionEvent;
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
import model.Carreta;
import model.TipoCarreta;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class carretaBean {

    private List<Carreta> carretas;
    private Carreta selectedCarreta;
    private List<TipoCarreta> tipos;
    private TipoCarreta selectedTipo;
    private Object carretaDao;
    private List<SelectItem> selectOneItemsCarreta;

    public carretaBean() {
        this.carretas = new ArrayList<Carreta>();
        this.selectedCarreta = new Carreta();
        this.selectedTipo= new TipoCarreta();
    }

    public List<TipoCarreta> getTipos() {
        TipoCarretaDao tipo = new TipoCarretaDaoImpl();
        tipos= tipo.SelectItems();
        return tipos;
    }

    public TipoCarreta getSelectedTipo() {
        return selectedTipo;
    }

    public void setSelectedTipo(TipoCarreta selectedTipo) {
        this.selectedTipo = selectedTipo;
    }

    public List<SelectItem> getSelectOneItemsCarreta() {
        this.selectOneItemsCarreta = new ArrayList<SelectItem>();
        CarretaDao carretaDao = new CarretaDaoImpl();
        List<Carreta> carretas = carretaDao.SelectItems();
        for (Carreta carreta : carretas) {
            SelectItem selecItem = new SelectItem(carreta.getChapaCarreta());
            this.selectOneItemsCarreta.add(selecItem);
        }
        return selectOneItemsCarreta;
    }
    public List<Carreta> getCarretas() {
        CarretaDao carretaDao = new CarretaDaoImpl();
        this.carretas = carretaDao.findAll();
        return carretas;
    }

    public Carreta getSelectedCarreta() {
        return selectedCarreta;
    }

    public void setSelectedCarreta(Carreta selectedCarreta) {
        this.selectedCarreta = selectedCarreta;
    }
    public void btnIniciar(ActionEvent actionEvent){
        this.carretas = new ArrayList<Carreta>();
        this.selectedCarreta = new Carreta();    
    }
    public void btnCreateCarreta(ActionEvent actionEvent) {
        CarretaDao carretaDao = new CarretaDaoImpl();
        String msg;
        Date hoy = new Date();        
        if (carretaDao.create(this.selectedCarreta)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    public void onRowSelectTipo(SelectEvent event){
        this.selectedCarreta.setTipoCarreta(this.selectedTipo);
    }
    public void btnUpdateCarreta(ActionEvent actionEvent) {
        CarretaDao camionDao = new CarretaDaoImpl();
        String msg;
        if (camionDao.update(this.selectedCarreta)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteCarreta(ActionEvent actionEvent) {
        CarretaDao carretaDao = new CarretaDaoImpl();
        String msg;
        if (carretaDao.delete(this.selectedCarreta.getChapaCarreta())) {
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
