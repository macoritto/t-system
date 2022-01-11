/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DetallePagoComb;
import model.PagoCombustible;

/**
 *
 * @author Usuario
 */
public interface DetpagocDao {
    public DetallePagoComb findByDetpagoc(DetallePagoComb detpagoc);
    public List<DetallePagoComb> findAll();
    public List<DetallePagoComb> findByPagoc(PagoCombustible pagoCombustible);
    public boolean create(DetallePagoComb detpagoc);
    public boolean update(DetallePagoComb detpagoc);
    public boolean delete(Integer idDetalle);
}
