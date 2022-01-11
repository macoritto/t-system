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
public class AdeClienteDaoImpl implements AdeClienteDao {
    @Override
    public List<AdeCliente> findAll() {
        List<AdeCliente> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM AdeCliente";
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
    public List<AdeCliente> findOne(Integer cod) {
        List<AdeCliente> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM AdeCliente where idAdeCliente='"+cod+"'";
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
    public List<AdeCliente> proveedor(Cliente cliente) {
        List<AdeCliente> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM AdeCliente where cliente='"+cliente.getIdCliente()+"' and estadoCobro='Pendiente'";
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
    public boolean create(AdeCliente adeSumi) {
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
    public boolean update(AdeCliente adeSumi) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            AdeCliente proveedordb =(AdeCliente) sesion.load(AdeCliente.class, adeSumi.getIdAdeCliente());
            proveedordb.setFecha(adeSumi.getFecha());
            proveedordb.setDescrip(adeSumi.getDescrip());
            proveedordb.setEstadoCobro(adeSumi.getEstadoCobro());
            proveedordb.setEstadoPago(adeSumi.getEstadoPago());
            proveedordb.setMonto(adeSumi.getMonto());
            proveedordb.setCliente(adeSumi.getCliente());
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
            AdeCliente unidad =(AdeCliente) sesion.load(AdeCliente.class, idAde);
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
