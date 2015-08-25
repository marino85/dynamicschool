/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author marino
 */
public class EvaluacionesDto {
    
    private String nombres;
    private String apellidos;
    private BigDecimal calificacion;
    private Date fechaInicio;

    public EvaluacionesDto(String nombres, String apellidos, BigDecimal calificacion, Date fechaInicio) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.calificacion = calificacion;
        this.fechaInicio = fechaInicio;
    }
    
    

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public BigDecimal getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(BigDecimal calificacion) {
        this.calificacion = calificacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    
    
    
    
}
