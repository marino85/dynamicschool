/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.constants.Constants;
import com.ccit.ejb.dto.UsuarioDTo;
import com.ccit.ejb.fachada.CursosFacade;
import com.ccit.ejb.fachada.impl.IappMatriculasFacade;
import com.ccit.ejb.fachada.impl.IappUsuarioFacade;
import com.ccit.ejb.modelo.*;
import com.ccit.exception.IappException;
import com.ccit.web.util.WebUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author marino
 */

@ManagedBean
@SessionScoped
public class CursosBean implements Serializable {

    @EJB
    private CursosFacade cursosFacade;
    @EJB
    private IappMatriculasFacade mFacade;
    @EJB
    private IappUsuarioFacade userFacade;
    private IappCursos newCourse;
    private IappCursos editCourse;
    private IappUsuario estudiante;    
    private ArrayList<IappUsuario> estudiantesSelected = new ArrayList<IappUsuario>();
    private boolean showDialog1 = false;
    private boolean showDialog2 = false;
    private boolean showInnscribirEst = false;
    private boolean showEstudiante = false;
    private String messaje;
    
    
    //variables para la busqueda de usuario no curso
    private Integer idJornada;
    private Integer idNivel;
    private String nombres;
    private String apellidos;
    private String matricula;
    public List<IappUsuario> estudiantesNocurso = new ArrayList<IappUsuario>();
    
    //metodos para buscar usuario.
    
    public void consultarEstudiantes(){
        estudiantesNocurso = userFacade.getEstudiantesNoCursoFiltro(
                editCourse, idJornada, idNivel, nombres, apellidos,matricula);
    }

    /**
     * Creates a new instance of UsuariosBean
     */
    public CursosBean() {
        
        
    }

    @PostConstruct
    public void init() {
        System.out.println("Cursos bean Init");
    }

    public void guardar(ActionEvent evt) {
        this.showDialog1 = true;
        if (editCourse.getIdEstadoCurso().getIdEstadoCurso().equals(Constants.CURSO_ACTIVO)) {
            BigDecimal total = BigDecimal.ZERO;
            for (IappModulos mod : editCourse.getIappModulosCollection()) {
                total = total.add(mod.getPorcentaje());
            }
            if (total.compareTo(new BigDecimal(BigInteger.ONE)) != 0) {
                this.setMessaje("El curso no puede ser activado, la suma de los porcentajes de los modulos debe ser 100%");
                return;
            }
        }
        try {
            UsuarioDTo u = (UsuarioDTo) WebUtil.getObjectFromSession("userLogged");
            editCourse.setUsuarioModificacion(u.getLogin());
            editCourse.setFechaModificacion(Calendar.getInstance().getTime());
            if (editCourse.getIdCurso() == null) {
                editCourse.setUsuarioCreacion(u.getLogin());
                editCourse.setFechaCreacion(Calendar.getInstance().getTime());
                editCourse.setEstadoRegistro(Constants.REGISTRO_ACTIVO);
                cursosFacade.crearCurso(editCourse);
                this.setMessaje("El curso se ha creado con Exito");
            } else {
                cursosFacade.merge(editCourse);
                this.setMessaje("El curso se ha modificado con Exito");
            }
        } catch (Exception e) {
            System.out.println("Exception error al guardar el curso");
            this.setMessaje("Ocurrio un error al guardar el curso por favor contacte al administrador");
        }
    }

    public void aceptar(ActionEvent evt) {
        this.setMessaje("");
        this.showDialog1 = false;
    }

    public void inscribirEstudiantes() {
        this.setShowInnscribirEst(true);
    }

    public void hideInscribirEstudiantes() {
        this.setShowInnscribirEst(false);
    }

    public String edit() {
        return "editarModulo";
    }

    public String trabajos() {
        return "consultarTrabajos";
    }

    public String contenidos() {
        return "consultarContenidos";
    }

    public String foros() {
        return "consultarForos";
    }

    public String evaluaciones() {
        return "consultarEvaluaciones";
    }

    public String createCourse() {
        editCourse = new IappCursos();
        editCourse.setIdEstadoCurso(new IappEstadosCurso());
        editCourse.setIdProfesor(new IappUsuario());
        editCourse.setIappMatriculasCollection(new ArrayList<IappMatriculas>());
        editCourse.setIappModulosCollection(new ArrayList<IappModulos>());
        return "editarCurso";
    }

    public void agregarEstudiante() {
        this.idJornada =  -1;
        this.idNivel=-1;
        this.nombres="";
        this.apellidos="";
        if(this.estudiantesNocurso!=null){
            this.estudiantesNocurso.clear();
        }
        if(this.estudiantesSelected!=null){
            this.estudiantesSelected.clear();
        }
        this.setShowEstudiante(true);
    }

    public void adicionarEstudiante(IappUsuario user) {
        if (user != null) {
            if(this.estudiantesSelected.contains(user)){
                  Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Remuevo estudiante");
                this.estudiantesSelected.remove(user);
            }
            else{
                  Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Adiciono estudiante");
                 this.estudiantesSelected.add(user);
            }

            
        }
        else{
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "No se ha seleccionado un estudiante");
        }
    }

    public void guardarEstudiante() {
        UsuarioDTo u = (UsuarioDTo) WebUtil.getObjectFromSession("userLogged");
        for (IappUsuario est : estudiantesSelected) {
            IappMatriculas matricula = new IappMatriculas();
            matricula.setEstadoRegistro(Constants.getREGISTRO_ACTIVO());
            matricula.setFechaCreacion(new Date());
            matricula.setFechaMatricula(new Date());
            matricula.setFechaModificacion(new Date());
            matricula.setFechaVencimientoPago(new Date());
            matricula.setFormaPago("E");
            matricula.setIdCurso(editCourse);
            matricula.setIdUsuario(est);
            matricula.setUsuarioCreacion(u.getLogin());
            matricula.setUsuarioModificacion(u.getLogin());
            editCourse.getIappMatriculasCollection().add(matricula);
        }
        this.estudiantesSelected.clear();
        this.setShowEstudiante(false);
    }
    
    public void eliminarMatricula(IappMatriculas matricula){
        try {
          this.editCourse.getIappMatriculasCollection().remove(matricula);
          mFacade.remove(matricula);
          cursosFacade.merge(editCourse);       
        } catch (Exception e) {
              Logger.getLogger(CursosBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void saveMatriculas() {
        try {
            cursosFacade.merge(editCourse);
            this.setShowInnscribirEst(false);
        } catch (IappException ex) {
            Logger.getLogger(CursosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void hideEstudiante() {
        this.setShowEstudiante(false);
    }

    public void confirmar() {
        this.setShowDialog2(true);
    }

    public void hideConfirmar() {
        this.setShowDialog2(false);
    }

    public String getMessaje() {
        return messaje;
    }

    public void setMessaje(String messaje) {
        this.messaje = messaje;
    }

    public IappCursos getEditCourse() {
        return editCourse;
    }

    public void setEditCourse(IappCursos editCourse) {
        this.editCourse = editCourse;
    }

    public IappCursos getNewCourse() {
        return newCourse;
    }

    public void setNewCourse(IappCursos newCourse) {
        this.newCourse = newCourse;
    }

    public boolean isShowDialog1() {
        return showDialog1;
    }

    public void setShowDialog1(boolean showDialog1) {
        this.showDialog1 = showDialog1;
    }

    public boolean isShowDialog2() {
        return showDialog2;
    }

    public void setShowDialog2(boolean showDialog2) {
        this.showDialog2 = showDialog2;
    }

    public boolean isShowInnscribirEst() {
        return showInnscribirEst;
    }

    public void setShowInnscribirEst(boolean showInnscribirEst) {
        this.showInnscribirEst = showInnscribirEst;
    }

    public boolean isShowEstudiante() {
        return showEstudiante;
    }

    public void setShowEstudiante(boolean showEstudiante) {
        this.showEstudiante = showEstudiante;
    }

    public IappUsuario getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(IappUsuario estudiante) {
        this.estudiante = estudiante;
    }

    public ArrayList<IappUsuario> getEstudiantesSelected() {
        return estudiantesSelected;
    }

    public void setEstudiantesSelected(ArrayList<IappUsuario> estudiantesSelected) {
        this.estudiantesSelected = estudiantesSelected;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Integer idJornada) {
        this.idJornada = idJornada;
    }

    public Integer getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Integer idNivel) {
        this.idNivel = idNivel;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public List<IappUsuario> getEstudiantesNocurso() {
        return estudiantesNocurso;
    }

    public void setEstudiantesNocurso(List<IappUsuario> estudiantesNocurso) {
        this.estudiantesNocurso = estudiantesNocurso;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    
    
    
  
}
