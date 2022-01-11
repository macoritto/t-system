/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DetPagoFlete;
import model.PagoFlete;

/**
 *
 * @author Usuario
 */
public interface DetpagoviDao {
    public DetPagoFlete findByDetpagovi(DetPagoFlete detpagovi);
    public List<DetPagoFlete> findAll();
    public List<DetPagoFlete> findByPagovi(PagoFlete pagoFlete);
    public boolean create(DetPagoFlete detpagovi);
    public boolean update(DetPagoFlete detpagovi);
    public boolean delete(Integer idDetalle);     
}
