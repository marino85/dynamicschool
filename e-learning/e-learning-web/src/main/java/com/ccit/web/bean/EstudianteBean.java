/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.dto.UsuarioDTo;
import com.ccit.ejb.fachada.UsuariosFacade;
import com.ccit.ejb.modelo.IappUsuario;
import com.ccit.web.util.WebUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author marino
 */
@ManagedBean
@SessionScoped
public class EstudianteBean implements Serializable {
    @EJB
    private UsuariosFacade usuariosFacade;
    
    
    private IappUsuario estudiante;
    private String messaje;
    private boolean showDialog;

    /**
     * Creates a new instance of EstudianteBean
     */
    public EstudianteBean() {
    }
    
    @PostConstruct
    public void init(){
        
        UsuarioDTo userLogged = (UsuarioDTo)WebUtil.getObjectFromSession("userLogged");
        if(userLogged!=null){            
          this.setEstudiante(usuariosFacade.findUser(userLogged.getNumDoc()));  
            
        }
        
    }
    
    
    public void guardar(ActionEvent evt){
        this.setShowDialog(true);
        try {
            usuariosFacade.merge(estudiante);
            this.setMessaje("La información se ha actualilzado correctamente");
        } catch (Exception e) {
             this.setMessaje("Ocurrio un error al actualizar el usuario");
             e.printStackTrace();
        }
        
        
    }
    
    public void aceptar(ActionEvent evt){
        
        this.setShowDialog(false);
        UsuarioDTo userLogged = (UsuarioDTo)WebUtil.getObjectFromSession("userLogged");
        if(userLogged!=null){            
          this.setEstudiante(usuariosFacade.findUser(userLogged.getNumDoc()));  
            
        }
        
    }

    public IappUsuario getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(IappUsuario estudiante) {
        this.estudiante = estudiante;
    }

    public String getMessaje() {
        return messaje;
    }

    public void setMessaje(String messaje) {
        this.messaje = messaje;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }

   
    
    
    
    
}
