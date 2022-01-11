/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Camion;
import model.OrdenDeCarga;
import model.Propietario;

/**
 *
 * @author Usuario
 */
public interface CamionDao {
    public List<Camion> findAll();
    public List<Camion> findOne(Propietario propietario);
    public List<Camion> findCamion(String camion);
    public boolean create(Camion camion);
    public boolean update(Camion camion);
    public boolean delete(String chapaCamion);
}
