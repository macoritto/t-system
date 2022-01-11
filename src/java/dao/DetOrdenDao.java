/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DetOrden;
import model.Extracto;

/**
 *
 * @author Usuario
 */
public interface DetOrdenDao {
    public DetOrden findByDetextracto(DetOrden detOrden);
    public List<DetOrden> findAll();
    public List<DetOrden> findByExtracto(Extracto extracto);
    public boolean create(DetOrden detOrden);
    public boolean update(DetOrden detOrden);
    public boolean delete(Integer idDetalle);    
}
