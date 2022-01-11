/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.DetExtracto;
import model.Extracto;
import model.PagoAdeDet;
import model.PagoSuministro;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class PagoAdeImpl implements DetAde {   
    
    @Override
    public boolean create(PagoAdeDet detade) {
        boolean flag ;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(detade);
            tx.commit();
            flag= true;

        } catch (Exception e) {
            flag=false;
            tx.rollback();
        }

        return flag;
    }
    @Override
    public List<PagoAdeDet> findByPagoc(PagoSuministro pagoSumi) {
        List<PagoAdeDet> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from PagoAdeDet where pagoSuministro="+pagoSumi.getIdPagosumi();
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
