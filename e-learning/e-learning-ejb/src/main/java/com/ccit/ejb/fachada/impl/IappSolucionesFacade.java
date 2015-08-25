/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.ejb.modelo.IappTestSolutions;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author innovasoft
 */
@Stateless
public class IappSolucionesFacade  extends AbstractFacade<IappTestSolutions>  {
    
    @PersistenceContext(unitName = "e-learning-ejb_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IappSolucionesFacade() {
        super(IappTestSolutions.class);
    }
}
