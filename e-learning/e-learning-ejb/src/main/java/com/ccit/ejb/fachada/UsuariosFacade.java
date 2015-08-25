/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada;

import com.ccit.ejb.dto.FiltroUsuariosDto;
import com.ccit.ejb.dto.UsuarioDTo;
import com.ccit.ejb.fachada.impl.IappUsuarioFacade;
import com.ccit.ejb.modelo.IappCursos;
import com.ccit.ejb.modelo.IappUsuario;
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
    
    
    public void crearUsuario(IappUsuario usuario)throws IappException{
        iappUsuarioFacade.create(usuario);
    }
    
    public void editUserAfterFirstLogin(UsuarioDTo usuario)throws IappException{
        
        IappUsuario u = iappUsuarioFacade.find(usuario.getIdUsuario());
        
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
    
    public void eliminarUsuario(IappUsuario usuario)throws IappException{
        iappUsuarioFacade.remove(usuario);
    }
    
    public List<IappUsuario> buscarUsuarios(){
       return iappUsuarioFacade.findAll();
    }
    
    public IappUsuario findUser(String ndoc){
        return iappUsuarioFacade.findUser(ndoc);
    }
    public IappUsuario findUser(String numDoc,String tipoDoc){
        return iappUsuarioFacade.findUser(numDoc, tipoDoc);
    }
    
    public void merge(IappUsuario u)throws IappException{
        
        
         iappUsuarioFacade.edit(u);
    }

    public List<IappUsuario> consultarUsuariosPorPerfil(Integer id_perfil) {
        return iappUsuarioFacade.findUserByPerfil(id_perfil);
    }
    
    
     public List<IappUsuario> findRange(int[] range) {
        return iappUsuarioFacade.findRange(range);
    }

    public int count() {
      return  iappUsuarioFacade.count();
    }

    public List<IappUsuario> getEstudiantes(IappCursos editCourse){
        return iappUsuarioFacade.getEstudiantesNoCurso(editCourse);
    } 
    
    public List<IappUsuario> getEstudiantesActivos(){
        return iappUsuarioFacade.getEstudiantesActivos();
    }
    
    
    public List<IappUsuario> getEstudiantes(FiltroUsuariosDto filtro) {
        return iappUsuarioFacade.getEstudiantes(filtro);
    }
}
