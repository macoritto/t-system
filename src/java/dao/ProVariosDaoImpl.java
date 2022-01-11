/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.ProveedorVarios;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class ProVariosDaoImpl implements ProVariosDao {
    @Override
    public List<ProveedorVarios> findAll() {
        List<ProveedorVarios> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM ProveedorVarios";
        System.out.println(sql);
        Transaction tx = sesion.beginTransaction();
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }

    @Override
    public boolean create(ProveedorVarios proveedorVarios) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(proveedorVarios);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(ProveedorVarios proveedorVarios) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            ProveedorVarios proveedorVariosdb =(ProveedorVarios) sesion.load(ProveedorVarios.class, proveedorVarios.getIdProveedorVarios());
            proveedorVariosdb.setNombre(proveedorVarios.getNombre());
            proveedorVariosdb.setTelefono(proveedorVarios.getTelefono());
            proveedorVariosdb.setDireccion(proveedorVarios.getDireccion());
            proveedorVariosdb.setFechaInicio(proveedorVarios.getFechaInicio());
            proveedorVariosdb.setRuc(proveedorVarios.getRuc());
            sesion.update(proveedorVariosdb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
}
    @Override
    public boolean delete(Integer idProveedorVarios) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            ProveedorVarios ProveedorVarios =(ProveedorVarios) sesion.load(ProveedorVarios.class, idProveedorVarios);
            sesion.delete(ProveedorVarios);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }
    
    
}
