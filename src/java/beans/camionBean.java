/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CamionDao;
import dao.CamionDaoImpl;
import dao.CarretaDao;
import dao.CarretaDaoImpl;
import dao.ChoferDao;
import dao.ChoferDaoImpl;
import dao.PropietarioDao;
import dao.PropietarioDaoImpl;
import dao.TipoCamionDao;
import dao.TipoCamionDaoImpl;
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
import model.Camion;
import model.TipoCamion;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import model.Chofer;
import model.Carreta;
import model.Propietario;
import model.TipoCarreta;
import model.Usuario;
import model.Viaje;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


/**
 *
 * @author Usuario
 */
@ManagedBean(name="camionBean")
@SessionScoped
public class camionBean {
    
    private List<Camion> camiones;
    private List<TipoCamion> tipos;
    private Camion selectedCamion;
    private List<Viaje> fleteview;
    private Object camionDao;
    private Chofer selectedChofer;
    private TipoCarreta selectedTipo;
    private Carreta selectedCarreta;
    private Propietario selectedPropietario;
    private List<SelectItem> selectOneItemsTipoCamion;
    private TipoCamion selectedTipoCamion;
    private List<Carreta> carre;
    private Camion camion;
    private Integer pcaba;
    private Integer pcarre;
    private Double totalpeso;
    private Integer totalflete;
    private Double totalbruto;    
    private List<SelectItem> selectEstado;
    public camionBean() {
        this.camiones = new ArrayList<Camion>();
        CamionDao camionDao = new CamionDaoImpl();
        this.camiones = camionDao.findAll();
        this.tipos = new ArrayList<TipoCamion>();
        this.selectedTipoCamion = new TipoCamion();
        this.selectedCamion = new Camion();
        this.selectedChofer = new Chofer();
        this.selectedCarreta = new Carreta();
        this.selectedPropietario = new Propietario();
        this.fleteview = new ArrayList<Viaje>();
        this.selectedTipo= new TipoCarreta();
    }
    private boolean chkBoxChecked;
    public boolean isBtnDisabled() {        
        return !this.chkBoxChecked;
    }
    public List<Viaje> getFleteview() {
        ViajeDao viajeDao = new ViajeDaoImpl();        
        this.fleteview = viajeDao.findCamion(this.selectedCamion);
        this.totalflete = this.fleteview.size();
        this.totalbruto = this.sumarFlete(this.fleteview);
        this.totalpeso = this.sumarPeso(this.fleteview);
        return fleteview;
    }

    public TipoCarreta getSelectedTipo() {
        return selectedTipo;
    }

    public void setSelectedTipo(TipoCarreta selectedTipo) {
        this.selectedTipo = selectedTipo;
    }

    public Double getTotalpeso() {
        return totalpeso;
    }

    public Integer getTotalflete() {
        return totalflete;

    }

    public Double getTotalbruto() {
        return totalbruto;
    }
    
    public void setChkBoxChecked(boolean chkBoxChecked) {
        this.chkBoxChecked = chkBoxChecked;
    }
     public List<Camion> getCamiones() {
        
        return camiones;
    }
     public List<TipoCamion> getTipos() {
        TipoCamionDao tipoDao = new TipoCamionDaoImpl();
        this.tipos = tipoDao.findAll();
        return tipos;
    } 

    public TipoCamion getSelectedTipoCamion() {
        return selectedTipoCamion;
    }

    public void setSelectedTipoCamion(TipoCamion selectedTipoCamion) {
        this.selectedTipoCamion = selectedTipoCamion;
    }
     
    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/flo.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getCamiones()));
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=reporte.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
      System.out.println("hola mundo");
    }
    public List<SelectItem> getSelectOneItemsTipoCamion() {
      this.selectOneItemsTipoCamion = new ArrayList<SelectItem>();
      TipoCamionDao tipocamionDao = new TipoCamionDaoImpl();
      List <TipoCamion> tipocamiones = tipocamionDao.SelectItems();
      for (TipoCamion tipocamion : tipocamiones){
          SelectItem selecItem = new SelectItem(tipocamion.getIdTipoCamion(), tipocamion.getDescripcion());
          this.selectOneItemsTipoCamion.add(selecItem);
        }
      return selectOneItemsTipoCamion;
    }
    public void onRowSelectTipo2(SelectEvent event){
        this.selectedCarreta.setTipoCarreta(this.selectedTipo);
    }
    public Chofer getSelectedChofer() {
        return selectedChofer;
    }
     public Carreta getSelectedCarreta() {
        return selectedCarreta;
    }
    public void setSelectedCarreta(Carreta selectedCarreta) {
        this.selectedCarreta = selectedCarreta;
    }
    public Propietario getSelectedPropietario() {
        return selectedPropietario;
    }
    public void setSelectedPropietario(Propietario selectedPropietario) {
        this.selectedPropietario = selectedPropietario;
    }
    public void setSelectedChofer(Chofer selectedChofer) {
        this.selectedChofer = selectedChofer;
    }
    public Camion getSelectedCamion() {
        return selectedCamion;
    }

    public void setSelectedCamion(Camion selectedCamion) {
        this.selectedCamion = selectedCamion;
    }
    public void onRowSelectChofer(SelectEvent event){
    this.selectedCamion.setChofer(this.selectedChofer);
    }
    public void onRowSelectCamion(SelectEvent event){
    
    }
    public void onRowSelectTipo(SelectEvent event){
    this.selectedCamion.setTipoCamion(this.selectedTipoCamion);
    pcaba= this.selectedTipoCamion.getPeso();
    
    if(this.selectedTipoCamion.getIdTipoCamion()==2 || this.selectedTipoCamion.getIdTipoCamion()==3){
       Boolean hola= false;
       this.chkBoxChecked=hola;
       CarretaDao carDao = new CarretaDaoImpl();
       this.carre = carDao.findOne();       
       for(int i =0; i<this.carre.size(); i++){
           this.selectedCarreta = this.carre.get(i);           
           System.out.println(this.selectedCarreta.getChapaCarreta());           
       }
       this.selectedCamion.setCarreta(this.selectedCarreta);
       pcarre= this.selectedCarreta.getTipoCarreta().getPeso();
    }else{
       Boolean hola= true;
       this.chkBoxChecked =hola;
       this.selectedCarreta = new Carreta();
    }
    }
    public void onRowSelectCarreta(SelectEvent event){
    this.selectedCamion.setCarreta(this.selectedCarreta);    
    pcarre= this.selectedCarreta.getTipoCarreta().getPeso();
    }
    public void onRowSelectPropietario(SelectEvent event){
    this.selectedCamion.setPropietario(this.selectedPropietario);
    }
    public void btncargar(ActionEvent actionEvent){
    System.out.println(this.selectedCamion.getChofer().getNombre());
    this.selectedChofer=this.selectedCamion.getChofer();
    this.selectedCarreta=this.selectedCamion.getCarreta();
    this.selectedPropietario=this.selectedCamion.getPropietario();
    System.out.println(this.selectedChofer.getNombre());
    this.selectedTipoCamion=this.selectedCamion.getTipoCamion();
    pcarre= this.selectedCamion.getCarreta().getTipoCarreta().getPeso();
    pcaba= this.selectedCamion.getTipoCamion().getPeso();
    System.out.print("    El estado es  ");
    //System.out.print(this.selectedCamion.getEstado());
    }
    public void sumarPeso(Integer sumarPeso){
        sumarPeso=this.selectedCamion.getTipoCamion().getPeso()+this.selectedCamion.getCarreta().getTipoCarreta().getPeso();
    }
            
    public void btnCreateCamion(ActionEvent actionEvent) {
        CamionDao camionDao = new CamionDaoImpl();
        String msg;
        this.selectedCamion.setEstado("Alta");
        this.selectedCamion.setPeso(pcaba+pcarre);        
        if (camionDao.create(this.selectedCamion)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedTipoCamion = new TipoCamion();
            this.selectedCamion = new Camion();
            this.selectedChofer = new Chofer();
            this.selectedCarreta = new Carreta();
            this.selectedPropietario = new Propietario();
            
            this.camiones = camionDao.findAll();
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    public List<SelectItem> getSelectEstado() {     
        this.selectEstado = new ArrayList<SelectItem>();        
        SelectItem selecItema = new SelectItem("Alta");
        this.selectEstado.add(selecItema);
        SelectItem selecItemp = new SelectItem("Baja");
        this.selectEstado.add(selecItemp);            
        return selectEstado;
    }
    public void btnIniciar(ActionEvent actionEvent){
        this.selectedCamion = new Camion();
        this.selectedCarreta = new Carreta();
        this.selectedChofer = new Chofer();
        this.selectedPropietario = new Propietario();
        this.selectedTipoCamion = new TipoCamion();       
    }
    public void btnUpdateCamion(ActionEvent actionEvent) {
        CamionDao camionDao = new CamionDaoImpl();
        pcaba= this.selectedCamion.getTipoCamion().getPeso();  
        this.selectedCamion.setPeso(pcarre+pcaba);        
        System.out.println(pcaba);
        System.out.println(pcarre);
        String msg;
        if (camionDao.update(this.selectedCamion)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            
            this.camiones = camionDao.findAll();
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteCamion(ActionEvent actionEvent) {
        CamionDao camionDao = new CamionDaoImpl();
        String msg;
        if (camionDao.delete(this.selectedCamion.getChapaCamion())) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            
            this.camiones = camionDao.findAll();
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public void btnCreateChofer(ActionEvent actionEvent) {
        ChoferDao choferDao = new ChoferDaoImpl();
        String msg;
        Date hoy = new Date();
        String fecha = new SimpleDateFormat("YYYY-MM-dd").format(hoy);
        this.selectedChofer.setFechaNacimiento(java.sql.Date.valueOf(fecha));
        if (choferDao.create(this.selectedChofer)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedCamion.setChofer(this.selectedChofer);
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    public void btnCreateCarreta(ActionEvent actionEvent) {
        CarretaDao carretaDao = new CarretaDaoImpl();
        String msg;
        Date hoy = new Date();        
        if (carretaDao.create(this.selectedCarreta)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedCamion.setCarreta(this.selectedCarreta);
            pcarre= this.selectedCarreta.getTipoCarreta().getPeso();
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    public void btnCreatePropietario(ActionEvent actionEvent) {
        PropietarioDao propietarioDao = new PropietarioDaoImpl();
        String msg;
        Date hoy = new Date();
        String fecha = new SimpleDateFormat("YYYY-MM-dd").format(hoy);
        this.selectedPropietario.setFechaCreacion(java.sql.Date.valueOf(fecha));
        if (propietarioDao.create(this.selectedPropietario)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedCamion.setPropietario(this.selectedPropietario);
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    private Double sumarFlete(List<Viaje> fletelist){
    Double suma =0.0;
       for(int i = 0; i < fletelist.size(); i++){
           suma = suma + fletelist.get(i).getMontoPagar();
       }
        return suma;
    }
    private Double sumarPeso(List<Viaje> fletelist){
    Double suma =0.0;
       for(int i = 0; i < fletelist.size(); i++){
           suma = suma + fletelist.get(i).getPesoDestino();
       }
        return suma;
    }
}
