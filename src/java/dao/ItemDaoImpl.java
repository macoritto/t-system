/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Camion;
import model.Item;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class ItemDaoImpl implements ItemDao {
     @Override
    public boolean create(Item item) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(item);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(Item item) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Item itemdb =(Item) sesion.load(Item.class, item.getIdItem());
            itemdb.setCamion(item.getCamion());                       
            itemdb.setDescrip(item.getDescrip());                        
            itemdb.setActivo(item.getActivo());
            itemdb.setPasivo(item.getPasivo());   
            itemdb.setOrdenDeCarga(item.getOrdenDeCarga());
            itemdb.setExtracto(item.getExtracto());
            sesion.update(itemdb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean delete(Integer idItem) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Item item =(Item) sesion.load(Item.class, idItem);
            sesion.delete(item);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }
    @Override
    public Item Itemcompa(Camion camion, Integer coditem, Integer codtipo) {
        Item model = null;        
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from Item where camion='"+camion.getChapaCamion()+"' and cod='"+coditem+"' and tipoItem.idTipo='"+codtipo+"'";
        System.out.println(sql);
        Transaction tx = sesion.beginTransaction();
        try {
            model = (Item) sesion.createQuery(sql).uniqueResult();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return model;
    }
    @Override
    public List<Item> findAll() {
        List<Item> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Item";
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
    public List<Item> findCamion(Camion camion) {
        List<Item> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Item where camion='"+camion.getChapaCamion()+"'";
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
    public Integer maxItem() {
        Integer cantidad = 0;
        Query model;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from Item";
        Transaction tx = sesion.beginTransaction();
        try {
            model = sesion.createQuery(sql);
            List hola = model.list();
            cantidad = hola.size();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

        return cantidad;
    }
}
