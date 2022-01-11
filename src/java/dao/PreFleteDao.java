/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.OrdenDeCarga;
import model.PrecioFlete;

/**
 *
 * @author Usuario
 */
public interface PreFleteDao {
    public List<PrecioFlete> findAll();
    public List<PrecioFlete> findBaja();
    public List<PrecioFlete> findPre(OrdenDeCarga ordenDeCarga);
    public boolean create(PrecioFlete precioFlete);
    public boolean update(PrecioFlete precioFlete);
    public boolean delete(Integer idPrecioFlete);
}
