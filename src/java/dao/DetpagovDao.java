/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DetallePagoVarios;
import model.PagoVarios;

/**
 *
 * @author Usuario
 */
public interface DetpagovDao {
    public DetallePagoVarios findByDetpagov(DetallePagoVarios detpagov);
    public List<DetallePagoVarios> findAll();
    public List<DetallePagoVarios> findByPagov(PagoVarios pagoVarios);
    public boolean create(DetallePagoVarios detpagov);
    public boolean update(DetallePagoVarios detpagov);
    public boolean delete(Integer idDetalle);     
}
