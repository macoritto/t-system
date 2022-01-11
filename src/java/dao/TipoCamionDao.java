/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.TipoCamion;

/**
 *
 * @author Usuario
 */
public interface TipoCamionDao {
    public List<TipoCamion> SelectItems();
    public List<TipoCamion> findAll();
}
