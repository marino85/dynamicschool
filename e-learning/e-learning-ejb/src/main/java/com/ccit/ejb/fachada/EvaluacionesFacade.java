/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada;
import com.ccit.ejb.modelo.IappTests;
import com.ccit.ejb.modelo.IappTestQuestions;
import com.ccit.ejb.modelo.IappEnrollments;
import com.ccit.ejb.modelo.IappTestQuestionAnswers;
import com.ccit.ejb.modelo.IappTestResult;
import com.ccit.ejb.modelo.IappTestSolutions;
import com.ccit.ejb.fachada.impl.IappPreguntaFacade;
import com.ccit.ejb.fachada.impl.IappSolucionesFacade;
import com.ccit.ejb.fachada.impl.IappRespuestasFacade;
import com.ccit.ejb.fachada.impl.IappEvaluacionesFacade;
import com.ccit.ejb.fachada.impl.IappCalEvaluacionFacade;
import com.ccit.exception.IappException;
import java.sql.SQLException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author marino
 */
@Stateless
@LocalBean
public class EvaluacionesFacade {
    @EJB
    private IappEvaluacionesFacade iappEvaluacionesFacade;
    @EJB
    private IappPreguntaFacade iappPreguntaFacade;
    @EJB
    private IappRespuestasFacade iappRespuestasFacade;
    @EJB
    private IappSolucionesFacade iappSolucionesFacade;
    @EJB
    private IappCalEvaluacionFacade iappCalEvaluacionFacade;
    
    
    public void merge(IappTests evl)throws IappException{
        for(IappTestQuestions prg:evl.getIappPreguntasCollection()){
            if(prg.getIdPregunta()!=null){
                for(IappTestQuestionAnswers rsp:prg.getIappRespuestasCollection()){
                    if(rsp.getIdRespuesta()==null){
                        iappRespuestasFacade.create(rsp);
                    }
                }
            }
            else{
                iappPreguntaFacade.create(prg);
            }
        }
        iappEvaluacionesFacade.edit(evl);
    }

    public void crearEvaluacion(IappTests newEvaluacion) throws IappException {
        iappEvaluacionesFacade.create(newEvaluacion);
    }
    
    public void remove(IappTests evl) throws IappException {
        iappEvaluacionesFacade.remove(evl);
    }
    
    public void enviarRespuestas(Collection <IappTestSolutions> soluciones) throws IappException{
        for(IappTestSolutions solucion:soluciones){
            iappSolucionesFacade.create(solucion);
        }
    }

    public void calificarExamen(IappEnrollments matricula, IappTests evaluacion, IappTestResult calificacion) throws SQLException, IappException{
        iappCalEvaluacionFacade.calificar(matricula,evaluacion,calificacion);
    }
    public void refresh(IappTests evaluacion) throws IappException {
        iappEvaluacionesFacade.refreshEntity(evaluacion);
    }

    public void refresh(IappTestQuestions pregunta) throws IappException {
        iappPreguntaFacade.refreshEntity(pregunta);
    }

    public void iniciarExamen(IappTestResult cal) throws IappException {
        iappCalEvaluacionFacade.create(cal);
    }
}
 