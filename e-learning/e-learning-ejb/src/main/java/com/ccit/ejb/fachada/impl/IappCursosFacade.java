/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.ejb.modelo.IappCursos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marino
 */
@Stateless
public class IappCursosFacade extends AbstractFacade<IappCursos> {

    @PersistenceContext(unitName = "DinamicPortal-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IappCursosFacade() {
        super(IappCursos.class);
    }

    public List<IappCursos> findCursosByType(String typeCourse) {
        return this.getEntityManager().createNamedQuery("IappCursos.findByTipoCurso", IappCursos.class).setParameter("tipoCurso", typeCourse).getResultList();
    }
}
