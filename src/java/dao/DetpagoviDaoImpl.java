/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DetPagoFlete;
import model.PagoFlete;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class DetpagoviDaoImpl implements DetpagoviDao {
    @Override
    public DetPagoFlete findByDetpagovi(DetPagoFlete detpagovi) {
        DetPagoFlete model = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from DetPagoFlete d where d.PagoFlete.idPagoFlete = '" + detpagovi.getPagoFlete().getIdPagoFlete() + "'";
        Transaction tx = sesion.beginTransaction();
        try {
            model = (DetPagoFlete) sesion.createQuery(sql).uniqueResult();
            tx.commit();


        } catch (Exception e) {
            tx.rollback();
        }

        return model;
    }

    @Override
    public List<DetPagoFlete> findAll() {
        List<DetPagoFlete> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from DetPagoFlete";
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
    public List<DetPagoFlete> findByPagovi(PagoFlete pagoFlete) {
        List<DetPagoFlete> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from DetPagoFlete where pagoFlete="+pagoFlete.getIdPagoFlete();
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
    public boolean create(DetPagoFlete detpagovi) {
        boolean flag ;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(detpagovi);
            tx.commit();
            flag= true;

        } catch (Exception e) {
            flag=false;
            tx.rollback();
        }

        return flag;
    }

    @Override
    public boolean update(DetPagoFlete detpagovi) {
        boolean flag ;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.update(detpagovi);
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
            DetPagoFlete detpagovi = (DetPagoFlete) sesion.load(DetPagoFlete.class,idDetalle);
            sesion.delete(detpagovi);
            tx.commit();
            flag= true;

        } catch (Exception e) {
            flag=false;
            tx.rollback();
        }

        return flag;

    }
    
}
