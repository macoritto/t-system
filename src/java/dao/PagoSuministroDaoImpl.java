/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DetallePagoComb;
import model.PagoAdeDet;
import model.PagoCombustible;
import model.PagoSumiDet;
import model.PagoSuministro;
import model.ProveeCliente;
import model.Proveedor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class PagoSuministroDaoImpl implements PagoSuministroDao {
    @Override
    public PagoCombustible findByPagoc(PagoSuministro pagoSuministro) {
        PagoCombustible model = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from PagoSuministro where idPagosumi = '" + pagoSuministro.getIdPagosumi()+ "'";
        try {
            sesion.beginTransaction();
            model = (PagoCombustible) sesion.createQuery(sql).uniqueResult();
            sesion.beginTransaction().commit();
        } catch (Exception e) {
            sesion.beginTransaction().rollback();
        }

        return model;
    }
    @Override
    public List<PagoSuministro> findAll() {
        List<PagoSuministro> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM PagoSuministro";
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
    public List<PagoSuministro> findpro(ProveeCliente proveedor) {
        List<PagoSuministro> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM PagoSuministro where proveeCliente='"+proveedor.getIdProveecli()+"'";
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
    public boolean create(PagoSuministro pagoSuministro, List<PagoSumiDet> detpagos) {
        boolean flag;
        Integer maxCodpago = this.maxPago() + 1;
        PagoSumiDet pagoDet = new PagoSumiDet();
        PagoAdeDet pagoAde = new PagoAdeDet();
        pagoSuministro.setIdPagosumi(maxCodpago);
        System.out.print("Este es el id del pago");
        System.out.print(pagoSuministro.getIdPagosumi());
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(pagoSuministro);
            for (int i = 0; i < detpagos.size(); i++) {
                pagoDet = detpagos.get(i);
                pagoDet.setPagoSuministro(pagoSuministro);
                sesion.save(pagoDet);
            }
//            sesion.clear();
//            for (int i = 0; i < detade.size(); i++) {
//                pagoAde = detade.get(i);
//                pagoAde.setPagoSuministro(pagoSuministro);
//                sesion.save(pagoAde);
//            }
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
    public boolean update(PagoSuministro pagoSuministro) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {    
            PagoSuministro pagodb =(PagoSuministro) sesion.load(PagoSuministro.class, pagoSuministro.getIdPagosumi());
            pagodb.setDescrip(pagoSuministro.getDescrip());
            pagodb.setEstado(pagoSuministro.getEstado());
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
    public boolean delete(PagoSuministro pagoSuministro) {
        boolean flag;
        List<PagoSumiDet> detalles;
        List<PagoAdeDet> detalles1;
        Integer codpago = pagoSuministro.getIdPagosumi();
        detalles = findByDetpagoc(codpago);
        detalles1= findByDetpagoa(codpago);
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            for (PagoSumiDet detalle : detalles) {
                sesion.delete(detalle);
            }
            for (PagoAdeDet detalle1 : detalles1) {
                sesion.delete(detalle1);
            }
            sesion.delete(pagoSuministro);
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
        String sql = "from PagoSuministro";
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
    public List<PagoSumiDet> findByDetpagoc(Integer codpagov) {
        List<PagoSumiDet> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from PagoSumiDet x left join fetch x.pagoSuministro p left join fetch x.suministro r where x.pagoSuministro.idPagosumi=" + codpagov;
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
    public List<PagoAdeDet> findByDetpagoa(Integer codpagov) {
        List<PagoAdeDet> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from PagoAdeDet x left join fetch x.pagoSuministro p left join fetch x.adeSuministro r where x.pagoSuministro.idPagosumi=" + codpagov;
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
