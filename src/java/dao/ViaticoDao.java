/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.Date;
import model.Camion;
import model.OrdenDeCarga;
import model.Viatico;

/**
 *
 * @author Usuario
 */
public interface ViaticoDao {
    public List<Viatico> findAll();
    public List<Viatico> findbus(Camion camion, Date fechaini,Date fechafin);
    public List<Viatico> findfecha(Date fechaini,Date fechafin);
    public List<Viatico> findVia(OrdenDeCarga orden, Camion camion);
    public List<Viatico> findVia2(OrdenDeCarga orden, Camion camion);
    public List<Viatico> findContra(OrdenDeCarga orden);
    public List<Viatico> findOne();
    public List<Viatico> find(Integer cod);
    public List<Viatico> findPendiente();
    public List<Viatico> findAceptado();
    public List<Viatico> findAnulado();
    public List<Viatico> findViaticos(Camion camion);
    public List<Viatico> findCamion(Camion camion);
    public boolean create(Viatico viatico);
    public boolean update(Viatico viatico);
    public boolean delete(Integer idViatico);
}
