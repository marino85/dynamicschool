/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.web.util.WebUtil;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author marino
 */
@ManagedBean
@RequestScoped
public class PagosOnlineResponse {
    
    
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
    private  String cus;
    private String banco_pse;
    private String fecha_procesamiento;
    private String emailComprador ;

    
    
    @PostConstruct
    public void init(){
        
        
        
        this.usuario_id= WebUtil.getRequest().getParameter("usuario_id");
        this.estado_pol= WebUtil.getRequest().getParameter("estado_pol");
        this.riesgo=WebUtil.getRequest().getParameter("riesgo");
        this.codigo_respuesta_pol=WebUtil.getRequest().getParameter("codigo_respuesta_pol");
        this.ref_venta=WebUtil.getRequest().getParameter("ref_venta");
        this.ref_pol=WebUtil.getRequest().getParameter("ref_pol");
        this.firma=WebUtil.getRequest().getParameter("firma");
        this.mensaje=WebUtil.getRequest().getParameter("mensaje");
        this.medio_pago=WebUtil.getRequest().getParameter("medio_pago");
        this.tipo_medio_pago=WebUtil.getRequest().getParameter("tipo_medio_pago");
        this.valor=WebUtil.getRequest().getParameter("valor");
        this.cus=WebUtil.getRequest().getParameter("cus");
        this.banco_pse=WebUtil.getRequest().getParameter("banco_pse");
        this.emailComprador=WebUtil.getRequest().getParameter("email_comprador");
        this.fecha_procesamiento=WebUtil.getRequest().getParameter("fecha_procesamiento");
    }

    /**
     * Creates a new instance of PagosOnlineResponse
     */
    public PagosOnlineResponse() {
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
