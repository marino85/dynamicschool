/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada;
import com.ccit.ejb.fachada.impl.IappEntregasFacade;
import com.ccit.ejb.fachada.impl.IappTrabajosFacade;
import com.ccit.ejb.fachada.impl.IappCalAsignacionesFacade;
import com.ccit.ejb.modelo.IappAsignaciones;
import com.ccit.ejb.modelo.IappCalAsignaciones;
import com.ccit.ejb.modelo.IappEntregas;
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
    
    public void merge(IappAsignaciones asg)throws IappException{
        iappTrabajosFacade.edit(asg);
    }

    public void crearTrabajo(IappAsignaciones newTrabajo) throws IappException {
        iappTrabajosFacade.create(newTrabajo);
    }
    
    public void guardarEntregas(Collection<IappEntregas> entregas)throws IappException{
        for(IappEntregas entrega:entregas){
            iappEntregasFacade.create(entrega);
        }
    }

    public void remove(IappAsignaciones trabajo) throws IappException {
        iappTrabajosFacade.remove(trabajo);
    }
    
    
    public void calificarTrabajo(IappCalAsignaciones calificacion) throws IappException{
        iappCalAsignacionesFacade.edit(calificacion);
    }

}
