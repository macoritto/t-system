/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import javax.faces.bean.SessionScoped;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.Usuario;
import org.primefaces.context.RequestContext;
import util.MyUtil;

/**
 *
 * @author Usuario
 */
@Named(value="loginBean")
@ManagedBean
@SessionScoped
public class loginBean implements Serializable{
    
    private Usuario usuario;
    private UsuarioDao usuarioDao;
    public loginBean() {
        this.usuarioDao = new UsuarioDaoImpl();
        if (this.usuario == null){
            this.usuario = new Usuario();
    }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void login(ActionEvent actionEvent){
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg;
        boolean loggedIn;
        String ruta = "";
        this.usuario = this.usuarioDao.login(this.usuario);
        if(this.usuario != null){
            loggedIn = true;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuario.getUsuario());
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> sessionMap = externalContext.getSessionMap();
            sessionMap.put("userLog", this.usuario);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenidos", this.usuario.getUsuario());
            System.out.print("hola jeje");
            ruta = MyUtil.basepathlogin()+"/views/cualquier/cualquier.xhtml";
        } else {
            loggedIn = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Usuario o Clave incorrecto");
             if (this.usuario == null){
                    this.usuario = new Usuario();
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("ruta", ruta);    
    }
    public void logout (){    
        System.out.print("hola 2 jeje");
        String ruta = MyUtil.basepathlogin()+"views/login.xhtml";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(false);
        sesion.invalidate();
        context.addCallbackParam("loggetOut", true);
        context.addCallbackParam("ruta", ruta);
    }
}