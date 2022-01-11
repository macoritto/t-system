/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Recibo;
import java.util.List;
import model.Camion;

/**
 *
 * @author Usuario
 */
public interface ReciboDao {
    public List<Recibo> findAll();
    public List<Recibo> findOne(Integer cod);
   public List<Recibo> findcamion(Camion camion);
    public boolean create(Recibo recibo);
    public boolean update(Recibo recibo);
    public boolean delete(Integer id);
}
