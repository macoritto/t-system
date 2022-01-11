/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Extracto;
import model.ExtractoSumicli;

/**
 *
 * @author Usuario
 */
public interface ExSumicliDao {
    public ExtractoSumicli findByDetextracto(ExtractoSumicli extractoSumicli);
    public List<ExtractoSumicli> findAll();
    public List<ExtractoSumicli> findByExtracto(Integer extracto);
    public boolean create(ExtractoSumicli extractoSumicli);
    public boolean update(ExtractoSumicli extractoSumicli);
    public boolean delete(Integer idDetalle);    
}
