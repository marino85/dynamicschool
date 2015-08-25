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
import com.ccit.ejb.modelo.IappQualificationCourse;
import com.ccit.ejb.modelo.IappCourses;
import com.ccit.ejb.modelo.IappEnrollments;
import com.ccit.ejb.modelo.IappSprints;
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
    
    public List<IappEnrollments> findEstudentsByCourse(Integer idCourse, String codigo, String nombres) {
        return iappMatriculasFacade.findEstudentsByCourse(idCourse, codigo, nombres);
    }
    public void merge(IappCourses c)throws IappException{
        for(IappSprints m:c.getIappModulosCollection()){
            if(m.getIdModulo()==null){
                iappModulosFacade.create(m);
            }
        }
        for(IappEnrollments m:c.getIappMatriculasCollection()){
            if(m.getIdMatricula()==null){
                iappMatriculasFacade.create(m);
            }
        }
        iappCursosFacade.edit(c);
    }

    public void crearCurso(IappCourses newCourse) throws IappException {
        iappCursosFacade.create(newCourse);
    }

    public List<IappCourses> buscarCursos() {
        return iappCursosFacade.findAll();
    }

    public void eliminarUsuario(IappCourses courseDelete) throws IappException {
        iappCursosFacade.remove(courseDelete);
    }

    public void eliminarModulos(List<IappSprints> modulesToDelete) throws IappException {
        for(IappSprints mod:modulesToDelete){
            iappModulosFacade.remove(mod);
        }
    }
    
    public void calificarCurso(IappQualificationCourse cal)throws IappException{
        calCurso.edit(cal);
    }
}
