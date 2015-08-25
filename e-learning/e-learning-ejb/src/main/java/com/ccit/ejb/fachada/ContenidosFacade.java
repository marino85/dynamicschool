/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada;
import com.ccit.ejb.fachada.impl.IappContenidosFacade;
import com.ccit.ejb.modelo.IappContent;
import com.ccit.exception.IappException;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author marino
 */
@Stateless
@LocalBean
public class ContenidosFacade {
    @EJB
    private IappContenidosFacade iappContenidosFacade;
    
    
    public void merge(IappContent cnt)throws IappException{
        iappContenidosFacade.edit(cnt);
    }

    public void crearContenido(IappContent newContenido) throws IappException {
        iappContenidosFacade.create(newContenido);
    }
    
    public void remove(IappContent contenido) throws IappException {
        iappContenidosFacade.remove(contenido);
    }
}
