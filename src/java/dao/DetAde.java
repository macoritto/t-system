/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DetExtracto;
import model.Extracto;
import model.PagoAdeDet;
import model.PagoSuministro;

/**
 *
 * @author Usuario
 */
public interface DetAde {
    public boolean create(PagoAdeDet pagodet);
    public List<PagoAdeDet> findByPagoc(PagoSuministro pagoSuministro);
}
