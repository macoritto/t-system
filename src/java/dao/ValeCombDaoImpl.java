/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Camion;
import model.OrdenDeCarga;
import model.Proveedor;
import model.ValeCombustible;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class ValeCombDaoImpl implements ValeCombDao{
    @Override
    public boolean create(ValeCombustible valeCombustible) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(valeCombustible);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(ValeCombustible valeCombustible) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            ValeCombustible valedb =(ValeCombustible) sesion.load(ValeCombustible.class, valeCombustible.getIdValeCombustible());
            valedb.setCamion(valeCombustible.getCamion());
            valedb.setUsuario(valeCombustible.getUsuario());
            valedb.setLitros(valeCombustible.getLitros());
            valedb.setPrecioComb(valeCombustible.getPrecioComb());
            valedb.setMontoTotal(valeCombustible.getMontoTotal());
            valedb.setDescripcion(valeCombustible.getDescripcion());
            valedb.setProveedor(valeCombustible.getProveedor());
            valedb.setTipoCombustible(valeCombustible.getTipoCombustible());
            valedb.setExtras(valeCombustible.getExtras());
            valedb.setOrdenDeCarga(valeCombustible.getOrdenDeCarga());
            valedb.setViatico(valeCombustible.getViatico());
            valedb.setMontoComb(valeCombustible.getMontoComb());
            valedb.setEstado(valeCombustible.getEstado());
            valedb.setFecha(valeCombustible.getFecha());
            sesion.update(valedb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean delete(Integer idValeCombustible) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            ValeCombustible valeCombustible =(ValeCombustible) sesion.load(ValeCombustible.class, idValeCombustible);
            sesion.delete(valeCombustible);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public List<ValeCombustible> findAll() {
        List<ValeCombustible> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM ValeCombustible";
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
    public List<ValeCombustible> findVales(Camion camion) {
        List<ValeCombustible> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM ValeCombustible where estado='Pendiente' and camion='"+camion.getChapaCamion()+"'";
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
    public List<ValeCombustible> findPendiente() {
        List<ValeCombustible> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM ValeCombustible where estado='Pendiente'";
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
    public List<ValeCombustible> findPen(Proveedor proveedor) {
        List<ValeCombustible> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM ValeCombustible where estado='Pendiente' and proveedor.idProveedor='"+proveedor.getIdProveedor()+"'";
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
    public List<ValeCombustible> findAnulado() {
        List<ValeCombustible> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM ValeCombustible where estado='Anulado'";
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
    public List<ValeCombustible> findAceptado() {
        List<ValeCombustible> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM ValeCombustible where estado='Aceptado'";
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
    public List<ValeCombustible> findReporte(ValeCombustible valeCombustible) {
        List<ValeCombustible> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM ValeCombustible where idValeCombustible='"+valeCombustible.getIdValeCombustible()+"'";
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
    public List<ValeCombustible> findContra(OrdenDeCarga orden) {
        List<ValeCombustible> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM ValeCombustible where ordenDeCarga.idOrdenDeCarga='"+orden.getIdOrdenDeCarga()+"'";
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
