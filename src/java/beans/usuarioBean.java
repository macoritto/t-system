/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.RolDao;
import dao.RolDaoImpl;
import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Rol;
import model.Usuario;
import org.primefaces.event.SelectEvent;
//import static org.hibernate.criterion.Projections.id;

/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class usuarioBean {

    private List<Usuario> usuarios;
    private Usuario selectedUsuario;
    private String contra;
    private String contra1;
    private List<Rol> roles;
    private Rol selectedRol;

    public usuarioBean() {
        this.usuarios = new ArrayList<Usuario>();
        this.selectedUsuario = new Usuario();
        this.selectedRol= new Rol();
        this.roles = new ArrayList<Rol>();
    }

    public List<Rol> getRoles() {
        RolDao rol = new RolDaoImpl();
        this.roles=rol.SelectItems();
        return roles;        
    }

    public Rol getSelectedRol() {
        return selectedRol;
    }

    public List<Usuario> getUsuarios() {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        this.usuarios = usuarioDao.findAll();
        return usuarios;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public void setSelectedRol(Rol selectedRol) {
        this.selectedRol = selectedRol;
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public String getContra() {
        return contra;
    }

    public String getContra1() {
        return contra1;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public void setContra1(String contra1) {
        this.contra1 = contra1;
    }
    

    public void btnCreateUsuario(ActionEvent actionEvent) {
        System.out.print("El rol que intenta guardar");
        System.out.print(this.selectedUsuario.getRol().getNombre());
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        String msg;
        this.selectedUsuario.setUsuariocreacion("admin");
        Date hoy = new Date();
        String fecha = new SimpleDateFormat("YYYY-MM-dd").format(hoy);
        this.selectedUsuario.setFechacreacion(java.sql.Date.valueOf(fecha));
        this.selectedUsuario.setFechamodificacion(java.sql.Date.valueOf(fecha));
        this.selectedUsuario.setUsuariomodificacion("admin");
        if (usuarioDao.create(this.selectedUsuario)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnUpdateUsuario(ActionEvent actionEvent) {
        
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        String msg;
        this.selectedUsuario.setClave(this.selectedUsuario.getUsuario());
        this.selectedUsuario.setUsuariocreacion("admin");
        Date hoy = new Date();
        String fecha = new SimpleDateFormat("YYYY-MM-dd").format(hoy);
        this.selectedUsuario.setFechacreacion(java.sql.Date.valueOf(fecha));
        this.selectedUsuario.setFechamodificacion(java.sql.Date.valueOf(fecha));
        this.selectedUsuario.setUsuariomodificacion("admin");
        if (usuarioDao.update(this.selectedUsuario)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
            public void btnUpdateKey(ActionEvent actionEvent) {
        String msg;
        if(contra.equals(contra1)){
            UsuarioDao usuarioDao = new UsuarioDaoImpl();
            this.selectedUsuario.setClave(this.contra1);
            if (usuarioDao.updatec(this.selectedUsuario)) {                
                msg = "Se actualizo correctamente el registro";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                msg = "Error al actualizar registro.";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }else{
            msg = "Error las contraseñas no coinciden.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);        
    }
    }

    public void btnDeleteUsuario(ActionEvent actionEvent) {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        String msg;
        if (usuarioDao.delete(this.selectedUsuario.getId())) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al actualizar registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public void onRowSelectTipo(SelectEvent event){
        this.selectedUsuario.setRol(this.selectedRol);
        System.out.print("Este es el rol");
        System.out.print(this.selectedUsuario.getRol().getNombre());
    }
}
