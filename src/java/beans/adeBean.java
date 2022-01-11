/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.AdeSumiDao;
import dao.AdeSumiDaoImpl;
import dao.ProSumiDao;
import dao.ProSumiDaoImpl;
import dao.UnidadSumiDao;
import dao.UnidadSumiDaoImpl;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.AdeSuministro;
import model.Combustible;
import model.PagoCombustible;
import model.ProveeCliente;
import model.UnidadProvee;
import model.Usuario;
import model.ValeCombustible;
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
public class adeBean {
    private List<AdeSuministro> adelantos;
    private List<SelectItem> selectedPro;
    private AdeSuministro selectedAdelanto;
    private ProveeCliente selectedProvee;
    private Object proCombDao;
    private Double Total;
    private Double Totalvia;
    private Double Totalcomb;
    private Double Totalpago;
    private Double Totalextras;
    private Usuario usuario;
    public adeBean() {
        this.adelantos = new ArrayList<AdeSuministro>();
        this.selectedAdelanto = new AdeSuministro();
    }
    public List<AdeSuministro> getAdelantos() {
        AdeSumiDao sumiDao = new AdeSumiDaoImpl();
        this.adelantos = sumiDao.findAll();
        return adelantos;        
    }
    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/procomblist.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getAdelantos()));
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=prov_combustible.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
public void btnIniciar(ActionEvent actionEvent){
        this.selectedAdelanto= new AdeSuministro();        
    }
    public AdeSuministro getSelectedAdelanto() {
        return selectedAdelanto;
    }
public List<SelectItem> getSelectPro() {
        this.selectedPro = new ArrayList<SelectItem>();
        ProSumiDao clienteDao = new ProSumiDaoImpl();
        List<ProveeCliente> clientes = clienteDao.SelectItems();
        for (ProveeCliente provee : clientes) {
            SelectItem selecItem = new SelectItem(provee.getIdProveecli(), provee.getNombre());
            this.selectedPro.add(selecItem);
        }
        return selectedPro;
    }

    public ProveeCliente getSelectedProvee() {
        return selectedProvee;
    }

    public void setSelectedProvee(ProveeCliente selectedProvee) {
        this.selectedProvee = selectedProvee;
    }
    public void onRowSelectPro(SelectEvent event) {
        this.selectedAdelanto.setProveeCliente(this.selectedProvee);
    }
    public void setSelectedAdelanto(AdeSuministro selectedAdelanto) {
        this.selectedAdelanto = selectedAdelanto;
    }
    public void btnCreateProveedorCombustible(ActionEvent actionEvent) {
        AdeSumiDao sumiDao = new AdeSumiDaoImpl();
        String msg;
        this.selectedAdelanto.setEstadoCobro("Pendiente");
        this.selectedAdelanto.setEstadoPago("Pendiente");
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
        this.selectedAdelanto.setUsuario(usuario);
        if (sumiDao.create(this.selectedAdelanto)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedAdelanto = new AdeSuministro();
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    public void btnUpdateProveedorCombustible(ActionEvent actionEvent) {
        AdeSumiDao sumiDao = new AdeSumiDaoImpl();
        String msg;
        if (sumiDao.update(this.selectedAdelanto)) {
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
        AdeSumiDao sumiDao = new AdeSumiDaoImpl();
        String msg;
        if (sumiDao.delete(this.selectedAdelanto.getIdAde())) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedAdelanto = new AdeSuministro();
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
}