/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.ejb.modelo.IappTests;
import com.ccit.ejb.modelo.IappTestQuestions;
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
public class IappPreguntaFacade  extends AbstractFacade<IappTestQuestions>  {
    
    @PersistenceContext(unitName = "e-learning-ejb_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IappPreguntaFacade() {
        super(IappTestQuestions.class);
    } 

    public void refreshEntity(IappTestQuestions pregunta) throws IappException {
        Query q= em.createQuery("SELECT e FROM IappRespuestas e WHERE e.idPregunta=:idPregunta");
        q.setParameter("idPregunta", pregunta);
        pregunta.setIappRespuestasCollection(q.getResultList());
    }
}
