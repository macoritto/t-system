/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.TipoItem;

/**
 *
 * @author Usuario
 */
public interface TipoItemDao {
    public List<TipoItem> findOne();
    public List<TipoItem> findFaltante();
    public List<TipoItem> findSeguro();
    public List<TipoItem> findComb();
    public List<TipoItem> findVia();
    public List<TipoItem> findVarios();
    public List<TipoItem> findCredito();
    public List<TipoItem> findAdelanto();
    public List<TipoItem> findRecibo();
}
