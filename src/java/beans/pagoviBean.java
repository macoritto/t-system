/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.AdeClienteDao;
import dao.AdeClienteDaoImpl;
import dao.DetPagoAdeDao;
import dao.DetPagoAdeDaoImpl;
import dao.DetPagoSumiDao;
import dao.DetPagoSumiDaoImpl;
import dao.DetpagocDaoImpl;
import dao.DetpagoviDao;
import dao.DetpagoviDaoImpl;
import dao.PagoviDao;
import dao.PagoviDaoImpl;
import dao.SumiCliDao;
import dao.SumiCliDaoImpl;
import dao.ViajeDao;
import dao.ViajeDaoImpl;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.AdeCliente;
import model.Cliente;
import model.DetPagoAde;
import model.DetPagoFlete;
import model.DetPagoSumi;
import model.PagoFlete;
import model.PagoSumiDet;
import model.SumiCliente;
import model.Usuario;
import model.Viaje;
import model.sumicli;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Usuario
 */
@ManagedBean
@SessionScoped
public class pagoviBean {

    private List<PagoFlete> pagos;
    private PagoFlete selectedPago;
    private PagoFlete selectedPago1;
    private sumicli selectedsumi;
    private List<sumicli> sumiclis;
    private List<sumicli> sumiclis2;
    private List<sumicli> sumiclisupdate;
    private List<Viaje> viajes;
    private Cliente selectedCliente;
    private Usuario usuario;
    private List<DetPagoFlete> detpagovi;
    private List<DetPagoFlete> detpagoviAux;
    private DetPagoFlete selectedDetpagovi;
    private DetPagoFlete selectedDetpagoviAux;
    private Viaje selectedViaje;
    private Double total;
    private Double descuento;
    private Double adelanto = 0.0;
    private Double suministro = 0.0;
    private Double bruto;
    private Boolean reteboo = true;
    private Boolean cargarboo = true;
    private Double saldocobrar = 0.0;
    private Double retencion = 0.0;
    private Integer codpagovi;
    private Connection conexion;
    private List<Viaje> selectedViajes;
    private List<Viaje> selectedViajesAux;
    private String NroLiq;
    private Integer codpago;
    private String Descrip;
    private Integer contaflete;
    private Integer pesoO;
    private Integer pesoD;
    private Integer dif;
    private Double ganancia;
    private String iva = "";

    public pagoviBean() {
        this.pagos = new ArrayList<PagoFlete>();
        this.selectedPago = new PagoFlete();
        this.selectedPago1 = new PagoFlete();
        this.viajes = new ArrayList<Viaje>();
        this.selectedCliente = new Cliente();
        this.usuario = new Usuario();
        this.detpagovi = new ArrayList<DetPagoFlete>();
        this.detpagoviAux = new ArrayList<DetPagoFlete>();
        this.selectedDetpagovi = new DetPagoFlete();
        this.selectedDetpagoviAux = new DetPagoFlete();
        this.selectedViaje = new Viaje();
        this.selectedPago.setFecha(new Date());
        this.selectedViajes = new ArrayList<Viaje>();
        this.selectedViajesAux = new ArrayList<Viaje>();
        this.selectedsumi = new sumicli();
        this.sumiclis = new ArrayList<sumicli>();
        this.sumiclis2 = new ArrayList<sumicli>();
        this.sumiclisupdate = new ArrayList<sumicli>();
    }
    private boolean chkBoxChecked;

    public boolean isChkBoxChecked() {
        return chkBoxChecked;
    }

    public boolean isBtnDisabled() {
        if (this.selectedPago.getEstado() == "Anulado") {
            this.chkBoxChecked = true;
        } else {
            this.chkBoxChecked = false;
        }
        return !this.chkBoxChecked;
    }

    public List<DetPagoFlete> getDetpagovi() {
        return detpagovi;
    }

    public Integer getContaflete() {
        return contaflete;
    }

    public Integer getPesoO() {
        return pesoO;
    }

    public Integer getPesoD() {
        return pesoD;
    }

    public Integer getDif() {
        return dif;
    }

    public Double getSaldocobrar() {
        return saldocobrar;
    }

    public void setSaldocobrar(Double saldocobrar) {
        this.saldocobrar = saldocobrar;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public Boolean getReteboo() {
        return reteboo;
    }

    public void setReteboo(Boolean reteboo) {
        this.reteboo = reteboo;
    }

    public Boolean getCargarboo() {
        return cargarboo;
    }

    public void setCargarboo(Boolean cargarboo) {
        this.cargarboo = cargarboo;
    }

    public List<sumicli> getSumiclisupdate() {
        this.sumiclisupdate = new ArrayList<sumicli>();
        PagoviDao pagoDao = new PagoviDaoImpl();
        DetPagoSumiDao sumidet = new DetPagoSumiDaoImpl();
        DetPagoAdeDao adedet = new DetPagoAdeDaoImpl();
        List<DetPagoSumi> sumilist;
        sumilist = new ArrayList<DetPagoSumi>();
        sumilist = sumidet.findByPagov(this.selectedPago);
        List<DetPagoAde> adelist;
        adelist = new ArrayList<DetPagoAde>();
        adelist = adedet.findByPagov(this.selectedPago);
        Double precioaux = 0.0, pagoaux = 0.0;
        this.detpagoviAux = pagoDao.findByDetpagovi(this.selectedPago.getIdPagoFlete());
        if (this.selectedPago.getIvacom().equals("NO")) {
            for (int i = 0; i < this.detpagoviAux.size(); i++) {
                this.selectedsumi.setId(this.detpagoviAux.get(i).getViaje().getIdViaje());
                this.selectedsumi.setBruto(this.detpagoviAux.get(i).getViaje().getMontoBruto());
                this.selectedsumi.setChapa(this.detpagoviAux.get(i).getViaje().getOrdenDeCarga().getCamion().getChapaCamion());
                this.selectedsumi.setBruto(this.detpagoviAux.get(i).getViaje().getMontoBruto());
                this.selectedsumi.setChofer(this.detpagoviAux.get(i).getViaje().getChofer().getNombre());
                this.selectedsumi.setDescrip(this.detpagoviAux.get(i).getViaje().getDescripcion());
                this.selectedsumi.setDescuento(this.detpagoviAux.get(i).getViaje().getMontofaltante());
                this.selectedsumi.setDestino(this.detpagoviAux.get(i).getViaje().getOrdenDeCarga().getUnidadDestino().getNombre());
                this.selectedsumi.setFecha(this.detpagoviAux.get(i).getViaje().getFechaDestino());
                this.selectedsumi.setMonto(this.detpagoviAux.get(i).getViaje().getMontoBruto());
                this.selectedsumi.setOrigen(this.detpagoviAux.get(i).getViaje().getOrdenDeCarga().getUnidadOrigen().getNombre());
                this.selectedsumi.setPdestino(this.detpagoviAux.get(i).getViaje().getPesoDestino());
                this.selectedsumi.setPorigen(this.detpagoviAux.get(i).getViaje().getPesoOrigen());
                this.selectedsumi.setPrecio(this.detpagoviAux.get(i).getViaje().getPrecioCobro());
                this.selectedsumi.setRemision(this.detpagoviAux.get(i).getViaje().getRemisionDestino());
                this.selectedsumi.setTipo("Flete");
                this.selectedsumi.setTotal(this.detpagoviAux.get(i).getViaje().getMontoCobrar());
                precioaux = this.detpagoviAux.get(i).getViaje().getPrecioPago();
                pagoaux = precioaux * this.detpagoviAux.get(i).getViaje().getPesoDestino();
                this.selectedsumi.setPagar(pagoaux);
                this.sumiclisupdate.add(this.selectedsumi);
                this.selectedsumi = new sumicli();
            }
            System.out.print("Esta es la cantidad de suministros");
            System.out.print(sumilist.size());
            for (int i = 0; i < sumilist.size(); i++) {
                System.out.print("entra aca");
                this.selectedsumi.setId(sumilist.get(i).getSumiCliente().getIdSumiCli());
                this.selectedsumi.setBruto(sumilist.get(i).getSumiCliente().getMonto());
                this.selectedsumi.setChapa(sumilist.get(i).getSumiCliente().getCamion().getChapaCamion());
                this.selectedsumi.setChofer(sumilist.get(i).getSumiCliente().getChofer().getNombre());
                this.selectedsumi.setDescrip(sumilist.get(i).getSumiCliente().getDescrip());
                this.selectedsumi.setDescuento(0.0);
                this.selectedsumi.setDestino(sumilist.get(i).getSumiCliente().getDescrip());
                this.selectedsumi.setFecha(sumilist.get(i).getSumiCliente().getFecha());
                this.selectedsumi.setMonto(sumilist.get(i).getSumiCliente().getMonto());
                this.selectedsumi.setOrigen("SUMINISTRO ENTREGADO POR EL CLIENTE");
                this.selectedsumi.setPdestino(0);
                this.selectedsumi.setPorigen(0);
                this.selectedsumi.setPrecio(0.0);
                this.selectedsumi.setRemision("N/S");
                this.selectedsumi.setTipo("Suministro");
                this.selectedsumi.setTotal(sumilist.get(i).getSumiCliente().getMonto());
                this.sumiclisupdate.add(this.selectedsumi);
                this.selectedsumi = new sumicli();
            }
            System.out.print("Esta es la cantidad de adelantos");
            System.out.print(adelist.size());
            for (int i = 0; i < adelist.size(); i++) {
                System.out.print("entra aca");
                this.selectedsumi.setId(adelist.get(i).getAdeCliente().getIdAdeCliente());
                this.selectedsumi.setBruto(adelist.get(i).getAdeCliente().getMonto());
                this.selectedsumi.setChapa("N/S");
                this.selectedsumi.setChofer("N/S");
                this.selectedsumi.setDescrip(adelist.get(i).getAdeCliente().getDescrip());
                this.selectedsumi.setDescuento(0.0);
                this.selectedsumi.setDestino(adelist.get(i).getAdeCliente().getDescrip());
                this.selectedsumi.setFecha(adelist.get(i).getAdeCliente().getFecha());
                this.selectedsumi.setMonto(adelist.get(i).getAdeCliente().getMonto());
                this.selectedsumi.setOrigen("ADELANTO ENTREGADO POR EL CLIENTE");
                this.selectedsumi.setPdestino(0);
                this.selectedsumi.setPorigen(0);
                this.selectedsumi.setPrecio(0.0);
                this.selectedsumi.setRemision("N/S");
                this.selectedsumi.setTipo("Adelanto");
                this.selectedsumi.setTotal(adelist.get(i).getAdeCliente().getMonto());
                this.sumiclisupdate.add(this.selectedsumi);
                this.selectedsumi = new sumicli();
            }
        } else {
            for (int i = 0; i < this.detpagoviAux.size(); i++) {
                this.selectedsumi.setId(this.detpagoviAux.get(i).getViaje().getIdViaje());
                this.selectedsumi.setBruto(this.detpagoviAux.get(i).getViaje().getMontoconiva());
                this.selectedsumi.setChapa(this.detpagoviAux.get(i).getViaje().getOrdenDeCarga().getCamion().getChapaCamion());
                this.selectedsumi.setBruto(this.detpagoviAux.get(i).getViaje().getMontoconiva());
                this.selectedsumi.setChofer(this.detpagoviAux.get(i).getViaje().getChofer().getNombre());
                this.selectedsumi.setDescrip(this.detpagoviAux.get(i).getViaje().getDescripcion());
                this.selectedsumi.setDescuento(this.detpagoviAux.get(i).getViaje().getMontofaltante());
                this.selectedsumi.setDestino(this.detpagoviAux.get(i).getViaje().getOrdenDeCarga().getUnidadDestino().getNombre());
                this.selectedsumi.setFecha(this.detpagoviAux.get(i).getViaje().getFechaDestino());
                this.selectedsumi.setMonto(this.detpagoviAux.get(i).getViaje().getMontoBruto());
                this.selectedsumi.setOrigen(this.detpagoviAux.get(i).getViaje().getOrdenDeCarga().getUnidadOrigen().getNombre());
                this.selectedsumi.setPdestino(this.detpagoviAux.get(i).getViaje().getPesoDestino());
                this.selectedsumi.setPorigen(this.detpagoviAux.get(i).getViaje().getPesoOrigen());
                this.selectedsumi.setPrecio(this.detpagoviAux.get(i).getViaje().getPrecioCobro());
                this.selectedsumi.setRemision(this.detpagoviAux.get(i).getViaje().getRemisionDestino());
                this.selectedsumi.setTipo("Flete");
                this.selectedsumi.setTotal(this.detpagoviAux.get(i).getViaje().getMontoconiva() - this.detpagoviAux.get(i).getViaje().getMontofaltante());
                precioaux = this.detpagoviAux.get(i).getViaje().getPrecioPago();
                pagoaux = precioaux * this.detpagoviAux.get(i).getViaje().getPesoDestino();
                this.selectedsumi.setPagar(pagoaux);
                this.sumiclisupdate.add(this.selectedsumi);
                this.selectedsumi = new sumicli();
            }
            System.out.print("Esta es la cantidad de suministros");
            System.out.print(sumilist.size());
            for (int i = 0; i < sumilist.size(); i++) {
                System.out.print("entra aca");
                this.selectedsumi.setId(sumilist.get(i).getSumiCliente().getIdSumiCli());
                this.selectedsumi.setBruto(sumilist.get(i).getSumiCliente().getMonto());
                this.selectedsumi.setChapa(sumilist.get(i).getSumiCliente().getCamion().getChapaCamion());
                this.selectedsumi.setChofer(sumilist.get(i).getSumiCliente().getChofer().getNombre());
                this.selectedsumi.setDescrip(sumilist.get(i).getSumiCliente().getDescrip());
                this.selectedsumi.setDescuento(0.0);
                this.selectedsumi.setDestino(sumilist.get(i).getSumiCliente().getDescrip());
                this.selectedsumi.setFecha(sumilist.get(i).getSumiCliente().getFecha());
                this.selectedsumi.setMonto(sumilist.get(i).getSumiCliente().getMonto());
                this.selectedsumi.setOrigen("SUMINISTRO ENTREGADO POR EL CLIENTE");
                this.selectedsumi.setPdestino(0);
                this.selectedsumi.setPorigen(0);
                this.selectedsumi.setPrecio(0.0);
                this.selectedsumi.setRemision("N/S");
                this.selectedsumi.setTipo("Suministro");
                this.selectedsumi.setTotal(sumilist.get(i).getSumiCliente().getMonto());
                this.sumiclisupdate.add(this.selectedsumi);
                this.selectedsumi = new sumicli();
            }
            System.out.print("Esta es la cantidad de adelantos");
            System.out.print(adelist.size());
            for (int i = 0; i < adelist.size(); i++) {
                System.out.print("entra aca");
                this.selectedsumi.setId(adelist.get(i).getAdeCliente().getIdAdeCliente());
                this.selectedsumi.setBruto(adelist.get(i).getAdeCliente().getMonto());
                this.selectedsumi.setChapa("N/S");
                this.selectedsumi.setChofer("N/S");
                this.selectedsumi.setDescrip(adelist.get(i).getAdeCliente().getDescrip());
                this.selectedsumi.setDescuento(0.0);
                this.selectedsumi.setDestino(adelist.get(i).getAdeCliente().getDescrip());
                this.selectedsumi.setFecha(adelist.get(i).getAdeCliente().getFecha());
                this.selectedsumi.setMonto(adelist.get(i).getAdeCliente().getMonto());
                this.selectedsumi.setOrigen("ADELANTO ENTREGADO POR EL CLIENTE");
                this.selectedsumi.setPdestino(0);
                this.selectedsumi.setPorigen(0);
                this.selectedsumi.setPrecio(0.0);
                this.selectedsumi.setRemision("N/S");
                this.selectedsumi.setTipo("Adelanto");
                this.selectedsumi.setTotal(adelist.get(i).getAdeCliente().getMonto());
                this.sumiclisupdate.add(this.selectedsumi);
                this.selectedsumi = new sumicli();
            }
        }

        this.contaflete = this.sumiclisupdate.size();
        this.pesoD = this.sumarDestino(this.sumiclisupdate);
        this.pesoO = this.sumarOrigen(this.sumiclisupdate);
        this.contaflete = this.sumiclisupdate.size();
        this.dif = this.pesoO - this.pesoD;
        this.ganancia = this.sumarGananciaUp(this.sumiclisupdate);
        this.selectedPago.setMontoTotal(this.sumarBrutoUp(this.sumiclisupdate));
        return sumiclisupdate;
    }

    public void setSumiclisupdate(List<sumicli> sumiclisupdate) {
        this.sumiclisupdate = sumiclisupdate;
    }

    public Double getRetencion() {
        return retencion;
    }

    public void setRetencion(Double retencion) {
        this.retencion = retencion;
    }

    public List<DetPagoFlete> getDetpagoviAux() {
        PagoviDao pagoDao = new PagoviDaoImpl();
        this.detpagoviAux = pagoDao.findByDetpagovi(this.selectedPago.getIdPagoFlete());
//        this.bruto = this.sumarBruto(detpagoviAux);
//        this.descuento = this.sumarDescuento(detpagoviAux);
        this.total = this.sumarTotalPago(pagos);
        this.contaflete = this.detpagoviAux.size();
//        this.pesoD = this.sumarDestino(detpagoviAux);
//        this.pesoO = this.sumarOrigen(detpagoviAux);
//        this.dif = this.pesoO-this.pesoD;
//        this.ganancia = this.sumarGanancia(detpagoviAux);
        return detpagoviAux;
    }

    public Double getGanancia() {
        return ganancia;
    }

    public sumicli getSelectedsumi() {
        return selectedsumi;
    }

    public void setSelectedsumi(sumicli selectedsumi) {
        this.selectedsumi = selectedsumi;
    }

    public List<sumicli> getSumiclis() {
        return sumiclis;
    }

    public void setSumiclis(List<sumicli> sumiclis) {
        this.sumiclis = sumiclis;
    }

    public List<sumicli> getSumiclis2() {
        return sumiclis2;
    }

    public void setSumiclis2(List<sumicli> sumiclis2) {
        this.sumiclis2 = sumiclis2;
    }

    public Double getAdelanto() {
        return adelanto;
    }

    public void setAdelanto(Double adelanto) {
        this.adelanto = adelanto;
    }

    public Double getSuministro() {
        return suministro;
    }

    public void setSuministro(Double suministro) {
        this.suministro = suministro;
    }

    public List<PagoFlete> getPagos() {
        PagoviDao pagoDao = new PagoviDaoImpl();
        this.pagos = pagoDao.findAll();
        this.total = this.sumarTotalPago(pagos);
        return pagos;
    }

    public List<Viaje> getViajes() {
        ViajeDao viajeDao = new ViajeDaoImpl();
        this.viajes = viajeDao.findpen(this.selectedCliente);
        //this.total=this.sumarTotalPago(pagos);
        return viajes;
    }

    public PagoFlete getSelectedPago() {
        return selectedPago;
    }

    public Cliente getSelectedCliente() {
        return selectedCliente;
    }

    public DetPagoFlete getSelectedDetpagovi() {
        return selectedDetpagovi;
    }

    public DetPagoFlete getSelectedDetpagoviAux() {
        return selectedDetpagoviAux;
    }

    public Viaje getSelectedViaje() {
        return selectedViaje;
    }

    public Double getTotal() {
        return total;
    }

    public List<Viaje> getSelectedViajes() {
        return selectedViajes;
    }

    public List<Viaje> getSelectedViajesAux() {
        return selectedViajesAux;
    }

    public Integer getCodpagovi() {
        return codpagovi;
    }

    public Double getDescuento() {
        return descuento;
    }

    public Double getBruto() {
        return bruto;
    }

    public void setBruto(Double bruto) {
        this.bruto = bruto;
    }

    public void setDetpagovi(List<DetPagoFlete> detpagovi) {
        this.detpagovi = detpagovi;
    }

    public void setSelectedPago(PagoFlete selectedPago) {
        this.selectedPago = selectedPago;
    }

    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    public void setSelectedDetpagovi(DetPagoFlete selectedDetpagovi) {
        this.selectedDetpagovi = selectedDetpagovi;
    }

    public void setSelectedDetpagoviAux(DetPagoFlete selectedDetpagoviAux) {
        this.selectedDetpagoviAux = selectedDetpagoviAux;
    }

    public void setSelectedViaje(Viaje selectedViaje) {
        this.selectedViaje = selectedViaje;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setSelectedViajes(List<Viaje> selectedViajes) {
        this.selectedViajes = selectedViajes;
    }

    public void setSelectedViajesAux(List<Viaje> selectedViajesAux) {
        this.selectedViajesAux = selectedViajesAux;
    }

    public String cargarArray(ActionEvent event) {
        if (iva.equals("SI")) {
            String msg = "";
            ViajeDao viajeDao = new ViajeDaoImpl();
            this.selectedsumi = new sumicli();
            this.sumiclis2 = new ArrayList<sumicli>();
            this.sumiclis = new ArrayList<sumicli>();
            this.viajes = viajeDao.findpen(this.selectedCliente);
            this.selectedViajesAux = new ArrayList<Viaje>();
            Double pagoaux = 0.0, precioaux = 0.0;
            for (int i = 0; i < this.viajes.size(); i++) {
                this.selectedsumi.setId(this.viajes.get(i).getIdViaje());
                this.selectedsumi.setBruto(this.viajes.get(i).getMontoconiva());
                this.selectedsumi.setChapa(this.viajes.get(i).getOrdenDeCarga().getCamion().getChapaCamion());
                this.selectedsumi.setBruto(this.viajes.get(i).getMontoconiva());
                this.selectedsumi.setChofer(this.viajes.get(i).getChofer().getNombre());
                this.selectedsumi.setDescrip(this.viajes.get(i).getDescripcion());
                this.selectedsumi.setDescuento(this.viajes.get(i).getMontofaltante());
                this.selectedsumi.setDestino(this.viajes.get(i).getOrdenDeCarga().getUnidadDestino().getNombre());
                this.selectedsumi.setFecha(this.viajes.get(i).getFechaDestino());
                this.selectedsumi.setMonto(this.viajes.get(i).getMontoconiva());
                this.selectedsumi.setOrigen(this.viajes.get(i).getOrdenDeCarga().getUnidadOrigen().getNombre());
                this.selectedsumi.setPdestino(this.viajes.get(i).getPesoDestino());
                this.selectedsumi.setPorigen(this.viajes.get(i).getPesoOrigen());
                this.selectedsumi.setPrecio(this.viajes.get(i).getPrecioiva());
                this.selectedsumi.setRemision(this.viajes.get(i).getRemisionDestino());
                this.selectedsumi.setTipo("Flete");
                this.selectedsumi.setTotal(this.viajes.get(i).getMontoconiva() - this.viajes.get(i).getMontofaltante());
                precioaux = this.viajes.get(i).getPrecioPago();
                pagoaux = precioaux * this.viajes.get(i).getPesoDestino();
                this.selectedsumi.setPagar(pagoaux);
                this.sumiclis.add(this.selectedsumi);
                this.selectedsumi = new sumicli();
            }
            List<AdeCliente> adelantos;
            AdeClienteDao ade = new AdeClienteDaoImpl();
            adelantos = ade.proveedor(this.selectedCliente);
            for (int i = 0; i < adelantos.size(); i++) {
                this.selectedsumi.setId(adelantos.get(i).getIdAdeCliente());
                this.selectedsumi.setBruto(adelantos.get(i).getMonto());
                this.selectedsumi.setChapa("N/S");
                this.selectedsumi.setChofer("N/S");
                this.selectedsumi.setDescrip(adelantos.get(i).getDescrip());
                this.selectedsumi.setDescuento(0.0);
                this.selectedsumi.setDestino(adelantos.get(i).getDescrip());
                this.selectedsumi.setFecha(adelantos.get(i).getFecha());
                this.selectedsumi.setMonto(adelantos.get(i).getMonto());
                this.selectedsumi.setOrigen("ADELANTO ENTREGADO POR EL CLIENTE");
                this.selectedsumi.setPdestino(0);
                this.selectedsumi.setPorigen(0);
                this.selectedsumi.setPrecio(0.0);
                this.selectedsumi.setRemision("N/S");
                this.selectedsumi.setTipo("Adelanto");
                this.selectedsumi.setTotal(adelantos.get(i).getMonto());
                this.sumiclis.add(this.selectedsumi);
                this.selectedsumi = new sumicli();
            }
            List<SumiCliente> sumi;
            SumiCliDao ades = new SumiCliDaoImpl();
            sumi = ades.proveedor(this.selectedCliente);
            System.out.print("LOS SUMINISTROS QUE SELECCIONA");
            System.out.print(sumi.size());
            for (int i = 0; i < sumi.size(); i++) {
                System.out.print("entra aca");
                this.selectedsumi.setId(sumi.get(i).getIdSumiCli());
                this.selectedsumi.setBruto(sumi.get(i).getMonto());
                this.selectedsumi.setChapa(sumi.get(i).getCamion().getChapaCamion());
                this.selectedsumi.setChofer(sumi.get(i).getChofer().getNombre());
                this.selectedsumi.setDescrip(sumi.get(i).getDescrip());
                this.selectedsumi.setDescuento(0.0);
                this.selectedsumi.setDestino(sumi.get(i).getDescrip());
                this.selectedsumi.setFecha(sumi.get(i).getFecha());
                this.selectedsumi.setMonto(sumi.get(i).getMonto());
                this.selectedsumi.setOrigen("SUMINISTRO ENTREGADO POR EL CLIENTE");
                this.selectedsumi.setPdestino(0);
                this.selectedsumi.setPorigen(0);
                this.selectedsumi.setPrecio(0.0);
                this.selectedsumi.setRemision("N/S");
                this.selectedsumi.setTipo("Suministro");
                this.selectedsumi.setTotal(sumi.get(i).getMonto());
                this.sumiclis.add(this.selectedsumi);
                this.selectedsumi = new sumicli();
            }
            this.contaflete = this.sumiclis.size();
            this.pesoD = this.sumarDestino(this.sumiclis);
            this.pesoO = this.sumarOrigen(this.sumiclis);
            this.dif = this.pesoO - this.pesoD;
            this.ganancia = this.sumarGanancia(this.sumiclis);
            this.selectedPago.setMontoTotal(this.sumarTotal(this.sumiclis));
            this.bruto = 0.0;
            this.saldocobrar = 0.0;
            this.descuento = 0.0;
            this.adelanto = 0.0;
            this.suministro = 0.0;
            this.selectedPago.setMontoTotal(0.0);
            //        FacesMessage message;
            //        this.detpagovi = new ArrayList<DetPagoFlete>();
            //        if(this.selectedViajesAux.size()>0){
            //            msg= "Se cargaron los fletes pendientes de Cobro.";            
            //            message = new FacesMessage(FacesMessage.SEVERITY_INFO,msg," ");
            //            return msg;
            //        }else{
            //            msg= "Cliente Seleccionado sin Fletes pendientes de cobro.";            
            //            message = new FacesMessage(FacesMessage.SEVERITY_INFO,msg," ");
            //            return msg;
            //        }    
            return msg;
        } else {
            String msg = "";
            ViajeDao viajeDao = new ViajeDaoImpl();
            this.selectedsumi = new sumicli();
            this.sumiclis2 = new ArrayList<sumicli>();
            this.sumiclis = new ArrayList<sumicli>();
            this.viajes = viajeDao.findpen(this.selectedCliente);
            this.selectedViajesAux = new ArrayList<Viaje>();
            Double pagoaux = 0.0, precioaux = 0.0;
            for (int i = 0; i < this.viajes.size(); i++) {
                this.selectedsumi.setId(this.viajes.get(i).getIdViaje());
                this.selectedsumi.setBruto(this.viajes.get(i).getMontoBruto());
                this.selectedsumi.setChapa(this.viajes.get(i).getOrdenDeCarga().getCamion().getChapaCamion());
                this.selectedsumi.setBruto(this.viajes.get(i).getMontoBruto());
                this.selectedsumi.setChofer(this.viajes.get(i).getChofer().getNombre());
                this.selectedsumi.setDescrip(this.viajes.get(i).getDescripcion());
                this.selectedsumi.setDescuento(this.viajes.get(i).getMontofaltante());
                this.selectedsumi.setDestino(this.viajes.get(i).getOrdenDeCarga().getUnidadDestino().getNombre());
                this.selectedsumi.setFecha(this.viajes.get(i).getFechaDestino());
                this.selectedsumi.setMonto(this.viajes.get(i).getMontoBruto());
                this.selectedsumi.setOrigen(this.viajes.get(i).getOrdenDeCarga().getUnidadOrigen().getNombre());
                this.selectedsumi.setPdestino(this.viajes.get(i).getPesoDestino());
                this.selectedsumi.setPorigen(this.viajes.get(i).getPesoOrigen());
                this.selectedsumi.setPrecio(this.viajes.get(i).getPrecioCobro());
                this.selectedsumi.setRemision(this.viajes.get(i).getRemisionDestino());
                this.selectedsumi.setTipo("Flete");
                this.selectedsumi.setTotal(this.viajes.get(i).getMontoBruto() - this.viajes.get(i).getMontofaltante());
                precioaux = this.viajes.get(i).getPrecioPago();
                pagoaux = precioaux * this.viajes.get(i).getPesoDestino();
                this.selectedsumi.setPagar(pagoaux);
                this.sumiclis.add(this.selectedsumi);
                this.selectedsumi = new sumicli();
            }
            List<AdeCliente> adelantos;
            AdeClienteDao ade = new AdeClienteDaoImpl();
            adelantos = ade.proveedor(this.selectedCliente);
            for (int i = 0; i < adelantos.size(); i++) {
                this.selectedsumi.setId(adelantos.get(i).getIdAdeCliente());
                this.selectedsumi.setBruto(adelantos.get(i).getMonto());
                this.selectedsumi.setChapa("N/S");
                this.selectedsumi.setChofer("N/S");
                this.selectedsumi.setDescrip(adelantos.get(i).getDescrip());
                this.selectedsumi.setDescuento(0.0);
                this.selectedsumi.setDestino(adelantos.get(i).getDescrip());
                this.selectedsumi.setFecha(adelantos.get(i).getFecha());
                this.selectedsumi.setMonto(adelantos.get(i).getMonto());
                this.selectedsumi.setOrigen("ADELANTO ENTREGADO POR EL CLIENTE");
                this.selectedsumi.setPdestino(0);
                this.selectedsumi.setPorigen(0);
                this.selectedsumi.setPrecio(0.0);
                this.selectedsumi.setRemision("N/S");
                this.selectedsumi.setTipo("Adelanto");
                this.selectedsumi.setTotal(adelantos.get(i).getMonto());
                this.sumiclis.add(this.selectedsumi);
                this.selectedsumi = new sumicli();
            }
            List<SumiCliente> sumi;
            SumiCliDao ades = new SumiCliDaoImpl();
            sumi = ades.proveedor(this.selectedCliente);
            System.out.print("LOS SUMINISTROS QUE SELECCIONA");
            System.out.print(sumi.size());
            for (int i = 0; i < sumi.size(); i++) {
                System.out.print("entra aca");
                this.selectedsumi.setId(sumi.get(i).getIdSumiCli());
                this.selectedsumi.setBruto(sumi.get(i).getMonto());
                this.selectedsumi.setChapa(sumi.get(i).getCamion().getChapaCamion());
                this.selectedsumi.setChofer(sumi.get(i).getChofer().getNombre());
                this.selectedsumi.setDescrip(sumi.get(i).getDescrip());
                this.selectedsumi.setDescuento(0.0);
                this.selectedsumi.setDestino(sumi.get(i).getDescrip());
                this.selectedsumi.setFecha(sumi.get(i).getFecha());
                this.selectedsumi.setMonto(sumi.get(i).getMonto());
                this.selectedsumi.setOrigen("SUMINISTRO ENTREGADO POR EL CLIENTE");
                this.selectedsumi.setPdestino(0);
                this.selectedsumi.setPorigen(0);
                this.selectedsumi.setPrecio(0.0);
                this.selectedsumi.setRemision("N/S");
                this.selectedsumi.setTipo("Suministro");
                this.selectedsumi.setTotal(sumi.get(i).getMonto());
                this.sumiclis.add(this.selectedsumi);
                this.selectedsumi = new sumicli();
            }
            this.contaflete = this.sumiclis.size();
            this.pesoD = this.sumarDestino(this.sumiclis);
            this.pesoO = this.sumarOrigen(this.sumiclis);
            this.dif = this.pesoO - this.pesoD;
            this.ganancia = this.sumarGanancia(this.sumiclis);
            this.selectedPago.setMontoTotal(this.sumarTotal(this.sumiclis));
            this.bruto = 0.0;
            this.saldocobrar = 0.0;
            this.descuento = 0.0;
            this.adelanto = 0.0;
            this.suministro = 0.0;
            this.selectedPago.setMontoTotal(0.0);
            //        FacesMessage message;
            //        this.detpagovi = new ArrayList<DetPagoFlete>();
            //        if(this.selectedViajesAux.size()>0){
            //            msg= "Se cargaron los fletes pendientes de Cobro.";            
            //            message = new FacesMessage(FacesMessage.SEVERITY_INFO,msg," ");
            //            return msg;
            //        }else{
            //            msg= "Cliente Seleccionado sin Fletes pendientes de cobro.";            
            //            message = new FacesMessage(FacesMessage.SEVERITY_INFO,msg," ");
            //            return msg;
            //        }    
            return msg;
        }
    }

    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/pagovilist.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getPagos()));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=cobro_de_flete.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void exportarPDF2(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException {
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/pagovi.jasper"));
        codpago = this.selectedPago1.getIdPagoFlete();
        Class.forName("org.postgresql.Driver");
        conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/transpbase", "postgres", "macorittogo");
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("vCodpago", codpago);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=pago_proveedor_varios.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void exportarPDF3(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException {
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/pagovi.jasper"));
        codpago = this.selectedPago.getIdPagoFlete();
        Class.forName("org.postgresql.Driver");
        conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/transpbase", "postgres", "macorittogo");
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("vCodpago", codpago);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=pago_proveedor_varios.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public String btnCargar(ActionEvent event) {
        String msg;
        for (int i = 0; i < this.selectedViajes.size(); i++) {
            this.selectedDetpagovi.setViaje(this.selectedViajes.get(i));
            this.selectedDetpagovi.setMonto(this.selectedViajes.get(i).getMontoCobrar());
            this.selectedDetpagovi.setDescuento(this.selectedViajes.get(i).getMontofaltante());
            this.selectedDetpagovi.setTotalMonto(this.selectedViajes.get(i).getMontoCobrar());
            detpagovi.add(this.selectedDetpagovi);
            this.selectedDetpagovi = new DetPagoFlete();
//            this.selectedPago.setMontoTotal(this.sumarTotal(detpagovi));
//            this.bruto = this.sumarBruto(detpagovi);
//            this.descuento = this.sumarDescuento(detpagovi);
            selectedViajesAux.remove(this.selectedViajes.get(i));
            this.contaflete = this.detpagovi.size();
//            this.pesoD = this.sumarDestino(detpagovi);
//            this.pesoO = this.sumarOrigen(detpagovi);
//            this.dif = this.pesoO-this.pesoD;
//            this.ganancia = this.sumarGanancia(detpagovi);
        }
        msg = "Se Creo correctamente el registro";
        return msg;
    }

    public void bntDescargar(sumicli sumicli) {
        this.sumiclis.add(sumicli);
        this.sumiclis2.remove(sumicli);
        this.contaflete = this.sumiclis2.size();
        this.pesoD = this.sumarDestino(this.sumiclis2);
        this.pesoO = this.sumarOrigen(this.sumiclis2);
        this.dif = this.pesoO - this.pesoD;
        this.ganancia = this.sumarGanancia(this.sumiclis2);
        this.selectedPago.setMontoTotal(this.sumarBruto(this.sumiclis2));
    }

    public void btnCreatePago() {
        this.selectedPago.setIvacom(iva);
        this.selectedPago.setRetencion(retencion.toString());
        PagoviDao pagoDao = new PagoviDaoImpl();
        String msg;
        FacesMessage message;
        //this.selectedPago.setMonto(0);
        this.selectedPago.setMontoIva(0);
        this.selectedPago.setNroLiq(this.NroLiq);
        this.selectedPago.setMontoIva(0);
        this.selectedPago.setDescripcion(this.Descrip);
        this.selectedPago.setEstado("Pagado");
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
        this.selectedPago.setUsuario(usuario);
        ViajeDao sumi = new ViajeDaoImpl();
        Viaje selectedviajeaux = new Viaje();
        this.viajes = new ArrayList<Viaje>();
        List<DetPagoFlete> detfletes = new ArrayList<DetPagoFlete>();
        DetPagoFlete selectedDetFlete = new DetPagoFlete();
        SumiCliDao sumi2 = new SumiCliDaoImpl();
        DetPagoSumiDao sumi3 = new DetPagoSumiDaoImpl();
        List<DetPagoSumi> detSumi = new ArrayList<DetPagoSumi>();
        DetPagoSumi selectedDetSumi = new DetPagoSumi();
        List<SumiCliente> suministros = new ArrayList<SumiCliente>();
        SumiCliente selectedSuministro = new SumiCliente();
        AdeClienteDao ade = new AdeClienteDaoImpl();
        DetPagoAdeDao ade2 = new DetPagoAdeDaoImpl();
        List<DetPagoAde> detAde = new ArrayList<DetPagoAde>();
        DetPagoAde selectedDetAde = new DetPagoAde();
        List<AdeCliente> adelantos = new ArrayList<AdeCliente>();
        AdeCliente selectedAdelanto = new AdeCliente();
        for (int i = 0; i < this.sumiclis2.size(); i++) {
            if (this.sumiclis2.get(i).getTipo().equals("Flete")) {
                this.viajes = sumi.findOne(this.sumiclis2.get(i).getId());
                for (int e = 0; e < this.viajes.size(); e++) {
                    selectedviajeaux = this.viajes.get(e);
                }
                selectedDetFlete.setMonto(selectedviajeaux.getMontoBruto());
                selectedDetFlete.setTotalMonto(selectedviajeaux.getMontoBruto());
                selectedDetFlete.setViaje(selectedviajeaux);
                detfletes.add(selectedDetFlete);
                selectedviajeaux = new Viaje();
                selectedDetFlete = new DetPagoFlete();
            } else {
                if (this.sumiclis2.get(i).getTipo().equals("Suministro")) {
                    suministros = sumi2.findOne(this.sumiclis2.get(i).getId());
                    for (int e = 0; e < suministros.size(); e++) {
                        selectedSuministro = suministros.get(e);
                    }
                    selectedDetSumi.setMonto(selectedSuministro.getMonto());
                    selectedDetSumi.setDescuento(selectedSuministro.getMonto());
                    selectedDetSumi.setTotalMonto(selectedSuministro.getMonto());
                    selectedDetSumi.setSumiCliente(selectedSuministro);
                    selectedDetSumi.setPagoFlete(this.selectedPago);
                    detSumi.add(selectedDetSumi);
                    selectedDetSumi = new DetPagoSumi();
                    selectedSuministro = new SumiCliente();
                } else {
                    if (this.sumiclis2.get(i).getTipo().equals("Adelanto")) {
                        adelantos = ade.findOne(this.sumiclis2.get(i).getId());
                        for (int e = 0; e < adelantos.size(); e++) {
                            selectedAdelanto = adelantos.get(e);
                        }
                        selectedDetAde.setMonto(selectedAdelanto.getMonto());
                        selectedDetAde.setDescuento(selectedAdelanto.getMonto());
                        selectedDetAde.setTotalMonto(selectedAdelanto.getMonto());
                        selectedDetAde.setAdeCliente(selectedAdelanto);
                        selectedDetAde.setPagoFlete(this.selectedPago);
                        detAde.add(selectedDetAde);
                        selectedDetAde = new DetPagoAde();
                        selectedAdelanto = new AdeCliente();
                    }
                }

            }

        }
        System.out.print(" contadores de los arrays   ");
        System.out.print(detAde.size());
        System.out.print(detSumi.size());
        if (pagoDao.create(this.selectedPago, detfletes)) {
            for (int i = 0; i < detfletes.size(); i++) {
                this.selectedViaje = detfletes.get(i).getViaje();
                this.selectedViaje.setEstadoCobro("Pagado");
                ViajeDao viajeDao = new ViajeDaoImpl();
                if (viajeDao.update(this.selectedViaje)) {
                }
            }
            for (int i = 0; i < detSumi.size(); i++) {
                selectedSuministro = detSumi.get(i).getSumiCliente();
                if (sumi3.create(detSumi.get(i))) {
                    System.out.print("aca creaa");
                    selectedSuministro.setEstadoCobro("Pagado");
                    if (sumi2.update(selectedSuministro)) {
                    }
                }

            }
            for (int i = 0; i < detAde.size(); i++) {
                selectedAdelanto = detAde.get(i).getAdeCliente();
                ade2.create(detAde.get(i));
                selectedAdelanto.setEstadoCobro("Pagado");
                if (ade.update(selectedAdelanto)) {
                }
            }
            msg = "Se Creo correctamente el registro, dar click en PDF.";
            this.selectedPago1 = this.selectedPago;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, " ");
            this.selectedPago = new PagoFlete();
            this.viajes = new ArrayList<Viaje>();
            this.selectedCliente = new Cliente();
            this.detpagovi = new ArrayList<DetPagoFlete>();
            this.detpagoviAux = new ArrayList<DetPagoFlete>();
            this.selectedDetpagovi = new DetPagoFlete();
            this.selectedDetpagoviAux = new DetPagoFlete();
            this.selectedViaje = new Viaje();
            this.selectedPago.setFecha(new Date());
            this.selectedViajes = new ArrayList<Viaje>();
            this.selectedViajesAux = new ArrayList<Viaje>();

        } else {
            msg = "Error al Crear el registro";
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onRowSelectCliente(SelectEvent event) {
        this.selectedPago.setCliente(this.selectedCliente);
    }

    public void cargarNro(ActionEvent event) {
        this.NroLiq = this.selectedPago.getNroLiq();
    }

    public void ivaselect(ActionEvent event) {
        if (this.iva.equals("SI")) {
            this.reteboo = false;
            this.cargarboo = true;
        } else {
            this.reteboo = true;
            this.cargarboo = false;
        }
    }

    public void retencionselect(ActionEvent event) {
        this.cargarboo = false;
    }

    public void cargarDescrip(ActionEvent event) {
        this.Descrip = this.selectedPago.getDescripcion();
    }

    public void onRowSelectPago(SelectEvent event) {
    }

    public void btnUpdatePago(ActionEvent actionEvent) {
        PagoviDao pagoDao = new PagoviDaoImpl();
        Integer bande;
        String msg;
        String est;
        String a;
        a = "Anulado";
        System.out.print("Este es el estado");
        System.out.print(this.selectedPago.getEstado());
        DetpagoviDao detpagoDao = new DetpagoviDaoImpl();
        DetPagoSumiDao sumidet = new DetPagoSumiDaoImpl();
        List<DetPagoSumi> suministros = new ArrayList<DetPagoSumi>();
        suministros = sumidet.findByPagov(this.selectedPago);
        SumiCliente seleccionarsu = new SumiCliente();
        DetPagoAdeDao adedet = new DetPagoAdeDaoImpl();
        List<DetPagoAde> adelantos = new ArrayList<DetPagoAde>();
        adelantos = adedet.findByPagov(this.selectedPago);
        AdeCliente seleccionarade = new AdeCliente();
        this.detpagoviAux = detpagoDao.findByPagovi(this.selectedPago);
        if (a.equals(this.selectedPago.getEstado())) {
            bande = 1;
        } else {
            bande = 0;
        }
        //System.out.println(bande);
        //System.out.println(this.selectedPago.getDescripcion());
        //System.out.println(this.selectedPago.getEstado());
        if (pagoDao.update(this.selectedPago)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, " ");
            FacesContext.getCurrentInstance().addMessage(null, message);
            if (bande == 1) {
                for (int i = 0; i < detpagoviAux.size(); i++) {
                    this.selectedViaje = detpagoviAux.get(i).getViaje();
                    this.selectedViaje.setEstadoCobro("Pendiente");
                    ViajeDao viaDao = new ViajeDaoImpl();
                    if (viaDao.update(this.selectedViaje)) {
                    }
                }
                for (int i = 0; i < suministros.size(); i++) {
                    seleccionarsu = suministros.get(i).getSumiCliente();
                    seleccionarsu.setEstadoCobro("Pendiente");
                    SumiCliDao sumiDao = new SumiCliDaoImpl();
                    if (sumiDao.update(seleccionarsu)) {
                        //sumidet.delete(seleccionarsu.getIdSumiCli());
                    }
                }
                for (int i = 0; i < adelantos.size(); i++) {
                    seleccionarade = adelantos.get(i).getAdeCliente();
                    seleccionarade.setEstadoCobro("Pendiente");
                    AdeClienteDao sumiDao = new AdeClienteDaoImpl();
                    if (sumiDao.update(seleccionarade)) {
                        //adedet.delete(seleccionarade.getIdAdeCliente());
                    }
                }
            } else {
                for (int i = 0; i < detpagoviAux.size(); i++) {
                    this.selectedViaje = detpagoviAux.get(i).getViaje();
                    this.selectedViaje.setEstadoCobro("Pagado");
                    ViajeDao viaDao = new ViajeDaoImpl();
                    if (viaDao.update(this.selectedViaje)) {
                    }
                }
                for (int i = 0; i < suministros.size(); i++) {
                    seleccionarsu = suministros.get(i).getSumiCliente();
                    seleccionarsu.setEstadoCobro("Pagado");
                    SumiCliDao sumiDao = new SumiCliDaoImpl();
                    if (sumiDao.update(seleccionarsu)) {
                        //sumidet.delete(seleccionarsu.getIdSumiCli());
                    }
                }
                for (int i = 0; i < adelantos.size(); i++) {
                    seleccionarade = adelantos.get(i).getAdeCliente();
                    seleccionarade.setEstadoCobro("Pagado");
                    AdeClienteDao sumiDao = new AdeClienteDaoImpl();
                    if (sumiDao.update(seleccionarade)) {
                        //adedet.delete(seleccionarade.getIdAdeCliente());
                    }
                }
            }
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, " ");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeletePago(ActionEvent actionEvent) {
        PagoviDao pagoDao = new PagoviDaoImpl();
        String msg;
        FacesMessage message;
        DetpagoviDao detpagoDao = new DetpagoviDaoImpl();
        DetPagoSumiDao sumidet = new DetPagoSumiDaoImpl();
        List<DetPagoSumi> suministros = new ArrayList<DetPagoSumi>();
        suministros = sumidet.findByPagov(this.selectedPago);
        SumiCliente seleccionarsu = new SumiCliente();
        DetPagoAdeDao adedet = new DetPagoAdeDaoImpl();
        List<DetPagoAde> adelantos = new ArrayList<DetPagoAde>();
        adelantos = adedet.findByPagov(this.selectedPago);
        AdeCliente seleccionarade = new AdeCliente();
        this.detpagoviAux = detpagoDao.findByPagovi(this.selectedPago);
        for (int i = 0; i < suministros.size(); i++) {
            seleccionarsu = suministros.get(i).getSumiCliente();
            seleccionarsu.setEstadoCobro("Pendiente");
            SumiCliDao sumiDao = new SumiCliDaoImpl();
            if (sumiDao.update(seleccionarsu)) {
                //sumidet.delete(seleccionarsu.getIdSumiCli());
            }
        }
        for (int i = 0; i < adelantos.size(); i++) {
            seleccionarade = adelantos.get(i).getAdeCliente();
            seleccionarade.setEstadoCobro("Pendiente");
            AdeClienteDao sumiDao = new AdeClienteDaoImpl();
            if (sumiDao.update(seleccionarade)) {
                //adedet.delete(seleccionarade.getIdAdeCliente());
            }
        }
        if (pagoDao.delete(this.selectedPago)) {
            msg = "Se Elimino correctamente el registro";
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, " ");
            for (int i = 0; i < detpagoviAux.size(); i++) {
                this.selectedViaje = detpagoviAux.get(i).getViaje();
                this.selectedViaje.setEstadoCobro("Pendiente");
                ViajeDao viajeDao = new ViajeDaoImpl();
                if (viajeDao.update(this.selectedViaje)) {
                }
            }

        } else {
            msg = "Error al Eliminar el registro";
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, " ");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void calcularTotal() {
        //this.selectedDetComb.setMonto(this.selectedVale.getMonto());
//        this.selectedPago.setMontoTotal(this.sumarTotal(detpagovi));
        //System.out.println(this.selectedDetventa.getCantidad());
    }

    private Double sumarTotal(List<sumicli> detpagovi) {
        Double suma = 0.0;
        for (int i = 0; i < detpagovi.size(); i++) {
            if (detpagovi.get(i).getTipo().equals("Flete")) {
                suma = suma + detpagovi.get(i).getMonto();
            }

        }
        suma = suma - this.descuento - this.adelanto - this.suministro;
        return suma;
    }

    private Double sumarBruto(List<sumicli> detpagovi) {
        Double suma = 0.0, sumaa = 0.0, sums = 0.0, resto = 0.0, desc = 0.0, aux = 0.0;
        Double auxrete = 0.0, auxrete2 = 0.0;
        if (iva.equals("SI")) {
            for (int i = 0; i < detpagovi.size(); i++) {
                if (detpagovi.get(i).getTipo().equals("Adelanto")) {
                    sumaa = sumaa + detpagovi.get(i).getMonto();
                } else {
                    if (detpagovi.get(i).getTipo().equals("Suministro")) {
                        sums = sums + detpagovi.get(i).getMonto();
                    } else {
                        suma = suma + detpagovi.get(i).getBruto();
                        desc = desc + detpagovi.get(i).getDescuento();
                    }
                }

            }
            this.adelanto = sumaa;
            this.suministro = sums;
            aux = sumaa + sums + desc;
            resto = suma - aux;
            auxrete = (suma / 11);
            auxrete2 = auxrete * this.retencion;
            this.saldocobrar = suma - auxrete2;
            //this.saldocobrar=
            this.bruto = suma;
            this.descuento = desc;
            suma = this.saldocobrar - aux;
        } else {
            for (int i = 0; i < detpagovi.size(); i++) {
                if (detpagovi.get(i).getTipo().equals("Adelanto")) {
                    sumaa = sumaa + detpagovi.get(i).getMonto();
                } else {
                    if (detpagovi.get(i).getTipo().equals("Suministro")) {
                        sums = sums + detpagovi.get(i).getMonto();
                    } else {
                        suma = suma + detpagovi.get(i).getBruto();
                        desc = desc + detpagovi.get(i).getDescuento();
                    }
                }

            }
            this.adelanto = sumaa;
            this.suministro = sums;
            aux = sumaa + sums + desc;
            resto = suma - aux;
            auxrete = (suma / 11);
            auxrete2 = auxrete * this.retencion;
            this.saldocobrar = suma;
            //this.saldocobrar=
            this.bruto = suma;
            this.descuento = desc;
            suma = resto;
        }
        return suma;
    }

    private Double sumarBrutoUp(List<sumicli> detpagovi) {
        Double suma = 0.0, sumaa = 0.0, sums = 0.0, resto = 0.0, desc = 0.0, aux = 0.0;
        Double auxrete = 0.0, auxrete2 = 0.0;
        if (this.selectedPago.getRetencion().equals("0.7")) {
            this.retencion = 0.7;
            System.out.print("asigno a la retencion como 0.7");
        } else {
            if (this.selectedPago.getRetencion().equals("0.5")) {
                this.retencion = 0.5;
            } else {
                this.retencion = 0.0;
            }
        }
        if (this.selectedPago.getIvacom().equals("SI")) {
            for (int i = 0; i < detpagovi.size(); i++) {
                if (detpagovi.get(i).getTipo().equals("Adelanto")) {
                    sumaa = sumaa + detpagovi.get(i).getMonto();
                } else {
                    if (detpagovi.get(i).getTipo().equals("Suministro")) {
                        sums = sums + detpagovi.get(i).getMonto();
                    } else {
                        suma = suma + detpagovi.get(i).getBruto();
                        desc = desc + detpagovi.get(i).getDescuento();
                    }
                }

            }
            this.adelanto = sumaa;
            this.suministro = sums;
            aux = sumaa + sums + desc;
            resto = suma - aux;
            auxrete = (suma / 11);
            auxrete2 = auxrete * this.retencion;
            System.out.print("valor de aux rete");
            System.out.print(auxrete2);
            this.saldocobrar = suma - auxrete2;
            //this.saldocobrar=
            this.bruto = suma;
            this.descuento = desc;
            suma = this.saldocobrar - aux;
        } else {
            for (int i = 0; i < detpagovi.size(); i++) {
                if (detpagovi.get(i).getTipo().equals("Adelanto")) {
                    sumaa = sumaa + detpagovi.get(i).getMonto();
                } else {
                    if (detpagovi.get(i).getTipo().equals("Suministro")) {
                        sums = sums + detpagovi.get(i).getMonto();
                    } else {
                        suma = suma + detpagovi.get(i).getBruto();
                        desc = desc + detpagovi.get(i).getDescuento();
                    }
                }

            }
            this.adelanto = sumaa;
            this.suministro = sums;
            aux = sumaa + sums + desc;
            resto = suma - aux;
            auxrete = (suma / 11);
            auxrete2 = auxrete * this.retencion;
            this.saldocobrar = suma;
            //this.saldocobrar=
            this.bruto = suma;
            this.descuento = desc;
            suma = resto;
        }
        return suma;
    }

    private Double sumarDescuento(List<sumicli> detpagovi) {
        Double suma = 0.0;
        for (int i = 0; i < detpagovi.size(); i++) {
            suma = suma + detpagovi.get(i).getDescuento();
        }
        return suma;
    }

    private Double sumarTotalPago(List<PagoFlete> pagos) {
        Double suma = 0.0;
        for (int i = 0; i < pagos.size(); i++) {
            suma = suma + pagos.get(i).getMontoTotal();
        }
        return suma;
    }

    public void btnIniciar(ActionEvent event) {
        this.pagos = new ArrayList<PagoFlete>();
        this.selectedPago = new PagoFlete();
        this.viajes = new ArrayList<Viaje>();
        this.selectedCliente = new Cliente();
        this.usuario = new Usuario();
        this.detpagovi = new ArrayList<DetPagoFlete>();
        this.detpagoviAux = new ArrayList<DetPagoFlete>();
        this.selectedDetpagovi = new DetPagoFlete();
        this.selectedDetpagoviAux = new DetPagoFlete();
        this.selectedViaje = new Viaje();
        this.selectedPago.setFecha(new Date());
        this.selectedViajes = new ArrayList<Viaje>();
        this.selectedViajesAux = new ArrayList<Viaje>();
        this.Descrip = new String();
        this.NroLiq = new String();
        this.selectedsumi = new sumicli();
        this.sumiclis = new ArrayList<sumicli>();
        this.sumiclis2 = new ArrayList<sumicli>();
        this.bruto = 0.0;
        this.descuento = 0.0;
        this.pesoD = 0;
        this.pesoO = 0;
    }

    public void btnCheck(ActionEvent event) {
        String a;
        a = this.selectedPago.getEstado();
        if (this.selectedPago.getEstado() == a) {
            this.chkBoxChecked = false;
        } else {
            this.chkBoxChecked = true;
        }
    }

    private Integer sumarOrigen(List<sumicli> detPagoAux) {
        Integer suma = 0;
        for (int i = 0; i < detPagoAux.size(); i++) {

            suma = suma + detPagoAux.get(i).getPorigen();
        }
        return suma;
    }

    private Integer sumarDestino(List<sumicli> detPagoAux) {
        Integer suma = 0;
        for (int i = 0; i < detPagoAux.size(); i++) {

            suma = suma + detPagoAux.get(i).getPdestino();
        }
        return suma;
    }

    private Double sumarGanancia(List<sumicli> detPagoAux) {
        Double suma = 0.0, auxrete = 0.0, auxrete2 = 0.0, auxiva = 0.0;
        if (iva.equals("NO")) {
            Double diferencia = 0.0;
            Double aux = 0.0;
            System.out.print("ESTE ES LO QUE VA A RESTAR SIN IVA");
            for (int i = 0; i < detPagoAux.size(); i++) {
                if (detPagoAux.get(i).getTipo().equals("Flete")) {
                    System.out.print(detPagoAux.get(i).getBruto());
                    System.out.print(detPagoAux.get(i).getPagar());
                    diferencia = detPagoAux.get(i).getBruto() - detPagoAux.get(i).getPagar();
                    suma = suma + diferencia;
                }
            }
        } else {
            Double diferencia = 0.0;
            Double aux = 0.0;
            System.out.print("ESTE ES LO QUE VA A RESTAR CON IVA");
            for (int i = 0; i < detPagoAux.size(); i++) {
                if (detPagoAux.get(i).getTipo().equals("Flete")) {
                    System.out.print(detPagoAux.get(i).getBruto());
                    System.out.print(detPagoAux.get(i).getPagar());
                    auxrete = (detPagoAux.get(i).getBruto() / 11);
                    auxrete2 = auxrete * this.retencion;
                    auxiva = detPagoAux.get(i).getBruto() - auxrete2;
                    diferencia = auxiva - detPagoAux.get(i).getPagar();
                    suma = suma + diferencia;
                }
            }
        }

        return suma;
    }

    private Double sumarGananciaUp(List<sumicli> detPagoAux) {
        Double suma = 0.0, auxrete = 0.0, auxrete2 = 0.0, auxiva = 0.0;
        System.out.print("ESTA ES LA RETENCION");
        System.out.print(this.selectedPago.getRetencion());
        if (this.selectedPago.getRetencion().equals("0.7")) {
            this.retencion = 0.7;
        } else {
            if (this.selectedPago.getRetencion().equals("0.5")) {
                this.retencion = 0.5;
            } else {
                this.retencion = 0.0;
            }
        }
        if (this.selectedPago.getIvacom().equals("NO")) {
            Double diferencia = 0.0;
            Double aux = 0.0;
            System.out.print("ESTE ES LO QUE VA A RESTAR SIN IVA");
            for (int i = 0; i < detPagoAux.size(); i++) {
                if (detPagoAux.get(i).getTipo().equals("Flete")) {
                    System.out.print(detPagoAux.get(i).getBruto());
                    System.out.print(detPagoAux.get(i).getPagar());
                    diferencia = detPagoAux.get(i).getBruto() - detPagoAux.get(i).getPagar();
                    suma = suma + diferencia;
                }
            }
        } else {
            Double diferencia = 0.0;
            Double aux = 0.0;
            System.out.print("ESTE ES LO QUE VA A RESTAR CON IVA");
            for (int i = 0; i < detPagoAux.size(); i++) {
                if (detPagoAux.get(i).getTipo().equals("Flete")) {
                    System.out.print(detPagoAux.get(i).getBruto());
                    System.out.print(detPagoAux.get(i).getPagar());
                    auxrete = (detPagoAux.get(i).getBruto() / 11);
                    auxrete2 = auxrete * this.retencion;
                    auxiva = detPagoAux.get(i).getBruto() - auxrete2;
                    diferencia = auxiva - detPagoAux.get(i).getPagar();
                    suma = suma + diferencia;
                }
            }
        }

        return suma;
    }

    public void bntAdd(sumicli sumicli) {
        this.sumiclis2.add(sumicli);
        this.sumiclis.remove(sumicli);
        this.contaflete = this.sumiclis2.size();
        this.pesoD = this.sumarDestino(this.sumiclis2);
        this.pesoO = this.sumarOrigen(this.sumiclis2);
        this.contaflete = this.sumiclis2.size();
        this.dif = this.pesoO - this.pesoD;
        this.selectedPago.setMontoTotal(this.sumarBruto(this.sumiclis2));
        this.ganancia = this.sumarGanancia(this.sumiclis2);
    }
}
