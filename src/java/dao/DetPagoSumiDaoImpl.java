/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DetPagoSumi;
import model.DetallePagoVarios;
import model.PagoFlete;
import model.PagoVarios;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class DetPagoSumiDaoImpl implements DetPagoSumiDao {
    @Override
    public DetPagoSumi findByDetpagov(DetPagoSumi detpagov) {
        DetPagoSumi model = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from DetPagoSumi d where d.PagoFlete.idPagoFlete = '" + detpagov.getPagoFlete().getIdPagoFlete() + "'";
        Transaction tx = sesion.beginTransaction();
        try {
            model = (DetPagoSumi) sesion.createQuery(sql).uniqueResult();
            tx.commit();


        } catch (Exception e) {
            tx.rollback();
        }

        return model;
    }

    @Override
    public List<DetPagoSumi> findAll() {
        List<DetPagoSumi> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from DetPagoSumi";
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
    public List<DetPagoSumi> findByPagov(PagoFlete pagoFlete) {
        List<DetPagoSumi> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from DetPagoSumi where pagoFlete="+pagoFlete.getIdPagoFlete();
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
    public boolean create(DetPagoSumi detpagov) {
        boolean flag ;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(detpagov);
            tx.commit();
            flag= true;

        } catch (Exception e) {
            flag=false;
            tx.rollback();
        }

        return flag;
    }

    @Override
    public boolean update(DetPagoSumi detpagov) {
        boolean flag ;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.update(detpagov);
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
            DetPagoSumi detpagov = (DetPagoSumi) sesion.load(DetPagoSumi.class,idDetalle);
            sesion.delete(detpagov);
            tx.commit();
            flag= true;

        } catch (Exception e) {
            flag=false;
            tx.rollback();
        }

        return flag;

    }
    
}
