/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.fachada.CursosFacade;
import com.ccit.ejb.modelo.IappCourses;
import com.ccit.exception.IappException;
import java.io.Serializable;
import java.util.ArrayList;
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
public class CursosTable implements Serializable{
    @EJB
    private CursosFacade cursosFacade;
    
    private ArrayList<IappCourses> selectedRows= new ArrayList<IappCourses>();
    private IappCourses courseDelete;
    private List<IappCourses> courses;
    private boolean showDialog=false;

    /**
     * Creates a new instance of UsuariosTable
     */
    public CursosTable() {
       
        
    }
    
    @PostConstruct
    public void init(){
        courses=cursosFacade.buscarCursos();
    }

    public void confirmar(){
        this.setShowDialog(true);
    }

    public void hideConfirmar(){
        this.setShowDialog(false);
    }

    public void eliminar(){
        try{
            cursosFacade.eliminarUsuario(courseDelete);
            actualizarTabla();
            this.setShowDialog(false);
        }
        catch(IappException e){
            e.printStackTrace();
        }
    }
    
    
    public void actualizarTabla(){
        courses=cursosFacade.buscarCursos();
    }
    
    
    public String edit(){
        return "editarCurso";
    }

   

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }

    public IappCourses getCourseDelete() {
        return courseDelete;
    }

    public void setCourseDelete(IappCourses courseDelete) {
        this.courseDelete = courseDelete;
    }

    public List<IappCourses> getCourses() {
        return courses;
    }

    public void setCourses(List<IappCourses> courses) {
        this.courses = courses;
    }

    public ArrayList<IappCourses> getSelectedRows() {
        return selectedRows;
    }

    public void setSelectedRows(ArrayList<IappCourses> selectedRows) {
        this.selectedRows = selectedRows;
    }
}
