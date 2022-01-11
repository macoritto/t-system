/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Extracto;
import model.ExtractoSumi;

/**
 *
 * @author Usuario
 */
public interface ExSumiDao {
    public ExtractoSumi findByDetextracto(Integer extractoSumi);
    public List<ExtractoSumi> findAll();
    public List<ExtractoSumi> findByExtracto(Integer extracto);
    public List<ExtractoSumi> findBySumi(Integer extracto);
    public boolean create(ExtractoSumi extractoSumi);
    public boolean update(ExtractoSumi extractoSumi);
    public boolean delete(Integer idDetalle);    
}
