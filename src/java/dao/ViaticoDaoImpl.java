/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Camion;
import java.util.Date;
import model.OrdenDeCarga;
import model.Viatico;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class ViaticoDaoImpl implements ViaticoDao{
    @Override
    public boolean create(Viatico viatico) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(viatico);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(Viatico viatico) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Viatico viaticodb =(Viatico) sesion.load(Viatico.class, viatico.getIdViatico());
            viaticodb.setMonto(viatico.getMonto());
            viaticodb.setFecha(viatico.getFecha());
            viaticodb.setDescripcion(viatico.getDescripcion());
            viaticodb.setCamion(viatico.getCamion());
            viaticodb.setChofer(viatico.getChofer());
            viaticodb.setUsuario(viatico.getUsuario());
            viaticodb.setEstado(viatico.getEstado());
            viaticodb.setOrdenDeCarga(viatico.getOrdenDeCarga());
            sesion.update(viaticodb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean delete(Integer idViatico) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Viatico viatico =(Viatico) sesion.load(Viatico.class, idViatico);
            sesion.delete(viatico);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public List<Viatico> findAll() {
        List<Viatico> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Viatico where idViatico!='14'";
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
    public List<Viatico> findbus(Camion camion, Date fechaini, Date fechafin) {
        List<Viatico> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Viatico v where v.camion='"+camion.getChapaCamion()+"' and v.fecha BETWEEN '"+fechaini+"' and '"+fechafin+"' ORDER BY fecha";
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
    public List<Viatico> findfecha(Date fechaini, Date fechafin) {
        List<Viatico> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Viatico v where v.fecha BETWEEN '"+fechaini+"' and '"+fechafin+"' ORDER BY fecha";
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
    public List<Viatico> findViaticos(Camion camion) {
        List<Viatico> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from Viatico where tipoViatico='2' and estado='Pendiente' and camion='" + camion.getChapaCamion() + "'";
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
    public List<Viatico> findCamion(Camion camion) {
        List<Viatico> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from Viatico where estado='Pendiente' and camion='" + camion.getChapaCamion() + "'";
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
    public List<Viatico> findOne() {
        List<Viatico> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Viatico where idViatico='14'";
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
    public List<Viatico> findContra(OrdenDeCarga orden) {
        List<Viatico> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Viatico where tipoViatico.descripcion!='Surtidor' and ordenDeCarga.idOrdenDeCarga='"+orden.getIdOrdenDeCarga()+"'";
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
    public List<Viatico> findVia(OrdenDeCarga orden, Camion camion) {
        List<Viatico> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Viatico where estado='Pendiente' and ordenDeCarga.idOrdenDeCarga='"+orden.getIdOrdenDeCarga()+"' and camion='"+camion.getChapaCamion()+"'";
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
    public List<Viatico> findVia2(OrdenDeCarga orden, Camion camion) {
        List<Viatico> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Viatico where estado='Pendiente' and ordenDeCarga.idOrdenDeCarga='"+orden.getIdOrdenDeCarga()+"' and camion='"+camion.getChapaCamion()+"'";
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
    public List<Viatico> find(Integer cod) {
        List<Viatico> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Viatico where idViatico='"+cod+"'";
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
    public List<Viatico> findPendiente() {
        List<Viatico> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from Viatico where estado='Pendiente'";
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
    public List<Viatico> findAnulado() {
        List<Viatico> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from Viatico where estado='Anulado'";
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
    public List<Viatico> findAceptado() {
        List<Viatico> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from Viatico where estado='Aceptado'";
        Transaction tx = sesion.beginTransaction();
        try {
            listado = sesion.createQuery(sql).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
        
    }
    
    
}
