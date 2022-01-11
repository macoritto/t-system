/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DetallePagoVarios;
import model.PagoVarios;
import model.ProveedorVarios;

/**
 *
 * @author Usuario
 */
public interface PagovDao {
    public PagoVarios findByPagov(PagoVarios pagoVarios);
    public List<PagoVarios> findAll();
    public List<PagoVarios> findProveedor(ProveedorVarios proveedor);
    public List<DetallePagoVarios> findByDetpagov(Integer codpagov);
    public boolean create(PagoVarios pagoVarios,List<DetallePagoVarios> detpagovarios);
    public boolean update(PagoVarios pagoVarios);
    public boolean delete(PagoVarios pagoVarios);
    public Integer maxPago();
    
}
