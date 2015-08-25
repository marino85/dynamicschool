/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.ejb.modelo.IappMensajes;
import com.sun.org.apache.bcel.internal.generic.L2D;
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
public class IappMensajesFacade extends AbstractFacade<IappMensajes> {
    @PersistenceContext(unitName = "e-learning-ejb_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IappMensajesFacade() {
        super(IappMensajes.class);
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<IappMensajes> listMessagesByForumId(Integer forumId){
        
       List<IappMensajes> mensajes=em.createNamedQuery("IappMensajes.ListByForumId", IappMensajes.class)
                .setParameter("idForo", forumId)
                .getResultList();
        
        return mensajes;
    }
    
     @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
     public List<IappMensajes> listMessagesByMessageId(Integer forumId,Integer messageId){
         List<IappMensajes> mensajes=em.createNamedQuery("IappMensajes.ListByRepplyMessageId", IappMensajes.class)
                .setParameter("idForo", forumId)
                 .setParameter("idMensaje", messageId)
                .getResultList();
        
        return mensajes;
    }
    
    
}
