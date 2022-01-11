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
public interface PagoCombustibleDao {
    public List<PagoCombustible> findAll();
    public Integer maxPago();
    public boolean create(PagoCombustible pagoCombustible, List<DetallePagoComb> detallepago);
    public boolean update(PagoCombustible pagoCombustible);
    public boolean delete(PagoCombustible idPagoCombustible);
    
}
