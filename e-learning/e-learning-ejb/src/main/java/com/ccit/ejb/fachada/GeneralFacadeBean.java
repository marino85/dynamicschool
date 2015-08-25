/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada;

import com.ccit.ejb.modelo.IappEstadosCurso;
import com.ccit.ejb.modelo.IappSede;
import com.ccit.ejb.modelo.IappNiveles;
import com.ccit.ejb.modelo.IappTipoDocumento;
import com.ccit.ejb.modelo.IappEstadoUsuario;
import com.ccit.ejb.modelo.IappClase;
import com.ccit.ejb.modelo.IappAulas;
import com.ccit.ejb.modelo.IappPerfiles;
import com.ccit.ejb.modelo.IappJornada;
import com.ccit.ejb.modelo.IappCursos;
import com.ccit.ejb.fachada.impl.IappSedeFacade;
import com.ccit.ejb.fachada.impl.IappPerfilesFacade;
import com.ccit.ejb.fachada.impl.IappClaseFacade;
import com.ccit.ejb.fachada.impl.IappJornadaFacade;
import com.ccit.ejb.fachada.impl.IappEstadoCursoFacade;
import com.ccit.ejb.fachada.impl.IappEstadoUsuarioFacade;
import com.ccit.ejb.fachada.impl.IappTipoDocumentoFacade;
import com.ccit.ejb.fachada.impl.IappAulasFacade;
import com.ccit.ejb.fachada.impl.IappCursosFacade;
import com.ccit.ejb.fachada.impl.IappNivelCursoFacade;
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
    private IappTipoDocumentoFacade iappTipoDocumentoFacade;
    @EJB
    private IappJornadaFacade iappJornadaFacade;
    @EJB
    private IappPerfilesFacade iappPerfilesFacade;
    @EJB
    private IappEstadoUsuarioFacade iappEstadoUsuarioFacade;
    @EJB
    private IappEstadoCursoFacade iappEstadoCursoFacade;
    @EJB
    private IappNivelCursoFacade iappNivelCursoFacade;
    @EJB
    private IappAulasFacade iappAulasFacade;
    @EJB
    private IappCursosFacade iappCursosFacade;
    @EJB
    private IappSedeFacade iappSedesFacade;
    @EJB
    private IappClaseFacade iappClaseFacade;
    
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

    public List<IappTipoDocumento> consultarTipoDoc() {

        if (cache.get(IappTipoDocumento.class.getName()) != null) {
            
            return cache.get(IappTipoDocumento.class.getName());
        } else {
            cache.put(IappTipoDocumento.class.getName(), iappTipoDocumentoFacade.findAll());
            
            return cache.get(IappTipoDocumento.class.getName());
        }


    }

    public List<IappEstadoUsuario> consultarEstadosUsuario() {


        if (cache.get(IappEstadoUsuario.class.getName()) != null) {
            return cache.get(IappEstadoUsuario.class.getName());
        } else {
            cache.put(IappEstadoUsuario.class.getName(), iappEstadoUsuarioFacade.findAll());
            
            return cache.get(IappEstadoUsuario.class.getName());
        }

    }

    public List<IappEstadosCurso> consultarEstadosCurso() {

        if (cache.get(IappEstadosCurso.class.getName()) != null) {
            return cache.get(IappEstadosCurso.class.getName());
        } else {
            cache.put(IappEstadosCurso.class.getName(), iappEstadoCursoFacade.findAll());
            
            return cache.get(IappEstadosCurso.class.getName());
        }

    }

    public List<IappNiveles> consultarNivelesCurso() {


        if (cache.get(IappNiveles.class.getName()) != null) {
            return cache.get(IappNiveles.class.getName());
        } else {
            cache.put(IappNiveles.class.getName(), iappNivelCursoFacade.findAll());
            
            return cache.get(IappNiveles.class.getName());
        }


    }

    public List<IappAulas> findAllAulas() {

        if (cache.get(IappAulas.class.getName()) != null) {
            return cache.get(IappAulas.class.getName());
        } else {
            cache.put(IappAulas.class.getName(), iappAulasFacade.findAll());
            return cache.get(IappAulas.class.getName());
        }

    }

    public List<IappCursos> findCoursesByType(String type) {
        return iappCursosFacade.findCursosByType(type);
    }

    public List<IappSede> finAllSedes() {


        if (cache.get(IappSede.class.getName()) != null) {
            return cache.get(IappSede.class.getName());
        } else {
            cache.put(IappSede.class.getName(), iappSedesFacade.findAll());
            return cache.get(IappSede.class.getName());
        }


    }

    public List<IappClase> findAllClases() {


        if (cache.get(IappClase.class.getName()) != null) {
            return cache.get(IappClase.class.getName());
        } else {
            cache.put(IappClase.class.getName(), iappClaseFacade.findAll());
            return cache.get(IappClase.class.getName());
        }


    }
    
   public void sendMail(String email, String subject, String body) throws NamingException, MessagingException {
         mailSender.sendMail(email, subject, body);
   }
}
