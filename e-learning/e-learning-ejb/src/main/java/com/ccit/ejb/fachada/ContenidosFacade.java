/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada;
import com.ccit.ejb.fachada.impl.IappContenidosFacade;
import com.ccit.ejb.modelo.IappContenidos;
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
    
    
    public void merge(IappContenidos cnt)throws IappException{
        iappContenidosFacade.edit(cnt);
    }

    public void crearContenido(IappContenidos newContenido) throws IappException {
        iappContenidosFacade.create(newContenido);
    }
    
    public void remove(IappContenidos contenido) throws IappException {
        iappContenidosFacade.remove(contenido);
    }
}
