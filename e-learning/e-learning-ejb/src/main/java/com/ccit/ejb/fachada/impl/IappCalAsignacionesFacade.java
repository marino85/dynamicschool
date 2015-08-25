/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.ejb.modelo.IappCalAsignaciones;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author innovasoft
 */
@Stateless
public class IappCalAsignacionesFacade extends AbstractFacade<IappCalAsignaciones> {
    @PersistenceContext(unitName = "DinamicPortal-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IappCalAsignacionesFacade() {
        super(IappCalAsignaciones.class);
    }
    
}
