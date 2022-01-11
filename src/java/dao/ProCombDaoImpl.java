/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Proveedor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class ProCombDaoImpl implements ProCombDao {
    @Override
    public List<Proveedor> findAll() {
        List<Proveedor> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Proveedor";
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
    public boolean create(Proveedor proveedor) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(proveedor);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(Proveedor proveedor) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Proveedor proveedordb =(Proveedor) sesion.load(Proveedor.class, proveedor.getIdProveedor());
            proveedordb.setNombre(proveedor.getNombre());
            proveedordb.setRuc(proveedor.getRuc());
            proveedordb.setDireccion(proveedor.getDireccion());
            proveedordb.setTelefono(proveedor.getTelefono());
            proveedordb.setEmblema(proveedor.getEmblema());
            proveedordb.setDescripcion(proveedor.getDescripcion());
            sesion.update(proveedordb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
}
    @Override
    public boolean delete(Integer idProveedor) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Proveedor Proveedor =(Proveedor) sesion.load(Proveedor.class, idProveedor);
            sesion.delete(Proveedor);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }
    
}
