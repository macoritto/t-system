/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.OrdenDeCarga;
import model.Tolerancia;

/**
 *
 * @author Usuario
 */
public interface ToleranciaDao {
    public List<Tolerancia> SelectItems();
    public List<Tolerancia> findTole(OrdenDeCarga ordenDeCarga);
    public List<Tolerancia> findAll();
    public boolean create(Tolerancia tolerancia);
    public boolean update(Tolerancia tolerancia);
    public boolean delete(Integer idTolerancia);
}
