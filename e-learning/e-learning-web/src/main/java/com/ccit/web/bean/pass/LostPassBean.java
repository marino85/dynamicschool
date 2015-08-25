/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean.pass;

import com.ccit.ejb.dto.FiltroUsuariosDto;
import com.ccit.ejb.fachada.GeneralFacadeBean;
import com.ccit.ejb.fachada.UsuariosFacade;
import com.ccit.ejb.modelo.IappUser;
import com.ccit.ejb.utilidades.EncrytUtil;
import com.ccit.exception.IappException;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.naming.NamingException;

/**
 *
 * @author marino
 */
@ManagedBean
@ViewScoped
public class LostPassBean implements Serializable {

    @EJB
    private UsuariosFacade usuariosFacade;
   
    @EJB
    private GeneralFacadeBean generalFacade;
    private IappUser userOnline;   
    private String tmpPass;
    private static ResourceBundle bundle = null;
    

    @PostConstruct
    public void init() {
        System.out.println("Init LostPassBean");
        userOnline = new IappUser();
        
        bundle = ResourceBundle.getBundle("dynamic");
        
    }

    /**
     * Creates a new instance of PagosOnlineBean
     */
    public LostPassBean() {
    }
    
    public void cambiarPasswd(){
        FiltroUsuariosDto dto = new FiltroUsuariosDto();
        dto.setEmail(userOnline.getEmail());
        dto.setNumeroDoc(userOnline.getNumeroDoc());
        dto.setCodigo(userOnline.getCodigo());
        
        List<IappUser> consulta = usuariosFacade.getEstudiantes(dto);
        
        if(!consulta.isEmpty()){
            IappUser usuario = consulta.get(0);
            String newPassword = EncrytUtil.nextSessionId();
            tmpPass= newPassword;
            usuario.setPasswd(EncrytUtil.encrypPwd(tmpPass));
            usuario.setCont(0);
            try {
                usuariosFacade.merge(usuario);
                sendEmail(usuario);               
                userOnline = new IappUser();
                tmpPass="";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se ha enviando un email con su nueva contraseña, Gracias"));
            } catch (IappException ex) {
                Logger.getLogger(LostPassBean.class.getName()).log(Level.SEVERE, null, ex);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No fue posible realizar el cambio de contraseña"));
            }
            
            
        }
        else{
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage("No fue posible realizar el cambio de contraseña"));
        }
       // return "lostPass";
        
        
    }

    

    

    private void sendEmail(IappUser user) {
        try {
            StringBuilder body = new StringBuilder();
            body.append("<div>");
            body.append("<p>Estimando (a);  ");
            body.append(user.getNombres());
            body.append(" ");
            body.append(user.getApellidos());
            body.append("</p>");
            body.append("<p>Su contraseña ha sido cambiada, por favor no olvide cambiarla una vez ingrese de nuevo a nuestro portal</p>");
            body.append("<p>Nueva contraseña</p>");
            body.append(tmpPass);
            body.append("<p>Gracias por preferirnos</p>");
            body.append("</p>");
            body.append("<br></br>");
            body.append("<table>");
            body.append("<tr>");
            body.append("<td><img src=\"http://www.dynamic-teaching.edu.co/templates/rt_clarion/images/logo/light/logo.png\"></img></td>   ");
            body.append("<td>");
            body.append("<p>Dynamic Teaching</p>");
            body.append("<p>Av. Calle 26 (El Dorado) # 38A - 33</p>");
            body.append("<p>Telefonos: 2448200 - 2441397</p>");
            body.append("<p>info@dynamic-teaching.edu.co</p>");
            body.append("</td>");
            body.append(" </tr>");
            body.append("<table>");
            body.append("</div>");
            this.generalFacade.sendMail(userOnline.getEmail(), "Recuperación de Contraseña", body.toString());
            this.generalFacade.sendMail(bundle.getString("mail_admin2"), "Recuperación de Contraseña", body.toString());
        } catch (NamingException ex) {
            Logger.getLogger(LostPassBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(LostPassBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    

    
    public IappUser getUserOnline() {
        return userOnline;
    }

    public void setUserOnline(IappUser userOnline) {
        this.userOnline = userOnline;
    }

   
}
