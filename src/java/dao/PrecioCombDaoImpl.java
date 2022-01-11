/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.PrecioComb;
import model.Proveedor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class PrecioCombDaoImpl implements PrecioCombDao {
    @Override
    public List<PrecioComb> findPrecios(Proveedor proveedor) {
        List<PrecioComb> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from PrecioComb where estado='Alta' and proveedor='" + proveedor.getIdProveedor() + "'";
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
    public List<PrecioComb> findAll() {
        List<PrecioComb> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from PrecioComb";
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
    public boolean create(PrecioComb precioComb) {
     boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(precioComb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;   
    }

    @Override
    public boolean update(PrecioComb precioComb) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            PrecioComb preciodb =(PrecioComb) sesion.load(PrecioComb.class, precioComb.getIdPrecioComb());
            preciodb.setMonto(precioComb.getMonto());
            preciodb.setProveedor(precioComb.getProveedor());
            preciodb.setTipoCombustible(precioComb.getTipoCombustible());
            preciodb.setEstado(precioComb.getEstado());
            sesion.update(preciodb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean delete(Integer idPrecioComb) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            PrecioComb precioComb =(PrecioComb) sesion.load(PrecioComb.class, idPrecioComb);
            sesion.delete(precioComb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }
}
