/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.ejb.dto.EvaluacionesDto;
import com.ccit.ejb.modelo.IappEnrollments;
import com.ccit.ejb.modelo.IappUser;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author innovasoft
 */
@Stateless
public class IappMatriculasFacade extends AbstractFacade<IappEnrollments> {

    @PersistenceContext(unitName = "e-learning-ejb_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IappMatriculasFacade() {
        super(IappEnrollments.class);
    }
    
    public List<EvaluacionesDto> findEvaluationsByCourse(Integer idCourse, String codigo, String nombres) {
         StringBuilder query = new StringBuilder("SELECT new com.innovasoft.ejb.dto.EvaluacionesDto(");
         query.append("eva.idMatricula.idUsuario.nombres, eva.idMatricula.idUsuario.apellidos,eva.calificacion,eva.fechaInicio)");
         query.append("FROM IappCalEvaluacion eva Where eva.idMatricula.idCurso.idCurso = :idCurso ");
         
        Query q = em.createQuery(query.toString());
        q.setParameter("idCurso", idCourse);
        
        return q.getResultList();

    }

    public List<IappEnrollments> findEstudentsByCourse(Integer idCourse, String codigo, String nombres) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<IappEnrollments> cr = cb.createQuery(IappEnrollments.class);
         Root<IappEnrollments> matriculas = cr.from(IappEnrollments.class); 
         List<Predicate> prediates = new ArrayList<Predicate>();
          prediates.add(cb.equal(matriculas.get("idCurso").get("idCurso"),idCourse));
        
        StringBuilder query = new StringBuilder("SELECT i FROM IappMatriculas i Where i.idCurso.idCurso = :idCurso ");

        if (codigo != null && !codigo.isEmpty()) {
            
             prediates.add(cb.equal(matriculas.get("idUsuario").get("codigo"),codigo));
//            query.append(" AND i.idUsuario.codigo = ");
//            query.append(codigo);
        } if (nombres != null && !nombres.isEmpty()) {
            
             prediates.add(cb.like(matriculas.get("idUsuario").get("nombres").as(String.class), "%" + nombres + "%"));
//            query.append(" AND i.idUsuario.nombres LIKE '% ");
//            query.append(nombres);
//            query.append(" %'");
        }
        if (prediates.size() > 0) {
            cr.where(prediates.toArray(new Predicate[prediates.size()]));
        }
        
        TypedQuery<IappEnrollments> tq = em.createQuery(cr);

        return tq.getResultList();
//        Query q = em.createQuery(query.toString());
//        q.setParameter("idCurso", idCourse);
//        
//
//        return q.getResultList();


    }
}
