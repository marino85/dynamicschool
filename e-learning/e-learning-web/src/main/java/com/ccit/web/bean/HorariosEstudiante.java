/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.dto.FiltroHorarioDto;
import com.ccit.ejb.dto.UsuarioDTo;
import com.ccit.ejb.fachada.GeneralFacadeBean;
import com.ccit.ejb.fachada.HorarioFacade;
import com.ccit.ejb.fachada.UsuariosFacade;
import com.ccit.ejb.modelo.IappHorario;
import com.ccit.ejb.modelo.IappHorarioEstudiante;
import com.ccit.ejb.modelo.IappUsuario;
import com.ccit.ejb.utilidades.UtilidadesFecha;
import com.ccit.web.obj.Horario;
import com.ccit.web.util.WebUtil;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author marino
 */
@ManagedBean
@ViewScoped
public class HorariosEstudiante {

    @EJB
    private UsuariosFacade usuariosFacade;
    @EJB
    private HorarioFacade horarioFacade;
    @EJB
    private GeneralFacadeBean generalFacadeBean;
    private List<Horario> horario = new ArrayList<Horario>();
    private Date semana = null;
    private FiltroHorarioDto filtro = new FiltroHorarioDto();
    private String messageInicioSemana;
    private String fecha;
    private String hora;
    private boolean showDialog = false;
    private List<IappHorario> horariosDisponibles = new ArrayList<IappHorario>();
    private IappHorario horarioSeleccionado;
    private String message;

    @PostConstruct
    public void init() {
        System.out.println("Init Horario Estudiante");
    }

    public void consultarHorario() {
        mostrarHorariosDisponibles();

    }

    public void fetchWeek(ActionEvent evt) {
        if (semana == null) {
            this.setMessageInicioSemana("Debe seleccionar una fecha");
            return;
        }



        Calendar c = Calendar.getInstance();
        c.setTime(semana);
        if (c.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            System.out.println("No es un Inicio de semana");
            this.setMessageInicioSemana("La fecha seleccionada no es un Inicio de semana");
            return;
        }
        UsuarioDTo user = (UsuarioDTo) WebUtil.getManageBeanFromSession("userLogged", UsuarioDTo.class);
        System.out.println(user.getIdCurso());
        if (horario != null) {
            horario.clear();
        }
        List<IappHorario> horarios = horarioFacade.buscarHorarioPorFechasNivel("13/08/2012", "18/08/2012", user.getIdNivel());

        horario.add(new Horario("7-8", fechaHorario("7-8", UtilidadesFecha.getFormatDate(c, Calendar.MONDAY), horarios), fechaHorario("7-8", UtilidadesFecha.getFormatDate(c, Calendar.TUESDAY), horarios), fechaHorario("7-8", UtilidadesFecha.getFormatDate(c, Calendar.WEDNESDAY), horarios), fechaHorario("7-8", UtilidadesFecha.getFormatDate(c, Calendar.THURSDAY), horarios), fechaHorario("7-8", UtilidadesFecha.getFormatDate(c, Calendar.FRIDAY), horarios), fechaHorario("7-8", UtilidadesFecha.getFormatDate(c, Calendar.SATURDAY), horarios)));
        horario.add(new Horario("8-9", fechaHorario("8-9", UtilidadesFecha.getFormatDate(c, Calendar.MONDAY), horarios), fechaHorario("8-9", UtilidadesFecha.getFormatDate(c, Calendar.TUESDAY), horarios), fechaHorario("8-9", UtilidadesFecha.getFormatDate(c, Calendar.WEDNESDAY), horarios), fechaHorario("8-9", UtilidadesFecha.getFormatDate(c, Calendar.THURSDAY), horarios), fechaHorario("8-9", UtilidadesFecha.getFormatDate(c, Calendar.FRIDAY), horarios), fechaHorario("8-9", UtilidadesFecha.getFormatDate(c, Calendar.SATURDAY), horarios)));
        horario.add(new Horario("9-10", fechaHorario("9-10", UtilidadesFecha.getFormatDate(c, Calendar.MONDAY), horarios), fechaHorario("9-10", UtilidadesFecha.getFormatDate(c, Calendar.TUESDAY), horarios), fechaHorario("9-10", UtilidadesFecha.getFormatDate(c, Calendar.WEDNESDAY), horarios), fechaHorario("9-10", UtilidadesFecha.getFormatDate(c, Calendar.THURSDAY), horarios), fechaHorario("9-10", UtilidadesFecha.getFormatDate(c, Calendar.FRIDAY), horarios), fechaHorario("9-10", UtilidadesFecha.getFormatDate(c, Calendar.SATURDAY), horarios)));
        horario.add(new Horario("10-11", fechaHorario("10-11", UtilidadesFecha.getFormatDate(c, Calendar.MONDAY), horarios), fechaHorario("10-11", UtilidadesFecha.getFormatDate(c, Calendar.TUESDAY), horarios), fechaHorario("10-11", UtilidadesFecha.getFormatDate(c, Calendar.WEDNESDAY), horarios), fechaHorario("10-11", UtilidadesFecha.getFormatDate(c, Calendar.THURSDAY), horarios), fechaHorario("10-11", UtilidadesFecha.getFormatDate(c, Calendar.FRIDAY), horarios), fechaHorario("10-11", UtilidadesFecha.getFormatDate(c, Calendar.SATURDAY), horarios)));
        horario.add(new Horario("11-12", fechaHorario("11-12", UtilidadesFecha.getFormatDate(c, Calendar.MONDAY), horarios), fechaHorario("11-12", UtilidadesFecha.getFormatDate(c, Calendar.TUESDAY), horarios), fechaHorario("11-12", UtilidadesFecha.getFormatDate(c, Calendar.WEDNESDAY), horarios), fechaHorario("11-12", UtilidadesFecha.getFormatDate(c, Calendar.THURSDAY), horarios), fechaHorario("11-12", UtilidadesFecha.getFormatDate(c, Calendar.FRIDAY), horarios), fechaHorario("11-12", UtilidadesFecha.getFormatDate(c, Calendar.SATURDAY), horarios)));
        horario.add(new Horario("13-14", fechaHorario("13-14", UtilidadesFecha.getFormatDate(c, Calendar.MONDAY), horarios), fechaHorario("13-14", UtilidadesFecha.getFormatDate(c, Calendar.TUESDAY), horarios), fechaHorario("13-14", UtilidadesFecha.getFormatDate(c, Calendar.WEDNESDAY), horarios), fechaHorario("13-14", UtilidadesFecha.getFormatDate(c, Calendar.THURSDAY), horarios), fechaHorario("13-14", UtilidadesFecha.getFormatDate(c, Calendar.FRIDAY), horarios), fechaHorario("13-14", UtilidadesFecha.getFormatDate(c, Calendar.SATURDAY), horarios)));
        horario.add(new Horario("14-15", fechaHorario("14-15", UtilidadesFecha.getFormatDate(c, Calendar.MONDAY), horarios), fechaHorario("14-15", UtilidadesFecha.getFormatDate(c, Calendar.TUESDAY), horarios), fechaHorario("14-15", UtilidadesFecha.getFormatDate(c, Calendar.WEDNESDAY), horarios), fechaHorario("14-15", UtilidadesFecha.getFormatDate(c, Calendar.THURSDAY), horarios), fechaHorario("14-15", UtilidadesFecha.getFormatDate(c, Calendar.FRIDAY), horarios), fechaHorario("14-15", UtilidadesFecha.getFormatDate(c, Calendar.SATURDAY), horarios)));
        horario.add(new Horario("15-16", fechaHorario("15-16", UtilidadesFecha.getFormatDate(c, Calendar.MONDAY), horarios), fechaHorario("15-16", UtilidadesFecha.getFormatDate(c, Calendar.TUESDAY), horarios), fechaHorario("15-16", UtilidadesFecha.getFormatDate(c, Calendar.WEDNESDAY), horarios), fechaHorario("15-16", UtilidadesFecha.getFormatDate(c, Calendar.THURSDAY), horarios), fechaHorario("15-16", UtilidadesFecha.getFormatDate(c, Calendar.FRIDAY), horarios), fechaHorario("15-16", UtilidadesFecha.getFormatDate(c, Calendar.SATURDAY), horarios)));
        horario.add(new Horario("16-17", fechaHorario("16-17", UtilidadesFecha.getFormatDate(c, Calendar.MONDAY), horarios), fechaHorario("16-17", UtilidadesFecha.getFormatDate(c, Calendar.TUESDAY), horarios), fechaHorario("16-17", UtilidadesFecha.getFormatDate(c, Calendar.WEDNESDAY), horarios), fechaHorario("16-17", UtilidadesFecha.getFormatDate(c, Calendar.THURSDAY), horarios), fechaHorario("16-17", UtilidadesFecha.getFormatDate(c, Calendar.FRIDAY), horarios), fechaHorario("16-17", UtilidadesFecha.getFormatDate(c, Calendar.SATURDAY), horarios)));
        horario.add(new Horario("17-18", fechaHorario("17-18", UtilidadesFecha.getFormatDate(c, Calendar.MONDAY), horarios), fechaHorario("17-18", UtilidadesFecha.getFormatDate(c, Calendar.TUESDAY), horarios), fechaHorario("17-18", UtilidadesFecha.getFormatDate(c, Calendar.WEDNESDAY), horarios), fechaHorario("17-18", UtilidadesFecha.getFormatDate(c, Calendar.THURSDAY), horarios), fechaHorario("17-18", UtilidadesFecha.getFormatDate(c, Calendar.FRIDAY), horarios), fechaHorario("17-18", UtilidadesFecha.getFormatDate(c, Calendar.SATURDAY), horarios)));
        horario.add(new Horario("19-20", fechaHorario("19-20", UtilidadesFecha.getFormatDate(c, Calendar.MONDAY), horarios), fechaHorario("19-20", UtilidadesFecha.getFormatDate(c, Calendar.TUESDAY), horarios), fechaHorario("19-20", UtilidadesFecha.getFormatDate(c, Calendar.WEDNESDAY), horarios), fechaHorario("19-20", UtilidadesFecha.getFormatDate(c, Calendar.THURSDAY), horarios), fechaHorario("19-20", UtilidadesFecha.getFormatDate(c, Calendar.FRIDAY), horarios), fechaHorario("19-20", UtilidadesFecha.getFormatDate(c, Calendar.SATURDAY), horarios)));
        horario.add(new Horario("20-21", fechaHorario("20-21", UtilidadesFecha.getFormatDate(c, Calendar.MONDAY), horarios), fechaHorario("20-21", UtilidadesFecha.getFormatDate(c, Calendar.TUESDAY), horarios), fechaHorario("20-21", UtilidadesFecha.getFormatDate(c, Calendar.WEDNESDAY), horarios), fechaHorario("20-21", UtilidadesFecha.getFormatDate(c, Calendar.THURSDAY), horarios), fechaHorario("20-21", UtilidadesFecha.getFormatDate(c, Calendar.FRIDAY), horarios), fechaHorario("20-21", UtilidadesFecha.getFormatDate(c, Calendar.SATURDAY), horarios)));


    }

    public String cancelarHorario() {



        try {
            UsuarioDTo user = (UsuarioDTo) WebUtil.getManageBeanFromSession("userLogged", UsuarioDTo.class);

            boolean estaInscrito = false;
            IappHorarioEstudiante hes = null;
            if (horarioSeleccionado.getIappHorarioEstudianteCollection() != null
                    && horarioSeleccionado.getIappHorarioEstudianteCollection().size() > 0) {
                for (IappHorarioEstudiante h : horarioSeleccionado.getIappHorarioEstudianteCollection()) {
                    if (h.getIdusuario() != null && h.getIdusuario().getIdUsuario() != null && h.getIdusuario().getIdUsuario() == user.getIdUsuario()) {

                        hes = h;
                        estaInscrito = true;
                    }
                }
            }
            if (estaInscrito) {
                //validamos que sean tres horas antes de la clase

                Calendar c = Calendar.getInstance();
                int horaactual = c.get(Calendar.HOUR_OF_DAY);
                System.out.println(horaactual);


                Calendar c2 = Calendar.getInstance();
                c2.setTime(hes.getIdHorario().getHora2());
                int horaclase = c2.get(Calendar.HOUR_OF_DAY);
                System.out.println(horaclase);
                if (!c.before(c2)) {
                    if ((horaclase - horaactual) <= 3) {
                        this.setMessage("Lo sentimos ud ya no puede cancelar esta clase");
                        return "";

                    }
                }


                IappHorarioEstudiante he = new IappHorarioEstudiante();
                he.setIdHorario(horarioSeleccionado);
                he.setIdusuario(new IappUsuario(user.getIdUsuario()));
                horarioFacade.cancelarEstudianteHorario(he);
//            horarioSeleccionado.getIappHorarioEstudianteCollection().remove(he);
//            horarioFacade.editarHorario(horarioSeleccionado);
                this.setMessage("La cancelación de la clase " + hes.getIdHorario().getIdClase().getDescripcion()
                        + " Aula: " + hes.getIdHorario().getIdAula().getDescripcion()
                        + " Hora: " + hes.getIdHorario().getHora() + " ha sido realizada con exito");
                this.setHorariosDisponibles(null);
                mostrarHorariosDisponibles();
            } else {
                this.setMessage("Ud no  se encuentra inscrito en este curso");
                return "";
            }
        } catch (Exception e) {
            System.out.println("Error Al Cancelar los Horarios");
            e.printStackTrace();
        }

        return "";
    }

    public String inscribirHorario() {
        UsuarioDTo user = (UsuarioDTo) WebUtil.getManageBeanFromSession("userLogged", UsuarioDTo.class);
        IappHorarioEstudiante he = new IappHorarioEstudiante();
        he.setIdHorario(horarioSeleccionado);
        he.setEstadoRegistro("A");
        he.setFechaCreacion(new Date());
        he.setFechaModificacion(new Date());
        he.setUsuarioCreacion(" ");
        he.setUsuarioModificacion(" ");
        he.setIdusuario(new IappUsuario(user.getIdUsuario()));

        //validamos que no este inscrito
        if (horarioSeleccionado.getIappHorarioEstudianteCollection() != null
                && horarioSeleccionado.getIappHorarioEstudianteCollection().size() > 0) {
            for (IappHorarioEstudiante h : horarioSeleccionado.getIappHorarioEstudianteCollection()) {
                if (h.getIdusuario() != null && h.getIdusuario().getIdUsuario() != null && h.getIdusuario().getIdUsuario() == user.getIdUsuario()) {
                    this.setMessage("Ud Ya se encuentra inscrito en este curso");
                    return "";
                }
            }
        }


        //Validamos que no se exceda los 12 estudiantes por clase
        if (horarioSeleccionado.getIappHorarioEstudianteCollection() != null
                && horarioSeleccionado.getIappHorarioEstudianteCollection().size() >= 12) {
            this.setMessage("El curso ya cuenta con el máximo de estudiantes inscritos, por favor intente en otro horario");
            return "";
        }



        IappUsuario usuarioEstudiante = usuariosFacade.findUser(user.getNumDoc(), user.getTipoDoc());

        if (usuarioEstudiante != null) {
            //valido jornada intensiva
            filtro.setIdUsuario(user.getIdUsuario());
            if (usuarioEstudiante.getIdJornada().getDescripcion().equalsIgnoreCase("Intensivo")) {
                if (horarioFacade.validarMaximoHorasEstudiante(filtro, 12)) {
                    this.setMessage("Ud excede el número máximo de horas permitidas para este semana");
                    return "";
                }
            } else {
                if (horarioFacade.validarMaximoHorasEstudiante(filtro)) {
                    this.setMessage("Ud excede el número máximo de horas permitidas para este semana");
                    return "";
                }
            }

        }

        

        try {
            horarioFacade.inscribirEstudianteHorario(he);
            horarioSeleccionado.getIappHorarioEstudianteCollection().add(he);
            horarioFacade.editarHorario(horarioSeleccionado);
            this.setMessage("Su inscripción ha sido realizada exitosamente");
            this.setHorariosDisponibles(null);
            mostrarHorariosDisponibles();
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }

        return "";
    }

    public String fechaHorario(String hora, String fecha, List<IappHorario> horarios) {

        for (IappHorario iappHorario : horarios) {

            if (iappHorario.getHora().equals(hora) && iappHorario.getFecha().equals(UtilidadesFecha.getDate(fecha))) {
                return new SimpleDateFormat("dd/MM/yyyy").format(iappHorario.getFecha());
            }

        }
        return "";

    }

    public void openDialog() {
        mostrarHorariosDisponibles();
        this.setShowDialog(true);
    }

    public void mostrarHorariosDisponibles() {

        UsuarioDTo user = (UsuarioDTo) WebUtil.getManageBeanFromSession("userLogged", UsuarioDTo.class);

        Calendar now = Calendar.getInstance();
        Calendar c = Calendar.getInstance();

        if (now.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            now.add(Calendar.DAY_OF_WEEK, 1);
            filtro.setFechaInicio(UtilidadesFecha.getFormatDate(now));
            c.add(Calendar.DAY_OF_WEEK, 6);
            filtro.setFechaFin(UtilidadesFecha.getFormatDate(c));
            System.out.println("Fecha Ini " + filtro.getFechaInicio());
            System.out.println("Fecha fin " + filtro.getFechaFin());
        } else {
            filtro.setFechaInicio(UtilidadesFecha.getFormatDate(now, Calendar.MONDAY));
            filtro.setFechaFin(UtilidadesFecha.getFormatDate(now, Calendar.SATURDAY));
            System.out.println("Fecha Ini " + filtro.getFechaInicio());
            System.out.println("Fecha fin " + filtro.getFechaFin());

        }

//        filtro.setFechaInicio(UtilidadesFecha.getFormatDate(c, Calendar.MONDAY));
//        filtro.setFechaFin(UtilidadesFecha.getFormatDate(c, Calendar.SATURDAY));
//        Calendar x = Calendar.getInstance();
//        if (now.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
//            System.out.println("OK");
//            x.roll(Calendar.DAY_OF_MONTH, 8);
//            System.out.println(x.getTime());
//            filtro.setFechaFin(UtilidadesFecha.getFormatDate(x));
//        }




        if (filtro.getIdSede() == null || filtro.getIdSede() == 0) {
            this.setMessage("Debe seleccionar una sede");
            return;
        }
        boolean filtrarPorClase = true;
        if (filtro.getIdClase() == null || filtro.getIdClase() == 0) {
            filtrarPorClase = false;
        }
        filtro.setIdNivel(user.getIdNivel());
        filtro.setIdProfesor(user.getIappUsuario().getIdProfesor());

        if (filtrarPorClase) {
            this.setHorariosDisponibles(horarioFacade.buscarHorarioFechaNivelSedeClase(filtro));
        } else {
            this.setHorariosDisponibles(horarioFacade.buscarHorarioFechaNivelSede(filtro));
        }
    }

    public void closeDialog(ActionEvent evt) {
        this.setMessage("");
        this.setShowDialog(false);
    }

    /**
     * Creates a new instance of HorariosEstudiante
     */
    public HorariosEstudiante() {
    }

    public Date getSemana() {
        return semana;
    }

    public void setSemana(Date semana) {
        this.semana = semana;
    }

    public String getMessageInicioSemana() {
        return messageInicioSemana;
    }

    public void setMessageInicioSemana(String messageInicioSemana) {
        this.messageInicioSemana = messageInicioSemana;
    }

    public List<Horario> getHorario() {
        return horario;
    }

    public void setHorario(List<Horario> horario) {
        this.horario = horario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }

    public List<IappHorario> getHorariosDisponibles() {
        return horariosDisponibles;
    }

    public void setHorariosDisponibles(List<IappHorario> horariosDisponibles) {
        this.horariosDisponibles = horariosDisponibles;
    }

    public IappHorario getHorarioSeleccionado() {
        return horarioSeleccionado;
    }

    public void setHorarioSeleccionado(IappHorario horarioSeleccionado) {
        this.horarioSeleccionado = horarioSeleccionado;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FiltroHorarioDto getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroHorarioDto filtro) {
        this.filtro = filtro;
    }
}
