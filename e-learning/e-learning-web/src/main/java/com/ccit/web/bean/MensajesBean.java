/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.dto.UsuarioDTo;
import com.ccit.ejb.fachada.ForoFacade;
import com.ccit.ejb.modelo.IappForo;
import com.ccit.ejb.modelo.IappMensajes;
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
@ManagedBean
@SessionScoped
public class MensajesBean {
    @EJB
    private ForoFacade foroFacade;    
    

    private IappForo foroSeleccionado;
    private IappMensajes mensaje;
    private IappMensajes mensajeSeleccionado;
    private boolean openPostMesssage;
    private boolean confirm;
    private String mjs;
    private boolean verForo;

    @PostConstruct
    public void init() {
        System.out.println("init mensajes");
        mensaje = new IappMensajes();
        mensajeSeleccionado = new IappMensajes();
    }
    
    public void postMessage(){
        try {
            mensaje.setFechaCreacion(new Date());
            mensaje.setFechaModificacion(new Date());
            mensaje.setIdForo(foroSeleccionado);
            mensaje.setUsuario(getUserFromSession().getNombre());
            mensaje.setUsuarioCreacion("A");
            mensaje.setUsuarioModificacion("A");
            mensaje.setEstadoRegistro("A");
            
            
            foroSeleccionado.getIappMensajesCollection().add(mensaje);
            
            foroFacade.editForo(foroSeleccionado);
            this.setOpenPostMesssage(false);
            this.setConfirm(true);
            this.setMjs("Su publicación ha sido exitosa");
            cleanMessage();
        } catch (IappException ex) {
            Logger.getLogger(MensajesBean.class.getName()).log(Level.SEVERE, null, ex);
            this.setOpenPostMesssage(false);
            this.setConfirm(true);
            this.setMjs("Ocurrio un error al publicar ");
        }
    }

    public void openVerForo() {
        this.setVerForo(true);
    }

    public void hideVerforo() {
        this.setVerForo(false);
    }
    
    private void cleanMessage(){
        this.setMensaje(new IappMensajes());
    }
    private UsuarioDTo getUserFromSession(){
        return (UsuarioDTo)WebUtil.getObjectFromSession("userLogged");   
    }
    public void openPostMessage() {
        this.mensaje = new IappMensajes();
        
        this.setOpenPostMesssage(true);
    }

    public void hidePostMessage() {
        this.setOpenPostMesssage(false);
    }

    public void openConfirmar() {
        this.setConfirm(true);
    }

    public void hideConfirmar() {
        this.setConfirm(false);
    }

    public IappForo getForoSeleccionado() {
        return foroSeleccionado;
    }

    public void setForoSeleccionado(IappForo foroSeleccionado) {
        this.foroSeleccionado = foroSeleccionado;
    }

    public IappMensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(IappMensajes mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isOpenPostMesssage() {
        return openPostMesssage;
    }

    public void setOpenPostMesssage(boolean openPostMesssage) {
        this.openPostMesssage = openPostMesssage;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public String getMjs() {
        return mjs;
    }

    public void setMjs(String mjs) {
        this.mjs = mjs;
    }
     public boolean isVerForo() {
        return verForo;
    }

    public void setVerForo(boolean verForo) {
        this.verForo = verForo;
    }

    public IappMensajes getMensajeSeleccionado() {
        return mensajeSeleccionado;
    }

    public void setMensajeSeleccionado(IappMensajes mensajeSeleccionado) {
        this.mensajeSeleccionado = mensajeSeleccionado;
    }
    
    
    
}
