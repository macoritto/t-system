/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.TipoCombustible;

/**
 *
 * @author Usuario
 */
public interface TipoCombDao {
    public List<TipoCombustible> findAll();
    public boolean create(TipoCombustible tipoCombustible);
    public boolean update(TipoCombustible tipoCombustible);
    public boolean delete(Integer idTipoCombustible);
    public List<TipoCombustible> SelectItems();
}
