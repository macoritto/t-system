/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.Date;
import model.Camion;
import model.Combustible;
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
public class CombDaoImpl implements CombDao {
   @Override
    public List<Combustible> findAll() {
        List<Combustible> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Combustible";
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
    public List<Combustible> findOne(Integer cod) {
        List<Combustible> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Combustible where idCombustible='"+cod+"'";
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
    public List<Combustible> findCamion(Camion camion) {
        List<Combustible> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Combustible c where estadoCobro='Pendiente' and c.valeCombustible.camion='"+camion.getChapaCamion()+"'";
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
    public List<Combustible> findCamionT(Camion camion, Date fechaini, Date fechafin) {
        List<Combustible> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Combustible c where c.valeCombustible.camion='"+camion.getChapaCamion()+"' and c.fecha BETWEEN '"+fechaini+"' and '"+fechafin+"' ORDER BY c.fecha";
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
    public List<Combustible> findCamionP(Proveedor proveedor, Date fechaini, Date fechafin) {
        List<Combustible> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Combustible c where c.valeCombustible.proveedor='"+proveedor.getIdProveedor()+"' and c.fecha BETWEEN '"+fechaini+"' and '"+fechafin+"' ORDER BY c.fecha";
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
    public List<Combustible> findVale(OrdenDeCarga orden) {
        List<Combustible> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Combustible c where c.valeCombustible.ordenDeCarga='"+orden.getIdOrdenDeCarga()+"'";
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
    public List<Combustible> findVale2(OrdenDeCarga orden, Camion camion) {
        List<Combustible> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Combustible c where estadoCobro='Pendiente' and c.valeCombustible.ordenDeCarga='"+orden.getIdOrdenDeCarga()+"' and c.valeCombustible.camion='"+camion.getChapaCamion()+"'";
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
    public List<Combustible> findpen(Proveedor proveedor) {
        List<Combustible> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Combustible where estadoPago='Pendiente' and valeCombustible.proveedor='" + proveedor.getIdProveedor() + "'";
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
    public List<Combustible> findpro(Proveedor proveedor) {
        List<Combustible> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Combustible where valeCombustible.proveedor='" + proveedor.getIdProveedor() + "'";
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
    public boolean create(Combustible combustible) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(combustible);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(Combustible combustible) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Combustible combdb =(Combustible) sesion.load(Combustible.class, combustible.getIdCombustible());
            combdb.setNBoleta(combustible.getNBoleta());
            combdb.setFecha(combustible.getFecha());
            combdb.setDescripcion(combustible.getDescripcion());
            combdb.setPrecio(combustible.getPrecio());
            combdb.setLitros(combustible.getLitros());
            combdb.setMontoTotal(combustible.getMontoTotal());
            combdb.setExtras(combustible.getExtras());
            combdb.setValeCombustible(combustible.getValeCombustible());
            combdb.setEstadoCobro(combustible.getEstadoCobro());
            combdb.setEstadoPago(combustible.getEstadoPago());
            sesion.update(combdb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
}
    @Override
    public boolean delete(Integer idCombustible) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Combustible combustible =(Combustible) sesion.load(Combustible.class, idCombustible);
            sesion.delete(combustible);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    } 
}
