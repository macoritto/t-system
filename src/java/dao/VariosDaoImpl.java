/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Camion;
import model.ProveedorVarios;
import model.Varios;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class VariosDaoImpl implements VariosDao {
    @Override
    public boolean create(Varios varios) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(varios);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(Varios varios) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Varios variosdb =(Varios) sesion.load(Varios.class, varios.getIdVarios());
            variosdb.setFecha(varios.getFecha());
            variosdb.setMonto(varios.getMonto());
            variosdb.setDescripcion(varios.getDescripcion());
            variosdb.setValeVarios(varios.getValeVarios());
            variosdb.setEstadoPago(varios.getEstadoPago());
            variosdb.setEstadoCobro(varios.getEstadoCobro());
            variosdb.setUsuario(varios.getUsuario());
            sesion.update(variosdb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean delete(Integer idVarios) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Varios varios =(Varios) sesion.load(Varios.class, idVarios);
            sesion.delete(varios);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public List<Varios> findAll() {
        List<Varios> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Varios";
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
    public List<Varios> findOne(Integer cod) {
        List<Varios> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Varios where idVarios='"+cod+"'";
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
    public List<Varios> findCamion(Camion camion) {
        List<Varios> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Varios v where estadoCobro='Pendiente' and v.valeVarios.camion='"+camion.getChapaCamion()+"'";
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
    public List<Varios> findbus(Camion camion, Date fechaini, Date fechafin) {
        List<Varios> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Varios v where v.valeVarios.camion='"+camion.getChapaCamion()+"' and v.fecha BETWEEN '"+fechaini+"' and '"+fechafin+"' ORDER BY v.fecha";
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
    public List<Varios> findfecha(ProveedorVarios proveedor, Date fechaini, Date fechafin) {
        List<Varios> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Varios v where v.valeVarios.proveedorVarios='"+proveedor.getIdProveedorVarios()+"' and v.fecha BETWEEN '"+fechaini+"' and '"+fechafin+"' ORDER BY v.fecha";
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
    public List<Varios> findpen(ProveedorVarios proveedor) {
        List<Varios> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Varios where estadoPago='Pendiente' and valeVarios.proveedorVarios='" + proveedor.getIdProveedorVarios() + "'";
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
    public List<Varios> findpro(ProveedorVarios proveedor) {
        List<Varios> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Varios where valeVarios.proveedorVarios='" + proveedor.getIdProveedorVarios() + "'";
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
