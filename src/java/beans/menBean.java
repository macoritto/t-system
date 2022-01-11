/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.Usuario;
import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import util.MyUtil;

/**
 *
 * @author Usuario
 */
@ManagedBean
@RequestScoped
public class menBean{

    private MenuModel modelShow = new DefaultMenuModel();    
    private Usuario usuario;
    private Integer codrol;
    public menBean() {        
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
        codrol=this.usuario.getRol().getId();
        //SubMenu de archivo
        DefaultMenuItem Inicio = new DefaultMenuItem("Inicio");
        DefaultMenuItem Salir = new DefaultMenuItem("Salir");
        DefaultSubMenu archivo = new DefaultSubMenu("Archivo");
        DefaultSubMenu agregar = new DefaultSubMenu("Agregar");
        DefaultSubMenu flota = new DefaultSubMenu("Flota");
        DefaultSubMenu vales = new DefaultSubMenu("Vales");
        DefaultSubMenu clientes = new DefaultSubMenu("Clientes");
        DefaultSubMenu prov = new DefaultSubMenu("Proveedores");
        DefaultSubMenu adelantos = new DefaultSubMenu("Adelantos");
        //creacion de los items de flota
        DefaultMenuItem flotai = new DefaultMenuItem("Flota");
        DefaultMenuItem aorden = new DefaultMenuItem("Orden de Carga");
        aorden.setOutcome("/views/agregar/aorden");
        DefaultMenuItem acomb = new DefaultMenuItem("Combustible");
        acomb.setOutcome("/views/agregar/acomb");
        DefaultMenuItem aflete = new DefaultMenuItem("Flete");
        aflete.setOutcome("/views/agregar/aflete");
        DefaultMenuItem aviatico = new DefaultMenuItem("Viatico");
        aviatico.setOutcome("/views/agregar/aviatico");
        DefaultMenuItem apropietario = new DefaultMenuItem("A. Propietario");
        apropietario.setOutcome("/views/agregar/adepro");
//        aorden.setIcon("icon-continuo");
        DefaultMenuItem usuario = new DefaultMenuItem("Usuarios");
        DefaultMenuItem clientesi = new DefaultMenuItem("Clientes");
        DefaultMenuItem productos = new DefaultMenuItem("Productos");
        DefaultMenuItem provarios = new DefaultMenuItem("Pro. Varios");
        provarios.setOutcome("/views/provarios/provarios");
        DefaultMenuItem procomb = new DefaultMenuItem("Pro. Combustible");
        procomb.setOutcome("/views/procomb/procomb");
        clientesi.setOutcome("/views/clientes/clientes");
        DefaultMenuItem origen = new DefaultMenuItem("U. Origen");
        origen.setOutcome("/views/unidadOrigen/unidadOrigen");
        DefaultMenuItem destino = new DefaultMenuItem("U. Destino");
        destino.setOutcome("/views/unidadDestino/unidadDestino");
        DefaultMenuItem preflete = new DefaultMenuItem("P. de Flete");
        preflete.setOutcome("/views/preflete/preflete");
        usuario.setOutcome("/views/usuario/index");
        productos.setOutcome("/views/producto/producto");
        flotai.setOutcome("/views/flota/flota");
        DefaultMenuItem carretas = new DefaultMenuItem("Carretas");
        carretas.setOutcome("/views/carreta/carreta");
        DefaultMenuItem choferes = new DefaultMenuItem("Choferes");
        choferes.setOutcome("/views/choferes/choferes");
        DefaultMenuItem propietarios = new DefaultMenuItem("Propietarios");
        propietarios.setOutcome("/views/propietario/propietario");
        Inicio.setIcon("ui-icon-home");
        Inicio.setOutcome("/views/inicio");
        Salir.setCommand("#{loginBean.logout}");
        Salir.setOncomplete("handleLoginRequest(xhr, status, args)");
        Salir.setIcon("ui-icon-power");
        //Creacion del Segundo Submenu Procesos
        DefaultSubMenu procesos = new DefaultSubMenu("Proceso");
        procesos.setIcon("ui-icon-gear");
        DefaultMenuItem orden = new DefaultMenuItem("Orden de Carga");
        DefaultMenuItem comb = new DefaultMenuItem("Combustibles");
        comb.setOutcome("/views/comb/comb");
        DefaultMenuItem varios = new DefaultMenuItem("Varios");
        varios.setOutcome("/views/varios/varios");
        DefaultMenuItem contra = new DefaultMenuItem("Contraseñas");
        contra.setOutcome("/views/contra/contra");
        DefaultMenuItem viaticos = new DefaultMenuItem("Viaticos");
        viaticos.setOutcome("/views/viaticos/viaticos");
        orden.setOutcome("/views/OrdenDeCarga/ordenDeCarga");
        DefaultMenuItem valev = new DefaultMenuItem("Vale Vario");
        valev.setOutcome("/views/valevarios/valevarios");        
        DefaultMenuItem valec = new DefaultMenuItem("Vale Combustible");
        valec.setOutcome("/views/valecomb/valecomb");
        DefaultMenuItem adepro = new DefaultMenuItem("A. Propietario");
        adepro.setOutcome("/views/adepro/adepro");
        DefaultMenuItem adecho = new DefaultMenuItem("A. Chofer");
        adecho.setOutcome("/views/adechofer/adechofer");
        DefaultMenuItem recibo = new DefaultMenuItem("Recibos");
        recibo.setOutcome("/views/recibo/recibo");
        DefaultMenuItem flete = new DefaultMenuItem("Fletes");
        flete.setOutcome("/views/fletes/fletes");
        //Creacion del tercer submenu pagos
        DefaultSubMenu pagos = new DefaultSubMenu("Pagos");
        DefaultMenuItem pagov = new DefaultMenuItem("Pago Varios");
        pagov.setOutcome("/views/pagov/pagov");
        DefaultMenuItem pagoc = new DefaultMenuItem("Pago Combustibles");
        pagoc.setOutcome("/views/pagoc/pagoc");
        DefaultMenuItem pagovi = new DefaultMenuItem("Cobro Flete");
        pagovi.setOutcome("/views/pagovi/pagovi");
        //Creacion del cuarto submenu extractos
        DefaultSubMenu extractos = new DefaultSubMenu("Extractos");
        DefaultMenuItem ex = new DefaultMenuItem("Extractos");
        ex.setOutcome("/views/extracto/extracto");
        DefaultMenuItem estados = new DefaultMenuItem("Estados");
        estados.setOutcome("/views/estados/estados");
        clientes.addElement(clientesi);
        clientes.addElement(origen);
        clientes.addElement(destino);
        clientes.addElement(preflete);
        flota.addElement(flotai);
        flota.addElement(carretas);
        flota.addElement(choferes);
        flota.addElement(propietarios);        
        prov.addElement(provarios);
        prov.addElement(procomb);        
        this.modelShow.addElement(Inicio);
        this.modelShow.addElement(agregar);
        if(codrol==0){
            archivo.addElement(usuario);
            archivo.addElement(flota);
            archivo.addElement(clientes);
            archivo.addElement(productos);
            archivo.addElement(prov);
            agregar.addElement(acomb);
            agregar.addElement(aflete);
            archivo.setIcon("ui-icon-circle-minus");
            vales.addElement(valev);
            vales.addElement(valec);
            adelantos.addElement(adepro);
            adelantos.addElement(adecho);
            procesos.setIcon("ui-icon-gear");
            procesos.addElement(orden);
            procesos.addElement(vales);
            procesos.addElement(comb);
            procesos.addElement(varios);
            procesos.addElement(flete);
            procesos.addElement(contra);
            procesos.addElement(viaticos);
            procesos.addElement(adelantos);
            procesos.addElement(recibo);
            pagos.setIcon("ui-icon-gear");
            pagos.addElement(pagov);
            pagos.addElement(pagoc);
            pagos.addElement(pagovi);
            extractos.setIcon("ui-icon-gear");
            extractos.addElement(ex);
            extractos.addElement(estados);
            this.modelShow.addElement(archivo);     
            this.modelShow.addElement(procesos);
            this.modelShow.addElement(pagos);
            this.modelShow.addElement(extractos);
            
        }     
        agregar.addElement(aorden);
        agregar.addElement(aviatico);
        agregar.addElement(apropietario);
        agregar.setIcon("ui-icon-circle-minus");
        this.modelShow.addElement(Salir);
    }

    public MenuModel getModelShow() {
        return modelShow;
    }

    public void setModelShow(MenuModel modelShow) {
        this.modelShow = modelShow;
    }
    
}
