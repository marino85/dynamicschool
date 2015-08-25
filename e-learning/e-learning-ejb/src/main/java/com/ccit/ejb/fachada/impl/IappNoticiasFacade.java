/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.ejb.modelo.IappNews;
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
public class IappNoticiasFacade extends AbstractFacade<IappNews> {
    @PersistenceContext(unitName = "e-learning-ejb_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IappNoticiasFacade() {
        super(IappNews.class);
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<IappNews> findLastNews(){
        List<IappNews> res=null;
        
       res= em.createNamedQuery("IappNoticias.findByLastPublication", IappNews.class).getResultList();
        
        
        return res;
        
    }
    
}
