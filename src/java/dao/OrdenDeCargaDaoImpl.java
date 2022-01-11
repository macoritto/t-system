/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Camion;
import model.Cliente;
import model.OrdenDeCarga;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class OrdenDeCargaDaoImpl implements OrdenDeCargaDao {
    @Override
    public List<OrdenDeCarga> findAll() {
        List<OrdenDeCarga> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM OrdenDeCarga where idOrdenDeCarga!='4'";
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
    public List<OrdenDeCarga> findCliente(Cliente cliente) {
        List<OrdenDeCarga> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM OrdenDeCarga where estadoOrden='Pendiente' and cliente='"+cliente.getIdCliente()+"'";
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
    public List<OrdenDeCarga> findOne() {
        List<OrdenDeCarga> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM OrdenDeCarga where idOrdenDeCarga='4'";
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
    public List<OrdenDeCarga> findUno(Integer cod) {
        List<OrdenDeCarga> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM OrdenDeCarga where idOrdenDeCarga='"+cod+"'";
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
    public List<OrdenDeCarga> encontrar(Integer cod) {
        List<OrdenDeCarga> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM OrdenDeCarga where idOrdenDeCarga='"+cod+"'";
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
    public List<OrdenDeCarga> encontrarLiq(Camion camion) {
        List<OrdenDeCarga> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM OrdenDeCarga where estadoliq='Pendiente' and camion='"+camion.getChapaCamion()+"'";
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
    public boolean create(OrdenDeCarga ordenDeCarga) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(ordenDeCarga);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(OrdenDeCarga ordenDeCarga) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            OrdenDeCarga ordendb =(OrdenDeCarga) sesion.load(OrdenDeCarga.class, ordenDeCarga.getIdOrdenDeCarga());
            ordendb.setCamion(ordenDeCarga.getCamion());
            ordendb.setCliente(ordenDeCarga.getCliente());
            ordendb.setProducto(ordenDeCarga.getProducto());
            ordendb.setUnidadDestino(ordenDeCarga.getUnidadDestino());
            ordendb.setUnidadOrigen(ordenDeCarga.getUnidadOrigen());
            ordendb.setFechaEmision(ordenDeCarga.getFechaEmision());
            ordendb.setEstadoOrden(ordenDeCarga.getEstadoOrden());
            ordendb.setEstadoliq(ordenDeCarga.getEstadoliq());
            sesion.update(ordendb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
}
    @Override
    public boolean delete(Integer idOrdenDeCarga) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            OrdenDeCarga ordenDeCarga =(OrdenDeCarga) sesion.load(OrdenDeCarga.class, idOrdenDeCarga);
            sesion.delete(ordenDeCarga);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }
    @Override
    public List<OrdenDeCarga> findPendiente() {
        List<OrdenDeCarga> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from OrdenDeCarga where estadoOrden='Pendiente'";
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
    public List<OrdenDeCarga> findAnulado() {
        List<OrdenDeCarga> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from OrdenDeCarga where estadoOrden='Anulado'";
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
    public List<OrdenDeCarga> findAceptado() {
        List<OrdenDeCarga> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from OrdenDeCarga where estadoOrden='Aceptado'";
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
    public List<OrdenDeCarga> findOrden(Camion camion) {
        List<OrdenDeCarga> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from OrdenDeCarga where estadoOrden='Pendiente' and camion='"+camion.getChapaCamion()+"'";
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
    public List<OrdenDeCarga> findPen(Camion camion) {
        List<OrdenDeCarga> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from OrdenDeCarga where estadoOrden!='Anulado' and camion='"+camion.getChapaCamion()+"'";
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
    public List<OrdenDeCarga> findLiq(Camion camion) {
        List<OrdenDeCarga> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from OrdenDeCarga where estadoliq='Pendiente' and camion='"+camion.getChapaCamion()+"'";
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
}
