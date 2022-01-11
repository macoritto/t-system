/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CombDao;
import dao.CombDaoImpl;
import dao.PagocDao;
import dao.PagocDaoImpl;
import dao.ProCombDao;
import dao.ProCombDaoImpl;
import dao.ValeCombDao;
import dao.ValeCombDaoImpl;
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
import model.Combustible;
import model.PagoCombustible;
import model.Proveedor;
import model.ValeCombustible;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Usuario
 */
@ManagedBean
@SessionScoped
public class proCombBean {
    private List<Proveedor> proCombustibles;
    private List<Combustible> combus;
    private ValeCombustible selectedValeComb;
    private List<PagoCombustible> pagoscomb;
    private Proveedor selectedProveedor;
    private List<ValeCombustible> vales;
    private Object proCombDao;
    private Double Total;
    private Double Totalvia;
    private Double Totalcomb;
    private Double Totalpago;
    private Double Totalextras;
    public proCombBean() {
        this.proCombustibles = new ArrayList<Proveedor>();
        this.combus = new ArrayList<Combustible>();
        this.pagoscomb = new ArrayList<PagoCombustible>();
        this.selectedProveedor = new Proveedor();
        this.selectedValeComb = new ValeCombustible();
        this.vales= new ArrayList<ValeCombustible>();
    }
    public List<Proveedor> getProCombustibles() {
        ProCombDao proCombDao = new ProCombDaoImpl();
        this.proCombustibles = proCombDao.findAll();
        return proCombustibles;        
    }
    public List<ValeCombustible> getVales() {
        ValeCombDao vale = new ValeCombDaoImpl();
        this.vales = vale.findPen(this.selectedProveedor);
        this.Total = this.sumarTotalPago(vales);
        this.Totalcomb = this.sumarTotalComb(vales);
        this.Totalvia = this.sumarTotalVia(vales);
        this.Totalextras = this.sumarTotalExtras(vales);
        return vales;                
    }
    public List<Combustible> getCombustible() {
       CombDao comb = new CombDaoImpl();
        this.combus = comb.findpro(this.selectedProveedor);
        this.Totalcomb = this.sumarTotalPagoComb(combus);
        return combus;                
    }
    public List<PagoCombustible> getPagoCombustible() {
       PagocDao pago = new PagocDaoImpl();
        this.pagoscomb = pago.findpro(this.selectedProveedor);
        this.Totalpago = this.sumarTotalPago1(pagoscomb);
        return pagoscomb;                
    }
    public void setCombus(List<Combustible> combus) {
        this.combus = combus;
    }
    

    public Double getTotal() {
        return Total;
    }

    public Double getTotalcomb() {
        return Totalcomb;
    }

    public Double getTotalpago() {
        return Totalpago;
    }

    public Double getTotalvia() {
        return Totalvia;
    }

    public Double getTotalextras() {
        return Totalextras;
    }

    public void setTotalvia(Double Totalvia) {
        this.Totalvia = Totalvia;
    }

    public void setTotalextras(Double Totalextras) {
        this.Totalextras = Totalextras;
    }
    
    public void setTotalcomb(Double Totalcomb) {
        this.Totalcomb = Totalcomb;
    }

    public void setTotalpago(Double Totalpago) {
        this.Totalpago = Totalpago;
    }
    

    public void setTotal(Double Total) {
        this.Total = Total;
    }
    
    public void setVales(List<ValeCombustible> vales) {
        this.vales = vales;
    }

    public ValeCombustible getSelectedValeComb() {
        return selectedValeComb;
    }

    public void setSelectedValeComb(ValeCombustible selectedValeComb) {
        this.selectedValeComb = selectedValeComb;
    }
    
    
    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/procomblist.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getProCombustibles()));
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=prov_combustible.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }

    public Proveedor getSelectedProveedor() {
        return selectedProveedor;
    }

    public void setSelectedProveedor(Proveedor selectedProveedor) {
        this.selectedProveedor = selectedProveedor;
    }
    public void btnCreateProveedorCombustible(ActionEvent actionEvent) {
        ProCombDao proCombDao = new ProCombDaoImpl();
        String msg;
        if (proCombDao.create(this.selectedProveedor)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedProveedor = new Proveedor();
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    public void btnUpdateProveedorCombustible(ActionEvent actionEvent) {
        ProCombDao proCombDao = new ProCombDaoImpl();
        String msg;
        if (proCombDao.update(this.selectedProveedor)) {
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
        ProCombDao proCombDao = new ProCombDaoImpl();
        String msg;
        if (proCombDao.delete(this.selectedProveedor.getIdProveedor())) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    private Double sumarTotalPago(List<ValeCombustible> vales){
    Double suma =0.0;
       for(int i = 0; i < vales.size(); i++){
           suma = suma + vales.get(i).getMontoTotal();
       }
        return suma;
    } 
    private Double sumarTotalComb(List<ValeCombustible> vales){
    Double suma =0.0;
       for(int i = 0; i < vales.size(); i++){
           suma = suma + vales.get(i).getMontoComb();
       }
        return suma;
    } 
    private Double sumarTotalVia(List<ValeCombustible> vales){
    Double suma =0.0;
       for(int i = 0; i < vales.size(); i++){
           suma = suma + vales.get(i).getViatico();
       }
        return suma;
    } 
    private Double sumarTotalExtras(List<ValeCombustible> vales){
    Double suma =0.0;
       for(int i = 0; i < vales.size(); i++){
           suma = suma + vales.get(i).getExtras();
       }
        return suma;
    } 
    private Double sumarTotalPagoComb(List<Combustible> combus){
    Double suma =0.0;
       for(int i = 0; i < combus.size(); i++){
           suma = suma + combus.get(i).getMontoTotal();
       }
        return suma;
    } 
    private Double sumarTotalPago1(List<PagoCombustible> pagoscomb){
    Double suma =0.0;
       for(int i = 0; i < pagoscomb.size(); i++){
           suma = suma + pagoscomb.get(i).getTotal();
       }
        return suma;
    } 
    public void exportarPDFvale(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/valelist.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getVales()));
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=vales_pendientes.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    } 
}