/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Cliente;
import model.DetPagoAde;
import model.DetPagoFlete;
import model.DetPagoSumi;
import model.PagoFlete;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class PagoviDaoImpl implements PagoviDao {
    @Override
    public PagoFlete findByPagovi(PagoFlete pagoFlete) {
        PagoFlete model = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from PagoFlete where idPagoFlete = '" + pagoFlete.getIdPagoFlete() + "'";
        try {
            sesion.beginTransaction();
            model = (PagoFlete) sesion.createQuery(sql).uniqueResult();
            sesion.beginTransaction().commit();
        } catch (Exception e) {
            sesion.beginTransaction().rollback();
        }

        return model;
    }
    @Override
    public List<PagoFlete> findAll() {
        List<PagoFlete> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM PagoFlete";
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
    public List<PagoFlete> findCliente(Cliente cliente) {
        List<PagoFlete> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM PagoFlete where cliente='"+cliente.getIdCliente()+"'";
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
    public boolean create(PagoFlete pagoFlete, List<DetPagoFlete> detpagovi) {
        boolean flag;
        Integer maxCodpago = this.maxPago() + 1;
        DetPagoFlete detallePagoFleteAux = new DetPagoFlete();
        pagoFlete.setIdPagoFlete(maxCodpago);
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(pagoFlete);
            for (int i = 0; i < detpagovi.size(); i++) {
                detallePagoFleteAux = detpagovi.get(i);
                detallePagoFleteAux.setPagoFlete(pagoFlete);
                sesion.save(detallePagoFleteAux);
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
    public boolean update(PagoFlete pagoFlete) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {    
            PagoFlete pagodb =(PagoFlete) sesion.load(PagoFlete.class, pagoFlete.getIdPagoFlete());
            pagodb.setNroLiq(pagoFlete.getNroLiq());   
            pagodb.setDescripcion(pagoFlete.getDescripcion());   
            pagodb.setEstado(pagoFlete.getEstado());
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
    public boolean delete(PagoFlete pagoFlete) {
        boolean flag;
        List<DetPagoFlete> detalles;
        List<DetPagoSumi> detalles1;
        List<DetPagoAde> detalles2;
        Integer codpago = pagoFlete.getIdPagoFlete();
        detalles = findByDetpagovi(codpago);
        detalles1 = findByDetpagosumi(codpago);
        detalles2 = findByDetpagoade(codpago);
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            for (DetPagoFlete detalle : detalles) {
                sesion.delete(detalle);
            }
            for (DetPagoSumi detalle1 : detalles1) {
                sesion.delete(detalle1);
            }
            for (DetPagoAde detalle2 : detalles2) {
                sesion.delete(detalle2);
            }
            sesion.delete(pagoFlete);
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
        String sql = "from PagoFlete";
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
    public List<DetPagoFlete> findByDetpagovi(Integer codpagovi) {
        List<DetPagoFlete> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from DetPagoFlete x left join fetch x.pagoFlete p left join fetch x.viaje r where x.pagoFlete.idPagoFlete=" + codpagovi;
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
        @Override
    public List<DetPagoSumi> findByDetpagosumi(Integer codpagovi) {
        List<DetPagoSumi> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from DetPagoSumi x left join fetch x.pagoFlete p left join fetch x.sumiCliente r where x.pagoFlete.idPagoFlete=" + codpagovi;
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
        @Override
    public List<DetPagoAde> findByDetpagoade(Integer codpagovi) {
        List<DetPagoAde> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from DetPagoAde x left join fetch x.pagoFlete p left join fetch x.adeCliente r where x.pagoFlete.idPagoFlete=" + codpagovi;
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
