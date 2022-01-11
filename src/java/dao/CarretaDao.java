/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Carreta;

/**
 *
 * @author Usuario
 */
public interface CarretaDao {
    public List<Carreta> SelectItems();
    public List<Carreta> findAll();
    public List<Carreta> findOne();
    public boolean create(Carreta carreta);
    public boolean update(Carreta carreta);
    public boolean delete(String chapaCarreta);
}
