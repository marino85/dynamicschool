/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.fachada;

import com.ccit.ejb.dto.FiltroHorarioDto;
import com.ccit.ejb.fachada.impl.IappHorarioEstudianteFacade;
import com.ccit.ejb.fachada.impl.IappHorarioFacade;
import com.ccit.ejb.modelo.IappHorario;
import com.ccit.ejb.modelo.IappHorarioEstudiante;
import com.ccit.ejb.utilidades.UtilidadesFecha;
import com.ccit.exception.IappException;
import java.util.List;
import javax.ejb.*;

/**
 *
 * @author marino
 */
@Stateless
@LocalBean
public class HorarioFacade {

    @EJB
    private IappHorarioEstudianteFacade iappHorarioEstudianteFacade;
    @EJB
    private IappHorarioFacade iappHorarioFacade;

    public void saveSchedule(IappHorario schedule) throws IappException {
        iappHorarioFacade.create(schedule);
    }

    public void cancelarEstudianteHorario(IappHorarioEstudiante estudianteHorario) throws IappException {      
        
        IappHorarioEstudiante ent = iappHorarioFacade.buscarHorarioEstudiante(estudianteHorario);
//        System.out.println(ent.getIdusuario().getIdUsuario());
//        System.out.println(ent.getIdHorario().getIdHorario());
        iappHorarioEstudianteFacade.remove(ent);
    }
    
    public void inscribirEstudianteHorario(IappHorarioEstudiante estudianteHorario) throws IappException {
        iappHorarioEstudianteFacade.create(estudianteHorario);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<IappHorario> buscarHorariosPorDiaHoraAula(String hora, String fecha, Integer idAula) {


        return iappHorarioFacade.buscarHorariosPorDiaHoraAula(hora, fecha, idAula);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<IappHorario> buscarHorariosPorDiaHora(String hora, String fecha) {

        return iappHorarioFacade.buscarHorariosPorDiaHora(hora, fecha);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<IappHorario> buscarHorariosPorDiaHoraAulaCurso(String hora, String fecha, Integer idAula, Integer curso) {

        return iappHorarioFacade.buscarHorariosPorDiaHoraAulaCurso(hora, fecha, idAula, curso);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<IappHorario> buscarHorarioPorFechasNivel(String fechaIni, String fechaFin, Integer nivel) {
        return iappHorarioFacade.buscarHorarioPorFechasNivel(fechaIni, fechaFin, nivel);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<IappHorario> buscarHorarioHoraFechaNivel(String fecha, String hora, Integer nivel) {
        return iappHorarioFacade.buscarHorarioHoraFechaNivel(fecha, hora, nivel);
    }

    public void editarHorario(IappHorario entity) throws IappException {
        iappHorarioFacade.edit(entity);
    }

    public void eliminarHorario(IappHorario entity) throws IappException {
        iappHorarioFacade.remove(entity);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<IappHorario> buscarHorarioFechaNivelSede(FiltroHorarioDto dto) {
        return iappHorarioFacade.buscarHorarioFechaNivelSede(dto);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<IappHorario> buscarHorarioFechaNivelSedeClase(FiltroHorarioDto dto) {


        return iappHorarioFacade.buscarHorarioFechaNivelSedeClase(dto);
    }
    
    public boolean validarMaximoHorasEstudiante(FiltroHorarioDto dto){
        
         return iappHorarioFacade.validarMaximoHorasEstudiante(dto);
    }
    
     public boolean validarMaximoHorasEstudiante(FiltroHorarioDto dto,Integer maxHoras){
        
         return iappHorarioFacade.validarMaximoHorasEstudiante(dto,maxHoras);
    }
     
    
    
    
    
}

