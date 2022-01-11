/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DetallePagoComb;
import model.PagoAdeDet;
import model.PagoCombustible;
import model.PagoSumiDet;
import model.PagoSuministro;
import model.ProveeCliente;
import model.Proveedor;

/**
 *
 * @author Usuario
 */
public interface PagoSuministroDao {
    public PagoCombustible findByPagoc(PagoSuministro pagosuministro);
    public List<PagoSuministro> findAll();
    public List<PagoSuministro> findpro(ProveeCliente proveedor);
    public List<PagoSumiDet> findByDetpagoc(Integer codpagos);
    public List<PagoAdeDet> findByDetpagoa(Integer codpagos);
    public boolean create(PagoSuministro pagoSuministro,List<PagoSumiDet> detpagos);
    public boolean update(PagoSuministro pagoSuministro);
    public boolean delete(PagoSuministro pagoSuministro);
    public Integer maxPago();
}
