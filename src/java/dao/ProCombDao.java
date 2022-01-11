/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Proveedor;

public interface ProCombDao {
    public List<Proveedor> findAll();
    public boolean create(Proveedor proveedor);
    public boolean update(Proveedor proveedor);
    public boolean delete(Integer idProveedor);
}
