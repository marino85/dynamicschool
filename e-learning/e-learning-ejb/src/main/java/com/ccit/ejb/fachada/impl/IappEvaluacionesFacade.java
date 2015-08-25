/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.ejb.modelo.IappEvaluaciones;
import com.ccit.exception.IappException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author innovasoft
 */
@Stateless
public class IappEvaluacionesFacade  extends AbstractFacade<IappEvaluaciones>  {
    
    @PersistenceContext(unitName = "DinamicPortal-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IappEvaluacionesFacade() {
        super(IappEvaluaciones.class);
    }

    public void refreshEntity(IappEvaluaciones evaluacion) throws IappException {
        Query q= em.createQuery("SELECT e FROM IappPreguntas e WHERE e.idEvaluacion=:idEvaluacion");
        q.setParameter("idEvaluacion", evaluacion);
        evaluacion.setIappPreguntasCollection(q.getResultList());
    }
}
