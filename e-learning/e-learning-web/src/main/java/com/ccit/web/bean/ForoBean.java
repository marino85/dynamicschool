/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.dto.UsuarioDTo;
import com.ccit.ejb.fachada.ForoFacade;
import com.ccit.ejb.modelo.IappForo;
import com.ccit.ejb.modelo.IappModulos;
import com.ccit.exception.IappException;
import com.ccit.web.util.WebUtil;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author marino
 */
@ManagedBean(name = "foroBean")
@SessionScoped
public class ForoBean {

    @EJB
    private ForoFacade foroFacade;
    private IappModulos editModule;
    private IappForo newForo;
    private IappForo editForo;
    private IappForo foroSeleccionado;
    private boolean showNewModal;
    private boolean showEditModal;
    private boolean verConfirmacion;
    private String mensaje;
    private boolean verMensaje;
    

    /**
     * Creates a new instance of ForoBean
     */
    public ForoBean() {
    }

    @PostConstruct
    public void init() {
        newForo = new IappForo();
        editForo = new IappForo();
    }

    public void createForo() {
        try {

            this.setShowNewModal(false);
            newForo.setUsuario(getUserFromSession().getNombre());
            newForo.setUsuarioCreacion("U");
            newForo.setUsuarioModificacion("U");
            newForo.setFechaCreacion(new Date());
            newForo.setFechaModificacion(new Date());
            newForo.setEstadoRegistro("A");
            newForo.setIdModulo(editModule);
            foroFacade.createForo(newForo);
            this.setMensaje("El foro se ha creado correctamente");
            this.setVerMensaje(true);
            editModule.getIappForoCollection().add(newForo);
        } catch (IappException ex) {
            Logger.getLogger(ForoBean.class.getName()).log(Level.SEVERE, null, ex);
            this.setShowNewModal(false);
            this.setMensaje("Ocurrio un error al crear el foro");
            this.setVerMensaje(true);
        }
    }

    private UsuarioDTo getUserFromSession() {
        return (UsuarioDTo) WebUtil.getObjectFromSession("userLogged");
    }

    public void editarForo() {
        try {
            this.setShowEditModal(false);
            editForo.setFechaModificacion(new Date());
            foroFacade.editForo(editForo);
            this.setMensaje("El foro se ha modificado correctamente");
            this.setVerMensaje(true);
        } catch (IappException ex) {
            Logger.getLogger(ForoBean.class.getName()).log(Level.SEVERE, null, ex);
            this.setMensaje("Ocurrio un error al modificar el foro");
            this.setVerMensaje(true);
            this.setShowEditModal(false);
        }
    }

    public void eliminarForo() {
        try {

            this.setVerConfirmacion(false);
            foroFacade.deleteForo(editForo);
            editModule.getIappForoCollection().remove(editForo);
            this.setMensaje("El foro se ha eliminado correctamente");
            this.setVerMensaje(true);
        } catch (IappException ex) {
            Logger.getLogger(ForoBean.class.getName()).log(Level.SEVERE, null, ex);
            this.setMensaje("Ocurrio un error al eliminar el foro");
            this.setVerMensaje(true);
            this.setVerConfirmacion(false);
        }
    }

   

    public void hideCreateForo() {
        this.setShowNewModal(false);
    }

    public void openCreateForo() {
        this.newForo = new IappForo();
        this.setShowNewModal(true);
    }

    public void hideEditForo() {
        this.setShowEditModal(false);
    }

    public void openEditForo() {
        this.setShowEditModal(true);
    }

    public void hideVerMensaje() {
        this.setVerMensaje(false);
    }

    public void openConfirmar() {
        setVerConfirmacion(true);
    }

    public void hideConfirmar() {
        setVerConfirmacion(false);
    }

    public IappForo getEditForo() {
        return editForo;
    }

    public void setEditForo(IappForo editForo) {
        this.editForo = editForo;
    }

    public IappForo getNewForo() {
        return newForo;
    }

    public void setNewForo(IappForo newForo) {
        this.newForo = newForo;
    }

    public IappModulos getEditModule() {
        return editModule;
    }

    public void setEditModule(IappModulos editModule) {
        this.editModule = editModule;
    }

    public boolean isShowNewModal() {
        return showNewModal;
    }

    public void setShowNewModal(boolean showNewModal) {
        this.showNewModal = showNewModal;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isVerMensaje() {
        return verMensaje;
    }

    public void setVerMensaje(boolean verMensaje) {
        this.verMensaje = verMensaje;
    }

    public boolean isShowEditModal() {
        return showEditModal;
    }

    public void setShowEditModal(boolean showEditModal) {
        this.showEditModal = showEditModal;
    }

    public boolean isVerConfirmacion() {
        return verConfirmacion;
    }

    public void setVerConfirmacion(boolean verConfirmacion) {
        this.verConfirmacion = verConfirmacion;
    }

   

    public IappForo getForoSeleccionado() {
        return foroSeleccionado;
    }

    public void setForoSeleccionado(IappForo foroSeleccionado) {
        this.foroSeleccionado = foroSeleccionado;
    }
    
    
}
