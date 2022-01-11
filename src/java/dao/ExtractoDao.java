/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Camion;
import model.DetExtracto;
import model.ExCamion;
import model.ExOrden;
import model.Extracto;
import model.Item;

/**
 *
 * @author Usuario
 */
public interface ExtractoDao {
    public Extracto findExtracto(Extracto extracto);
    public List<ExCamion> findById(Integer codextracto);
    public List<Extracto> findAll();
    public List<Extracto> findAnulado();
    public List<DetExtracto> findByDetextracto(Integer codextracto);
    public boolean create(Extracto extracto,List<DetExtracto> detextracto, List<ExCamion> excamion, List<ExOrden> exOrden);
    public boolean update(Extracto extracto);
    public boolean delete(Extracto extracto);
    public Integer maxPago();
    
}
