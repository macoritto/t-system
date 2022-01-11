/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.Date;
import model.Camion;
import model.Cliente;
import model.OrdenDeCarga;
import model.Propietario;
import model.Viaje;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class ViajeDaoImpl implements ViajeDao{
    @Override
    public List<Viaje> findAll() {
        List<Viaje> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Viaje";
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
    public List<Viaje> findCamion(Camion camion) {
        List<Viaje> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Viaje v where v.ordenDeCarga.camion='"+camion.getChapaCamion()+"'";
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
        public List<Viaje> fletesumi(Integer orden) {
        List<Viaje> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Viaje v where v.ordenDeCarga.idOrdenDeCarga='"+orden+"' and v.estadoCobro='Pendiente'";
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
    public List<Viaje> FleteBus(Camion camion, Date fechaini, Date fechafin) {
        List<Viaje> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Viaje v where v.ordenDeCarga.camion='"+camion.getChapaCamion()+"' and v.fechaDestino BETWEEN '"+fechaini+"' and '"+fechafin+"' ORDER BY v.fechaDestino";
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
    public List<Viaje> FleteBusCli(Cliente cliente, Date fechaini, Date fechafin) {
        List<Viaje> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Viaje v where v.ordenDeCarga.cliente='"+cliente.getIdCliente()+"' and v.fechaDestino BETWEEN '"+fechaini+"' and '"+fechafin+"' ORDER BY v.fechaDestino";
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
    public List<Viaje> FleteBusPro(Propietario propietario, Date fechaini, Date fechafin) {
        List<Viaje> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Viaje v where v.ordenDeCarga.camion.propietario='"+propietario.getIdPropietario()+"' and v.fechaDestino BETWEEN '"+fechaini+"' and '"+fechafin+"' ORDER BY v.fechaDestino";
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
    public List<Viaje> findPen(Camion camion) {
        List<Viaje> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Viaje v where estadoPago='Pendiente' and v.ordenDeCarga.camion='" + camion.getChapaCamion() +"'";
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
    public List<Viaje> findEx(Camion camion){
        List<Viaje> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Viaje v where estadoPago='Pendiente' and v.ordenDeCarga.camion='" + camion.getChapaCamion() +"' order by idViaje";
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
    public List<Viaje> findOne(Integer cod) {
        List<Viaje> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Viaje v where idViaje='" + cod +"'";
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
    public Viaje Encontraruno(Integer cod) {
        Viaje listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Viaje v where idViaje='" + cod +"'";
        System.out.println(sql);
        Transaction tx = sesion.beginTransaction();
        try {
            listado = (Viaje) sesion.createQuery(sql);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }
    @Override
    public boolean create(Viaje viaje) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(viaje);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(Viaje viaje) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Viaje viajedb =(Viaje) sesion.load(Viaje.class, viaje.getIdViaje());
            viajedb.setIdViaje(viaje.getIdViaje());
            viajedb.setRemisionOrigen(viaje.getRemisionOrigen());
            viajedb.setRemisionDestino(viaje.getRemisionDestino());
            viajedb.setPesoOrigen(viaje.getPesoOrigen());
            viajedb.setPesoDestino(viaje.getPesoDestino());
            viajedb.setFechaOrigen(viaje.getFechaOrigen());
            viajedb.setFechaDestino(viaje.getFechaDestino());
            viajedb.setPrecioCobro(viaje.getPrecioCobro());
            viajedb.setPrecioPago(viaje.getPrecioPago());
            viajedb.setDescripcion(viaje.getDescripcion());
            viajedb.setOrdenDeCarga(viaje.getOrdenDeCarga());
            viajedb.setTolerancia(viaje.getTolerancia());
            viajedb.setChofer(viaje.getChofer());
            viajedb.setEstadoPago(viaje.getEstadoPago());
            viajedb.setEstadoCobro(viaje.getEstadoCobro());
            viajedb.setMontoBruto(viaje.getMontoBruto());
            viajedb.setMontoCobrar(viaje.getMontoCobrar());
            viajedb.setMontoPagar(viaje.getMontoPagar());
            viajedb.setMontofaltante(viaje.getMontofaltante());
            viajedb.setMontoseguro(viaje.getMontoseguro());
            viajedb.setEstadocontra(viaje.getEstadocontra());
            sesion.update(viajedb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
}
    @Override
    public boolean delete(Integer idViaje) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Viaje viaje =(Viaje) sesion.load(Viaje.class, idViaje);
            sesion.delete(viaje);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }
    @Override
    public List<Viaje> findpen(Cliente cliente) {
        List<Viaje> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Viaje where estadoCobro='Pendiente' and ordenDeCarga.cliente='" + cliente.getIdCliente() + "'";
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
    public List<Viaje> findContra(Camion camion) {
        List<Viaje> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Viaje v where estadocontra='Pendiente' and v.ordenDeCarga.camion='" + camion.getChapaCamion() +"'";
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
