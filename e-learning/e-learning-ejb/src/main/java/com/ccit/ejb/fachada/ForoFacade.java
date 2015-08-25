/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada;

import com.ccit.ejb.fachada.impl.IappForoFacade;
import com.ccit.ejb.fachada.impl.IappMensajesFacade;
import com.ccit.ejb.modelo.IappForo;
import com.ccit.ejb.modelo.IappMensajes;
import com.ccit.exception.IappException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author marino
 */
@Stateless
@LocalBean
public class ForoFacade {

    @EJB
    private IappMensajesFacade iappMensajesFacade;
    @EJB
    private IappForoFacade iappForoFacade;

    public void createForo(IappForo ent) throws IappException {
        iappForoFacade.create(ent);
    }

    public void editForo(IappForo ent) throws IappException {
        iappForoFacade.edit(ent);
    }

    public void deleteForo(IappForo ent) throws IappException {
        iappForoFacade.remove(ent);
    }

    public List<IappForo> listForo() {
        return iappForoFacade.findAll();
    }

    /**
     * create a new message
     *
     * @param message
     * @throws IappException
     */
    public void postMessage(IappMensajes message) throws IappException {
        iappMensajesFacade.create(message);
    }

    /**
     * create a new messague with replymessage != null
     *
     * @param message
     * @throws IappException
     */
    public void replyMessage(IappMensajes message) throws IappException {
        iappMensajesFacade.create(message);
    }

    public List<IappMensajes> listMessagesByForumId(Integer forumId) {
        return iappMensajesFacade.listMessagesByForumId(forumId);
    }

    public List<IappMensajes> listMessagesByMessageId(Integer forumId, Integer messageId) {
        return iappMensajesFacade.listMessagesByMessageId(forumId, messageId);
    }
}
