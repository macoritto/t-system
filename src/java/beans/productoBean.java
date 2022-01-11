/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ClienteDao;
import dao.ClienteDaoImpl;
import dao.ProductoDao;
import dao.ProductoDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.Cliente;
import model.Producto;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class productoBean {
    private List<Producto> productos;
    private Producto selectedProducto;
    private Cliente selectedCliente;
    private List<SelectItem> selectOneItemsCliente;
    private Object productoDao;
    public productoBean() {
        this.productos = new ArrayList<Producto>();
        this.selectedProducto = new Producto();
        this.selectedCliente= new Cliente();
    }

    public List<Producto> getProductos() {
        ProductoDao productoDao = new ProductoDaoImpl();
        this.productos = productoDao.findAll();
        return productos;
    }

    public Cliente getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
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

    public Producto getSelectedProducto() {
        return selectedProducto;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void setSelectedProducto(Producto selectedProducto) {
        this.selectedProducto = selectedProducto;
    }
     public void btnCreateProducto(ActionEvent actionEvent) {
        ProductoDao productoDao = new ProductoDaoImpl();
        String msg;
        if (productoDao.create(this.selectedProducto)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedProducto= new Producto();
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    public void btnUpdateProducto(ActionEvent actionEvent) {
        ProductoDao productoDao = new ProductoDaoImpl();
        String msg;
        if (productoDao.update(this.selectedProducto)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteProducto(ActionEvent actionEvent) {
        ProductoDao productoDao = new ProductoDaoImpl();
        String msg;
        if (productoDao.delete(this.selectedProducto.getIdProducto())) {
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
        this.productos = new ArrayList<Producto>();
        this.selectedProducto = new Producto();
    }
    public void onRowSelectSeguro(SelectEvent event){
        this.selectedProducto.setCliente(this.selectedCliente);
    }
}
