/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.ejb.modelo.IappNiveles;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author innovasoft
 */
@Stateless
public class IappNivelCursoFacade extends AbstractFacade<IappNiveles> {
    @PersistenceContext(unitName = "DinamicPortal-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IappNivelCursoFacade() {
        super(IappNiveles.class);
    } 
}
