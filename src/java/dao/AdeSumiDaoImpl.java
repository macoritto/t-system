/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.AdeSuministro;
import model.ProveeCliente;
import model.UnidadProvee;
import model.TipoProvee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class AdeSumiDaoImpl implements AdeSumiDao {
    @Override
    public List<AdeSuministro> findAll() {
        List<AdeSuministro> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM AdeSuministro";
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
    public List<AdeSuministro> findOne(Integer cod) {
        List<AdeSuministro> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM AdeSuministro where idAde='"+cod+"'";
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
    public List<AdeSuministro> proveedor(ProveeCliente provee) {
        List<AdeSuministro> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM AdeSuministro where proveedor_id='"+provee.getIdProveecli()+"' and estadoPago='Pendiente'";
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
    public boolean create(AdeSuministro adeSumi) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(adeSumi);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(AdeSuministro adeSumi) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            AdeSuministro proveedordb =(AdeSuministro) sesion.load(AdeSuministro.class, adeSumi.getIdAde());
            proveedordb.setFecha(adeSumi.getFecha());
            proveedordb.setDescrip(adeSumi.getDescrip());
            proveedordb.setEstadoCobro(adeSumi.getEstadoCobro());
            proveedordb.setEstadoPago(adeSumi.getEstadoPago());
            proveedordb.setMonto(adeSumi.getMonto());
            proveedordb.setProveeCliente(adeSumi.getProveeCliente());
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
            AdeSuministro unidad =(AdeSuministro) sesion.load(AdeSuministro.class, idAde);
            sesion.delete(unidad);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }
    
}
