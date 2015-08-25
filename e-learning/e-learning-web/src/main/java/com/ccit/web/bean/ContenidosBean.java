/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.constants.Constants;
import com.ccit.ejb.dto.UsuarioDTo;
import com.ccit.ejb.fachada.ContenidosFacade;
import com.ccit.ejb.modelo.IappContenidos;
import com.ccit.ejb.modelo.IappModulos;
import com.ccit.exception.IappException;
import com.ccit.web.util.WebUtil;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
public class ContenidosBean implements Serializable {

    @EJB
    ContenidosFacade contenidosFacade;
    private IappModulos editModule;
    private IappContenidos contenido;
    private String messaje;
    private boolean verConfirmacion, verMensaje;
    private boolean nuevoContenidoDialog, verContenidoDialog, reproducirAudio, reproducirVideo;
    private UploadedFile uploadFile;
    private StreamedContent downloadFile;

    /**
     * Creates a new instance of UsuariosBean
     */
    public ContenidosBean() {
    }

    @PostConstruct
    public void init() {
        Logger.getLogger(ContenidosBean.class.getName()).log(Level.INFO, "Inicia Contenido Bean");
         contenido = new IappContenidos();
    }

    public void eliminarContenido() {
        try {
            editModule.getIappContenidosCollection().remove(contenido);
            contenidosFacade.remove(contenido);
            setVerConfirmacion(false);
        } catch (IappException ex) {
            Logger.getLogger(ContenidosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardar() {
        if (contenido.getFile() == null) {
            setMessaje("Debe adjuntar por lo menos un archivo.");
            setVerMensaje(true);
        } else {
            if (!editModule.getIappContenidosCollection().contains(contenido)) {
                UsuarioDTo u = (UsuarioDTo) WebUtil.getObjectFromSession("userLogged");
                if (contenido.getFechaCreacion() == null) {
                    contenido.setUsuarioCreacion(u.getLogin());
                    contenido.setFechaCreacion(Calendar.getInstance().getTime());
                    contenido.setEstadoRegistro(Constants.REGISTRO_ACTIVO);
                }
                contenido.setUsuarioModificacion(u.getLogin());
                contenido.setFechaModificacion(Calendar.getInstance().getTime());
                editModule.getIappContenidosCollection().add(contenido);
            }
            try {
                if (contenido.getIdContenido() == null) {
                    contenidosFacade.crearContenido(contenido);
                } else {
                    contenidosFacade.merge(contenido);
                }
                if (contenido != null && contenido.getFileArray() != null) {
                    WebUtil.writeFile(contenido.getFileArray(), contenido.getFile());
                }
                setMessaje("La informacion ha sido actualizada exitosamente.");
                setVerMensaje(true);
            } catch (IOException ex) {
                Logger.getLogger(ContenidosBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IappException ex) {
                setMessaje("Error al guardar los archivos en la base de datos.");
                setVerMensaje(true);
            }
            setNuevoContenidoDialog(false);
        }
    }

    public StreamedContent getFile() {
        StreamedContent content = null;
        File fileContenido = new File(WebUtil.FILES_PATH + "/" + contenido.getFile());
        if (fileContenido.exists()) {
            try {
                FileInputStream stream = new FileInputStream(fileContenido);
                String contentType = "";
                if (this.contenido.getFile().endsWith(".pdf")) {
                    contentType = "application/pdf";
                } else if (this.contenido.getFile().endsWith(".docx")) {
                    contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
                } else if (this.contenido.getFile().endsWith(".doc")) {
                    contentType = "application/msword";
                } else if (this.contenido.getFile().endsWith(".pptx")) {
                    contentType = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
                } else if (this.contenido.getFile().endsWith(".ppt")) {
                    contentType = "application/msword";
                } else if (this.contenido.getFile().endsWith(".xlsx")) {
                    contentType = " application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                } else if (this.contenido.getFile().endsWith(".xls")) {
                    contentType = "application/msword";
                }
                else if (this.contenido.getFile().endsWith(".mp3")) {
                    contentType = "application/mp3";
                }
                content = new DefaultStreamedContent(stream,contentType,contenido.getFile());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ContenidosBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Logger.getLogger(ContenidosBean.class.getName()).log(Level.INFO, "contenido no existe");
        }

        return content;
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
                contenido.setFileArray(this.getUploadFile().getContents());
                contenido.setFile(name);
                contenido.setTitulo(this.getUploadFile().getFileName());
                Logger.getLogger(TrabajosBean.class.getName()).log(Level.INFO, "Nombre {0}", this.getUploadFile().getFileName());
            } else {
                Logger.getLogger(TrabajosBean.class.getName()).log(Level.INFO, "Error al adjuntar el archivo");
            }
        } catch (IOException ex) {
            Logger.getLogger(TrabajosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void aceptar() {
        setMessaje("");
        setVerMensaje(false);
    }

    public void createContenido() {
        contenido = new IappContenidos();
        contenido.setIdModulo(editModule);
        setNuevoContenidoDialog(true);
    }

    public void editContenido() {
        setNuevoContenidoDialog(true);

    }

//    public void fileContentEntryListener(FileEntryEvent e) {
//        FileEntry fe = (FileEntry) e.getComponent();
//        FileEntryResults results = fe.getResults();
//        UsuarioDTo u = (UsuarioDTo) WebUtil.getObjectFromSession("userLogged");
//        try {
//            for (FileEntryResults.FileInfo i : results.getFiles()) {
//                File file = i.getFile();
//                SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//                String name = sf.format(new Date()) + file.getName();
//                contenido.setFileArray(WebUtil.fileToByteArray(file));
//                contenido.setFile(name);
//                contenido.setTitulo(file.getName());
//            }
//            this.verContenido();
//            setVerContenidoDialog(false);
//
//        } catch (IOException ex) {
//        }
//    }
    public void hideVerContenido() {
        setVerContenidoDialog(false);

    }

    public void hideVerContenidoAudio() {
        setReproducirAudio(false);
    }

    public void hideVerContenidoVideo() {
        setReproducirVideo(false);
    }

    public void confirmar() {
        setVerConfirmacion(true);
    }

    public void hideConfirmar() {
        setVerConfirmacion(false);
    }

    public void hideCrearContenido() {
        setNuevoContenidoDialog(false);
    }

    public void verContenidoAudio() {
        String file = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources");
        System.out.println(file);
        File f = new File(file + "/swf/" + contenido.getFile());
        if (!f.exists()) {

            try {
                FileInputStream in = new FileInputStream("/usr/local/work/" + contenido.getFile());
                byte[] infile = new byte[in.available()];
                in.read(infile);
                FileOutputStream fileOut = new FileOutputStream(file + "/swf/" + contenido.getFile());
                fileOut.write(infile);
                fileOut.close();
            } catch (IOException ex) {

                ex.printStackTrace();
            }

        }
        setReproducirAudio(true);
    }

    public void verContenidoVideo() {
        String file = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources");
        System.out.println(file);
        File f = new File(file + "/swf/" + contenido.getFile());
        if (!f.exists()) {
            try {
                FileInputStream in = new FileInputStream("/usr/local/work/" + contenido.getFile());
                byte[] infile = new byte[in.available()];
                in.read(infile);
                FileOutputStream fileOut = new FileOutputStream(file + "/swf/" + contenido.getFile());
                fileOut.write(infile);
                fileOut.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        setReproducirVideo(true);
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

    public void verContenido() {
        setVerContenidoDialog(true);
    }

    public IappModulos getEditModule() {
        return editModule;
    }

    public void setEditModule(IappModulos editModule) {
        this.editModule = editModule;
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

    public IappContenidos getContenido() {
        return contenido;
    }

    public void setContenido(IappContenidos contenido) {
        this.contenido = contenido;
    }

    public boolean isNuevoContenidoDialog() {
        return nuevoContenidoDialog;
    }

    public void setNuevoContenidoDialog(boolean nuevoContenidoDialog) {
        this.nuevoContenidoDialog = nuevoContenidoDialog;
    }

    public boolean isVerContenidoDialog() {
        return verContenidoDialog;
    }

    public void setVerContenidoDialog(boolean verContenidoDialog) {
        this.verContenidoDialog = verContenidoDialog;
    }

    public boolean isReproducirAudio() {
        return reproducirAudio;
    }

    public void setReproducirAudio(boolean reproducirAudio) {
        this.reproducirAudio = reproducirAudio;
    }

    public boolean isReproducirVideo() {
        return reproducirVideo;
    }

    public void setReproducirVideo(boolean reproducirVideo) {
        this.reproducirVideo = reproducirVideo;
    }
}
