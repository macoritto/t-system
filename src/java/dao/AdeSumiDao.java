/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.AdeSuministro;
import model.ProveeCliente;

public interface AdeSumiDao {
    public List<AdeSuministro> findAll();
    public List<AdeSuministro> findOne(Integer cod);
    public List<AdeSuministro> proveedor(ProveeCliente provee);
    public boolean create(AdeSuministro adesumi);
    public boolean update(AdeSuministro adesumi);
    public boolean delete(Integer idSumi);
}
