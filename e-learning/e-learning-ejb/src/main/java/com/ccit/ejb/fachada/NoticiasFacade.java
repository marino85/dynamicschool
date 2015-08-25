/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada;

import com.ccit.ejb.fachada.impl.IappNoticiasFacade;
import com.ccit.ejb.modelo.IappNews;
import com.ccit.exception.IappException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author marino
 */
@Stateless
@LocalBean
public class NoticiasFacade {
    @EJB
    private IappNoticiasFacade iappNoticiasFacade;

      
    public void crearNoticia(IappNews noticia)throws IappException{
        iappNoticiasFacade.create(noticia);
    }
    public void editarNoticia(IappNews noticia)throws IappException{
        iappNoticiasFacade.edit(noticia);
    }
    public void eliminarNoticia(IappNews noticia)throws IappException{
        iappNoticiasFacade.remove(noticia);
    }
    public List<IappNews> consultarNoticias(){
        return iappNoticiasFacade.findAll();
    }
    public List<IappNews> findLastNews(){
        return iappNoticiasFacade.findLastNews();
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
