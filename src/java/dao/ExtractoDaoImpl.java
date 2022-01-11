/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Camion;
import model.DetExtracto;
import model.ExCamion;
import model.ExOrden;
import model.Extracto;
import model.Item;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class ExtractoDaoImpl implements ExtractoDao {
    @Override
    public Extracto findExtracto(Extracto extracto) {
        Extracto model = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from Extracto where idLiquidacion = '" + extracto.getIdLiquidacion() + "'";
        try {
            sesion.beginTransaction();
            model = (Extracto) sesion.createQuery(sql).uniqueResult();
            sesion.beginTransaction().commit();
        } catch (Exception e) {
            sesion.beginTransaction().rollback();
        }

        return model;
    }
    @Override
    public List<Extracto> findAll() {
        List<Extracto> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Extracto";
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
    public List<Extracto> findAnulado() {
        List<Extracto> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Extracto where estado='Anulado'";
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
    public boolean create(Extracto extracto, List<DetExtracto> detextracto, List<ExCamion> excamion, List<ExOrden> exOrden) {
        boolean flag;
        Integer maxCodpago = this.maxPago() + 1;
        DetExtracto detExtractoAux = new DetExtracto();
        ExCamion exaux= new ExCamion();
        ExOrden exor= new ExOrden();
        Item item= new Item();
        extracto.setIdLiquidacion(maxCodpago);
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(extracto);
            for (int i = 0; i < detextracto.size(); i++) {
                detExtractoAux = detextracto.get(i);
                detExtractoAux.setExtracto(extracto);
                sesion.save(detExtractoAux);
            }
            for (int h=0; h<excamion.size();h++){
                exaux= excamion.get(h);
                exaux.setExtracto(extracto);
                sesion.save(exaux);
            }
            for (int h=0; h<exOrden.size();h++){
                exor= exOrden.get(h);
                exor.setExtracto(extracto);
                sesion.save(exor);
            }
//            for (int h=0; h<items.size();h++){
//                item= items.get(h);
//                item.setExtracto(extracto);
////                sesion.update(item);
//            }
            tx.commit();
            flag = true;

        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage() + " Mensaje Error: " + e.getMessage());
            flag = false;
            tx.rollback();
        }

        return flag;
    }

    @Override
    public boolean update(Extracto extracto) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {    
            Extracto extractodb =(Extracto) sesion.load(Extracto.class, extracto.getIdLiquidacion());
            extractodb.setDescripcion(extracto.getDescripcion());
            extractodb.setFecha(extracto.getFecha());
            extractodb.setFechaPago(extracto.getFechaPago());
            extractodb.setEstado(extracto.getEstado());
            extracto.setPropietario(extracto.getPropietario());
            sesion.update(extractodb);
            tx.commit();
            flag = true;

        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }

        return flag;
    }

    @Override
    public boolean delete(Extracto extracto) {
        boolean flag;
        List<DetExtracto> detalles;
        List<ExCamion> detalles1;
        Integer codextracto = extracto.getIdLiquidacion();        
        detalles = findByDetextracto(codextracto);
        detalles1 = findById(codextracto);
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            for (DetExtracto detalle : detalles) {
                sesion.delete(detalle);
            }
            for (ExCamion detalle1 : detalles1) {
                sesion.delete(detalle1);
            }
            
            sesion.delete(extracto);
            tx.commit();
            flag = true;

        } catch (Exception e) {
            flag = false;
            System.out.println(e.getLocalizedMessage() + e.getMessage());
            tx.rollback();
        }

        return flag;

    }

    @Override
    public Integer maxPago() {
        Integer cantidad = 0;
        Query model;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from Extracto";
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
    @Override
    public List<DetExtracto> findByDetextracto(Integer codextracto) {
        List<DetExtracto> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from DetExtracto x left join fetch x.extracto p left join fetch x.item r where x.extracto.idLiquidacion=" +codextracto+" order by x.item.idItem";
        Transaction tx = sesion.beginTransaction();
        try {            
            listado = sesion.createQuery(sql).list();
            tx.commit();

        } catch (Exception e) {
            System.out.println("Error findBypagov: " + e.getLocalizedMessage() + e.getMessage());
            tx.rollback();
        }

        return listado;
    }
    @Override
    public List<ExCamion> findById(Integer codextracto) {
        List<ExCamion> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from ExCamion where extracto="+codextracto;
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
