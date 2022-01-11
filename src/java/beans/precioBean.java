/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import dao.PreSumiDao;
import dao.PreSumiDaoImpl;
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
import model.Combustible;
import model.PagoCombustible;
import model.ProveeCliente;
import model.PrecioSuministro;
import model.UnidadProvee;
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
public class precioBean {
    private List<PrecioSuministro> precios;
    private List<PrecioSuministro> prepro;
    private List<SelectItem> selectedPro;
    private UnidadProvee selectedUnidad;
    private PrecioSuministro selectedPrecio;
    private ProveeCliente selectedProvee;
    private Object proCombDao;
    private Double Total;
    private Double Totalvia;
    private Double Totalcomb;
    private Double Totalpago;
    private Double Totalextras;
    public precioBean() {
        this.precios = new ArrayList<PrecioSuministro>();
        this.selectedPrecio = new PrecioSuministro();
        this.prepro=new ArrayList<PrecioSuministro>();
    }
    public List<PrecioSuministro> getPrecios() {
        PreSumiDao sumiDao = new PreSumiDaoImpl();
        this.precios = sumiDao.findAll();
        return precios;        
    }
//    public List<PrecioSuministro> getPrepro() {
//        PreSumiDao sumiDao = new PreSumiDaoImpl();
//        this.precios = sumiDao.findAll();
//        return precios;        
//    }
    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/procomblist.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getPrecios()));
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=prov_combustible.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
public void btnIniciar(ActionEvent actionEvent){
        this.selectedUnidad= new UnidadProvee();      
        this.selectedProvee = new ProveeCliente();
        this.selectedPrecio = new PrecioSuministro();
    }
    public UnidadProvee getSelectedUnidad() {
        return selectedUnidad;
    }

    public PrecioSuministro getSelectedPrecio() {
        return selectedPrecio;
    }

    public void setSelectedPrecio(PrecioSuministro selectedPrecio) {
        this.selectedPrecio = selectedPrecio;
    }

    public ProveeCliente getSelectedProvee() {
        return selectedProvee;
    }

    public void setSelectedProvee(ProveeCliente selectedProvee) {
        this.selectedProvee = selectedProvee;
    }
    public void onRowSelectPro(SelectEvent event) {
        this.selectedPrecio.setUnidadProvee(this.selectedUnidad);
    }
    public void setSelectedUnidad(UnidadProvee selectedUnidad) {
        this.selectedUnidad = selectedUnidad;
    }
    public void btnCreateProveedorCombustible(ActionEvent actionEvent) {
        PreSumiDao sumiDao = new PreSumiDaoImpl();
        String msg;
        if (sumiDao.create(this.selectedPrecio)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedPrecio = new PrecioSuministro();
            this.selectedUnidad = new UnidadProvee();
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    public void btnUpdateProveedorCombustible(ActionEvent actionEvent) {
        PreSumiDao sumiDao = new PreSumiDaoImpl();
        String msg;
        if (sumiDao.update(this.selectedPrecio)) {
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
        PreSumiDao sumiDao = new PreSumiDaoImpl();
        String msg;
        if (sumiDao.delete(this.selectedPrecio.getIdPrecioSumi())) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedUnidad = new UnidadProvee();
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