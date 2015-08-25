/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.fachada.GeneralFacadeBean;
import com.ccit.ejb.fachada.HorarioFacade;
import com.ccit.ejb.modelo.*;
import com.ccit.exception.IappException;
import com.ccit.web.obj.Horario;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author marino
 */

@ManagedBean
@ViewScoped
public class HorariosBean implements Serializable{
    @EJB
    private HorarioFacade horarioFacade;
    
    @EJB
    private GeneralFacadeBean generalFacadeBean;
    
    
    
   private  Date semana = null;
   private  List<Horario> horario = new ArrayList<Horario>();   
   private String fecha;
   private String hora;
   private IappHorario selectedHorario = null;
   private String message;
   private String messageInicioSemana;
   private boolean showDialog=false;
   private List<IappHorario> horarioSeleccionado = new ArrayList<IappHorario>();
   private  List<IappAulas> aulas = new ArrayList<IappAulas>();
   private IappSede sedeSeleccionada= null;
   private Integer numSemanas;

    /**
     * Creates a new instance of HorariosBean
     */
    public HorariosBean() {
    }
    
    @PostConstruct
    public void init(){
        
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Inicia bean horarios Bean");
        
        this.selectedHorario= new IappHorario();
        this.selectedHorario.setIdAula(new IappAulas());
        this.selectedHorario.setIdUsuario(new IappUsuario());
        this.selectedHorario.setIdClase(new IappClase());
        this.selectedHorario.setIdNivel(new IappNiveles());
        this.selectedHorario.setCupoTotal(0);
        
        this.sedeSeleccionada= new IappSede();
        
        
    }

    public void fetchWeek(ActionEvent evt) {

        
        if(semana==null){
             this.setMessageInicioSemana("Debe seleccionar una fecha");
            return;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(semana);
        
        if(c.get(Calendar.DAY_OF_WEEK)!=Calendar.MONDAY){
            System.out.println("No es un Inicio de semana");
            this.setMessageInicioSemana("La fecha seleccionada no es un Inicio de semana");
            return ;
        }

        if(horario!=null)horario.clear();
        this.setMessageInicioSemana("");
        horario.add(new Horario("06-07", "" + getFormatDate(c, Calendar.MONDAY), "" + getFormatDate(c, Calendar.TUESDAY), "" + getFormatDate(c, Calendar.WEDNESDAY), "" + getFormatDate(c, Calendar.THURSDAY), "" + getFormatDate(c,  Calendar.FRIDAY), "" + getFormatDate(c,  Calendar.SATURDAY)));
        horario.add(new Horario("07-08", "" + getFormatDate(c, Calendar.MONDAY), "" + getFormatDate(c, Calendar.TUESDAY), "" + getFormatDate(c, Calendar.WEDNESDAY), "" + getFormatDate(c, Calendar.THURSDAY), "" + getFormatDate(c,  Calendar.FRIDAY), "" + getFormatDate(c,  Calendar.SATURDAY)));
        horario.add(new Horario("08-09", "" + getFormatDate(c, Calendar.MONDAY), "" + getFormatDate(c, Calendar.TUESDAY), "" + getFormatDate(c, Calendar.WEDNESDAY), "" + getFormatDate(c,  Calendar.THURSDAY), "" + getFormatDate(c,  Calendar.FRIDAY), "" + getFormatDate(c, Calendar.SATURDAY)));
        horario.add(new Horario("09-10","" + getFormatDate(c, Calendar.MONDAY), "" + getFormatDate(c, Calendar.TUESDAY), "" + getFormatDate(c, Calendar.WEDNESDAY), "" + getFormatDate(c,  Calendar.THURSDAY), "" + getFormatDate(c, Calendar.FRIDAY), "" + getFormatDate(c, Calendar.SATURDAY)));
        horario.add(new Horario("10-11","" + getFormatDate(c, Calendar.MONDAY), "" + getFormatDate(c, Calendar.TUESDAY), "" + getFormatDate(c, Calendar.WEDNESDAY), "" + getFormatDate(c,  Calendar.THURSDAY), "" + getFormatDate(c, Calendar.FRIDAY), "" + getFormatDate(c, Calendar.SATURDAY)));
        horario.add(new Horario("11-12","" + getFormatDate(c, Calendar.MONDAY), "" + getFormatDate(c, Calendar.TUESDAY), "" + getFormatDate(c, Calendar.WEDNESDAY), "" + getFormatDate(c,  Calendar.THURSDAY), "" + getFormatDate(c, Calendar.FRIDAY), "" + getFormatDate(c, Calendar.SATURDAY)));
        horario.add(new Horario("12-13","" + getFormatDate(c, Calendar.MONDAY), "" + getFormatDate(c, Calendar.TUESDAY), "" + getFormatDate(c, Calendar.WEDNESDAY), "" + getFormatDate(c,  Calendar.THURSDAY), "" + getFormatDate(c, Calendar.FRIDAY), "" + getFormatDate(c, Calendar.SATURDAY)));
        horario.add(new Horario("13-14","" + getFormatDate(c, Calendar.MONDAY), "" + getFormatDate(c,Calendar.TUESDAY), "" + getFormatDate(c, Calendar.WEDNESDAY), "" + getFormatDate(c,  Calendar.THURSDAY), "" + getFormatDate(c,  Calendar.FRIDAY), "" + getFormatDate(c, Calendar.SATURDAY)));
        horario.add(new Horario("14-15","" + getFormatDate(c, Calendar.MONDAY), "" + getFormatDate(c,Calendar.TUESDAY), "" + getFormatDate(c, Calendar.WEDNESDAY), "" + getFormatDate(c,  Calendar.THURSDAY), "" + getFormatDate(c,  Calendar.FRIDAY), "" + getFormatDate(c, Calendar.SATURDAY)));
        horario.add(new Horario("15-16","" + getFormatDate(c, Calendar.MONDAY), "" + getFormatDate(c, Calendar.TUESDAY), "" + getFormatDate(c, Calendar.WEDNESDAY), "" + getFormatDate(c,  Calendar.THURSDAY), "" + getFormatDate(c,  Calendar.FRIDAY), "" + getFormatDate(c,Calendar.SATURDAY)));
        horario.add(new Horario("16-17","" + getFormatDate(c, Calendar.MONDAY), "" + getFormatDate(c, Calendar.TUESDAY), "" + getFormatDate(c, Calendar.WEDNESDAY), "" + getFormatDate(c,  Calendar.THURSDAY), "" + getFormatDate(c,  Calendar.FRIDAY), "" + getFormatDate(c, Calendar.SATURDAY)));
        horario.add(new Horario("17-18","" + getFormatDate(c, Calendar.MONDAY), "" + getFormatDate(c, Calendar.TUESDAY), "" + getFormatDate(c, Calendar.WEDNESDAY), "" + getFormatDate(c,  Calendar.THURSDAY), "" + getFormatDate(c,  Calendar.FRIDAY), "" + getFormatDate(c, Calendar.SATURDAY)));
        horario.add(new Horario("18-19", "" + getFormatDate(c, Calendar.MONDAY), "" + getFormatDate(c,Calendar.TUESDAY), "" + getFormatDate(c, Calendar.WEDNESDAY), "" + getFormatDate(c,  Calendar.THURSDAY), "" + getFormatDate(c,  Calendar.FRIDAY), "" + getFormatDate(c, Calendar.SATURDAY)));
        horario.add(new Horario("19-20", "" + getFormatDate(c, Calendar.MONDAY), "" + getFormatDate(c, Calendar.TUESDAY), "" + getFormatDate(c, Calendar.WEDNESDAY), "" + getFormatDate(c,  Calendar.THURSDAY), "" + getFormatDate(c, Calendar.FRIDAY), "" + getFormatDate(c, Calendar.SATURDAY)));
        horario.add(new Horario("20-21", "" + getFormatDate(c, Calendar.MONDAY), "" + getFormatDate(c, Calendar.TUESDAY), "" + getFormatDate(c, Calendar.WEDNESDAY), "" + getFormatDate(c,  Calendar.THURSDAY), "" + getFormatDate(c,  Calendar.FRIDAY), "" + getFormatDate(c, Calendar.SATURDAY)));
       

       
    }
    
    
    public void openDialog(){
        System.out.println(hora);
        System.out.println(fecha);
        this.setHorarioSeleccionado(horarioFacade.buscarHorariosPorDiaHora(hora, fecha));
        this.setShowDialog(true);
    }
    public void closeDialog(ActionEvent evt){
        this.limpiarHorario();
        this.setMessage("");
        this.setShowDialog(false);
    }
    
    public void consultarHorario(){
        
        this.setHorarioSeleccionado(horarioFacade.buscarHorariosPorDiaHora(hora, fecha));
        
    }
    
    
    
    
    
    public void eliminarHorario(){
         if(selectedHorario==null){
            this.setMessage("Debe seleccionar un registro");
        }
         if(selectedHorario.getIappHorarioEstudianteCollection()!=null && selectedHorario.getIappHorarioEstudianteCollection().size()>0){
              this.setMessage("No se puede eliminar el registro");
              return;
         }
         
         try{
             horarioFacade.eliminarHorario(selectedHorario);
             this.setMessage("Se elimino el horario correctamente");
             consultarHorario();
             limpiarHorario();
         }
         catch(IappException e){
            this.setMessage("Error al eliminar el horario consulte su administrador");
            System.out.println("Error al guardar horario");
            e.printStackTrace();
            
        }
    }
    public void editarHorario(){
        if(selectedHorario==null){
            
            this.setMessage("Debe seleccionar un registro");
        }
        else{
            this.setSedeSeleccionada(selectedHorario.getIdAula().getIdSede());
            for (IappAulas aulas : generalFacadeBean.findAllAulas()) {
            if(aulas.getIdSede().getIdSede().toString().equals(selectedHorario.getIdAula().getIdSede().getIdSede().toString())){
                 this.getAulas().add(aulas);
            }
       }
        }
        
        
    }
    
    public void saveSchedule(){
        
        
        try{
            if(selectedHorario.getIdHorario()==null){
            
            if(horarioFacade.buscarHorariosPorDiaHoraAula(hora, fecha, selectedHorario.getIdAula().getIdAula()).size()>0){
                this.setMessage("No puede realizar una asignacion en este horario");
                limpiarHorario();
                return;
            }
            
           
            selectedHorario.setHora(hora);
            selectedHorario.setFecha(getDate(fecha));
            selectedHorario.setEstadoRegistro("A");
            selectedHorario.setFechaCreacion(new Date());
            selectedHorario.setFechaModificacion(new Date());
            selectedHorario.setUsuarioCreacion(" ");
            selectedHorario.setUsuarioModificacion(" ");
            selectedHorario.setCupoTotal(12);
            selectedHorario.setHora2(getDateTime(fecha, hora));
            horarioFacade.saveSchedule(selectedHorario);
            
             if(numSemanas!=null && numSemanas>0){
                for (int i = 1; i <= numSemanas; i++) {
                   Calendar nuevaFecha = Calendar.getInstance();
                   nuevaFecha.setTime(selectedHorario.getFecha());
                   Calendar hora2 = Calendar.getInstance();
                   hora2.setTime(selectedHorario.getHora2());
                   nuevaFecha.add(Calendar.WEEK_OF_MONTH, 1);
                   hora2.add(Calendar.WEEK_OF_MONTH, 1);
                   selectedHorario.setIdHorario(null);
                   selectedHorario.setFecha(nuevaFecha.getTime());
                   selectedHorario.setHora2(hora2.getTime());
                   horarioFacade.saveSchedule(selectedHorario);
                   
                    System.out.println("repitiendo cad a"+nuevaFecha);
                    
                }
            }
            
            
            this.setMessage("El horario fue asignado correctamente");
            
            }
            else{
                horarioFacade.editarHorario(selectedHorario);
                this.setMessage("El horario fue editado correctamente");
            }
            consultarHorario();
            limpiarHorario();
        }
        catch(IappException e){
            this.setMessage("Error al asignar el horario consulte su administrador");
            System.out.println("Error al guardar horario");
            e.printStackTrace();
            
        }
        catch(Exception ex){
           ex.printStackTrace();
        }
        
    }

    public String getFormatDate(Calendar c, int day) {
        String fecha = "";
        c.set(Calendar.DAY_OF_WEEK, day);

        fecha = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());



        return fecha;

    }
    
    public Date getDate(String date){
        try{
           return new SimpleDateFormat("dd/MM/yyyy").parse(date);
        }catch(Exception e){
            return null;
        }
        
    }
    
    public Date getDateTime(String date,String hour){
         try{
           return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(date+" "+hour.substring(0, 2)+":00:00");
        }catch(Exception e){
            return null;
        }
    }
    
    public List<SelectItem> getItemsSedes(){
         List<SelectItem> items= new ArrayList<SelectItem>();
         
         List<IappSede> sedes = generalFacadeBean.finAllSedes();
         
         for (IappSede iappSede : sedes) {
            items.add(new SelectItem(iappSede.getIdSede(), iappSede.getDescripcion()));
        }
         
         return items;
    }

   public void seleccionarSede(){
       System.out.println("entra a seleccinar sede");
       this.getAulas().clear();
      
       for (IappAulas aulasList : generalFacadeBean.findAllAulas()) {
           if(aulasList.getIdSede().getIdSede().equals(sedeSeleccionada.getIdSede())){
               this.getAulas().add(aulasList);
           }
       }
       
       
       
   }

    public void limpiarHorario(){
        this.selectedHorario= new IappHorario();
       this.selectedHorario.setIdAula(new IappAulas());
        this.selectedHorario.setIdUsuario(new IappUsuario());
        this.selectedHorario.setIdClase(new IappClase());
        this.selectedHorario.setIdNivel(new IappNiveles());
        this.selectedHorario.setCupoTotal(0);
         
        this.setSedeSeleccionada(new IappSede());
        
        
    }
    public Date getSemana() {
        return semana;
    }

    public void setSemana(Date semana) {
        this.semana = semana;
    }

    public List<Horario> getHorario() {
        return horario;
    }

    public void setHorario(List<Horario> horario) {
        this.horario = horario;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
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

    public IappHorario getSelectedHorario() {
        return selectedHorario;
    }

    public void setSelectedHorario(IappHorario selectedHorario) {
        this.selectedHorario = selectedHorario;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<IappHorario> getHorarioSeleccionado() {
        return horarioSeleccionado;
    }

    public void setHorarioSeleccionado(List<IappHorario> horarioSeleccionado) {
        this.horarioSeleccionado = horarioSeleccionado;
    }

    public String getMessageInicioSemana() {
        return messageInicioSemana;
    }

    public void setMessageInicioSemana(String messageInicioSemana) {
        this.messageInicioSemana = messageInicioSemana;
    }

    public List<IappAulas> getAulas() {
        return aulas;
    }

    public void setAulas(List<IappAulas> aulas) {
        this.aulas = aulas;
    }

    public IappSede getSedeSeleccionada() {
        return sedeSeleccionada;
    }

    public void setSedeSeleccionada(IappSede sedeSeleccionada) {
        this.sedeSeleccionada = sedeSeleccionada;
    }

    public Integer getNumSemanas() {
        return numSemanas;
    }

    public void setNumSemanas(Integer numSemanas) {
        this.numSemanas = numSemanas;
    }
    
    
    
    
    
    
}
