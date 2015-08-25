/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.ejb.modelo.IappStreaming;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marino
 */
@Stateless
public class IappStreamingFacade extends AbstractFacade<IappStreaming> {
    @PersistenceContext(unitName = "e-learning-ejb_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<IappStreaming> consultarClasesOnline(Integer idUsuario,Integer idCurso){
       return  em.createNamedQuery("IappStreaming.findByIdUsuario").setParameter("idusuario", idUsuario)
               .setParameter("idCurso", idCurso).getResultList();
    }

    public IappStreamingFacade() {
        super(IappStreaming.class);
    }
    
}
