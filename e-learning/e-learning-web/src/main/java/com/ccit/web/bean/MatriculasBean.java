/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.fachada.CursosFacade;
import com.ccit.ejb.fachada.impl.IappMatriculasFacade;
import com.ccit.ejb.modelo.IappCursos;
import com.ccit.ejb.modelo.IappMatriculas;
import com.ccit.ejb.modelo.IappUsuario;
import com.ccit.exception.IappException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author marino
 */
@ManagedBean
@SessionScoped
public class MatriculasBean implements Serializable {

    @EJB
    private CursosFacade cursosFacade;
    @EJB
    private IappMatriculasFacade mFacade;
    private List<IappCursos> courses = new ArrayList<IappCursos>();
    private IappUsuario usuario;

    /**
     * Creates a new instance of UsuariosBean
     */
    public MatriculasBean() {
    }

    @PostConstruct
    public void init() {
        System.out.println("Matricuals bean Init");
       consultarCursos();
    }
    
    public void consultarCursos(){
         courses = cursosFacade.buscarCursos();
    }

    public String irMatriculas() {
        return "matriculas";
    }

    public String regresar() {
        return "consultarEstudiante";
    }

    public void inscribir(IappCursos curso) {
        try {

            IappMatriculas m = new IappMatriculas();
            m.setIdUsuario(usuario);
            m.setIdCurso(curso);
            m.setFechaCreacion(new Date());
            m.setFechaMatricula(new Date());
            m.setFechaModificacion(new Date());
            m.setFechaVencimientoPago(new Date());
            m.setFormaPago("E");
            m.setEstadoRegistro("A");
            m.setUsuarioCreacion("1234");
            m.setUsuarioModificacion("1234");
            mFacade.create(m);
            consultarCursos();
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Se ha inscrito exitosamente"));

        } catch (IappException ex) {
            Logger.getLogger(MatriculasBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Ocurrio un error al inscribir un estudiante"));
        }

    }

    public void eliminar(IappCursos curso) {
        try {

            for (IappMatriculas matricula : curso.getIappMatriculasCollection()) {
                if (matricula.getIdUsuario().getIdUsuario().intValue()
                        == this.usuario.getIdUsuario().intValue()) {
                    mFacade.remove(matricula);
                    break;
                }
            }
            consultarCursos();
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Se ha desvinculado exitosamente"));

        } catch (IappException ex) {
            Logger.getLogger(MatriculasBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Ocurrio un error al eliminar del curso "));
        }

    }

    public boolean estaInscrito(IappCursos curso) {


        for (IappMatriculas matricula : curso.getIappMatriculasCollection()) {
            if (matricula.getIdUsuario().getIdUsuario().intValue()
                    == this.usuario.getIdUsuario().intValue()) {
                return true;
            }
        }
        return false;
    }

    public List<IappCursos> getCourses() {
        return courses;
    }

    public void setCourses(List<IappCursos> courses) {
        this.courses = courses;
    }

    public IappUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(IappUsuario usuario) {
        this.usuario = usuario;
    }
}
