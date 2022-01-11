/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.CombustibleContra;
import model.Contrasenha;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class CombContraDaoImpl implements CombContraDao {
    @Override
    public CombustibleContra findByDet(CombustibleContra combcontra) {
        CombustibleContra model = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from CombustibleContra d where d.Contrasenha.idContrasenha = '" + combcontra.getContrasenha().getIdContrasenha() + "'";
        Transaction tx = sesion.beginTransaction();
        try {
            model = (CombustibleContra) sesion.createQuery(sql).uniqueResult();
            tx.commit();


        } catch (Exception e) {
            tx.rollback();
        }

        return model;
    }

    @Override
    public List<CombustibleContra> findAll() {
        List<CombustibleContra> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from CombustibleContra";
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
    public List<CombustibleContra> findByEx(Contrasenha contrasenha) {
        List<CombustibleContra> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from CombustibleContra where contrasenha="+contrasenha.getIdContrasenha();
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
    public List<CombustibleContra> findById(Integer codcontra) {
        List<CombustibleContra> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from CombustibleContra where contrasenha="+codcontra;
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
    public boolean create(CombustibleContra combcontra) {
        boolean flag ;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(combcontra);
            tx.commit();
            flag= true;

        } catch (Exception e) {
            flag=false;
            tx.rollback();
        }

        return flag;
    }

    @Override
    public boolean update(CombustibleContra contracomb) {
        boolean flag ;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.update(contracomb);
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
            CombustibleContra combcontra = (CombustibleContra) sesion.load(CombustibleContra.class,idDetalle);
            sesion.delete(combcontra);
            tx.commit();
            flag= true;

        } catch (Exception e) {
            flag=false;
            tx.rollback();
        }

        return flag;

    }
    
    
}
