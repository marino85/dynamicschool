/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.ejb.modelo.IappCourses;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marino
 */
@Stateless
public class IappCursosFacade extends AbstractFacade<IappCourses> {

    @PersistenceContext(unitName = "e-learning-ejb_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IappCursosFacade() {
        super(IappCourses.class);
    }

    public List<IappCourses> findCursosByType(String typeCourse) {
        return this.getEntityManager().createNamedQuery("IappCursos.findByTipoCurso", IappCourses.class).setParameter("tipoCurso", typeCourse).getResultList();
    }
}
