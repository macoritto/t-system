/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.CombustibleContra;
import model.Contrasenha;
import model.ViaticoContra;

/**
 *
 * @author Usuario
 */
public interface ContraDao {
    public List<Contrasenha> findAll();
    public boolean create(Contrasenha contrasenha, List<CombustibleContra> combcontra, List<ViaticoContra> viacontra);
    public boolean update(Contrasenha contrasenha);
    public boolean delete(Integer idContrasenha);
}
