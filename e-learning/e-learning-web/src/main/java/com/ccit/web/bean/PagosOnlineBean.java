///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.ccit.web.bean;
//
//import com.ccit.ejb.constants.Constants;
//import com.ccit.ejb.fachada.FacturaFacade;
//import com.ccit.ejb.fachada.GeneralFacadeBean;
//import com.ccit.ejb.fachada.UsuariosFacade;
//import com.ccit.ejb.modelo.*;
//import com.ccit.ejb.utilidades.EncrytUtil;
//import com.ccit.exception.IappException;
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//import javax.faces.context.FacesContext;
//import javax.mail.MessagingException;
//import javax.naming.NamingException;
//import org.apache.commons.codec.digest.DigestUtils;
//
///**
// *
// * @author marino
// */
//@ManagedBean
//@SessionScoped
//public class PagosOnlineBean implements Serializable {
//
//    @EJB
//    private UsuariosFacade usuariosFacade;
//    @EJB
//    private FacturaFacade invoiceFacade;
//    @EJB
//    private GeneralFacadeBean generalFacade;
//    private IappUser userOnline;
//    private IappCourses courseSelected;
//    private IappFactura invoice;
//    private String payImmediatly;
//    private boolean acceptTerms;
//    private boolean acceptConditions;
//    private String key;
//    private String idClient;
//    private String urlRespuesta;
//    private String urlConfirmacion;
//    private static ResourceBundle bundle = null;
//    private BigDecimal pagoMensualidad;
//
//    @PostConstruct
//    public void init() {
//        System.out.println("Init Pagos Online");
//        userOnline = new IappUser();
//        userOnline.setIdJornada(new IappJornada());
//        userOnline.setIdPerfil(new IappPerfiles());
////        userOnline.setEstadoUsuario(new IappEstadoUsuario());
////        userOnline.setIdNivel(new IappNiveles());
////        this.userOnline.setTipoDoc(new IappDocumentType());
//        invoice = new IappFactura();
//        bundle = ResourceBundle.getBundle("dynamic");
//        this.courseSelected = new IappCourses();
//    }
//
//    public void resetPagosOnline() {
//        System.out.println("Reset Pagos Online");
//        userOnline = new IappUser();
//        userOnline.setIdJornada(new IappJornada());
//        userOnline.setIdPerfil(new IappPerfiles());
////        userOnline.setEstadoUsuario(new IappEstadoUsuario());
////        userOnline.setIdNivel(new IappNiveles());
////        this.userOnline.setTipoDoc(new IappDocumentType());
//        invoice = new IappFactura();
//        bundle = ResourceBundle.getBundle("dynamic");
//        this.courseSelected = new IappCourses();
//    }
//
//    /**
//     * Creates a new instance of PagosOnlineBean
//     */
//    public PagosOnlineBean() {
//    }
//
//    public String pagarMensualidad() {
//        preparePayment(this.courseSelected.getDescripcion(), this.pagoMensualidad);
//
//        prepareKey(this.pagoMensualidad);
//        sendWellcomeEmailAdministradorMensualidad();
//
//        return "send";
//    }
//
//    public String continuarPago() {
//        if (payImmediatly.equals("S")) {
//            try {
//                registerUserOnline();
//            } catch (IappException ex) {
//                Logger.getLogger(PagosOnlineBean.class.getName()).log(Level.SEVERE, null, ex);
//                FacesMessage f = new FacesMessage(ex.getMessage());
//                FacesContext.getCurrentInstance().addMessage("Mensaje", f);
//                return "resumen";
//            }
//            preparePayment();
//            prepareKey();
//            sendWellcomeEmailAdministrador();
//            return "send";
//        } else if (payImmediatly.equals("N")) {
//            try {
//                registerUserOnline();
//            } catch (IappException ex) {
//                FacesMessage f = new FacesMessage(ex.getMessage());
//                FacesContext.getCurrentInstance().addMessage("Mensaje", f);
//                Logger.getLogger(PagosOnlineBean.class.getName()).log(Level.SEVERE, null, ex);
//                return "resumen";
//            }
//            sendWellcomeEmail();
//            sendWellcomeEmailAdministrador();
//            FacesMessage f = new FacesMessage("Su  registro ha sido exitoso, uno de nuestros asesores se pondra en contacto con ud, gracias por elegirnos");
//            FacesContext.getCurrentInstance().addMessage("Mensaje", f);
//            return "rexito";
//        }
//        return "";
//    }
//
//    private void registerUserOnline() throws IappException {
//
//        try {
//
//            if (usuariosFacade.findUser(userOnline.getNumeroDoc(), userOnline.getTipoDoc().getIdTipoDoc()) != null) {
//                System.out.println("El usuario Ya Existe en el sistema Pagos Online");
//                throw new IappException("El usuario ya existe en el sistema");
//            }
//
//        } catch (Exception e) {
//            System.out.println("El usuario no existe se puede crear");
//        }
//
//
//
//
//        userOnline.setCodigo("0000");
//        userOnline.setPasswd(EncrytUtil.encrypPwd(userOnline.getNumeroDoc()));
//        userOnline.setCont(0);
//        userOnline.setIdPerfil(new IappPerfiles(Constants.PERFIL_ESTUDIANTE));
//        userOnline.setIdNivel(courseSelected.getIdNivel());
//        userOnline.setEstadoUsuario(new IappEstadoUsuario("I"));
//        try {
//            usuariosFacade.crearUsuario(userOnline);
//        } catch (IappException ex) {
//            Logger.getLogger(PagosOnlineBean.class.getName()).log(Level.SEVERE, null, ex);
//            throw ex;
//        }
//    }
//
//    private void sendWellcomeEmail() {
//        try {
//            StringBuilder body = new StringBuilder();
//            body.append("<div>");
//            body.append("<p>Bienvenido  ");
//            body.append(this.userOnline.getNombres());
//            body.append("</p>");
//            body.append("<p>Su registro ha sido exitoso, proximamente uno de nuestros asesores se estará contactandolo</p>");
//            body.append("<p>Gracias por preferirnos</p>");
//            body.append("</p>");
//            body.append("<br></br>");
//            body.append("<table>");
//            body.append("<tr>");
//            body.append("<td><img src=\"http://www.dynamic-teaching.edu.co/templates/rt_clarion/images/logo/light/logo.png\"></img></td>   ");
//            body.append("<td>");
//            body.append("<p>Dynamic Teaching</p>");
//            body.append("<p>Av. Calle 26 (El Dorado) # 38A - 33</p>");
//            body.append("<p>Telefonos: 2448200 - 2441397</p>");
//            body.append("<p>info@dynamic-teaching.edu.co</p>");
//            body.append("</td>");
//            body.append(" </tr>");
//            body.append("<table>");
//            body.append("</div>");
//            this.generalFacade.sendMail(userOnline.getEmail(), "Bienvenido", body.toString());
//        } catch (NamingException ex) {
//            Logger.getLogger(PagosOnlineBean.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (MessagingException ex) {
//            Logger.getLogger(PagosOnlineBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    private void sendWellcomeEmailAdministrador() {
//        try {
//            String emailAdmin = bundle.getString("mail_administrador");
//            StringBuilder body = new StringBuilder();
//            body.append("<div>");
//            body.append("<p>Registro Nuevo Usuario ");
//            body.append("</p>");
//            body.append("<p>Señor administrador se ha realizado la incripción de la siguiente persona</p>");
//            body.append("<p>Nombre :");
//            body.append(userOnline.getNombres());
//            body.append("</p>");
//            body.append("<p>Apellidos :");
//            body.append(userOnline.getApellidos());
//            body.append("</p>");
//            body.append("<p>Número Identificación :");
//            body.append(userOnline.getNumeroDoc());
//            body.append("</p>");
//            body.append("<p>Telefono :");
//            body.append(userOnline.getTelefono());
//            body.append("</p>");
//            body.append("<p>E-mail :");
//            body.append(userOnline.getEmail());
//            body.append("</p>");
//            body.append("<p>Interesado Curso :");
//            body.append(this.courseSelected.getNombre());
//            body.append("</p>");
//            body.append("<br></br>");
//            body.append("<table>");
//            body.append("<tr>");
//            body.append("<td><img src=\"http://www.dynamic-teaching.edu.co/templates/rt_clarion/images/logo/light/logo.png\"></img></td>   ");
//            body.append("<td>");
//            body.append("<p>Dynamic Teaching</p>");
//            body.append("<p>Av. Calle 26 (El Dorado) # 38A - 33</p>");
//            body.append("<p>Telefonos: 2448200 - 2441397</p>");
//            body.append("<p>info@dynamic-teaching.edu.co</p>");
//            body.append("</td>");
//            body.append(" </tr>");
//            body.append("<table>");
//            body.append("</div>");
//            this.generalFacade.sendMail(emailAdmin, "Registro Nuevo Usuario-" + this.userOnline.getEmail(), body.toString());
//        } catch (NamingException ex) {
//            Logger.getLogger(PagosOnlineBean.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (MessagingException ex) {
//            Logger.getLogger(PagosOnlineBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    private void sendWellcomeEmailAdministradorMensualidad() {
//        try {
//            String emailAdmin = bundle.getString("mail_administrador");
//            StringBuilder body = new StringBuilder();
//            body.append("<div>");
//            body.append("<p>Registro Nuevo Usuario ");
//            body.append("</p>");
//            body.append("<p>Señor administrador se ha realizado el pago de mensualidad del siguiente estudiante</p>");
//            body.append("<p>Nombre :");
//            body.append(userOnline.getNombres());
//            body.append("</p>");
//            body.append("<p>Apellidos :");
//            body.append(userOnline.getApellidos());
//            body.append("</p>");
//            body.append("<p>Número Identificación :");
//            body.append(userOnline.getNumeroDoc());
//            body.append("</p>");
//            body.append("<p>Telefono :");
//            body.append(userOnline.getTelefono());
//            body.append("</p>");
//            body.append("<p>E-mail :");
//            body.append(userOnline.getEmail());
//            body.append("</p>");
//            body.append("<p>Confirmar la transacción una vez se haya confirmado la transacción via mail</p>");
//            body.append("<br></br>");
//            body.append("<table>");
//            body.append("<tr>");
//            body.append("<td><img src=\"http://www.dynamic-teaching.edu.co/templates/rt_clarion/images/logo/light/logo.png\"></img></td>   ");
//            body.append("<td>");
//            body.append("<p>Dynamic Teaching</p>");
//            body.append("<p>Av. Calle 26 (El Dorado) # 38A - 33</p>");
//            body.append("<p>Telefonos: 2448200 - 2441397</p>");
//            body.append("<p>info@dynamic-teaching.edu.co</p>");
//            body.append("</td>");
//            body.append(" </tr>");
//            body.append("<table>");
//            body.append("</div>");
//            this.generalFacade.sendMail(emailAdmin, "Pago Mensualidad Usuario-" + this.userOnline.getEmail(), body.toString());
//        } catch (NamingException ex) {
//            Logger.getLogger(PagosOnlineBean.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (MessagingException ex) {
//            Logger.getLogger(PagosOnlineBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    private void clear() {
//        userOnline = new IappUser();
//        userOnline.setIdJornada(new IappJornada());
//        userOnline.setIdPerfil(new IappPerfiles());
//        userOnline.setEstadoUsuario(new IappEstadoUsuario());
//        userOnline.setIdNivel(new IappNiveles());
//        this.userOnline.setTipoDoc(new IappDocumentType());
//        this.courseSelected = new IappCourses();
//    }
//
//    private void preparePayment() {
//        this.invoice.setBaseiva(BigDecimal.ZERO);
//        this.invoice.setDescripcion("Inscripcion Curso " + courseSelected.getDescripcion());
//        this.invoice.setIva(BigDecimal.ZERO);
//        this.invoice.setValor(courseSelected.getValor());
//        this.invoice.setIdUsuario(userOnline);
//
//        try {
//            invoiceFacade.createInvoice(invoice);
//        } catch (IappException ex) {
//            Logger.getLogger(PagosOnlineBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    private void preparePayment(String descripcion, BigDecimal valor) {
//        this.invoice.setBaseiva(BigDecimal.ZERO);
//        this.invoice.setDescripcion(descripcion);
//        this.invoice.setIva(BigDecimal.ZERO);
//        this.invoice.setValor(valor);
//        this.invoice.setIdUsuario(userOnline);
//
//        try {
//            invoiceFacade.createInvoice(invoice);
//        } catch (IappException ex) {
//            Logger.getLogger(PagosOnlineBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    public String prepareTypeId() {
//
//        if (userOnline.getTipoDoc().getIdTipoDoc().equals("")) {
//        }
//        return "";
//    }
//
//    private void prepareKey() {
//        StringBuilder builder = new StringBuilder();
//        builder.append(bundle.getString("key"));
//        builder.append("~");
//        builder.append(bundle.getString("id_cliente"));
//        builder.append("~");
//        builder.append(this.invoice.getRefVenta());
//        builder.append("~");
//        builder.append(courseSelected.getValor());
//        builder.append("~");
//        builder.append("COP");
//        this.setKey(DigestUtils.md5Hex(builder.toString()));
//        this.setIdClient(bundle.getString("id_cliente"));
//        this.setUrlConfirmacion(bundle.getString("url_confirmacion"));
//        this.setUrlRespuesta(bundle.getString("url_respuesta"));
//
//    }
//
//    private void prepareKey(BigDecimal valor) {
//        StringBuilder builder = new StringBuilder();
//        builder.append(bundle.getString("key"));
//        builder.append("~");
//        builder.append(bundle.getString("id_cliente"));
//        builder.append("~");
//        builder.append(this.invoice.getRefVenta());
//        builder.append("~");
//        builder.append(valor);
//        builder.append("~");
//        builder.append("COP");
//        this.setKey(DigestUtils.md5Hex(builder.toString()));
//        this.setIdClient(bundle.getString("id_cliente"));
//        this.setUrlConfirmacion(bundle.getString("url_confirmacion"));
//        this.setUrlRespuesta(bundle.getString("url_respuesta"));
//
//    }
//
//    private void sendMail(String to, String message) {
//        try {
//            generalFacade.sendMail(to, "Registro", "Message");
//        } catch (NamingException ex) {
//            Logger.getLogger(PagosOnlineBean.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (MessagingException ex) {
//            Logger.getLogger(PagosOnlineBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public IappUser getUserOnline() {
//        return userOnline;
//    }
//
//    public void setUserOnline(IappUser userOnline) {
//        this.userOnline = userOnline;
//    }
//
//    public IappCourses getCourseSelected() {
//        return courseSelected;
//    }
//
//    public void setCourseSelected(IappCourses courseSelected) {
//        this.courseSelected = courseSelected;
//    }
//
//    public String getPayImmediatly() {
//        return payImmediatly;
//    }
//
//    public void setPayImmediatly(String payImmediatly) {
//        this.payImmediatly = payImmediatly;
//    }
//
//    public boolean isAcceptConditions() {
//        return acceptConditions;
//    }
//
//    public void setAcceptConditions(boolean acceptConditions) {
//        this.acceptConditions = acceptConditions;
//    }
//
//    public boolean isAcceptTerms() {
//        return acceptTerms;
//    }
//
//    public void setAcceptTerms(boolean acceptTerms) {
//        this.acceptTerms = acceptTerms;
//    }
//
//    public String getKey() {
//        return key;
//    }
//
//    public void setKey(String key) {
//        this.key = key;
//    }
//
//    public String getIdClient() {
//        return idClient;
//    }
//
//    public void setIdClient(String idClient) {
//        this.idClient = idClient;
//    }
//
//    public IappFactura getInvoice() {
//        return invoice;
//    }
//
//    public void setInvoice(IappFactura invoice) {
//        this.invoice = invoice;
//    }
//
//    public String getUrlConfirmacion() {
//        return urlConfirmacion;
//    }
//
//    public void setUrlConfirmacion(String urlConfirmacion) {
//        this.urlConfirmacion = urlConfirmacion;
//    }
//
//    public String getUrlRespuesta() {
//        return urlRespuesta;
//    }
//
//    public void setUrlRespuesta(String urlRespuesta) {
//        this.urlRespuesta = urlRespuesta;
//    }
//
//    public BigDecimal getPagoMensualidad() {
//        return pagoMensualidad;
//    }
//
//    public void setPagoMensualidad(BigDecimal pagoMensualidad) {
//        this.pagoMensualidad = pagoMensualidad;
//    }
//}
