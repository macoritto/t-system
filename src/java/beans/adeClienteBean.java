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
import dao.ProSumiDao;
import dao.ProSumiDaoImpl;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.AdeCliente;
import model.AdeSuministro;
import model.Cliente;
import model.Combustible;
import model.PagoCombustible;
import model.ProveeCliente;
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
public class adeClienteBean {

    private List<AdeCliente> adelantos;
    private List<SelectItem> selectedPro;
    private AdeCliente selectedAdelanto;
    private Cliente selectedCliente;
    private Object proCombDao;
    private Double Total;
    private Double Totalvia;
    private Double Totalcomb;
    private Double Totalpago;
    private Double Totalextras;
    private Usuario usuario;

    public adeClienteBean() {
        this.adelantos = new ArrayList<AdeCliente>();
        this.selectedAdelanto = new AdeCliente();
    }

    public List<AdeCliente> getAdelantos() {
        AdeClienteDao sumiDao = new AdeClienteDaoImpl();
        this.adelantos = sumiDao.findAll();
        return adelantos;
    }

    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/procomblist.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getAdelantos()));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=prov_combustible.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void btnIniciar(ActionEvent actionEvent) {
        this.selectedAdelanto = new AdeCliente();
        this.selectedCliente = new Cliente();
    }

    public AdeCliente getSelectedAdelanto() {
        return selectedAdelanto;
    }

    public Cliente getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    public void onRowSelectPro(SelectEvent event) {
        this.selectedAdelanto.setCliente(this.selectedCliente);
    }

    public void setSelectedAdelanto(AdeCliente selectedAdelanto) {
        this.selectedAdelanto = selectedAdelanto;
    }

    public void btnCreateProveedorCombustible(ActionEvent actionEvent) {
        AdeClienteDao sumiDao = new AdeClienteDaoImpl();
        String msg;
        this.selectedAdelanto.setEstadoCobro("Pendiente");
        this.selectedAdelanto.setEstadoPago("Pendiente");
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
        this.selectedAdelanto.setUsuario(usuario);
        if (this.selectedCliente.getNombre()==null) {
            msg = "Debe seleccionar el Cliente.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            if (sumiDao.create(this.selectedAdelanto)) {
                msg = "Se creo correctamente el registro";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
                FacesContext.getCurrentInstance().addMessage(null, message);
                this.selectedAdelanto = new AdeCliente();
                this.selectedCliente = new Cliente();
            } else {
                msg = "Error al crear registro.";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }

    }

    public void btnUpdateProveedorCombustible(ActionEvent actionEvent) {
        AdeClienteDao sumiDao = new AdeClienteDaoImpl();
        String msg;
        if (sumiDao.update(this.selectedAdelanto)) {
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
        AdeClienteDao sumiDao = new AdeClienteDaoImpl();
        String msg;
        if (sumiDao.delete(this.selectedAdelanto.getIdAdeCliente())) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.selectedAdelanto = new AdeCliente();
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
}
