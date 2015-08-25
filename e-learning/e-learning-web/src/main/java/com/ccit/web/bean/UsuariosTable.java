/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.web.bean;

import com.ccit.ejb.dto.FiltroUsuariosDto;
import com.ccit.ejb.fachada.UsuariosFacade;
import com.ccit.ejb.modelo.IappUsuario;
import com.ccit.exception.IappException;
import com.ccit.web.util.Paginador;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;


/**
 *
 * @author marino
 */
@ManagedBean
@ViewScoped
public class UsuariosTable implements Serializable{
    @EJB
    private UsuariosFacade usuariosFacade;
    
   
    
    private ArrayList<IappUsuario> selectedRows= new ArrayList<IappUsuario>();
    
    private IappUsuario userDelete;
    
    private List<IappUsuario> users;
    
    private boolean showDialog=false;
    
    private DataModel personas;
    
    private Paginador paginador;
    
    private FiltroUsuariosDto filtroUsuarios;
    
    

    /**
     * Creates a new instance of UsuariosTable
     */
    public UsuariosTable() {
       
        
    }
    
    public Paginador getpaginador(){
        if (paginador == null) {
            paginador = new Paginador(10) {

                @Override
                public int getItemsCount() {
                    return usuariosFacade.count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(usuariosFacade.findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return paginador;
    }
    
    @PostConstruct
    public void init(){
        users=usuariosFacade.buscarUsuarios();
        this.filtroUsuarios= new FiltroUsuariosDto();
    }
    
    
    public void buscarUsuarios(){
        users=usuariosFacade.getEstudiantes(this.filtroUsuarios);
    }

    public List<IappUsuario> getUsers() {
        return users;
    }

    public void setUsers(List<IappUsuario> users) {
        this.users = users;
    }
    
    
    public void confirmar(ActionEvent evt){
        this.setShowDialog(true);
    }
    
    public void eliminar(ActionEvent evt){
        
        try{
            
            usuariosFacade.eliminarUsuario(userDelete);
            actualizarTabla();
            this.setShowDialog(false);
        }
        catch(IappException e){
            e.printStackTrace();
        }
    }
    
     public void cancelarEliminar(ActionEvent evt){
        
       
          
            this.setShowDialog(false);
       
    }
    
    
    public void actualizarTabla(){
        users=usuariosFacade.buscarUsuarios();
    }
    
    
    public String edit(){
        return "editarEstudiante";
    }

   

    public ArrayList<IappUsuario> getSelectedRows() {
        return selectedRows;
    }

    public void setSelectedRows(ArrayList<IappUsuario> selectedRows) {
        this.selectedRows = selectedRows;
    }

    public IappUsuario getUserDelete() {
        return userDelete;
    }

    public void setUserDelete(IappUsuario userDelete) {
        this.userDelete = userDelete;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }

    public FiltroUsuariosDto getFiltroUsuarios() {
        return filtroUsuarios;
    }

    public void setFiltroUsuarios(FiltroUsuariosDto filtroUsuarios) {
        this.filtroUsuarios = filtroUsuarios;
    }

    public Paginador getPaginador() {
        return paginador;
    }

    public void setPaginador(Paginador paginador) {
        this.paginador = paginador;
    }

    public DataModel getPersonas() {
        return personas;
    }

    public void setPersonas(DataModel personas) {
        this.personas = personas;
    }

    public UsuariosFacade getUsuariosFacade() {
        return usuariosFacade;
    }

    public void setUsuariosFacade(UsuariosFacade usuariosFacade) {
        this.usuariosFacade = usuariosFacade;
    }
    
    
    
    
    
}
