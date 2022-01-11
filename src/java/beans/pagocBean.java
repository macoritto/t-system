/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CombDao;
import dao.CombDaoImpl;
import dao.DetpagocDao;
import dao.DetpagocDaoImpl;
import dao.PagocDao;
import dao.PagocDaoImpl;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
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
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Combustible;
import model.DetallePagoComb;
import model.PagoCombustible;
import model.Proveedor;
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
@ViewScoped
public class pagocBean implements Serializable {

    private PagoCombustible selectedPago;
    private List<PagoCombustible> pagos;
    private Proveedor selectedProveedor;
    private Usuario usuario;
    private ValeCombustible selectedVale;
    private Combustible selectedCombustible;
    private List<Combustible> combustibles;
    private List <DetallePagoComb> detpagoc;
    private List <DetallePagoComb> detpagovista;
    private DetallePagoComb selectedDetComb;
    private DetallePagoComb selectedDetCombAux;
    private Double total;
    private Double totalcomb;
    private Double totalex;
    private Integer codpagoc;
    private Double totalviatico;
    private Connection conexion;
    private List<Combustible> selectedCombustibles;
    private List<Combustible> selectedCombAux;
    private Integer codpago;
    private String descrip1;
    public pagocBean() {
        this.selectedPago = new PagoCombustible();
        this.selectedPago.setFecha(new Date());
        this.pagos = new ArrayList<PagoCombustible>();
        this.selectedProveedor = new Proveedor();
        this.usuario = new Usuario();
        this.combustibles = new ArrayList<Combustible>();
        this.selectedVale = new ValeCombustible();
        this.detpagoc = new ArrayList<DetallePagoComb>();
        this.detpagovista = new ArrayList<DetallePagoComb>();
        this.selectedDetComb = new DetallePagoComb();
        this.selectedDetCombAux = new DetallePagoComb();
        this.selectedCombustibles = new ArrayList<Combustible>();
        this.selectedCombAux = new ArrayList<Combustible>();
    }
    public List<PagoCombustible> getPagos() {
        PagocDao pagoDao = new PagocDaoImpl();
        this.pagos = pagoDao.findAll();
        this.total=this.sumarTotalPago(pagos);
        return pagos;
    }

    public Double getTotalcomb() {
        return totalcomb;
    }

    public Double getTotalex() {
        return totalex;
    }

    public void setTotalcomb(Double totalcomb) {
        this.totalcomb = totalcomb;
    }

    public void setTotalex(Double totalex) {
        this.totalex = totalex;
    }
    

    public Double getTotalviatico() {
        return totalviatico;
    }

    public void setTotalviatico(Double totalviatico) {
        this.totalviatico = totalviatico;
    }    
    public List<Combustible> getCombustibles() {
        CombDao combDao = new CombDaoImpl();
        this.combustibles = combDao.findpen(this.selectedProveedor);
        this.total=this.sumarTotalPago(pagos);        
        return combustibles;
    }
     public List<DetallePagoComb> getDetpagovista() {
        PagocDao pagoDao = new PagocDaoImpl();
        this.detpagovista = pagoDao.findByDetpagoc(this.selectedPago.getIdPagoCombustible());   
        this.totalviatico = this.sumarTotalViatico(detpagovista);
        this.totalcomb = this.sumarTotalComb(detpagovista);
        this.totalex = this.sumarTotalExtras(detpagovista);
        this.total = this.sumarTotal(detpagovista);
        return detpagovista;
    }

    public List<DetallePagoComb> getDetpagoc() {
        return detpagoc;
    }

    public void setDetpagoc(List<DetallePagoComb> detpagoc) {
        this.detpagoc = detpagoc;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getCodpagoc() {
        return codpagoc;
    }

    public void setCodpagoc(Integer codpagoc) {
        this.codpagoc = codpagoc;
    }
    
    public PagoCombustible getSelectedPago() {
        return selectedPago;
    }

    public Proveedor getSelectedProveedor() {
        return selectedProveedor;
    }

    public ValeCombustible getSelectedVale() {
        return selectedVale;
    }

    public Combustible getSelectedCombustible() {
        return selectedCombustible;
    }

    public DetallePagoComb getSelectedDetComb() {
        return selectedDetComb;
    }

    public DetallePagoComb getSelectedDetCombAux() {
        return selectedDetCombAux;
    }

    public List<Combustible> getSelectedCombustibles() {
        return selectedCombustibles;
    }

    public List<Combustible> getSelectedCombAux() {
        return selectedCombAux;
    }

    public void setSelectedPago(PagoCombustible selectedPago) {
        this.selectedPago = selectedPago;
    }

    public void setSelectedProveedor(Proveedor selectedProveedor) {
        this.selectedProveedor = selectedProveedor;
    }

    public void setSelectedVale(ValeCombustible selectedVale) {
        this.selectedVale = selectedVale;
    }

    public void setSelectedCombustible(Combustible selectedCombustible) {
        this.selectedCombustible = selectedCombustible;
    }

    public void setSelectedDetComb(DetallePagoComb selectedDetComb) {
        this.selectedDetComb = selectedDetComb;
    }

    public void setSelectedDetCombAux(DetallePagoComb selectedDetCombAux) {
        this.selectedDetCombAux = selectedDetCombAux;
    }

    public void setSelectedCombustibles(List<Combustible> selectedCombustibles) {
        this.selectedCombustibles = selectedCombustibles;
    }

    public void setSelectedCombAux(List<Combustible> selectedCombAux) {
        this.selectedCombAux = selectedCombAux;
    }
    public String cargarArray(ActionEvent event){
        String msg;
        CombDao combDao = new CombDaoImpl();
        this.combustibles = combDao.findpen(this.selectedProveedor);
        this.selectedCombAux = new ArrayList<Combustible>();
        for(int i = 0; i<this.combustibles.size(); i++){
            //this.selectedAux.get(i).setIdVarios(this.varios.get(i).getIdVarios());
            //this.selectedAux.get(i).setEstado(this.varios.get(i).getEstado());
            //this.selectedAux.get(i).setFecha(this.varios.get(i).getFecha());
            //this.selectedAux.get(i).setMonto(this.varios.get(i).getMonto());
            //this.selectedAux.get(i).setUsuario(this.varios.get(i).getUsuario());
            //this.selectedAux.get(i).setValeVarios(this.varios.get(i).getValeVarios());
            selectedCombAux.add(this.combustibles.get(i));
        }
        this.detpagoc = new ArrayList<DetallePagoComb>();
        msg= "Se Creo correctamente el registro";
        return msg;
    }
    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/pagoclist.jasper"));
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
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/pagoc.jasper"));
      codpago= this.selectedPago.getIdPagoCombustible();
      Class.forName("org.postgresql.Driver");
      conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/transpbase", "postgres", "macorittogo");
      Map<String, Object> parametros = new HashMap<String, Object>();
      parametros.put("vCodpago", codpago);
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=pago_proveedor.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
    public String btnCargar(ActionEvent event) {
        String msg;
        for(int i = 0; i < this.selectedCombustibles.size(); i++){
            this.selectedDetComb.setCombustible(this.selectedCombustibles.get(i));
            this.selectedDetComb.setMonto(this.selectedCombustibles.get(i).getMontoTotal());
            this.selectedDetComb.setLitros(this.selectedCombustibles.get(i).getLitros());
            this.selectedDetComb.setPrecioComb(this.selectedCombustibles.get(i).getPrecio());
            detpagoc.add(this.selectedDetComb);
            this.selectedDetComb = new DetallePagoComb();            
            this.totalviatico = this.sumarTotalViatico(detpagoc);
            this.totalcomb = this.sumarTotalComb(detpagoc);
            this.totalex = this.sumarTotalExtras(detpagoc);
            this.selectedPago.setTotal(this.totalviatico+this.totalcomb+this.totalex);
            selectedCombAux.remove(this.selectedCombustibles.get(i));
        }
    msg= "Se Creo correctamente el registro";
    return msg;
    }
    public void bntDescargar(DetallePagoComb detallePagoComb) {
        selectedCombAux.add(detallePagoComb.getCombustible());
        detpagoc.remove(detallePagoComb);
        //for(int i=0; i <this.detpagov.size(); i++){
            //if(this.detpagov.get(i)==detallePagoVarios){
                //selectedAux.add(detallePagoVarios.getVarios());
            //}
        //}
        this.totalviatico = this.sumarTotalViatico(detpagoc);
        this.totalcomb = this.sumarTotalComb(detpagoc);
        this.totalex = this.sumarTotalExtras(detpagoc);
        this.selectedPago.setTotal(this.totalviatico+this.totalcomb+this.totalex);
    }
    public void btnCreatePago(){
       PagocDao pagoDao=new PagocDaoImpl();
       String msg;
       FacesMessage message;
       this.selectedPago.setEstado("Pagado");
       this.selectedPago.setDescripcion(this.descrip1);
       usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
       this.selectedPago.setUsuario(usuario);
      if (pagoDao.create(this.selectedPago, this.detpagoc)){   
          for(int i = 0; i < detpagoc.size(); i++){       
            this.selectedCombustible=detpagoc.get(i).getCombustible();
            this.selectedCombustible.setEstadoPago("Pagado");
            CombDao combDao = new CombDaoImpl();
            if (combDao.update(this.selectedCombustible)) {                    
            }       
          }
          msg= "Se Creo correctamente el registro";
          message = new FacesMessage(FacesMessage.SEVERITY_INFO,msg," ");                                            
       } else{
           msg="Error al Crear el registro";
           message = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,null);
       }
       
       FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void onRowSelectPro(SelectEvent event){
        this.selectedPago.setProveedor(this.selectedProveedor);
    }
    public void onRowSelectPago(SelectEvent event){        
    } 
    public void btnUpdatePago(ActionEvent actionEvent){
       PagocDao pagoDao=new PagocDaoImpl();
       Integer bande;
       String msg;   
       String est;
       String a;
       a="Anulado";
       est=this.selectedPago.getEstado();
       DetpagocDao detpagoDao = new DetpagocDaoImpl();
       this.detpagovista=detpagoDao.findByPagoc(this.selectedPago);
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
                    this.selectedCombustible=detpagovista.get(i).getCombustible();
                    this.selectedCombustible.setEstadoPago("Pendiente");
                    CombDao combDao = new CombDaoImpl();
                    if (combDao.update(this.selectedCombustible)) {                    
                    }                 
                    }
            }else{
                for(int i = 0; i < detpagovista.size(); i++){       
                    this.selectedCombustible=detpagovista.get(i).getCombustible();
                    this.selectedCombustible.setEstadoPago("Pagado");
                    CombDao combDao = new CombDaoImpl();
                    if (combDao.update(this.selectedCombustible)) {                    
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
      PagocDao pagoDao= new PagocDaoImpl();
      String msg;
       FacesMessage message;
       DetpagocDao detpagoDao = new DetpagocDaoImpl();
       this.detpagovista=detpagoDao.findByPagoc(this.selectedPago);
       if (pagoDao.delete(this.selectedPago)){
           msg= "Se Elimino correctamente el registro";
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,msg," ");
            for(int i = 0; i < detpagovista.size(); i++){       
                    this.selectedCombustible=detpagovista.get(i).getCombustible();
                    this.selectedCombustible.setEstadoPago("Pendiente");
                    CombDao combDao = new CombDaoImpl();
                    if (combDao.update(this.selectedCombustible)) {                    
                    }                 
            }
       } else{
           msg="Error al Eliminar el registro";
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg," ");
       }
       
       FacesContext.getCurrentInstance().addMessage(null, message);
        
    }
    public void calcularTotal() {
        //this.selectedDetComb.setMonto(this.selectedVale.getMonto());
        this.selectedPago.setTotal(this.sumarTotal(detpagoc));
        //System.out.println(this.selectedDetventa.getCantidad());
    }   
    private Double sumarTotal(List<DetallePagoComb> detpagoc){
    Double suma =0.0;
       for(int i = 0; i < detpagoc.size(); i++){
           suma = suma + detpagoc.get(i).getMonto();
       }
        return suma;
    }

    private Double sumarTotalPago(List<PagoCombustible> pagos){
    Double suma =0.0;
       for(int i = 0; i < pagos.size(); i++){
           suma = suma + pagos.get(i).getTotal();
       }
        return suma;
    }
    private Double sumarTotalViatico(List<DetallePagoComb> detvia){
    Double suma =0.0;
       for(int i = 0; i < detvia.size(); i++){
           suma = suma + detvia.get(i).getCombustible().getViatico_1();
       }
        return suma;
    }
    private Double sumarTotalComb(List<DetallePagoComb> detcomb){
    Double suma =0.0;
       for(int i = 0; i < detcomb.size(); i++){
           suma = suma + detcomb.get(i).getCombustible().getMontoComb();
       }
        return suma;
    }
    private Double sumarTotalExtras(List<DetallePagoComb> detvia){
    Double suma =0.0;
       for(int i = 0; i < detvia.size(); i++){
           suma = suma + detvia.get(i).getCombustible().getExtras();
       }
        return suma;
    }
    public void cargarDescrip(ActionEvent event){
        this.descrip1=this.selectedPago.getDescripcion();
    }
}
