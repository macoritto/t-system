/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.DetpagovDao;
import dao.DetpagovDaoImpl;
import dao.PagovDao;
import dao.PagovDaoImpl;
import dao.ValeVariosDao;
import dao.ValeVariosDaoImpl;
import dao.VariosDao;
import dao.VariosDaoImpl;
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
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.DetallePagoVarios;
import model.PagoVarios;
import model.Proveedor;
import model.ProveedorVarios;
import model.Usuario;
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
@ViewScoped
public class pagovBean {
    private List<PagoVarios> pagos;
    private PagoVarios selectedPago;
    private PagoVarios selectedPago1;
    private ValeVarios selectedVale;
    private List<Varios> varios;
    private ProveedorVarios selectedProveedor;
    private Usuario usuario;
    private List<DetallePagoVarios> detpagov;
    private List<DetallePagoVarios> detpagovista;
    private DetallePagoVarios selectedDetpagov;
    private DetallePagoVarios selectedDetpagvaux;
    private Varios SelectedVario;
    private Double total;
    private Integer codpagov;
    private Connection conexion;
    private List<Varios> selectedVarios;
    private List<Varios> selectedAux;
    private Integer codpago;
    private String descrip;
    private String descrip1;
    public pagovBean() {
        this.pagos = new ArrayList<PagoVarios>();
        this.detpagov = new ArrayList<DetallePagoVarios>();
        this.detpagovista = new ArrayList<DetallePagoVarios>();
        this.selectedDetpagov = new DetallePagoVarios();
        this.selectedPago = new PagoVarios();
        this.selectedPago1 = new PagoVarios();
        this.selectedVale = new ValeVarios();
        this.selectedDetpagvaux = new DetallePagoVarios();
        this.selectedPago.setFecha(new Date());
        this.selectedProveedor = new ProveedorVarios();
        this.selectedVarios = new ArrayList<Varios>();
        this.selectedAux = new ArrayList<Varios>();
        this.varios = new ArrayList<Varios>();
        this.SelectedVario = new Varios();
        this.descrip1 = this.selectedPago.getDescripcion();
    }
       public List<PagoVarios> getPagos() {
        PagovDao pagoDao = new PagovDaoImpl();
        this.pagos = pagoDao.findAll();
        this.total=this.sumarTotalPago(pagos);
        return pagos;
    }

    public List<Varios> getSelectedAux() {
        return selectedAux;
    }

    public void setSelectedAux(List<Varios> selectedAux) {
        this.selectedAux = selectedAux;
    }

    public Varios getSelectedVario() {
        return SelectedVario;
    }

    public void setSelectedVario(Varios SelectedVario) {
        this.SelectedVario = SelectedVario;
    }
       
    public List<Varios> getVarios() {
        VariosDao variosDao = new VariosDaoImpl();
        this.varios = variosDao.findpen(this.selectedProveedor);
        //this.total=this.sumarTotalPago(pagos);
        return varios;
    }

    public List<Varios> getSelectedVarios() {
        return selectedVarios;
    }

    public void setSelectedVarios(List<Varios> selectedVarios) {
        this.selectedVarios = selectedVarios;
    }


    public ProveedorVarios getSelectedProveedor() {
        return selectedProveedor;
    }

    public void setSelectedProveedor(ProveedorVarios selectedProveedor) {
        this.selectedProveedor = selectedProveedor;
    }
       
    public List<DetallePagoVarios> getDetpagovista() {
        PagovDao pagoDao = new PagovDaoImpl();
        this.detpagovista = pagoDao.findByDetpagov(this.selectedPago.getIdPagoVarios());
        this.total = this.sumarTotal(detpagovista);
        return detpagovista;
    }

    public PagoVarios getSelectedPago() {
        return selectedPago;
    }

    public ValeVarios getSelectedVale() {
        return selectedVale;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<DetallePagoVarios> getDetpagov() {
        return detpagov;
    }

    public DetallePagoVarios getSelectedDetpagov() {
        return selectedDetpagov;
    }

    public DetallePagoVarios getSelectedDetpagvaux() {
        return selectedDetpagvaux;
    }

    public Double getTotal() {
        return total;
    }

    public void setSelectedPago(PagoVarios selectedPago) {
        this.selectedPago = selectedPago;
    }

    public void setSelectedVale(ValeVarios selectedVale) {
        this.selectedVale = selectedVale;
    }

    public void setSelectedDetpagov(DetallePagoVarios selectedDetpagov) {
        this.selectedDetpagov = selectedDetpagov;
    }

    public void setSelectedDetpagvaux(DetallePagoVarios selectedDetpagvaux) {
        this.selectedDetpagvaux = selectedDetpagvaux;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setCodpagov(Integer codpagov) {
        this.codpagov = codpagov;
    }
    public void cargarArray(ActionEvent event){
        String msg;
        VariosDao variosDao = new VariosDaoImpl();
        this.varios = variosDao.findpen(this.selectedProveedor);
        this.selectedAux = new ArrayList<Varios>();
        FacesMessage message;
        for(int i = 0; i<this.varios.size(); i++){
            //this.selectedAux.get(i).setIdVarios(this.varios.get(i).getIdVarios());
            //this.selectedAux.get(i).setEstado(this.varios.get(i).getEstado());
            //this.selectedAux.get(i).setFecha(this.varios.get(i).getFecha());
            //this.selectedAux.get(i).setMonto(this.varios.get(i).getMonto());
            //this.selectedAux.get(i).setUsuario(this.varios.get(i).getUsuario());
            //this.selectedAux.get(i).setValeVarios(this.varios.get(i).getValeVarios());
            selectedAux.add(this.varios.get(i));
        }
        this.detpagov = new ArrayList<DetallePagoVarios>();                 
        msg= "Se cargaron los varios pendientes de Pago.";      
        message = new FacesMessage(FacesMessage.SEVERITY_INFO,msg," "); 
        
    }
    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/pagovlist.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getPagos()));
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=pagos_a_proveedores.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
      public void exportarPDF2(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/pagov.jasper"));
      codpago= this.selectedPago1.getIdPagoVarios();      
      String image;
      image="http://localhost:8080/t-system/faces/";
      Class.forName("org.postgresql.Driver");
      conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/transpbase", "postgres", "macorittogo");
      Map<String, Object> parametros = new HashMap<String, Object>();
      Map<String, Object> parameters = new HashMap<String, Object>();
      parametros.put("vCodpago", codpago);
      parametros.put("IMAGE", image);
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
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/pagov.jasper"));
      codpago= this.selectedPago.getIdPagoVarios();      
      String image;
      image="http://localhost:8080/t-system/faces/";
      Class.forName("org.postgresql.Driver");
      conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/transpbase", "postgres", "macorittogo");
      Map<String, Object> parametros = new HashMap<String, Object>();
      Map<String, Object> parameters = new HashMap<String, Object>();
      parametros.put("vCodpago", codpago);
      parametros.put("IMAGE", image);
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
        for(int i = 0; i < this.selectedVarios.size(); i++){
            this.selectedDetpagov.setVarios(this.selectedVarios.get(i));
            this.selectedDetpagov.setMonto(this.selectedVarios.get(i).getMonto());
            this.selectedDetpagov.setDescripcion(this.selectedVarios.get(i).getDescripcion());
            System.out.println(this.selectedDetpagov.getDescripcion());
            detpagov.add(this.selectedDetpagov);
            this.selectedDetpagov = new DetallePagoVarios();
            this.selectedPago.setTotal(this.sumarTotal(detpagov));
            selectedAux.remove(this.selectedVarios.get(i));
        }
    msg= "Se Creo correctamente el registro";
    return msg;
    }
    public void bntDescargar(DetallePagoVarios detallePagoVarios) {
        selectedAux.add(detallePagoVarios.getVarios());
        detpagov.remove(detallePagoVarios);
        //for(int i=0; i <this.detpagov.size(); i++){
            //if(this.detpagov.get(i)==detallePagoVarios){
                //selectedAux.add(detallePagoVarios.getVarios());
            //}
        //}
        this.selectedPago.setTotal(this.sumarTotal(detpagov));          
    }
    public void btnCreatePago(){
       PagovDao pagoDao=new PagovDaoImpl();
       String msg;
       FacesMessage message;
       this.selectedPago.setEstado("Pagado");
       this.selectedPago.setDescripcion(this.descrip1);
       usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
       this.selectedPago.setUsuario(usuario);
      if (pagoDao.create(this.selectedPago, this.detpagov)){   
          for(int i = 0; i < detpagov.size(); i++){       
            this.SelectedVario=detpagov.get(i).getVarios();
            this.SelectedVario.setEstadoPago("Pagado");
            VariosDao variosDao = new VariosDaoImpl();
            if (variosDao.update(this.SelectedVario)) {                    
            }       
          }
          msg= "Se Creo correctamente el registro, dar click a PDF";
          message = new FacesMessage(FacesMessage.SEVERITY_INFO,msg," ");     
          this.selectedPago1 = this.selectedPago;
          this.selectedPago = new PagoVarios();
          this.detpagov = new ArrayList<DetallePagoVarios>();
          this.selectedVarios = new ArrayList<Varios>();
          this.pagos = new ArrayList<PagoVarios>();        
          this.detpagovista = new ArrayList<DetallePagoVarios>();
          this.selectedDetpagov = new DetallePagoVarios();
          this.selectedPago = new PagoVarios();
          this.selectedVale = new ValeVarios();
          this.selectedDetpagvaux = new DetallePagoVarios();
          this.selectedPago.setFecha(new Date());
          this.selectedProveedor = new ProveedorVarios();
          this.selectedVarios = new ArrayList<Varios>();
          this.selectedAux = new ArrayList<Varios>();
          this.varios = new ArrayList<Varios>();
          this.SelectedVario = new Varios();
       } else{
           msg="Error al Crear el registro";
           message = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,"");
       }
       
       FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void onRowSelectPro(SelectEvent event){
        this.selectedPago.setProveedorVarios(this.selectedProveedor);
    }
    public void onRowSelectPago(SelectEvent event){        
    } 
    public void btnUpdatePago(ActionEvent actionEvent){
       PagovDao pagoDao=new PagovDaoImpl();
       Integer bande;
       String msg;   
       String est;
       String a;
       a="Anulado";
       est=this.selectedPago.getEstado();
       DetpagovDao detpagoDao = new DetpagovDaoImpl();
       this.detpagovista=detpagoDao.findByPagov(this.selectedPago);
       if(a.equals(this.selectedPago.getEstado())){
           bande=1;
       }else{
           bande=0;
       }
       System.out.println(bande);
       System.out.println(this.selectedPago.getDescripcion());
       System.out.println(this.selectedPago.getEstado());       
       if (pagoDao.update(this.selectedPago)){
           msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, " ");
            FacesContext.getCurrentInstance().addMessage(null, message);           
            if(bande==1){
                for(int i = 0; i < detpagovista.size(); i++){       
                    this.SelectedVario=detpagovista.get(i).getVarios();
                    this.SelectedVario.setEstadoPago("Pendiente");
                    VariosDao variosDao = new VariosDaoImpl();
                    if (variosDao.update(this.SelectedVario)) {                    
                    }                 
                }
            }else{
                for(int i = 0; i < detpagovista.size(); i++){       
                    this.SelectedVario=detpagovista.get(i).getVarios();
                    this.SelectedVario.setEstadoPago("Pagado");
                    VariosDao variosDao = new VariosDaoImpl();
                    if (variosDao.update(this.SelectedVario)) {                    
                    }                 
                }                        
            }        
       }else{
           msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, " ");
            FacesContext.getCurrentInstance().addMessage(null, message);
       }              
    } 
    
    public void btnDeletePago(ActionEvent actionEvent){
      PagovDao pagoDao= new PagovDaoImpl();
      String msg;
       FacesMessage message;
       DetpagovDao detpagoDao = new DetpagovDaoImpl();
       this.detpagovista=detpagoDao.findByPagov(this.selectedPago);
       if (pagoDao.delete(this.selectedPago)){
           msg= "Se Elimino correctamente el registro";
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,msg," ");
            for(int i = 0; i < detpagovista.size(); i++){       
                    this.SelectedVario=detpagovista.get(i).getVarios();
                    this.SelectedVario.setEstadoPago("Pendiente");
                    VariosDao variosDao = new VariosDaoImpl();
                    if (variosDao.update(this.SelectedVario)) {                    
                    }                 
            }
       } else{
           msg="Error al Eliminar el registro";
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg," ");
       }
       
       FacesContext.getCurrentInstance().addMessage(null, message);
        
    }
    public void calcularTotal() {
        this.selectedDetpagov.setMonto(this.selectedVale.getMonto());
        this.selectedPago.setTotal(this.sumarTotal(detpagov));
        //System.out.println(this.selectedDetventa.getCantidad());
    }
    public void calcularTotalAux() {        
        this.selectedDetpagvaux.setMonto(this.selectedVale.getMonto()); 
        this.selectedPago.setTotal(this.sumarTotal(detpagov));                
    }
    private Double sumarTotal(List<DetallePagoVarios> detpagov){
    Double suma =0.0;
       for(int i = 0; i < detpagov.size(); i++){
           suma = suma + detpagov.get(i).getMonto();
       }
        return suma;
    }

    private Double sumarTotalPago(List<PagoVarios> pagos){
    Double suma =0.0;
       for(int i = 0; i < pagos.size(); i++){
           suma = suma + pagos.get(i).getTotal();
       }
        return suma;
    }
    public void cargarDescrip(ActionEvent event){
        this.descrip1=this.selectedPago.getDescripcion();
    }   
}
