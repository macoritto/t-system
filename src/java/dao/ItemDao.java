/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Camion;
import model.Item;

/**
 *
 * @author Usuario
 */
public interface ItemDao {
    public List<Item> findAll();   
    public Item Itemcompa(Camion camion, Integer coditem, Integer codtipo);
    public List<Item> findCamion(Camion camion);
    public boolean create(Item item);
    public boolean update(Item item);
    public boolean delete(Integer idIten);
    public Integer maxItem();    
}
