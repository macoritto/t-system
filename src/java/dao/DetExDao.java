/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DetExtracto;
import model.Extracto;

/**
 *
 * @author Usuario
 */
public interface DetExDao {
    public DetExtracto findByDetextracto(DetExtracto detextracto);
    public List<DetExtracto> findAll();
    public List<DetExtracto> findByExtracto(Extracto extracto);
    public boolean create(DetExtracto detextracto);
    public boolean update(DetExtracto detextracto);
    public boolean delete(Integer idDetalle);    
}
