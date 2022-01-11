/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Extracto;
import model.ExtractoSumi;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class ExSumiDaoImpl implements ExSumiDao {
    @Override
    public ExtractoSumi findByDetextracto(Integer extractoSumi) {
        ExtractoSumi model = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from ExtractoSumi d where d.det_extracto= '" + extractoSumi + "'";
        Transaction tx = sesion.beginTransaction();
        try {
            model = (ExtractoSumi) sesion.createQuery(sql).uniqueResult();
            tx.commit();


        } catch (Exception e) {
            tx.rollback();
        }

        return model;
    }

    @Override
    public List<ExtractoSumi> findAll() {
        List<ExtractoSumi> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from ExtractoSumi";
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
    public List<ExtractoSumi> findByExtracto(Integer extracto) {
        List<ExtractoSumi> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from ExtractoSumi where id_det_extracto='"+extracto+"'";
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
    public List<ExtractoSumi> findBySumi(Integer extracto) {
        List<ExtractoSumi> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from ExtractoSumi where id_suministro='"+extracto+"'";
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
    public boolean create(ExtractoSumi extractoSumi) {
        boolean flag ;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(extractoSumi);
            tx.commit();
            flag= true;

        } catch (Exception e) {
            flag=false;
            tx.rollback();
        }

        return flag;
    }

    @Override
    public boolean update(ExtractoSumi extractoSumi) {
        boolean flag ;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.update(extractoSumi);
            tx.commit();
            flag= true;

        } catch (Exception e) {
            flag=false;
            tx.rollback();
        }

        return flag;
    }

    @Override
    public boolean delete(Integer idDetalle) {
        boolean flag ;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            ExtractoSumi extractoSumi = (ExtractoSumi) sesion.load(ExtractoSumi.class,idDetalle);
            sesion.delete(extractoSumi);
            tx.commit();
            flag= true;

        } catch (Exception e) {
            flag=false;
            tx.rollback();
        }

        return flag;

    }
}
