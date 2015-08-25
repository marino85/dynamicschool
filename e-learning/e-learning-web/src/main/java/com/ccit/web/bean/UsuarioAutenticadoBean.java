/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.dto.UsuarioDTo;
import com.ccit.ejb.fachada.UsuariosFacade;
import com.ccit.ejb.modelo.IappCursos;
import com.ccit.ejb.modelo.IappUsuario;
import com.ccit.web.util.WebUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author altamiranda
 */
@ManagedBean
@ViewScoped
public class UsuarioAutenticadoBean implements Serializable {
    @EJB
    private UsuariosFacade usuariosFacade;

    IappUsuario user;
    IappCursos viewedCourse;
    
    /**
     * Creates a new instance of UsuariosBean
     */
    public UsuarioAutenticadoBean() {
    }

    @PostConstruct
    public void init() {
        UsuarioDTo u = ( UsuarioDTo )WebUtil.getObjectFromSession("userLogged");
        user=usuariosFacade.findUser(u.getNumDoc());
    }
    
    public UsuarioDTo getUserDto(){
         UsuarioDTo u = ( UsuarioDTo )WebUtil.getObjectFromSession("userLogged");
         return u;
    }

    public IappUsuario getUser() {
        return user;
    }

    public void setUser(IappUsuario user) {
        this.user = user;
    }

    public IappCursos getViewedCourse() {
        return viewedCourse;
    }

    public void setViewedCourse(IappCursos viewedCourse) {
        this.viewedCourse = viewedCourse;
    }
    
    public String viewCourse(){
        return "verCurso";
    }

  
    
    
}
