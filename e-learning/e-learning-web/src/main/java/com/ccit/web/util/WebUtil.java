/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.util;

import com.ccit.ejb.dto.UsuarioDTo;
import java.io.*;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marino
 */
public class WebUtil {

    private static ResourceBundle bundle = ResourceBundle.getBundle("dynamic");
    public static final String FILES_PATH = bundle.getString("path_files");

    /**
     * Verifica que la extension del recurso solicitado sea un tipo soportado
     *
     * @param uriSolicitada Uri solicitado
     * @return
     * <code>true<code> si es permitida y
     * <code>false<code> de lo
     *           contrario
     */
    public static final boolean esExtensionPermitidaPorGet(String uriSolicitada) {
        return (uriSolicitada.endsWith("js"))
                || (uriSolicitada.endsWith("css.xhtml"))
                || (uriSolicitada.endsWith("js.xhtml"))
                || (uriSolicitada.endsWith("jpg"))
                || (uriSolicitada.endsWith("jpeg"))
                || (uriSolicitada.endsWith("bmp"))
                || (uriSolicitada.endsWith("ico"))
                || (uriSolicitada.endsWith("gif"))
                || (uriSolicitada.endsWith("pdf"))
                || (uriSolicitada.endsWith("png"))
                || (uriSolicitada.endsWith("png.xhtml"))
                || (uriSolicitada.endsWith("css"));
    }

    /**
     * Verifica que el metodo de ejecucion de la peticion este soportado. La
     * pagina solo debe soportar GET y POST
     */
    public static boolean esValidoMetodo(String metodo) {
        boolean puedePasar = false;
        if (metodo.equals("POST")
                || metodo.equalsIgnoreCase("POST")) {
            puedePasar = true;
        } else if (metodo.equals("GET")
                || metodo.equalsIgnoreCase("GET")) {
            puedePasar = true;
        } else {
            // para todos los demas metodos no puede pasar (TRACE, PUT, DELETE, OPTIONS)
            puedePasar = false;
        }
        return puedePasar;
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    public static void setObjectInSession(String id, Object obj) {
        getRequest().getSession().setAttribute(id, obj);
    }

    public static Object getObjectFromSession(String id) {
        return getRequest().getSession().getAttribute(id);
    }

    public static Object getManageBeanFromSession(String id, Class e) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().evaluateExpressionGet(context, "#{" + id + "}", e);
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static byte[] fileToByteArray(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        long length = file.length();
        byte[] bytes = new byte[(int) length];
        is.read(bytes);
        is.close();
        return bytes;
    }

    public static String writeFile(byte[] bytes, String name) throws IOException {

        try {
            FileOutputStream fileOut = new FileOutputStream(FILES_PATH + "/" + name);
            fileOut.write(bytes);
            fileOut.close();
        } catch (IOException ex) {

            ex.printStackTrace();
        }
        return name;
    }

    public static byte[] readFile(String name) {
        try {
            File file = new File(FILES_PATH + "/" + name);
            InputStream is = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            is.read(bytes);
            is.close();
            return bytes;
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public static String getContentType(String fileName) {
        String contentType = "application/octecstream";
        if (fileName.endsWith(".pdf")) {
            contentType = "application/pdf";
        } else if (fileName.endsWith(".docx")) {
            contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        } else if (fileName.endsWith(".doc")) {
            contentType = "application/msword";
        } else if (fileName.endsWith(".pptx")) {
            contentType = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
        } else if (fileName.endsWith(".ppt")) {
            contentType = "application/msword";
        } else if (fileName.endsWith(".xlsx")) {
            contentType = " application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        } else if (fileName.endsWith(".xls")) {
            contentType = "application/msword";
        } else if (fileName.endsWith(".mp3")) {
            contentType = "application/mp3";
        }

        return contentType;
    }
    
    public static UsuarioDTo getUsuarioInsession(){
        return (UsuarioDTo) WebUtil.getObjectFromSession("userLogged");
    }
}
