/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.constants.Constants;
import com.ccit.ejb.fachada.GeneralFacadeBean;
import com.ccit.ejb.fachada.UsuariosFacade;
import com.ccit.ejb.modelo.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author innovasoft
 */

@ManagedBean
@ViewScoped
public class GeneralListBean implements Serializable {
    @EJB
    private GeneralFacadeBean generalFacadeBean;
    @EJB
    private UsuariosFacade usuariosFacade;
    
    private List<IappUser> usuarios = new ArrayList<IappUser>();
  
    private List<IappPerfiles> perfiles = new ArrayList<IappPerfiles>();
    private List<IappJornada> jornada = new ArrayList<IappJornada>();
  
    private List<IappCourses> cursos= new ArrayList<IappCourses>();
  
    private List<IappTestQuestionAnswers> respuesta = new ArrayList<IappTestQuestionAnswers>();
    private List <IappUser> estudiantesList = new ArrayList<IappUser>();
    
    public GeneralListBean() {
    }

    public List<IappUser> getUsuarios() {
        usuarios = usuariosFacade.consultarUsuariosPorPerfil(Constants.PERFIL_PROFESOR);
        return usuarios;
    }

    public void setUsuarios(List<IappUser> usuarios) {
        this.usuarios = usuarios;
    }

     

    public List<IappJornada> getJornada() {
        jornada  = generalFacadeBean.consultarJornada();
        return jornada;
    }

    public void setJornada(List<IappJornada> jornada) {
        this.jornada = jornada;
    }

    public List<IappPerfiles> getPerfiles() {
        perfiles = generalFacadeBean.consultarPerfiles();
        return perfiles;
    }

    public void setPerfiles(List<IappPerfiles> perfiles) {
        this.perfiles = perfiles;
    }

   
    public List<IappCourses> getCursos() {
        //this.setCursos(generalFacadeBean.findCoursesByType(Constants.CURSO_PRESENCIAL));
        return cursos;
    }

    public void setCursos(List<IappCourses> cursos) {
        this.cursos = cursos;
    }

 

    public List<IappTestQuestionAnswers> getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(List<IappTestQuestionAnswers> respuesta) {
        this.respuesta = respuesta;
    }

    public List<IappUser> getEstudiantesList(IappCourses editCourse) {
        estudiantesList = usuariosFacade.getEstudiantes(editCourse);
//        estudiantesList = usuariosFacade.getEstudiantesActivos();
        return estudiantesList;
    }

    public List<IappUser> getEstudiantesList() {
        return estudiantesList;
    }

    public void setEstudiantesList(List<IappUser> estudiantesList) {
        this.estudiantesList = estudiantesList;
    }
}
