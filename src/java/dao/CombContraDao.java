/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.CombustibleContra;
import model.Contrasenha;

/**
 *
 * @author Usuario
 */
public interface CombContraDao {
    public CombustibleContra findByDet(CombustibleContra combcontra);
    public List<CombustibleContra> findAll();
    public List<CombustibleContra> findByEx(Contrasenha contrasenha);
    public List<CombustibleContra> findById(Integer codcontra);
    public boolean create(CombustibleContra combcontra);
    public boolean update(CombustibleContra combcontra);
    public boolean delete(Integer idDetalle);
}
