package beans;

import dao.CamionDao;
import dao.CamionDaoImpl;
import dao.ClienteDao;
import dao.ClienteDaoImpl;
import dao.DestinoDao;
import dao.DestinoDaoImpl;
import dao.OrdenDeCargaDao;
import dao.OrdenDeCargaDaoImpl;
import dao.OrigenDao;
import dao.OrigenDaoImpl;
import dao.ProductoDao;
import dao.ProductoDaoImpl;
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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Camion;
import model.Cliente;
import model.OrdenDeCarga;
import model.Producto;
import model.UnidadDestino;
import model.UnidadOrigen;
import model.Usuario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.event.SelectEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author USER
 */
@ManagedBean
@ViewScoped
public class ordenBean {

    private List<OrdenDeCarga> ordenes;
    private List<Camion> camiones;
    private List<OrdenDeCarga> ordenpendientes;
    private List<OrdenDeCarga> pendientes;
    private List<OrdenDeCarga> anulados;
    private List<OrdenDeCarga> aceptados;
    private OrdenDeCarga selectedOrdenDeCarga;
    private List<OrdenDeCarga> ordeneslist;
    private OrdenDeCarga selectedOrdenDeCarga2;
    private Producto selectedProducto;
    private List <Producto> productos;
    private Object ordenDao;
    private Camion selectedCamion;
    private Usuario selectedUsuario;
    private Cliente selectedCliente;
    private UnidadOrigen selectedOrigen;
    private UnidadDestino SelectedDestino;
    private List<SelectItem> selectOneItemsCliente;
    private List<SelectItem> selectOneItemsOrigen;
    private List<SelectItem> selectOneItemsDestino;
    private List<SelectItem> selectOneItemsProducto;
    private Usuario usuario;
    private Integer codorden;
    private Connection conexion;    
    private String nomusu;
    private List<SelectItem> selectEstado;
    private String camion;
    private String update;
    
    public ordenBean() {
        this.ordenes = new ArrayList<OrdenDeCarga>();
        OrdenDeCargaDao ordenDao = new OrdenDeCargaDaoImpl();
        this.ordenes = ordenDao.findAll();
        this.pendientes = new ArrayList<OrdenDeCarga>();
        this.pendientes = ordenDao.findPendiente();
        this.anulados = new ArrayList<OrdenDeCarga>();
        this.anulados = ordenDao.findAnulado();
        this.aceptados = new ArrayList<OrdenDeCarga>();
        this.aceptados = ordenDao.findAceptado();
        this.ordenpendientes = new ArrayList<OrdenDeCarga>();
        this.selectedOrdenDeCarga = new OrdenDeCarga();
        this.selectedOrdenDeCarga2 = new OrdenDeCarga();
        this.selectedCamion = new Camion();
        this.selectedCliente = new Cliente();
        this.selectedOrigen = new UnidadOrigen();
        this.SelectedDestino = new UnidadDestino();
        this.ordeneslist = new ArrayList<OrdenDeCarga>();
        this.camiones = new ArrayList<Camion>();
        this.selectedOrdenDeCarga.setFechaEmision(new Date());     
        this.selectedProducto= new Producto();
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
    
    public List<SelectItem> getSelectEstado() {
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
        String nomusu= this.usuario.getUsuario();
        if(nomusu.equals("carlos")){        
            this.selectEstado = new ArrayList<SelectItem>();        
            SelectItem selecItema = new SelectItem("Anulado");
            this.selectEstado.add(selecItema);
            SelectItem selecItemp = new SelectItem("Pendiente");
            this.selectEstado.add(selecItemp);
            SelectItem selecItemc = new SelectItem("Aceptado");
            this.selectEstado.add(selecItemc);                
        }else{
            this.selectEstado = new ArrayList<SelectItem>();        
            SelectItem selecItema = new SelectItem("Anulado");
            this.selectEstado.add(selecItema);
        }
        return selectEstado;
    }
    public Cliente getSelectedCliente() {
        return selectedCliente;
    }

    public UnidadOrigen getSelectedOrigen() {
        return selectedOrigen;
    }

    public UnidadDestino getSelectedDestino() {
        return SelectedDestino;
    }
    public String getCamion() {
        return camion;
    }
    public void setCamion(String camion) {
        this.camion = camion;
    }

    public Producto getSelectedProducto() {
        return selectedProducto;
    }

    public void setSelectedProducto(Producto selectedProducto) {
        this.selectedProducto = selectedProducto;
    }
    
    public String getUpdate() {
        return update;
    }
    public void setUpdate(String update) {
        this.update = update;
    }
    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    public void setSelectedOrigen(UnidadOrigen selectedOrigen) {
        this.selectedOrigen = selectedOrigen;
    }

    public void setSelectedDestino(UnidadDestino SelectedDestino) {
        this.SelectedDestino = SelectedDestino;
    }

    public List<OrdenDeCarga> getOrdeneslist() {
        return ordeneslist;
    }    

    public void setOrdeneslist(List<OrdenDeCarga> ordeneslist) {
        this.ordeneslist = ordeneslist;
    }
    
    public List<SelectItem> getSelectOneItemsProducto() {
//        this.selectOneItemsProducto = new ArrayList<SelectItem>();
//        ProductoDao productoDao = new ProductoDaoImpl();
//        List<Producto> productos = productoDao.SelectItemsCli(this.selectedCliente);
//        for (Producto producto : productos) {
//            SelectItem selecItem = new SelectItem(producto.getIdProducto(), producto.getNombre());
//            this.selectOneItemsProducto.add(selecItem);
//        }
        return selectOneItemsProducto;
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
    
    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/ordenlist.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getOrdeneslist()));
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=reporte_ordenes.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
    public void exportarPDF2(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/orden.jasper"));
      codorden= this.selectedOrdenDeCarga2.getIdOrdenDeCarga();    
      usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
      nomusu = this.usuario.getUsuario();
      Class.forName("org.postgresql.Driver");
      conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5433/newtranspbase", "postgres", "macorittogo");
      Map<String, Object> parametros = new HashMap<String, Object>();
      parametros.put("vCodorden", codorden);
      parametros.put("usuario", nomusu);
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=orden_de_carga.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
    }
    public void exportarPDF3(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException {
      File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/orden.jasper"));
      codorden= this.selectedOrdenDeCarga.getIdOrdenDeCarga();    
      usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
      nomusu = this.usuario.getUsuario();
      Class.forName("org.postgresql.Driver");
      conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/newtranspbase", "postgres", "macorittogo");
      Map<String, Object> parametros = new HashMap<String, Object>();
      parametros.put("vCodorden", codorden);
      parametros.put("usuario", nomusu);
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      response.addHeader("Content-disposition", "attachment; filename=orden_de_carga.pdf");
      ServletOutputStream stream = response.getOutputStream();
      JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
      stream.flush();
      stream.close();
      FacesContext.getCurrentInstance().responseComplete();
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
    public List<OrdenDeCarga> getOrdenes() {
        
        return ordenes;
    }
    public List<Camion> getCamiones() {
        return camiones;
    }
    public List<OrdenDeCarga> getOrdenpendientes() {
        
        return ordenpendientes;
    }

    
    public List<OrdenDeCarga> getPendientes() {
        
       
        return pendientes;
    }

    public List<OrdenDeCarga> getAnulados() {
        
        
        return anulados;
    }

    public List<OrdenDeCarga> getAceptados() {
        
        
        return aceptados;
    }

    public List<Producto> getProductos() {
        ProductoDao produ= new ProductoDaoImpl();
        this.productos=produ.SelectItemsCli(this.selectedCliente.getIdCliente());
        return productos;
    }

    public OrdenDeCarga getSelectedOrdenDeCarga() {
        return selectedOrdenDeCarga;
    }

    public void setSelectedOrdenDeCarga(OrdenDeCarga selectedOrdenDeCarga) {
        this.selectedOrdenDeCarga = selectedOrdenDeCarga;
    }

    public Camion getSelectedCamion() {
        return selectedCamion;
    }

    public void setSelectedCamion(Camion selectedCamion) {
        this.selectedCamion = selectedCamion;        
    }
    public void cargarcamion(ActionEvent actionEvent){
        CamionDao camionDao = new CamionDaoImpl();
        this.camiones = camionDao.findAll();
    }
    public void onRowSelectCamion(SelectEvent event) {
        this.selectedOrdenDeCarga.setCamion(this.selectedCamion);
        Date fecha1;
        Date fecha3;
        Date fecha2= new Date();   
        Integer band =0;
        Integer bandc =0;
        String msg;
        FacesMessage message;
        fecha1 = this.selectedCamion.getFechaVencimiento();
        fecha3 = this.selectedCamion.getCarreta().getFechaDinatran();
        System.out.println(fecha1);
        System.out.println(fecha2);
        switch(fecha2.compareTo(fecha1)){
            case 1: 
            System.out.println("Fecha es mayor");
            band=1;
            break;
            case 0:
            System.out.println("Las fechas son iguales");
            break;
            case -1:
            System.out.println("La fecha2 es menor");
            break; 
        }
        switch(fecha2.compareTo(fecha3)){
            case 1: 
            System.out.println("Fecha es mayor");
            bandc=1;
            break;
            case 0:
            System.out.println("Las fechas son iguales");
            break;
            case -1:
            System.out.println("La fecha2 es menor");
            break; 
        }
        if(band==1 || bandc==1){
            msg = "El Camion Seleccionado se encuentra con Dinatran Vencido.";
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public void onRowSelectCliente(SelectEvent event) {
        this.selectedOrdenDeCarga.setCliente(this.selectedCliente);
    }
    public void onRowSelectOrigen(SelectEvent event) {
        this.selectedOrdenDeCarga.setUnidadOrigen(this.selectedOrigen);
    }
    public void onRowSelectProducto(SelectEvent event) {
        this.selectedOrdenDeCarga.setProducto(this.selectedProducto);
    }
    public void onRowSelectDestino(SelectEvent event) {
        this.selectedOrdenDeCarga.setUnidadDestino(this.SelectedDestino);
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }
//    public void comparar(ActionEvent actionEvent) {
//        for(int i=0 ;i<this.camiones.size(); i++){
//            if(this.camiones.get(i).getChapaCamion().equals(this.camion.getChapaCamion())){
//                this.selectedOrdenDeCarga.setCamion(this.camiones.get(i));
//            }
//        }
//    }

    public void btnCreateOrdenDeCarga(ActionEvent actionEvent) {
        System.out.println(this.selectedOrdenDeCarga.getFechaEmision().toString());
        OrdenDeCargaDao ordenDao = new OrdenDeCargaDaoImpl();
        String msg;
        this.selectedOrdenDeCarga.setEstadoOrden("Pendiente");
        this.selectedOrdenDeCarga.setEstadoliq("Pendiente");
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
        this.selectedOrdenDeCarga.setUsuario(usuario);
        FacesMessage message;
        if(this.selectedOrdenDeCarga!=null){        
                    if (ordenDao.create(this.selectedOrdenDeCarga)) {
                        System.out.print("ESTE ES EL ESTADO QUE INTENTA CARGAR");
                        System.out.print(this.selectedOrdenDeCarga.getEstadoliq());
                        msg = "Se creo correctamente el registro, Para el reporte dar click en PDF";
                        message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
                        this.selectedOrdenDeCarga2 = this.selectedOrdenDeCarga; 
                        this.selectedCliente = this.selectedOrdenDeCarga.getCliente();
                        this.selectedOrigen = this.selectedOrdenDeCarga.getUnidadOrigen();
                        this.SelectedDestino = this.selectedOrdenDeCarga.getUnidadDestino();
                        //SelectItem selecItem = new SelectItem(this.selectedOrdenDeCarga.getProducto().getIdProducto(), this.selectedOrdenDeCarga.getProducto().getNombre());                        
            //            this.selectOneItemsProducto.set(1, this.selectedOrdenDeCarga.getProducto());
                        this.selectedOrdenDeCarga = new OrdenDeCarga();
                        this.selectedOrdenDeCarga.setFechaEmision(this.selectedOrdenDeCarga2.getFechaEmision());
                        this.selectedOrdenDeCarga.setProducto(this.selectedOrdenDeCarga2.getProducto());
                        this.selectedOrdenDeCarga.setCliente(this.selectedOrdenDeCarga2.getCliente());
                        this.selectedOrdenDeCarga.setUnidadOrigen(this.selectedOrdenDeCarga2.getUnidadOrigen());
                        this.selectedOrdenDeCarga.setUnidadDestino(this.selectedOrdenDeCarga2.getUnidadDestino());
                        this.ordenes = new ArrayList<OrdenDeCarga>();
                        this.pendientes = new ArrayList<OrdenDeCarga>();
                        this.anulados = new ArrayList<OrdenDeCarga>();
                        this.aceptados = new ArrayList<OrdenDeCarga>();
                        this.ordenpendientes = new ArrayList<OrdenDeCarga>();            
                        this.selectedCamion = new Camion();                                    

                    } else {
                        msg = "Error al crear registro, cargar los datos requeridos";
                        message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");

                    }  
//        FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear registro, cargar los datos requeridos";
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            
        }  
        FacesContext.getCurrentInstance().addMessage(null, message);

    }
public void btnIniciar(ActionEvent actionEvent){

        this.selectedOrdenDeCarga = new OrdenDeCarga();
        Date fecha1;
        fecha1 = new Date();
        this.selectedOrdenDeCarga.setFechaEmision(fecha1);
        
        this.selectedCamion = new Camion();
        this.selectedCliente = new Cliente();
        this.selectedOrigen = new UnidadOrigen();
        this.SelectedDestino = new UnidadDestino(); 
    }
    public void btnUpdateOrden(ActionEvent actionEvent) {
        OrdenDeCargaDao ordenDao = new OrdenDeCargaDaoImpl();
        String msg;
        if (ordenDao.update(this.selectedOrdenDeCarga)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteOrden(ActionEvent actionEvent) {
        OrdenDeCargaDao ordenDao = new OrdenDeCargaDaoImpl();
        String msg;
        if (ordenDao.delete(this.selectedOrdenDeCarga.getIdOrdenDeCarga())) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public void cargarDescrip(AjaxBehaviorEvent event){        
        //this.camion=this.selectedOrdenDeCarga.getCamion().getChapaCamion();
        
        CamionDao camionDao = new CamionDaoImpl();
        this.camiones = camionDao.findAll();
        System.out.print(this.camion);
        System.out.print(this.camiones.size());
        Integer band=0;
        for(int i=0 ;i<this.camiones.size(); i++){
            if(this.camiones.get(i).getChapaCamion().equals(this.selectedCamion.getChapaCamion())){
                this.selectedCamion=this.camiones.get(i);
                System.out.print(this.camion);
                this.selectedOrdenDeCarga.setCamion(this.selectedCamion);
                band= band+1;
                this.update="formCreate";
            }
        }
        if(band==0){
            this.selectedCamion = new Camion();
            this.update="";
        }
    }
}
