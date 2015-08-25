/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean.streaming;

import com.ccit.ejb.constants.Constants;
import com.ccit.ejb.fachada.impl.IappStreamingEstudianteFacade;
import com.ccit.ejb.fachada.impl.IappStreamingFacade;
import com.ccit.ejb.modelo.IappCursos;
import com.ccit.ejb.modelo.IappStreaming;
import com.ccit.ejb.modelo.IappStreamingEstudiante;
import com.ccit.ejb.modelo.IappUsuario;
import com.ccit.exception.IappException;
import com.ccit.web.util.BigBlueButtonAPI;
import com.ccit.web.util.WebUtil;
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
public class CreateStreamingBean {

    @EJB
    private IappStreamingEstudianteFacade iappStreamingEstudianteFacade;
    @EJB
    private IappStreamingFacade iappStreamingFacade;
    private String moderatorUrl;
    private String estudianteUrl;
    private boolean mostrarCrearPopup = false;
    private boolean mostrarInciarSesionEstudiante = false;
    private boolean mostrarInscribirEstudiantes = false;
    private boolean mostrarEstudiantesCurso = false;
    private String nombreClase;
    private Date fechaSesion;
    private List<IappStreaming> listadoClases;
    private List<IappStreamingEstudiante> listadoEstudiantes;
    private List<IappStreamingEstudiante> listadoClasesEstudiantes;
    private IappStreaming claseSelected;
    private IappCursos curso;
    private IappUsuario estudiante;

    @PostConstruct
    public void init() {
        System.out.println("Init create Streaming");
        //consultarClasesOnline();
    }

    public void consultarClasesOnline() {
        listadoClases = iappStreamingFacade.consultarClasesOnline(WebUtil.getUsuarioInsession().getIdUsuario(),
                curso.getIdCurso());
    }

    public void cerrarEstudiantes() {
        this.setMostrarEstudiantesCurso(false);
    }

    public void mostrarEstudiantes() {
        this.setMostrarEstudiantesCurso(true);
    }

    public void cerrarInscribirEstudiantes() {
        this.setMostrarInscribirEstudiantes(false);
    }

    public void mostrarInscribirEstudiantes() {
        consultarEstudiantes();
        this.setMostrarInscribirEstudiantes(true);
    }

    public void inscribirEstudiante(IappUsuario usuario) {

        IappStreamingEstudiante nuevoEstudiante = new IappStreamingEstudiante();
        nuevoEstudiante.setIdUsuario(usuario);
        nuevoEstudiante.setIdStreaming(claseSelected);
        try {
            iappStreamingEstudianteFacade.create(nuevoEstudiante);
            consultarEstudiantes();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Estudiante Inscrito en clase"));
        } catch (IappException ex) {
            Logger.getLogger(CreateStreamingBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void consultarClasesEstudiante(){
        listadoClasesEstudiantes = iappStreamingEstudianteFacade.consultarClasesEstudiantes(
                curso.getIdCurso(), WebUtil.getUsuarioInsession().getIdUsuario());
    }
    
    public void consultarEstudiantes() {
        listadoEstudiantes = iappStreamingEstudianteFacade.consultarEstudiantesInscritos(curso.getIdCurso(), claseSelected.getIdStreaming());
    }

    public void crearSesionOnline() {
        try {
            BigBlueButtonAPI api = new BigBlueButtonAPI();
            // String joinUrl = api.getJoinURL(WebUtil.getUsuarioInsession().getNombre(), nombreClase, "false", "<br>Welcome to %%CONFNAME%%.<br>", null, null);

            IappStreaming streaming = new IappStreaming();
            streaming.setIdMeeting(nombreClase);
            streaming.setNombreMeeting(nombreClase);
            // streaming.setUrlJoinModerator(joinUrl);
            streaming.setIdUsuario(new IappUsuario(WebUtil.getUsuarioInsession().getIdUsuario()));
            streaming.setEstado(Constants.ESTADO_ONLINE_CREADO);
            streaming.setIdCurso(curso);
            streaming.setFechaCreacion(new Date());
            streaming.setFechaSesion(fechaSesion);
            iappStreamingFacade.create(streaming);
            clear();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Sesión creada correctamente"));

//            this.setModeratorUrl(joinUrl);
//            this.setMostrarCrearPopup(true);
        } catch (IappException ex) {
            Logger.getLogger(CreateStreamingBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void clear() {
        if (listadoClases != null) {
            this.listadoClases.clear();
        }
        if (nombreClase != null) {
            this.nombreClase = "";
        }
        this.fechaSesion = null;
    }

    public String crearSesion() {
        clear();
        return "crearSesionOnline";
    }
    
    public String consultarClasesEstudianteOnline(){
        consultarClasesEstudiante();
        return "consultarSesionOnline";
    }
    
    

    public void iniciarSesionEstudiante(){
       BigBlueButtonAPI api = new BigBlueButtonAPI();        
       String urlJoin =  api.getJoinURLViewer(WebUtil.getUsuarioInsession().getNombre(), claseSelected.getIdMeeting());
       this.setEstudianteUrl(urlJoin);
       this.setMostrarInciarSesionEstudiante(true);
    }
    
    public void cerrarSesionEstudiante(){
        this.setMostrarInciarSesionEstudiante(false); 
    }
    
    public void iniciarSesion() {
        BigBlueButtonAPI api = new BigBlueButtonAPI();
        String joinUrl = api.getJoinURL(WebUtil.getUsuarioInsession().getNombre(), claseSelected.getIdMeeting(), "false", "<br>Welcome to %%CONFNAME%%.<br>", null, null);

        claseSelected.setEstado(Constants.ESTADO_ONLINE_INICIADO);
        claseSelected.setUrlJoinModerator(joinUrl);
        try {
            iappStreamingFacade.edit(claseSelected);
            this.setModeratorUrl(joinUrl);
            this.setMostrarCrearPopup(true);
        } catch (IappException ex) {
            Logger.getLogger(CreateStreamingBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setModeratorUrl(claseSelected.getUrlJoinModerator());
        this.setMostrarCrearPopup(true);
    }

    public void cerrarSesion() {
        
        this.setMostrarCrearPopup(false);
    }

    public void terminarSesion() {
        claseSelected.setEstado(Constants.ESTADO_ONLINE_FINALIZADO);
        try {
            BigBlueButtonAPI api = new BigBlueButtonAPI();
            iappStreamingFacade.edit(claseSelected);
            String result = api.endMeeting(claseSelected.getIdMeeting(), "mp");
            System.out.println(result);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Clase Finalizada Exitosamente"));
        } catch (IappException ex) {
            Logger.getLogger(CreateStreamingBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getModeratorUrl() {
        return moderatorUrl;
    }

    public void setModeratorUrl(String moderatorUrl) {
        this.moderatorUrl = moderatorUrl;
    }

    public boolean isMostrarCrearPopup() {
        return mostrarCrearPopup;
    }

    public void setMostrarCrearPopup(boolean mostrarCrearPopup) {
        this.mostrarCrearPopup = mostrarCrearPopup;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public List<IappStreaming> getListadoClases() {
        return listadoClases;
    }

    public void setListadoClases(List<IappStreaming> listadoClases) {
        this.listadoClases = listadoClases;
    }

    public IappCursos getCurso() {
        return curso;
    }

    public IappStreaming getClaseSelected() {
        return claseSelected;
    }

    public void setClaseSelected(IappStreaming claseSelected) {
        this.claseSelected = claseSelected;
    }

    public void setCurso(IappCursos curso) {
        this.curso = curso;
    }

    public List<IappStreamingEstudiante> getListadoEstudiantes() {
        return listadoEstudiantes;
    }

    public void setListadoEstudiantes(List<IappStreamingEstudiante> listadoEstudiantes) {
        this.listadoEstudiantes = listadoEstudiantes;
    }

    public boolean isMostrarInscribirEstudiantes() {
        return mostrarInscribirEstudiantes;
    }

    public void setMostrarInscribirEstudiantes(boolean mostrarInscribirEstudiantes) {
        this.mostrarInscribirEstudiantes = mostrarInscribirEstudiantes;
    }

    public boolean isMostrarEstudiantesCurso() {
        return mostrarEstudiantesCurso;
    }

    public void setMostrarEstudiantesCurso(boolean mostrarEstudiantesCurso) {
        this.mostrarEstudiantesCurso = mostrarEstudiantesCurso;
    }

    public Date getFechaSesion() {
        return fechaSesion;
    }

    public void setFechaSesion(Date fechaSesion) {
        this.fechaSesion = fechaSesion;
    }

    public List<IappStreamingEstudiante> getListadoClasesEstudiantes() {
        return listadoClasesEstudiantes;
    }

    public void setListadoClasesEstudiantes(List<IappStreamingEstudiante> listadoClasesEstudiantes) {
        this.listadoClasesEstudiantes = listadoClasesEstudiantes;
    }

    public String getEstudianteUrl() {
        return estudianteUrl;
    }

    public void setEstudianteUrl(String estudianteUrl) {
        this.estudianteUrl = estudianteUrl;
    }

    public boolean isMostrarInciarSesionEstudiante() {
        return mostrarInciarSesionEstudiante;
    }

    public void setMostrarInciarSesionEstudiante(boolean mostrarInciarSesionEstudiante) {
        this.mostrarInciarSesionEstudiante = mostrarInciarSesionEstudiante;
    }
    
    
}

