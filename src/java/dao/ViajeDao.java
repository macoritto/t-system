/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.Date;
import model.Camion;
import model.Cliente;
import model.OrdenDeCarga;
import model.Propietario;
import model.Viaje;

/**
 *
 * @author Usuario
 */
public interface ViajeDao {
    public List<Viaje> findAll();
    public List<Viaje> findCamion(Camion camion);
    public List<Viaje> fletesumi(Integer orden);
    public List<Viaje> FleteBus(Camion camion, Date fechaini, Date fechafin);
    public List<Viaje> FleteBusCli(Cliente Cliente, Date fechaini, Date fechafin);
    public List<Viaje> FleteBusPro(Propietario propietario, Date fechaini, Date fechafin);
    public List<Viaje> findOne(Integer cod);
    public Viaje Encontraruno(Integer cod);
    public List<Viaje> findpen(Cliente cliente);
    public List<Viaje> findContra(Camion camion);
    public boolean create(Viaje viaje);
    public boolean update(Viaje viaje);
    public boolean delete(Integer idViaje);
    public List<Viaje> findPen(Camion camion);
    public List<Viaje> findEx(Camion camion);
}
