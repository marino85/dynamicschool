/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.constants.Constants;
import com.ccit.ejb.dto.UsuarioDTo;
import com.ccit.ejb.fachada.CursosFacade;
import com.ccit.ejb.fachada.EvaluacionesFacade;
import com.ccit.ejb.modelo.*;
import com.ccit.exception.IappException;
import com.ccit.web.util.WebUtil;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author marino
 */
@ManagedBean
@SessionScoped
public class EvaluacionesBean implements Serializable {

    @EJB
    EvaluacionesFacade evaluacionesFacade;
    @EJB
    CursosFacade cursoFacade;
    private IappModulos editModule;
    private IappMatriculas matricula;
    private IappEvaluaciones evaluacion;
    private IappPreguntas pregunta;
    private IappRespuestas respuesta;
    private String messaje;
    private boolean verConfirmacion, verMensaje, verPreguntaConfirmacion, verRespuestaConfirmacion;
    private boolean nuevaEvaluacionDialog, verEvaluacionDialog, nuevaPreguntaDialog, nuevaRespuestaDialog, tomarEvaluacionDialog;
    private long milisegundosevaluacion;
    private boolean presentarEvaluacion;
    private boolean dialogConfirmar;
    private String codigo;
    private String nombres;
    private List<IappMatriculas> matriculas = new ArrayList<IappMatriculas>();
    Collection<IappSoluciones> soluciones;
    IappCalEvaluacion calificacion;

    /**
     * Creates a new instance of UsuariosBean
     */
    public EvaluacionesBean() {
    }

    public void buscarEstudiantes() {
        matriculas = cursoFacade.findEstudentsByCourse(this.editModule.getIdCurso().getIdCurso(), codigo, nombres);
    }

    @PostConstruct
    public void init() {
        evaluacion = new IappEvaluaciones();
        pregunta = new IappPreguntas();
        respuesta = new IappRespuestas();
        this.presentarEvaluacion = true;
    }

    public void eliminarEvaluacion() {
        try {
            editModule.getIappEvaluacionesCollection().remove(evaluacion);
            evaluacionesFacade.remove(evaluacion);
            setVerConfirmacion(false);
        } catch (IappException ex) {
            Logger.getLogger(EvaluacionesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarPregunta() {
        evaluacion.getIappPreguntasCollection().remove(pregunta);
        setVerPreguntaConfirmacion(false);
    }

    public void eliminarRespuesta() {
        pregunta.getIappRespuestasCollection().remove(respuesta);
        setVerRespuestaConfirmacion(false);
    }

    public void guardar() {
        if (!editModule.getIappEvaluacionesCollection().contains(evaluacion)) {
            UsuarioDTo u = (UsuarioDTo) WebUtil.getObjectFromSession("userLogged");
            if (evaluacion.getFechaCreacion() == null) {
                evaluacion.setUsuarioCreacion(u.getLogin());
                evaluacion.setFechaCreacion(Calendar.getInstance().getTime());
                evaluacion.setEstadoRegistro(Constants.REGISTRO_ACTIVO);
            }
            evaluacion.setUsuarioModificacion(u.getLogin());
            evaluacion.setFechaModificacion(Calendar.getInstance().getTime());
            editModule.getIappEvaluacionesCollection().add(evaluacion);
        }
        try {
            if (evaluacion.getIdEvaluacion() == null) {
                evaluacionesFacade.crearEvaluacion(evaluacion);
            } else {
                evaluacionesFacade.merge(evaluacion);
            }
            setMessaje("La informacion ha sido actualizada exitosamente.");
            setVerMensaje(true);
        } catch (IappException ex) {
            setMessaje("Error al guardar los archivos en la base de datos.");
            setVerMensaje(true);
        }
        setNuevaEvaluacionDialog(false);
    }

    public void guardarPregunta() {
        if (!evaluacion.getIappPreguntasCollection().contains(pregunta)) {
            UsuarioDTo u = (UsuarioDTo) WebUtil.getObjectFromSession("userLogged");
            if (pregunta.getFechaCreacion() == null) {
                pregunta.setUsuarioCreacion(u.getLogin());
                pregunta.setFechaCreacion(Calendar.getInstance().getTime());
                pregunta.setEstadoRegistro(Constants.REGISTRO_ACTIVO);
            }
            pregunta.setUsuarioModificacion(u.getLogin());
            pregunta.setFechaModificacion(Calendar.getInstance().getTime());
            evaluacion.getIappPreguntasCollection().add(pregunta);
        }
        setNuevaPreguntaDialog(false);
    }

    public void guardarRespuesta() {
        if (!pregunta.getIappRespuestasCollection().contains(respuesta)) {
            UsuarioDTo u = (UsuarioDTo) WebUtil.getObjectFromSession("userLogged");
            if (respuesta.getFechaCreacion() == null) {
                respuesta.setUsuarioCreacion(u.getLogin());
                respuesta.setFechaCreacion(Calendar.getInstance().getTime());
                respuesta.setEstadoRegistro(Constants.REGISTRO_ACTIVO);
            }
            respuesta.setUsuarioModificacion(u.getLogin());
            respuesta.setFechaModificacion(Calendar.getInstance().getTime());
            pregunta.getIappRespuestasCollection().add(respuesta);
        }
        setNuevaRespuestaDialog(false);
    }

    public void aceptar() {
        setMessaje("");
        setVerMensaje(false);
    }

    public void seleccionPregunta(ValueChangeEvent event) {
    }

    public void crearPregunta() {
        pregunta = new IappPreguntas();
        pregunta.setEstadoRegistro(Constants.REGISTRO_ACTIVO);
        pregunta.setIappRespuestasCollection(new ArrayList<IappRespuestas>());
        pregunta.setIdEvaluacion(evaluacion);
        this.setNuevaPreguntaDialog(Boolean.TRUE);
    }

    public void crearRespuesta() {
        respuesta = new IappRespuestas();
        respuesta.setEstadoRegistro(Constants.REGISTRO_ACTIVO);
        respuesta.setIdPregunta(pregunta);
        respuesta.setCorrecta(Boolean.FALSE);
        this.setNuevaRespuestaDialog(Boolean.TRUE);
    }

    public void createEvaluacion() {
        evaluacion = new IappEvaluaciones();
        evaluacion.setIappPreguntasCollection(new <IappPreguntas>ArrayList());
        evaluacion.setIdModulo(editModule);
        setNuevaEvaluacionDialog(true);
    }

    public void editEvaluacion() {
        if (evaluacion.isEditable()) {
            setNuevaEvaluacionDialog(true);
        }
    }

    public String iniciarEvaluacion() {
        calificacion = evaluacion.getCalificacion(matricula);
        if (!evaluacion.isEditable()) {
            if (calificacion != null) {
                return "verEvaluacion";
            } else {
                setTomarEvaluacionDialog(true);
                calificacion = new IappCalEvaluacion();
                calificacion.setIdMatricula(matricula);
                calificacion.setIdEvaluacion(evaluacion);
                calificacion.setEstadoRegistro(Constants.REGISTRO_ACTIVO);
                calificacion.setFechaCreacion(new Date());
                calificacion.setFechaModificacion(new Date());
                calificacion.setNoCorrectas(0);
                calificacion.setUsuarioCreacion("0");
                calificacion.setUsuarioModificacion("0");
                calificacion.setFechaInicio(new Date());
                this.setMilisegundosevaluacion(evaluacion.getDuracion() * 60);
                this.presentarEvaluacion = true;
            }
        }
        return "";
    }

    public void hideTomarEvaluacionDialog() {
        calificacion = null;
        setTomarEvaluacionDialog(false);
    }

    public String tomarEvaluacion() {
        try {
            evaluacion.getIappCalEvaluacionCollection().add(calificacion);
            evaluacionesFacade.iniciarExamen(calificacion);
            setTomarEvaluacionDialog(false);
        } catch (IappException ex) {
            Logger.getLogger(EvaluacionesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "verEvaluacion";
    }

    public void editPregunta() {
        setNuevaPreguntaDialog(true);
    }

    public void editRespuesta() {
        setNuevaRespuestaDialog(true);
    }

    public void hideVerEvaluacion() {
        setVerEvaluacionDialog(false);
    }

    public void finalizarEvaluacion() {
        this.setDialogConfirmar(true);
    }

    public void cancelarFinalizarEvaluacion() {
        this.setDialogConfirmar(false);
    }

    public String validarRespuestas() {
        soluciones = new ArrayList<IappSoluciones>();
        UsuarioDTo u = (UsuarioDTo) WebUtil.getObjectFromSession("userLogged");
        for (IappPreguntas prg : evaluacion.getIappPreguntasCollection()) {
            if (prg.multiRespuesta()) {
                String[] rsp = prg.getRespuestas();
                if (rsp != null && rsp.length > 0) {
                    for (int i = 0; i < rsp.length; i++) {
                        for (IappRespuestas resp : prg.getIappRespuestasCollection()) {
                            if (rsp[i].equals(resp.toString())) {
                                IappSoluciones sol = new IappSoluciones();
                                sol.setEstadoRegistro(Constants.REGISTRO_ACTIVO);
                                sol.setFechaCreacion(new Date());
                                sol.setFechaModificacion(new Date());
                                sol.setIdMatricula(matricula);
                                sol.setIdRespuesta(resp);
                                sol.setUsuarioCreacion(u.getLogin());
                                sol.setUsuarioModificacion(u.getLogin());
                                soluciones.add(sol);
                            }
                        }
                    }
                } else {
                    setVerConfirmacion(true);
                    setDialogConfirmar(false);
                }
            } else {
                String rsp = prg.getRespuesta();
                if (rsp != null && !rsp.isEmpty()) {
                    for (IappRespuestas resp : prg.getIappRespuestasCollection()) {
                        if (rsp.equals(resp.toString())) {
                            IappSoluciones sol = new IappSoluciones();
                            sol.setEstadoRegistro(Constants.REGISTRO_ACTIVO);
                            sol.setFechaCreacion(new Date());
                            sol.setFechaModificacion(new Date());
                            sol.setIdMatricula(matricula);
                            sol.setIdRespuesta(resp);
                            sol.setUsuarioCreacion(u.getLogin());
                            sol.setUsuarioModificacion(u.getLogin());
                            soluciones.add(sol);
                        }
                    }
                } else {
                    setVerConfirmacion(true);
                    setDialogConfirmar(false);
                }
            }
        }
        if (!isVerConfirmacion()) {
            enviarRespuestas();
            return "consultarEvaluaciones";
        }
        return "";
    }

    public String enviarRespuestas() {
        try {
            if (!evaluacion.isEditable() && !calificacion.isEditable(evaluacion.getDuracion())) {
                evaluacionesFacade.enviarRespuestas(soluciones);
                evaluacionesFacade.calificarExamen(matricula, evaluacion, calificacion);
            }
        } catch (IappException ex) {
            Logger.getLogger(EvaluacionesBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EvaluacionesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setPresentarEvaluacion(false);
        return "consultarEvaluaciones";
    }

    public void confirmar() {
        if (evaluacion.isEditable()) {
            setVerConfirmacion(true);
        }
    }

    public void confirmarPregunta() {
        setVerPreguntaConfirmacion(true);
    }

    public void confirmarRespuesta() {
        setVerRespuestaConfirmacion(true);
    }

    public void hideConfirmar() {
        setVerConfirmacion(false);
    }

    public void hidePreguntaConfirmar() {
        setVerPreguntaConfirmacion(false);
    }

    public void hideRespuestaConfirmar() {
        setVerRespuestaConfirmacion(false);
    }

    public void hideCrearEvaluacion() {
        try {
            if (evaluacion.getIdEvaluacion() != null) {
                evaluacionesFacade.refresh(evaluacion);
            }
            setNuevaEvaluacionDialog(false);
        } catch (IappException ex) {
            Logger.getLogger(EvaluacionesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void hideCrearPregunta() {
        try {
            if (pregunta.getIdPregunta() != null) {
                evaluacionesFacade.refresh(pregunta);
            }
            setNuevaPreguntaDialog(false);
        } catch (IappException ex) {
            Logger.getLogger(EvaluacionesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void hideCrearRespuesta() {
        setNuevaRespuestaDialog(false);
    }

    public void verEvaluacion() {
        setVerEvaluacionDialog(true);
    }

    public IappModulos getEditModule() {
        return editModule;
    }

    public void setEditModule(IappModulos editModule) {
        this.editModule = editModule;

        if (this.editModule.getIdModulo() != null) {
        }
    }

    public String getMessaje() {
        return messaje;
    }

    public void setMessaje(String messaje) {
        this.messaje = messaje;
    }

    public boolean isVerConfirmacion() {
        return verConfirmacion;
    }

    public void setVerConfirmacion(boolean verConfirmacion) {
        this.verConfirmacion = verConfirmacion;
    }

    public boolean isVerMensaje() {
        return verMensaje;
    }

    public void setVerMensaje(boolean verMensaje) {
        this.verMensaje = verMensaje;
    }

    public boolean isNuevaEvaluacionDialog() {
        return nuevaEvaluacionDialog;
    }

    public void setNuevaEvaluacionDialog(boolean nuevaEvaluacionDialog) {
        this.nuevaEvaluacionDialog = nuevaEvaluacionDialog;
    }

    public boolean isVerEvaluacionDialog() {
        return verEvaluacionDialog;
    }

    public void setVerEvaluacionDialog(boolean verEvaluacionDialog) {
        this.verEvaluacionDialog = verEvaluacionDialog;
    }

    public IappEvaluaciones getEvaluacion() {
        return evaluacion;
    }
    
     public IappCalEvaluacion verCalificacion(IappMatriculas matricula) {
         System.out.println(matricula);
        if(matricula!=null){
         for(IappCalEvaluacion eva:this.getEvaluacion().getIappCalEvaluacionCollection()){
             if(eva.getIdMatricula().getIdMatricula().intValue()==matricula.getIdMatricula().intValue()){
                 return eva;
             }
         }
            System.out.println(matricula.getIdMatricula());
        }
         return null;
     }

    public void setEvaluacion(IappEvaluaciones evaluacion) {
        this.evaluacion = evaluacion;
        if (this.evaluacion.getIdModulo() != null) {

matriculas = cursoFacade.findEstudentsByCourse(this.editModule.getIdCurso().getIdCurso(), codigo, nombres);
//            matriculas = new ArrayList<IappMatriculas>(this.evaluacion.getIdModulo().getIdCurso().getIappMatriculasCollection());
        }
    }

    public IappPreguntas getPregunta() {
        return pregunta;
    }

    public void setPregunta(IappPreguntas pregunta) {
        this.pregunta = pregunta;
    }

    public boolean isNuevaPreguntaDialog() {
        return nuevaPreguntaDialog;
    }

    public void setNuevaPreguntaDialog(boolean nuevaPreguntaDialog) {
        this.nuevaPreguntaDialog = nuevaPreguntaDialog;
    }

    public IappRespuestas getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(IappRespuestas respuesta) {
        this.respuesta = respuesta;
    }

    public boolean isNuevaRespuestaDialog() {
        return nuevaRespuestaDialog;
    }

    public void setNuevaRespuestaDialog(boolean nuevaRespuestaDialog) {
        this.nuevaRespuestaDialog = nuevaRespuestaDialog;
    }

    public boolean isVerPreguntaConfirmacion() {
        return verPreguntaConfirmacion;
    }

    public void setVerPreguntaConfirmacion(boolean verPreguntaConfirmacion) {
        this.verPreguntaConfirmacion = verPreguntaConfirmacion;
    }

    public boolean isVerRespuestaConfirmacion() {
        return verRespuestaConfirmacion;
    }

    public void setVerRespuestaConfirmacion(boolean verRespuestaConfirmacion) {
        this.verRespuestaConfirmacion = verRespuestaConfirmacion;
    }

    public boolean isTomarEvaluacionDialog() {
        return tomarEvaluacionDialog;
    }

    public void setTomarEvaluacionDialog(boolean tomarEvaluacionDialog) {
        this.tomarEvaluacionDialog = tomarEvaluacionDialog;
    }

    public IappMatriculas getMatricula() {
        return matricula;
    }

    public void setMatricula(IappMatriculas matricula) {
        this.matricula = matricula;
    }

    public IappCalEvaluacion getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(IappCalEvaluacion calificacion) {
        this.calificacion = calificacion;
    }

    public long getMilisegundosevaluacion() {
        return milisegundosevaluacion;
    }

    public void setMilisegundosevaluacion(long milisegundosevaluacion) {
        this.milisegundosevaluacion = milisegundosevaluacion;
    }

    public boolean isPresentarEvaluacion() {
        return presentarEvaluacion;
    }

    public void setPresentarEvaluacion(boolean presentarEvaluacion) {
        this.presentarEvaluacion = presentarEvaluacion;
    }

    public boolean isDialogConfirmar() {
        return dialogConfirmar;
    }

    public void setDialogConfirmar(boolean dialogConfirmar) {
        this.dialogConfirmar = dialogConfirmar;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<IappMatriculas> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<IappMatriculas> matriculas) {
        this.matriculas = matriculas;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
}
