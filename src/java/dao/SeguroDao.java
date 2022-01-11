/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.OrdenDeCarga;
import model.Seguro;

/**
 *
 * @author Usuario
 */
public interface SeguroDao {
    public List<Seguro> findSeguro(Integer id);    
    public List<Seguro> findAll();    
    public boolean create(Seguro seguro);
    public boolean update(Seguro seguro);
    public boolean delete(Integer idSeguro);
}
