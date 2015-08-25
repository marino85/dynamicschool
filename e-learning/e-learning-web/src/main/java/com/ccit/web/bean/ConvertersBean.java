///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.ccit.web.bean;
//
//import com.ccit.ejb.modelo.*;
//import com.ccit.web.util.WebUtil;
//import java.io.Serializable;
//import java.util.List;
//import javax.enterprise.context.SessionScoped;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.inject.Named;
//
///**
// *
// * @author innovasoft
// */
//@Named(value = "convertersBean")
//@SessionScoped
//public class ConvertersBean implements Serializable {
//    private GeneralListBean gb;
//    /**
//     * Creates a new instance of ConvertersBean
//     */
//    public ConvertersBean() {
//        gb = (GeneralListBean)WebUtil.getManageBeanFromSession("generalListBean", GeneralListBean.class);
//    }
//    
//    public Converter getPerfilesConverter(){
//        return new Converter() {
//
//            @Override
//            public Object getAsObject(FacesContext context, UIComponent component, String value) {
//                if(value==null){
//                    return null;
//                }
//                List<IappPerfiles> perfiles=gb.getPerfiles();
//                for(IappPerfiles prf:perfiles){
//                    if(value.equals(prf.toString())){
//                        return prf;
//                    }
//                }
//                return null;
//            }
//
//            @Override
//            public String getAsString(FacesContext context, UIComponent component, Object value) {
//                return value.toString();
//            }
//        };
//    }
//    
//    public Converter getTipoDocConverter(){
//        return new Converter() {
//
//            @Override
//            public Object getAsObject(FacesContext context, UIComponent component, String value) {
//                if(value==null){
//                    return null;
//                }
//                List<IappDocumentType> tiposDoc=gb.getTiposDoc();
//                for(IappDocumentType td:tiposDoc){
//                    if(value.equals(td.toString())){
//                        return td;
//                    }
//                }
//                return null;
//            }
//
//            @Override
//            public String getAsString(FacesContext context, UIComponent component, Object value) {
//                return value.toString();
//            }
//        };
//    }
//
//    public Converter getJornadaConverter(){
//        return new Converter() {
//
//            @Override
//            public Object getAsObject(FacesContext context, UIComponent component, String value) {
//                if(value==null){
//                    return null;
//                }
//                List<IappJornada> jornada=gb.getJornada();
//                for(IappJornada jr:jornada){
//                    if(value.equals(jr.toString())){
//                        return jr;
//                    }
//                }
//                return null;
//            }
//
//            @Override
//            public String getAsString(FacesContext context, UIComponent component, Object value) {
//                return value.toString();
//            }
//        };
//    }
//
//    public Converter getEstadosUsuarioConverter(){
//        return new Converter() {
//
//            @Override
//            public Object getAsObject(FacesContext context, UIComponent component, String value) {
//                if(value==null){
//                    return null;
//                }
//                List<IappEstadoUsuario> estados=gb.getEstadosUsuario();
//                for(IappEstadoUsuario jr:estados){
//                    if(value.equals(jr.toString())){
//                        return jr;
//                    }
//                }
//                return null;
//            }
//
//            @Override
//            public String getAsString(FacesContext context, UIComponent component, Object value) {
//                return value.toString();
//            }
//        };
//    }
//    
//    public Converter getEstudiantesConverter(){
//        return new Converter() {
//
//            @Override
//            public Object getAsObject(FacesContext context, UIComponent component, String value) {
//                if(value==null){
//                    return null;
//                }
//                List<IappUser> usuario=gb.getEstudiantesList();
//                for(IappUser us:usuario){
//                    if(value.equals(us.toString())){
//                        return us;
//                    }
//                }
//                return null;
//            }
//
//            @Override
//            public String getAsString(FacesContext context, UIComponent component, Object value) {
//                return value.toString();
//            }
//        };
//    }
//
//    public Converter getNivelesConverter(){
//        return new Converter() {
//            @Override
//            public Object getAsObject(FacesContext context, UIComponent component, String value) {
//                if(value==null){
//                    return null;
//                }
//                List<IappNiveles> niveles=gb.getNivelesCurso();
//                for(IappNiveles nc:niveles){
//                    if(value.equals(nc.toString())){
//                        return nc;
//                    }
//                }
//                return null;
//            }
//
//            @Override
//            public String getAsString(FacesContext context, UIComponent component, Object value) {
//                return value.toString();
//            }
//        };
//    }
//
//    public Converter getEstadosCursoConverter(){
//        return new Converter() {
//            @Override
//            public Object getAsObject(FacesContext context, UIComponent component, String value) {
//                if(value==null){
//                    return null;
//                }
//                List<IappEstadosCurso> estados=gb.getEstadosCurso();
//                for(IappEstadosCurso ec:estados){
//                    if(value.equals(ec.toString())){
//                        return ec;
//                    }
//                }
//                return null;
//            }
//
//            @Override
//            public String getAsString(FacesContext context, UIComponent component, Object value) {
//                return value.toString();
//            }
//        };
//    }
//
//    public Converter getUsuariosConverter(){
//        return new Converter() {
//            @Override
//            public Object getAsObject(FacesContext context, UIComponent component, String value) {
//                if(value==null){
//                    return null;
//                }
//                List<IappUser> usuarios=gb.getUsuarios();
//                for(IappUser usr:usuarios){
//                    if(value.equals(usr.toString())){
//                        return usr;
//                    }
//                }
//                return null;
//            }
//
//            @Override
//            public String getAsString(FacesContext context, UIComponent component, Object value) {
//                return value.toString();
//            }
//        };
//    }
//
//    public Converter getCursosConverter(){
//        return new Converter() {
//            @Override
//            public Object getAsObject(FacesContext context, UIComponent component, String value) {
//                if(value==null){
//                    return null;
//                }
//                List<IappCourses> cursos=gb.getCursos();
//                for(IappCourses crs:cursos){
//                    if(value.equals(crs.getIdCurso().toString())){
//                        return crs;
//                    }
//                }
//                return null;
//            }
//
//            @Override
//            public String getAsString(FacesContext context, UIComponent component, Object value) {
//                return value.toString();
//            }
//        };
//    }
//    
//    public Converter getAulasConverter(){
//        return new Converter() {
//            @Override
//            public Object getAsObject(FacesContext context, UIComponent component, String value) {
//                if(value==null){
//                    return null;
//                }
//                List<IappAulas> aulas=gb.getAulas();
//                for(IappAulas als:aulas){
//                    if(value.equals(als.toString())){
//                        return als;
//                    }
//                }
//                return null;
//            }
//
//            @Override
//            public String getAsString(FacesContext context, UIComponent component, Object value) {
//                return value.toString();
//            }
//        };
//    }
//    
//    
//    public Converter getClasesConverter(){
//        return new Converter() {
//            @Override
//            public Object getAsObject(FacesContext context, UIComponent component, String value) {
//                if(value==null){
//                    return null;
//                }
//                List<IappClase> clases=gb.getClases();
//                for(IappClase cls:clases){
//                    if(value.equals(cls.toString())){
//                        return cls;
//                    }
//                }
//                return null;
//            }
//
//            @Override
//            public String getAsString(FacesContext context, UIComponent component, Object value) {
//                return value.toString();
//            }
//        };
//    }
//    
//    
//    public Converter getSedesConverter(){
//        return new Converter() {
//            @Override
//            public Object getAsObject(FacesContext context, UIComponent component, String value) {
//                if(value==null){
//                    return null;
//                }
//                List<IappSede> sede=gb.getSedes();
//                for(IappSede cls:sede){
//                    if(value.equals(cls.getIdSede().toString())){
//                        return cls;
//                    }
//                }
//                return null;
//            }
//
//            @Override
//            public String getAsString(FacesContext context, UIComponent component, Object value) {
//                return value.toString();
//            }
//        };
//    }
//    
//    
//    
//    
//    
//    
//}
