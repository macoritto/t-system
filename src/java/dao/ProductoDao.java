/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Cliente;
import model.Producto;

/**
 *
 * @author Usuario
 */
public interface ProductoDao {
    public List<Producto> findAll();
    public boolean create(Producto producto);
    public boolean update(Producto producto);
    public boolean delete(Integer idProducto);
    public List<Producto> SelectItems();
     public List<Producto> SelectItemsCli(Integer cod);
}
