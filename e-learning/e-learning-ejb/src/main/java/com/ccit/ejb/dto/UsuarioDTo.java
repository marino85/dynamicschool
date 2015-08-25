/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.dto;

import com.ccit.ejb.constants.Constants;
import com.ccit.ejb.modelo.IappUser;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author marino
 */
public class UsuarioDTo {
    
    private Integer idUsuario;
    private String tipoDoc;
    private String numDoc;
    private String nombre;
    private String apellido;
    private String perfil;
    private String login;
    private Integer cont;
    private String pass;
    private String date;
    private Integer idCurso;
    private Integer idNivel;
    private Integer idPerfil;
    private String email;
    private String indicadorTiempo;
    private IappUser iappUsuario;
    
    
    
    public boolean isAdministrador(){
        return idPerfil.equals(Constants.PERFIL_ADMINISTRADOR);
    }
    public boolean isEstudiante(){
        return idPerfil.equals(Constants.PERFIL_ESTUDIANTE);
    }
    public boolean isProfesor(){
        return idPerfil.equals(Constants.PERFIL_PROFESOR);
    }
    
    public boolean isSecretario(){
         return idPerfil.equals(Constants.PERFIL_SECRETARIO);
    }
   
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getCont() {
        return cont;
    }

    public void setCont(Integer cont) {
        this.cont = cont;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getDate() {        
        
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }

    public void setDate(String date) {
        
        
        this.date = date;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Integer getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Integer idNivel) {
        this.idNivel = idNivel;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndicadorTiempo() {
        return indicadorTiempo;
    }

    public void setIndicadorTiempo(String indicadorTiempo) {
        this.indicadorTiempo = indicadorTiempo;
    }

    public IappUser getIappUsuario() {
        return iappUsuario;
    }

    public void setIappUsuario(IappUser iappUsuario) {
        this.iappUsuario = iappUsuario;
    }
    
    
    
    
}
