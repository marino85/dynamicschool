/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.exception.IappException;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;

/**
 *
 * @author marino
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) throws IappException {

        try {
            getEntityManager().persist(entity);
        } catch (EntityExistsException e) {
            throw new IappException("Entida ya existe", e);

        } catch (IllegalArgumentException e) {
            throw new IappException("Argumento Ilegal", e);
        } catch (TransactionRequiredException e) {
            throw new IappException("Transaccion Requerida", e);
        }
    }

    public void edit(T entity) throws IappException {

        try {
            getEntityManager().merge(entity);
        } catch (IllegalArgumentException e) {
            throw new IappException("Argumento Ilegal", e);
        } catch (TransactionRequiredException e) {
            throw new IappException("Transaccion Requerida", e);
        }
    }

    public void remove(T entity) throws IappException {
        try {
            getEntityManager().remove(getEntityManager().merge(entity));
        } catch (IllegalArgumentException e) {
            throw new IappException("Argumento Ilegal", e);
        } catch (TransactionRequiredException e) {
            throw new IappException("Transaccion Requerida", e);
        }
    }

    public T find(Object id) throws IappException{
        
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
