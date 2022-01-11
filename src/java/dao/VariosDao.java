/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Camion;
import model.ProveedorVarios;
import java.util.Date;
import model.Varios;

/**
 *
 * @author Usuario
 */
public interface VariosDao {
    public List<Varios> findAll();
    public List<Varios> findOne(Integer cod);
    public List<Varios> findCamion(Camion camion);
    public List<Varios> findbus(Camion camion, Date fechaini, Date fechafin);
    public List<Varios> findfecha(ProveedorVarios proveedor, Date fechaini, Date fechafin);
    public List<Varios> findpen(ProveedorVarios proveedorVarios);
    public List<Varios> findpro(ProveedorVarios proveedorVarios);
    public boolean create(Varios varios);
    public boolean update(Varios varios);
    public boolean delete(Integer idVarios);    
}
