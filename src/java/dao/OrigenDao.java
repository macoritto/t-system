/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.UnidadOrigen;

/**
 *
 * @author Usuario
 */
public interface OrigenDao {
    public List<UnidadOrigen> findAll();
    public boolean create(UnidadOrigen unidadOrigen);
    public boolean update(UnidadOrigen unidadOrigen);
    public boolean delete(Integer idUnidadOrigen);
    public List<UnidadOrigen> SelectItems();
    
}
