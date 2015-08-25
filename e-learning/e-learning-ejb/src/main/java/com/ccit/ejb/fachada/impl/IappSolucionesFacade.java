/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.ejb.modelo.IappEvaluaciones;
import com.ccit.ejb.modelo.IappMatriculas;
import com.ccit.ejb.modelo.IappSoluciones;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author innovasoft
 */
@Stateless
public class IappSolucionesFacade  extends AbstractFacade<IappSoluciones>  {
    
    @PersistenceContext(unitName = "DinamicPortal-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IappSolucionesFacade() {
        super(IappSoluciones.class);
    }
}
