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
    public class sumitem implements java.io.Serializable {

        private Date fecha;
        private Double monto;        
        private String descrip;
        private String tipo;
        private String chapa;
        private Integer orden;      
        private String color;
        private Double precio;
        private Integer id;
        public sumitem(){
        }
        public sumitem(Date fecha, Double monto, String descrip, String tipo, String chapa, Integer orden, String color, Double precio, Integer id) {
            this.fecha = fecha;
            this.monto = monto;
            this.descrip=descrip;
            this.tipo=tipo;
            this.chapa=chapa;
            this.orden=orden;
            this.color=color;
            this.precio=precio;
            this.id=id;
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

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    }