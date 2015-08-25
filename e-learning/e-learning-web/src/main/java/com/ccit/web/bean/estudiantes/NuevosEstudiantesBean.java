/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean.estudiantes;

import com.ccit.ejb.constants.Constants;
import com.ccit.ejb.fachada.GeneralFacadeBean;
import com.ccit.ejb.fachada.UsuariosFacade;
import com.ccit.ejb.modelo.*;
import com.ccit.ejb.utilidades.EncrytUtil;
import com.ccit.ejb.utilidades.UtilidadesFecha;
import com.ccit.exception.IappException;
import com.ccit.web.bean.GeneralListBean;
import com.ccit.web.util.WebUtil;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.mail.MessagingException;
import javax.naming.NamingException;

/**
 *
 * @author marino
 */
@ManagedBean
@SessionScoped
public class NuevosEstudiantesBean implements Serializable {

    @EJB
    private UsuariosFacade usuariosFacade;
    @EJB
    private GeneralFacadeBean generalFacade;
    private IappUsuario userOnline;
    private Integer idJornada;
    private static ResourceBundle bundle = null;
    private String sede;
    private String tomaClase;
    private String nombreBeneficiario;
    private String idResponsable;
    private String celularResposable;
    private String telFijoResponsable;
    private String emailResponsable;

    @PostConstruct
    public void init() {
        System.out.println("Nuevos Estudiantes Init");
        userOnline = new IappUsuario();
        userOnline.setIdJornada(new IappJornada());
        userOnline.setIdPerfil(new IappPerfiles());
        userOnline.setEstadoUsuario(new IappEstadoUsuario());
        userOnline.setIdNivel(new IappNiveles());
        this.userOnline.setTipoDoc(new IappTipoDocumento());

        bundle = ResourceBundle.getBundle("dynamic");

    }

    public void resetPagosOnline() {
        System.out.println("Reset Nuevos Estudiantes");
        userOnline = new IappUsuario();
        userOnline.setIdJornada(new IappJornada());
        userOnline.setIdPerfil(new IappPerfiles());
        userOnline.setEstadoUsuario(new IappEstadoUsuario());
        userOnline.setIdNivel(new IappNiveles());
        this.userOnline.setTipoDoc(new IappTipoDocumento());



    }

    public String continuar() {

        if (this.idJornada == 1) {
            return "termspresencial";
        } else if (this.idJornada == 7) {
            return "termskids";
        } else if (this.idJornada == 3
                || this.idJornada == 4
                || this.idJornada == 5
                || this.idJornada == 6) {
            return "termsblended";
        }else if(this.idJornada == 8){
            return "termsintensivo";
        }

        return "";

    }

    public List<SelectItem> getItemsSedes() {
        List<SelectItem> items = new ArrayList<SelectItem>();

        List<IappSede> sedes = generalFacade.finAllSedes();

        for (IappSede iappSede : sedes) {
            items.add(new SelectItem(iappSede.getDescripcion(), iappSede.getDescripcion()));
        }

        return items;
    }

    /**
     * Creates a new instance of New Estudents
     */
    public NuevosEstudiantesBean() {
    }

    public String registerUserOnline() throws IappException {

        try {

            if (usuariosFacade.findUser(userOnline.getNumeroDoc(), userOnline.getTipoDoc().getIdTipoDoc()) != null) {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage("El usuario Ya Existe en el sistema"));
                return "";

            }

        } catch (Exception e) {
            System.out.println("El usuario no existe se puede crear");
        }




        String password = EncrytUtil.nextSessionId();
        userOnline.setPasswd(EncrytUtil.encrypPwd(EncrytUtil.nextSessionId()));
        userOnline.setCont(0);
        userOnline.setIdPerfil(new IappPerfiles(Constants.PERFIL_ESTUDIANTE));
        userOnline.setIdJornada(new IappJornada(idJornada));
        userOnline.setEstadoUsuario(new IappEstadoUsuario("I"));
        userOnline.setIdNivel(new IappNiveles(1));
        userOnline.setFechaInicio(new Date());
        try {
            usuariosFacade.crearUsuario(userOnline);
            sendWellcomeEmail(password, userOnline.getEmail());
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Se ha enviado a su correo electronico los datos de acceso al portal"));
        } catch (IappException ex) {
            Logger.getLogger(NuevosEstudiantesBean.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return "confirmnew";
    }

    private void sendWellcomeEmail(String contrasena, String to) {
        try {
            String emailAdmin = bundle.getString("mail_admin2");
            GeneralListBean gb = (GeneralListBean) WebUtil.getManageBeanFromSession("generalListBean", GeneralListBean.class);
            StringBuilder body = new StringBuilder();
            body.append("<div>");
            body.append("<p>Bienvenido  ");
            body.append("</p>");
            body.append("<p>Hemos recibido el registro como nuevo estudiante con los siguientes datos </p>");
            body.append("<p>Fecha :");
            body.append(UtilidadesFecha.getFormatDate(Calendar.getInstance()));
            body.append("</p>");
            body.append("<p>Matricula :");
            body.append(userOnline.getCodigo());
            body.append("</p>");
            body.append("<p>Nombre :");
            body.append(userOnline.getNombres());
            body.append("</p>");
            body.append("<p>Apellidos :");
            body.append(userOnline.getApellidos());
            body.append("</p>");
            body.append("<p>Número Identificación :");
            body.append(userOnline.getNumeroDoc());
            body.append("</p>");
            body.append("<p>Telefono :");
            body.append(userOnline.getTelefono());
            body.append("</p>");
            body.append("<p>E-mail :");
            body.append(userOnline.getEmail());
            body.append("</p>");

            String jornaString = "";
            List<IappJornada> jornada = gb.getJornada();
            for (IappJornada jr : jornada) {
                if (this.idJornada.equals(jr.getIdJornada())) {
                    jornaString = jr.getDescripcion();
                    break;
                }
            }


            body.append("<p>Jornada :");
            body.append(jornaString);
            body.append("</p>");

            body.append("<p>Sede :");
            body.append(sede);
            body.append("</p>");
           

            body.append("<p>Titular Matricula Contrato:");
            body.append(nombreBeneficiario != null ? nombreBeneficiario : "");
            body.append("</p>");

             body.append("<p>Identificación Responsable:");
            body.append(idResponsable);
            body.append("</p>");
            
            body.append("<p>Teléfono Celular:");
            body.append(celularResposable);
            body.append("</p>");
            
            body.append("<p>Teléfono Fijo:");
            body.append(telFijoResponsable);
            body.append("</p>");
            
            body.append("<p>Correo Electrónico:");
            body.append(emailResponsable);
            body.append("</p>");
            

            body.append("<p>Gracias por hacer parte de nuestra institución, su usuario y contraseña asignado es:");

            body.append("</p>");

            body.append("<p>USUARIO: ");

            body.append(userOnline.getNumeroDoc());

            body.append("</p>");

            body.append("<p>CONTRASEÑA: ");

            body.append(contrasena);

            body.append("</p>");


            body.append("<p>");

            body.append(
                    "Señor estudiante, en poco estaremos en comunicación para iniciar el proceso de validación y continuar el proceso.");

            body.append("</p>");

            body.append("<p>");
            body.append("Recuerde que el acceso a nuestra página Web es: www.dynamic-teaching.edu.co, nuestro portal");
            body.append(" académico: www.dynamicteaching-online.com");
            body.append("</p>");

            body.append("<br></br>");
            body.append("<table>");
            body.append("<tr>");
            body.append("<td><img src=\"http://www.dynamic-teaching.edu.co/templates/rt_clarion/images/logo/light/logo.png\"></img></td>   ");
            body.append("<td>");
            body.append("<p>Dynamic Teaching</p>");
            body.append("<p>Av. Calle 26 (El Dorado) # 38A - 33</p>");
            body.append("<p>info@dynamic-teaching.edu.co</p>");
            body.append("</td>");
            body.append(" </tr>");
            body.append("<table>");
            body.append("</div>");
            this.generalFacade.sendMail(to, "Bienvenido a nuestra comunidad", body.toString());
            this.generalFacade.sendMail(emailAdmin, "Bienvenido a nuestra comunidad", body.toString());
            this.generalFacade.sendMail(bundle.getString("mail_academic"), "Bienvenido a nuestra comunidad", body.toString());
            this.generalFacade.sendMail(bundle.getString("mail_secreatria"), "Bienvenido a nuestra comunidad", body.toString());
            this.generalFacade.sendMail(bundle.getString("mail_verificacion"), "Bienvenido a nuestra comunidad", body.toString());
        } catch (NamingException ex) {
            Logger.getLogger(NuevosEstudiantesBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(NuevosEstudiantesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendMail(String to, String message) {
        try {
            generalFacade.sendMail(to, "Registro", "Message");
        } catch (NamingException ex) {
            Logger.getLogger(NuevosEstudiantesBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(NuevosEstudiantesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public IappUsuario getUserOnline() {
        return userOnline;
    }

    public void setUserOnline(IappUsuario userOnline) {
        this.userOnline = userOnline;
    }

    public Integer getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Integer idJornada) {
        this.idJornada = idJornada;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getNombreBeneficiario() {
        return nombreBeneficiario;
    }

    public void setNombreBeneficiario(String nombreBeneficiario) {
        this.nombreBeneficiario = nombreBeneficiario;
    }

    public String getTomaClase() {
        return tomaClase;
    }

    public void setTomaClase(String tomaClase) {
        this.tomaClase = tomaClase;
    }

    public String getCelularResposable() {
        return celularResposable;
    }

    public void setCelularResposable(String celularResposable) {
        this.celularResposable = celularResposable;
    }

    public String getEmailResponsable() {
        return emailResponsable;
    }

    public void setEmailResponsable(String emailResponsable) {
        this.emailResponsable = emailResponsable;
    }

    public String getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(String idResponsable) {
        this.idResponsable = idResponsable;
    }

    public String getTelFijoResponsable() {
        return telFijoResponsable;
    }

    public void setTelFijoResponsable(String telFijoResponsable) {
        this.telFijoResponsable = telFijoResponsable;
    }
    
    
}
