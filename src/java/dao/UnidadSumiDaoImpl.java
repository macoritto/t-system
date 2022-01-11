/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.UnidadProvee;
import model.TipoProvee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class UnidadSumiDaoImpl implements UnidadSumiDao {
    @Override
    public List<UnidadProvee> findAll() {
        List<UnidadProvee> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM UnidadProvee";
        System.out.println(sql);
        Transaction tx = sesion.beginTransaction();
        try {
            listado = sesion.createQuery(sql).list();
            //System.out.println(sesion.createQuery(sql).list());
            System.out.println("HOLA");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }

    @Override
    public boolean create(UnidadProvee unidad) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(unidad);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(UnidadProvee unidad) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            UnidadProvee proveedordb =(UnidadProvee) sesion.load(UnidadProvee.class, unidad.getIdUnidad());
            proveedordb.setNombre(unidad.getNombre());
            proveedordb.setTelefono(unidad.getTelefono());
            proveedordb.setDescrip(unidad.getDescrip());
            proveedordb.setProveeCliente(unidad.getProveeCliente());
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
    public boolean delete(Integer idUnidad) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            UnidadProvee unidad =(UnidadProvee) sesion.load(UnidadProvee.class, idUnidad);
            sesion.delete(unidad);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }
    
}
