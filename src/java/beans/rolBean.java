/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.RolDao;
import dao.RolDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import model.Rol;

/**
 *
 * @author Usuario
 */
@ManagedBean
@RequestScoped
public class rolBean {
    private List<SelectItem> selectOneItemsRol;
    public rolBean() {
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
