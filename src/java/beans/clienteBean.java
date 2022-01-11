/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ClienteDao;
import dao.ClienteDaoImpl;
import dao.OrdenDeCargaDao;
import dao.OrdenDeCargaDaoImpl;
import dao.PagoviDao;
import dao.PagoviDaoImpl;
import dao.SeguroDao;
import dao.SeguroDaoImpl;
import dao.ToleranciaDao;
import dao.ToleranciaDaoImpl;
import dao.ViajeDao;
import dao.ViajeDaoImpl;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.OrdenDeCarga;
import model.PagoFlete;
import model.Seguro;
import model.Tolerancia;
import model.Viaje;
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
@ViewScoped
public class clienteBean {
    private List<Cliente> clientes;
    private List<Tolerancia> tolerancias;
    private List<PagoFlete> pagospen;
    private Cliente selectedCliente;
    private Object clienteDao;
    private Tolerancia selectedTolerancia;
    private List<Seguro> seguros;
    private Seguro selectedSeguro;
    private List<SelectItem> selectOneItemsCliente;
    private List<OrdenDeCarga> ordenes;
    private List<Viaje> fletes;
    private Viaje selectedFlete;
    private Double total;
    private OrdenDeCarga selectedOrden;
    private Double totalpago;
    public clienteBean() {
        this.clientes = new ArrayList<Cliente>();
        this.selectedCliente = new Cliente();
        this.tolerancias = new ArrayList<Tolerancia>();
        this.selectedTolerancia = new Tolerancia();
        this.seguros = new ArrayList<Seguro>();
        this.selectedSeguro = new Seguro();
        this.ordenes = new ArrayList<OrdenDeCarga>();
        this.fletes = new ArrayList<Viaje>();
        this.selectedFlete = new Viaje();
        this.selectedOrden = new OrdenDeCarga();
        this.pagospen = new ArrayList<PagoFlete>();
    }
    public List<Cliente> getClientes() {
        ClienteDao clienteDao = new ClienteDaoImpl();
        this.clientes = clienteDao.findAll();
        return clientes;
    }

    public List<PagoFlete> getPagospen() {
        PagoviDao pago = new PagoviDaoImpl();
        this.pagospen = pago.findCliente(this.selectedCliente);
        this.totalpago = this.sumarTotalPagoscli(this.pagospen);
        return pagospen;
    }

    public Double getTotalpago() {
        return totalpago;
    }
    
    public Double getTotal() {
        return total;
    }

    public OrdenDeCarga getSelectedOrden() {
        return selectedOrden;
    }

    public void setSelectedOrden(OrdenDeCarga selectedOrden) {
        this.selectedOrden = selectedOrden;
    }

   
    
    public List<Viaje> getFletes() {
        ViajeDao viaje = new ViajeDaoImpl();
        this.fletes = viaje.findpen(this.selectedCliente);
        this.total = this.sumarTotalPago(fletes);
        return fletes;
    }

    public Viaje getSelectedFlete() {
        return selectedFlete;
    }
     public void setSelectedFlete(Viaje selectedFlete) {
        this.selectedFlete = selectedFlete;
    }
    

    public Seguro getSelectedSeguro() {
        return selectedSeguro;
    }

    public void setSelectedSeguro(Seguro selectedSeguro) {
        this.selectedSeguro = selectedSeguro;
    }

    public List<OrdenDeCarga> getOrdenes() {
        OrdenDeCargaDao orden = new OrdenDeCargaDaoImpl();
        this.ordenes = orden.findCliente(this.selectedCliente);
        return ordenes;
    }
    public void exportarPDForden(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/ordenlist.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getOrdenes()));
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=reporte_ordenes.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
    public void exportarPDFflete(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/fletes.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getFletes()));
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=fletes_pendientes.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }

    public List<Seguro> getSeguros() {
        SeguroDao seguroDao = new SeguroDaoImpl();
        this.seguros = seguroDao.findAll();
        return seguros;
    }
    
    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/clilist.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getClientes()));
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=clientes.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
    public Cliente getSelectedCliente() {
        return selectedCliente;
    }

    public Tolerancia getSelectedTolerancia() {
        return selectedTolerancia;
    }

    public void setSelectedTolerancia(Tolerancia selectedTolerancia) {
        this.selectedTolerancia = selectedTolerancia;
    }
     
    public List<Tolerancia> getTolerancias() {
        ToleranciaDao toleDao = new ToleranciaDaoImpl();
        this.tolerancias = toleDao.findAll();
        return tolerancias;
    }
    
    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }
    public void btnCreateCliente(ActionEvent actionEvent) {
        ClienteDao clienteDao = new ClienteDaoImpl();
        String msg;
        if (clienteDao.create(this.selectedCliente)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, " ");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedCliente = new Cliente();
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, " ");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    public void btnCreateTole(ActionEvent actionEvent) {
        ToleranciaDao toleDao = new ToleranciaDaoImpl();
        String msg;
        if (toleDao.create(this.selectedTolerancia)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedTolerancia = new Tolerancia();            
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    public void btnCreateSeguro(ActionEvent actionEvent) {
        SeguroDao seguroDao = new SeguroDaoImpl();
        String msg;
        if (seguroDao.create(this.selectedSeguro)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedSeguro = new Seguro();
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    public void btnUpdateCliente(ActionEvent actionEvent) {
        ClienteDao clienteDao = new ClienteDaoImpl();
        String msg;
        if (clienteDao.update(this.selectedCliente)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public void btnUpdateSeguro(ActionEvent actionEvent) {
        SeguroDao seguroDao = new SeguroDaoImpl();
        String msg;
        if (seguroDao.update(this.selectedSeguro)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public void btnUpdateTole(ActionEvent actionEvent) {
        ToleranciaDao toleDao = new ToleranciaDaoImpl();
        String msg;
        if (toleDao.update(this.selectedTolerancia)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteCliente(ActionEvent actionEvent) {
        ClienteDao clienteDao = new ClienteDaoImpl();
        String msg;
        if (clienteDao.delete(this.selectedCliente.getIdCliente())) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
}
    public void btnDeleteSeguro(ActionEvent actionEvent) {
        SeguroDao seguroDao = new SeguroDaoImpl();
        String msg;
        if (seguroDao.delete(this.selectedSeguro.getIdSeguro())) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
}
    public void btnDeleteDelete(ActionEvent actionEvent) {
        ToleranciaDao toleDao = new ToleranciaDaoImpl();
        String msg;
        if (toleDao.delete(this.selectedTolerancia.getIdTolerancia())) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
}
    public List<SelectItem> getSelectOneItemsCliente() {
        this.selectOneItemsCliente = new ArrayList<SelectItem>();
        ClienteDao clienteDao = new ClienteDaoImpl();
        List<Cliente> clientes = clienteDao.SelectItems();
        for (Cliente cliente : clientes) {
            SelectItem selecItem = new SelectItem(cliente.getIdCliente(), cliente.getNombre());
            this.selectOneItemsCliente.add(selecItem);
        }
        return selectOneItemsCliente;
    }
    public void onRowSelectCli(SelectEvent event) {        
    }
    private Double sumarTotalPago(List<Viaje> fletes){
    Double suma =0.0;
       for(int i = 0; i < fletes.size(); i++){
           suma = suma + fletes.get(i).getMontoCobrar();
       }
        return suma;
    }
    private Double sumarTotalPagoscli(List<PagoFlete> pagos){
    Double suma =0.0;
       for(int i = 0; i < pagos.size(); i++){
           suma = suma + pagos.get(i).getMontoTotal();
       }
        return suma;
    }
    public void onRowSelectTipo(SelectEvent event){
        this.selectedTolerancia.setCliente(this.selectedCliente);
    }
    public void onRowSelectSeguro(SelectEvent event){
        this.selectedSeguro.setCliente(this.selectedCliente);
    }
    public void btniniciar(ActionEvent actionEvent){
        this.selectedCliente= new Cliente();
        this.selectedTolerancia= new Tolerancia();
        this.selectedSeguro= new Seguro();
    }
}
