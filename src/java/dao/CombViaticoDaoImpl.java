/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Contrasenha;
import model.ViaticoContra;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class CombViaticoDaoImpl implements CombViaticoDao {
    @Override
    public ViaticoContra findByDet(ViaticoContra viacontra) {
        ViaticoContra model = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from ViaticoContra d where d.Contrasenha.idContrasenha = '" + viacontra.getContrasenha().getIdContrasenha() + "'";
        Transaction tx = sesion.beginTransaction();
        try {
            model = (ViaticoContra) sesion.createQuery(sql).uniqueResult();
            tx.commit();


        } catch (Exception e) {
            tx.rollback();
        }

        return model;
    }

    @Override
    public List<ViaticoContra> findAll() {
        List<ViaticoContra> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from ViaticoContra";
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
    public List<ViaticoContra> findByEx(Contrasenha contrasenha) {
        List<ViaticoContra> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from ViaticoContra where contrasenha="+contrasenha.getIdContrasenha();
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
    public List<ViaticoContra> findById(Integer codcontra) {
        List<ViaticoContra> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from ViaticoContra where contrasenha="+codcontra;
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
    public boolean create(ViaticoContra viacontra) {
        boolean flag ;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(viacontra);
            tx.commit();
            flag= true;

        } catch (Exception e) {
            flag=false;
            tx.rollback();
        }

        return flag;
    }

    @Override
    public boolean update(ViaticoContra viacontra) {
        boolean flag ;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.update(viacontra);
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
            ViaticoContra viacontra = (ViaticoContra) sesion.load(ViaticoContra.class,idDetalle);
            sesion.delete(viacontra);
            tx.commit();
            flag= true;

        } catch (Exception e) {
            flag=false;
            tx.rollback();
        }

        return flag;

    }
    
}
