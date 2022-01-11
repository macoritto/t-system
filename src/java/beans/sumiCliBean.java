/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.AdeClienteDao;
import dao.AdeClienteDaoImpl;
import dao.AdeSumiDao;
import dao.AdeSumiDaoImpl;
import dao.OrdenDeCargaDao;
import dao.OrdenDeCargaDaoImpl;
import dao.ProSumiDao;
import dao.ProSumiDaoImpl;
import dao.SumiCliDao;
import dao.SumiCliDaoImpl;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.AdeCliente;
import model.AdeSuministro;
import model.Camion;
import model.Cliente;
import model.Combustible;
import model.OrdenDeCarga;
import model.PagoCombustible;
import model.ProveeCliente;
import model.SumiCliente;
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
@SessionScoped
public class sumiCliBean {

    private List<SumiCliente> suministros;
    private List<SelectItem> selectedPro;
    private SumiCliente selectedSuministro;
    private Cliente selectedCliente;
    private Object proCombDao;
    private Double Total;
    private Double Totalvia;
    private Double Totalcomb;
    private Double Totalpago;
    private Double Totalextras;
    private Usuario usuario;
    private OrdenDeCarga selectedOrdenDeCarga;
    private List<OrdenDeCarga> orden;
    private Camion selectedCamion;

    public sumiCliBean() {
        this.suministros = new ArrayList<SumiCliente>();
        this.selectedSuministro = new SumiCliente();
        this.selectedOrdenDeCarga = new OrdenDeCarga();
        this.selectedCamion = new Camion();
        this.orden = new ArrayList<OrdenDeCarga>();
    }

    public List<SumiCliente> getSuministros() {
        SumiCliDao sumiDao = new SumiCliDaoImpl();
        this.suministros = sumiDao.findAll();
        return suministros;
    }

    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/procomblist.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getSuministros()));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=prov_combustible.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void btnIniciar(ActionEvent actionEvent) {
        this.selectedSuministro = new SumiCliente();
        this.selectedCliente = new Cliente();
    }

    public SumiCliente getSelectedSuministro() {
        return selectedSuministro;
    }

    public Cliente getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    public void onRowSelectPro(SelectEvent event) {
        this.selectedSuministro.setCliente(this.selectedCliente);
    }

    public void setSelectedSuministro(SumiCliente selectedSuministro) {
        this.selectedSuministro = selectedSuministro;
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

    public void btnCreateProveedorCombustible(ActionEvent actionEvent) {
        SumiCliDao sumiDao = new SumiCliDaoImpl();
        String msg;
        this.selectedSuministro.setEstadoCobro("Pendiente");
        this.selectedSuministro.setEstadoPago("Pendiente");
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
        this.selectedSuministro.setUsuario(usuario);
        if (this.selectedCliente.getNombre() == null) {
            msg = "Debe seleccionar el Cliente.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            if (sumiDao.create(this.selectedSuministro)) {
                msg = "Se creo correctamente el registro";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
                FacesContext.getCurrentInstance().addMessage(null, message);
                this.selectedSuministro = new SumiCliente();
                this.selectedCliente = new Cliente();
            } else {
                msg = "Error al crear registro.";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }

    }

    public void btnUpdateProveedorCombustible(ActionEvent actionEvent) {
        SumiCliDao sumiDao = new SumiCliDaoImpl();
        String msg;
        if (sumiDao.update(this.selectedSuministro)) {
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
        SumiCliDao sumiDao = new SumiCliDaoImpl();
        String msg;
        if (sumiDao.delete(this.selectedSuministro.getIdSumiCli())) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedSuministro = new SumiCliente();
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    private Double sumarTotalPago(List<ValeCombustible> vales) {
        Double suma = 0.0;
        for (int i = 0; i < vales.size(); i++) {
            suma = suma + vales.get(i).getMontoTotal();
        }
        return suma;
    }

    private Double sumarTotalComb(List<ValeCombustible> vales) {
        Double suma = 0.0;
        for (int i = 0; i < vales.size(); i++) {
            suma = suma + vales.get(i).getMontoComb();
        }
        return suma;
    }

    private Double sumarTotalVia(List<ValeCombustible> vales) {
        Double suma = 0.0;
        for (int i = 0; i < vales.size(); i++) {
            suma = suma + vales.get(i).getViatico();
        }
        return suma;
    }

    private Double sumarTotalExtras(List<ValeCombustible> vales) {
        Double suma = 0.0;
        for (int i = 0; i < vales.size(); i++) {
            suma = suma + vales.get(i).getExtras();
        }
        return suma;
    }

    private Double sumarTotalPagoComb(List<Combustible> combus) {
        Double suma = 0.0;
        for (int i = 0; i < combus.size(); i++) {
            suma = suma + combus.get(i).getMontoTotal();
        }
        return suma;
    }

    private Double sumarTotalPago1(List<PagoCombustible> pagoscomb) {
        Double suma = 0.0;
        for (int i = 0; i < pagoscomb.size(); i++) {
            suma = suma + pagoscomb.get(i).getTotal();
        }
        return suma;
    }

    public void onRowSelectOrden(AjaxBehaviorEvent event) {
        String msg;
        if(this.selectedOrdenDeCarga.getIdOrdenDeCarga()!=1){

        OrdenDeCargaDao ordenDao = new OrdenDeCargaDaoImpl();
        this.orden = ordenDao.findUno(this.selectedOrdenDeCarga.getIdOrdenDeCarga());
        if (this.orden.size() > 0) {
            for (int i = 0; i < orden.size(); i++) {
                this.selectedOrdenDeCarga = orden.get(i);
            }
        } else {
            this.selectedOrdenDeCarga = new OrdenDeCarga();
        }
        this.selectedCamion = this.selectedOrdenDeCarga.getCamion();
        this.selectedCliente = this.selectedOrdenDeCarga.getCliente();
        if (this.selectedOrdenDeCarga.getCamion() == null) {
            msg = "No Existe la Orden De Carga Especificada.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            if (this.selectedOrdenDeCarga.getEstadoOrden().equals("Aceptado")) {
                msg = "El numero de Orden Especificado ya fue liquidado.";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                this.selectedSuministro.setCamion(this.selectedCamion);
                this.selectedSuministro.setChofer(this.selectedOrdenDeCarga.getCamion().getChofer());
                this.selectedSuministro.setOrdenDeCarga(this.selectedOrdenDeCarga);
                this.selectedSuministro.setCliente(this.selectedOrdenDeCarga.getCliente());
                msg = "Se selecciono la Orden de Carga.";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
        }else{
            msg = "La orden para suministros sin flete no se encuentra habilitado para suministros dados por el cliente.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnCreateComb(ActionEvent actionEvent) {
        SumiCliDao sumiDao = new SumiCliDaoImpl();
        String msg;
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
        this.selectedSuministro.setEstadoCobro("Pendiente");
        this.selectedSuministro.setEstadoPago("Pendiente");
        this.selectedSuministro.setUsuario(usuario);
        System.out.print(this.selectedOrdenDeCarga.getIdOrdenDeCarga());
        if (sumiDao.create(this.selectedSuministro)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedSuministro = new SumiCliente();
            this.selectedOrdenDeCarga = new OrdenDeCarga();
            this.selectedCamion = new Camion();
            this.selectedCliente = new Cliente();
            this.orden = new ArrayList<OrdenDeCarga>();
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnUpdateComb(ActionEvent actionEvent) {
        SumiCliDao sumiDao = new SumiCliDaoImpl();
        String msg;
        if (sumiDao.update(this.selectedSuministro)) {
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
        SumiCliDao sumiDao = new SumiCliDaoImpl();
        String msg;
        if (sumiDao.delete(this.selectedSuministro.getIdSumiCli())) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
