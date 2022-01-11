/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Camion;
import model.OrdenDeCarga;
import model.Proveedor;
import model.ValeCombustible;

/**
 *
 * @author Usuario
 */
public interface ValeCombDao {
    public List<ValeCombustible> findAll();
    public List<ValeCombustible> findPen(Proveedor proveedor);
    public List<ValeCombustible> findPendiente();
    public List<ValeCombustible> findAceptado();
    public List<ValeCombustible> findAnulado();
    public List<ValeCombustible> findContra(OrdenDeCarga orden);
    public List<ValeCombustible> findReporte(ValeCombustible valeCombustible);
    public List<ValeCombustible> findVales(Camion camion);
    public boolean create(ValeCombustible valeCombustible);
    public boolean update(ValeCombustible valeCombustible);
    public boolean delete(Integer idValeCombustible);
}
