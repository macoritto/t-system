/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.UnidadProvee;

public interface UnidadSumiDao {
    public List<UnidadProvee> findAll();
    public boolean create(UnidadProvee unidadsumi);
    public boolean update(UnidadProvee unidadsumi);
    public boolean delete(Integer idUnidad);
}
