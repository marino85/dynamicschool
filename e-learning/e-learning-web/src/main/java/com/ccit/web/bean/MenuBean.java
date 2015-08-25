/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author marino
 */ 
@ManagedBean
@SessionScoped
public class MenuBean implements Serializable{
    private Integer openTab;
    /**
     * Creates a new instance of MenuBean
     */
    public MenuBean() {
    }
    
    
    public String irAHorarios(){
        
        return "horarios";
    }
    
    public String irAActualizarDatosEstudiantes(){
       
        return "actualizarestudiantes";
    }
    
    
    
     public String irACrearUsuarios(){
         
        return "crear";
    }
    
    public String irAGenerarCarnet(){
        return "";
    }
    
     public String irAReporteDeHorario(){
        return "horarios";
    }

    public Integer getOpenTab() {
        return openTab;
    }

    public void setOpenTab(Integer openTab) {
        this.openTab = openTab;
    }
     
    
    
}
