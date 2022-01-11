/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DetallePagoComb;
import model.PagoCombustible;
import model.Proveedor;

/**
 *
 * @author Usuario
 */
public interface PagocDao {
    public PagoCombustible findByPagoc(PagoCombustible pagoCombustible);
    public List<PagoCombustible> findAll();
    public List<PagoCombustible> findpro(Proveedor proveedor);
    public List<DetallePagoComb> findByDetpagoc(Integer codpagoc);
    public boolean create(PagoCombustible pagoCombustible,List<DetallePagoComb> detpagoc);
    public boolean update(PagoCombustible pagoCombustible);
    public boolean delete(PagoCombustible pagoCombustible);
    public Integer maxPago();
}
