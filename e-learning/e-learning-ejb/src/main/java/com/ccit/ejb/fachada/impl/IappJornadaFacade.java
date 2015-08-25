/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.ejb.modelo.IappJornada;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marino
 */
@Stateless
public class IappJornadaFacade extends AbstractFacade<IappJornada> {
    @PersistenceContext(unitName = "e-learning-ejb_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IappJornadaFacade() {
        super(IappJornada.class);
    }
    
}
