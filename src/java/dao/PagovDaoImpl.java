/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DetallePagoVarios;
import model.PagoVarios;
import model.ProveedorVarios;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class PagovDaoImpl implements PagovDao {
    @Override
    public PagoVarios findByPagov(PagoVarios pagoVarios) {
        PagoVarios model = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from PagoVarios where idPagoVarios = '" + pagoVarios.getIdPagoVarios() + "'";
        try {
            sesion.beginTransaction();
            model = (PagoVarios) sesion.createQuery(sql).uniqueResult();
            sesion.beginTransaction().commit();
        } catch (Exception e) {
            sesion.beginTransaction().rollback();
        }

        return model;
    }
    @Override
    public List<PagoVarios> findAll() {
        List<PagoVarios> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM PagoVarios";
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
    public List<PagoVarios> findProveedor(ProveedorVarios proveedor) {
        List<PagoVarios> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM PagoVarios where proveedorVarios='"+proveedor.getIdProveedorVarios()+"'";
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
    public boolean create(PagoVarios pagoVarios, List<DetallePagoVarios> detpagovarios) {
        boolean flag;
        Integer maxCodventa = this.maxPago() + 1;
        DetallePagoVarios detallePagoVariosAux = new DetallePagoVarios();
        pagoVarios.setIdPagoVarios(maxCodventa);
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(pagoVarios);
            for (int i = 0; i < detpagovarios.size(); i++) {
                detallePagoVariosAux = detpagovarios.get(i);
                detallePagoVariosAux.setPagoVarios(pagoVarios);
                sesion.save(detallePagoVariosAux);
            }
            tx.commit();
            flag = true;

        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage() + " Mensaje Error: " + e.getMessage());
            flag = false;
            tx.rollback();
        }

        return flag;
    }

    @Override
    public boolean update(PagoVarios pagoVarios) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {    
            PagoVarios pagodb =(PagoVarios) sesion.load(PagoVarios.class, pagoVarios.getIdPagoVarios());
            pagodb.setDescripcion(pagoVarios.getDescripcion());
            pagodb.setEstado(pagoVarios.getEstado());
            sesion.update(pagodb);
            tx.commit();
            flag = true;

        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }

        return flag;
    }

    @Override
    public boolean delete(PagoVarios pagoVarios) {
        boolean flag;
        List<DetallePagoVarios> detalles;
        Integer codpago = pagoVarios.getIdPagoVarios();
        detalles = findByDetpagov(codpago);
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            for (DetallePagoVarios detalle : detalles) {
                sesion.delete(detalle);
            }
            sesion.delete(pagoVarios);
            tx.commit();
            flag = true;

        } catch (Exception e) {
            flag = false;
            System.out.println(e.getLocalizedMessage() + e.getMessage());
            tx.rollback();
        }

        return flag;

    }

    @Override
    public Integer maxPago() {
        Integer cantidad = 0;
        Query model;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from PagoVarios";
        Transaction tx = sesion.beginTransaction();
        try {
            model = sesion.createQuery(sql);
            List hola = model.list();
            cantidad = hola.size();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

        return cantidad;
    }
    @Override
    public List<DetallePagoVarios> findByDetpagov(Integer codpagov) {
        List<DetallePagoVarios> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from DetallePagoVarios x left join fetch x.pagoVarios p left join fetch x.varios r where x.pagoVarios.idPagoVarios=" + codpagov;
        Transaction tx = sesion.beginTransaction();
        try {            
            listado = sesion.createQuery(sql).list();
            tx.commit();

        } catch (Exception e) {
            System.out.println("Error findBypagov: " + e.getLocalizedMessage() + e.getMessage());
            tx.rollback();
        }

        return listado;
    }
}
    