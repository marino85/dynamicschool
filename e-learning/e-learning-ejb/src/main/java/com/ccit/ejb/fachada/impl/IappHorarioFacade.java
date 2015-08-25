/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada.impl;

import com.ccit.ejb.dto.FiltroHorarioDto;
import com.ccit.ejb.modelo.IappHorario;
import com.ccit.ejb.modelo.IappHorarioEstudiante;
import com.ccit.ejb.utilidades.UtilidadesFecha;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marino
 */
@Stateless
public class IappHorarioFacade extends AbstractFacade<IappHorario> {

    @PersistenceContext(unitName = "DinamicPortal-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IappHorarioFacade() {
        super(IappHorario.class);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<IappHorario> buscarHorariosPorDiaHoraAula(String hora, String fecha, Integer idAula) {


        return this.getEntityManager().createNamedQuery("IappHorario.findByUniqueKeyHoraFechaAula").setParameter("hora", hora).setParameter("fecha", UtilidadesFecha.getDate(fecha)).setParameter("idAula", idAula).getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<IappHorario> buscarHorariosPorDiaHora(String hora, String fecha) {

        return this.getEntityManager().createNamedQuery("IappHorario.findByUniqueKeyHoraFecha").setParameter("hora", hora).setParameter("fecha", UtilidadesFecha.getDate(fecha)).getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<IappHorario> buscarHorariosPorDiaHoraAulaCurso(String hora, String fecha, Integer idAula, Integer curso) {


        return this.getEntityManager().createNamedQuery("IappHorario.findByUniqueKeyHoraFechaAulaCurso").setParameter("hora", hora).setParameter("fecha", UtilidadesFecha.getDate(fecha)).setParameter("idAula", idAula).setParameter("idCurso", curso).getResultList();
    }
    
    
     @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<IappHorario> buscarHorarioPorFechasNivel(String fechaIni,String fechaFin,Integer nivel){
        return this.getEntityManager().createNamedQuery("IappHorario.findByFechasNivel").setParameter("fechaIni", UtilidadesFecha.getDate(fechaIni)).setParameter("fechaFin",UtilidadesFecha.getDate(fechaFin))
                .setParameter("idNivel", nivel).getResultList();
    }
     @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
     public IappHorarioEstudiante buscarHorarioEstudiante(IappHorarioEstudiante estudianteHorario){
         return (IappHorarioEstudiante)this.getEntityManager().createNamedQuery("IappHorarioEstudiante.findByIdUsuarioIdHorario")
                 .setParameter("idHorario", estudianteHorario.getIdHorario().getIdHorario()).
                 setParameter("idusuario", estudianteHorario.getIdusuario().getIdUsuario()).getSingleResult();
                
     }
     
      @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
     public List<IappHorario> buscarHorarioHoraFechaNivel(String fecha,String hora,Integer nivel){
        return this.getEntityManager().createNamedQuery("IappHorario.findByFechaHoraNivel").setParameter("fecha", UtilidadesFecha.getDate(fecha)).setParameter("hora", hora)
                .setParameter("idNivel", nivel).getResultList();
    }
      
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<IappHorario> buscarHorarioFechaNivelSede(FiltroHorarioDto dto) {
        return this.getEntityManager().createNamedQuery("IappHorario.findByFechaNivelSede").
                setParameter("fechaIni", UtilidadesFecha.getDate(dto.getFechaInicio()))
                .setParameter("fechaFin", UtilidadesFecha.getDate(dto.getFechaFin()))
                .setParameter("idNivel", dto.getIdNivel())
                .setParameter("idSede", dto.getIdSede()).getResultList();
//                .setParameter("idUsuario", dto.getIdProfesor()).getResultList();
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<IappHorario> buscarHorarioFechaNivelSedeClase(FiltroHorarioDto dto) {
        
        
        return this.getEntityManager().createNamedQuery("IappHorario.findByFechaNivelSedeClase").
                setParameter("fechaIni", UtilidadesFecha.getDate(dto.getFechaInicio()))
                .setParameter("fechaFin", UtilidadesFecha.getDate(dto.getFechaFin()))
                .setParameter("idNivel", dto.getIdNivel())
                .setParameter("idSede", dto.getIdSede())
                .setParameter("idClase", dto.getIdClase()).getResultList();
//                .setParameter("idUsuario", dto.getIdProfesor()).getResultList();
    }
    
    public boolean validarMaximoHorasEstudiante(FiltroHorarioDto dto){
        
         List list =       this.getEntityManager().createNamedQuery("IappHorarioEstudiante.findHorariosEstudianteSemana").
                 setParameter("fechaIni", UtilidadesFecha.getDate(dto.getFechaInicio())).setParameter("fechaFIn", UtilidadesFecha.getDate(dto.getFechaFin())).setParameter("idUsuario",dto.getIdUsuario()).getResultList();
                
           if(list!=null && list.size()>=6)     {
               return true;
           }
           return false;
    }
    
     public boolean validarMaximoHorasEstudiante(FiltroHorarioDto dto,Integer maxHoras){
        
         List list =       this.getEntityManager().createNamedQuery("IappHorarioEstudiante.findHorariosEstudianteSemana").
                 setParameter("fechaIni", UtilidadesFecha.getDate(dto.getFechaInicio())).setParameter("fechaFIn", UtilidadesFecha.getDate(dto.getFechaFin())).setParameter("idUsuario",dto.getIdUsuario()).getResultList();
                
           if(list!=null && list.size()>=maxHoras)     {
               return true;
           }
           return false;
    }
     
      
      
}
