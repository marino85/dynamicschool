/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.ejb.modelo.IappTestResult;
import com.ccit.ejb.modelo.IappTests;
import com.ccit.ejb.modelo.IappEnrollments;
import com.ccit.exception.IappException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author innovasoft
 */
@Stateless
public class IappCalEvaluacionFacade  extends AbstractFacade<IappTestResult>  {
    
    @PersistenceContext(unitName = "e-learning-ejb_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IappCalEvaluacionFacade() {
        super(IappTestResult.class);
    }

    public void calificar(IappEnrollments matricula, IappTests evaluacion, IappTestResult calificacion) throws SQLException, IappException {
        Connection con=em.unwrap(Connection.class);
        PreparedStatement query = con.prepareCall("select general.getcalificacionevaluacion(?,?) calificacion");
        System.out.println("evaluacion.getIdEvaluacion().intValue():"+evaluacion.getIdEvaluacion().intValue());
        System.out.println("matricula.getIdMatricula().intValue():"+matricula.getIdMatricula().intValue());
        query.setInt(1, evaluacion.getIdEvaluacion().intValue());
        query.setInt(2, matricula.getIdMatricula().intValue());
        ResultSet rs= query.executeQuery();
        if(rs.next()){
            System.out.println("rs.getInt('calificacion')"+rs.getInt("calificacion"));
            calificacion.setNoCorrectas(rs.getInt("calificacion"));
            calificacion.setUsuarioCreacion("1");
            calificacion.setUsuarioModificacion("1");
            double totalPreguntas=evaluacion.getIappPreguntasCollection().size();
            double noCorrectas = calificacion.getNoCorrectas();
            double nota = (noCorrectas/totalPreguntas) *5;            
            calificacion.setCalificacion(BigDecimal.valueOf(nota));
            this.edit(calificacion);
        }
        rs.close();
        query.close();
    }
}
