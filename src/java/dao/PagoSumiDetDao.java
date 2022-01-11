/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DetallePagoComb;
import model.PagoCombustible;
import model.PagoSumiDet;
import model.PagoSuministro;

/**
 *
 * @author Usuario
 */
public interface PagoSumiDetDao {
    public PagoSumiDet findByDetpagoc(PagoSumiDet detsumi);
    public List<PagoSumiDet> findAll();
    public List<PagoSumiDet> findByPagoc(PagoSuministro pagoSuministro);
    public boolean create(PagoSumiDet detsumi);
    public boolean update(PagoSumiDet detsumi);
    public boolean delete(Integer idDetalle);
}
