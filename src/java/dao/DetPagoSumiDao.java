/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DetPagoSumi;
import model.DetallePagoVarios;
import model.PagoFlete;
import model.PagoVarios;

/**
 *
 * @author Usuario
 */
public interface DetPagoSumiDao {
    public DetPagoSumi findByDetpagov(DetPagoSumi detpagov);
    public List<DetPagoSumi> findAll();
    public List<DetPagoSumi> findByPagov(PagoFlete pagoFlete);
    public boolean create(DetPagoSumi detpagos);
    public boolean update(DetPagoSumi detpagos);
    public boolean delete(Integer idDetalle);     
}
