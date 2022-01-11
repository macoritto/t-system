/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Camion;
import model.Cliente;
import model.OrdenDeCarga;
import model.Viaje;

/**
 *
 * @author Usuario
 */
public interface OrdenDeCargaDao {
    public List<OrdenDeCarga> findAll();
    public List<OrdenDeCarga> findCliente(Cliente cliente);
    public List<OrdenDeCarga> findOne();
    public List<OrdenDeCarga> findUno(Integer cod);
    public List<OrdenDeCarga> encontrar(Integer cod);
    public List<OrdenDeCarga> encontrarLiq(Camion camion);
    public List<OrdenDeCarga> findPen(Camion camion);
    public List<OrdenDeCarga> findOrden(Camion camion);
    public boolean create(OrdenDeCarga ordenDeCarga);
    public boolean update(OrdenDeCarga ordenDeCarga);
    public boolean delete(Integer idOrdenDeCarga);
    public List<OrdenDeCarga> findPendiente();
    public List<OrdenDeCarga> findAnulado();
    public List<OrdenDeCarga> findAceptado();
    public List<OrdenDeCarga> findLiq(Camion camion);
}

