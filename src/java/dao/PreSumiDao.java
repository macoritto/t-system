/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.PrecioSuministro;
import model.ProveeCliente;
import model.UnidadProvee;

public interface PreSumiDao {
    public List<PrecioSuministro> findAll();
    public boolean create(PrecioSuministro presumi);
    public boolean update(PrecioSuministro presumi);
    public boolean delete(Integer idPrecioSumi);
    public List<PrecioSuministro> findPro(UnidadProvee unidad);
}
