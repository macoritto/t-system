/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.DetOrdenDao;
import dao.DetOrdenDaoImpl;
import dao.RolDao;
import dao.RolDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.DetExtracto;
import model.DetOrden;
import model.OrdenDeCarga;
import model.Rol;

/**
 *
 * @author Usuario
 */
@ManagedBean
@RequestScoped
public class detordenBean {
    private List<SelectItem> selectOneItemsRol;
    public detordenBean() {
    }
    public void btnCreateDetOrden(ActionEvent actionEvent) {
        String msg;
        DetOrden selectedOrden = new DetOrden();
        DetOrdenDao ordenDao = new DetOrdenDaoImpl();
        OrdenDeCarga orden = new OrdenDeCarga();
        DetExtracto detex= new DetExtracto();
        detex.setIdDet(123742);
        orden.setIdOrdenDeCarga(27012);
        selectedOrden.setDescrip("aaa");
        selectedOrden.setMonto(0.0);
        selectedOrden.setOrdenDeCarga(orden);
        selectedOrden.setDetExtracto(detex);
        selectedOrden.setIdDetOrden(1);
        if(ordenDao.create(selectedOrden)){
            msg="Se creo el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
            msg="no se creo el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public List<SelectItem> getSelectOneItemsRol() {
        this.selectOneItemsRol = new ArrayList<SelectItem>();
        RolDao rolDao = new RolDaoImpl();
        List <Rol> roles = rolDao.SelectItems();
        for (Rol rol : roles){
            SelectItem selecItem = new SelectItem(rol.getId(), rol.getNombre());
            this.selectOneItemsRol.add(selecItem);
        }
        return selectOneItemsRol;
    }
    
}
