/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Camion;
import model.TipoViatico;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class TipoViaticoDaoImpl implements TipoViaticoDao{

    @Override
    public List<TipoViatico> SelectItems() {
        List<TipoViatico> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM TipoViatico";
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
    public List<TipoViatico> FindOne() {
        List<TipoViatico> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM TipoViatico where idTipo='2'";
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
