/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.AdeCliente;
import model.AdeSuministro;
import model.Cliente;
import model.ProveeCliente;

public interface AdeClienteDao {
    public List<AdeCliente> findAll();
    public List<AdeCliente> findOne(Integer cod);
    public List<AdeCliente> proveedor(Cliente cliente);
    public boolean create(AdeCliente adecliente);
    public boolean update(AdeCliente adecliente);
    public boolean delete(Integer idAde);
}
