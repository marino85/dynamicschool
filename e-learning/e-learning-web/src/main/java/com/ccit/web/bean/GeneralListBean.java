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
    
    private List<IappUsuario> usuarios = new ArrayList<IappUsuario>();
    private List<IappEstadosCurso> estadosCurso = new ArrayList<IappEstadosCurso>();
    private List<IappNiveles> nivelesCurso = new ArrayList<IappNiveles>();
    private List<IappPerfiles> perfiles = new ArrayList<IappPerfiles>();
    private List<IappJornada> jornada = new ArrayList<IappJornada>();
    private List<IappTipoDocumento> tiposDoc = new ArrayList<IappTipoDocumento>();
    private List<IappEstadoUsuario> estadosUsuario = new ArrayList<IappEstadoUsuario>();
    private List<IappCursos> cursos= new ArrayList<IappCursos>();
    private List<IappAulas>  aulas= new ArrayList<IappAulas> ();
    private List<IappSede> sedes = new ArrayList<IappSede>();
    private List<IappClase> clases = new ArrayList<IappClase>();
    private List<IappRespuestas> respuesta = new ArrayList<IappRespuestas>();
    private List <IappUsuario> estudiantesList = new ArrayList<IappUsuario>();
    
    public GeneralListBean() {
    }

    public List<IappUsuario> getUsuarios() {
        usuarios = usuariosFacade.consultarUsuariosPorPerfil(Constants.PERFIL_PROFESOR);
        return usuarios;
    }

    public void setUsuarios(List<IappUsuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<IappNiveles> getNivelesCurso() {
        nivelesCurso = generalFacadeBean.consultarNivelesCurso();
        return nivelesCurso;
    }

    public void setNivelesCurso(List<IappNiveles> nivelesCurso) {
        this.nivelesCurso = nivelesCurso;
    }

    public List<IappEstadosCurso> getEstadosCurso() {
        estadosCurso = generalFacadeBean.consultarEstadosCurso();
        return estadosCurso;
    }

    public void setEstadosCurso(List<IappEstadosCurso> estadosCurso) {
        this.estadosCurso = estadosCurso;
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

    public List<IappTipoDocumento> getTiposDoc() {
        tiposDoc = generalFacadeBean.consultarTipoDoc();
        return tiposDoc;
    }

    public void setTiposDoc(List<IappTipoDocumento> tiposDoc) {
        this.tiposDoc = tiposDoc;
    }

    public List<IappEstadoUsuario> getEstadosUsuario() {
        estadosUsuario = generalFacadeBean.consultarEstadosUsuario();
        return estadosUsuario;
    }

    public void setEstadosUsuario(List<IappEstadoUsuario> estadosUsuario) {
        this.estadosUsuario = estadosUsuario;
    }
    public List<IappAulas> getAulas() {
        this.setAulas(generalFacadeBean.findAllAulas());
        return aulas;
    }

    public void setAulas(List<IappAulas> aulas) {
        this.aulas = aulas;
    }

    public List<IappCursos> getCursos() {
        this.setCursos(generalFacadeBean.findCoursesByType(Constants.CURSO_PRESENCIAL));
        return cursos;
    }

    public void setCursos(List<IappCursos> cursos) {
        this.cursos = cursos;
    }

    public List<IappClase> getClases() {
        this.setClases(generalFacadeBean.findAllClases());
        return clases;
    }

    public void setClases(List<IappClase> clases) {
        this.clases = clases;
    }

    public List<IappSede> getSedes() {
        this.setSedes(generalFacadeBean.finAllSedes());
        return sedes;
    }

    public void setSedes(List<IappSede> sedes) {
        this.sedes = sedes;
    }

    public List<IappRespuestas> getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(List<IappRespuestas> respuesta) {
        this.respuesta = respuesta;
    }

    public List<IappUsuario> getEstudiantesList(IappCursos editCourse) {
        estudiantesList = usuariosFacade.getEstudiantes(editCourse);
//        estudiantesList = usuariosFacade.getEstudiantesActivos();
        return estudiantesList;
    }

    public List<IappUsuario> getEstudiantesList() {
        return estudiantesList;
    }

    public void setEstudiantesList(List<IappUsuario> estudiantesList) {
        this.estudiantesList = estudiantesList;
    }
}
