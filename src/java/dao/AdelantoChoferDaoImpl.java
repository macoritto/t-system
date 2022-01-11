/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.AdelantoChofer;
import model.Camion;
import model.Chofer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class AdelantoChoferDaoImpl implements AdelantoChoferDao {
    @Override
    public List<AdelantoChofer> findAll() {
        List<AdelantoChofer> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM AdelantoChofer";
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
    public List<AdelantoChofer> findOne(Integer cod) {
        List<AdelantoChofer> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM AdelantoChofer where idAdelantoChofer='"+cod+"'";
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
    public List<AdelantoChofer> findCamion(Camion camion) {
        List<AdelantoChofer> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM AdelantoChofer where estadoCobro='Pendiente' and camion='"+camion.getChapaCamion()+"'";
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
    public List<AdelantoChofer> findbus(Chofer chofer, Date fechaini, Date fechafin) {
        List<AdelantoChofer> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM AdelantoChofer where camion.chofer='"+chofer.getIdChofer()+"'and fecha BETWEEN '"+fechaini+"' and '"+fechafin+"' ORDER BY fecha";
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
    public List<AdelantoChofer> findbusfecha(Date fechaini, Date fechafin) {
        List<AdelantoChofer> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM AdelantoChofer a where a.fecha BETWEEN '"+fechaini+"' and '"+fechafin+"' ORDER BY a.fecha";
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
    public boolean create(AdelantoChofer adelantoChofer) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(adelantoChofer);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(AdelantoChofer adelantoChofer) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            AdelantoChofer adelantoChoferdb =(AdelantoChofer) sesion.load(AdelantoChofer.class, adelantoChofer.getIdAdelantoChofer());
            adelantoChoferdb.setFecha(adelantoChofer.getFecha());
            adelantoChoferdb.setDescripcion(adelantoChofer.getDescripcion());
            adelantoChoferdb.setMonto(adelantoChofer.getMonto());
            adelantoChoferdb.setChofer(adelantoChofer.getChofer());
            adelantoChoferdb.setCamion(adelantoChofer.getCamion());
            adelantoChoferdb.setEstadoCobro(adelantoChofer.getEstadoCobro());
            sesion.update(adelantoChoferdb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
}
    @Override
    public boolean delete(Integer idAdelantoChofer) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            AdelantoChofer adelantoChofer =(AdelantoChofer) sesion.load(AdelantoChofer.class, idAdelantoChofer);
            sesion.delete(adelantoChofer);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    
}
