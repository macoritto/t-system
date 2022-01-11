/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.ExCamion;
import model.Extracto;

/**
 *
 * @author Usuario
 */
public interface ExCamionDao {
    public ExCamion findByDet(ExCamion excamion);
    public List<ExCamion> findAll();
    public List<ExCamion> findByEx(Extracto extracto);
    public List<ExCamion> findById(Integer codextracto);
    public boolean create(ExCamion excamion);
    public boolean update(ExCamion excamion);
    public boolean delete(Integer idDetalle);   
}
