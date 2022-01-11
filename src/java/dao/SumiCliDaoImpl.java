/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.AdeCliente;
import model.AdeSuministro;
import model.Cliente;
import model.OrdenDeCarga;
import model.ProveeCliente;
import model.SumiCliente;
import model.UnidadProvee;
import model.TipoProvee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class SumiCliDaoImpl implements SumiCliDao {
    @Override
    public List<SumiCliente> findAll() {
        List<SumiCliente> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM SumiCliente";
        System.out.println(sql);
        Transaction tx = sesion.beginTransaction();
        try {
            listado = sesion.createQuery(sql).list();
            //System.out.println(sesion.createQuery(sql).list());
            System.out.println("HOLA");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }
    @Override
    public List<SumiCliente> findOne(Integer orden) {
        List<SumiCliente> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM SumiCliente where ordenDeCarga.idOrdenDeCarga='"+orden+"' and estadoCobro='Pendiente'";
        System.out.println(sql);
        Transaction tx = sesion.beginTransaction();
        try {
            listado = sesion.createQuery(sql).list();
            //System.out.println(sesion.createQuery(sql).list());
            System.out.println("HOLA");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }
        @Override
    public List<SumiCliente> findOneChapa(Integer orden, String chapa) {
        List<SumiCliente> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM SumiCliente where ordenDeCarga.idOrdenDeCarga='"+orden+"' and estadoCobro='Pendiente' and camion.chapaCamion='"+chapa+"'";
        System.out.println(sql);
        Transaction tx = sesion.beginTransaction();
        try {
            listado = sesion.createQuery(sql).list();
            //System.out.println(sesion.createQuery(sql).list());
            System.out.println("HOLA");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }
    @Override
    public List<SumiCliente> proveedor(Cliente cliente) {
        List<SumiCliente> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM SumiCliente where cliente='"+cliente.getIdCliente()+"' and estadoCobro='Pendiente'";
        System.out.println(sql);
        Transaction tx = sesion.beginTransaction();
        try {
            listado = sesion.createQuery(sql).list();
            //System.out.println(sesion.createQuery(sql).list());
            System.out.println("HOLA");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }

    @Override
    public boolean create(SumiCliente sumiCliente) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(sumiCliente);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(SumiCliente sumiCliente) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            SumiCliente proveedordb =(SumiCliente) sesion.load(SumiCliente.class, sumiCliente.getIdSumiCli());
            proveedordb.setFecha(sumiCliente.getFecha());
            proveedordb.setDescrip(sumiCliente.getDescrip());
            proveedordb.setEstadoCobro(sumiCliente.getEstadoCobro());
            proveedordb.setEstadoPago(sumiCliente.getEstadoPago());
            proveedordb.setMonto(sumiCliente.getMonto());
            proveedordb.setCliente(sumiCliente.getCliente());
            sesion.update(proveedordb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
}
    @Override
    public boolean delete(Integer idAde) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            SumiCliente unidad =(SumiCliente) sesion.load(SumiCliente.class, idAde);
            sesion.delete(unidad);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }
    @Override
    public List<SumiCliente> encontrar(Integer orden) {
        List<SumiCliente> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM SumiCliente where idSumiCli='"+orden+"'";
        System.out.println(sql);
        Transaction tx = sesion.beginTransaction();
        try {
            listado = sesion.createQuery(sql).list();
            //System.out.println(sesion.createQuery(sql).list());
            System.out.println("HOLA");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return listado;
    }
}
