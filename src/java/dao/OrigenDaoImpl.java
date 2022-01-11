/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.UnidadOrigen;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class OrigenDaoImpl implements OrigenDao{

    @Override
    public List<UnidadOrigen> findAll() {
        List<UnidadOrigen> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM UnidadOrigen";
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
    public boolean create(UnidadOrigen unidadOrigen) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(unidadOrigen);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(UnidadOrigen unidadOrigen) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            UnidadOrigen origendb =(UnidadOrigen) sesion.load(UnidadOrigen.class, unidadOrigen.getIdUnidadOrigen());
            origendb.setNombre(unidadOrigen.getNombre());
            origendb.setCorreo(unidadOrigen.getCorreo());
            origendb.setDireccion(unidadOrigen.getDireccion());
            origendb.setDescripcion(unidadOrigen.getDescripcion());
            sesion.update(origendb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean delete(Integer idUnidadOrigen) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            UnidadOrigen unidadOrigen =(UnidadOrigen) sesion.load(UnidadOrigen.class, idUnidadOrigen);
            sesion.delete(unidadOrigen);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public List<UnidadOrigen> SelectItems() {
        List<UnidadOrigen> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM UnidadOrigen";
        Transaction tx = sesion.beginTransaction();
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }
}
