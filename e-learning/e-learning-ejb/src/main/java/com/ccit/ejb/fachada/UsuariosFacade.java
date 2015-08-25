/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada;

import com.ccit.ejb.dto.FiltroUsuariosDto;
import com.ccit.ejb.dto.UsuarioDTo;
import com.ccit.ejb.fachada.impl.IappUsuarioFacade;
import com.ccit.ejb.modelo.IappCourses;
import com.ccit.ejb.modelo.IappUser;
import com.ccit.exception.IappException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author marino
 */
@Stateless
@LocalBean
public class UsuariosFacade {
    @EJB
    private IappUsuarioFacade iappUsuarioFacade;
    
    
    public void crearUsuario(IappUser usuario)throws IappException{
        iappUsuarioFacade.create(usuario);
    }
    
    public void editUserAfterFirstLogin(UsuarioDTo usuario)throws IappException{
        
        IappUser u = iappUsuarioFacade.find(usuario.getIdUsuario());
        
        if(u!=null){
            u.setPasswd(usuario.getPass());
            Integer cont=usuario.getCont().intValue()+1;
            u.setCont(cont);
            iappUsuarioFacade.edit(u);
        }
        else{
            System.out.println("No se encontro el usuario");
        }
    }
    
    public void eliminarUsuario(IappUser usuario)throws IappException{
        iappUsuarioFacade.remove(usuario);
    }
    
    public List<IappUser> buscarUsuarios(){
       return iappUsuarioFacade.findAll();
    }
    
    public IappUser findUser(String ndoc){
        return iappUsuarioFacade.findUser(ndoc);
    }
    public IappUser findUser(String numDoc,String tipoDoc){
        return iappUsuarioFacade.findUser(numDoc, tipoDoc);
    }
    
    public void merge(IappUser u)throws IappException{
        
        
         iappUsuarioFacade.edit(u);
    }

    public List<IappUser> consultarUsuariosPorPerfil(Integer id_perfil) {
        return iappUsuarioFacade.findUserByPerfil(id_perfil);
    }
    
    
     public List<IappUser> findRange(int[] range) {
        return iappUsuarioFacade.findRange(range);
    }

    public int count() {
      return  iappUsuarioFacade.count();
    }

    public List<IappUser> getEstudiantes(IappCourses editCourse){
        return iappUsuarioFacade.getEstudiantesNoCurso(editCourse);
    } 
    
    public List<IappUser> getEstudiantesActivos(){
        return iappUsuarioFacade.getEstudiantesActivos();
    }
    
    
    public List<IappUser> getEstudiantes(FiltroUsuariosDto filtro) {
        return iappUsuarioFacade.getEstudiantes(filtro);
    }
}
