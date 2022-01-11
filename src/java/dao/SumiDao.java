/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.PrecioSuministro;
import model.ProveeCliente;
import model.Suministro;
import model.TipoSuministro;

/**
 *
 * @author Usuario
 */
public interface SumiDao {
    public List<Suministro> findAll();
    public List<TipoSuministro> tipos();
    public List<Suministro> findOne(Integer cod);
    public List<Suministro> encontrar(Integer cod);
    public List<Suministro> pendientes(Integer cod);
    public List<Suministro> pendienteschapa(Integer cod, String chapa);
    public List<Suministro> pendseguro(Integer cod, TipoSuministro tipo);
    public List<Suministro> pendfaltante(Integer cod, TipoSuministro tipo);
    public List<Suministro> findprovee(Integer proveeCliente);
    public boolean create(Suministro suministro);
    public boolean update(Suministro suministro);
    public boolean delete(Integer idSuministro);
    public List<TipoSuministro> SelectItems();
    public List<PrecioSuministro> precio();
}
