/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.CombustibleContra;
import model.Contrasenha;
import model.ViaticoContra;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class ContraDaoImpl implements ContraDao {
    @Override
    public List<Contrasenha> findAll() {
        List<Contrasenha> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Contrasenha";
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
    public boolean create(Contrasenha contrasenha, List<CombustibleContra> combcontra, List<ViaticoContra> viacontra) {
        boolean flag;
        CombustibleContra combcontraaux = new CombustibleContra();
        ViaticoContra viaaux= new ViaticoContra();
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(contrasenha);
            for (int i = 0; i < combcontra.size(); i++) {
                combcontraaux = combcontra.get(i);
                combcontraaux.setContrasenha(contrasenha);
                sesion.save(combcontraaux);
            }
            for (int h=0; h<viacontra.size();h++){
                viaaux= viacontra.get(h);
                viaaux.setContrasenha(contrasenha);
                sesion.save(viaaux);
            }
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(Contrasenha contrasenha) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Contrasenha contradb =(Contrasenha) sesion.load(Contrasenha.class, contrasenha.getIdContrasenha());
            contradb.setViaje(contrasenha.getViaje());
            contradb.setFecha(contrasenha.getFecha());
            contradb.setDescripcion(contrasenha.getDescripcion());
            sesion.update(contradb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
}
    @Override
    public boolean delete(Integer idContrasenha) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Contrasenha contrasenha =(Contrasenha) sesion.load(Contrasenha.class, idContrasenha);
            sesion.delete(contrasenha);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }
}
