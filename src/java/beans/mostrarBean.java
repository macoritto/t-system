/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Usuario;

/**
 *
 * @author Usuario
 */
@Named(value = "mostrarBean")
@SessionScoped
public class mostrarBean implements Serializable{

    private Usuario usuario;
    private Boolean mostrarOperador;
    public mostrarBean() {
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLog");
        Boolean mostrar=(usuario.getRol().getId() == 1);
        mostrarOperador=mostrar;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Boolean getMostrarOperador() {
        return mostrarOperador;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setMostrarOperador(Boolean mostrarOperador) {
        this.mostrarOperador = mostrarOperador;
    }
    public void bntMostrarOperador() {
            this.mostrarOperador = !this.mostrarOperador;            
    }
    
}
