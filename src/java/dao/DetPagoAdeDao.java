/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DetPagoAde;
import model.PagoFlete;

/**
 *
 * @author Usuario
 */
public interface DetPagoAdeDao {
    public DetPagoAde findByDetpagov(DetPagoAde detpagov);
    public List<DetPagoAde> findAll();
    public List<DetPagoAde> findByPagov(PagoFlete pagoFlete);
    public boolean create(DetPagoAde detpagos);
    public boolean update(DetPagoAde detpagos);
    public boolean delete(Integer idDetalle);     
}
