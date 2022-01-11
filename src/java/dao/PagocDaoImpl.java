/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DetallePagoComb;
import model.PagoCombustible;
import model.Proveedor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class PagocDaoImpl implements PagocDao {
    @Override
    public PagoCombustible findByPagoc(PagoCombustible pagoCombustible) {
        PagoCombustible model = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from PagoCombustible where idPagoCombustible = '" + pagoCombustible.getIdPagoCombustible() + "'";
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
    public List<PagoCombustible> findAll() {
        List<PagoCombustible> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM PagoCombustible";
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
    public List<PagoCombustible> findpro(Proveedor proveedor) {
        List<PagoCombustible> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM PagoCombustible where proveedor='"+proveedor.getIdProveedor()+"'";
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
    public boolean create(PagoCombustible pagoCombustible, List<DetallePagoComb> detpagoc) {
        boolean flag;
        Integer maxCodpago = this.maxPago() + 1;
        DetallePagoComb detallePagoCombAux = new DetallePagoComb();
        pagoCombustible.setIdPagoCombustible(maxCodpago);
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(pagoCombustible);
            for (int i = 0; i < detpagoc.size(); i++) {
                detallePagoCombAux = detpagoc.get(i);
                detallePagoCombAux.setPagoCombustible(pagoCombustible);
                sesion.save(detallePagoCombAux);
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
    public boolean update(PagoCombustible pagoCombustible) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {    
            PagoCombustible pagodb =(PagoCombustible) sesion.load(PagoCombustible.class, pagoCombustible.getIdPagoCombustible());
            pagodb.setDescripcion(pagoCombustible.getDescripcion());
            pagodb.setEstado(pagoCombustible.getEstado());
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
    public boolean delete(PagoCombustible pagoCombustible) {
        boolean flag;
        List<DetallePagoComb> detalles;
        Integer codpago = pagoCombustible.getIdPagoCombustible();
        detalles = findByDetpagoc(codpago);
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            for (DetallePagoComb detalle : detalles) {
                sesion.delete(detalle);
            }
            sesion.delete(pagoCombustible);
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
        String sql = "from PagoCombustible";
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
    public List<DetallePagoComb> findByDetpagoc(Integer codpagov) {
        List<DetallePagoComb> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from DetallePagoComb x left join fetch x.pagoCombustible p left join fetch x.combustible r where x.pagoCombustible.idPagoCombustible=" + codpagov;
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
