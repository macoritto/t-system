/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Extracto;
import model.ExtractoSumi;
import model.ExtractoSumicli;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class ExSumicliDaoImpl implements ExSumicliDao {
    @Override
    public ExtractoSumicli findByDetextracto(ExtractoSumicli extractoSumicli) {
        ExtractoSumicli model = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from ExtractoSumi d where d.det_extracto.Extracto.idLiquidacion = '" + extractoSumicli.getDetExtracto().getExtracto().getIdLiquidacion() + "'";
        Transaction tx = sesion.beginTransaction();
        try {
            model = (ExtractoSumicli) sesion.createQuery(sql).uniqueResult();
            tx.commit();


        } catch (Exception e) {
            tx.rollback();
        }

        return model;
    }

    @Override
    public List<ExtractoSumicli> findAll() {
        List<ExtractoSumicli> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from ExtractoSumicli";
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
    public List<ExtractoSumicli> findByExtracto(Integer extracto) {
        List<ExtractoSumicli> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from ExtractoSumicli where id_det_extracto='"+extracto+"'";
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
    public boolean create(ExtractoSumicli extractoSumicli) {
        boolean flag ;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(extractoSumicli);
            tx.commit();
            flag= true;

        } catch (Exception e) {
            flag=false;
            tx.rollback();
        }

        return flag;
    }

    @Override
    public boolean update(ExtractoSumicli extractoSumicli) {
        boolean flag ;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.update(extractoSumicli);
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
            ExtractoSumicli extractoSumicli = (ExtractoSumicli) sesion.load(ExtractoSumicli.class,idDetalle);
            sesion.delete(extractoSumicli);
            tx.commit();
            flag= true;

        } catch (Exception e) {
            flag=false;
            tx.rollback();
        }

        return flag;

    }
}
