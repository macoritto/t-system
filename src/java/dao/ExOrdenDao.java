/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DetOrden;
import model.ExOrden;
import model.Extracto;

/**
 *
 * @author Usuario
 */
public interface ExOrdenDao {
    public ExOrden findByDetextracto(ExOrden exOrden);
    public List<ExOrden> findAll();
    public List<ExOrden> findByExtracto(Integer extracto);
    public boolean create(ExOrden exOrden);
    public boolean update(ExOrden exOrden);
    public boolean delete(Integer idDetalle);    
}
