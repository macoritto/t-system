/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.ProveeCliente;

public interface ProSumiDao {
    public List<ProveeCliente> findAll();
    public boolean create(ProveeCliente procli);
    public boolean update(ProveeCliente procli);
    public boolean delete(Integer idProveecli);
    public List<ProveeCliente> SelectItems();
}
