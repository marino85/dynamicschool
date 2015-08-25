/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.fachada.CursosFacade;
import com.ccit.ejb.modelo.*;
import com.ccit.exception.IappException;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author marino
 */
@ManagedBean
@SessionScoped
public class CalDocenteBean {

    @EJB
    private CursosFacade cF;
    private IappCursos curso;
    private IappMatriculas matricula;
    private Double notaFinal;
    private IappCalCurso calCurso;

    @PostConstruct
    public void init() {
    }

    public void calificarCurso() {
        calCurso = matricula.getIappCalCurso();
        if (calCurso == null) {
            calCurso = new IappCalCurso();
            
        }
        calCurso.setIdMatricula(matricula.getIdMatricula());
        calCurso.setIappMatriculas(matricula);

        calCurso.setCalTrabajos(new BigDecimal(getPromedioTrabajos()));
        calCurso.setCalEvaluaciones(new BigDecimal(getPromedioEvaluaciones()));
       

        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal nFinal = BigDecimal.ZERO;
        BigDecimal pTrabajaos = BigDecimal.ZERO;
        BigDecimal pEvaluaciones = BigDecimal.ZERO;
        BigDecimal pAdicional = BigDecimal.ZERO;

        pTrabajaos = calCurso.getCalTrabajos().multiply(matricula.getIdCurso().getPorcentajetrabajos());
        pEvaluaciones = calCurso.getCalEvaluaciones().multiply(matricula.getIdCurso().getPorcentajeevaluaciones());
        pAdicional = calCurso.getCalAdicional().multiply(matricula.getIdCurso().getPorcentajefinal());

        nFinal = sum.add(pTrabajaos).add(pEvaluaciones).add(pAdicional);
        calCurso.setCalTotal(nFinal);
        try {
            cF.calificarCurso(calCurso);
        } catch (IappException e) {
            e.printStackTrace();
        }


    }

    public double getPromedioTrabajos() {

        List<IappCalAsignaciones> trabajos = (List<IappCalAsignaciones>) matricula.getIappCalAsignacionesCollection();
        BigDecimal sum = BigDecimal.ZERO;
        for (IappCalAsignaciones iappCalAsignaciones : trabajos) {
            if (iappCalAsignaciones.getCalificacion() != null) {
                sum = sum.add(iappCalAsignaciones.getCalificacion());
            }
        }
        return sum.doubleValue() / trabajos.size();


    }

    public double getPromedioEvaluaciones() {

        List<IappCalEvaluacion> evaluaciones = (List<IappCalEvaluacion>) matricula.getIappCalEvaluacionCollection();
        BigDecimal sum = BigDecimal.ZERO;
        for (IappCalEvaluacion iappCalEvaluacion : evaluaciones) {
            if (iappCalEvaluacion.getCalificacion() != null) {
                sum = sum.add(iappCalEvaluacion.getCalificacion());
            }

        }

        return sum.doubleValue() / evaluaciones.size();
    }

    public IappCursos getCurso() {
        return curso;
    }

    public void setCurso(IappCursos curso) {
        this.curso = curso;
    }

    public IappMatriculas getMatricula() {
        return matricula;
    }

    public void setMatricula(IappMatriculas matricula) {
        this.matricula = matricula;
        
        if(this.matricula.getIappCalCurso()==null)
            this.matricula.setIappCalCurso(new IappCalCurso());
    }

    public Double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(Double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public IappCalCurso getCalCurso() {
        return calCurso;
    }

    public void setCalCurso(IappCalCurso calCurso) {
        this.calCurso = calCurso;
    }

    /**
     * Creates a new instance of CalDocenteBean
     */
    public CalDocenteBean() {
    }
}
