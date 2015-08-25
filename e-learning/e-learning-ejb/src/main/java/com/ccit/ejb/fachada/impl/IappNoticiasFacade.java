/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.ejb.modelo.IappNoticias;
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
public class IappNoticiasFacade extends AbstractFacade<IappNoticias> {
    @PersistenceContext(unitName = "DinamicPortal-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IappNoticiasFacade() {
        super(IappNoticias.class);
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<IappNoticias> findLastNews(){
        List<IappNoticias> res=null;
        
       res= em.createNamedQuery("IappNoticias.findByLastPublication", IappNoticias.class).getResultList();
        
        
        return res;
        
    }
    
}
