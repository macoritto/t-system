/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Cliente;

public interface ClienteDao {
    public List<Cliente> findAll();
    public boolean create(Cliente cliente);
    public boolean update(Cliente cliente);
    public boolean delete(Integer idCliente);
    public List<Cliente> SelectItems();
}
