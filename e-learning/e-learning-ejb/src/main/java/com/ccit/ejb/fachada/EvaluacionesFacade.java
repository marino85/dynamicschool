/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada;
import com.ccit.ejb.modelo.IappEvaluaciones;
import com.ccit.ejb.modelo.IappPreguntas;
import com.ccit.ejb.modelo.IappMatriculas;
import com.ccit.ejb.modelo.IappRespuestas;
import com.ccit.ejb.modelo.IappCalEvaluacion;
import com.ccit.ejb.modelo.IappSoluciones;
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
    
    
    public void merge(IappEvaluaciones evl)throws IappException{
        for(IappPreguntas prg:evl.getIappPreguntasCollection()){
            if(prg.getIdPregunta()!=null){
                for(IappRespuestas rsp:prg.getIappRespuestasCollection()){
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

    public void crearEvaluacion(IappEvaluaciones newEvaluacion) throws IappException {
        iappEvaluacionesFacade.create(newEvaluacion);
    }
    
    public void remove(IappEvaluaciones evl) throws IappException {
        iappEvaluacionesFacade.remove(evl);
    }
    
    public void enviarRespuestas(Collection <IappSoluciones> soluciones) throws IappException{
        for(IappSoluciones solucion:soluciones){
            iappSolucionesFacade.create(solucion);
        }
    }

    public void calificarExamen(IappMatriculas matricula, IappEvaluaciones evaluacion, IappCalEvaluacion calificacion) throws SQLException, IappException{
        iappCalEvaluacionFacade.calificar(matricula,evaluacion,calificacion);
    }
    public void refresh(IappEvaluaciones evaluacion) throws IappException {
        iappEvaluacionesFacade.refreshEntity(evaluacion);
    }

    public void refresh(IappPreguntas pregunta) throws IappException {
        iappPreguntaFacade.refreshEntity(pregunta);
    }

    public void iniciarExamen(IappCalEvaluacion cal) throws IappException {
        iappCalEvaluacionFacade.create(cal);
    }
}
 