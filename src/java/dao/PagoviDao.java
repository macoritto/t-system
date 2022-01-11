/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Cliente;
import model.DetPagoAde;
import model.DetPagoFlete;
import model.DetPagoSumi;
import model.PagoFlete;

/**
 *
 * @author Usuario
 */
public interface PagoviDao {
    public PagoFlete findByPagovi(PagoFlete pagoFlete);
    public List<PagoFlete> findAll();
    public List<PagoFlete> findCliente(Cliente cliente);
    public List<DetPagoFlete> findByDetpagovi(Integer codpagovi);
    public List<DetPagoSumi> findByDetpagosumi(Integer codpagovi);
    public List<DetPagoAde> findByDetpagoade(Integer codpagovi);
    public boolean create(PagoFlete pagoFlete,List<DetPagoFlete> detpagovi);
    public boolean update(PagoFlete pagoFlete);
    public boolean delete(PagoFlete pagoFlete);
    public Integer maxPago();    
}
