/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.ejb.modelo.IappEvaluaciones;
import com.ccit.ejb.modelo.IappPreguntas;
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
public class IappPreguntaFacade  extends AbstractFacade<IappPreguntas>  {
    
    @PersistenceContext(unitName = "DinamicPortal-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IappPreguntaFacade() {
        super(IappPreguntas.class);
    } 

    public void refreshEntity(IappPreguntas pregunta) throws IappException {
        Query q= em.createQuery("SELECT e FROM IappRespuestas e WHERE e.idPregunta=:idPregunta");
        q.setParameter("idPregunta", pregunta);
        pregunta.setIappRespuestasCollection(q.getResultList());
    }
}
