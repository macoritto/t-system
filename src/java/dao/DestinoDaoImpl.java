/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.UnidadDestino;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class DestinoDaoImpl implements DestinoDao {

    @Override
    public List<UnidadDestino> findAll() {
        List<UnidadDestino> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM UnidadDestino";
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
    public boolean create(UnidadDestino unidadDestino) {
       boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(unidadDestino);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(UnidadDestino unidadDestino) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            UnidadDestino destinodb =(UnidadDestino) sesion.load(UnidadDestino.class, unidadDestino.getIdUnidadDestino());
            destinodb.setNombre(unidadDestino.getNombre());
            destinodb.setCorreo(unidadDestino.getCorreo());
            destinodb.setDireccion(unidadDestino.getDireccion());
            destinodb.setDescripcion(unidadDestino.getDescripcion());
            sesion.update(destinodb);
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
            UnidadDestino unidadDestino =(UnidadDestino) sesion.load(UnidadDestino.class, idUnidadOrigen);
            sesion.delete(unidadDestino);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public List<UnidadDestino> SelectItems() {
        List<UnidadDestino> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM UnidadDestino";
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
