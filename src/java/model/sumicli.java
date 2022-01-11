/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author carlo
 */
    public class sumicli implements java.io.Serializable {
        private Integer id;
        private Date fecha;
        private Double monto;        
        private String descrip;
        private String tipo;
        private String chapa;
        private String chofer;
        private Double litros;
        private Double precio;
        private Double total; 
        private String origen;
        private String destino;
        private String remision;
        private Integer porigen;
        private Integer pdestino;
        private Double descuento;
        private Double bruto;
        private Double pagar;
        public sumicli(){
        }
        public sumicli(Integer id,Date fecha, Double monto, String descrip, String tipo, String chapa,String chofer, Double litros, Double precio, Double total, String origen, String destino, String remision, Integer porigen, Integer pdestino, Double descuento, Double bruto, Double pagar) {
            this.id=id;
            this.fecha = fecha;
            this.monto = monto;
            this.descrip=descrip;
            this.tipo=tipo;
            this.chapa=chapa;
            this.chofer=chofer;
            this.litros=litros;
            this.precio=precio;
            this.total=total;
            this.origen=origen;
            this.destino=destino;
            this.remision=remision;
            this.porigen=porigen;
            this.pdestino=pdestino;
            this.descuento=descuento;
            this.bruto=bruto;
            this.pagar=pagar;
        }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getChapa() {
        return chapa;
    }

    public void setChapa(String chapa) {
        this.chapa = chapa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChofer() {
        return chofer;
    }

    public void setChofer(String chofer) {
        this.chofer = chofer;
    }

    public Double getLitros() {
        return litros;
    }

    public void setLitros(Double litros) {
        this.litros = litros;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getRemision() {
        return remision;
    }

    public void setRemision(String remision) {
        this.remision = remision;
    }

    public Integer getPorigen() {
        return porigen;
    }

    public void setPorigen(Integer porigen) {
        this.porigen = porigen;
    }

    public Integer getPdestino() {
        return pdestino;
    }

    public void setPdestino(Integer pdestino) {
        this.pdestino = pdestino;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getBruto() {
        return bruto;
    }

    public void setBruto(Double bruto) {
        this.bruto = bruto;
    }

    public Double getPagar() {
        return pagar;
    }

    public void setPagar(Double pagar) {
        this.pagar = pagar;
    }


    }