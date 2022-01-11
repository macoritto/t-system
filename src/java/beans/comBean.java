/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CamionDao;
import dao.CamionDaoImpl;
import dao.CombDao;
import dao.CombDaoImpl;
import dao.OrdenDeCargaDao;
import dao.OrdenDeCargaDaoImpl;
import dao.PrecioCombDao;
import dao.PrecioCombDaoImpl;
import dao.ProCombDao;
import dao.ProCombDaoImpl;
import dao.SumiDao;
import dao.SumiDaoImpl;
import dao.TipoViaticoDao;
import dao.TipoViaticoDaoImpl;
import dao.ValeCombDao;
import dao.ValeCombDaoImpl;
import dao.ViaticoDao;
import dao.ViaticoDaoImpl;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Camion;
import model.Combustible;
import model.TipoViatico;
import model.Usuario;
import model.ValeCombustible;
import model.Viatico;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.event.SelectEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.event.AjaxBehaviorEvent;
import model.OrdenDeCarga;
import model.PrecioComb;
import model.Proveedor;

/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class comBean {

    /**
     * Creates a new instance of comBean
     */
    private Combustible selectedCombustible;
    private List<Proveedor> proCombustibles;
    private List<Combustible> combustibles;
    private OrdenDeCarga selectedOrdenDeCarga;
    private List<OrdenDeCarga> orden;
    private PrecioComb selectedPrecio;
    private ValeCombustible selectedValeComb;
    private ValeCombustible selectedValeComb2;
    private List<Combustible> buscomb;
    private List<Combustible> comblist;
    private List<ValeCombustible> valespen;
    private List<Camion> camiones;
    private ValeCombustible selectedValeCombustible;
    private Camion selectedCamion;  
    private Viatico selectedViatico;
    private Usuario usuario;
    private List<TipoViatico> tipo;
    private List<Viatico> viaticos;
    private Double totalcomb;
    private Double totalvia;
    private Double totalex;
    private Double total;
    private Double bustotalcomb;
    private Double bustotalvia;
    private Double bustotalex;
    private Double bustotal;
    private List<PrecioComb> precios;
    private Integer nrocomb;
    private Integer busnrocomb;    
    private Date fechaini;
    private Date fechafin;
    private Date fechaini1;
    private Date fechafin1;
    private Proveedor selectedProveedor;
    private Double litros;
    private Boolean validar;
    private Integer codvale;
    private Connection conexion;
    public comBean() {
        this.selectedValeCombustible = new ValeCombustible();
        this.selectedCamion = new Camion();
        this.selectedCombustible = new Combustible();
        this.combustibles = new ArrayList<Combustible>();
        CombDao combDao = new CombDaoImpl();
        this.combustibles = combDao.findAll();
        this.comblist = new ArrayList<Combustible>();
        this.valespen = new ArrayList<ValeCombustible>();
        this.camiones = new ArrayList<Camion>();
        CamionDao camionDao = new CamionDaoImpl();
        this.camiones = camionDao.findAll();
        this.precios = new ArrayList<PrecioComb>();
        this.tipo = new ArrayList<TipoViatico>();
        this.selectedViatico = new Viatico();
        this.buscomb = new ArrayList<Combustible>();
        this.selectedProveedor = new Proveedor();
        this.selectedCombustible.setFecha(new Date());
        this.proCombustibles = new ArrayList<Proveedor>();
        ProCombDao proCombDao = new ProCombDaoImpl();
        this.proCombustibles = proCombDao.findAll();
        this.selectedValeComb = new ValeCombustible();
        this.selectedPrecio = new PrecioComb();
        this.selectedOrdenDeCarga = new OrdenDeCarga();
        this.orden = new ArrayList<OrdenDeCarga>();
        this.selectedValeComb2 = new ValeCombustible();
        this.validar =false;
    }
    public List<Proveedor> getProCombustibles() {
        return proCombustibles;        
    }
    public Combustible getSelectedCombustible() {
        return selectedCombustible;
    }
    public PrecioComb getSelectedPrecio() {
        return selectedPrecio;
    }

    public ValeCombustible getSelectedValeComb() {
        return selectedValeComb;
    }

    public Boolean getValidar() {
        return validar;
    }

    public void setSelectedPrecio(PrecioComb selectedPrecio) {
        this.selectedPrecio = selectedPrecio;
    }

    public void setSelectedValeComb(ValeCombustible selectedValeComb) {
        this.selectedValeComb = selectedValeComb;
    }

    

    public List<Combustible> getBuscomb() {
        return buscomb;
    }

    public void setBuscomb(List<Combustible> buscomb) {
        this.buscomb = buscomb;
    }

    public Double getBustotalcomb() {
        return bustotalcomb;
    }

    public Double getBustotalvia() {
        return bustotalvia;
    }

    public Double getBustotalex() {
        return bustotalex;
    }

    public Double getBustotal() {
        return bustotal;
    }

    public Double getLitros() {
        return litros;
    }

    public void setLitros(Double litros) {
        this.litros = litros;
    }
    
    public void setBustotalcomb(Double bustotalcomb) {
        this.bustotalcomb = bustotalcomb;
    }

    public void setBustotalvia(Double bustotalvia) {
        this.bustotalvia = bustotalvia;
    }

    public void setBustotalex(Double bustotalex) {
        this.bustotalex = bustotalex;
    }

    public void setBustotal(Double bustotal) {
        this.bustotal = bustotal;
    }

    public Integer getBusnrocomb() {
        return busnrocomb;
    }

    public void setBusnrocomb(Integer busnrocomb) {
        this.busnrocomb = busnrocomb;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public Date getFechaini() {
        return fechaini;
    }

    public void setFechaini(Date fechaini) {
        this.fechaini = fechaini;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Proveedor getSelectedProveedor() {
        return selectedProveedor;
    }

    public void setSelectedProveedor(Proveedor selectedProveedor) {
        this.selectedProveedor = selectedProveedor;
    }

    public Date getFechafin1() {
        return fechafin1;
    }

    public Date getFechaini1() {
        return fechaini1;
    }

    public void setFechaini1(Date fechaini1) {
        this.fechaini1 = fechaini1;
    }

    public void setFechafin1(Date fechafin1) {
        this.fechafin1 = fechafin1;
    }

    public OrdenDeCarga getSelectedOrdenDeCarga() {
        return selectedOrdenDeCarga;
    }

    public void setSelectedOrdenDeCarga(OrdenDeCarga selectedOrdenDeCarga) {
        this.selectedOrdenDeCarga = selectedOrdenDeCarga;
    }
    
    public void onRowSelectPro(SelectEvent event) {
        this.selectedValeComb.setProveedor(this.selectedProveedor);
        this.selectedPrecio = new PrecioComb();
    }
    public void onRowSelectOrden(AjaxBehaviorEvent event) {
        String msg;
        OrdenDeCargaDao ordenDao = new OrdenDeCargaDaoImpl();
        this.orden= ordenDao.findUno(this.selectedOrdenDeCarga.getIdOrdenDeCarga());
        for(int i = 0; i < orden.size(); i++){                
            this.selectedOrdenDeCarga=orden.get(i);                
        }
        this.selectedCamion=this.selectedOrdenDeCarga.getCamion();                
        if(this.selectedOrdenDeCarga.getCamion()==null){
            msg = "No Existe la Orden De Carga Especificada.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }        
    }
    public List<PrecioComb> getPrecios() {
        PrecioCombDao precioDao = new PrecioCombDaoImpl();
        this.precios = precioDao.findPrecios(this.selectedProveedor);
        return precios;
    }  
    private boolean chkBoxChecked;

    public boolean isChkBoxChecked() {
        return chkBoxChecked;
    }

    public boolean isBtnDisabled() {
        
        return !this.chkBoxChecked;
    }

    private boolean chkBoxCheckedo;

    public boolean isChkBoxCheckedo() {
        return chkBoxChecked;
    }

    public boolean isBtnDisabledo() {
        
        return !this.chkBoxCheckedo;
    }

    public void setChkBoxCheckedo(boolean chkBoxCheckedo) {
        this.chkBoxCheckedo = chkBoxCheckedo;
    }
public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/comblist.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getComblist()));
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=comb.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
public void exportarPDF1(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/comblist.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getBuscomb()));
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=comb.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
    public List<Combustible> getCombustibles() {
        
        return combustibles;
    }
    public List<Camion> getCamiones() {        
        return camiones;
    }
    public ValeCombustible getSelectedValeCombustible() {
        return selectedValeCombustible;
    }

    public Camion getSelectedCamion() {
        return selectedCamion;
    }

    public Viatico getSelectedViatico() {
        return selectedViatico;
    }

    public List<Combustible> getComblist() {
        this.totalvia = this.sumarViatico(this.comblist);
        this.nrocomb = this.comblist.size();
        return comblist;
    }

    public Double getTotalvia() {
        return totalvia;
    }

    public void setTotalvia(Double totalvia) {
        this.totalvia = totalvia;
    }

    public Integer getNrocomb() {
        return nrocomb;
    }

    public void setNrocomb(Integer nrocomb) {
        this.nrocomb = nrocomb;
    }
    
    public void setComblist(List<Combustible> comblist) {
        this.comblist = comblist;
    }    
    public void setSelectedViatico(Viatico selectedViatico) {
        this.selectedViatico = selectedViatico;
    }
    
    public void setSelectedCombustible(Combustible selectedCombustible) {
        this.selectedCombustible = selectedCombustible;
    }

    public void setSelectedValeCombustible(ValeCombustible selectedValeCombustible) {
        this.selectedValeCombustible = selectedValeCombustible;
    }

    public void setSelectedCamion(Camion selectedCamion) {
        this.selectedCamion = selectedCamion;
    }

    public Double getTotalcomb() {
        return totalcomb;
    }

    public Double getTotalex() {
        return totalex;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotalcomb(Double totalcomb) {
        this.totalcomb = totalcomb;
    }

    public void setTotalex(Double totalex) {
        this.totalex = totalex;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    public void onRowSelectVale(SelectEvent event) {
        this.selectedCombustible.setValeCombustible(this.selectedValeCombustible);
        this.selectedCombustible.setPrecio(this.selectedValeCombustible.getPrecioComb());
        this.selectedCombustible.setExtras(this.selectedValeCombustible.getExtras());
        this.selectedCombustible.setLitros(this.selectedValeCombustible.getLitros());
        this.selectedCombustible.setMontoTotal(this.selectedValeCombustible.getMontoTotal());
        this.selectedCombustible.setMontoComb(this.selectedValeCombustible.getMontoComb());
        this.selectedCombustible.setViatico_1(this.selectedValeCombustible.getViatico());
    }
    public void onRowSelectCamion(SelectEvent event) {    
        for(int i=0; i<this.comblist.size(); i++){
            this.selectedCombustible = this.comblist.get(i);
        }
    }
    public void onRowSelectComb(SelectEvent event) {    
        this.totalvia = this.sumarViatico(this.comblist);
        this.nrocomb = this.comblist.size();
        this.totalcomb = this.sumarComb(this.comblist);
        this.totalex = this.sumarExtras(this.comblist);  
        this.total = this.sumarTotal(this.comblist);
    }
       public List<ValeCombustible> getValespen() {
        ValeCombDao valeCombDao = new ValeCombDaoImpl();
        this.valespen = valeCombDao.findVales(this.selectedCamion);
        return valespen;
    }
 public void btnCreateComb(ActionEvent actionEvent) {
        ValeCombDao valeDao = new ValeCombDaoImpl();
        String msg;        
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
        this.selectedValeComb.setUsuario(usuario);
        this.selectedValeComb.setEstado("Aceptado");  
        OrdenDeCargaDao ordenDao = new OrdenDeCargaDaoImpl();
        this.selectedValeComb.setCamion(this.selectedCamion);
        this.selectedValeComb.setPrecioComb(this.selectedPrecio.getMonto());
        this.selectedValeComb.setFecha(this.selectedCombustible.getFecha());
        this.selectedValeComb.setDescripcion("");
        this.orden= ordenDao.findOne();
        for(int i = 0; i < orden.size(); i++){                
            this.selectedOrdenDeCarga=orden.get(i);                
        }            
        this.selectedValeComb.setOrdenDeCarga(this.selectedOrdenDeCarga);
        System.out.print(this.selectedOrdenDeCarga.getIdOrdenDeCarga());
        if (valeDao.create(this.selectedValeComb)) {
                            usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
                            this.selectedCombustible.setUsuario(usuario);
                            this.selectedCombustible.setEstadoCobro("Pendiente");
                            this.selectedCombustible.setEstadoPago("Pendiente");
                            this.selectedCombustible.setDescripcion("");
                            this.selectedCombustible.setMontoComb(this.selectedValeComb.getMontoComb());
                            this.selectedCombustible.setViatico_1(this.selectedValeComb.getViatico());
                            this.selectedCombustible.setExtras(this.selectedValeComb.getExtras());
                            this.selectedCombustible.setMontoTotal(this.selectedValeComb.getMontoTotal());
                            this.selectedCombustible.setPrecio(this.selectedValeComb.getPrecioComb());
                            System.out.println(this.selectedValeCombustible.getViatico());
                             this.selectedCombustible.setValeCombustible(this.selectedValeComb);
                            if(this.selectedValeComb.getViatico()>0){
                                    System.out.println("hasta aca");
                                    this.selectedViatico.setMonto(this.selectedValeComb.getViatico());
                                    this.selectedViatico.setFecha(this.selectedCombustible.getFecha());
                                    Integer id;
                                    id= this.selectedCombustible.getIdCombustible();
                                    this.selectedViatico.setDescripcion(" Retirado del Surtidor N° B.: ".concat(this.selectedCombustible.getNBoleta()));
                                    this.selectedViatico.setEstado("Pendiente");
                                    this.selectedViatico.setCamion(this.selectedValeComb.getCamion());
                                    this.selectedViatico.setChofer(this.selectedValeComb.getCamion().getChofer());
                                    this.selectedViatico.setUsuario(usuario);
                                    this.selectedViatico.setOrdenDeCarga(this.selectedValeComb.getOrdenDeCarga());
                                    TipoViaticoDao viatico = new TipoViaticoDaoImpl();
                                    this.tipo = viatico.FindOne();
                                    System.out.println("hasta aca");
                                    for(int i=0; i<this.tipo.size(); i++){
                                        this.selectedViatico.setTipoViatico(this.tipo.get(i));
                                    }        
                                    ViaticoDao via = new ViaticoDaoImpl();
                                    System.out.println("hola");
                                    if(via.create(this.selectedViatico)){
                                        System.out.println("hola");
                                        this.selectedCombustible.setViatico(this.selectedViatico);
                                    }
                                   
                            }else{
                                System.out.print("Viatico 0");
                                ViaticoDao viati = new ViaticoDaoImpl();
                                this.viaticos = new ArrayList<Viatico>();
                                this.viaticos = viati.findOne();
                                for(int i =0; i< this.viaticos.size(); i++){
                                    this.selectedViatico = this.viaticos.get(i);
                                }
                                this.selectedCombustible.setViatico(this.selectedViatico);
                                
                                System.out.print(this.selectedCombustible.getViatico().getMonto());
                            }
                            CombDao combDao = new CombDaoImpl();
                            if (combDao.create(this.selectedCombustible)) {
                                msg = "Se creo correctamente el registro";
                                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg,"");
                                FacesContext.getCurrentInstance().addMessage(null, message);
                                this.selectedValeComb.setEstado("Aceptado");
                                Integer id;
                                id= this.selectedCombustible.getIdCombustible();
                                String surtidor;
                                surtidor=this.selectedCombustible.getValeCombustible().getProveedor().getNombre();
                                this.selectedViatico.setDescripcion(" Nro. de Comb:".concat(id.toString().concat(" Retirados del Surtidor: ".concat(surtidor))));
                                ViaticoDao via = new ViaticoDaoImpl();
                                if(via.update(this.selectedViatico)){
                                    System.out.println(this.selectedViatico.getDescripcion());
                                }
                                
                                if (valeDao.update(this.selectedValeCombustible)) {            
                                }
                                this.selectedCombustible = new Combustible();
                                this.selectedViatico = new Viatico();
                                this.selectedValeComb2 = this.selectedValeComb;
                                this.selectedValeComb = new ValeCombustible();
                                this.selectedCamion = new Camion();
                                this.selectedProveedor = new Proveedor();
                                this.selectedPrecio = new PrecioComb();
                                this.selectedCombustible.setFecha(new Date());
                            }else{
                                ViaticoDao via = new ViaticoDaoImpl();
                                if(via.delete(this.selectedViatico.getIdViatico())){

                                }
                                msg = "Error al crear registro.";
                                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
                                FacesContext.getCurrentInstance().addMessage(null, message);
                            }
            this.orden = new ArrayList<OrdenDeCarga>();
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
        

    } 
    public void btnUpdateComb(ActionEvent actionEvent) {
        CombDao combDao = new CombDaoImpl();
        String msg;
        if (combDao.update(this.selectedCombustible)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteComb(ActionEvent actionEvent) {
        CombDao combDao = new CombDaoImpl();
        this.selectedValeCombustible = this.selectedCombustible.getValeCombustible();
        String msg;
        if (combDao.delete(this.selectedCombustible.getIdCombustible())) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            ViaticoDao via = new ViaticoDaoImpl();
            this.selectedViatico = this.selectedCombustible.getViatico();
            if(this.selectedViatico.getIdViatico()!=14){
                via.delete(this.selectedViatico.getIdViatico());
            }                        
            this.selectedValeCombustible.setEstado("Pendiente");
            ValeCombDao valeDao = new ValeCombDaoImpl();
            if (valeDao.update(this.selectedValeCombustible)) {            
            }
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public void btnIniciar(ActionEvent actionEvent){
        this.selectedValeCombustible = new ValeCombustible();
        this.selectedCamion = new Camion();
        this.selectedCombustible = new Combustible();
        this.combustibles = new ArrayList<Combustible>();        
        this.valespen = new ArrayList<ValeCombustible>();
               
        this.selectedViatico = new Viatico();        
    }
    private Double sumarViatico(List<Combustible> comblist){
    Double suma =0.0;
       for(int i = 0; i < comblist.size(); i++){
           suma = suma + comblist.get(i).getViatico_1();
       }
        return suma;
    }
    private Double sumarComb(List<Combustible> comblist){
    Double suma =0.0;
       for(int i = 0; i < comblist.size(); i++){
           suma = suma + comblist.get(i).getMontoComb();
       }
        return suma;
    }
    private Double sumarExtras(List<Combustible> comblist){
    Double suma =0.0;
       for(int i = 0; i < comblist.size(); i++){
           suma = suma + comblist.get(i).getExtras();
       }
        return suma;
    }
    private Double sumarLitros(List<Combustible> comblist){
    Double suma =0.0;
       for(int i = 0; i < comblist.size(); i++){
           suma = suma + comblist.get(i).getLitros();
       }
        return suma;
    }
    private Double sumarTotal(List<Combustible> comblist){
    Double suma =0.0;
       for(int i = 0; i < comblist.size(); i++){
           suma = suma + comblist.get(i).getMontoTotal();
       }
        return suma;
    }
    public void btnCargar(ActionEvent event) {
        CombDao comb = new CombDaoImpl();
        this.buscomb = comb.findCamionT(this.selectedCamion, this.fechaini, this.fechafin);     
        this.busnrocomb = this.buscomb.size();
        this.bustotalcomb = this.sumarComb(this.buscomb);
        this.bustotalvia = this.sumarViatico(this.buscomb);
        this.bustotalex = this.sumarExtras(this.buscomb);
        this.bustotal = this.sumarTotal(this.buscomb);
        this.litros = this.sumarLitros(this.buscomb);
    }
    public void btnCargarPro(ActionEvent event) {
        CombDao comb = new CombDaoImpl();
        this.buscomb = comb.findCamionP(this.selectedProveedor, this.fechaini1, this.fechafin1);     
        this.busnrocomb = this.buscomb.size();
        this.bustotalcomb = this.sumarComb(this.buscomb);
        this.bustotalvia = this.sumarViatico(this.buscomb);
        this.bustotalex = this.sumarExtras(this.buscomb);
        this.bustotal = this.sumarTotal(this.buscomb);
        this.litros = this.sumarLitros(this.buscomb);
        
    }
    public void onRowSelectPrecio(SelectEvent event) {
        this.selectedValeComb.setPrecioComb(this.selectedPrecio.getMonto());
        //banpro = 1;
        //this.selectedValeComb.setMontoTotal(this.selectedValeComb.getLitros()*this.selectedValeComb.getPrecioComb());
        //this.selectedValeComb.setTipoCombustible(this.selectedPrecio.getTipoCombustible());
    }
    public void btnProcesar(ActionEvent event) {
        String msg;
        FacesMessage message;        
        Double li = this.selectedCombustible.getLitros();
        PrecioComb precio;
        Double total;
        precio = this.selectedPrecio;
        if(precio==null){
            precio = new PrecioComb();
        }        
        System.out.println("El precio seleccionado");
        System.out.println(precio.getMonto());
        System.out.println("VIATICO");
        System.out.println(this.selectedCombustible.getViatico_1());
        System.out.println("EXTRAS");
        System.out.println(this.selectedCombustible.getExtras());
        System.out.println("LITROS");
        System.out.println(this.selectedCombustible.getLitros());
            if(precio.getMonto()>0.0){
                this.selectedValeComb.setPrecioComb(this.selectedPrecio.getMonto());
                this.selectedValeComb.setMontoComb(this.selectedCombustible.getLitros()*this.selectedValeComb.getPrecioComb());
                System.out.println(this.selectedValeComb.getMontoComb());
                this.selectedValeComb.setTipoCombustible(this.selectedPrecio.getTipoCombustible()); 
                total = this.selectedValeComb.getViatico()+this.selectedValeComb.getExtras()+this.selectedValeComb.getMontoComb();
                this.selectedValeComb.setMontoTotal(total);
                this.selectedCombustible.setExtras(this.selectedValeComb.getExtras());
                this.selectedCombustible.setViatico_1(this.selectedValeComb.getViatico());
                if(this.selectedValeComb.getMontoComb()>0 || this.selectedValeComb.getExtras()>0){
                    this.chkBoxChecked = true;   
                    msg ="Se proceso con existo";
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
                }else{
                    msg ="Ingresar el litraje o los extras.";
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
                }

                //this.chkBoxChecked = true;                
            }else{
               msg ="Seleccionar Precio";
               message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg,"");
               
            }                                                   
        FacesContext.getCurrentInstance().addMessage(null, message);        
        //return msg;
    }
    public void exportarPDF2(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/vale.jasper"));
      codvale= this.selectedValeComb2.getIdValeCombustible();
      Class.forName("org.postgresql.Driver");
      conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/transpbase", "postgres", "macorittogo");
      Map<String, Object> parametros = new HashMap<String, Object>();
      parametros.put("vCodvale", codvale);
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=vale_comb.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
}
