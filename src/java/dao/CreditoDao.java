/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Camion;
import model.Credito;
import model.Propietario;
import java.util.Date;
/**
 *
 * @author Usuario
 */
public interface CreditoDao {
    public List<Credito> findAll();
    public List<Credito> findOne(Integer cod);
    public List<Credito> findPropie(Propietario propie);
    public List<Credito> findbus(Propietario propie, Date fechaini, Date fechafin);
    public List<Credito> findfecha(Date fechaini, Date fechafin);
    public List<Credito> findOneUp(String nroliq);
    public List<Credito> findCamion(Camion camion);
    public boolean create(Credito credito);
    public boolean update(Credito credito);
    public boolean delete(Integer idCredito);   
}
