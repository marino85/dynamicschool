/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.constants.Constants;
import com.ccit.ejb.dto.UsuarioDTo;
import com.ccit.ejb.fachada.CursosFacade;
import com.ccit.ejb.fachada.TrabajosFacade;
import com.ccit.ejb.modelo.*;
import com.ccit.exception.IappException;
import com.ccit.web.util.WebUtil;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author marino
 */
@ManagedBean
@SessionScoped
public class TrabajosBean implements Serializable {

    @EJB
    TrabajosFacade trabajosFacade;
    @EJB
    CursosFacade cursoFacade;
    private IappSprints editModule;
    private IappHomeWorks trabajo;
    private IappQualificationHomeWorks calificacion;
    private IappSentFiles entrega;
    private String messaje;
    private boolean verTrabajoDialog;
    private boolean verConfirmacion, verMensaje, verCalificacion;
    private boolean nuevoTrabajoDialog;
    private IappEnrollments matricula;
    private UploadedFile uploadFile;
    private StreamedContent downloadFile;
    private List<IappEnrollments> matriculas = new ArrayList<IappEnrollments>();
    private String codigo;
    private String nombres;

    /**
     * Creates a new instance of UsuariosBean
     */
    public TrabajosBean() {
        
    }

    @PostConstruct
    public void init() {
        trabajo = new IappHomeWorks();
        
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Init Trabajos Bean");
    }

    public void buscarEstudiantes(){
            matriculas = cursoFacade.findEstudentsByCourse(this.editModule.getIdCurso().getIdCurso(), codigo, nombres);
    }
    
    public void eliminarTrabajo() {
        try {
            editModule.getIappAsignacionesCollection().remove(trabajo);
            trabajosFacade.remove(trabajo);
            setVerConfirmacion(false);
        } catch (IappException ex) {
            Logger.getLogger(TrabajosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardar() {
        if (trabajo.getFile() == null) {
            setMessaje("Debe adjuntar por lo menos un archivo.");
            setVerMensaje(true);
        } else {
            if (!editModule.getIappAsignacionesCollection().contains(trabajo)) {
                UsuarioDTo u = (UsuarioDTo) WebUtil.getObjectFromSession("userLogged");
                if (trabajo.getFechaCreacion() == null) {
                    trabajo.setUsuarioCreacion(u.getLogin());
                    trabajo.setFechaCreacion(Calendar.getInstance().getTime());
                    trabajo.setEstadoRegistro(Constants.REGISTRO_ACTIVO);
                }
                trabajo.setUsuarioModificacion(u.getLogin());
                trabajo.setFechaModificacion(Calendar.getInstance().getTime());
                editModule.getIappAsignacionesCollection().add(trabajo);
            }
            try {
                if (trabajo.getIdAsignacion() == null) {
                    trabajosFacade.crearTrabajo(trabajo);

                } else {
                    trabajosFacade.merge(trabajo);
                }
                if (trabajo != null && trabajo.getFileArray() != null) {
                    WebUtil.writeFile(trabajo.getFileArray(), trabajo.getFile());
                }
                setMessaje("La informacion ha sido actualizada exitosamente.");
                setVerMensaje(true);
            } catch (IOException ex) {
                Logger.getLogger(TrabajosBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IappException ex) {
                setMessaje("Error al guardar los archivos en la base de datos.");
                setVerMensaje(true);
            }
            setNuevoTrabajoDialog(false);
        }
    }

    public void enviarTrabajo() {
        if (!trabajo.getIappEntregasCollection().isEmpty()) {
            try {
                trabajosFacade.guardarEntregas(trabajo.iappEntregasFromUserCollection(matricula));
                for (IappSentFiles ent : trabajo.iappEntregasFromUserCollection(matricula)) {
                    WebUtil.writeFile(ent.getFileArray(), ent.getFile());
                }
                setMessaje("El trabajo ha sido enviado exitosamente.");
            } catch (IOException ex) {
                Logger.getLogger(TrabajosBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IappException ex) {
                setMessaje("Error al guardar los archivos en la base de datos.");
            }
            setVerMensaje(true);
            setVerTrabajoDialog(false);
        } else {
            setMessaje("Debe adjuntar por lo menos un archivo.");
            setVerMensaje(true);
        }
    }

    public void aceptar() {
        setMessaje("");
        setVerMensaje(false);
    }

    public StreamedContent descargarArchivo() {
        StreamedContent file = null;
        try {
            WebUtil.getSession().setAttribute("fileName", this.entrega.getFile());
            WebUtil.getSession().setAttribute("Name", this.entrega.getNombre());
            String contentType = WebUtil.getContentType(this.entrega.getFile());
            
            WebUtil.getSession().setAttribute("contentType", contentType);
            ByteArrayInputStream in = new ByteArrayInputStream(WebUtil.readFile(this.entrega.getFile()));
            file = new DefaultStreamedContent(in, contentType, this.entrega.getNombre());
            //FacesContext.getCurrentInstance().getExternalContext().redirect("Download");
        } catch (Exception ex) {
            Logger.getLogger(ReportesHorariosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return file;

    }

    public void createTrabajo() {
        trabajo = new IappHomeWorks();
        trabajo.setIdModulo(editModule);
        setNuevoTrabajoDialog(true);
    }

    public void editTrabajo() {
        
        ByteArrayInputStream in = new ByteArrayInputStream(WebUtil.readFile(this.trabajo.getFile()));        
         this.downloadFile = new DefaultStreamedContent(in,
                        WebUtil.getContentType(this.trabajo.getFile()),
                        this.trabajo.getFile());       
        
        setNuevoTrabajoDialog(true);
        // setVerTrabajoDialog(true);
    }

    public void verEntregas() {
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            if (event.getFile() != null) {
                this.setUploadFile(event.getFile());
                this.downloadFile = new DefaultStreamedContent(this.getUploadFile().getInputstream(),
                        this.getUploadFile().getContentType(),
                        this.getUploadFile().getFileName());
                SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                String name = sf.format(new Date()) + this.getUploadFile().getFileName();
                trabajo.setFileArray(this.getUploadFile().getContents());
                trabajo.setFile(name);
                trabajo.setNombreFile(this.getUploadFile().getFileName());
                Logger.getLogger(TrabajosBean.class.getName()).log(Level.INFO, "Nombre {0}", this.getUploadFile().getFileName());
            } else {
                Logger.getLogger(TrabajosBean.class.getName()).log(Level.INFO, "Error al adjuntar el archivo");
            }
        } catch (IOException ex) {
            Logger.getLogger(TrabajosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void handleFileUploadEstudiante(FileUploadEvent event) {
        try {
            UsuarioDTo u = (UsuarioDTo) WebUtil.getObjectFromSession("userLogged");
            if (event.getFile() != null) {
               
                
                SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");             
                
                IappSentFiles ent = new IappSentFiles();
                ent.setDescripcion((event.getFile().getSize()) + "kb");
                ent.setEstadoRegistro(Constants.REGISTRO_ACTIVO);
                ent.setFechaCreacion(Calendar.getInstance().getTime());
                ent.setFechaEntrega(Calendar.getInstance().getTime());
                ent.setFechaModificacion(Calendar.getInstance().getTime());
                ent.setIdAsignacion(trabajo);
                ent.setIdMatricula(matricula);
                ent.setNombre(event.getFile().getFileName());
                ent.setDescripcion("Descripcion");
                ent.setUsuarioCreacion(u.getLogin());
                ent.setUsuarioModificacion(u.getLogin());  
                ent.setEstadoRegistro("A");
                String name = sf.format(new Date()) + event.getFile().getFileName();
                ent.setFile(name);
                ent.setFileArray(event.getFile().getContents());
                trabajo.getIappEntregasCollection().add(ent);
                Logger.getLogger(TrabajosBean.class.getName()).log(Level.INFO, "Nombre {0}", event.getFile().getFileName());
            } else {
                Logger.getLogger(TrabajosBean.class.getName()).log(Level.INFO, "Error al adjuntar el archivo");
            }
        } catch (Exception ex) {
            Logger.getLogger(TrabajosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public StreamedContent getCurrentUploadedFile(IappSentFiles entrega) {
        
        try {
            String contentType = WebUtil.getContentType(entrega.getFile());
           
            ByteArrayInputStream in = 
                    new  ByteArrayInputStream(entrega.getFileArray());
           
            StreamedContent file = new DefaultStreamedContent(in,
                    contentType, entrega.getFile());
            return file;
        } catch (Exception ex) {
            Logger.getLogger(TrabajosBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }

    public StreamedContent getCurrentFile() {
        
        try {
            String contentType = WebUtil.getContentType(this.trabajo.getFile());
            
            ByteArrayInputStream in = 
                    new  ByteArrayInputStream(WebUtil.readFile(trabajo.getFile()));
           
            StreamedContent file = new DefaultStreamedContent(in,
                    contentType, this.trabajo.getFile());
            return file;
        } catch (Exception ex) {
            Logger.getLogger(TrabajosBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }

//    public void fileWorkEntryListener(FileEntryEvent e) {
//        FileEntry fe = (FileEntry) e.getComponent();
//        FileEntryResults results = fe.getResults();
//        UsuarioDTo u = (UsuarioDTo) WebUtil.getObjectFromSession("userLogged");
//        try {
//            for (FileEntryResults.FileInfo i : results.getFiles()) {
//                File file = i.getFile();
//                SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//                String name = sf.format(new Date()) + file.getName();
//                trabajo.setFileArray(WebUtil.fileToByteArray(file));
//                trabajo.setFile(name);
//                trabajo.setNombreFile(file.getName());
//            }
//            this.verTrabajo();
//            setVerTrabajoDialog(false);
//
//        } catch (IOException ex) {
//        }
//    }
//    public void fileEntryListener(FileEntryEvent e) {
//        FileEntry fe = (FileEntry) e.getComponent();
//        FileEntryResults results = fe.getResults();
//        UsuarioDTo u = (UsuarioDTo) WebUtil.getObjectFromSession("userLogged");
//        try {
//            for (FileEntryResults.FileInfo i : results.getFiles()) {
//                File file = i.getFile();
//                IappSentFiles ent = new IappSentFiles();
//                ent.setDescripcion((i.getSize() / 1000) + "kb");
//                ent.setEstadoRegistro(Constants.REGISTRO_ACTIVO);
//                ent.setFechaCreacion(Calendar.getInstance().getTime());
//                ent.setFechaEntrega(Calendar.getInstance().getTime());
//                ent.setFechaModificacion(Calendar.getInstance().getTime());
//                ent.setIdAsignacion(trabajo);
//                ent.setIdMatricula(matricula);
//                ent.setNombre(i.getFileName());
//                ent.setUsuarioCreacion(u.getLogin());
//                ent.setUsuarioModificacion(u.getLogin());
//                SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//                String name = sf.format(new Date()) + file.getName();
//                ent.setFile(name);
//                ent.setFileArray(WebUtil.fileToByteArray(file));
//                trabajo.getIappEntregasCollection().add(ent);
//            }
//        } catch (IOException ex) {
//        }
//    }
    public void calificarTrabajo(IappEnrollments matricula) {
        UsuarioDTo u = (UsuarioDTo) WebUtil.getObjectFromSession("userLogged");
        if (getTrabajo().calificacion(matricula) != null) {
            calificacion = getTrabajo().calificacion(matricula);
        } else {
            calificacion = new IappQualificationHomeWorks();
            calificacion.setIdMatricula(matricula);
            calificacion.setIdAsignacion(trabajo);
            calificacion.setEstadoRegistro(Constants.REGISTRO_ACTIVO);
            calificacion.setFechaCreacion(new Date());
            calificacion.setFechaModificacion(new Date());
            calificacion.setUsuarioCreacion(u.getLogin());
            calificacion.setUsuarioModificacion(u.getLogin());
        }


        this.setVerCalificacion(true);
    }

    public void guardarCalificacion() {
        try {
            trabajosFacade.calificarTrabajo(calificacion);
            trabajo.getIappCalAsignacionesCollection().add(calificacion);
            this.setVerCalificacion(false);
        } catch (IappException ex) {
            Logger.getLogger(TrabajosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public StreamedContent getDownloadFile() {
        return downloadFile;
    }

    public void setDownloadFile(StreamedContent downloadFile) {
        this.downloadFile = downloadFile;
    }

    public UploadedFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(UploadedFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public void hideVerCalificacion() {
        this.setVerCalificacion(false);
    }

    public void hideVerTrabajo() {
        setVerTrabajoDialog(false);
        trabajo.setIappEntregasCollection(new ArrayList<IappSentFiles>());
    }

    public void confirmar() {
        setVerConfirmacion(true);
    }

    public void hideConfirmar() {
        setVerConfirmacion(false);
    }

    public void hideCrearTrabajo() {
        this.uploadFile = null;
        this.downloadFile = null;
        trabajo.setFileArray(null);
        trabajo.setFile(null);
        trabajo.setNombreFile(null);
        setNuevoTrabajoDialog(false);
    }

    public void verTrabajo() {
        setVerTrabajoDialog(true);
    }

    public IappSprints getEditModule() {
        return editModule;
    }

    public void setEditModule(IappSprints editModule) {
        this.editModule = editModule;
    }

    public IappHomeWorks getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(IappHomeWorks trabajo) {
        this.trabajo = trabajo;
        if(trabajo.getIdModulo()!=null){
        matriculas= new ArrayList<IappEnrollments>(this.trabajo.getIdModulo().getIdCurso().getIappMatriculasCollection());
        }
    }

    public String getMessaje() {
        return messaje;
    }

    public void setMessaje(String messaje) {
        this.messaje = messaje;
    }

    public boolean isVerTrabajoDialog() {
        return verTrabajoDialog;
    }

    public void setVerTrabajoDialog(boolean verTrabajoDialog) {
        this.verTrabajoDialog = verTrabajoDialog;
    }

    public IappEnrollments getMatricula() {
        return matricula;
    }

    public void setMatricula(IappEnrollments matricula) {
        this.matricula = matricula;
    }

    public boolean isVerConfirmacion() {
        return verConfirmacion;
    }

    public void setVerConfirmacion(boolean verConfirmacion) {
        this.verConfirmacion = verConfirmacion;
    }

    public boolean isNuevoTrabajoDialog() {
        return nuevoTrabajoDialog;
    }

    public void setNuevoTrabajoDialog(boolean nuevoTrabajoDialog) {
        this.nuevoTrabajoDialog = nuevoTrabajoDialog;
    }

    public boolean isVerMensaje() {
        return verMensaje;
    }

    public void setVerMensaje(boolean verMensaje) {
        this.verMensaje = verMensaje;
    }

    public IappSentFiles getEntrega() {
        return entrega;
    }

    public void setEntrega(IappSentFiles entrega) {
        this.entrega = entrega;
    }

    public IappQualificationHomeWorks getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(IappQualificationHomeWorks calificacion) {
        this.calificacion = calificacion;
    }

    public boolean isVerCalificacion() {
        return verCalificacion;
    }

    public void setVerCalificacion(boolean verCalificacion) {
        this.verCalificacion = verCalificacion;
    }

    public List<IappEnrollments> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<IappEnrollments> matriculas) {
        this.matriculas = matriculas;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    
    
}
