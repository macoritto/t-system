/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Cliente;
import model.OrdenDeCarga;
import model.SumiCliente;

public interface SumiCliDao {
    public List<SumiCliente> findAll();
    public List<SumiCliente> findOne(Integer orden);
    public List<SumiCliente> findOneChapa(Integer orden, String chapa); 
    public List<SumiCliente> proveedor(Cliente cliente);
    public List<SumiCliente> encontrar(Integer id);
    public boolean create(SumiCliente sumicliente);
    public boolean update(SumiCliente sumicliente);
    public boolean delete(Integer idSumi);
}
