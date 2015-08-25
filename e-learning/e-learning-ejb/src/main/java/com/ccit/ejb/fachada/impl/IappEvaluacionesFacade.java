/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.ejb.modelo.IappTests;
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
public class IappEvaluacionesFacade  extends AbstractFacade<IappTests>  {
    
    @PersistenceContext(unitName = "e-learning-ejb_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IappEvaluacionesFacade() {
        super(IappTests.class);
    }

    public void refreshEntity(IappTests evaluacion) throws IappException {
        Query q= em.createQuery("SELECT e FROM IappPreguntas e WHERE e.idEvaluacion=:idEvaluacion");
        q.setParameter("idEvaluacion", evaluacion);
        evaluacion.setIappPreguntasCollection(q.getResultList());
    }
}
