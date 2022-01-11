/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.Date;
import model.Camion;
import model.Combustible;
import model.OrdenDeCarga;
import model.PrecioSuministro;
import model.ProveeCliente;
import model.Proveedor;
import model.Suministro;
import model.TipoSuministro;
import model.ValeCombustible;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Usuario
 */
public class SumiDaoImpl implements SumiDao {
   @Override
    public List<Suministro> findAll() {
        List<Suministro> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Suministro where tipoSuministro!='4' and tipoSuministro!='5' ORDER BY id_suministro";
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
    public List<TipoSuministro> tipos() {
        List<TipoSuministro> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM TipoSuministro";
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
    public List<Suministro> findOne(Integer cod) {
        List<Suministro> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Suministro where idSuministro='"+cod+"'";
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
    public List<Suministro> encontrar(Integer cod) {
        List<Suministro> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Suministro where idSuministro='"+cod+"'";
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
    public List<Suministro> pendientes(Integer cod) {
        List<Suministro> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Suministro where orden_de_carga='"+cod+"' and estado_cobro='Pendiente' ORDER BY fecha";
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
    public List<Suministro> pendienteschapa(Integer cod, String chapa) {
        List<Suministro> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Suministro where orden_de_carga='"+cod+"' and estado_cobro='Pendiente' and chapa_camion='"+chapa+"' ORDER BY fecha";
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
    public List<Suministro> pendseguro(Integer cod, TipoSuministro tipo) {
        List<Suministro> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Suministro where orden_de_carga='"+cod+"' and estado_cobro='Pendiente' and tipo_suministro="+tipo.getIdTipo()+" ORDER BY fecha";
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
    public List<Suministro> pendfaltante(Integer cod, TipoSuministro tipo) {
        List<Suministro> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Suministro where orden_de_carga='"+cod+"' and estado_cobro='Pendiente' and tipo_suministro="+tipo.getIdTipo()+" ORDER BY fecha";
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
    public List<Suministro> findprovee(Integer proveeCliente) {
        List<Suministro> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Suministro s left join fetch s.unidadProvee u left join fetch u.proveeCliente p where u.proveeCliente.idProveecli='"+proveeCliente+"' and estado_pago='Pendiente' ORDER BY fecha";
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
    public boolean create(Suministro suministro) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(suministro);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }

    @Override
    public boolean update(Suministro suministro) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Suministro combdb =(Suministro) sesion.load(Suministro.class, suministro.getIdSuministro());
            combdb.setCamion(suministro.getCamion());
            combdb.setChofer(suministro.getChofer());
            combdb.setDescrip(suministro.getDescrip());
            combdb.setDetSumis(suministro.getDetSumis());
            combdb.setEstadoCobro(suministro.getEstadoCobro());
            combdb.setEstadoPago(suministro.getEstadoPago());
            combdb.setFecha(suministro.getFecha());
            combdb.setLitros(suministro.getLitros());
            combdb.setMonto(suministro.getMonto());            
            combdb.setMontoTotal(suministro.getMontoTotal());
            combdb.setOrdenDeCarga(suministro.getOrdenDeCarga());
            combdb.setPagoSumiDets(suministro.getPagoSumiDets());
            combdb.setPrecioSumi(suministro.getPrecioSumi());
            combdb.setPrecioSuministro(suministro.getPrecioSuministro());
            combdb.setTipoSuministro(suministro.getTipoSuministro());
            combdb.setUnidadProvee(suministro.getUnidadProvee());
            combdb.setUsuario(suministro.getUsuario());
            sesion.update(combdb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
}
    @Override
    public boolean delete(Integer idSuministro) {
         boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Suministro suministro =(Suministro) sesion.load(Suministro.class, idSuministro);
            sesion.delete(suministro);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            tx.rollback();
        }
        return flag;
    }
    @Override
    public List<TipoSuministro> SelectItems() {
        List<TipoSuministro> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM TipoSuministro";
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
    public List<PrecioSuministro> precio() {
        List<PrecioSuministro> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM PrecioSuministro where idPrecioSumi='3'";
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
