/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ClienteDao;
import dao.ClienteDaoImpl;
import dao.DestinoDao;
import dao.DestinoDaoImpl;
import dao.OrigenDao;
import dao.OrigenDaoImpl;
import dao.PreFleteDao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.PrecioFlete;
import dao.PreFleteDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.Cliente;
import model.UnidadDestino;
import model.UnidadOrigen;
import model.Usuario;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class preFleteBean {

    private List<PrecioFlete> precios;
    private List<PrecioFlete> preciosbaja;
    private PrecioFlete selectedPrecioFlete;
    private Object preFleteDao;
    private List<SelectItem> selectOneItemsCliente;
    private List<SelectItem> selectOneItemsOrigen;
    private List<SelectItem> selectOneItemsDestino;
    private UnidadOrigen selectedOrigen;
    private UnidadDestino selectedDestino;
    private Cliente selectedCliente;
    private Usuario usuario;

    /**
     * Creates a new instance of preFleteBean
     */
    public preFleteBean() {
        this.selectedPrecioFlete = new PrecioFlete();
        this.precios = new ArrayList<PrecioFlete>();
        this.preciosbaja = new ArrayList<PrecioFlete>();
        this.selectOneItemsCliente=new ArrayList<SelectItem>();
        this.selectOneItemsOrigen=new ArrayList<SelectItem>();
        this.selectOneItemsDestino=new ArrayList<SelectItem>();
        this.selectedPrecioFlete= new PrecioFlete();
        this.selectedPrecioFlete.setCliente(new Cliente());
        this.selectedPrecioFlete.setUnidadDestino(new UnidadDestino());
        this.selectedPrecioFlete.setUnidadOrigen(new UnidadOrigen());
        this.selectedCliente=new Cliente();
        this.selectedOrigen= new UnidadOrigen();
        this.selectedDestino= new UnidadDestino();
    }

    public List<SelectItem> getSelectOneItemsCliente() {
        this.selectOneItemsCliente = new ArrayList<SelectItem>();
        ClienteDao clienteDao = new ClienteDaoImpl();
        List<Cliente> clientes = clienteDao.SelectItems();
        for (Cliente cliente : clientes) {
            SelectItem selecItem = new SelectItem(cliente.getIdCliente(), cliente.getNombre());
            this.selectOneItemsCliente.add(selecItem);
        }
        return selectOneItemsCliente;
    }
        public List<SelectItem> getSelectOneItemsOrigen() {
        this.selectOneItemsOrigen = new ArrayList<SelectItem>();
        OrigenDao origenDao = new OrigenDaoImpl();
        List<UnidadOrigen> origenes = origenDao.SelectItems();
        for (UnidadOrigen origen : origenes) {
            SelectItem selecItem = new SelectItem(origen.getIdUnidadOrigen(), origen.getNombre());
            this.selectOneItemsOrigen.add(selecItem);
        }
        return selectOneItemsOrigen;
    }

    public Cliente getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    public List<SelectItem> getSelectOneItemsDestino() {
        this.selectOneItemsDestino = new ArrayList<SelectItem>();
        DestinoDao destinoDao = new DestinoDaoImpl();
        List<UnidadDestino> destinos = destinoDao.SelectItems();
        for (UnidadDestino destino : destinos) {
            SelectItem selecItem = new SelectItem(destino.getIdUnidadDestino(), destino.getNombre());
            this.selectOneItemsDestino.add(selecItem);
        }
        return selectOneItemsDestino;
    }

    public UnidadOrigen getSelectedOrigen() {
        return selectedOrigen;
    }

    public UnidadDestino getSelectedDestino() {
        return selectedDestino;
    }

    public void setSelectedOrigen(UnidadOrigen selectedOrigen) {
        this.selectedOrigen = selectedOrigen;
    }

    public void setSelectedDestino(UnidadDestino selectedDestino) {
        this.selectedDestino = selectedDestino;
    }

    public void setSelectOneItemsCliente(List<SelectItem> selectOneItemsCliente) {
        this.selectOneItemsCliente = selectOneItemsCliente;
    }

    public void setSelectOneItemsOrigen(List<SelectItem> selectOneItemsOrigen) {
        this.selectOneItemsOrigen = selectOneItemsOrigen;
    }

    public void setSelectOneItemsDestino(List<SelectItem> selectOneItemsDestino) {
        this.selectOneItemsDestino = selectOneItemsDestino;
    }

    public List<PrecioFlete> getPrecios() {
        PreFleteDao preFleteDao = new PreFleteDaoImpl();
        this.precios = preFleteDao.findAll();
        return precios;
    }
    public List<PrecioFlete> getPreciosBaja() {
        PreFleteDao preFleteDao = new PreFleteDaoImpl();
        this.preciosbaja = preFleteDao.findBaja();
        return preciosbaja;
    }

    public PrecioFlete getSelectedPrecioFlete() {
        return selectedPrecioFlete;
    }

    public void setSelectedPrecioFlete(PrecioFlete selectedPrecioFlete) {
        this.selectedPrecioFlete = selectedPrecioFlete;
    }

    public void btnCreatePrecioFlete(ActionEvent actionEvent) {
        PreFleteDao preFleteDao = new PreFleteDaoImpl();
        String msg;
        System.out.print("ESTE ES EL PRECIO CON EL IVA");
        System.out.print(this.selectedPrecioFlete.getPrecioiva());
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
        this.selectedPrecioFlete.setUsuario(usuario);
        this.selectedPrecioFlete.setEstado("Alta");
        if (preFleteDao.create(this.selectedPrecioFlete)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedPrecioFlete = new PrecioFlete();
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnUpdatePrecioFlete(ActionEvent actionEvent) {
        PreFleteDao preFleteDao = new PreFleteDaoImpl();
        String msg;
        System.out.print("ESTE ES EL PRECIO CON EL IVA");
        System.out.print(this.selectedPrecioFlete.getPrecioiva());
        if (preFleteDao.update(this.selectedPrecioFlete)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteUnidadOrigen(ActionEvent actionEvent) {
        PreFleteDao preFleteDao = new PreFleteDaoImpl();
        String msg;
        if (preFleteDao.delete(this.selectedPrecioFlete.getIdPrecioFlete())) {
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
        this.selectedPrecioFlete = new PrecioFlete();
        this.selectedCliente= new Cliente();
        this.selectedOrigen= new UnidadOrigen();
        this.selectedDestino= new UnidadDestino();
    }
    public void onRowSelectSeguro(SelectEvent event){
        this.selectedPrecioFlete.setCliente(this.selectedCliente);
    }
        public void onRowSelectOrigen(SelectEvent event){
        this.selectedPrecioFlete.setUnidadOrigen(this.selectedOrigen);
    }
    public void onRowSelectDestino(SelectEvent event){
        this.selectedPrecioFlete.setUnidadDestino(this.selectedDestino);
    }
}
