/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.ejb.modelo.IappTestQuestionAnswers;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author innovasoft
 */
@Stateless
public class IappRespuestasFacade  extends AbstractFacade<IappTestQuestionAnswers>  {
    
    @PersistenceContext(unitName = "e-learning-ejb_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IappRespuestasFacade() {
        super(IappTestQuestionAnswers.class);
    } 
}
