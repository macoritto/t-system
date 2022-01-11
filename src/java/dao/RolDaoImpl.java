/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Rol;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class RolDaoImpl implements RolDao {

    @Override
    public List<Rol> SelectItems() {
        List<Rol> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Rol";
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
    
}
