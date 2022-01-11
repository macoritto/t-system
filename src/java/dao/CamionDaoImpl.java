/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Camion;
import model.OrdenDeCarga;
import model.Propietario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class CamionDaoImpl implements CamionDao {
    @Override
    public List<Camion> findAll() {
        List<Camion> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Camion where chapaCamion!='-' ORDER BY chapaCamion";
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
    public List<Camion> findOne(Propietario propietario) {
        List<Camion> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Camion where propietario='"+ propietario.getIdPropietario()+"'";
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
    public List<Camion> findCamion(String camion) {
        List<Camion> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Camion where chapa_camion='"+camion+"'";
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
    public boolean create(Camion camion) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(camion);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(Camion camion) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Camion camiondb =(Camion) sesion.load(Camion.class, camion.getChapaCamion());
            camiondb.setColor(camion.getColor());
            camiondb.setPeso(camion.getPeso());
            camiondb.setCarreta(camion.getCarreta());
            camiondb.setPropietario(camion.getPropietario());
            camiondb.setTipoCamion(camion.getTipoCamion());
            camiondb.setMarca(camion.getMarca());
            camiondb.setChofer(camion.getChofer());
            camiondb.setAnho(camion.getAnho());
            camiondb.setModelo(camion.getModelo());
            camiondb.setDepartamento(camion.getDepartamento());
            camiondb.setChapaCamion(camion.getChapaCamion());
            camiondb.setFechaVencimiento(camion.getFechaVencimiento());
            camiondb.setEstado(camion.getEstado());
            sesion.update(camiondb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
}
    @Override
    public boolean delete(String chapaCamion) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Camion camion =(Camion) sesion.load(Camion.class, chapaCamion);
            sesion.delete(camion);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

}
