/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.TipoCamionDao;
import dao.TipoCamionDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import model.TipoCamion;

/**
 *
 * @author Usuario
 */
@ManagedBean
@RequestScoped
public class tipocamionBean {
    private List<SelectItem> selectOneItemsTipoCamion;
    public tipocamionBean() {
    }

    public List<SelectItem> getSelectOneItemsTipoCamion() {
        this.selectOneItemsTipoCamion = new ArrayList<SelectItem>();
        TipoCamionDao tipocamionDao = new TipoCamionDaoImpl();
        List <TipoCamion> tipocamiones = tipocamionDao.SelectItems();
        for (TipoCamion tipocamion : tipocamiones){
            SelectItem selecItem = new SelectItem(tipocamion.getIdTipoCamion(), tipocamion.getDescripcion());
            this.selectOneItemsTipoCamion.add(selecItem);
        }
        return selectOneItemsTipoCamion;
    }
}
