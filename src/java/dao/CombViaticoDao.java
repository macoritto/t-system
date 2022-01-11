/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Contrasenha;
import model.ViaticoContra;

/**
 *
 * @author Usuario
 */
public interface CombViaticoDao {
    public ViaticoContra findByDet(ViaticoContra viacontra);
    public List<ViaticoContra> findAll();
    public List<ViaticoContra> findByEx(Contrasenha contrasenha);
    public List<ViaticoContra> findById(Integer codcontra);
    public boolean create(ViaticoContra viacontra);
    public boolean update(ViaticoContra viacontra);
    public boolean delete(Integer idDetalle);
}
