/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CombContraDao;
import dao.CombContraDaoImpl;
import dao.CombViaticoDao;
import dao.CombViaticoDaoImpl;
import dao.ContraDao;
import dao.ContraDaoImpl;
import dao.ValeCombDao;
import dao.ValeCombDaoImpl;
import dao.ViajeDao;
import dao.ViajeDaoImpl;
import dao.ViaticoDao;
import dao.ViaticoDaoImpl;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Contrasenha;
import model.Viaje;
import model.Camion;
import model.CombustibleContra;
import model.OrdenDeCarga;
import model.Usuario;
import model.ValeCombustible;
import model.Viatico;
import model.ViaticoContra;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class contraBean {
    private List<Contrasenha> contrasenhas;
    private List<Viaje> pendientes;
    private Contrasenha selectedContra;
    private Contrasenha selectedContra2;
    private Viaje selectedViaje;
    private Camion selectedCamion;
    private List<ValeCombustible> vales;
    private Usuario usuario;
    private OrdenDeCarga selectedOrden;
    private Double TotalComb;
    private Double TotalViatico;
    private List<Viatico> viaticos;
    private Viatico selectedViatico;
    private CombustibleContra selectedCombcontra;
    private List<CombustibleContra> combcontras;
    private ViaticoContra selectedViaContra;
    private List<ViaticoContra> viacontras;
    private List<ViaticoContra> viaticovista;
    private List<CombustibleContra> combvista;
    private Integer codextracto;
    private Connection conexion;
    private Double totalflete;
    private Double totalflete1;
    private Double totalfletevista;
    public contraBean() {
        this.contrasenhas = new ArrayList<Contrasenha>();
        this.pendientes = new ArrayList<Viaje>();
        this.selectedViaje = new Viaje();
        this.selectedContra = new Contrasenha();
        this.selectedContra2 = new Contrasenha();
        this.selectedCamion = new Camion();
        this.vales = new ArrayList<ValeCombustible>();
        this.selectedOrden = new OrdenDeCarga();
        this.viaticos = new ArrayList<Viatico>();
        this.selectedViatico = new Viatico();
        this.selectedCombcontra = new CombustibleContra();
        this.combcontras = new ArrayList<CombustibleContra>();
        this.selectedViaContra = new ViaticoContra();
        this.viacontras = new ArrayList<ViaticoContra>();
        this.viaticovista = new ArrayList<ViaticoContra>();
        this.combvista = new ArrayList<CombustibleContra>();
    }
    public List<Viaje> getPendientes() {
        ViajeDao viajeDao = new ViajeDaoImpl();
        this.pendientes = viajeDao.findContra(this.selectedCamion);
        return pendientes;
    }

    public List<ViaticoContra> getViaticovista() {
        CombViaticoDao via = new CombViaticoDaoImpl();
        this.viaticovista = via.findByEx(this.selectedContra);
        this.TotalViatico = this.sumarTotalViaContra(viaticovista);
        return viaticovista;
    }

    public List<CombustibleContra> getCombvista() {
        CombContraDao combcontra = new CombContraDaoImpl();
        this.combvista = combcontra.findByEx(this.selectedContra);
        this.TotalComb = this.sumarTotalViaComb(combvista);
        return combvista;
    }

    public List<Viatico> getViaticos() {
        ViaticoDao viaticoDao = new ViaticoDaoImpl();
        viaticos = viaticoDao.findContra(this.selectedViaje.getOrdenDeCarga());    
        ValeCombDao combDao = new ValeCombDaoImpl();
        this.vales = combDao.findContra(this.selectedViaje.getOrdenDeCarga());
        this.TotalViatico = this.sumarTotalPago(viaticos)+this.sumarTotalVia(vales);
        for(int i=0; i<this.viaticos.size(); i++){
            this.selectedViaContra.setViatico(this.viaticos.get(i));
            this.viacontras.add(this.selectedViaContra);
            this.selectedViaContra = new ViaticoContra();
        }
        return viaticos;
    }
    
    public Double getTotalComb() {
        return TotalComb;
    }

    public Double getTotalViatico() {
        return TotalViatico;
    }

    public Double getTotalfletevista() {
        return totalfletevista;
    }

    public void setTotalfletevista(Double totalfletevista) {
        this.totalfletevista = totalfletevista;
    }    

    public Double getTotalflete() {
        this.totalflete = (this.selectedViaje.getMontoPagar()+this.selectedViaje.getMontofaltante()+this.selectedViaje.getMontoseguro());
        this.totalfletevista = (this.selectedContra.getViaje().getMontoPagar()+this.selectedContra.getViaje().getMontofaltante()+this.selectedContra.getViaje().getMontoseguro());
        return totalflete;
    }

    public void setTotalflete(Double totalflete) {
        this.totalflete = totalflete;
    }
    
    public void setTotalComb(Double TotalComb) {
        this.TotalComb = TotalComb;
    }

    public void setTotalViatico(Double TotalViatico) {
        this.TotalViatico = TotalViatico;
    }
    
    public List<Contrasenha> getContrasenhas() {
        ContraDao contraDao = new ContraDaoImpl();
        this.contrasenhas = contraDao.findAll();
        return contrasenhas;
    }
    public Contrasenha getSelectedContra() {
        return selectedContra;
    }

    public Viaje getSelectedViaje() {
        return selectedViaje;
    }

    public List<ValeCombustible> getVales() {
        ValeCombDao combDao = new ValeCombDaoImpl();
        this.vales = combDao.findContra(this.selectedViaje.getOrdenDeCarga());
        System.out.println("Prueba");
        for(int i =0;i<this.vales.size(); i++){
            System.out.print(i);
        }
        Double saldo =0.0;
        Double monto;
        Double faltante;
        Double seguro;
        this.TotalComb = this.sumarTotalComb(vales);        
        faltante = this.selectedViaje.getMontofaltante();
        seguro = this.selectedViaje.getSeguro().getMonto();
        monto = this.selectedViaje.getMontoPagar()+seguro+faltante;
        saldo = monto-seguro-faltante-this.TotalComb-this.TotalViatico;
        this.selectedContra.setSaldo(saldo);
        for(int i=0; i<this.vales.size(); i++){
            this.selectedCombcontra.setValeCombustible(this.vales.get(i));
            this.combcontras.add(this.selectedCombcontra);
        }
        return vales;
    }

    public void setSelectedContra(Contrasenha selectedContra) {
        this.selectedContra = selectedContra;
    }

    public void setSelectedViaje(Viaje selectedViaje) {
        this.selectedViaje = selectedViaje;
    }

    public Camion getSelectedCamion() {
        return selectedCamion;
    }

    public void setSelectedCamion(Camion selectedCamion) {
        this.selectedCamion = selectedCamion;
    }
    public void onRowSelectViaje(SelectEvent event) {
        this.selectedContra.setViaje(this.selectedViaje);
    }
    public void onRowSelectCamion(SelectEvent event) {

    }
    public void onRowSelectContra(SelectEvent event) {

    }
    public void exportarPDF2(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/contra.jasper"));      
      codextracto= this.selectedContra2.getIdContrasenha();
      this.totalflete1 = this.selectedContra2.getViaje().getMontoPagar()+this.selectedContra2.getViaje().getMontofaltante()+this.selectedContra2.getViaje().getMontoseguro();
      Class.forName("org.postgresql.Driver");
      conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/transpbase", "postgres", "macorittogo");
      Map<String, Object> parametros = new HashMap<String, Object>();
      parametros.put("vCodpago", codextracto);
      parametros.put("bruto", totalflete1);
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=contrasenha.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
    public void exportarPDF3(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/contra.jasper"));      
      codextracto= this.selectedContra.getIdContrasenha();
      Class.forName("org.postgresql.Driver");
      this.totalflete1 = this.selectedContra.getViaje().getMontoPagar()+this.selectedContra.getViaje().getMontofaltante()+this.selectedContra.getViaje().getMontoseguro();
      conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/transpbase", "postgres", "macorittogo");
      Map<String, Object> parametros = new HashMap<String, Object>();
      parametros.put("vCodpago", codextracto);
      parametros.put("bruto", totalflete1);
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=contrasenha.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
     public void btnCreateContra(ActionEvent actionEvent) {
        ContraDao contraDao = new ContraDaoImpl();
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
        this.selectedContra.setUsuario(usuario);        
        this.selectedContra.setViatico(this.TotalViatico);
        this.selectedContra.setCombustible(this.TotalComb);
        String msg;
        if (contraDao.create(this.selectedContra, this.combcontras, this.viacontras)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedViaje.setEstadocontra("Aceptado");
            ViajeDao viajeDao = new ViajeDaoImpl();
            if (viajeDao.update(this.selectedViaje)) {            
            }
            this.selectedContra2 = this.selectedContra;
            this.selectedCamion = new Camion();
            this.selectedContra = new Contrasenha();            
            this.selectedViaContra = new ViaticoContra();
            this.selectedViaje = new Viaje();
            this.viacontras = new ArrayList<ViaticoContra>();
            this.combcontras = new ArrayList<CombustibleContra>();
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnUpdateContra(ActionEvent actionEvent) {
        ContraDao contraDao = new ContraDaoImpl();
        String msg;
        if (contraDao.update(this.selectedContra)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteContra(ActionEvent actionEvent) {
        ContraDao contraDao = new ContraDaoImpl();
        String msg;
        this.combcontras = new ArrayList<CombustibleContra>();
            CombContraDao com = new CombContraDaoImpl();            
            this.combcontras = com.findByEx(this.selectedContra);
            for(int i=0; i<this.combcontras.size(); i++){
                CombContraDao comb = new CombContraDaoImpl();            
                comb.delete(this.combcontras.get(i).getId());
        }        
        this.viacontras = new ArrayList<ViaticoContra>();
            CombViaticoDao combvia = new CombViaticoDaoImpl();
            this.viacontras = combvia.findByEx(this.selectedContra);
            for(int i=0; i<this.viacontras.size(); i++){
                combvia.delete(this.viacontras.get(i).getId());
        }
        CombViaticoDao viacomb = new CombViaticoDaoImpl();
            this.viacontras = new ArrayList<ViaticoContra>();
            this.viacontras = viacomb.findByEx(this.selectedContra);
            for(int i=0; i<this.viacontras.size(); i++){                
                viacomb.delete(this.viacontras.get(i).getId());
            }
        if (contraDao.delete(this.selectedContra.getIdContrasenha())) {
            this.selectedViaje = this.selectedContra.getViaje();
            this.selectedViaje.setEstadocontra("Pendiente");
            ViajeDao viaje = new ViajeDaoImpl();
            if(viaje.update(this.selectedViaje)){
            }            
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public void btnIniciar(ActionEvent actionEvent){
        this.contrasenhas = new ArrayList<Contrasenha>();
        this.pendientes = new ArrayList<Viaje>();
        this.selectedViaje = new Viaje();
        this.selectedContra = new Contrasenha();
        this.selectedCamion = new Camion(); 
    }
    private Double sumarTotalVia(List<ValeCombustible> vales){
    Double suma =0.0;
       for(int i = 0; i < vales.size(); i++){
           suma = suma + vales.get(i).getViatico();
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

    private Double sumarTotalPago(List<Viatico> viaticos){
    Double suma =0.0;
       for(int i = 0; i < viaticos.size(); i++){
           suma = suma + viaticos.get(i).getMonto();
       }
        return suma;
    }
    private Double sumarTotalViaContra(List<ViaticoContra> viaticocontra){
    Double suma =0.0;
       for(int i = 0; i < viaticocontra.size(); i++){
           suma = suma + viaticocontra.get(i).getViatico().getMonto();
       }
        return suma;
    }
    private Double sumarTotalViaComb(List<CombustibleContra> combcontra){
    Double suma =0.0;
       for(int i = 0; i < combcontra.size(); i++){
           suma = suma + combcontra.get(i).getValeCombustible().getMontoComb();
       }
        return suma;
    }
}
