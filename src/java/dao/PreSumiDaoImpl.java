/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.PrecioSuministro;
import model.ProveeCliente;
import model.TipoProvee;
import model.UnidadProvee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class PreSumiDaoImpl implements PreSumiDao {
    @Override
    public List<PrecioSuministro> findAll() {
        List<PrecioSuministro> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM PrecioSuministro";
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
    public boolean create(PrecioSuministro precio) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(precio);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(PrecioSuministro precio) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            PrecioSuministro proveedordb =(PrecioSuministro) sesion.load(PrecioSuministro.class, precio.getIdPrecioSumi());
            proveedordb.setNombre(precio.getNombre());
            proveedordb.setPrecio(precio.getPrecio());
            proveedordb.setEstado(precio.getEstado());
            proveedordb.setUnidadProvee(precio.getUnidadProvee());
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
    public boolean delete(Integer idPrecioSumi) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            PrecioSuministro Proveedor =(PrecioSuministro) sesion.load(PrecioSuministro.class, idPrecioSumi);
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
    public List<PrecioSuministro> findPro(UnidadProvee unidad) {
        List<PrecioSuministro> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM PrecioSuministro where id_unidad_pro='"+unidad.getIdUnidad()+"'";
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
