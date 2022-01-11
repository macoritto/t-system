/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ProCombDao;
import dao.ProCombDaoImpl;
import dao.ProVariosDao;
import dao.ProVariosDaoImpl;
import dao.ValeVariosDao;
import dao.ValeVariosDaoImpl;
import dao.VariosDao;
import dao.VariosDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Camion;
import model.Proveedor;
import model.ProveedorVarios;
import model.Usuario;
import model.ValeVarios;
import model.Varios;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class VariosBean {

    private List<Varios> varios;
    private List<Varios> variosbus;
    private List<Varios> variosfecha;
    private ProveedorVarios selectedProveedor;
    private Varios selectedVarios;
    private List<ValeVarios> vales;
    private ValeVarios selectedValeVarios;
    private Camion selectedCamion;
    private List<ProveedorVarios> proveedores;
    private Usuario usuario;
    private Date fechaini;
    private Date fechafin;
    private Date fechaini1;
    private Date fechafin1;
    private Integer nrovarios;
    private Double montototal;
    public VariosBean() {
        this.selectedVarios = new Varios();
        this.selectedValeVarios = new ValeVarios();
        this.selectedProveedor = new ProveedorVarios();
        this.varios = new ArrayList<Varios>();
        VariosDao variosDao = new VariosDaoImpl();
        this.varios = variosDao.findAll();
        this.variosfecha = new ArrayList<Varios>();
        this.proveedores = new ArrayList<ProveedorVarios>();
        this.vales = new ArrayList<ValeVarios>();
        this.selectedCamion = new Camion();
    }

    public ProveedorVarios getSelectedProveedor() {
        return selectedProveedor;
    }

    public void setSelectedProveedor(ProveedorVarios selectedProveedor) {
        this.selectedProveedor = selectedProveedor;
    }

    public List<Varios> getVariosbus() {
        return variosbus;
    }

    public List<Varios> getVariosfecha() {
        return variosfecha;
    }

    public Date getFechaini() {
        return fechaini;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public Date getFechaini1() {
        return fechaini1;
    }

    public Date getFechafin1() {
        return fechafin1;
    }

    public Integer getNrovarios() {
        return nrovarios;
    }

    public Double getMontototal() {
        return montototal;
    }

    public void setVariosbus(List<Varios> variosbus) {
        this.variosbus = variosbus;
    }

    public void setVariosfecha(List<Varios> variosfecha) {
        this.variosfecha = variosfecha;
    }

    public void setFechaini(Date fechaini) {
        this.fechaini = fechaini;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public void setFechaini1(Date fechaini1) {
        this.fechaini1 = fechaini1;
    }

    public void setFechafin1(Date fechafin1) {
        this.fechafin1 = fechafin1;
    }

    public void setNrovarios(Integer nrovarios) {
        this.nrovarios = nrovarios;
    }

    public void setMontototal(Double montototal) {
        this.montototal = montototal;
    }

    public List<Varios> getVarios() {
        
        return varios;
    }
        public List<ValeVarios> getVales() {
        ValeVariosDao valeDao = new ValeVariosDaoImpl();
        this.vales = valeDao.findVales(this.selectedCamion);
        return vales;
    }
     public List<ProveedorVarios> getProveedores() {
        ProVariosDao proDao = new ProVariosDaoImpl();
        this.proveedores = proDao.findAll();
        return proveedores;
    }

    public Varios getSelectedVarios() {
        return selectedVarios;
    }

    public ValeVarios getSelectedValeVarios() {
        return selectedValeVarios;
    }

    public void setSelectedVarios(Varios selectedVarios) {
        this.selectedVarios = selectedVarios;
    }

    public void setSelectedValeVarios(ValeVarios selectedValeVarios) {
        this.selectedValeVarios = selectedValeVarios;
    }

    public Camion getSelectedCamion() {
        return selectedCamion;
    }

    public void setSelectedCamion(Camion selectedCamion) {
        this.selectedCamion = selectedCamion;
    }
    

    public void onRowSelectCamion(SelectEvent event) {
    }
    public void onRowSelectPro(SelectEvent event) {     
    }
    public void onRowSelectVale(SelectEvent event) {
        this.selectedVarios.setValeVarios(this.selectedValeVarios);
        this.selectedVarios.setMonto(this.selectedValeVarios.getMonto());
        this.selectedVarios.setDescripcion(this.selectedValeVarios.getDescripcion());
    }            
    
    public void btnCreateVarios(ActionEvent actionEvent) {
        VariosDao variosDao = new VariosDaoImpl();
        String msg;
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
        this.selectedVarios.setUsuario(usuario);
        this.selectedVarios.setEstadoPago("Pendiente");
        this.selectedVarios.setEstadoCobro("Pendiente");
        if (variosDao.create(this.selectedVarios)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedValeVarios.setEstado("Aceptado");
            ValeVariosDao valeDao = new ValeVariosDaoImpl();
            if (valeDao.update(this.selectedValeVarios)) {            
            }
            this.selectedCamion = new Camion();
            this.selectedValeVarios = new ValeVarios();
            this.selectedVarios = new Varios();
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    public void btnUpdateVarios(ActionEvent actionEvent) {
        VariosDao variosDao = new VariosDaoImpl();
        String msg;
        if (variosDao.update(this.selectedVarios)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteVarios(ActionEvent actionEvent) {
        VariosDao variosDao = new VariosDaoImpl();
        String msg;
        this.selectedValeVarios = this.selectedVarios.getValeVarios();
        if (variosDao.delete(this.selectedVarios.getIdVarios())) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedValeVarios.setEstado("Pendiente");
            ValeVariosDao valeDao = new ValeVariosDaoImpl();
            if (valeDao.update(this.selectedValeVarios)) {            
            }
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public void btnIniciar(ActionEvent actionEvent){
        this.selectedVarios = new Varios();
        this.selectedValeVarios = new ValeVarios();
        this.varios = new ArrayList<Varios>(); 
        this.proveedores = new ArrayList<ProveedorVarios>();
        this.vales = new ArrayList<ValeVarios>();
        this.selectedCamion = new Camion();   
    }
    public void btnCargar(ActionEvent event) {
        VariosDao varios = new VariosDaoImpl();
        this.variosbus = varios.findbus(this.selectedCamion, this.fechaini, this.fechafin);     
        this.nrovarios = this.variosbus.size();
        this.montototal = this.sumarTotal(this.variosbus);        
    }
    public void btnCargarPro(ActionEvent event) {
        VariosDao varios = new VariosDaoImpl();
        System.out.print("hola anda");
        this.variosfecha = varios.findfecha(this.selectedProveedor, this.fechaini1, this.fechafin1);     
        System.out.print("hola anda1");
        System.out.print(this.variosfecha.size());
        this.nrovarios = this.variosfecha.size();
        this.montototal = this.sumarTotal(this.variosfecha);     
        
    }
    private Double sumarTotal(List<Varios> varios){
    Double suma =0.0;
       for(int i = 0; i < varios.size(); i++){
           suma = suma + varios.get(i).getMonto();
       }
        return suma;
    }
}
