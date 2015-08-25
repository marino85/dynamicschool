/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.constants.Constants;
import com.ccit.ejb.dto.UsuarioDTo;
import com.ccit.ejb.modelo.IappCursos;
import com.ccit.ejb.modelo.IappModulos;
import com.ccit.web.util.WebUtil;
import java.io.Serializable;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author marino
 */
@ManagedBean
@SessionScoped
public class ModulosBean implements Serializable {

    private IappCursos editCourse;
    private IappModulos module;
    private String messaje;
    private boolean showDialog; 

    /**
     * Creates a new instance of UsuariosBean
     */
    public ModulosBean() {
        
    }
    
    @PostConstruct
    public void init() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "init ModulosBean");
    }
    
    public void eliminar(ActionEvent evt){
        editCourse.getIappModulosCollection().remove(module);
    }
    
    public void guardar(ActionEvent evt){
        if(!editCourse.getIappModulosCollection().contains(module)){
            UsuarioDTo u = (UsuarioDTo)WebUtil.getObjectFromSession("userLogged");
            if(module.getFechaCreacion()==null){
                module.setUsuarioCreacion(u.getLogin());
                module.setFechaCreacion(Calendar.getInstance().getTime());
                module.setEstadoRegistro(Constants.REGISTRO_ACTIVO);
            }
            module.setUsuarioModificacion(u.getLogin());
            module.setFechaModificacion(Calendar.getInstance().getTime());
            editCourse.getIappModulosCollection().add(module);
        }
        setMessaje("La información ha sido guardada, recuerde guardar los cambios del curso para que la informacion sea guardada de forma permanente");
        setShowDialog(true);
    }

    public void aceptar(ActionEvent evt) {
        this.setMessaje("");
        setShowDialog(false);
    }
    
    public String createModule(){
        module= new IappModulos();
        module.setIdCurso(editCourse);
        return "editarModulo";
    }
    
    public IappCursos getEditCourse() {
        return editCourse;
    }

    public void setEditCourse(IappCursos editCourse) {
        this.editCourse = editCourse;
    }

    public IappModulos getModule() {
        return module;
    }

    public void setModule(IappModulos module) {
        this.module = module;
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
