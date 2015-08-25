/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.fachada.GeneralFacadeBean;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author marino
 */

public class PagosOnlineComfirm {
   

    
    private String usuario_id;
    private String estado_pol;
    private String riesgo;
    private String codigo_respuesta_pol;
    private String ref_venta;
    private String ref_pol;
    private String firma;
    private String mensaje;
    private String medio_pago;
    private String tipo_medio_pago;
    private String valor;
    private String cus;
    private String banco_pse;
    private String fecha_procesamiento;
    private String emailComprador;
    private static ResourceBundle bundle = null;

   

    public PagosOnlineComfirm() {
          bundle = ResourceBundle.getBundle("dynamic");
    }

    
    public void sendWellcomeEmail() {
        System.out.println("Enviado Welcome Mail a"+this.emailComprador);
        if (this.emailComprador != null && !this.emailComprador.equals("")) {
            try {
                StringBuilder body = new StringBuilder();
                body.append("<div>");
                body.append("<p>Bienvenido  ");
                body.append("");
                body.append("</p>");
                body.append("<p>Su registro ha sido exitoso, a continuación podra ver el estado de su transacción:</p>");
                body.append("<p>  Fecha de Procesamiento: ");
                body.append(this.fecha_procesamiento);
                body.append("</p>");
                body.append("<p>Estado de la transacción: ");
                body.append(this.mensaje);
                body.append("</p>");
                body.append("<p>referencia de la venta: ");
                body.append(this.ref_venta);
                body.append("</p>");
                body.append("<p>Referencia de la transacción:");
                body.append(this.ref_pol);
                body.append("</p>");

                if (this.banco_pse != null && !this.banco_pse.equals("")) {
                    body.append("<p>cus:");
                    body.append(this.cus);
                    body.append("</p>");
                    body.append("<p> Banco:");
                    body.append(this.banco_pse);
                    body.append("</p>");
                }

                body.append("<p>Valor total:");
                body.append(this.valor);
                body.append("</p>");
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
                lookupGeneralFacadeBeanBean().sendMail(this.emailComprador, "Bienvenido", body.toString());
            } catch (NamingException ex) {
                Logger.getLogger(PagosOnlineBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(PagosOnlineBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void sendWellcomeEmailAdministrador() {
      
        
            try {
                String emailAdmin = bundle.getString("mail_administrador");
                System.out.println("Enviando confirmación a administrador "+emailAdmin);
                StringBuilder body = new StringBuilder();
                body.append("<div>");
                body.append("<p>Confirmación Inscripción Nuevo Usuario ");
                body.append("<p>Señor administrador, el siguiente es el detalle de la transacción del usuario </p>");
                body.append(this.emailComprador);
                body.append("</p>");
                body.append("<p>  Fecha de Procesamiento: ");
                body.append(this.fecha_procesamiento);
                body.append("</p>");
                body.append("<p>Estado de la transacción: ");
                body.append(this.mensaje);
                body.append("</p>");
                body.append("<p>referencia de la venta: ");
                body.append(this.ref_venta);
                body.append("</p>");
                body.append("<p>Referencia de la transacción:");
                body.append(this.ref_pol);
                body.append("</p>");

                if (this.banco_pse != null && !this.banco_pse.equals("")) {
                    body.append("<p>cus:");
                    body.append(this.cus);
                    body.append("</p>");
                    body.append("<p> Banco:");
                    body.append(this.banco_pse);
                    body.append("</p>");
                }

                body.append("<p>Valor total:");
                body.append(this.valor);
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
                lookupGeneralFacadeBeanBean().sendMail(emailAdmin, "Confirmación Registro Usuario-" + this.emailComprador, body.toString());
            } catch (NamingException ex) {
                Logger.getLogger(PagosOnlineBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(PagosOnlineBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

   private GeneralFacadeBean lookupGeneralFacadeBeanBean() {
        try {
            Context c = new InitialContext();
            return (GeneralFacadeBean) c.lookup("java:global/DynamicPortal/DynamicPortal-ejb/GeneralFacadeBean!com.innovasoft.ejb.fachada.GeneralFacadeBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public String getBanco_pse() {
        return banco_pse;
    }

    public void setBanco_pse(String banco_pse) {
        this.banco_pse = banco_pse;
    }

    public String getCodigo_respuesta_pol() {
        return codigo_respuesta_pol;
    }

    public void setCodigo_respuesta_pol(String codigo_respuesta_pol) {
        this.codigo_respuesta_pol = codigo_respuesta_pol;
    }

    public String getCus() {
        return cus;
    }

    public void setCus(String cus) {
        this.cus = cus;
    }

    public String getEstado_pol() {
        return estado_pol;
    }

    public void setEstado_pol(String estado_pol) {
        this.estado_pol = estado_pol;
    }

    public String getFecha_procesamiento() {
        return fecha_procesamiento;
    }

    public void setFecha_procesamiento(String fecha_procesamiento) {
        this.fecha_procesamiento = fecha_procesamiento;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getMedio_pago() {
        return medio_pago;
    }

    public void setMedio_pago(String medio_pago) {
        this.medio_pago = medio_pago;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getRef_pol() {
        return ref_pol;
    }

    public void setRef_pol(String ref_pol) {
        this.ref_pol = ref_pol;
    }

    public String getRef_venta() {
        return ref_venta;
    }

    public void setRef_venta(String ref_venta) {
        this.ref_venta = ref_venta;
    }

    public String getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }

    public String getTipo_medio_pago() {
        return tipo_medio_pago;
    }

    public void setTipo_medio_pago(String tipo_medio_pago) {
        this.tipo_medio_pago = tipo_medio_pago;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getEmailComprador() {
        return emailComprador;
    }

    public void setEmailComprador(String emailComprador) {
        this.emailComprador = emailComprador;
    }
}
