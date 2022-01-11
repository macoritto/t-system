/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.Date;
import model.Camion;
import model.Combustible;
import model.OrdenDeCarga;
import model.Proveedor;
import model.ValeCombustible;

/**
 *
 * @author Usuario
 */
public interface CombDao {
    public List<Combustible> findAll();
    public List<Combustible> findVale(OrdenDeCarga orden);
    public List<Combustible> findVale2(OrdenDeCarga orden, Camion camion);
    public List<Combustible> findOne(Integer cod);
    public List<Combustible> findCamion(Camion camion);
    public List<Combustible> findCamionT(Camion camion, Date fechaini, Date fechafin);
    public List<Combustible> findCamionP(Proveedor proveedor, Date fechaini, Date fechafin);
    public List<Combustible> findpen(Proveedor proveedor);
    public List<Combustible> findpro(Proveedor proveedor);
    public boolean create(Combustible combustible);
    public boolean update(Combustible combustible);
    public boolean delete(Integer idCombustible);
}
