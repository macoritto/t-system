/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Camion;
import model.ProveedorVarios;
import model.ValeVarios;

/**
 *
 * @author Usuario
 */
public interface ValeVariosDao {
    public List<ValeVarios> findAceptado();
    public List<ValeVarios> findPendiente();
    public List<ValeVarios> findAnulado();
    public List<ValeVarios> findAll();
    public List<ValeVarios> findVales(Camion camion);
    public List<ValeVarios> findPro(ProveedorVarios proveedor);
    public boolean create(ValeVarios valeVarios);
    public boolean update(ValeVarios valeVarios);
    public boolean delete(Integer idValeVarios);
    
}
