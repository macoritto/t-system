/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.ProveeCliente;
import model.TipoProvee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class ProSumiDaoImpl implements ProSumiDao {
    @Override
    public List<ProveeCliente> findAll() {
        List<ProveeCliente> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        TipoProvee tipo = new TipoProvee();
        tipo.setIdProvee(2);
        String sql = "FROM ProveeCliente where tipoProvee='"+tipo.getIdProvee()+"'";
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
    public boolean create(ProveeCliente proveedor) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(proveedor);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(ProveeCliente proveedor) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            ProveeCliente proveedordb =(ProveeCliente) sesion.load(ProveeCliente.class, proveedor.getIdProveecli());
            proveedordb.setNombre(proveedor.getNombre());
            proveedordb.setRuc(proveedor.getRuc());
            proveedordb.setDireccion(proveedor.getDireccion());
            proveedordb.setTelefono(proveedor.getTelefono());
            proveedordb.setCorreo(proveedor.getCorreo());
            proveedordb.setDescripcion(proveedor.getDescripcion());
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
    public boolean delete(Integer idProveedor) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            ProveeCliente Proveedor =(ProveeCliente) sesion.load(ProveeCliente.class, idProveedor);
            sesion.delete(Proveedor);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }
    @Override
    public List<ProveeCliente> SelectItems() {
        List<ProveeCliente> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM ProveeCliente";
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
