/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.ejb.constants.Constants;
import com.ccit.ejb.modelo.IappCalEvaluacion;
import com.ccit.ejb.modelo.IappEvaluaciones;
import com.ccit.ejb.modelo.IappMatriculas;
import com.ccit.exception.IappException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author innovasoft
 */
@Stateless
public class IappCalEvaluacionFacade  extends AbstractFacade<IappCalEvaluacion>  {
    
    @PersistenceContext(unitName = "DinamicPortal-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IappCalEvaluacionFacade() {
        super(IappCalEvaluacion.class);
    }

    public void calificar(IappMatriculas matricula, IappEvaluaciones evaluacion, IappCalEvaluacion calificacion) throws SQLException, IappException {
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
