/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.UnidadDestino;

/**
 *
 * @author Usuario
 */
public interface DestinoDao {
    public List<UnidadDestino> findAll();
    public boolean create(UnidadDestino unidadDestino);
    public boolean update(UnidadDestino unidadDestino);
    public boolean delete(Integer idUnidadOrigen);
    public List<UnidadDestino> SelectItems();
}
