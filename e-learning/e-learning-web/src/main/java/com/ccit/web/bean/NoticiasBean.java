/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.fachada.NoticiasFacade;
import com.ccit.ejb.modelo.IappNews;
import com.ccit.exception.IappException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class NoticiasBean implements Serializable{
    @EJB
    private NoticiasFacade noticiasFacade;
    
    

    private IappNews newNoticia;
    private IappNews viewNoticia;
    private IappNews editNoticia;
    private List<IappNews> noticias;
    private List<IappNews> lastNews;
    private boolean showDialog=false;
    private boolean showNoticia=false;
     private String messaje;
    @PostConstruct
    public void init(){
        newNoticia=new IappNews();
        editNoticia=new IappNews();
        buscarNoticias();
        findLastNews();
    }
    
    
    public void crearNoticia(){
        try {
            
            newNoticia.setFechaCreacion(new Date());
            noticiasFacade.crearNoticia(newNoticia);
            this.setShowDialog(true);
            this.setMessaje("Se ha creado la noticia correctamente");
        } catch (IappException ex) {
            Logger.getLogger(NoticiasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void buscarNoticias(){
        noticias=noticiasFacade.consultarNoticias();
    }
    
    public void findLastNews(){
        lastNews=noticiasFacade.findLastNews();
    }
    
    
    public String irEditarNoticia(){
        return "editarnoticias";
        
    }
    public void editarNoticia(){
        try {
            noticiasFacade.editarNoticia(editNoticia);
            this.setShowDialog(true);
            this.setMessaje("Se ha modificado la noticia correctamente");
        } catch (IappException ex) {
            Logger.getLogger(NoticiasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void verNoticia(){
        this.setShowNoticia(true);
    }
    
    public void closeNoticia(){
        this.setShowNoticia(false);
    }
    
    
    public void eliminarNoticia(){
        try {
            noticiasFacade.eliminarNoticia(newNoticia);
        } catch (IappException ex) {
            Logger.getLogger(NoticiasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Creates a new instance of Noticias
     */
    public NoticiasBean() {
    }

     public void aceptar(ActionEvent evt){
        this.setMessaje("");
        this.setShowDialog(false);
        this.setNewNoticia(new IappNews());
        //this.setEditNoticia(new IappNews());
        buscarNoticias();
    }
    public IappNews getEditNoticia() {
        return editNoticia;
    }

    public void setEditNoticia(IappNews editNoticia) {
        this.editNoticia = editNoticia;
    }

    public IappNews getNewNoticia() {
        return newNoticia;
    }

    public void setNewNoticia(IappNews newNoticia) {
        this.newNoticia = newNoticia;
    }
    
     public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }

    public String getMessaje() {
        return messaje;
    }

    public void setMessaje(String messaje) {
        this.messaje = messaje;
    }

    public List<IappNews> getNoticias() {
        return noticias;
    }

    public void setNoticias(List<IappNews> noticias) {
        this.noticias = noticias;
    }

    public IappNews getViewNoticia() {
        return viewNoticia;
    }

    public void setViewNoticia(IappNews viewNoticia) {
        this.viewNoticia = viewNoticia;
    }

    public boolean isShowNoticia() {
        return showNoticia;
    }

    public void setShowNoticia(boolean showNoticia) {
        this.showNoticia = showNoticia;
    }

    public List<IappNews> getLastNews() {
        return lastNews;
    }

    public void setLastNews(List<IappNews> lastNews) {
        this.lastNews = lastNews;
    }
    
    
    
    
    
    
}
