/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.ejb.modelo.IappStreamingEstudiante;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marino
 */
@Stateless
public class IappStreamingEstudianteFacade extends AbstractFacade<IappStreamingEstudiante> {
    @PersistenceContext(unitName = "DinamicPortal-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IappStreamingEstudianteFacade() {
        super(IappStreamingEstudiante.class);
    }
    
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<IappStreamingEstudiante> consultarClasesEstudiantes(Integer idCurso,Integer idUsuario){
         return em.createNamedQuery("IappStreamingEstudiante.findByClasesEstudiantes")
               .setParameter("idCurso", idCurso)
               .setParameter("idUsuario", idUsuario).getResultList();
    }
    
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<IappStreamingEstudiante> consultarEstudiantesInscritos(Integer idCurso,Integer clase){
       return em.createNamedQuery("IappStreamingEstudiante.findByEstudiantesCourse")
                .setParameter("idCurso", idCurso)
               .setParameter("idStreaming", clase).getResultList();
    }
    
}
