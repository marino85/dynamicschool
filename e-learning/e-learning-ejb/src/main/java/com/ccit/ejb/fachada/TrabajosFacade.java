/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada;
import com.ccit.ejb.fachada.impl.IappEntregasFacade;
import com.ccit.ejb.fachada.impl.IappTrabajosFacade;
import com.ccit.ejb.fachada.impl.IappCalAsignacionesFacade;
import com.ccit.ejb.modelo.IappHomeWorks;
import com.ccit.ejb.modelo.IappQualificationHomeWorks;
import com.ccit.ejb.modelo.IappSentFiles;
import com.ccit.exception.IappException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author marino
 */
@Stateless
@LocalBean
public class TrabajosFacade {
    @EJB
    private IappEntregasFacade iappEntregasFacade;
    @EJB
    private IappTrabajosFacade iappTrabajosFacade;
    @EJB
    private IappCalAsignacionesFacade iappCalAsignacionesFacade;    
    
    public void merge(IappHomeWorks asg)throws IappException{
        iappTrabajosFacade.edit(asg);
    }

    public void crearTrabajo(IappHomeWorks newTrabajo) throws IappException {
        iappTrabajosFacade.create(newTrabajo);
    }
    
    public void guardarEntregas(Collection<IappSentFiles> entregas)throws IappException{
        for(IappSentFiles entrega:entregas){
            iappEntregasFacade.create(entrega);
        }
    }

    public void remove(IappHomeWorks trabajo) throws IappException {
        iappTrabajosFacade.remove(trabajo);
    }
    
    
    public void calificarTrabajo(IappQualificationHomeWorks calificacion) throws IappException{
        iappCalAsignacionesFacade.edit(calificacion);
    }

}
