/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.TipoCarretaDao;
import dao.TipoCarretaDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import model.TipoCarreta;

/**
 *
 * @author Usuario
 */
@ManagedBean
@RequestScoped
public class tipoCarretaBean {
    private List<SelectItem> selectOneItemsTipoCarreta;
    public tipoCarretaBean() {
    }

    public List<SelectItem> getSelectOneItemsTipoCarreta() {
        this.selectOneItemsTipoCarreta = new ArrayList<SelectItem>();
        TipoCarretaDao tipocarretaDao = new TipoCarretaDaoImpl();
        List <TipoCarreta> tipocarretas = tipocarretaDao.SelectItems();
        for (TipoCarreta tipocarreta : tipocarretas){
            SelectItem selecItem = new SelectItem(tipocarreta.getIdTipoCarreta(), tipocarreta.getDescripcion());
            this.selectOneItemsTipoCarreta.add(selecItem);
        }
        return selectOneItemsTipoCarreta;
    }
}