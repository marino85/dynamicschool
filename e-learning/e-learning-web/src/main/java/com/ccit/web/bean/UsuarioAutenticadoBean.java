/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.dto.UsuarioDTo;
import com.ccit.ejb.fachada.UsuariosFacade;
import com.ccit.ejb.modelo.IappCourses;
import com.ccit.ejb.modelo.IappUser;
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

    IappUser user;
    IappCourses viewedCourse;
    
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

    public IappUser getUser() {
        return user;
    }

    public void setUser(IappUser user) {
        this.user = user;
    }

    public IappCourses getViewedCourse() {
        return viewedCourse;
    }

    public void setViewedCourse(IappCourses viewedCourse) {
        this.viewedCourse = viewedCourse;
    }
    
    public String viewCourse(){
        return "verCurso";
    }

  
    
    
}
