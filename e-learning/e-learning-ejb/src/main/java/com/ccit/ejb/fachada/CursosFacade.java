/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada;
import com.ccit.ejb.dto.EvaluacionesDto;
import com.ccit.ejb.fachada.impl.IappCalCursoFacade;
import com.ccit.ejb.fachada.impl.IappCursosFacade;
import com.ccit.ejb.fachada.impl.IappMatriculasFacade;
import com.ccit.ejb.fachada.impl.IappModulosFacade;
import com.ccit.ejb.fachada.impl.IappUsuarioFacade;
import com.ccit.ejb.modelo.IappCalCurso;
import com.ccit.ejb.modelo.IappCursos;
import com.ccit.ejb.modelo.IappMatriculas;
import com.ccit.ejb.modelo.IappModulos;
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
public class CursosFacade {
    @EJB
    private IappCursosFacade iappCursosFacade;
    @EJB
    private IappModulosFacade iappModulosFacade;
    @EJB
    private IappUsuarioFacade iappUsuarioFacade;
    @EJB
    private IappMatriculasFacade iappMatriculasFacade;
    @EJB
    private IappCalCursoFacade calCurso;
    
    
     public List<EvaluacionesDto> findEvaluationsByCourse(Integer idCourse, String codigo, String nombres) {
       return iappMatriculasFacade.findEvaluationsByCourse(idCourse, codigo, nombres);
     }
    
    public List<IappMatriculas> findEstudentsByCourse(Integer idCourse, String codigo, String nombres) {
        return iappMatriculasFacade.findEstudentsByCourse(idCourse, codigo, nombres);
    }
    public void merge(IappCursos c)throws IappException{
        for(IappModulos m:c.getIappModulosCollection()){
            if(m.getIdModulo()==null){
                iappModulosFacade.create(m);
            }
        }
        for(IappMatriculas m:c.getIappMatriculasCollection()){
            if(m.getIdMatricula()==null){
                iappMatriculasFacade.create(m);
            }
        }
        iappCursosFacade.edit(c);
    }

    public void crearCurso(IappCursos newCourse) throws IappException {
        iappCursosFacade.create(newCourse);
    }

    public List<IappCursos> buscarCursos() {
        return iappCursosFacade.findAll();
    }

    public void eliminarUsuario(IappCursos courseDelete) throws IappException {
        iappCursosFacade.remove(courseDelete);
    }

    public void eliminarModulos(List<IappModulos> modulesToDelete) throws IappException {
        for(IappModulos mod:modulesToDelete){
            iappModulosFacade.remove(mod);
        }
    }
    
    public void calificarCurso(IappCalCurso cal)throws IappException{
        calCurso.edit(cal);
    }
}
