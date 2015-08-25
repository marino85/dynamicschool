/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada;

import com.ccit.ejb.modelo.IappPerfiles;
import com.ccit.ejb.modelo.IappJornada;

import com.ccit.ejb.fachada.impl.IappPerfilesFacade;

import com.ccit.ejb.fachada.impl.IappJornadaFacade;

import com.ccit.mail.MailSender;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.naming.NamingException;

/**
 *
 * @author marino
 */
@Stateless
@LocalBean
public class GeneralFacadeBean {

    @EJB
    private MailSender mailSender;

 
    @EJB
    private IappJornadaFacade iappJornadaFacade;
    @EJB
    private IappPerfilesFacade iappPerfilesFacade;
  

    private static Map<String, List> cache = new HashMap<String, List>();

    public List<IappPerfiles> consultarPerfiles() {

        if (cache.get(IappPerfiles.class.getName()) != null) {

            return cache.get(IappPerfiles.class.getName());
        } else {
            cache.put(IappPerfiles.class.getName(), iappPerfilesFacade.findAll());

            return cache.get(IappPerfiles.class.getName());
        }

    }

    public List<IappJornada> consultarJornada() {

        if (cache.get(IappJornada.class.getName()) != null) {

            return cache.get(IappJornada.class.getName());
        } else {
            cache.put(IappJornada.class.getName(), iappJornadaFacade.findAll());

            return cache.get(IappJornada.class.getName());
        }

    }

    public void sendMail(String email, String subject, String body) throws NamingException, MessagingException {
        mailSender.sendMail(email, subject, body);
    }
}
