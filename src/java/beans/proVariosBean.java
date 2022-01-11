/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.PagovDao;
import dao.PagovDaoImpl;
import dao.ProVariosDao;
import dao.ProVariosDaoImpl;
import dao.ValeVariosDao;
import dao.ValeVariosDaoImpl;
import dao.VariosDao;
import dao.VariosDaoImpl;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.PagoVarios;
import model.ProveedorVarios;
import model.ValeVarios;
import model.Varios;
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
public class proVariosBean {

    private List<ProveedorVarios> proCombustiblesVarios;
    private ProveedorVarios selectedProveedorVarios;
    private Object proVariosDao;
    private List<ValeVarios> vales;
    private List<PagoVarios> pagos;
    private List<Varios> varios;
    private ValeVarios selectedVale;
    private Double total;
    private Double totalvarios;
    private Double totalpagos;
    public proVariosBean() {
        this.proCombustiblesVarios = new ArrayList<ProveedorVarios>();
        this.selectedProveedorVarios = new ProveedorVarios();
        this.vales = new ArrayList<ValeVarios>();
        this.pagos = new ArrayList<PagoVarios>();
        this.varios = new ArrayList<Varios>();
        this.selectedVale = new ValeVarios();
    }
     public List<ProveedorVarios> getProCombustiblesVarios() {
        ProVariosDao proVariosDao = new ProVariosDaoImpl();
        this.proCombustiblesVarios = proVariosDao.findAll();
        return proCombustiblesVarios;
    }

    public ValeVarios getSelectedVale() {
        return selectedVale;
    }    

    public void setSelectedVale(ValeVarios selectedVale) {
        this.selectedVale = selectedVale;
    }
    

    public Double getTotal() {
        return total;
    }
    public Double getTotalVarios() {
        return totalvarios;
    }
    public Double getTotalPagos() {
        return totalpagos;
    }
    public List<ValeVarios> getVales() {
        ValeVariosDao vale = new ValeVariosDaoImpl();
        vales = vale.findPro(this.selectedProveedorVarios);
        this.total = this.sumarTotalPago(vales);
        return vales;
    }
    public List<PagoVarios> getPagos() {
        PagovDao pago = new PagovDaoImpl();
        pagos = pago.findProveedor(this.selectedProveedorVarios);
        this.totalpagos = this.sumarTotalPagoPagos(pagos);
        return pagos;
    }
    public List<Varios> getVarios() {
        VariosDao variosd = new VariosDaoImpl();
        varios = variosd.findpro(this.selectedProveedorVarios);
        this.totalvarios = this.sumarTotalPagoVarios(varios);
        return varios;
    }
     
    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/provarioslist.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getProCombustiblesVarios()));
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=reporte_choferes.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    } 
    public void exportarPDFvale(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/valevarioslist.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getVales()));
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=vales_pendientes.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    } 

    public ProveedorVarios getSelectedProveedorVarios() {
        return selectedProveedorVarios;
    }

    public void setSelectedProveedorVarios(ProveedorVarios selectedProveedorVarios) {
        this.selectedProveedorVarios = selectedProveedorVarios;
    }
    
    public void btnCreateProveedorVarios(ActionEvent actionEvent) {
        ProVariosDao proVariosDao = new ProVariosDaoImpl();
        String msg;
        if (proVariosDao.create(this.selectedProveedorVarios)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedProveedorVarios = new ProveedorVarios();
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    public void btnUpdateProveedorVarios(ActionEvent actionEvent) {
        ProVariosDao proVariosDao = new ProVariosDaoImpl();
        String msg;
        if (proVariosDao.update(this.selectedProveedorVarios)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteProveedorCombustible(ActionEvent actionEvent) {
        ProVariosDao proCombDao = new ProVariosDaoImpl();
        String msg;
        if (proCombDao.delete(this.selectedProveedorVarios.getIdProveedorVarios())) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
}
    public void onRowSelectPro(SelectEvent event) {        
    }
    private Double sumarTotalPago(List<ValeVarios> vales){
    Double suma =0.0;
       for(int i = 0; i < vales.size(); i++){
           suma = suma + vales.get(i).getMonto();
       }
        return suma;
    }
    private Double sumarTotalPagoVarios(List<Varios> varios){
    Double suma =0.0;
       for(int i = 0; i < varios.size(); i++){
           suma = suma + varios.get(i).getMonto();
       }
        return suma;
    }
    private Double sumarTotalPagoPagos(List<PagoVarios> pagos){
    Double suma =0.0;
       for(int i = 0; i < pagos.size(); i++){
           suma = suma + pagos.get(i).getTotal();
       }
        return suma;
    }
}
