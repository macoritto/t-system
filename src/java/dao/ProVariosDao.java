/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.ProveedorVarios;

/**
 *
 * @author Usuario
 */
public interface ProVariosDao {
    public List<ProveedorVarios> findAll();
    public boolean create(ProveedorVarios proveedor);
    public boolean update(ProveedorVarios proveedor);
    public boolean delete(Integer idProveedorVarios);
}
