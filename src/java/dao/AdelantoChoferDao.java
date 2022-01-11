/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.AdelantoChofer;
import model.Camion;
import model.Chofer;
import java.util.Date;
/**
 *
 * @author Usuario
 */
public interface AdelantoChoferDao {
    
    public List<AdelantoChofer> findAll();
    public List<AdelantoChofer> findOne(Integer cod);
    public List<AdelantoChofer> findCamion(Camion camion);
    public List<AdelantoChofer> findbus(Chofer chofer, Date fechaini, Date fechafin);
    public List<AdelantoChofer> findbusfecha(Date fechaini, Date fechafin);
    public boolean create(AdelantoChofer adelantoChofer);
    public boolean update(AdelantoChofer adelantoChofer);
    public boolean delete(Integer idAdelantoChofer);    
}
