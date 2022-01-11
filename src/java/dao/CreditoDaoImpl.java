/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Camion;
import model.Credito;
import model.Propietario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class CreditoDaoImpl implements CreditoDao {
    @Override
    public List<Credito> findAll() {
        List<Credito> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Credito";
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
    public List<Credito> findOne(Integer cod) {
        List<Credito> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Credito where idCredito='"+cod+"'";
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
    public List<Credito> findPropie(Propietario propie) {
        List<Credito> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Credito where estadoCobro='Pendiente' and camion.propietario='"+propie.getIdPropietario()+"'";
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
    public List<Credito> findbus(Propietario propie, Date fechaini, Date fechafin) {
        List<Credito> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Credito where camion.propietario='"+propie.getIdPropietario()+"' and fecha BETWEEN '"+fechaini+"' and '"+fechafin+"' ORDER BY fecha";
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
    public List<Credito> findfecha(Date fechaini, Date fechafin) {
        List<Credito> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Credito c where c.fecha BETWEEN '"+fechaini+"' and '"+fechafin+"' ORDER BY c.fecha";
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
    public List<Credito> findOneUp(String nroliq) {
        List<Credito> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Credito where descripcion LIKE '%Saldo Negativo de la liquidación Nro.:%' and descripcion LIKE '%"+nroliq+"'";
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
    public List<Credito> findCamion(Camion camion) {
        List<Credito> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Credito c where estadoCobro='Pendiente' and c.camion='"+camion.getChapaCamion()+"'";
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
    public boolean create(Credito credito) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(credito);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(Credito credito) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Credito creditodb =(Credito) sesion.load(Credito.class, credito.getIdCredito());
            creditodb.setFecha(credito.getFecha());
            creditodb.setDescripcion(credito.getDescripcion());
            creditodb.setMonto(credito.getMonto());
            creditodb.setFechaVencimiento(credito.getFechaVencimiento());
            creditodb.setCamion(credito.getCamion());
            creditodb.setEstadoCobro(credito.getEstadoCobro());
            sesion.update(creditodb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
}
    @Override
    public boolean delete(Integer idCredito) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Credito credito =(Credito) sesion.load(Credito.class, idCredito);
            sesion.delete(credito);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

}
