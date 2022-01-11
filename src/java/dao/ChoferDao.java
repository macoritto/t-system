/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Chofer;

public interface ChoferDao {
    public List<Chofer> SelectItems();
    public List<Chofer> findAll();
    public boolean create(Chofer chofer);
    public boolean update(Chofer chofer);
    public boolean delete(Integer idChofer);
}
