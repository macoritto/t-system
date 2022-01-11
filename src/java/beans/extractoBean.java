/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.AdelantoChoferDao;
import dao.AdelantoChoferDaoImpl;
import dao.CamionDao;
import dao.CamionDaoImpl;
import dao.CombDao;
import dao.CombDaoImpl;
import dao.CreditoDao;
import dao.CreditoDaoImpl;
import dao.DetExDao;
import dao.DetExDaoImpl;
import dao.DetOrdenDao;
import dao.DetOrdenDaoImpl;
import dao.ExCamionDao;
import dao.ExCamionDaoImpl;
import dao.ExOrdenDao;
import dao.ExOrdenDaoImpl;
import dao.ExSumiDao;
import dao.ExSumiDaoImpl;
import dao.ExSumicliDao;
import dao.ExSumicliDaoImpl;
import dao.ExtractoDao;
import dao.ExtractoDaoImpl;
import dao.ItemDao;
import dao.ItemDaoImpl;
import dao.OrdenDeCargaDao;
import dao.OrdenDeCargaDaoImpl;
import dao.PropietarioDao;
import dao.PropietarioDaoImpl;
import dao.ReciboDao;
import dao.ReciboDaoImpl;
import dao.SumiCliDao;
import dao.SumiCliDaoImpl;
import dao.SumiDao;
import dao.SumiDaoImpl;
import dao.TipoItemDao;
import dao.TipoItemDaoImpl;
import dao.ValeCombDao;
import dao.ValeCombDaoImpl;
import dao.ValeVariosDao;
import dao.ValeVariosDaoImpl;
import dao.VariosDao;
import dao.VariosDaoImpl;
import dao.ViajeDao;
import dao.ViajeDaoImpl;
import dao.ViaticoDao;
import dao.ViaticoDaoImpl;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.AdelantoChofer;
import model.Camion;
import model.Combustible;
import model.Credito;
import model.DetExtracto;
import model.DetOrden;
import model.ExCamion;
import model.ExOrden;
import model.Extracto;
import model.ExtractoSumi;
import model.ExtractoSumicli;
import model.Item;
import model.OrdenDeCarga;
import model.Propietario;
import model.Recibo;
import model.SumiCliente;
import model.Suministro;
import model.TipoItem;
import model.UnidadOrigen;
import model.Usuario;
import model.ValeCombustible;
import model.ValeVarios;
import model.Varios;
import model.Viaje;
import model.Viatico;
import model.sumitem;
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
//@ViewScoped
public class extractoBean {

    private List<Extracto> extractos;
    private List<Suministro> suministros;
    private List<Extracto> exanulados;
    private List<TipoItem> tipoviatico;
    private List<TipoItem> tipoviaje;
    private List<TipoItem> tipocomb;
    private List<TipoItem> tipovario;
    private List<TipoItem> tipocredito;
    private List<TipoItem> tipoadelanto;
    private List<TipoItem> tiposeguro;
    private List<TipoItem> tipofaltante;
    private List<TipoItem> tipoRecibo;
    private List<Credito> creditos;
    private List<Varios> varioOne;
    private List<Credito> creditoOne;
    private List<AdelantoChofer> adechoOne;
    private List<Recibo> reciboOne;
    private AdelantoChofer selectedAdelanto;
    private Credito selectedCredito;
    private Varios selectedVario;
    private List<Viaje> viajeOne;
    private List<Viatico> viaticoOne;
    private Viatico selectedViatico;
    private Integer idpdf;
    private List<Combustible> combOne;
    private Combustible selectedComb;
    private Viaje selectedViaje;
    private List<AdelantoChofer> adelantos;
    private List<ExCamion> excamion;
    private List<ExCamion> camionvista;
    private Integer contafle;
    private Double acufle;
    private Double acucomb;
    private Double acuvia;
    private Double acuvarios;
    private Double acucre;
    private Double acuade;
    private Double acureci;
    private Double acufal;
    private Double acuse;
    private Double asumi;
    private ExCamion selectedEx;
    private List<Varios> varios;
    private List<Viaje> viajes;
    private List<Viatico> viaticos;
    private List<Combustible> combs;
    private List<Combustible> combflete;
    private Extracto selectedExtracto;
    private List<Camion> camiones;
    private List<Item> items;
    private Item selectedItem;
    private Item selectedItemView;
    private Item selectedItemcompa;
    private Propietario selectedPropietario;
    private Camion selectedCamion;
    private List<Camion> selectedCamiones;
    private List<DetExtracto> detextracto;
    private List<DetExtracto> detextractoaux;
    private DetExtracto selectedDetextracto;
    private DetExtracto selectedDetExtractoAux;
    private Double total;
    private List<OrdenDeCarga> ordenes;
    private Usuario usuario;
    private Double totalActivo;
    private Double totalPasivo;
    private Double totalcobrar;
    private Integer codextractov;
    private Connection conexion;
    private List<Item> selectedItems;
    private List<Item> selectedItems1;
    private List<Item> itempen;
    private List<Item> selectedItemsAux;
    private Integer codextracto;
    private List<Extracto> estados;
    private Extracto selectedEstado;
    private List<Propietario> propietarios;
    private Item selectedItemp;
    private List<ValeCombustible> valescomb;
    private List<ValeVarios> variosp;
    private List<OrdenDeCarga> ordenesSumi;
    private List<OrdenDeCarga> ordenesSumi2;
    private ValeVarios selectedVarios;
    private List<Combustible> combex;
    private List<Viatico> viaflete;
    private List<Recibo> recibos;
    private Recibo selectedRecibo;
    private Credito selectedCredito2;
    private List<Viatico> viaex;
    private List<DetExtracto> detextractoup;
    private String Descrip;
    private String nomusu;
    private String colores = "red";
    private OrdenDeCarga selectedOrden = new OrdenDeCarga();
    private List<sumitem> sumitemsex = new ArrayList<sumitem>();
    private String estadonativo;

    public extractoBean() {
        this.extractos = new ArrayList<Extracto>();
        this.selectedExtracto = new Extracto();
        this.items = new ArrayList<Item>();
        this.selectedItem = new Item();
        this.selectedItemView = new Item();
        this.selectedCamion = new Camion();
        this.detextracto = new ArrayList<DetExtracto>();
        this.detextractoaux = new ArrayList<DetExtracto>();
        this.selectedDetextracto = new DetExtracto();
        this.selectedDetExtractoAux = new DetExtracto();
        this.selectedItems = new ArrayList<Item>();
        this.selectedItemsAux = new ArrayList<Item>();
        this.selectedExtracto.setFecha(new Date());
        this.selectedPropietario = new Propietario();
        this.combs = new ArrayList<Combustible>();
        this.viaticos = new ArrayList<Viatico>();
        this.selectedCamiones = new ArrayList<Camion>();
        this.camiones = new ArrayList<Camion>();
        this.varios = new ArrayList<Varios>();
        this.creditos = new ArrayList<Credito>();
        this.adelantos = new ArrayList<AdelantoChofer>();
        this.excamion = new ArrayList<ExCamion>();
        this.camionvista = new ArrayList<ExCamion>();
        this.selectedEx = new ExCamion();
        this.selectedViaje = new Viaje();
        this.selectedComb = new Combustible();
        this.itempen = new ArrayList<Item>();
        this.selectedViatico = new Viatico();
        this.estados = new ArrayList<Extracto>();
        this.selectedEstado = new Extracto();
        this.propietarios = new ArrayList<Propietario>();
        this.ordenes = new ArrayList<OrdenDeCarga>();
        this.selectedItemp = new Item();
        this.valescomb = new ArrayList<ValeCombustible>();
        this.combflete = new ArrayList<Combustible>();
        this.viaex = new ArrayList<Viatico>();
        this.viaflete = new ArrayList<Viatico>();
        this.selectedCredito = new Credito();
        this.selectedCredito2 = new Credito();
        this.combex = new ArrayList<Combustible>();
        this.recibos = new ArrayList<Recibo>();
        this.selectedRecibo = new Recibo();
        this.detextractoup = new ArrayList<DetExtracto>();
        this.exanulados = new ArrayList<Extracto>();
        this.ordenesSumi = new ArrayList<OrdenDeCarga>();
        this.ordenesSumi2 = new ArrayList<OrdenDeCarga>();
        //this.chkBoxChecked = false;
    }

    public List<Extracto> getExtractos() {
        ExtractoDao exDao = new ExtractoDaoImpl();
        this.extractos = exDao.findAll();
        this.total = this.sumarTotalPago(extractos);
        return extractos;
    }

    public List<Extracto> getExanulados() {
        ExtractoDao exDao = new ExtractoDaoImpl();
        this.exanulados = exDao.findAnulado();
        this.total = this.sumarTotalPago(exanulados);
        return exanulados;
    }

    public Double getAsumi() {
        return asumi;
    }

    private boolean chkBoxChecked;

    public boolean isChkBoxChecked() {
        return !chkBoxChecked;
    }

    public boolean isBtnDisabled() {

        return this.chkBoxChecked;
    }

    public List<Item> getItempen() {
        return itempen;
    }

    public Integer getContafle() {
        return contafle;
    }

    public Double getAcufle() {
        return acufle;
    }

    public Double getAcucomb() {
        return acucomb;
    }

    public Double getAcuvia() {
        return acuvia;
    }

    public Double getAcuvarios() {
        return acuvarios;
    }

    public Double getAcucre() {
        return acucre;
    }

    public Double getAcuade() {
        return acuade;
    }

    public Double getAcureci() {
        return acureci;
    }

    public Double getAcufal() {
        return acufal;
    }

    public Double getAcuse() {
        return acuse;
    }

    public String getColores() {
        return colores;
    }

    public void setColores(String colores) {
        this.colores = colores;
    }

    public List<OrdenDeCarga> getOrdenesSumi2() {
        return ordenesSumi2;
    }

    public void setOrdenesSumi2(List<OrdenDeCarga> ordenesSumi2) {
        this.ordenesSumi2 = ordenesSumi2;
    }

    public OrdenDeCarga getSelectedOrden() {
        return selectedOrden;
    }

    public void setSelectedOrden(OrdenDeCarga selectedOrden) {
        this.selectedOrden = selectedOrden;
    }

    public List<ExCamion> getCamionVista() {
        ExCamionDao excamionDao = new ExCamionDaoImpl();
        this.camionvista = excamionDao.findByEx(this.selectedExtracto);
        return camionvista;
    }

    public List<Camion> getSelectedCamiones() {
        return selectedCamiones;
    }

    public void setSelectedCamiones(List<Camion> selectedCamiones) {
        this.selectedCamiones = selectedCamiones;
    }

    public Double getTotalcobrar() {
        return totalcobrar;
    }

    public void setTotalcobrar(Double totalcobrar) {
        this.totalcobrar = totalcobrar;
    }

    public Double getTotalActivo() {
        return totalActivo;
    }

    public Double getTotalPasivo() {
        return totalPasivo;
    }

    public void setTotalPasivo(Double totalPasivo) {
        this.totalPasivo = totalPasivo;
    }

    public Item getSelectedItemView() {
        return selectedItemView;
    }

    public void setSelectedItemView(Item selectedItemView) {
        this.selectedItemView = selectedItemView;
    }

    public void setTotalActivo(Double totalActivo) {
        this.totalActivo = totalActivo;
    }

    public List<Camion> getCamiones() {
        CamionDao camionDao = new CamionDaoImpl();
        this.camiones = camionDao.findOne(this.selectedPropietario);
        return camiones;
    }

    public Propietario getSelectedPropietario() {
        return selectedPropietario;
    }

    public void setSelectedPropietario(Propietario selectedPropietario) {
        this.selectedPropietario = selectedPropietario;
    }

    public List<Item> getItems() {
        ItemDao itemDao = new ItemDaoImpl();
        this.items = itemDao.findCamion(this.selectedCamion);
        //this.total=this.sumarTotalPago(pagos);
        return items;
    }

    public Extracto getSelectedExtracto() {
        return selectedExtracto;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public Camion getSelectedCamion() {
        return selectedCamion;
    }

    public List<DetExtracto> getDetextracto() {
        return detextracto;
    }

    public List<DetExtracto> getDetextractoAux() {
        ExtractoDao exDao = new ExtractoDaoImpl();
        this.detextractoaux = exDao.findByDetextracto(this.selectedExtracto.getIdLiquidacion());
        this.estadonativo = this.selectedExtracto.getEstado();
        //this.totalActivo = this.sumarActivo(detextractoaux);
        this.totalPasivo = this.sumarPasivo(detextractoaux);
        this.acufle = this.sumarFletevista(detextractoaux);
//        this.acucomb = this.sumarComb(detextractoaux);
//        this.acuade = this.sumarAdelanto(detextractoaux);
//        this.acucre = this.sumarCredito(detextractoaux);
//        this.acufal = this.sumarFaltante(detextractoaux);
//        this.acureci = this.sumarRecibo(detextractoaux);
//        this.acuse = this.sumarSeguro(detextractoaux);
//        this.acuvarios = this.sumarVarios(detextractoaux);
//        this.acuvia = this.sumarVia(detextractoaux);
        totalcobrar = (this.totalActivo + this.totalPasivo);
        return detextractoaux;
    }

    public DetExtracto getSelectedDetextracto() {
        return selectedDetextracto;
    }

    public DetExtracto getSelectedDetExtractoAux() {
        return selectedDetExtractoAux;
    }

    public Double getTotal() {
        return total;
    }

    public List<Item> getSelectedItems() {
        return selectedItems;
    }

    public List<Item> getSelectedItemsAux() {
        return selectedItemsAux;
    }

    public Integer getCodextracto() {
        return codextracto;
    }

    public Item getSelectedItemp() {
        return selectedItemp;
    }

    public List<OrdenDeCarga> getOrdenesSumi() {
        return ordenesSumi;
    }

    public void setSelectedExtracto(Extracto selectedExtracto) {
        this.selectedExtracto = selectedExtracto;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void setSelectedCamion(Camion selectedCamion) {
        this.selectedCamion = selectedCamion;
    }

    public void setSelectedDetextracto(DetExtracto selectedDetextracto) {
        this.selectedDetextracto = selectedDetextracto;
    }

    public void setSelectedDetExtractoAux(DetExtracto selectedDetExtractoAux) {
        this.selectedDetExtractoAux = selectedDetExtractoAux;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setSelectedItems(List<Item> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public void setSelectedItemsAux(List<Item> selectedItemsAux) {
        this.selectedItemsAux = selectedItemsAux;
    }

    public void setCodextracto(Integer codextracto) {
        this.codextracto = codextracto;
    }

    public List<Suministro> getSuministros() {
        return suministros;
    }

    public void setSuministros(List<Suministro> suministros) {
        this.suministros = suministros;
    }

    public void cargarArray(ActionEvent event) {
        //CARGAR EL ARRAY PARA LA VISUALIZACION DE LOS ITEMS A CARGAR
        String msg;
        this.selectedItemsAux = new ArrayList<Item>();
        //for(int i = 0; i<this.items.size(); i++){           
        //selectedItemsAux.add(this.items.get(i));
        //}        
        //INICIALIZACION DE LOS TIPOS DE ITEMS
        TipoItemDao tipoDao = new TipoItemDaoImpl();
        this.tipoviaje = tipoDao.findOne();
        this.tipocomb = tipoDao.findComb();
        this.tipoviatico = tipoDao.findVia();
        this.tipovario = tipoDao.findVarios();
        this.tipocredito = tipoDao.findCredito();
        this.tipoadelanto = tipoDao.findAdelanto();
        this.tipofaltante = tipoDao.findFaltante();
        this.tiposeguro = tipoDao.findSeguro();
        this.tipoRecibo = tipoDao.findRecibo();
        String c;
        ItemDao itemDao = new ItemDaoImpl();
        Integer maxCoditem = itemDao.maxItem() + 1;
        //RECORRIDO DE LOS CAMIONES SELECCIONADOS Y CARGADOS EN EL ARRAY              
        for (int h = 0; h < this.selectedCamiones.size(); h++) {
            this.items = itemDao.findCamion(this.selectedCamiones.get(h));
            maxCoditem = maxCoditem + 1;
            ViajeDao viajesDao = new ViajeDaoImpl();
            //BUSCAR LOS FLETES PENDIENTES DE PAGO Y SUS GASTOS    
            this.viajes = viajesDao.findEx(this.selectedCamiones.get(h));
            String Origen;
            String Destino;
            String dcomb;
            Double litros1;
            String litros2;
            String tipocomb;
            String pro;
            Integer band = 0;
            FacesMessage message;
            Integer ban = 0;
            for (int i = 0; i < this.viajes.size(); i++) {
                String nro;
                Double precio;
                Integer pesoo;
                Integer pesod;
                Double preciof;
                preciof = this.viajes.get(i).getMontofaltante() / this.viajes.get(i).getFalCobrar();
                if (preciof > 0) {
                    BigDecimal valor = new BigDecimal(preciof);
                    BigDecimal valorRedondeado = valor.setScale(0, BigDecimal.ROUND_HALF_UP);
                    preciof = valorRedondeado.doubleValue();
                } else {
                    preciof = 0.0;
                }
                pesoo = this.viajes.get(i).getPesoOrigen();
                pesod = this.viajes.get(i).getPesoDestino();
                precio = this.viajes.get(i).getPrecioPago();
                Integer dif;
                String producto;
                producto = this.viajes.get(i).getOrdenDeCarga().getProducto().getNombre();
                dif = pesoo - pesod;
                nro = this.viajes.get(i).getRemisionDestino();
                maxCoditem = maxCoditem + 1;
                this.selectedItem = new Item();
                Origen = this.viajes.get(i).getOrdenDeCarga().getUnidadOrigen().getNombre().toString();
                Destino = this.viajes.get(i).getOrdenDeCarga().getUnidadDestino().getNombre().toString();
                c = " ".concat(Origen).concat(" A ").concat(Destino).concat(" N° de Remision".concat(nro).concat(" -Precio :").concat(precio.toString().concat("-Peso O: ".concat(pesoo.toString()).concat(" -Peso D:").concat(pesod.toString()))));
                this.selectedItem.setIdItem(maxCoditem);
                this.selectedItem.setCamion(this.viajes.get(i).getOrdenDeCarga().getCamion());
                this.selectedItem.setFecha(this.viajes.get(i).getFechaDestino());
                this.selectedItem.setCod(this.viajes.get(i).getIdViaje());
                this.selectedItem.setDescrip(c);
                this.selectedItem.setActivo(this.viajes.get(i).getMontoPagar() + this.viajes.get(i).getMontofaltante() + this.viajes.get(i).getMontoseguro());
                this.selectedItem.setPasivo(0.0);
                for (int e = 0; e < this.tipoviaje.size(); e++) {
                    this.selectedItem.setTipoItem(this.tipoviaje.get(e));
                }
                //SE CARGA EL FLETE AL ITEM
                selectedItemsAux.add(this.selectedItem);
                this.selectedItem = new Item();
                if (this.viajes.get(i).getMontofaltante() > 0) {
                    band = 1;
                    maxCoditem = maxCoditem + 1;
                    this.selectedItem.setIdItem(maxCoditem);
                    this.selectedItem.setCamion(this.viajes.get(i).getOrdenDeCarga().getCamion());
                    this.selectedItem.setCod(this.viajes.get(i).getIdViaje());
                    this.selectedItem.setFecha(this.viajes.get(i).getFechaDestino());
                    this.selectedItem.setDescrip("Faltante del Flete N°".concat(nro).concat(" - P. O: ").concat(pesoo.toString().concat(" -P. D: ").concat(pesod.toString()).concat(" -Dif: ".concat(dif.toString()).concat(" -Producto: ").concat(producto).concat(" y con Precio: ".concat(preciof.toString())))));
                    this.selectedItem.setActivo(0.0);
                    this.selectedItem.setPasivo(this.viajes.get(i).getMontofaltante());
                    for (int e = 0; e < this.tipofaltante.size(); e++) {
                        this.selectedItem.setTipoItem(this.tipofaltante.get(e));
                    }
                    selectedItemsAux.add(this.selectedItem);
                    this.selectedItem = new Item();
                    //SE CARGA EL FALTANTE EN EL CASO DE QUE SEA MAYOR A CERO
                }
                if (this.viajes.get(i).getSeguro().getMonto() > 0) {
                    ban = 1;
                    maxCoditem = maxCoditem + 1;
                    this.selectedItem.setIdItem(maxCoditem);
                    this.selectedItem.setCamion(this.viajes.get(i).getOrdenDeCarga().getCamion());
                    this.selectedItem.setCod(this.viajes.get(i).getIdViaje());
                    this.selectedItem.setFecha(this.viajes.get(i).getFechaDestino());
                    this.selectedItem.setDescrip("Seguro del viaje N°".concat(nro));
                    this.selectedItem.setActivo(0.0);
                    this.selectedItem.setPasivo(this.viajes.get(i).getSeguro().getMonto());
                    for (int e = 0; e < this.tiposeguro.size(); e++) {
                        this.selectedItem.setTipoItem(this.tiposeguro.get(e));
                    }
                    selectedItemsAux.add(this.selectedItem);
                    this.selectedItem = new Item();
                    //SE CARGA EL MONTO DEL FALTANTE EL CASO DE QUE SEA MAYOR A CERO 
                }
                CombDao comDao = new CombDaoImpl();
                this.combflete = new ArrayList<Combustible>();
                this.combex = new ArrayList<Combustible>();
                this.combflete = comDao.findVale2(this.viajes.get(i).getOrdenDeCarga(), this.selectedCamiones.get(h));
                //SE BUSCAN LOS COMBUSTIBLES CARGADOS PARA EL FLETE
                for (int co = 0; co < this.combflete.size(); co++) {
                    maxCoditem = maxCoditem + 1;
                    this.selectedItem = new Item();
                    this.selectedItem.setIdItem(maxCoditem);
                    this.selectedItem.setFecha(this.combflete.get(co).getFecha());
                    this.selectedItem.setCamion(this.combflete.get(co).getValeCombustible().getCamion());
                    if (this.combflete.get(co).getExtras() > 0.0) {
                        this.selectedItem.setPasivo(this.combflete.get(co).getMontoComb() + this.combflete.get(co).getExtras());
                        pro = this.combflete.get(co).getValeCombustible().getProveedor().getNombre().toString();
                        litros1 = this.combflete.get(co).getLitros();
                        litros2 = litros1.toString();
                        String nroboleta;
                        nroboleta = this.combflete.get(co).getNBoleta();
                        Double montoex = this.combflete.get(co).getExtras();
                        String montoextras = montoex.toString();
                        tipocomb = this.combflete.get(co).getValeCombustible().getTipoCombustible().getNombre().toString();
                        dcomb = litros2.concat(" de ").concat(tipocomb).concat(" cargados en ").concat(pro).concat(" con Nro de Boleta: ".concat(nroboleta)).concat(" Extras Descrip: ".concat(this.combflete.get(co).getValeCombustible().getDescripcion().concat(" Monto: ".concat(montoextras))));
                    } else {
                        this.selectedItem.setPasivo(this.combflete.get(co).getMontoComb());
                        pro = this.combflete.get(co).getValeCombustible().getProveedor().getNombre().toString();
                        litros1 = this.combflete.get(co).getLitros();
                        litros2 = litros1.toString();
                        String nroboleta;
                        nroboleta = this.combflete.get(co).getNBoleta();
                        tipocomb = this.combflete.get(co).getValeCombustible().getTipoCombustible().getNombre().toString();
                        dcomb = litros2.concat(" de ").concat(tipocomb).concat(" cargados en ").concat(pro).concat(" con Nro de Boleta: ".concat(nroboleta));
                    }
                    this.selectedItem.setDescrip(dcomb);
                    this.selectedItem.setCod(this.combflete.get(co).getIdCombustible());
                    this.selectedItem.setActivo(0.0);
                    for (int e = 0; e < this.tipocomb.size(); e++) {
                        this.selectedItem.setTipoItem(this.tipocomb.get(e));
                    }
                    //SE CARGAN LOS DATOS DEL COMB EN UN ARRAY PARA LUEGO HACER LA COMPARACION
                    this.selectedComb = this.combflete.get(co);
                    this.combex.add(this.selectedComb);
                    this.selectedComb = new Combustible();
                    selectedItemsAux.add(this.selectedItem);
                    this.selectedItem = new Item();
                }
                ViaticoDao via = new ViaticoDaoImpl();
                this.viaflete = new ArrayList<Viatico>();
                this.viaflete = via.findVia(this.viajes.get(i).getOrdenDeCarga(), this.selectedCamiones.get(h));
                //SE CARGAN LOS VIATICOS RETIRADOS PARA DICHO FLETE
                for (int v = 0; v < this.viaflete.size(); v++) {
                    this.selectedItem = new Item();
                    maxCoditem = maxCoditem + 1;
                    String Des;
                    Des = this.viaflete.get(v).getDescripcion();
                    c = "Efectivo Retirado de la Empresa, Descrip: ".concat(Des);
                    this.selectedItem.setDescrip(c);
                    this.selectedItem.setIdItem(maxCoditem);
                    this.selectedItem.setCamion(this.viaflete.get(v).getCamion());
                    this.selectedItem.setFecha(this.viaflete.get(v).getFecha());
                    this.selectedItem.setPasivo(this.viaflete.get(v).getMonto());
                    this.selectedItem.setCod(this.viaflete.get(v).getIdViatico());
                    this.selectedItem.setActivo(0.0);
                    for (int e = 0; e < this.tipoviatico.size(); e++) {
                        this.selectedItem.setTipoItem(this.tipoviatico.get(e));
                    }
                    //SE CARGAN LOS DATOS DE LOS VIATICOS EN UN ARRAY PARA LUEGO HACER LA COMPARACION
                    this.selectedViatico = this.viaflete.get(v);
                    this.viaex.add(this.selectedViatico);
                    this.selectedViatico = new Viatico();
                    selectedItemsAux.add(this.selectedItem);
                    this.selectedItem = new Item();
                }
            }
            CombDao comDao = new CombDaoImpl();
            this.combs = new ArrayList<Combustible>();
            this.combs = comDao.findCamion(this.selectedCamiones.get(h));
            //SE CARGAN TODOS LOS COMB PARA COMPARAR SI YA SE ENCUENTRAN CARGADOS COMO ITEM PARA QUE NO HAYA FILTRACIONES
            for (int i = 0; i < this.combs.size(); i++) {
                Integer b = 0;
                if (this.combex != null) {
                    for (int co = 0; co < this.combex.size(); co++) {
                        if (this.combs.get(i).getIdCombustible() == this.combex.get(co).getIdCombustible()) {
                            b = 1;
                            System.out.println("Entra");
                            //SI ENTRA EN EL IF ENCUENTRA UNA COINCIDENCIA
                        }
                        System.out.println("comb=");
                        System.out.println(this.combex.get(co).getIdCombustible());
                    }
                } else {
                    b = 0;
                }
                System.out.println("b=");
                System.out.println(b);
                if (b != 1) {
                    maxCoditem = maxCoditem + 1;
                    this.selectedItem = new Item();
                    this.selectedItem.setIdItem(maxCoditem);
                    this.selectedItem.setFecha(this.combs.get(i).getFecha());
                    this.selectedItem.setCamion(this.combs.get(i).getValeCombustible().getCamion());
                    if (this.combs.get(i).getExtras() > 0.0) {
                        this.selectedItem.setPasivo(this.combs.get(i).getMontoComb() + this.combs.get(i).getExtras());
                        pro = this.combs.get(i).getValeCombustible().getProveedor().getNombre().toString();
                        litros1 = this.combs.get(i).getLitros();
                        litros2 = litros1.toString();
                        String nroboleta;
                        nroboleta = this.combs.get(i).getNBoleta();
                        Double montoex = this.combs.get(i).getExtras();
                        String montoextras = montoex.toString();
                        tipocomb = this.combs.get(i).getValeCombustible().getTipoCombustible().getNombre().toString();
                        dcomb = litros2.concat(" de ").concat(tipocomb).concat(" cargados en ").concat(pro).concat(" con Nro de Boleta: ".concat(nroboleta)).concat(" Extras Descrip: ".concat(this.combs.get(i).getValeCombustible().getDescripcion().concat(" Monto: ".concat(montoextras))));
                    } else {
                        this.selectedItem.setPasivo(this.combs.get(i).getMontoComb());
                        pro = this.combs.get(i).getValeCombustible().getProveedor().getNombre().toString();
                        litros1 = this.combs.get(i).getLitros();
                        litros2 = litros1.toString();
                        String nroboleta;
                        nroboleta = this.combs.get(i).getNBoleta();
                        tipocomb = this.combs.get(i).getValeCombustible().getTipoCombustible().getNombre().toString();
                        dcomb = litros2.concat(" de ").concat(tipocomb).concat(" cargados en ").concat(pro).concat(" con Nro de Boleta: ".concat(nroboleta));
                    }
                    this.selectedItem.setDescrip(dcomb);
                    this.selectedItem.setCod(this.combs.get(i).getIdCombustible());
                    this.selectedItem.setActivo(0.0);
                    for (int e = 0; e < this.tipocomb.size(); e++) {
                        this.selectedItem.setTipoItem(this.tipocomb.get(e));
                    }
                    selectedItemsAux.add(this.selectedItem);
                    this.selectedItem = new Item();
                }
            }
            ViaticoDao viaDao = new ViaticoDaoImpl();
            this.viaticos = new ArrayList<Viatico>();
            this.viaticos = viaDao.findCamion(this.selectedCamiones.get(h));
            //SE CARGAN TODOS LOS VIATICOS PARA COMPARAR SI YA SE ENCUENTRAN CARGADOS COMO ITEM PARA QUE NO HAYA FILTRACIONES
            for (int i = 0; i < this.viaticos.size(); i++) {
                Integer b = 0;
                for (int co = 0; co < this.viaex.size(); co++) {
                    if (this.viaticos.get(i).getIdViatico() == this.viaex.get(co).getIdViatico()) {
                        b = 1;
                        System.out.println("Entra");
                    }
                    System.out.println("via=");
                    System.out.println(this.viaex.get(co).getIdViatico());
                }
                if (b != 1) {
                    this.selectedItem = new Item();
                    maxCoditem = maxCoditem + 1;
                    String Des;
                    Des = this.viaticos.get(i).getDescripcion();
                    c = "Efectivo Retirado de la Empresa, Descrip: ".concat(Des);
                    this.selectedItem.setDescrip(c);
                    this.selectedItem.setIdItem(maxCoditem);
                    this.selectedItem.setCamion(this.viaticos.get(i).getCamion());
                    this.selectedItem.setPasivo(this.viaticos.get(i).getMonto());
                    this.selectedItem.setFecha(this.viaticos.get(i).getFecha());
                    this.selectedItem.setCod(this.viaticos.get(i).getIdViatico());
                    this.selectedItem.setActivo(0.0);
                    for (int e = 0; e < this.tipoviatico.size(); e++) {
                        this.selectedItem.setTipoItem(this.tipoviatico.get(e));
                    }
                    selectedItemsAux.add(this.selectedItem);
                    this.selectedItem = new Item();
                }
            }
            VariosDao varioDao = new VariosDaoImpl();
            this.varios = new ArrayList<Varios>();
            this.varios = varioDao.findCamion(this.selectedCamiones.get(h));
            for (int i = 0; i < this.varios.size(); i++) {
                this.selectedItem = new Item();
                maxCoditem = maxCoditem + 1;
                c = this.varios.get(i).getDescripcion().toString().concat(" Suministrado por").concat(this.varios.get(i).getValeVarios().getProveedorVarios().getNombre().toString());
                this.selectedItem.setDescrip(c);
                this.selectedItem.setIdItem(maxCoditem);
                this.selectedItem.setCamion(this.varios.get(i).getValeVarios().getCamion());
                this.selectedItem.setFecha(this.varios.get(i).getFecha());
                this.selectedItem.setPasivo(this.varios.get(i).getMonto());
                this.selectedItem.setActivo(0.0);
                this.selectedItem.setCod(this.varios.get(i).getIdVarios());
                for (int e = 0; e < this.tipovario.size(); e++) {
                    this.selectedItem.setTipoItem(this.tipovario.get(e));
                }
                selectedItemsAux.add(this.selectedItem);
                this.selectedItem = new Item();
            }
            CreditoDao creditoDao = new CreditoDaoImpl();
            this.creditos = new ArrayList<Credito>();
            this.creditos = creditoDao.findCamion(this.selectedCamiones.get(h));
            for (int i = 0; i < this.creditos.size(); i++) {
                this.selectedItem = new Item();
                maxCoditem = maxCoditem + 1;
                String descrip;
                descrip = this.creditos.get(i).getDescripcion();
                c = "Credito aprobado al Propietario- Descrip: ".concat(descrip);
                this.selectedItem.setDescrip(c);
                this.selectedItem.setIdItem(maxCoditem);
                this.selectedItem.setCamion(this.creditos.get(i).getCamion());
                this.selectedItem.setPasivo(this.creditos.get(i).getMonto());
                this.selectedItem.setFecha(this.creditos.get(i).getFecha());
                this.selectedItem.setActivo(0.0);
                this.selectedItem.setCod(this.creditos.get(i).getIdCredito());
                for (int e = 0; e < this.tipocredito.size(); e++) {
                    this.selectedItem.setTipoItem(this.tipocredito.get(e));
                }
                selectedItemsAux.add(this.selectedItem);
                this.selectedItem = new Item();
            }
            AdelantoChoferDao adeDao = new AdelantoChoferDaoImpl();
            this.adelantos = new ArrayList<AdelantoChofer>();
            this.adelantos = adeDao.findCamion(this.selectedCamiones.get(h));
            for (int i = 0; i < this.adelantos.size(); i++) {
                this.selectedItem = new Item();
                maxCoditem = maxCoditem + 1;
                String descrip;
                descrip = this.adelantos.get(i).getDescripcion();
                c = "Adelanto Entregado al Chofer: ".concat(this.adelantos.get(i).getChofer().getNombre().toString()).concat(this.adelantos.get(i).getChofer().getApellido().concat(" Descrip: ".concat(descrip)));
                this.selectedItem.setDescrip(c);
                this.selectedItem.setCamion(this.adelantos.get(i).getCamion());
                this.selectedItem.setIdItem(maxCoditem);
                this.selectedItem.setPasivo(this.adelantos.get(i).getMonto());
                this.selectedItem.setActivo(0.0);
                this.selectedItem.setFecha(this.adelantos.get(i).getFecha());
                this.selectedItem.setCod(this.adelantos.get(i).getIdAdelantoChofer());
                for (int e = 0; e < this.tipoadelanto.size(); e++) {
                    this.selectedItem.setTipoItem(this.tipoadelanto.get(e));
                }
                selectedItemsAux.add(this.selectedItem);
                this.selectedItem = new Item();
            }
            ReciboDao reciboDao = new ReciboDaoImpl();
            this.recibos = new ArrayList<Recibo>();
            this.recibos = reciboDao.findcamion(this.selectedCamiones.get(h));
            for (int i = 0; i < this.recibos.size(); i++) {
                this.selectedItem = new Item();
                maxCoditem = maxCoditem + 1;
                String descrip = this.recibos.get(i).getDescrip();
                c = "Efectivo Recibido por ".concat(this.recibos.get(i).getCamion().getPropietario().getNombre().toString()).concat(this.recibos.get(i).getCamion().getPropietario().getApellido().concat(" Descrip: ".concat(descrip)));
                this.selectedItem.setDescrip(c);
                this.selectedItem.setCamion(this.recibos.get(i).getCamion());
                this.selectedItem.setIdItem(maxCoditem);
                this.selectedItem.setPasivo(0.0);
                this.selectedItem.setActivo(this.recibos.get(i).getMonto());
                this.selectedItem.setCod(this.recibos.get(i).getIdRecibo());
                this.selectedItem.setFecha(new Date());
                for (int e = 0; e < this.tipoRecibo.size(); e++) {
                    this.selectedItem.setTipoItem(this.tipoRecibo.get(e));
                }
                selectedItemsAux.add(this.selectedItem);
                this.selectedItem = new Item();
            }
        }

        for (int ca = 0; ca < this.selectedCamiones.size(); ca++) {
            OrdenDeCargaDao ordenp = new OrdenDeCargaDaoImpl();
            this.ordenes = ordenp.findOrden(this.selectedCamiones.get(ca));
            String descripp;
            String origenp;
            String destinop;
            String clientep;
            Integer nrop;
            for (int i = 0; i < this.ordenes.size(); i++) {
                origenp = this.ordenes.get(i).getUnidadOrigen().getNombre();
                destinop = this.ordenes.get(i).getUnidadDestino().getNombre();
                clientep = this.ordenes.get(i).getCliente().getNombre();
                nrop = this.ordenes.get(i).getIdOrdenDeCarga();
                descripp = "Orden N° ".concat(nrop.toString()).concat(" -Cliente :".concat(clientep)).concat(" Con Origen: ".concat(origenp).concat(" y Destino".concat(destinop)));
                this.selectedItemp.setDescrip(descripp);
                this.selectedItemp.setActivo(0);
                this.selectedItemp.setPasivo(0);
                this.selectedItemp.setFecha(this.ordenes.get(i).getFechaEmision());
                this.selectedItemp.setCamion(this.ordenes.get(i).getCamion());
                this.itempen.add(this.selectedItemp);
                this.selectedItemp = new Item();
            }
            ValeCombDao comb = new ValeCombDaoImpl();
            this.valescomb = comb.findVales(this.selectedCamiones.get(ca));
            Double litrosp;
            Integer nropp = 0;
            String provp;
            String descrip;
            for (int i = 0; i < this.valescomb.size(); i++) {
                litrosp = this.valescomb.get(i).getLitros();
                provp = this.valescomb.get(i).getProveedor().getNombre();
                nrop = this.valescomb.get(i).getIdValeCombustible();
                descrip = "Vale de Comb N° ".concat(nropp.toString().concat(" En el surtidor: ".concat(provp).concat(" -Litros: ".concat(litrosp.toString()))));
                this.selectedItemp.setCamion(this.valescomb.get(i).getCamion());
                this.selectedItemp.setActivo(0);
                this.selectedItemp.setFecha(this.valescomb.get(i).getFecha());
                this.selectedItemp.setPasivo(this.valescomb.get(i).getMontoTotal());
                this.selectedItemp.setDescrip(descrip);
                this.itempen.add(this.selectedItemp);
                this.selectedItemp = new Item();
            }
            ValeVariosDao valev = new ValeVariosDaoImpl();
            this.variosp = valev.findVales(this.selectedCamiones.get(ca));
            String proveedor;
            Integer nrov = 0;
            String des;
            String descripv;
            for (int i = 0; i < this.variosp.size(); i++) {
                proveedor = this.variosp.get(i).getProveedorVarios().getNombre();
                nrov = this.variosp.get(i).getIdValeVarios();
                des = this.variosp.get(i).getDescripcion();
                descripv = "Vale Varios N° ".concat(nrov.toString()).concat(" -Proveedor: ".concat(proveedor).concat(" Descripcion: ".concat(des)));
                this.selectedItemp.setCamion(this.variosp.get(i).getCamion());
                this.selectedItemp.setFecha(this.variosp.get(i).getFecha());
                this.selectedItemp.setActivo(0);
                this.selectedItemp.setPasivo(this.variosp.get(i).getMonto());
                this.selectedItemp.setDescrip(descripv);
                this.itempen.add(this.selectedItemp);
                this.selectedItemp = new Item();
            }
        }
        if (this.itempen.size() > 0) {
            msg = "Camion/es seleccionados con Items pendientes, dar click en alerta.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, " ");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Camion/es seleccionados sin pendientes.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, " ");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.detextracto = new ArrayList<DetExtracto>();

    }

    public void cargarSuministro(ActionEvent event) {
        this.ordenesSumi2 = new ArrayList<OrdenDeCarga>();
        this.totalActivo = 0.0;
        this.totalPasivo = 0.0;
        this.totalcobrar = 0.0;
        OrdenDeCargaDao ordenDao = new OrdenDeCargaDaoImpl();
        for (int i = 0; i < this.selectedCamiones.size(); i++) {
            this.selectedCamion = this.selectedCamiones.get(i);
        }
        this.ordenesSumi = ordenDao.findLiq(this.selectedCamion);
        OrdenDeCarga oraux = new OrdenDeCarga();
        UnidadOrigen unio = new UnidadOrigen();
        oraux.setIdOrdenDeCarga(1);
        unio.setIdUnidadOrigen(2);
        unio.setNombre("SUMINISTROS SIN FLETES PARA LIQUIDAR.");
        oraux.setUnidadOrigen(unio);
        this.ordenesSumi.add(oraux);
        oraux = new OrdenDeCarga();
        SumiDao sumiDao = new SumiDaoImpl();
        for (int i = 0; i < this.ordenesSumi.size(); i++) {
            this.suministros = sumiDao.pendientes(this.ordenesSumi.get(i).getIdOrdenDeCarga());
            this.ordenesSumi.get(i).setChapacamion(this.selectedCamion.getChapaCamion());
        }
        this.asumi = this.sumarSuministro(suministros);
        String msg;
        msg = "Se cargaron los items para la Liquidación.";
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, " ");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/extractolist.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getExtractos()));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=extractos.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void exportarPDF2(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException {
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/newextracto.jasper"));
        codextracto = this.selectedExtracto.getIdLiquidacion();
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
        nomusu = usuario.getUsuario();
        InputStream rutaimagen = new FileInputStream(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/estees1.jasper"));
        Class.forName("org.postgresql.Driver");
        System.out.print(rutaimagen.toString());
        conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5433/newtranspbase", "postgres", "macorittogo");
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("vCodpago", this.codextracto);
        parametros.put("acufle", this.acufle);
        parametros.put("usuario", nomusu);
        parametros.put("acucomb", this.acucomb);
        parametros.put("acuvia", this.acuvia);
        parametros.put("acuvarios", this.acuvarios);
        parametros.put("acucre", this.acucre);
        parametros.put("acuade", this.acuade);
        parametros.put("acufal", this.acufal);
        parametros.put("acuse", this.acuse);
        parametros.put("acureci", this.acureci);
        parametros.put("contafle", this.contafle);
        parametros.put("rutaimagen", rutaimagen);
        parametros.put("acuactivo", this.totalActivo);
        parametros.put("acupasivo", this.totalPasivo);
        System.out.print("ESTA ES LA RUTA");
        System.out.print(this.getClass().getResourceAsStream(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/sesion.png")));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=extracto N° " + this.selectedExtracto.getIdLiquidacion() + ".pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void exportarPDFdirecto(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException {
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/newextracto.jasper"));
        codextracto = this.idpdf;
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
        nomusu = usuario.getUsuario();
        InputStream rutaimagen = new FileInputStream(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/rclogo1.png"));
        Class.forName("org.postgresql.Driver");
        conexion = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5433/newtranspbase", "postgres", "macorittogo");
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("vCodpago", this.codextracto);
        parametros.put("acufle", this.acufle);
        parametros.put("usuario", nomusu);
        parametros.put("acucomb", this.acucomb);
        parametros.put("acuvia", this.acuvia);
        parametros.put("acuvarios", this.acuvarios);
        parametros.put("acucre", this.acucre);
        parametros.put("acuade", this.acuade);
        parametros.put("acufal", this.acufal);
        parametros.put("acuse", this.acuse);
        parametros.put("acureci", this.acureci);
        parametros.put("contafle", this.contafle);
        parametros.put("rutaimagen", rutaimagen);
        parametros.put("acuactivo", this.totalActivo);
        parametros.put("acupasivo", this.totalPasivo);
        System.out.print("ESTA ES LA RUTA");
        System.out.print(this.getClass().getResourceAsStream(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/sesion.png")));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conexion);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=extracto N° " + codextracto + ".pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void btnCargar(ActionEvent event) {
        String msg;
        System.out.println("Tamaño de array antes");
        System.out.print(this.selectedItems.size());
        this.selectedItems1 = new ArrayList<Item>();
        for (int i = 0; i < this.selectedItems.size(); i++) {
            selectedItemsAux.remove(this.selectedItems.get(i));
        }
        for (int i = 0; i < this.selectedItems.size(); i++) {
            ItemDao itemDao = new ItemDaoImpl();
            this.selectedItemcompa = new Item();
            this.selectedItemcompa = itemDao.Itemcompa(this.selectedItems.get(i).getCamion(), this.selectedItems.get(i).getCod(), this.selectedItems.get(i).getTipoItem().getIdTipo());
            if (this.selectedItemcompa != null) {
                this.selectedItems1.add(this.selectedItemcompa);
                this.selectedItemcompa = new Item();
            } else {
                this.selectedItems1.add(this.selectedItems.get(i));
            }
        }
        System.out.println("Tamaño de array despues");
        System.out.print(this.selectedItems.size());
        for (int i = 0; i < this.selectedItems1.size(); i++) {
            this.selectedDetextracto.setItem(this.selectedItems1.get(i));
            this.selectedDetextracto.setIdDet(this.selectedItems1.get(i).getIdItem());
            this.selectedDetextracto.setActivo(this.selectedItems1.get(i).getActivo());
            this.selectedDetextracto.setPasivo(this.selectedItems1.get(i).getPasivo());
            this.selectedDetextracto.setDescrip(this.selectedItems1.get(i).getDescrip());
            detextracto.add(this.selectedDetextracto);
            //this.totalActivo = this.sumarActivo(detextracto);
            this.totalPasivo = this.sumarPasivo(detextracto);
            this.selectedDetextracto = new DetExtracto();
        }
        totalcobrar = (this.totalActivo - this.totalPasivo);
        msg = "Se Creo correctamente el registro";
        OrdenDeCargaDao ordenp = new OrdenDeCargaDaoImpl();

    }

    public void bntDescargar(DetExtracto detExtracto) {
        selectedItemsAux.add(detExtracto.getItem());
        selectedItems1.remove(detExtracto.getItem());
        selectedItems.remove(detExtracto.getItem());
        detextracto.remove(detExtracto);
        //this.totalActivo = this.sumarActivo(detextracto);
        //this.totalPasivo = this.sumarPasivo(detextracto);
        totalcobrar = (this.totalActivo - this.totalPasivo);
        //for(int i=0; i <this.detpagov.size(); i++){
        //if(this.detpagov.get(i)==detallePagoVarios){
        //selectedAux.add(detallePagoVarios.getVarios());
        //}
        //}
        //this.selectedExtracto.setMonto(this.sumarTotal(detextracto));          
    }

    public void bntAdd(DetExtracto detExtracto) {
        this.selectedDetextracto.setItem(this.selectedItem);
        this.selectedDetextracto.setIdDet(this.selectedItem.getIdItem());
        this.selectedDetextracto.setActivo(this.selectedItem.getActivo());
        this.selectedDetextracto.setPasivo(this.selectedItem.getPasivo());
        this.selectedDetextracto.setDescrip(this.selectedItem.getDescrip());
        selectedItems.add(this.selectedItem);
        detextracto.add(this.selectedDetextracto);
        selectedItemsAux.remove(this.selectedItem);
        //this.totalActivo = this.sumarActivo(detextracto);
        this.totalPasivo = this.sumarPasivo(detextracto);
        //this.acufle = this.sumarFlete(detextracto);
        //this.acucomb = this.sumarComb(detextracto);
        this.acuade = this.sumarAdelanto(detextracto);
        //this.acucre = this.sumarCredito(detextracto);
        //this.acufal = this.sumarFaltante(detextracto);
        this.acureci = this.sumarRecibo(detextracto);
        //this.acuse = this.sumarSeguro(detextracto);
        //this.acuvarios = this.sumarVarios(detextracto);
        //this.acuvia = this.sumarVia(detextracto);
        totalcobrar = (this.totalActivo - this.totalPasivo);
        this.selectedDetextracto = new DetExtracto();
        this.selectedItem = new Item();
    }

    public void bntAddorden(OrdenDeCarga orden) {
        List<sumitem> arraycarga = new ArrayList<sumitem>();
        System.out.print(" ENTRA EN EL BTN ADD ORDEN ");
        System.out.print(orden.getSaldoextra());
        System.out.print(" ESTE ES EL TAMAÑO DE SU ARRAY ");
        System.out.print(orden.getSumitems().size());
        arraycarga = orden.getSumitems();
        for (int e = 0; e < arraycarga.size(); e++) {
            this.sumitemsex.add(arraycarga.get(e));
        }
        System.out.print("este es el tamaño de detextracto");
        System.out.print(this.detextracto.size());
        System.out.print("este es el nuevo array");
        System.out.print(this.sumitemsex.size());
        this.ordenesSumi2.add(orden);
        this.ordenesSumi.remove(orden);
        this.acufle = this.sumarFlete(this.sumitemsex);
        this.acucomb = this.sumarComb(this.sumitemsex);
        this.acucre = this.sumarCredito(this.sumitemsex);
        this.acufal = this.sumarFaltante(this.sumitemsex);
        //this.acureci = this.sumarRecibo(detextracto);
        this.acuse = this.sumarSeguro(this.sumitemsex);
        this.acuvarios = this.sumarVarios(this.sumitemsex);
        this.acuvia = this.sumarVia(this.sumitemsex);
        this.totalcobrar = this.totalcobrar + orden.getSaldoextra();
        this.totalActivo = this.totalActivo + orden.getTotalactivo();
        this.totalPasivo = this.totalPasivo + orden.getTotalpasivo();
        orden = new OrdenDeCarga();
    }

    public void bntDescargarOrden(OrdenDeCarga orden) {
        List<sumitem> arraydescarga = new ArrayList<sumitem>();
        System.out.print(" ENTRA EN EL BTN ADD ORDEN ");
        System.out.print(orden.getSaldoextra());
        System.out.print(" ESTE ES EL TAMAÑO DE SU ARRAY ");
        System.out.print(orden.getSumitems().size());
        arraydescarga = orden.getSumitems();
        System.out.print(" ESTE ES EL TAMAÑO DE SU ARRAY ANTES DE REMOVER");
        System.out.print(this.sumitemsex.size());
        for (int e = 0; e < arraydescarga.size(); e++) {
            this.sumitemsex.remove(arraydescarga.get(e));
        }
        System.out.print(" ESTE ES EL TAMAÑO DE SU ARRAY DESPUES DE REMOVER");
        System.out.print(this.sumitemsex.size());
        this.ordenesSumi.add(orden);
        this.ordenesSumi2.remove(orden);
        System.out.print(" ESTE ES EL TOTAL ");
        System.out.print(this.totalcobrar);
        System.out.print(" ESTE ES EL TOTAL en la orden ");
        System.out.print(orden.getSaldoextra());
        this.acufle = this.sumarFlete(this.sumitemsex);
        this.acucomb = this.sumarComb(this.sumitemsex);
        this.acucre = this.sumarCredito(this.sumitemsex);
        this.acufal = this.sumarFaltante(this.sumitemsex);
        //this.acureci = this.sumarRecibo(detextracto);
        this.acuse = this.sumarSeguro(this.sumitemsex);
        this.acuvarios = this.sumarVarios(this.sumitemsex);
        this.acuvia = this.sumarVia(this.sumitemsex);
        this.totalcobrar = this.totalcobrar - orden.getSaldoextra1();
        this.totalActivo = this.totalActivo - orden.getTotalactivo1();
        this.totalPasivo = this.totalPasivo - orden.getTotalpasivo1();
        orden = new OrdenDeCarga();
    }

    public void btnCreatePago() {
        ExtractoDao exDao = new ExtractoDaoImpl();
        String msg;
        FacesMessage message;
        DetOrden detorden = new DetOrden();
        List<DetOrden> detordenes = new ArrayList<DetOrden>();
        ExtractoSumi exsumi = new ExtractoSumi();
        ExtractoSumicli exsumicli = new ExtractoSumicli();
        List<ExtractoSumi> detexsumi = new ArrayList<ExtractoSumi>();
        List<Item> items = new ArrayList<Item>();
        List<ExtractoSumicli> detexsumicli = new ArrayList<ExtractoSumicli>();
        List<SumiCliente> sumiCliente1 = new ArrayList<SumiCliente>();
        List<Viaje> fletes = new ArrayList<Viaje>();
        List<Viaje> fletes1 = new ArrayList<Viaje>();
        Viaje selectedFlete = new Viaje();
        Suministro selsumi = new Suministro();
        SumiCliente selsumicli = new SumiCliente();
        TipoItem sitem = new TipoItem();
        ItemDao itemcrear = new ItemDaoImpl();
        this.selectedExtracto.setEstado("Pagado");
        this.selectedExtracto.setFechaPago(this.selectedExtracto.getFecha());
        this.selectedExtracto.setMonto(this.totalcobrar.intValue());
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
        this.selectedExtracto.setUsuario(usuario);
        for (int e = 0; e < this.sumitemsex.size(); e++) {
            //this.selectedItem.setIdItem(1);
            this.selectedCamion.setChapaCamion(this.sumitemsex.get(e).getChapa());
            this.selectedItem.setCamion(selectedCamion);
            this.selectedItem.setDescrip(this.sumitemsex.get(e).getDescrip());
            this.selectedItem.setEstado("Pagado");
            this.selectedItem.setFecha(this.sumitemsex.get(e).getFecha());
            this.selectedItem.setCod(this.sumitemsex.get(e).getId());
            //this.selectedDetextracto.setItem(this.selectedItem);            
            if (this.sumitemsex.get(e).getTipo().equals("Flete")) {
                this.selectedDetextracto.setActivo(this.sumitemsex.get(e).getMonto());
                this.selectedDetextracto.setPasivo(0.0);
                this.selectedItem.setActivo(this.sumitemsex.get(e).getMonto());
                this.selectedItem.setPasivo(0.0);
                selectedFlete.setIdViaje(this.sumitemsex.get(e).getId());
                fletes.add(selectedFlete);
                selectedFlete = new Viaje();
                sitem.setIdTipo(6);
                this.selectedItem.setTipoItem(sitem);
            } else {
                if (this.sumitemsex.get(e).getTipo().equals("SumiCli")) {
                    this.selectedItem.setActivo(0.0);
                    this.selectedItem.setPasivo(this.sumitemsex.get(e).getMonto());
                    this.selectedDetextracto.setActivo(0.0);
                    this.selectedDetextracto.setPasivo(this.sumitemsex.get(e).getMonto());
                    exsumicli.setDescrip(this.sumitemsex.get(e).getDescrip());
                    exsumicli.setMonto(this.sumitemsex.get(e).getMonto());
                    exsumicli.setDetExtracto(this.selectedDetextracto);
                    selsumicli.setIdSumiCli(this.sumitemsex.get(e).getId());
                    exsumicli.setSumiCliente(selsumicli);
                    detexsumicli.add(exsumicli);
                    exsumicli = new ExtractoSumicli();
                    selsumicli = new SumiCliente();
                    sitem.setIdTipo(2);
                    this.selectedItem.setTipoItem(sitem);
                } else {
                    this.selectedItem.setActivo(0.0);
                    this.selectedItem.setPasivo(this.sumitemsex.get(e).getMonto());
                    this.selectedDetextracto.setActivo(0.0);
                    this.selectedDetextracto.setPasivo(this.sumitemsex.get(e).getMonto());
                    exsumi.setDescrip(this.sumitemsex.get(e).getDescrip());
                    exsumi.setMonto(this.sumitemsex.get(e).getMonto());
                    exsumi.setDetExtracto(this.selectedDetextracto);
                    System.out.print("CUANDO CARGA EL ID DEL SUMINISTRO");
                    System.out.print(this.sumitemsex.get(e).getId());
                    selsumi.setIdSuministro(this.sumitemsex.get(e).getId());
                    exsumi.setSuministro(selsumi);
                    detexsumi.add(exsumi);
                    exsumi = new ExtractoSumi();
                    selsumi = new Suministro();
                    if (this.sumitemsex.get(e).getTipo().equals("Viatico")) {
                        sitem.setIdTipo(0);
                        this.selectedItem.setTipoItem(sitem);
                    } else {
                        if (this.sumitemsex.get(e).getTipo().equals("Combustible")) {
                            sitem.setIdTipo(1);
                            this.selectedItem.setTipoItem(sitem);
                        } else {
                            if (this.sumitemsex.get(e).getTipo().equals("Credito")) {
                                sitem.setIdTipo(2);
                                this.selectedItem.setTipoItem(sitem);
                            } else {
                                if (this.sumitemsex.get(e).getTipo().equals("Varios")) {
                                    sitem.setIdTipo(3);
                                    this.selectedItem.setTipoItem(sitem);
                                } else {
                                    if (this.sumitemsex.get(e).getTipo().equals("Faltante")) {
                                        sitem.setIdTipo(4);
                                        this.selectedItem.setTipoItem(sitem);
                                    } else {
                                        if (this.sumitemsex.get(e).getTipo().equals("Seguro")) {
                                            sitem.setIdTipo(5);
                                            this.selectedItem.setTipoItem(sitem);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
            detorden.setDescrip(this.sumitemsex.get(e).getDescrip());
            detorden.setDetExtracto(this.selectedDetextracto);
            detorden.setMonto(this.sumitemsex.get(e).getMonto());
            this.selectedOrden = new OrdenDeCarga();
            this.selectedOrden.setIdOrdenDeCarga(this.sumitemsex.get(e).getOrden());
            detorden.setOrdenDeCarga(this.selectedOrden);
            detordenes.add(detorden);
            detorden = new DetOrden();
            this.selectedItem.setOrdenDeCarga(this.selectedOrden);
            //this.selectedItem.setExtracto(this.selectedExtracto);
            items.add(this.selectedItem);
            itemcrear.create(this.selectedItem);
            this.selectedDetextracto.setItem(this.selectedItem);
            this.selectedDetextracto.setDescrip(this.sumitemsex.get(e).getDescrip());
            this.detextracto.add(this.selectedDetextracto);
            this.selectedItem = new Item();
            this.selectedDetextracto = new DetExtracto();
            this.selectedOrden = new OrdenDeCarga();
        }
        this.selectedEx = new ExCamion();
        for (int e = 0; e < this.selectedCamiones.size(); e++) {
            this.selectedEx.setCamion(this.selectedCamiones.get(e));
            this.excamion.add(this.selectedEx);
            this.selectedEx = new ExCamion();
        }

        ExOrden exorden = new ExOrden();
        List<ExOrden> exordenes = new ArrayList<ExOrden>();
        for (int e = 0; e < this.ordenesSumi2.size(); e++) {
            exorden.setOrdenDeCarga(this.ordenesSumi2.get(e));
            exorden.setExtracto(this.selectedExtracto);
            exorden.setDescrip("adasd");
            exordenes.add(exorden);
            exorden = new ExOrden();
        }
        Suministro sumiupdate = new Suministro();
        SumiDao sumiDaoup = new SumiDaoImpl();
        SumiCliente sumicliupdate = new SumiCliente();
        SumiCliDao sumicliDaoup = new SumiCliDaoImpl();
        Viaje selectedFleteup = new Viaje();
//       for(int i=0; i<this.selectedItems.size(); i++){
//           ItemDao itemDao = new ItemDaoImpl();
////           this.selectedItemcompa = new Item();
////           this.selectedItemcompa = itemDao.Itemcompa(this.selectedItems.get(i).getCamion(), this.selectedItems.get(i).getCod(), this.selectedItems.get(i).getTipoItem().getIdTipo());
////           if(this.selectedItemcompa!=null){
////               this.selectedItemcompa.setEstado("Pagado");
////               itemDao.update(selectedItemcompa);
////               this.selectedItems.add(this.selectedItemcompa);
////               this.selectedItems.remove(this.selectedItems.get(i));               
////           }else{
//                this.selectedItems.get(i).setEstado("Pagado");           
//                itemDao.create(selectedItems.get(i));         
////           }
//       }

        if (exDao.create(this.selectedExtracto, this.detextracto, this.excamion, exordenes)) {
//            DetOrdenDao detDao = new DetOrdenDaoImpl();
//            for (int e = 0; e < detordenes.size(); e++) {
//                if (detDao.create(detordenes.get(e))) {
//                    System.out.print("CREA");
//                } else {
//                    System.out.print("NO CREA");
//                }
//            }
            for (int e = 0; e < items.size(); e++) {
                System.out.print("ESTA ES LA DESCRIPCION Y EL TIPO");
                System.out.print(items.get(e).getDescrip());
                System.out.print(items.get(e).getTipoItem());
                items.get(e).setExtracto(this.selectedExtracto);
                itemcrear.update(items.get(e));
            }
            ExSumiDao detExsumi = new ExSumiDaoImpl();
            List<Suministro> sumiup = new ArrayList<Suministro>();
            for (int e = 0; e < detexsumi.size(); e++) {
                if (detExsumi.create(detexsumi.get(e))) {
                    sumiup = sumiDaoup.encontrar(detexsumi.get(e).getSuministro().getIdSuministro());
                    for (int i = 0; i < sumiup.size(); i++) {
                        sumiupdate = sumiup.get(i);
                    }
                    System.out.print("CREA el siumi");
                    sumiupdate.setEstadoCobro("Pagado");
                    sumiDaoup.update(sumiupdate);
                    sumiupdate = new Suministro();
                } else {
                    System.out.print("NO CREA el sumi");
                }
            }
            ViajeDao fleteDao = new ViajeDaoImpl();
            for (int e = 0; e < fletes.size(); e++) {
                fletes1 = fleteDao.findOne(fletes.get(e).getIdViaje());
                for (int i = 0; i < fletes1.size(); i++) {
                    selectedFleteup = fletes1.get(i);
                }
                selectedFleteup.setEstadoCobro("Pagado");
                if (fleteDao.update(selectedFleteup)) {
                    System.out.print("MODIFICA");
                } else {
                    System.out.print("NO MODIFICA EL FLETE");
                }
                selectedFleteup = new Viaje();
                fletes1 = new ArrayList<Viaje>();
            }
            ExSumicliDao detExsumicli = new ExSumicliDaoImpl();
            for (int e = 0; e < detexsumicli.size(); e++) {
                if (detExsumicli.create(detexsumicli.get(e))) {
                    sumiCliente1 = sumicliDaoup.encontrar(detexsumicli.get(e).getSumiCliente().getIdSumiCli());
                    for (int i = 0; i < sumiCliente1.size(); i++) {
                        sumicliupdate = sumiCliente1.get(i);
                    }
                    sumicliupdate.setEstadoCobro("Pagado");
                    sumicliDaoup.update(sumicliupdate);
                    System.out.print("CREA el siumi 2");
                    sumicliupdate = new SumiCliente();
                } else {
                    System.out.print("NO CREA el sumi 2");
                }
            }
            OrdenDeCargaDao ordenDao = new OrdenDeCargaDaoImpl();
            OrdenDeCarga ordenupdate = new OrdenDeCarga();
            List<OrdenDeCarga> ordenesup = new ArrayList<OrdenDeCarga>();
            for (int e = 0; e < exordenes.size(); e++) {
                System.out.print("AL EMPEZAR");
                ordenesup = ordenDao.encontrar(exordenes.get(e).getOrdenDeCarga().getIdOrdenDeCarga());
                for (int i = 0; i < ordenesup.size(); i++) {
                    ordenupdate = ordenesup.get(i);
                    System.out.print("ACA CARGA EL OBJETO");
                }
                if (ordenupdate.getIdOrdenDeCarga() == 1) {
                    System.out.print("ACA NO MODIFICA EL ESTADO DE LA ORDEN");
                } else {
                    ordenupdate.setEstadoliq("Pagado");
                    ordenupdate.setFechaEmision(new Date());
                    if (ordenDao.update(ordenupdate)) {

                        System.out.print("ACA MODIFICA");
                        System.out.print(ordenupdate.getEstadoliq());
                    } else {
                        System.out.print("aca no modifica");
                    }
                    ordenupdate = new OrdenDeCarga();
                }

            }
//            for (int e = 0; e < this.selectedItems.size(); e++) {
//                if (this.selectedItems.get(e).getTipoItem().getIdTipo() == 0) {
//                    ViajeDao viajeDao = new ViajeDaoImpl();
//                    this.viajeOne = new ArrayList<Viaje>();
//                    this.viajeOne = viajeDao.findOne(this.selectedItems.get(e).getCod());
//                    for (int h = 0; h < this.viajeOne.size(); h++) {
//                        this.selectedViaje = this.viajeOne.get(h);
//                    }
//                    this.selectedViaje.setEstadoPago("Pagado");
//                    if (viajeDao.update(this.selectedViaje)) {
//                        this.selectedViaje = new Viaje();
//                    }
//                }
//                if (this.selectedItems.get(e).getTipoItem().getIdTipo() == 1) {
//                    CombDao combDao = new CombDaoImpl();
//                    this.combOne = new ArrayList<Combustible>();
//                    this.combOne = combDao.findOne(this.selectedItems.get(e).getCod());
//                    for (int h = 0; h < this.combOne.size(); h++) {
//                        this.selectedComb = this.combOne.get(h);
//                    }
//                    this.selectedComb.setEstadoCobro("Pagado");
//                    if (combDao.update(this.selectedComb)) {
//                        this.selectedComb = new Combustible();
//                    }
//                }
//                if (this.selectedItems.get(e).getTipoItem().getIdTipo() == 2) {
//                    ViaticoDao viaticoDao = new ViaticoDaoImpl();
//                    this.viaticoOne = new ArrayList<Viatico>();
//                    this.viaticoOne = viaticoDao.find(this.selectedItems.get(e).getCod());
//                    for (int h = 0; h < this.viaticoOne.size(); h++) {
//                        this.selectedViatico = this.viaticoOne.get(h);
//                    }
//                    this.selectedViatico.setEstado("Pagado");
//                    if (viaticoDao.update(this.selectedViatico)) {
//                        this.selectedViatico = new Viatico();
//                    }
//                }
//                if (this.selectedItems.get(e).getTipoItem().getIdTipo() == 3) {
//                    VariosDao variosDao = new VariosDaoImpl();
//                    this.varioOne = new ArrayList<Varios>();
//                    this.varioOne = variosDao.findOne(this.selectedItems.get(e).getCod());
//                    for (int h = 0; h < this.varioOne.size(); h++) {
//                        this.selectedVario = this.varioOne.get(h);
//                    }
//                    this.selectedVario.setEstadoCobro("Pagado");
//                    if (variosDao.update(this.selectedVario)) {
//                        this.selectedVario = new Varios();
//                    }
//                }
//                if (this.selectedItems.get(e).getTipoItem().getIdTipo() == 4) {
//                    CreditoDao creditoDao = new CreditoDaoImpl();
//                    this.creditoOne = new ArrayList<Credito>();
//                    this.creditoOne = creditoDao.findOne(this.selectedItems.get(e).getCod());
//                    for (int h = 0; h < this.creditoOne.size(); h++) {
//                        this.selectedCredito = this.creditoOne.get(h);
//                    }
//                    this.selectedCredito.setEstadoCobro("Pagado");
//                    if (creditoDao.update(this.selectedCredito)) {
//                        this.selectedCredito = new Credito();
//                    }
//                }
//                if (this.selectedItems.get(e).getTipoItem().getIdTipo() == 5) {
//                    AdelantoChoferDao adechoDao = new AdelantoChoferDaoImpl();
//                    this.adechoOne = new ArrayList<AdelantoChofer>();
//                    this.adechoOne = adechoDao.findOne(this.selectedItems.get(e).getCod());
//                    for (int h = 0; h < this.adechoOne.size(); h++) {
//                        this.selectedAdelanto = this.adechoOne.get(h);
//                    }
//                    this.selectedAdelanto.setEstadoCobro("Pagado");
//                    if (adechoDao.update(this.selectedAdelanto)) {
//                        this.selectedAdelanto = new AdelantoChofer();
//                    }
//                }
//                if (this.selectedItems.get(e).getTipoItem().getIdTipo() == 8) {
//                    ReciboDao reciDao = new ReciboDaoImpl();
//                    this.reciboOne = new ArrayList<Recibo>();
//                    this.reciboOne = reciDao.findOne(this.selectedItems.get(e).getCod());
//                    for (int h = 0; h < this.reciboOne.size(); h++) {
//                        this.selectedRecibo = this.reciboOne.get(h);
//                    }
//                    this.selectedRecibo.setEstado("Pagado");
//                    if (reciDao.update(this.selectedRecibo)) {
//                        this.selectedRecibo = new Recibo();
//                    }
//                }
//            }
            if (this.selectedExtracto.getMonto() < 0) {
                String nroliq;
                Integer nroliqui;
                nroliqui = this.selectedExtracto.getIdLiquidacion();
                nroliq = nroliqui.toString();
                Double monto;
                Integer monto1;
                monto = this.selectedExtracto.getMonto();
                monto1 = (monto.intValue()) * (-1);
                this.selectedCredito2.setEstadoCobro("Pendiente");
                this.selectedCredito2.setDescripcion("Saldo Negativo de la liquidación Nro.:".concat(nroliq));
                this.selectedCredito2.setFecha(this.selectedExtracto.getFecha());
                this.selectedCredito2.setMonto(monto1);
                this.selectedCredito2.setFechaVencimiento(this.selectedExtracto.getFecha());
                this.selectedCredito2.setUsuario(usuario);
                for (int i = 0; i < this.excamion.size(); i++) {
                    this.selectedCredito2.setCamion(this.excamion.get(i).getCamion());
                }
                CreditoDao credito = new CreditoDaoImpl();
                credito.create(this.selectedCredito2);
            }
            msg = "Se Creo correctamente el registro";
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, " ");
            exordenes = new ArrayList<ExOrden>();
            this.idpdf = this.selectedExtracto.getIdLiquidacion();
            this.extractos = new ArrayList<Extracto>();
            this.selectedExtracto = new Extracto();
            this.items = new ArrayList<Item>();
            this.selectedItem = new Item();
            this.selectedCamion = new Camion();
            this.detextracto = new ArrayList<DetExtracto>();
            this.detextractoaux = new ArrayList<DetExtracto>();
            this.selectedDetextracto = new DetExtracto();
            this.selectedDetExtractoAux = new DetExtracto();
            this.selectedItems = new ArrayList<Item>();
            this.selectedItemsAux = new ArrayList<Item>();
            this.selectedExtracto.setFecha(new Date());
            this.selectedPropietario = new Propietario();
            this.combs = new ArrayList<Combustible>();
            this.viaticos = new ArrayList<Viatico>();
            this.selectedCamiones = new ArrayList<Camion>();
            this.camiones = new ArrayList<Camion>();
            this.varios = new ArrayList<Varios>();
            this.creditos = new ArrayList<Credito>();
            this.adelantos = new ArrayList<AdelantoChofer>();
            this.excamion = new ArrayList<ExCamion>();
            this.camionvista = new ArrayList<ExCamion>();
            this.selectedEx = new ExCamion();
            this.selectedViaje = new Viaje();
            this.selectedComb = new Combustible();
            this.itempen = new ArrayList<Item>();
            this.selectedViatico = new Viatico();
            this.estados = new ArrayList<Extracto>();
            this.selectedEstado = new Extracto();
            this.propietarios = new ArrayList<Propietario>();
            this.ordenes = new ArrayList<OrdenDeCarga>();
            this.selectedItemp = new Item();
            this.valescomb = new ArrayList<ValeCombustible>();
            this.combflete = new ArrayList<Combustible>();
            this.viaex = new ArrayList<Viatico>();
            this.viaflete = new ArrayList<Viatico>();
            this.selectedCredito = new Credito();
            this.selectedCredito2 = new Credito();
            this.combex = new ArrayList<Combustible>();
            this.recibos = new ArrayList<Recibo>();
            this.selectedRecibo = new Recibo();
            this.selectedItems1 = new ArrayList<Item>();
            this.total = 0.0;
            this.totalcobrar = 0.0;
            this.codextracto = 0;
            this.tipocomb = new ArrayList<TipoItem>();
            this.tipoviatico = new ArrayList<TipoItem>();
            this.tipovario = new ArrayList<TipoItem>();
            this.tipocredito = new ArrayList<TipoItem>();
            this.tipoadelanto = new ArrayList<TipoItem>();
            this.tipofaltante = new ArrayList<TipoItem>();
            this.tiposeguro = new ArrayList<TipoItem>();
            this.tipoRecibo = new ArrayList<TipoItem>();
            this.items = new ArrayList<Item>();
            this.selectedItem = new Item();
            this.selectedItemView = new Item();
            this.selectedCamion = new Camion();
            this.selectedItems = new ArrayList<Item>();
            this.detextracto = new ArrayList<DetExtracto>();
            this.detextractoaux = new ArrayList<DetExtracto>();
            this.selectedCamiones = new ArrayList<Camion>();
            items = new ArrayList<Item>();
            this.sumitemsex = new ArrayList<sumitem>();
        } else {
            msg = "Error al Crear el registro";
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            for (int i = 0; i < this.selectedItems.size(); i++) {
                ItemDao itemDao = new ItemDaoImpl();
                if (itemDao.delete(selectedItems.get(i).getIdItem())) {
                }
            }
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onRowSelectCamion(SelectEvent event) {
        //this.selectedExtracto.setCamion(this.selectedCamion);
    }

    public void onRowSelectExtracto(SelectEvent event) {
    }

    public void onRowSelectPropietario(SelectEvent event) {
        this.selectedExtracto.setPropietario(this.selectedPropietario);
        this.acuade = 0.0;
        this.acucomb = 0.0;
        this.acucre = 0.0;
        this.acufal = 0.0;
        this.acufle = 0.0;
        this.acureci = 0.0;
        this.acuse = 0.0;
        this.acuvarios = 0.0;
        this.acuvia = 0.0;
        this.contafle = 0;
        this.totalActivo = 0.0;
        this.totalPasivo = 0.0;
        this.totalcobrar = 0.0;
        this.items = new ArrayList<Item>();
        this.selectedItem = new Item();
        this.selectedItemView = new Item();
        this.selectedCamion = new Camion();
        this.selectedItems = new ArrayList<Item>();
        this.detextracto = new ArrayList<DetExtracto>();
        this.detextractoaux = new ArrayList<DetExtracto>();
        this.selectedCamiones = new ArrayList<Camion>();
    }

    public void btnUpdateExtracto(ActionEvent actionEvent) {
        ExtractoDao exDao = new ExtractoDaoImpl();
        Integer bande;
        String msg;
        String est;
        String a;
        if (!this.estadonativo.equals("Anulado")) {
        //a="Anulado";
            //est=this.selectedPago.getEstado();
            //DetpagovDao detpagoDao = new DetpagovDaoImpl();
            //this.detpagovista=detpagoDao.findByPagov(this.selectedPago);
            //if(a.equals(this.selectedPago.getEstado())){
            //bande=1;
            //}else{
            //bande=0;
            //}
            //System.out.println(bande);
            //System.out.println(this.selectedPago.getDescripcion());
            //System.out.println(this.selectedPago.getEstado());
            String es = "Anulado";
            this.detextractoup = new ArrayList<DetExtracto>();
            this.detextractoup = exDao.findByDetextracto(this.selectedExtracto.getIdLiquidacion());
            if (exDao.update(this.selectedExtracto)) {
                msg = "Se actualizo correctamente el registro";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, " ");
                FacesContext.getCurrentInstance().addMessage(null, message);
                DetExDao exdao = new DetExDaoImpl();
                this.viajeOne = new ArrayList<Viaje>();
                this.combOne = new ArrayList<Combustible>();
                this.viaticoOne = new ArrayList<Viatico>();
                this.varioOne = new ArrayList<Varios>();
                this.creditoOne = new ArrayList<Credito>();
                this.adechoOne = new ArrayList<AdelantoChofer>();
                this.reciboOne = new ArrayList<Recibo>();
                if (this.selectedExtracto.getEstado().equals(es)) {
                    if (this.selectedExtracto.getMonto() < 0) {
                        System.out.print("Entra en el 1");
                        CreditoDao creditoDao = new CreditoDaoImpl();
                        this.selectedCredito = new Credito();
                        Integer NroLiq = this.selectedExtracto.getIdLiquidacion();;
                        String liqnro = NroLiq.toString();
                        this.creditoOne = new ArrayList<Credito>();
                        this.creditoOne = creditoDao.findOneUp(liqnro);
                        System.out.print("Tamaño del array");
                        System.out.print(this.creditoOne.size());
                        if (creditoOne != null) {
                            System.out.print("Entra en el if");
                            System.out.print(this.selectedCredito.getMonto());
                            for (int h = 0; h < this.creditoOne.size(); h++) {
                                this.selectedCredito = this.creditoOne.get(h);
                            }
                            creditoDao.delete(this.selectedCredito.getIdCredito());
                            this.selectedCredito = new Credito();
                        }
                    }
                    System.out.println("Entra al if de anulado");
                    ExtractoSumi exsumi = new ExtractoSumi();
                    List<ExtractoSumi> exsumilist = new ArrayList<ExtractoSumi>();
                    ExSumiDao exsumiDao = new ExSumiDaoImpl();
                    Suministro selsumi = new Suministro();
                    SumiDao sumiDao = new SumiDaoImpl();
                    ExtractoSumicli exsumicli = new ExtractoSumicli();
                    List<ExtractoSumicli> exsumiclilist = new ArrayList<ExtractoSumicli>();
                    ExSumicliDao exsumiclidao = new ExSumicliDaoImpl();
                    SumiCliente selsumicli = new SumiCliente();
                    SumiCliDao sumicliDao = new SumiCliDaoImpl();
                    ExOrden exorden = new ExOrden();
                    List<ExOrden> exordenlist = new ArrayList<ExOrden>();
                    ExOrdenDao exordenDao = new ExOrdenDaoImpl();
                    OrdenDeCargaDao ordenDao = new OrdenDeCargaDaoImpl();
                    OrdenDeCarga seleorden = new OrdenDeCarga();
                    for (int i = 0; i < this.detextractoup.size(); i++) {
                        if (this.detextractoup.get(i).getItem().getTipoItem().getIdTipo() == 6) {
                            ViajeDao viajeDao = new ViajeDaoImpl();
                            this.viajeOne = viajeDao.findOne(this.detextractoup.get(i).getItem().getCod());
                            for (int h = 0; h < this.viajeOne.size(); h++) {
                                this.selectedViaje = this.viajeOne.get(h);
                            }
                            this.selectedViaje.setEstadoCobro("Pendiente");
                            if (viajeDao.update(this.selectedViaje)) {
                                this.selectedViaje = new Viaje();
                            }
                        } else {
                            exsumilist = exsumiDao.findByExtracto(this.detextractoup.get(i).getIdDet());
                            System.out.print("NUMERO DEL ARRAY");
                            System.out.print(exsumilist.size());
                            if (exsumilist.size()!=0) {
                                for (int h = 0; h < exsumilist.size(); h++) {
                                    exsumi = exsumilist.get(h);
                                    selsumi = exsumilist.get(h).getSuministro();
                                }
                                selsumi.setEstadoCobro("Pendiente");
                                sumiDao.update(selsumi);
                                exsumi = new ExtractoSumi();
                                exsumilist = new ArrayList<ExtractoSumi>();
                                selsumi = new Suministro();
                            } else {
                                exsumiclilist = exsumiclidao.findByExtracto(this.detextractoup.get(i).getIdDet());
                                if (exsumiclilist != null) {
                                    for (int h = 0; h < exsumiclilist.size(); h++) {
                                        exsumicli = exsumiclilist.get(h);
                                        selsumicli = exsumiclilist.get(h).getSumiCliente();
                                    }
                                    selsumicli.setEstadoCobro("Pendiente");
                                    sumicliDao.update(selsumicli);
                                    exsumicli = new ExtractoSumicli();
                                    exsumiclilist = new ArrayList<ExtractoSumicli>();
                                    selsumicli = new SumiCliente();
                                }
                            }
//                    if (this.detextractoup.get(i).getItem().getTipoItem().getIdTipo() == 1) {
//                        CombDao combDao = new CombDaoImpl();
//                        this.combOne = combDao.findOne(this.detextractoup.get(i).getItem().getCod());
//                        for (int h = 0; h < this.combOne.size(); h++) {
//                            this.selectedComb = this.combOne.get(h);
//                        }
//                        this.selectedComb.setEstadoCobro("Pendiente");
//                        if (combDao.update(this.selectedComb)) {
//                            this.selectedComb = new Combustible();
//                        }
//                    }
//                    if (this.detextractoup.get(i).getItem().getTipoItem().getIdTipo() == 2) {
//                        ViaticoDao viaticoDao = new ViaticoDaoImpl();
//                        this.viaticoOne = viaticoDao.find(this.detextractoup.get(i).getItem().getCod());
//                        for (int h = 0; h < this.viaticoOne.size(); h++) {
//                            this.selectedViatico = this.viaticoOne.get(h);
//                        }
//                        this.selectedViatico.setEstado("Pendiente");
//                        if (viaticoDao.update(this.selectedViatico)) {
//                            this.selectedViatico = new Viatico();
//                        }
//                    }
//                    if (this.detextractoup.get(i).getItem().getTipoItem().getIdTipo() == 3) {
//                        VariosDao variosDao = new VariosDaoImpl();
//                        this.varioOne = variosDao.findOne(this.detextractoup.get(i).getItem().getCod());
//                        for (int h = 0; h < this.varioOne.size(); h++) {
//                            this.selectedVario = this.varioOne.get(h);
//                        }
//                        this.selectedVario.setEstadoCobro("Pendiente");
//                        if (variosDao.update(this.selectedVario)) {
//                            this.selectedVario = new Varios();
//                        }
//                    }
//                    if (this.detextractoup.get(i).getItem().getTipoItem().getIdTipo() == 4) {
//                        CreditoDao creditoDao = new CreditoDaoImpl();
//                        this.creditoOne = creditoDao.findOne(this.detextractoup.get(i).getItem().getCod());
//                        for (int h = 0; h < this.creditoOne.size(); h++) {
//                            this.selectedCredito = this.creditoOne.get(h);
//                        }
//                        this.selectedCredito.setEstadoCobro("Pendiente");
//                        if (creditoDao.update(this.selectedCredito)) {
//                            this.selectedCredito = new Credito();
//                        }
//                    }
//                    if (this.detextractoup.get(i).getItem().getTipoItem().getIdTipo() == 5) {
//                        AdelantoChoferDao adechoDao = new AdelantoChoferDaoImpl();
//                        this.adechoOne = adechoDao.findOne(this.detextractoup.get(i).getItem().getCod());
//                        for (int h = 0; h < this.adechoOne.size(); h++) {
//                            this.selectedAdelanto = this.adechoOne.get(h);
//                        }
//                        this.selectedAdelanto.setEstadoCobro("Pendiente");
//                        if (adechoDao.update(this.selectedAdelanto)) {
//                            this.selectedAdelanto = new AdelantoChofer();
//                        }
//                    }
//                    if (this.detextractoup.get(i).getItem().getTipoItem().getIdTipo() == 8) {
//                        ReciboDao reciDao = new ReciboDaoImpl();
//                        this.reciboOne = reciDao.findOne(this.detextractoup.get(i).getItem().getCod());
//                        for (int h = 0; h < this.reciboOne.size(); h++) {
//                            this.selectedRecibo = this.reciboOne.get(h);
//                        }
//                        this.selectedRecibo.setEstado("Pendiente");
//                        if (reciDao.update(this.selectedRecibo)) {
//                            this.selectedRecibo = new Recibo();
//                        }
                        }
                    }
                    exordenlist = exordenDao.findByExtracto(this.selectedExtracto.getIdLiquidacion());
                    for (int h = 0; h < exordenlist.size(); h++) {
                        exorden = exordenlist.get(h);
                        seleorden = exordenlist.get(h).getOrdenDeCarga();
                        seleorden.setEstadoliq("Pendiente");
                        ordenDao.update(seleorden);
                        seleorden = new OrdenDeCarga();
                        exorden = new ExOrden();
                    }
                    exordenlist = new ArrayList<ExOrden>();
                }
            } else {
                msg = "Error al actualizar registro.";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, " ");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            msg = "Esta Liquidación ya se encuentra Anulada.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, " ");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnIniciar(ActionEvent actionEvent) {
        this.idpdf = this.selectedExtracto.getIdLiquidacion();
        this.extractos = new ArrayList<Extracto>();
        this.selectedExtracto = new Extracto();
        this.sumitemsex =new ArrayList<sumitem>();
        this.items = new ArrayList<Item>();
        this.selectedItem = new Item();
        this.selectedCamion = new Camion();
        this.detextracto = new ArrayList<DetExtracto>();
        this.detextractoaux = new ArrayList<DetExtracto>();
        this.selectedDetextracto = new DetExtracto();
        this.selectedDetExtractoAux = new DetExtracto();
        this.selectedItems = new ArrayList<Item>();
        this.selectedItemsAux = new ArrayList<Item>();
        this.selectedExtracto.setFecha(new Date());
        this.selectedPropietario = new Propietario();
        this.combs = new ArrayList<Combustible>();
        this.viaticos = new ArrayList<Viatico>();
        this.selectedCamiones = new ArrayList<Camion>();
        this.camiones = new ArrayList<Camion>();
        this.varios = new ArrayList<Varios>();
        this.creditos = new ArrayList<Credito>();
        this.adelantos = new ArrayList<AdelantoChofer>();
        this.excamion = new ArrayList<ExCamion>();
        this.camionvista = new ArrayList<ExCamion>();
        this.selectedEx = new ExCamion();
        this.selectedViaje = new Viaje();
        this.selectedComb = new Combustible();
        this.itempen = new ArrayList<Item>();
        this.selectedViatico = new Viatico();
        this.estados = new ArrayList<Extracto>();
        this.selectedEstado = new Extracto();
        this.propietarios = new ArrayList<Propietario>();
        this.ordenes = new ArrayList<OrdenDeCarga>();
        this.selectedItemp = new Item();
        this.valescomb = new ArrayList<ValeCombustible>();
        this.combflete = new ArrayList<Combustible>();
        this.viaex = new ArrayList<Viatico>();
        this.viaflete = new ArrayList<Viatico>();
        this.selectedCredito = new Credito();
        this.selectedCredito2 = new Credito();
        this.combex = new ArrayList<Combustible>();
        this.recibos = new ArrayList<Recibo>();
        this.selectedRecibo = new Recibo();
        this.selectedItems1 = new ArrayList<Item>();
        this.total = 0.0;
        this.totalActivo = 0.0;
        this.totalPasivo = 0.0;
        this.totalcobrar = 0.0;
        this.codextracto = 0;
        this.tipocomb = new ArrayList<TipoItem>();
        this.tipoviatico = new ArrayList<TipoItem>();
        this.tipovario = new ArrayList<TipoItem>();
        this.tipocredito = new ArrayList<TipoItem>();
        this.tipoadelanto = new ArrayList<TipoItem>();
        this.tipofaltante = new ArrayList<TipoItem>();
        this.tiposeguro = new ArrayList<TipoItem>();
        this.tipoRecibo = new ArrayList<TipoItem>();
        this.items = new ArrayList<Item>();
        this.selectedItem = new Item();
        this.selectedItemView = new Item();
        this.selectedCamion = new Camion();
        this.selectedItems = new ArrayList<Item>();
        this.detextracto = new ArrayList<DetExtracto>();
        this.detextractoaux = new ArrayList<DetExtracto>();
        this.selectedCamiones = new ArrayList<Camion>();
        this.ordenesSumi = new ArrayList<OrdenDeCarga>();
        this.ordenesSumi2 = new ArrayList<OrdenDeCarga>();
        System.out.print("FUNCIONA PERRIS");
    }

    public void btnDeletePago(ActionEvent actionEvent) {
        ExtractoDao exDao = new ExtractoDaoImpl();
        String msg;
        FacesMessage message;
        DetExDao detexDao = new DetExDaoImpl();
        this.detextractoaux = detexDao.findByExtracto(this.selectedExtracto);
        if (exDao.delete(this.selectedExtracto)) {
            msg = "Se Elimino correctamente el registro";
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, " ");
            //for(int i = 0; i < detpagovista.size(); i++){       
            //this.SelectedVario=detpagovista.get(i).getVarios();
            //this.SelectedVario.setEstado("Pendiente");
            //VariosDao variosDao = new VariosDaoImpl();
            //if (variosDao.update(this.SelectedVario)) {                    
            //}                 
            //}
        } else {
            msg = "Error al Eliminar el registro";
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, " ");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void calcularTotal() {
        this.selectedDetextracto.setActivo(this.selectedItem.getActivo());
        this.selectedDetextracto.setPasivo(this.selectedItem.getPasivo());
        //this.selectedExtracto.setMonto(this.sumarTotal(detextracto));
        //System.out.println(this.selectedDetventa.getCantidad());
    }

    public void cargarPendiente(ActionEvent event) {
        this.itempen = new ArrayList<Item>();
    }

    private Double sumarTotal(List<DetExtracto> detextracto) {
        Double suma = 0.0;
        for (int i = 0; i < detextracto.size(); i++) {
            suma = suma + detextracto.get(i).getActivo();
        }
        return suma;
    }

    private Double sumarActivo(List<sumitem> sumitem) {
        Double suma = 0.0;
        for (int i = 0; i < sumitem.size(); i++) {
            if (sumitem.get(i).getTipo().equals("Flete")) {
                suma = suma + sumitem.get(i).getMonto();
            }
        }
        return suma;
    }

    private Double sumarSuministro(List<Suministro> suministro) {
        Double suma = 0.0;
        for (int i = 0; i < suministro.size(); i++) {
            suma = suma + suministro.get(i).getMonto();
        }
        this.asumi = suma;
        return suma;
    }

    private Double sumarPasivo(List<DetExtracto> detextracto) {
        Double sumapasivo = 0.0;
        Double sumaactivo = 0.0;
        for (int i = 0; i < detextracto.size(); i++) {
            sumapasivo = sumapasivo + detextracto.get(i).getPasivo();
            sumaactivo = sumaactivo + detextracto.get(i).getActivo();
        }
        this.totalActivo = sumaactivo;
        this.totalPasivo = sumapasivo;
        return sumapasivo;
    }

    private Double sumarFlete(List<sumitem> sumitem) {
        Double suma = 0.0;
        this.contafle = 0;
        for (int i = 0; i < sumitem.size(); i++) {
            if (sumitem.get(i).getTipo().equals("Flete")) {
                suma = suma + sumitem.get(i).getMonto();
                this.contafle = this.contafle + 1;
            }
        }
        return suma;
    }

    private Double sumarFletevista(List<DetExtracto> detextracto) {
        Double suma = 0.0;
        this.contafle = 0;
        Double acufleaux = 0.0, acucombaux = 0.0, acuadeaux = 0.0, acucreaux = 0.0, acufalaux = 0.0, acureciaux = 0.0, acuseaux = 0.0, acuvariosaux = 0.0, acuviaux = 0.0;
        for (DetExtracto detextracto1 : detextracto) {
            if (detextracto1.getItem().getTipoItem().getDescrip().equals("Flete")) {
                suma = suma + detextracto1.getActivo();
                acufleaux = acufleaux + detextracto1.getActivo();
                this.contafle = this.contafle + 1;
            } else {
                if (detextracto1.getItem().getTipoItem().getDescrip().equals("Viatico")) {
                    acuviaux = acuviaux + detextracto1.getPasivo();
                } else {
                    if (detextracto1.getItem().getTipoItem().getDescrip().equals("Combustible")) {
                        acucombaux = acucombaux + detextracto1.getPasivo();
                    } else {
                        if (detextracto1.getItem().getTipoItem().getDescrip().equals("Credito")) {
                            acucreaux = acucreaux + detextracto1.getPasivo();
                        } else {
                            if (detextracto1.getItem().getTipoItem().getDescrip().equals("Varios")) {
                                acuvariosaux = acuvariosaux + detextracto1.getPasivo();
                            } else {
                                if (detextracto1.getItem().getTipoItem().getDescrip().equals("Faltante")) {
                                    acufalaux = acufalaux + detextracto1.getPasivo();
                                } else {
                                    if (detextracto1.getItem().getTipoItem().getDescrip().equals("Seguro")) {
                                        acuseaux = acuseaux + detextracto1.getPasivo();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        this.acufle = acufleaux;
        this.acucomb = acucombaux;
        this.acuade = acuadeaux;
        this.acucre = acucreaux;
        this.acufal = acufalaux;
        this.acureci = acureciaux;
        this.acuse = acuseaux;
        this.acuvarios = acuvariosaux;
        this.acuvia = acuviaux;
        return suma;
    }

    private Double sumarComb(List<sumitem> sumitem) {
        Double suma = 0.0;
        for (int i = 0; i < sumitem.size(); i++) {
            if (sumitem.get(i).getTipo().equals("Combustible")) {
                suma = suma + sumitem.get(i).getMonto();
            }
        }
        return suma;
    }

    private Double sumarVia(List<sumitem> sumitem) {
        Double suma = 0.0;
        for (int i = 0; i < sumitem.size(); i++) {
            if (sumitem.get(i).getTipo().equals("Viatico")) {
                suma = suma + sumitem.get(i).getMonto();
            }
        }
        return suma;
    }

    private Double sumarVarios(List<sumitem> sumitem) {
        Double suma = 0.0;
        for (int i = 0; i < sumitem.size(); i++) {
            if (sumitem.get(i).getTipo().equals("Varios")) {
                suma = suma + sumitem.get(i).getMonto();
            }
        }
        return suma;
    }

    private Double sumarCredito(List<sumitem> sumitem) {
        Double suma = 0.0;
        for (int i = 0; i < sumitem.size(); i++) {
            if (sumitem.get(i).getTipo().equals("Credito") || sumitem.get(i).getTipo().equals("SumiCli")) {
                suma = suma + sumitem.get(i).getMonto();
            }
        }
        return suma;
    }

    private Double sumarAdelanto(List<DetExtracto> detextracto) {
        Double suma = 0.0;
        for (int i = 0; i < detextracto.size(); i++) {
            if (detextracto.get(i).getItem().getTipoItem().getIdTipo() == 5) {
                suma = suma + detextracto.get(i).getPasivo();
            }
        }
        return suma;
    }

    private Double sumarFaltante(List<sumitem> sumitem) {
        Double suma = 0.0;
        for (int i = 0; i < sumitem.size(); i++) {
            if (sumitem.get(i).getTipo().equals("Faltante")) {
                suma = suma + sumitem.get(i).getMonto();
            }
        }
        return suma;
    }

    private Double sumarSeguro(List<sumitem> sumitem) {
        Double suma = 0.0;
        for (int i = 0; i < sumitem.size(); i++) {
            if (sumitem.get(i).getTipo().equals("Seguro")) {
                suma = suma + sumitem.get(i).getMonto();
            }
        }
        return suma;
    }

    private Double sumarRecibo(List<DetExtracto> detextracto) {
        Double suma = 0.0;
        for (int i = 0; i < detextracto.size(); i++) {
            if (detextracto.get(i).getItem().getTipoItem().getIdTipo() == 8) {
                suma = suma + detextracto.get(i).getActivo();
            }
        }
        return suma;
    }

    private Double sumarTotalPago(List<Extracto> extractos) {
        Double suma = 0.0;
        for (int i = 0; i < extractos.size(); i++) {
            suma = suma + extractos.get(i).getMonto();
        }
        return suma;
    }

    public void cargarDescrip(ActionEvent event) {
        this.Descrip = this.selectedExtracto.getDescripcion();
    }

}
