/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.PrecioCombDao;
import dao.PrecioCombDaoImpl;
import dao.TipoCombDao;
import dao.TipoCombDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.PrecioComb;
import model.Proveedor;
import model.TipoCombustible;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class precioComBean {

    private PrecioComb selectedPrecioComb;
    private List<PrecioComb> precios;
    private List<SelectItem> selectOneItemsTipoComb;
    private Proveedor selectedProveedor;
    public precioComBean() {
        this.precios = new ArrayList<PrecioComb>();
        this.selectedPrecioComb = new PrecioComb();
        this.selectedProveedor = new Proveedor();
    }

    public PrecioComb getSelectedPrecioComb() {
        return selectedPrecioComb;
    }

    public List<PrecioComb> getPrecios() {
        PrecioCombDao precioDao = new PrecioCombDaoImpl();
        this.precios = precioDao.findAll();
        return precios;
    }
     public List<SelectItem> getSelectOneItemsTipoComb() {
      this.selectOneItemsTipoComb = new ArrayList<SelectItem>();
      TipoCombDao tipocombustibleDao = new TipoCombDaoImpl();
      List <TipoCombustible> tipocombustibles = tipocombustibleDao.SelectItems();
      for (TipoCombustible tipocombustible : tipocombustibles){
          SelectItem selecItem = new SelectItem(tipocombustible.getIdTipoCombustible(), tipocombustible.getNombre());
          this.selectOneItemsTipoComb.add(selecItem);
        }
      return selectOneItemsTipoComb;
    }

    public Proveedor getSelectedProveedor() {
        return selectedProveedor;
    }

    public void setSelectedProveedor(Proveedor selectedProveedor) {
        this.selectedProveedor = selectedProveedor;
    }
    public void setSelectedPrecioComb(PrecioComb selectedPrecioComb) {
        this.selectedPrecioComb = selectedPrecioComb;
    }
    public void onRowSelectPro(SelectEvent event) {
        String nombre;
        nombre=this.selectedProveedor.getNombre();    
        //this.selectedPrecioComb.getProveedor() = this.selectedProveedor;
        this.selectedPrecioComb.setProveedor(this.selectedProveedor);        
    }
    public void onRowSelectProvv(SelectEvent event) {
        this.selectedProveedor = this.selectedPrecioComb.getProveedor();
        System.out.println(this.selectedPrecioComb.getProveedor().getNombre());
    }
       public void btnCreatePrecioComb(ActionEvent actionEvent) {
        PrecioCombDao precioDao = new PrecioCombDaoImpl();
        String msg;
        this.selectedPrecioComb.setEstado("Alta");
        if (precioDao.create(this.selectedPrecioComb)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnUpdatePrecioComb(ActionEvent actionEvent) {
        PrecioCombDao precioDao = new PrecioCombDaoImpl();
        String msg;
        if (precioDao.update(this.selectedPrecioComb)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }    
    public void btnDeletePrecioComb(ActionEvent actionEvent) {
        PrecioCombDao precioDao = new PrecioCombDaoImpl();
        String msg;
        if (precioDao.delete(this.selectedPrecioComb.getIdPrecioComb())) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }    
    public void btnCargar(ActionEvent actionEvent){
        this.selectedProveedor = this.selectedPrecioComb.getProveedor();    
    }
}
