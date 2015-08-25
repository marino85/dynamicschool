/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.constants.Constants;
import com.ccit.ejb.fachada.UsuariosFacade;
import com.ccit.ejb.modelo.*;
import com.ccit.ejb.utilidades.EncrytUtil;
import com.ccit.exception.IappException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author marino
 */
@ManagedBean
@SessionScoped
public class UsuariosBean implements Serializable {

    @EJB
    private UsuariosFacade usuariosFacade;
    private IappUser newUser;
    private IappUser editUser;
    private boolean showDialog = false;
    private String messaje;

    @PostConstruct
    public void init() {
        newUser = new IappUser();
        newUser.setIdJornada(new IappJornada());
        newUser.setIdPerfil(new IappPerfiles());
    
    }

    public void modificar(ActionEvent evt) {
        System.out.println("Modificar......");
        this.showDialog = true;
        try {
            System.out.println("perfil:" + editUser.getIdPerfil().getDescripcion());
            System.out.println("perfil:" + editUser.getIdPerfil().getIdPerfil());
            usuariosFacade.merge(editUser);
            this.setMessaje("La información se ha actualizado con Exito");
        } catch (IappException e) {
            System.out.println("IappException error al modificar el usuario");
            this.setMessaje("Ocurrio un error al guardar el usuario por favor contacte al administrador");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("error al guardar el usuario");
            this.setMessaje("Ocurrio un error al guardar el usuario por favor contacte al administrador");
            e.printStackTrace();
        }

    }

    public void guardar(ActionEvent evt) {
        System.out.println("guardar.........");
        this.showDialog = true;

        try {
            if (newUser.getIdPerfil() == null || newUser.getIdPerfil().getIdPerfil() == -1) {

                this.setMessaje("Seleccione un perfil");
                return;
            }
            try {
//                if (usuariosFacade.findUser(newUser.getNumeroDoc(), newUser.getTipoDoc().getIdTipoDoc()) != null) {
//                    this.setMessaje("El usuario ya existe");
//                    return;
//                }
            } catch (Exception e) {
                System.out.println("");
            }

            newUser.setCont(0);
            newUser.setPasswd(EncrytUtil.encrypPwd(newUser.getNumeroDoc()));
            newUser.setIdUsuario(null);
            if (!newUser.getIdPerfil().getIdPerfil().equals(Constants.PERFIL_ESTUDIANTE)) {
              //  newUser.setIdNivel(new IappNiveles(1));
            }
            usuariosFacade.crearUsuario(newUser);
            this.setMessaje("El usuario se ha creado con Exito");
        } catch (IappException e) {
            System.out.println("IappException error al guardar el usuario");
            this.setMessaje("Ocurrio un error al guardar el usuario por favor contacte al administrador");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("error al guardar el usuario");
            this.setMessaje("Ocurrio un error al guardar el usuario por favor contacte al administrador");
            e.printStackTrace();
        }



    }

    public void aceptar(ActionEvent evt) {
        this.setMessaje("");
        this.showDialog = false;
        this.newUser = new IappUser();
        this.newUser.setIdJornada(new IappJornada());
        this.newUser.setIdPerfil(new IappPerfiles(-1));
//        this.newUser.setEstadoUsuario(new IappEstadoUsuario());
//        this.newUser.setTipoDoc(new IappDocumentType());
//        this.newUser.setIdNivel(new IappNiveles());
    }

    public IappUser getNewUser() {
        return newUser;
    }

    public void setNewUser(IappUser newUser) {
        this.newUser = newUser;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }

    public String getMessaje() {
        return messaje;
    }

    public void setMessaje(String messaje) {
        this.messaje = messaje;
    }

    public IappUser getEditUser() {
        return editUser;
    }

    public void setEditUser(IappUser editUser) {
        this.editUser = editUser;
    }

    /**
     * Creates a new instance of UsuariosBean
     */
    public UsuariosBean() {
    }
}
