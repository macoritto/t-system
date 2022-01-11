/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DetExtracto;
import model.Extracto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class DetExDaoImpl implements DetExDao {
    @Override
    public DetExtracto findByDetextracto(DetExtracto detextracto) {
        DetExtracto model = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from DetExtracto d where d.Extracto.idLiquidacion = '" + detextracto.getExtracto().getIdLiquidacion() + "'";
        Transaction tx = sesion.beginTransaction();
        try {
            model = (DetExtracto) sesion.createQuery(sql).uniqueResult();
            tx.commit();


        } catch (Exception e) {
            tx.rollback();
        }

        return model;
    }

    @Override
    public List<DetExtracto> findAll() {
        List<DetExtracto> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from DetExtracto";
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
    public List<DetExtracto> findByExtracto(Extracto extracto) {
        List<DetExtracto> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from DetExtracto where extracto='"+extracto.getIdLiquidacion()+"' order by idDet";
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
    public boolean create(DetExtracto detextracto) {
        boolean flag ;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(detextracto);
            tx.commit();
            flag= true;

        } catch (Exception e) {
            flag=false;
            tx.rollback();
        }

        return flag;
    }

    @Override
    public boolean update(DetExtracto detextracto) {
        boolean flag ;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.update(detextracto);
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
            DetExtracto detextracto = (DetExtracto) sesion.load(DetExtracto.class,idDetalle);
            sesion.delete(detextracto);
            tx.commit();
            flag= true;

        } catch (Exception e) {
            flag=false;
            tx.rollback();
        }

        return flag;

    }
}
