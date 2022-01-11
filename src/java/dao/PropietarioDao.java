/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Propietario;

/**
 *
 * @author Usuario
 */
public interface PropietarioDao {
    public List<Propietario> SelectItems();
    public List<Propietario> findAll();
    public boolean create(Propietario propietario);
    public boolean update(Propietario propietario);
    public boolean delete(Integer idPropietario);
}
