/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada;

import com.ccit.ejb.constants.Constants;
import com.ccit.ejb.dto.UsuarioDTo;
import com.ccit.ejb.fachada.impl.IappUsuarioFacade;
import com.ccit.ejb.modelo.IappUsuario;
import com.ccit.ejb.utilidades.EncrytUtil;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author marino
 */
@Stateless
@LocalBean
public class LoginBean {

    @EJB
    private IappUsuarioFacade iappUsuarioFacade;

    /**
     * Metdodo que realiza la validacion del login del usuario.
     *
     * @param user
     * @param pass
     * @return
     */
    public UsuarioDTo login(String user, String pass) {
        IappUsuario usuario = null;
        try {
            usuario = iappUsuarioFacade.findUser(user);
        } catch (Exception e) {
            return null;
        }

        if (usuario != null) {

            if (usuario.getPasswd().equals(EncrytUtil.encrypPwd(pass))) {

                UsuarioDTo u = new UsuarioDTo();
                u.setApellido(usuario.getApellidos());
                u.setNombre(usuario.getNombres());
                u.setCont(usuario.getCont());
                u.setPerfil(usuario.getIdPerfil().getDescripcion());
                u.setNumDoc(usuario.getNumeroDoc());
                u.setTipoDoc(usuario.getTipoDoc().getIdTipoDoc());
                u.setIdUsuario(usuario.getIdUsuario());
                u.setLogin(usuario.getNumeroDoc());
                u.setIdNivel(usuario.getIdNivel().getIdNivel());
                u.setIdPerfil(usuario.getIdPerfil().getIdPerfil());
                u.setEmail(usuario.getEmail());
                u.setIappUsuario(usuario);
                //validamos Fecha Fin Si es Estudiante

                if (usuario.getIdPerfil().getIdPerfil().equals(Constants.PERFIL_ESTUDIANTE)) {
                    if (usuario.getFechaFin() == null) {
                        return null;
                    }

                    Calendar c = Calendar.getInstance();
                    Calendar usu = Calendar.getInstance();
                    usu.setTime(usuario.getFechaFin());

                    if (usu.after(c)) {
                        u.setIndicadorTiempo("N");
                    } else {
                        u.setIndicadorTiempo("S");
                    }
                }


                return u;

            } else {
                return null;
            }


        } else {
            return null;
        }

    }
}
