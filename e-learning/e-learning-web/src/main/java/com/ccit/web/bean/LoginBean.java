/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.constants.Constants;
import com.ccit.ejb.dto.UsuarioDTo;
import com.ccit.ejb.fachada.GeneralFacadeBean;
import com.ccit.ejb.fachada.UsuariosFacade;
import com.ccit.ejb.utilidades.EncrytUtil;
import com.ccit.exception.IappException;
import com.ccit.web.util.WebUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


/**
 *
 * @author marino
 */
@ManagedBean
@SessionScoped
public class LoginBean {

    @EJB
    private GeneralFacadeBean generalFacadeBean;
    @EJB
    private UsuariosFacade usuariosFacade;
    @EJB
    private com.ccit.ejb.fachada.LoginBean loginBean;
    private StreamedContent imageBanner;
    private String user;
    private String password;
    private String currentPasswd;
    private String newPasswd;
    private String reTypePasswd;
    private String message;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    @PostConstruct
    public void init() {
        System.out.println("INIT LoginBean");
    }

    public void resetFields() {
        this.user = "";
        this.password = "";
    }

    public String login() {

        UsuarioDTo u = null;

        u = loginBean.login(user, password);

        if (u != null) {

            if (u.getIdPerfil().equals(Constants.PERFIL_ESTUDIANTE)) {
                if (u.getIndicadorTiempo().equals("S")) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Por favor comuniquese con el área administrativa"));
                    this.setMessage("Por favor comuniquese con el área administrativa");
                    resetFields();
                    return "login";
                }
                //validamos con shollpack la deuda el estudiante
//                try {
//                    schoolpackev1dynamics.EstadoExecute parameter = new schoolpackev1dynamics.EstadoExecute();
//                    parameter.setDocumento(u.getNumDoc());
//
//                    if (execute(parameter).getDatoestado().getValorDeuda() > 0) {
//                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Señor estudiante no puede tener acceso al portal por encontrarse en mora con los pagos de mensualidades, por favor comunicarse al  2124223 Extensión 107"));
//                    this.setMessage("Señor estudiante no puede tener acceso al portal por encontrarse en mora con los pagos de mensualidades, por favor comunicarse al  2124223 Extensión 107");
//                    resetFields();
//                    return "login";
//                    }
//                } catch (Exception e) {
//                    System.out.println("Error al consumir school pack");
//                }

            }


            u.setPass(EncrytUtil.encrypPwd(password));
            WebUtil.setObjectInSession("userLogged", u);


            if (u.getCont() == 0) {
                System.out.println("Inicia session usuario " + u.getNumDoc());
                resetFields();
                return "changePass";
            } else {
//                try {
//                    this.setUser("");
//                    this.setPassword("");
//                    generalFacadeBean.sendMail("ew943@hotmail.com", "Ingreso", "Ud ha ingreso a l portal ");
//                    return "main";
//                } catch (NamingException ex) {
//                    Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
//                    return "main";
//                } catch (MessagingException ex) {
//                    Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
//                    return "main";
//                }
                System.out.println("Inicia session usuario " + u.getNumDoc());
                resetFields();
                this.setMessage("");
                return "main";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al autenticar el usuario"));
            this.setMessage("Error al autenticar el usuario");
            resetFields();
            return "login";
        }
    }

    public void changePasswd() {
        UsuarioDTo u = (UsuarioDTo) WebUtil.getObjectFromSession("userLogged");

        if (!EncrytUtil.encrypPwd(currentPasswd).equals(u.getPass())) {
            this.setMessage("El password actual no es corrrecto");
            return;
            // return "changePass";
        }
        if (!newPasswd.equals(reTypePasswd)) {
            this.setMessage("El nuevo password no coincide");

            // return "changePass";
        } else {

            u.setPass(EncrytUtil.encrypPwd(newPasswd));
            try {
                usuariosFacade.editUserAfterFirstLogin(u);
                WebUtil.setObjectInSession("userLogged", u);
            } catch (IappException e) {
            }
            this.setMessage("El password se actualizo correctamente");
            // return "changePass";
        }


    }

  

    public String cerrarSession() {
        UsuarioDTo u = (UsuarioDTo) WebUtil.getObjectFromSession("userLogged");
        if (u != null) {
            System.out.println("termina session" + u.getNumDoc());
        }
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();

        return "login";
    }

    public StreamedContent getImageBanner() {
        try {
            String path = "/usr/local/work/imagen_banner.png";
            imageBanner = new DefaultStreamedContent(new FileInputStream(path), "image/png");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imageBanner;
    }

    public void setImageBanner(StreamedContent imageBanner) {
        this.imageBanner = imageBanner;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNewPasswd() {
        return newPasswd;
    }

    public void setNewPasswd(String newPasswd) {
        this.newPasswd = newPasswd;
    }

    public String getReTypePasswd() {
        return reTypePasswd;
    }

    public void setReTypePasswd(String reTypePasswd) {
        this.reTypePasswd = reTypePasswd;
    }

    public String getCurrentPasswd() {
        return currentPasswd;
    }

    public void setCurrentPasswd(String currentPasswd) {
        this.currentPasswd = currentPasswd;
    }
}
