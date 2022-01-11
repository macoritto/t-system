/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.OrdenDeCarga;
import model.PrecioFlete;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class PreFleteDaoImpl implements PreFleteDao{
    @Override
    public List<PrecioFlete> findAll() {
        List<PrecioFlete> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM PrecioFlete where estado='Alta'";
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
    public List<PrecioFlete> findBaja() {
        List<PrecioFlete> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM PrecioFlete where estado='Baja'";
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
      public List<PrecioFlete> findPre(OrdenDeCarga ordenDeCarga) {
        List<PrecioFlete> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM PrecioFlete where cliente='"+ordenDeCarga.getCliente().getIdCliente()+"' and unidadOrigen='"+ordenDeCarga.getUnidadOrigen().getIdUnidadOrigen()+"' and unidadDestino='"+ordenDeCarga.getUnidadDestino().getIdUnidadDestino()+"' and estado='Alta'";
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
    public boolean create(PrecioFlete precioFlete) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(precioFlete);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(PrecioFlete precioFlete) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            PrecioFlete preciodb =(PrecioFlete) sesion.load(PrecioFlete.class, precioFlete.getIdPrecioFlete());
            preciodb.setPrecioPago(precioFlete.getPrecioPago());
            preciodb.setPrecioCobro(precioFlete.getPrecioCobro());
            preciodb.setCliente(precioFlete.getCliente());
            preciodb.setUnidadDestino(precioFlete.getUnidadDestino());
            preciodb.setUnidadOrigen(precioFlete.getUnidadOrigen());
            preciodb.setUsuario(precioFlete.getUsuario());
            preciodb.setDescripcion(precioFlete.getDescripcion());
            preciodb.setEstado(precioFlete.getEstado());
            preciodb.setPrecioiva(precioFlete.getPrecioiva());
            sesion.update(preciodb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean delete(Integer idPrecioFlete) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            PrecioFlete precioFlete =(PrecioFlete) sesion.load(PrecioFlete.class, idPrecioFlete);
            sesion.delete(precioFlete);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

}
