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
import dao.ItemDao;
import dao.ItemDaoImpl;
import dao.PropietarioDao;
import dao.PropietarioDaoImpl;
import dao.ReciboDao;
import dao.ReciboDaoImpl;
import dao.TipoItemDao;
import dao.TipoItemDaoImpl;
import dao.VariosDao;
import dao.VariosDaoImpl;
import dao.ViajeDao;
import dao.ViajeDaoImpl;
import dao.ViaticoDao;
import dao.ViaticoDaoImpl;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.AdelantoChofer;
import model.Camion;
import model.Combustible;
import model.Credito;
import model.DetExtracto;
import model.ExCamion;
import model.Extracto;
import model.Item;
import model.Propietario;
import model.Recibo;
import model.TipoItem;
import model.Usuario;
import model.Varios;
import model.Viaje;
import model.Viatico;

/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class estadoBean {
    private List<Extracto> extractos;    
    private List<TipoItem> tipoviatico;
    private List<TipoItem> tipoviaje;
    private List<TipoItem> tipocomb;
    private List<TipoItem> tipovario;
    private List<TipoItem> tipocredito;
    private List<TipoItem> tipoadelanto;
    private List<TipoItem> tiposeguro;
    private List<TipoItem> tipoRecibo;
    private List<TipoItem> tipofaltante;
    private List<Credito> creditos;
    private List<Varios> varioOne;
    private List<Credito> creditoOne;
    private List<AdelantoChofer> adechoOne;
    private AdelantoChofer selectedAdelanto;
    private Credito selectedCredito;
    private Varios selectedVario;
    private List<Viaje> viajeOne;
    private List<Viatico> viaticoOne;
    private Viatico selectedViatico;
    private List<Combustible> combOne;
    private Combustible selectedComb;
    private Viaje selectedViaje;
    private List<AdelantoChofer> adelantos;
    private List<ExCamion> excamion;
    private List<ExCamion> camionvista;
    private ExCamion selectedEx;
    private List<Varios> varios;
    private List<Viaje> viajes;
    private List<Viatico> viaticos;
    private List<Combustible> combs; 
    private Extracto selectedExtracto;
    private List<Camion> camiones;
    private List<Item> items;
    private Item selectedItem;
    private Propietario selectedPropietario;
    private Camion selectedCamion;
    private List<Camion> selectedCamiones;
    private List<DetExtracto> detextracto;
    private List<DetExtracto> detextractoaux;
    private DetExtracto selectedDetextracto;
    private DetExtracto selectedDetExtractoAux;    
    private Double total;
    private Usuario usuario;
    private Double totalActivo;
    private Double totalPasivo;
    private Double totalcobrar;
    private Integer codextractov;
    private Connection conexion;
    private List<Item> selectedItems;
    private List<Item> itempen;
    private List<Item> selectedItemsAux;
    private Integer codextracto;
    private List<Extracto> estados;
    private Extracto selectedEstado;
    private List<Propietario> propietarios;
    private List<Extracto> negativos;
    private List<Extracto> positivos;
    private List<Recibo> recibos;
    private Double totaln;
    private Double totalp;
    public estadoBean() {
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
        this.selectedComb= new Combustible();
        this.itempen = new ArrayList<Item>();
        this.selectedViatico = new Viatico();
        this.estados = new ArrayList<Extracto>();
        this.selectedEstado = new Extracto();
        this.propietarios = new ArrayList<Propietario>();
        this.negativos = new ArrayList<Extracto>();
        this.positivos = new ArrayList<Extracto>();
        this.recibos = new ArrayList<Recibo>();
    }
    public List<Extracto> getEstados() {        
        String msg;        
        Integer id =1;                
        this.selectedItemsAux = new ArrayList<Item>();
        //for(int i = 0; i<this.items.size(); i++){           
            //selectedItemsAux.add(this.items.get(i));
        //}        
        TipoItemDao tipoDao= new TipoItemDaoImpl();
        this.tipoviaje = tipoDao.findOne();
        this.tipocomb = tipoDao.findComb();
        this.tipoviatico = tipoDao.findVia();
        this.tipovario = tipoDao.findVarios();
        this.tipocredito = tipoDao.findCredito();
        this.tipoadelanto = tipoDao.findAdelanto();
        this.tipofaltante = tipoDao.findFaltante();
        this.tiposeguro = tipoDao.findSeguro();
        this.tipoRecibo = tipoDao.findRecibo();
        this.estados = new ArrayList<Extracto>();
        this.negativos = new ArrayList<Extracto>();
        this.positivos = new ArrayList<Extracto>();
        String c;
        ItemDao itemDao = new ItemDaoImpl();
        Integer maxCoditem = itemDao.maxItem() + 1;
        PropietarioDao proDao = new PropietarioDaoImpl();
        this.propietarios = proDao.findAll();  
        System.out.println("T pro");
        System.out.println(this.propietarios.size());
        for(int k =0;k<this.propietarios.size();k++){
                CamionDao camionDao = new CamionDaoImpl();
                this.selectedCamiones = camionDao.findOne(this.propietarios.get(k));
                for(int h =0;h<this.selectedCamiones.size(); h++){                                
                    maxCoditem= maxCoditem +1;
                    ViajeDao viajesDao= new ViajeDaoImpl();
                    this.viajes = viajesDao.findPen(this.selectedCamiones.get(h));
                    String Origen;
                    String Destino;
                    String dcomb;
                    Double litros1;
                    String litros2;       
                    String tipocomb;
                    String pro;
                    Integer band=0;
                    Integer ban=0;
                for(int i = 0; i<this.viajes.size(); i++){
                    Integer nro;
                    nro=this.viajes.get(i).getIdViaje();
                    maxCoditem= maxCoditem +1;
                    this.selectedItem = new Item();
                    Origen=this.viajes.get(i).getOrdenDeCarga().getUnidadOrigen().getNombre().toString();
                    Destino=this.viajes.get(i).getOrdenDeCarga().getUnidadDestino().getNombre().toString();
                    c="Flete con Origen:".concat(Origen).concat(" con Destino:").concat(Destino).concat(" N° de viaje".concat(nro.toString()));   
                    this.selectedItem.setIdItem(maxCoditem);
                    this.selectedItem.setCamion(this.viajes.get(i).getOrdenDeCarga().getCamion());
                    this.selectedItem.setCod(this.viajes.get(i).getIdViaje());
                    this.selectedItem.setDescrip(c);
                    this.selectedItem.setActivo(this.viajes.get(i).getMontoPagar()+this.viajes.get(i).getMontofaltante()+this.viajes.get(i).getMontoseguro());
                    this.selectedItem.setPasivo(0.0);            
                    for(int e =0; e<this.tipoviaje.size(); e++){
                        this.selectedItem.setTipoItem(this.tipoviaje.get(e));
                    }
                    selectedItemsAux.add(this.selectedItem);
                    this.selectedItem = new Item();
                        if(this.viajes.get(i).getMontofaltante()>0){                    
                            band=1;      
                            maxCoditem= maxCoditem +1;
                            this.selectedItem.setIdItem(maxCoditem);
                            this.selectedItem.setCamion(this.viajes.get(i).getOrdenDeCarga().getCamion());
                            this.selectedItem.setCod(this.viajes.get(i).getIdViaje());
                            this.selectedItem.setDescrip("Faltante del viaje N°".concat(nro.toString()));
                            this.selectedItem.setActivo(0.0);
                            this.selectedItem.setPasivo(this.viajes.get(i).getMontofaltante());
                            for(int e =0; e<this.tipofaltante.size(); e++){
                                this.selectedItem.setTipoItem(this.tipofaltante.get(e));
                            }
                            selectedItemsAux.add(this.selectedItem);
                            this.selectedItem = new Item();
                        }
                        if(this.viajes.get(i).getSeguro().getMonto()>0){                    
                            ban=1;                    
                            maxCoditem= maxCoditem +1;
                            this.selectedItem.setIdItem(maxCoditem);
                            this.selectedItem.setCamion(this.viajes.get(i).getOrdenDeCarga().getCamion());
                            this.selectedItem.setCod(this.viajes.get(i).getIdViaje());
                            this.selectedItem.setDescrip("Seguro del viaje N°".concat(nro.toString()));
                            this.selectedItem.setActivo(0.0);
                            this.selectedItem.setPasivo(this.viajes.get(i).getSeguro().getMonto());
                            for(int e =0; e<this.tiposeguro.size(); e++){
                                this.selectedItem.setTipoItem(this.tiposeguro.get(e));
                            }
                            selectedItemsAux.add(this.selectedItem);
                            this.selectedItem = new Item();
                        }            
                }
                CombDao comDao= new CombDaoImpl();
                this.combs = comDao.findCamion(this.selectedCamiones.get(h));
                for(int i =0;i<this.combs.size();i++){
                    maxCoditem= maxCoditem +1;
                    this.selectedItem= new Item();
                    pro=this.combs.get(i).getValeCombustible().getProveedor().getNombre().toString();
                    litros1= this.combs.get(i).getLitros();
                    litros2=litros1.toString();
                    tipocomb=this.combs.get(i).getValeCombustible().getTipoCombustible().getNombre().toString();
                    dcomb=litros2.concat(" de ").concat(tipocomb).concat(" cargados en ").concat(pro);
                    this.selectedItem.setIdItem(maxCoditem);
                    this.selectedItem.setDescrip(dcomb);
                    this.selectedItem.setCamion(this.combs.get(i).getValeCombustible().getCamion());
                    this.selectedItem.setPasivo(this.combs.get(i).getMontoComb()+this.combs.get(i).getExtras());
                    this.selectedItem.setCod(this.combs.get(i).getIdCombustible());
                    this.selectedItem.setActivo(0.0);
                    for(int e =0; e<this.tipocomb.size(); e++){
                        this.selectedItem.setTipoItem(this.tipocomb.get(e));
                    }
                    selectedItemsAux.add(this.selectedItem);
                }
                ViaticoDao viaDao = new ViaticoDaoImpl();
                this.viaticos = viaDao.findCamion(this.selectedCamiones.get(h));
                for(int i =0;i<this.viaticos.size();i++){
                    this.selectedItem= new Item();        
                    maxCoditem= maxCoditem +1;
                    c="Efectivo Retirado de la Empresa";
                    this.selectedItem.setDescrip(c);
                    this.selectedItem.setIdItem(maxCoditem);
                    this.selectedItem.setCamion(this.viaticos.get(i).getCamion());
                    this.selectedItem.setPasivo(this.viaticos.get(i).getMonto());
                    this.selectedItem.setCod(this.viaticos.get(i).getIdViatico());
                    this.selectedItem.setActivo(0.0);
                    for(int e =0; e<this.tipoviatico.size(); e++){
                        this.selectedItem.setTipoItem(this.tipoviatico.get(e));
                    }
                    selectedItemsAux.add(this.selectedItem);
                }    
                VariosDao varioDao = new VariosDaoImpl();
                this.varios = varioDao.findCamion(this.selectedCamiones.get(h));
                for(int i =0;i<this.varios.size();i++){
                    this.selectedItem= new Item();        
                    maxCoditem= maxCoditem +1;
                    c=this.varios.get(i).getDescripcion().toString().concat(" Suministrado por").concat(this.varios.get(i).getValeVarios().getProveedorVarios().getNombre().toString());
                    this.selectedItem.setDescrip(c);
                    this.selectedItem.setIdItem(maxCoditem);
                    this.selectedItem.setCamion(this.varios.get(i).getValeVarios().getCamion());
                    this.selectedItem.setPasivo(this.varios.get(i).getMonto());
                    this.selectedItem.setActivo(0.0);
                    this.selectedItem.setCod(this.varios.get(i).getIdVarios());
                    for(int e =0; e<this.tipovario.size(); e++){
                        this.selectedItem.setTipoItem(this.tipovario.get(e));
                    }
                    selectedItemsAux.add(this.selectedItem);
                }  
                CreditoDao creditoDao = new CreditoDaoImpl();
                this.creditos = creditoDao.findCamion(this.selectedCamiones.get(h));
                for(int i =0;i<this.creditos.size();i++){
                    this.selectedItem= new Item();
                    maxCoditem= maxCoditem +1;
                    c="Credito aprobado al Propietario";
                    this.selectedItem.setDescrip(c);
                    this.selectedItem.setIdItem(maxCoditem);
                    this.selectedItem.setCamion(this.creditos.get(i).getCamion());
                    this.selectedItem.setPasivo(this.creditos.get(i).getMonto());
                    this.selectedItem.setActivo(0.0);
                    this.selectedItem.setCod(this.creditos.get(i).getIdCredito());
                    for(int e =0; e<this.tipocredito.size(); e++){
                        this.selectedItem.setTipoItem(this.tipocredito.get(e));
                    }
                    selectedItemsAux.add(this.selectedItem);
                }  
                AdelantoChoferDao adeDao = new AdelantoChoferDaoImpl();
                this.adelantos = adeDao.findCamion(this.selectedCamiones.get(h));
                for(int i =0;i<this.adelantos.size();i++){
                    this.selectedItem= new Item();        
                    maxCoditem= maxCoditem +1;
                    c="Adelanto Entregado al Chofer".concat(this.adelantos.get(i).getChofer().getNombre().toString()).concat(this.adelantos.get(i).getChofer().getApellido());
                    this.selectedItem.setDescrip(c);
                    this.selectedItem.setCamion(this.adelantos.get(i).getCamion());
                    this.selectedItem.setIdItem(maxCoditem);
                    this.selectedItem.setPasivo(this.adelantos.get(i).getMonto());
                    this.selectedItem.setActivo(0.0);
                    this.selectedItem.setCod(this.adelantos.get(i).getIdAdelantoChofer());
                    for(int e =0; e<this.tipoadelanto.size(); e++){
                        this.selectedItem.setTipoItem(this.tipoadelanto.get(e));
                    }
                    selectedItemsAux.add(this.selectedItem);
                }
                ReciboDao reciboDao = new ReciboDaoImpl();
                this.recibos = reciboDao.findcamion(this.selectedCamiones.get(h));
                    for(int i =0;i<this.recibos.size();i++){
                    this.selectedItem= new Item();        
                    maxCoditem= maxCoditem +1;
                    c="Efectivo Recibido por ".concat(this.recibos.get(i).getCamion().getPropietario().getNombre().toString()).concat(this.recibos.get(i).getCamion().getPropietario().getApellido());
                    this.selectedItem.setDescrip(c);
                    this.selectedItem.setCamion(this.recibos.get(i).getCamion());
                    this.selectedItem.setIdItem(maxCoditem);
                    this.selectedItem.setPasivo(0.0);
                    this.selectedItem.setActivo(this.recibos.get(i).getMonto());
                    this.selectedItem.setCod(this.recibos.get(i).getIdRecibo());
                        for(int e =0; e<this.tipoRecibo.size(); e++){
                            this.selectedItem.setTipoItem(this.tipoRecibo.get(e));
                        }
                    selectedItemsAux.add(this.selectedItem);
                    this.selectedItem = new Item();
            } 
                }
                for(int e = 0; e < this.selectedItemsAux.size(); e++){
                    System.out.println(this.selectedItems.size());
                    this.selectedDetextracto.setItem(this.selectedItemsAux.get(e));
                    this.selectedDetextracto.setIdDet(this.selectedItemsAux.get(e).getIdItem());
                    this.selectedDetextracto.setActivo(this.selectedItemsAux.get(e).getActivo());
                    this.selectedDetextracto.setPasivo(this.selectedItemsAux.get(e).getPasivo());
                    this.selectedDetextracto.setDescrip(this.selectedItemsAux.get(e).getDescrip());                 
                    detextracto.add(this.selectedDetextracto);                    
                    this.selectedDetextracto = new DetExtracto();                                
                }
                this.totalActivo = this.sumarActivo(detextracto);
                this.totalPasivo = this.sumarPasivo(detextracto);
                Integer pa = this.totalPasivo.intValue();
                Integer ac = this.totalActivo.intValue();
                System.out.println(this.propietarios.get(k).getNombre());
                System.out.print(pa);
                System.out.print(ac);
                this.totalcobrar = (this.totalActivo-this.totalPasivo);                
                System.out.print(pa-ac);
                this.totalActivo =0.0;
                this.totalPasivo =0.0;
                this.selectedEstado.setIdLiquidacion(id);
                this.selectedEstado.setEstado("Provisorio");
                this.selectedEstado.setFecha(new Date());
                if(this.totalcobrar==0){
                    this.selectedEstado.setMonto(0.0);
                }else{
                    this.selectedEstado.setMonto(this.totalcobrar);
                }                
                this.selectedEstado.setPropietario(this.propietarios.get(k));
                if(this.selectedEstado.getMonto()<0){
                    this.negativos.add(this.selectedEstado);
                }else{
                    if(this.selectedEstado.getMonto()>0){
                        this.positivos.add(this.selectedEstado);
                    }
                }
                this.estados.add(this.selectedEstado);
                this.detextracto = new ArrayList<DetExtracto>();  
                this.selectedItemsAux = new ArrayList<Item>();
                this.selectedEstado= new Extracto();
                this.totalcobrar =0.0;
                id= id+1;
          }
        this.total = this.sumarTotalPago(estados);
        System.out.println("Tamaño");
        System.out.println(this.estados.size());
        
        return estados;        
    }

    public List<Extracto> getPositivos() {
        return positivos;
    }

    public Double getTotalp() {
        this.totalp = this.sumarTotalPositivo(positivos);
        return totalp;
    }
 
    public Extracto getSelectedEstado() {
        return selectedEstado;
    }

    public List<Extracto> getNegativos() {
        return negativos;
    }
    
    public Double getTotal() {
        //this.total=this.sumarTotalPago(estados);
        return total;
    }

    public Double getTotaln() {
        this.totaln = this.sumarTotalNegativo(negativos);
        return totaln;
    }
    

    public void setTotal(Double total) {
        this.total = total;
    }
    
    private Double sumarTotal(List<DetExtracto> detextracto){
    Double suma =0.0;
       for(int i = 0; i < detextracto.size(); i++){
           suma = suma + detextracto.get(i).getActivo();
       }
        return suma;
    }
    private Double sumarActivo(List<DetExtracto> detextracto){
    Double suma =0.0;
       for(int i = 0; i < detextracto.size(); i++){
           System.out.println(detextracto.get(i).getItem().getDescrip());
           System.out.println(detextracto.get(i).getItem().getActivo());
           suma = suma + detextracto.get(i).getActivo();
       }
        System.out.println(suma);
        return suma;
    }
    private Double sumarPasivo(List<DetExtracto> detextracto){
    Double suma =0.0;
       for(int i = 0; i < detextracto.size(); i++){
           System.out.println(detextracto.get(i).getItem().getDescrip());
           System.out.println(detextracto.get(i).getItem().getPasivo());
           suma = suma + detextracto.get(i).getPasivo();
       }
        System.out.println(suma);
        return suma;
    }

    private Double sumarTotalPago(List<Extracto> estados){
    Double suma =0.0;
       for(int i = 0; i < estados.size(); i++){
           suma = suma + estados.get(i).getMonto();
           System.out.println(suma);
           System.out.println(estados.size());
       }
        return suma;
    }
    private Double sumarTotalNegativo(List<Extracto> negativos){
    Double suma =0.0;
       for(int i = 0; i < negativos.size(); i++){
           suma = suma + negativos.get(i).getMonto();
           System.out.println(suma);
           System.out.println(negativos.size());
       }
        return suma;
    }
    private Double sumarTotalPositivo(List<Extracto> positivos){
    Double suma =0.0;
       for(int i = 0; i < positivos.size(); i++){
           suma = suma + positivos.get(i).getMonto();
           System.out.println(suma);
           System.out.println(positivos.size());
       }
        return suma;
    }
}
